package com.team_talk.demo.controller;

import com.team_talk.demo.domain.Board;
import com.team_talk.demo.domain.Card;
import com.team_talk.demo.domain.User;
import com.team_talk.demo.dto.BoardRequestDto;
import com.team_talk.demo.repository.BoardRepository;
import com.team_talk.demo.repository.CardRepository;
import com.team_talk.demo.repository.PinRepository;
import com.team_talk.demo.repository.UserRepository;
import com.team_talk.demo.security.UserDetailsImpl;
import com.team_talk.demo.service.BoardService;
import com.team_talk.demo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final CardService cardService;
    private final BoardRepository boardRepository;
    private final BoardService boardService;


    @GetMapping("/api/boards")
    public List<Board> SelectBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        String username = userDetails.getUsername();
        User user = userDetails.getUser();
        List<Board> boards=boardService.userBoard(username, user);

        return boards;
    }


    @PostMapping("/api/invite")
    public void createBoard(
            @RequestParam(required = false, value = "username") String username,
            @RequestParam(required = false, value = "boardId") Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        String boardLeader = userDetails.getUsername();
        if (boardLeader.equals(username)) {
            System.out.println("안돼");
        } else {
            boardService.invite(username, boardId);
        }
    }

    @PostMapping("/api/boards")
    public void createBoard(
            @RequestBody BoardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        boardService.create(requestDto, user);
    }

    @PutMapping("/api/boards/{boardId}")
    public void updateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardRequestDto requestDto
    ) {

        boardService.update(requestDto, boardId);
    }

    @DeleteMapping("/api/boards/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardRepository.deleteById(boardId);
    }



}
