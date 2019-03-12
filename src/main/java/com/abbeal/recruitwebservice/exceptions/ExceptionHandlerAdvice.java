package com.abbeal.recruitwebservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(QuizzNotPresentException.class)
    public ResponseEntity<String> handleException(QuizzNotPresentException e) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body("Le quizz demandé n'existe pas !");
    }        
    
    @ExceptionHandler(NotEnoughQuestionsException.class)
    public ResponseEntity<String> handleException(NotEnoughQuestionsException e) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body("Il n'y a pas assez de questions pour satisfaire la demande !");
    }    
    
    
    @ExceptionHandler(QuizzInstanceNotPresentException.class)
    public ResponseEntity<String> handleException(QuizzInstanceNotPresentException e) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body("La quizz instance demandée n'existe pas !");
    } 
}