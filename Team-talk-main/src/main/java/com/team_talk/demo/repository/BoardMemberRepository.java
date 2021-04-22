package com.team_talk.demo.repository;

import com.team_talk.demo.domain.Board;
import com.team_talk.demo.domain.BoardMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardMemberRepository extends JpaRepository<BoardMember, Long> {
    List<BoardMember> findByUsername(String username);
}
