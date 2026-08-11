package com.dev.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorDetails {
    public String error;
    public String details;
    public LocalDateTime timestamp;
}
