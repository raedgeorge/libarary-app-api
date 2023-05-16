package com.atech.libarary.dao;

import com.atech.libarary.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author raed abu Sa'da
 * on 16/05/2023
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
