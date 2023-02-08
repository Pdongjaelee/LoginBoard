package com.example.callbus.Member.entity;

import com.example.callbus.Member.Lessee.entity.Lessee;
import com.example.callbus.Member.Lessor.entity.Lessor;
import com.example.callbus.Member.Realtor.entity.Realtor;
import com.example.callbus.Member.dto.request.BoardRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "board_id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realtor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Realtor realtor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lessor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Lessor lessor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lessee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Lessee lessee;


    public Board(Long id) {
        this.id = id;
    }

    public Board(BoardRequestDto boardRequestDto, Realtor realtor, Lessor lessor, Lessee lessee) {
        this.title = boardRequestDto.getTitle();
        this.contents = boardRequestDto.getContents();
        this.realtor= realtor;
        this.lessor = lessor;
        this.lessee = lessee;

    }

    public Board(BoardRequestDto boardRequestDto, Realtor realtor) {

    }

    public Board(BoardRequestDto boardRequestDto, Lessor lessor) {

    }

    public Board(BoardRequestDto boardRequestDto, Lessee lessee) {

    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.contents = boardRequestDto.getContents();
    }
}
