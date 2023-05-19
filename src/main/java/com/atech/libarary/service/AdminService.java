package com.atech.libarary.service;

import com.atech.libarary.dao.BookRepository;
import com.atech.libarary.entity.Book;
import com.atech.libarary.requestmodels.AddBookRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author raed abu Sa'da
 * on 19/05/2023
 */

@Service
@Transactional
@AllArgsConstructor
public class AdminService {

    private final BookRepository bookRepository;

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
}
