package com.atech.libarary.dao;

import com.atech.libarary.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author raed abu Sa'da
 * on 16/05/2023
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findAllByUserEmail(@RequestParam("user_email") String userEmail, Pageable pageable);
}
