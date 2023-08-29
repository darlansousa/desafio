package com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.input;

import com.picpaysimplifiedproject.picpaysimplifiedproject.domain.user.UserType;

import java.math.BigDecimal;

public record UserInputDTO(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String password,
        UserType type) {
}
