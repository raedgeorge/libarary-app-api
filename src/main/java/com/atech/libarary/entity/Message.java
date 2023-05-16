package com.atech.libarary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author raed abu Sa'da
 * on 16/05/2023
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String question;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "admin_email")
    private String adminEmail;

    private String response;
    private boolean closed;

    public Message(String title, String question) {
        this.title = title;
        this.question = question;
    }
}
