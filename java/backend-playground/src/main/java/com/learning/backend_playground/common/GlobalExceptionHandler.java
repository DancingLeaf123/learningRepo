package com.learning.backend_playground.common;

import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;



@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final int VALIDATION_FAILED_CODE = 400;

    private static final String VALIDATION_FAILED_PREFIX = "validation failed: ";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException ex){
            final String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
            return Result.fail(VALIDATION_FAILED_CODE, VALIDATION_FAILED_PREFIX + detail);

    }

    /**
     * Handles single-parameter validation (e.g. {@code @Min} on {@code @PathVariable}).
     *
     * @param ex constraint violations
     * @return failure envelope
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handleConstraintViolation(
            final ConstraintViolationException ex) {
        final String detail = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage())
                .collect(Collectors.joining("; "));
        return Result.fail(VALIDATION_FAILED_CODE, VALIDATION_FAILED_PREFIX + detail);
    }
}




