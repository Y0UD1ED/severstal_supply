package com.example.suppliers.exceptions;

import com.example.suppliers.entities.Provider;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.Date;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppError> methodArgumentNotValidException(MethodArgumentNotValidException e){
        String validationError=e.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), validationError,new Date()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<AppError> validationException(ValidationException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SupplyNotFoundException.class)
    public ResponseEntity<AppError> supplyNotFoundException(SupplyNotFoundException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.NOT_FOUND.value(),e.getMessage(),new Date()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<AppError> productNotFoundException(ProductNotFoundException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.NOT_FOUND.value(),e.getMessage(),new Date()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProviderNotFoundException.class)
    public ResponseEntity<AppError> providerNotFoundException(ProviderNotFoundException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.NOT_FOUND.value(),e.getMessage(),new Date()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<AppError> dateTimeParseException(DateTimeParseException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(),"Неверный формат даты! Введите в виде dd-MM-yyyy!",new Date()),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<AppError> missingRequestParameterException(MissingServletRequestParameterException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(),"Параметр "+e.getParameterName()+" является обязательным!",new Date()),HttpStatus.BAD_REQUEST);
    }
}
