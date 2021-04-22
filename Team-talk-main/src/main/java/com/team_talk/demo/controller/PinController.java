package com.team_talk.demo.controller;

import com.team_talk.demo.domain.Board;
import com.team_talk.demo.domain.Pin;
import com.team_talk.demo.dto.BoardRequestDto;
import com.team_talk.demo.dto.PinRequestDto;
import com.team_talk.demo.repository.PinRepository;
import com.team_talk.demo.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PinController {

    private final PinRepository pinRepository;
    private final PinService pinService;


    @PostMapping("/api/pins")
    public void createPin(
            @RequestBody PinRequestDto requestDto,
            @RequestParam(required = false, value = "boardId") Long boardId
    ) {
        pinService.create(requestDto, boardId);
    }

    @PutMapping("/api/pins")
    public void updatePin(
            @RequestParam(required = false, value = "pinId") Long pinId,
            @RequestBody PinRequestDto requestDto
    ) {
        pinService.update(requestDto, pinId);
    }

    @DeleteMapping("/api/pins")
    public void deletePin(@RequestParam(required = false, value = "pinId") Long pinId) {
        pinRepository.deleteById(pinId);
    }

}
