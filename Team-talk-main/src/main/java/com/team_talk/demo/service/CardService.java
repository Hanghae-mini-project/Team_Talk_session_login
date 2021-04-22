package com.team_talk.demo.service;

import com.team_talk.demo.domain.Board;
import com.team_talk.demo.domain.Card;
import com.team_talk.demo.domain.Pin;
import com.team_talk.demo.dto.CardRequestDto;
import com.team_talk.demo.dto.PinRequestDto;
import com.team_talk.demo.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final PinService pinService;
    public Card create(CardRequestDto requestDto, Long pinId) {
        Pin pin=pinService.findById(pinId);
        Card newCard = new Card(requestDto,pin);
        return cardRepository.save(newCard);
    }

    public void update(CardRequestDto requestDto, Long cardId){
        Card card= cardRepository.findById(cardId).orElseThrow(()->new IllegalArgumentException("내용이 존재하지 않습니다."));
        card.update(requestDto);
    }

    public Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }
}
