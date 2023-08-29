package com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.input;

import java.math.BigDecimal;

public record TransactionInputDTO(BigDecimal value, Long senderId, Long receiverId) {
}
