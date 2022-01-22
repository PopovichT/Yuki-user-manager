package com.example.emailverification.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name is empty")
    @Size(min = 2,max = 30, message = "Name is not valid")
    private String name;

    @Column(unique=true)
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<MessagePost> posts = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", posts=" + posts +
                '}';
    }
}

