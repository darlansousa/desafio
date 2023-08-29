package com.picpaysimplifiedproject.picpaysimplifiedproject.infra;


import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.output.ExceptionOutputDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionOutputDTO> duplicateEntity(DataIntegrityViolationException exception) {
        ExceptionOutputDTO exceptionOutput = new ExceptionOutputDTO("Error to record data", 400);
        return ResponseEntity.badRequest().body(exceptionOutput);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionOutputDTO> notFoundEntity(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionOutputDTO> duplicateEntity(BusinessException exception) {
        ExceptionOutputDTO exceptionOutput = new ExceptionOutputDTO("Business rule violated", 422);
        return ResponseEntity.unprocessableEntity().body(exceptionOutput);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionOutputDTO> duplicateEntity(Exception exception) {
        ExceptionOutputDTO exceptionOutput = new ExceptionOutputDTO("Undefined error", 500);
        return ResponseEntity.internalServerError().body(exceptionOutput);
    }

}
