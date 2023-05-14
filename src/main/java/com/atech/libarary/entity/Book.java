package com.atech.libarary.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author raed abu Sa'da
 * on 01/05/2023
 */

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;
    private String author;
    private String description;
    private int copies;

    @Column(name = "copies_available")
    private int copiesAvailable;

    private String category;
    private String img;
}
