package com.atech.libarary.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "checkout")
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "checkout_date")
    private String checkoutDate;

    @Column(name = "return_date")
    private String returnDate;
}
