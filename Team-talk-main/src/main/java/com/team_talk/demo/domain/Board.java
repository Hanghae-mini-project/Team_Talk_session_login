package com.team_talk.demo.domain;

import com.fasterxml.jackson.annotation.*;
import com.team_talk.demo.dto.BoardRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성해준다.
@Entity
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Pin> pins = new ArrayList<Pin>();


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.REMOVE)
//    @JsonManagedReference
//    private List<BoardMember> boardMembers = new ArrayList<BoardMember>();


    @ManyToOne
    @JsonBackReference
    private User user;


    public Board(BoardRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.user =user;
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
    }


//    public void addPin(Pin pin)
//    {
//        this.pins.add(pin);
//        if(pin.getBoard()!=this){
//            pin.setBoard(this);
//        }


//    }


}

