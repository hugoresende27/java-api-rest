package com.hr.apirest2.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)//id 1,2,3,4 autoincrement
    @Column(name = "id", unique = true)
    private Long id;//use Integer to not get null error, Long to not get bigger number

    @Column(name = "username", length = 100, nullable = false,unique = true, columnDefinition = "dev")
    @NotNull
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;
}
