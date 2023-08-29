package com.picpaysimplifiedproject.picpaysimplifiedproject.services;

import com.picpaysimplifiedproject.picpaysimplifiedproject.client.ExternalAuthorizerClient;
import com.picpaysimplifiedproject.picpaysimplifiedproject.domain.transaction.Transaction;
import com.picpaysimplifiedproject.picpaysimplifiedproject.domain.user.User;
import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.input.TransactionInputDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.output.TransactionOutputDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.exception.BusinessException;
import com.picpaysimplifiedproject.picpaysimplifiedproject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private ExternalAuthorizerClient authorizerClient;
    @Autowired
    private NotificationService notificationService;

    public TransactionOutputDTO createTransaction(final TransactionInputDTO transaction) throws BusinessException {
        final var sender = userService.findUserById(transaction.senderId());
        final var receiver = userService.findUserById(transaction.receiverId());
        userService.validateTransaction(sender, sender.getBalance());
        authorizeTransaction(sender, transaction.value());

        final var newTransaction = Transaction.builder()
                .amount(transaction.value())
                .sender(sender)
                .receiver(receiver)
                .timestamp(LocalDateTime.now())
                .build();

        this.repository.save(newTransaction);
        cashRedeem(sender, transaction.value());
        cashDeposit(receiver, transaction.value());

        notificationService.sendNotification(sender, "Send money");
        notificationService.sendNotification(receiver, "Received money");

        return new TransactionOutputDTO(newTransaction.getAmount(), sender.getId(), receiver.getId());
    }

    private void authorizeTransaction(User sender, BigDecimal value) throws BusinessException {
        final var response = authorizerClient.getAuthorizationStatus();
        final var isSuccess = response.getStatusCode() == HttpStatusCode.valueOf(200);
        final var isAuthorized = "Autorizado".equals(response.getBody().message());
        if(!Boolean.logicalAnd(isSuccess, isAuthorized)) {
            throw new BusinessException("Unauthorized transaction");
        }
    }

    private void cashRedeem(User sender, BigDecimal value) {
        sender.setBalance(sender.getBalance().subtract(value));
        this.userService.save(sender);
    }

    private void cashDeposit(User receiver, BigDecimal value) {
        receiver.setBalance(receiver.getBalance().add(value));
        this.userService.save(receiver);
    }

}
