package com.picpaysimplifiedproject.picpaysimplifiedproject.controller;

import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.input.TransactionInputDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.output.TransactionOutputDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.exception.BusinessException;
import com.picpaysimplifiedproject.picpaysimplifiedproject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionOutputDTO> createTransaction(
            @RequestBody TransactionInputDTO transaction) throws BusinessException {
       return ResponseEntity.ok(service.createTransaction(transaction));
    }
}
