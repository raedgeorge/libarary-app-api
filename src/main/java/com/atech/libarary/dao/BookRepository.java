package com.atech.libarary.dao;

import com.atech.libarary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author raed abu Sa'da
 * on 01/05/2023
 */

@EnableJpaRepositories(value = "books")
public interface BookRepository extends JpaRepository<Book, Long> {
}
