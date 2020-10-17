package kr.chis.webfluxapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcetptionAdvice {
    @ExceptionHandler(BookException.class)
    private ResponseEntity bookException(BookException ex){
        ErrorMessage em = new ErrorMessage();
        em.setErrorCode(ex.getErrorCode());
        em.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(em);
    }
}
