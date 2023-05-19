package com.atech.libarary.service;

import com.atech.libarary.dao.BookRepository;
import com.atech.libarary.dao.CheckoutRepository;
import com.atech.libarary.dao.ReviewRepository;
import com.atech.libarary.entity.Book;
import com.atech.libarary.requestmodels.AddBookRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author raed abu Sa'da
 * on 19/05/2023
 */

@Service
@Transactional
@AllArgsConstructor
public class AdminService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final CheckoutRepository checkoutRepository;

    public void postBook(AddBookRequest bookRequest){

        Book book = new Book();
        book.setCopies(bookRequest.getCopies());
        book.setCopiesAvailable(bookRequest.getCopies());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setDescription(bookRequest.getDescription());
        book.setCategory(bookRequest.getCategory());
        book.setImg(bookRequest.getImg());

        bookRepository.save(book);

    }

    public void increaseBookQuantity(Long bookId) throws Exception{

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (!optionalBook.isPresent()){
            throw new Exception("book with this id not found!");
        }

        Book bookInDB = optionalBook.get();

        bookInDB.setCopies(bookInDB.getCopies() + 1);
        bookInDB.setCopiesAvailable(bookInDB.getCopiesAvailable() + 1);

        bookRepository.save(bookInDB);
    }

    public void decreaseBookQuantity(Long bookId) throws Exception {

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()){
            throw new Exception("book with this id not found!");
        }

        Book bookInDB = optionalBook.get();

        if (bookInDB.getCopiesAvailable() <= 0 || bookInDB.getCopies() <= 0){
            throw new Exception("book quantity is already 0. cant decrease more");
        }

        bookInDB.setCopies(bookInDB.getCopies() -1);
        bookInDB.setCopiesAvailable(bookInDB.getCopiesAvailable() -1);

        bookRepository.save(bookInDB);
    }

    public void deleteBookById(Long bookId) throws Exception{

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (!optionalBook.isPresent()){
            throw new Exception("book with this id not found!");
        }

        reviewRepository.deleteAllByBookId(bookId);
        checkoutRepository.deleteAllByBookId(bookId);
        bookRepository.delete(optionalBook.get());
    }
}
