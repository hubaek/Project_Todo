package com.nbcamp.todo.service;

import com.nbcamp.todo.dto.TodoRequestDto;
import com.nbcamp.todo.dto.TodoResponseDto;
import com.nbcamp.todo.entity.Todo;
import com.nbcamp.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);
        Todo saveTodo = todoRepository.save(todo);
        return new TodoResponseDto(todo);
    }

    public List<TodoResponseDto> getTodos() {

        return todoRepository.findAllByOrderByUpdatedAtDesc().stream().map(TodoResponseDto::new).toList();
    }



}
