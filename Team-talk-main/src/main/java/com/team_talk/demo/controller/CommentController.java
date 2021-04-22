package com.team_talk.demo.controller;


import com.team_talk.demo.domain.User;
import com.team_talk.demo.dto.CardRequestDto;
import com.team_talk.demo.dto.CommentRequestDto;
import com.team_talk.demo.security.UserDetailsImpl;
import com.team_talk.demo.service.CommentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {


    private final CommentService commentService;

    @PostMapping("/api/comments/{cardId}")
    public void createCard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentRequestDto requestDto,
            @PathVariable Long cardId
    ) {
        User user = userDetails.getUser();
        commentService.create(requestDto, cardId, user);
    }
}
