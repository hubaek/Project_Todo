package com.nbcamp.todoList.exception;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleCustumException(CustomException ex) {
        logger.error("Exception 발생 : {}", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getErrorCode().getErrorCode(),
                ex.getErrorCode().getErrorMessage()
        );

        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(errorResponse);

    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
//        logger.error("IllegalArgumentException 발생 : ",ex.getMessage());
//        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
//        return new ResponseEntity<>(
//                errorResponse,
//                HttpStatus.BAD_REQUEST);
//    }

    @Getter
    public static class ErrorResponse {
        private String errorCode;
        private String errorMessage;

        public ErrorResponse(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }
}
