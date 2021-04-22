package com.team_talk.demo.controller;


import com.team_talk.demo.domain.Comment;
import com.team_talk.demo.domain.User;
import com.team_talk.demo.dto.CardRequestDto;
import com.team_talk.demo.dto.CommentRequestDto;
import com.team_talk.demo.dto.PinRequestDto;
import com.team_talk.demo.repository.CommentRepository;
import com.team_talk.demo.security.UserDetailsImpl;
import com.team_talk.demo.service.CommentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {


    private final CommentService commentService;
    private final CommentRepository commentRepository;


    @PostMapping("/api/comments")
    public void createCard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentRequestDto requestDto,
            @RequestParam(required = false, value = "cardId") Long cardId
    ) {
        User user = userDetails.getUser();
        commentService.create(requestDto, cardId, user);
    }

    @PutMapping("/api/comments")
    public void updatePin(
            @RequestParam(required = false, value = "commentId") Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentRequestDto requestDto
    ) {
        Comment comment =commentService.findById(commentId);
        String writer=comment.getWriter();
        if(userDetails.getUsername().equals(writer)) {
            commentService.update(requestDto, commentId);
        }
    }

    @DeleteMapping("/api/comments")
    public void deletePin(
            @RequestParam(required = false, value = "commentId") Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Comment comment =commentService.findById(commentId);
        String writer=comment.getWriter();
        if(userDetails.getUsername().equals(writer)) {
            commentRepository.deleteById(commentId);
        }

    }
}
