package com.nbcamp.todoList.exception;

public class SignupException extends CustomException {
    public SignupException(ErrorCode errorCode) {
        super(errorCode);
    }
}
