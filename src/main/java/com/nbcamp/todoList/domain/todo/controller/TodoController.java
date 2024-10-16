package com.nbcamp.todoList.domain.todo.controller;

import com.nbcamp.todoList.domain.todo.dto.TodoRequestDto;
import com.nbcamp.todoList.domain.todo.dto.TodoResponseDto;
import com.nbcamp.todoList.domain.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoResponseDto createTodo(@RequestBody @Valid TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    @GetMapping
    public List<TodoResponseDto> getTodos(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return todoService.getTodos(page, size);
    }

    @GetMapping("/{todoId}")
    public TodoResponseDto getTodo(@PathVariable Long todoId) {
        return todoService.getTodo(todoId);
    }

    @PutMapping("/{todoId}")
    public Long updateTodo(@PathVariable Long todoId, @RequestBody @Valid TodoRequestDto requestDto) {
        return todoService.updateTodo(todoId, requestDto);
    }

    @DeleteMapping("/{todoId}")
    public Long deleteTodo(@PathVariable Long todoId) {
        return todoService.deleteTodo(todoId);
    }




}
