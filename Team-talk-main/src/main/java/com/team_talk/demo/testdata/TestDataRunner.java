package com.team_talk.demo.testdata;

import com.team_talk.demo.domain.Board;
import com.team_talk.demo.domain.Card;
import com.team_talk.demo.domain.Pin;
import com.team_talk.demo.domain.User;
import com.team_talk.demo.dto.SignupRequestDto;
import com.team_talk.demo.repository.BoardRepository;
import com.team_talk.demo.repository.CardRepository;
import com.team_talk.demo.repository.PinRepository;
import com.team_talk.demo.repository.UserRepository;
import com.team_talk.demo.service.BoardService;
import com.team_talk.demo.service.CardService;
import com.team_talk.demo.service.PinService;
import com.team_talk.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


//@Component
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    CardRepository cardRepostiory;

    @Autowired
    CardService cardService;

    @Autowired
    PinRepository pinRepository;

    @Autowired
    PinService pinService;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    int i = 1;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Board testboard = new Board();
        testboard.setTitle(i + "번방");
        boardRepository.save(testboard);
        Long id = testboard.getId();

        createPinData(i + "-1" + "공지사항", id);
        createPinData(i + "-2" + "api설계", id);
        createPinData(i + "-3" + "db설계", id);
        createPinData(i + "-4" + "와이어프레임", id);
        createUserData();
    }

    private void createUserData() {
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        String[] skills = new String[]{"Spring", "ReactNative", "NodeJS", "React"};
        for (int i = 1; i <= 20; i++) {
            signupRequestDto.setUsername(i + "번");
            signupRequestDto.setPassword(i + "");
            signupRequestDto.setSkill(skills[i % 4]);
            userService.registerUser(signupRequestDto);
        }
    }

    private void createPinData(String title, Long id) {
        Pin pin = new Pin();
        pin.setTitle(title);
        pin.setBoard(boardService.findById(id));
        pinRepository.save(pin);
        Long PinId = pin.getId();
        createTestData("웹 개발의 봄 스프링", "어렵다", PinId);
        createTestData("리엑트 네이티브", "어렵다", PinId);
        createTestData("노드 JS", "어렵다", PinId);
        createTestData("리엑트", "어렵다", PinId);
    }

    private void createTestData(String title, String description, Long id) {
        Card card = new Card();
        card.setTitle(title);
        card.setDescription(description);
        card.setPin(pinService.findById(id));
        cardRepostiory.save(card);

    }

}
