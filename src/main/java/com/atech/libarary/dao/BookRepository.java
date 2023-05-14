package com.atech.libarary.dao;

import com.atech.libarary.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author raed abu Sa'da
 * on 01/05/2023
 */

@EnableJpaRepositories(value = "books")
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);

    @Query("select b from Book b where b.id in :bookIdsList")
    List<Book> findBooksByBookIds(@Param("bookIdsList") List<Long> bookIdsList);
}
