package com.team_talk.demo.repository;


import com.team_talk.demo.domain.Card;
import com.team_talk.demo.domain.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
}