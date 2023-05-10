package com.atech.libarary.dao;

import com.atech.libarary.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author raed abu Sa'da
 * on 09/05/2023
 */
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    Checkout findByBookIdAndUserEmail(Long bookId, String userEmail);

    List<Checkout> findAllByUserEmail(String userEmail);
}
