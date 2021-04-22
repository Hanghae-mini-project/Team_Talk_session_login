package com.team_talk.demo.controller;

import com.team_talk.demo.domain.Card;
import com.team_talk.demo.dto.CardRequestDto;
import com.team_talk.demo.dto.PinRequestDto;
import com.team_talk.demo.repository.CardRepository;
import com.team_talk.demo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CardController {

    private final CardRepository cardRepository;
    private final CardService cardService;

//    @GetMapping("/api/cards")
//    public List<Card> AllCard() {
//        return cardRepository.findAll();
//    }
//

    @PostMapping("/api/cards")
    public void createCard(
            @RequestBody CardRequestDto requestDto,
            @RequestParam(required = false, value = "pinId") Long pinId
    ) {
        cardService.create(requestDto, pinId);
    }

    @PutMapping("/api/cards")
    public void updateCard(
            @RequestParam(required = false, value = "cardId") Long cardId,
            @RequestBody CardRequestDto requestDto
    ) {
        cardService.update(requestDto, cardId);
    }

    @DeleteMapping("/api/cards")
    public void deleteCard(@RequestParam(required = false, value = "cardId") Long cardId) {
        cardRepository.deleteById(cardId);
    }


}