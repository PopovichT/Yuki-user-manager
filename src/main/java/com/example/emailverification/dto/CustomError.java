package com.example.emailverification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private ZonedDateTime zonedDateTime;
    private String message;
}
