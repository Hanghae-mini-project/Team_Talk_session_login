package com.team_talk.demo.service;

import com.team_talk.demo.domain.Card;
import com.team_talk.demo.domain.Comment;
import com.team_talk.demo.domain.Pin;
import com.team_talk.demo.domain.User;
import com.team_talk.demo.dto.CardRequestDto;
import com.team_talk.demo.dto.CommentRequestDto;
import com.team_talk.demo.repository.CardRepository;
import com.team_talk.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardService cardService;


    public Comment create(CommentRequestDto requestDto, Long cardId, User user) {
        Card card=cardService.findById(cardId);
        String username=user.getUsername();
        Comment newComment = new Comment(requestDto, card,user,username);
        return commentRepository.save(newComment);
    }


    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }
}
