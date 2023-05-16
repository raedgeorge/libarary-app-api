package com.atech.libarary.service;

import com.atech.libarary.dao.BookRepository;
import com.atech.libarary.dao.CheckoutRepository;
import com.atech.libarary.dao.HistoryRepository;
import com.atech.libarary.entity.Book;
import com.atech.libarary.entity.Checkout;
import com.atech.libarary.entity.History;
import com.atech.libarary.responsemodels.ShelfCurrentLoansResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author raed abu Sa'da
 * on 09/05/2023
 */

@Service
@Transactional
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final HistoryRepository historyRepository;
    private final CheckoutRepository checkoutRepository;

    public Book checkOutBook(String userEmail, Long bookId) throws Exception {

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        Checkout validateCheckout = checkoutRepository.findByBookIdAndUserEmail(bookId, userEmail);

        if(!optionalBook.isPresent() || validateCheckout != null || optionalBook.get().getCopiesAvailable() <= 0 ){
            throw new Exception("Book doesn't exist or already checked out by other user");
        }

        Book bookInDb = optionalBook.get();

       bookInDb.setCopiesAvailable(bookInDb.getCopiesAvailable() - 1);

        Book savedBook = bookRepository.save(bookInDb);

        Checkout checkout = new Checkout();
       checkout.setUserEmail(userEmail);
       checkout.setBookId(bookId);
       checkout.setCheckoutDate(LocalDate.now().toString());
       checkout.setReturnDate(LocalDate.now().plusDays(7).toString());

       checkoutRepository.save(checkout);

        return savedBook;
    }

    public Boolean checkoutBookByUser(String userEmail, Long bookId){

        Checkout validateCheckout = checkoutRepository.findByBookIdAndUserEmail(bookId, userEmail);

        return validateCheckout != null;
    }

    public int currentLoansCount(String userEmail){

        return checkoutRepository.findAllByUserEmail(userEmail).size();
    }

    public List<ShelfCurrentLoansResponse> currentLoans(String userEmail) throws Exception{

        List<ShelfCurrentLoansResponse> shelfCurrentLoansResponses = new ArrayList<>();

        List<Checkout> checkouts = checkoutRepository.findAllByUserEmail(userEmail);

        if (checkouts.isEmpty()){
            throw new Exception("user has no books checked out");
        }

        List<Long> bookIdsList = new ArrayList<>();

        checkouts.forEach(checkout -> bookIdsList.add(checkout.getBookId()) );

        List<Book> bookList = bookRepository.findBooksByBookIds(bookIdsList);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        bookList.forEach(book -> {
            Optional<Checkout> optionalCheckout = checkouts.stream()
                               .filter(c -> Objects.equals(c.getBookId(), book.getId())).findFirst();

            if (optionalCheckout.isPresent()){

                int daysLeft;

                try {
                    Date returnDate = simpleDateFormat.parse(optionalCheckout.get().getReturnDate());
                    Date currentDate = simpleDateFormat.parse(LocalDate.now().toString());

                    TimeUnit timeUnit = TimeUnit.DAYS;

                    long timeDifference = timeUnit.convert(returnDate.getTime() - currentDate.getTime(), TimeUnit.MILLISECONDS);
                    daysLeft = (int) timeDifference;

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                ShelfCurrentLoansResponse shelfResponse = new ShelfCurrentLoansResponse();
                shelfResponse.setBook(book);
                shelfResponse.setDaysLeft(daysLeft);

                shelfCurrentLoansResponses.add(shelfResponse);
            }
        });

        return shelfCurrentLoansResponses;
    }

    public void returnBook(String userEmail, Long bookId ) throws Exception {

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Checkout checkout = checkoutRepository.findByBookIdAndUserEmail(bookId, userEmail);

        if (optionalBook.isEmpty() || checkout == null){
            throw new Exception("Book does not exists, or not checked out by user");
        }

        Book book = optionalBook.get();

        History history = History.builder()
                            .userEmail(userEmail)
                            .author(book.getAuthor())
                            .checkoutDate(checkout.getCheckoutDate())
                            .returnDate(LocalDate.now().toString())
                            .title(book.getTitle())
                            .description(book.getDescription())
                            .img(book.getImg()).build();

        historyRepository.save(history);

        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepository.save(book);
        checkoutRepository.deleteById(checkout.getId());
    }

    public void renewBookLoan(String userEmail, Long bookId) throws Exception {

        Checkout checkout = checkoutRepository.findByBookIdAndUserEmail(bookId, userEmail);

        if (checkout == null ){
            throw new Exception("Book does not exists, or not checked out by user");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date now = sdf.parse(LocalDate.now().toString());
        Date returnDate = sdf.parse(checkout.getReturnDate());

        if (returnDate.compareTo(now) > 0 || returnDate.compareTo(now) == 0 ){
            checkout.setReturnDate(LocalDate.now().plusDays(7L).toString());
        }
        checkoutRepository.save(checkout);
    }
}
