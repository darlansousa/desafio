package com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.output;

import java.math.BigDecimal;

public record TransactionOutputDTO(BigDecimal value, Long sender, Long receiver) {
}
