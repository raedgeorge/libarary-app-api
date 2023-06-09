package com.atech.libarary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author raed abu Sa'da
 * on 05/05/2023
 */

@Builder
@NoArgsConstructor
@Data
@Entity
@Table(name = "review")
@AllArgsConstructor
public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", unique = true, nullable = false)
    private Long bookId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "rating")
    private double rating;

    @CreationTimestamp
    @Column(name = "date")
    private Date creationDate;

    @Column(name = "review_description")
    private String reviewDescription;
}
