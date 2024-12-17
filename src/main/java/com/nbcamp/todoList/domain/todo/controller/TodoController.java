package com.nbcamp.todoList.domain.todo.controller;

import com.nbcamp.todoList.common.annotation.Auth;
import com.nbcamp.todoList.common.dto.AuthMember;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoCreateRequest;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoResponseDto;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoUpdateRequest;
import com.nbcamp.todoList.domain.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody @Valid TodoCreateRequest createRequest, @Auth AuthMember authMember) {
        TodoResponseDto todoResponseDto = todoService.createTodo(createRequest, authMember.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<TodoResponseDto>> getTodos(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Page<TodoResponseDto> todoResponseDto = todoService.getTodos(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoResponseDto);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId) {
        TodoResponseDto todoResponseDto = todoService.getTodo(todoId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoResponseDto);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long todoId, @RequestBody @Valid TodoUpdateRequest updateRequest, @Auth AuthMember authMember) {
        TodoResponseDto todoResponseDto = todoService.updateTodo(todoId, updateRequest, authMember.getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoResponseDto);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> deleteTodo(@PathVariable Long todoId, @Auth AuthMember authMember) {
        TodoResponseDto todoResponseDto = todoService.deleteTodo(todoId, authMember.getId());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(todoResponseDto);
    }




}
