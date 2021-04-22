package com.team_talk.demo.service;

import com.team_talk.demo.domain.Board;
import com.team_talk.demo.domain.BoardMember;
import com.team_talk.demo.domain.User;
import com.team_talk.demo.dto.BoardRequestDto;
import com.team_talk.demo.repository.BoardMemberRepository;
import com.team_talk.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final BoardMemberRepository boardMemberRepository;

    public Board create(BoardRequestDto requestDto, User user) {
        Board newBoard = new Board(requestDto, user);
        return boardRepository.save(newBoard);
    }

    public void update(BoardRequestDto requestDto, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("내용이 존재하지 않습니다."));
        board.update(requestDto);
    }

    public BoardMember invite(String username, Long boardId) {
        Board board = findById(boardId);
        User user = userService.findByUsername(username);
        BoardMember newBoardMember = new BoardMember(board, username);
        return boardMemberRepository.save(newBoardMember);
    }

    public List<Board> userBoard(String username, User user) {
        List<Board> userBoard = boardRepository.findByUser(user);
        List<BoardMember> members = boardMemberRepository.findByUsername(username);
        for (int i = 0; i < members.size(); i++) {
            userBoard.add(members.get(i).getBoard());
        }
        return userBoard;
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }


}
