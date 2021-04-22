package com.team_talk.demo.service;


import com.team_talk.demo.domain.Board;
import com.team_talk.demo.domain.Pin;
import com.team_talk.demo.dto.BoardRequestDto;
import com.team_talk.demo.dto.CardRequestDto;
import com.team_talk.demo.dto.PinRequestDto;
import com.team_talk.demo.repository.BoardRepository;
import com.team_talk.demo.repository.CardRepository;
import com.team_talk.demo.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PinService {

    private final PinRepository pinRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    public Pin create(PinRequestDto requestDto,Long boardId) {
        Board board=boardService.findById(boardId);
        Pin newPin = new Pin(requestDto,board);
        return pinRepository.save(newPin);
    }

    public void update(PinRequestDto requestDto, Long pinId){
        Pin pin= pinRepository.findById(pinId).orElseThrow(()->new IllegalArgumentException("내용이 존재하지 않습니다."));
        pin.update(requestDto);
    }

    public Pin findById(Long id) {
        return pinRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

}
