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

    @GetMapping("/api/pins")
    public List<Pin> AllBoard() {
        return pinRepository.findAll();
    }

    @GetMapping("/api/select_pin")
    public Pin SelectPin(
            @RequestParam(required = false, value = "pin") Long pinId
    ) {
        Pin pin = pinService.findById(pinId);
        return pin;
    }

    @PostMapping("/api/pins/{boardId}")
    public void createPin(
            @RequestBody PinRequestDto requestDto,
            @PathVariable Long boardId
    ) {
        pinService.create(requestDto, boardId);
    }

    @PutMapping("/api/pins/{pinId}")
    public void updatePin(
            @PathVariable Long pinId,
            @RequestBody PinRequestDto requestDto
    ) {
        pinService.update(requestDto, pinId);
    }

    @DeleteMapping("/api/pins/{pinId}")
    public void deletePin(@PathVariable Long pinId) {
        pinRepository.deleteById(pinId);
    }

}
