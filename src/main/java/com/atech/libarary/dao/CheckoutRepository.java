package com.atech.libarary.dao;

import com.atech.libarary.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author raed abu Sa'da
 * on 09/05/2023
 */
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    Checkout findByBookIdAndUserEmail(Long bookId, String userEmail);

    List<Checkout> findAllByUserEmail(String userEmail);

    @Modifying
    @Query("delete from Checkout c where c.bookId in :book_id ")
    void deleteAllByBookId(@Param("book_id") Long bookId);
}
