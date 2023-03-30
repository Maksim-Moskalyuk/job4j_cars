package ru.job4j.car.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "auto_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String login;

    @Column
    private String password;
}