package com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.output;

import java.math.BigDecimal;

public record UserOutputDTO(
        Long id,
        String firstName,
        String lastName,
        String document,
        String email,
        BigDecimal balance) {
}
