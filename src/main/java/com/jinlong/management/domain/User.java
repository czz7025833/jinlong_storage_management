package com.jinlong.management.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable=false)
    private String cellphoneNumber;

    @Column(nullable = true)
    private int points;
}
