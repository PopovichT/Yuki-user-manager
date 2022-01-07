package com.example.emailverification.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class User {

    @Id
    @GeneratedValue

    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<MessagePost> posts = new ArrayList<>();
}

