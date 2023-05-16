package com.atech.libarary.dao;

import com.atech.libarary.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author raed abu Sa'da
 * on 15/05/2023
 */
public interface HistoryRepository extends JpaRepository<History, Long> {

    Page<History> findAllByUserEmail(@RequestParam("email") String userEmail, Pageable pageable);
}
