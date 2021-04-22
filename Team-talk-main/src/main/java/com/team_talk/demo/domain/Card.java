package com.team_talk.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team_talk.demo.dto.CardRequestDto;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor(force=true)
@Entity
public class Card extends Timestamped {



    @Id //해당 프로퍼티가 테이블의 주키 역할을 한다는 것을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    //주키의 값을 위한 자동 생성 전략을 명시  //strategy는 엔티티의 주키를 생성할 때 사용해야 하는 주키생산 전략
    //1. AUTO : (persistence provider가) 특정 DB에 맞게 자동 선택
    //2. IDENTITY : DB의 identity 컬럼을 이용
    //3. SEQUENCE : DB의 시퀀스 컬럼을 이용
    //4. TABLE : 유일성이 보장된 데이터베이스 테이블을 이용

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Comment> comment = new ArrayList<Comment>();

    @ManyToOne
    @JsonBackReference
    private Pin pin;

    public Card(CardRequestDto requestDto, Pin pin) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.pin = pin;
//        this.pin = pinService.findById(requestDto.getPinId());
    }

    public void update(CardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();

    }



}
