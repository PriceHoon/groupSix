package com.sparta.spartagroupsixproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
//@NoArgsConstructor
@Entity
//@SuperBuilder
@Builder
@AllArgsConstructor
public class LikeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    private Long commentId;

    @Column(nullable = false)
    private Long userId;

    public LikeComment() {

    }
}

