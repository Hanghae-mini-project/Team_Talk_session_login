package com.team_talk.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team_talk.demo.dto.BoardRequestDto;
import com.team_talk.demo.dto.CardRequestDto;
import com.team_talk.demo.dto.CommentRequestDto;
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
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String writer;

    @ManyToOne
    @JsonBackReference
    private Card card;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Comment(CommentRequestDto requestDto, Card card,User user,String username) {
        this.description=requestDto.getDescription();
        this.writer=username;
        this.card=card;
        this.user=user;
    }

}
