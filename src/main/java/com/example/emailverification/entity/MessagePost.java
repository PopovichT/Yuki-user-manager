package com.example.emailverification.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class MessagePost {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotEmpty(message = "Message is empty")
    private String message;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "MessagePost{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", user=" + user.getName() +
                '}';
    }
}
