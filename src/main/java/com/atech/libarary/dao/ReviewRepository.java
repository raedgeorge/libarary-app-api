package com.atech.libarary.dao;

import com.atech.libarary.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author raed abu Sa'da
 * on 05/05/2023
 */
@EnableJpaRepositories(value = "reviews")
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByBookId(@RequestParam("bookId") Long bookId, Pageable pageable);

    Review findByBookIdAndUserEmail(Long bookId, String userEmail);

    @Modifying
    @Query("delete from Review r where r.bookId in :book_id")
    void deleteAllByBookId(@Param("book_id") Long bookId);
}
