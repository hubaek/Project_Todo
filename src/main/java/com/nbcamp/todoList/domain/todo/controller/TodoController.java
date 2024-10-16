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
    public List<TodoResponseDto> getTodos() {
        return todoService.getTodos();
    }

    @PutMapping("/{id}")
    public Long updateTodo(@PathVariable Long id, @RequestBody @Valid TodoRequestDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }




}
