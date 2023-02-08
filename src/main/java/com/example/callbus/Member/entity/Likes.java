package com.example.callbus.Member.entity;

import com.example.callbus.Member.Lessee.entity.Lessee;
import com.example.callbus.Member.Lessor.entity.Lessor;
import com.example.callbus.Member.Realtor.entity.Realtor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false)
    private Long id;

    @JoinColumn(name = "mountain_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Realtor realtor;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Lessor lessor;

    public Likes(Board board, Realtor realtor, Lessor lessor) {
        this.board = board;
        this.realtor = realtor;
        this.lessor = lessor;
    }

    public Likes(Realtor realtor, Board board) {

    }

    public Likes(Board board, Lessor lessor) {

    }

    public Likes(Board board, Lessee lessee) {

    }
}

