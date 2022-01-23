package com.example.emailverification.mother;

import com.example.emailverification.entity.User;

public class UserObjectMother {

    public static User.UserBuilder valid() {
        return User.builder()
                .email("yuki@yandex.ru")
                .name("Dima");
    }
}
