package com.nbcamp.todoList.exception;

public class MemberAlreadyExistsException extends SignupException {

    public MemberAlreadyExistsException() {
        super(ErrorCode.MEMBER_ALREADY_EXISTS);
    }
}
