package com.atech.libarary.service;

import com.atech.libarary.dao.BookRepository;
import com.atech.libarary.dao.CheckoutRepository;
import com.atech.libarary.entity.Book;
import com.atech.libarary.entity.Checkout;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author raed abu Sa'da
 * on 09/05/2023
 */

@Service
@Transactional
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
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
}
