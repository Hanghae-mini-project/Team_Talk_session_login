package com.team_talk.demo.repository;

import com.team_talk.demo.domain.Board;
import com.team_talk.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
        List<Board> findByUser(User user);
}
