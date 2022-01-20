package com.example.emailverification.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @NotEmpty(message = "Name is empty")
    @Size(min = 2,max = 30, message = "Name is not valid")
    private String name;

    private String email;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<MessagePost> posts = new ArrayList<>();
}

