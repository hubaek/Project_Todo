package com.nbcamp.todoList.domain.todo.service;

import com.nbcamp.todoList.domain.todo.dto.TodoRequestDto;
import com.nbcamp.todoList.domain.todo.dto.TodoResponseDto;
import com.nbcamp.todoList.domain.todo.entity.Todo;
import com.nbcamp.todoList.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Long updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = findTodo(id);
        todo.update(requestDto);
        return id;
    }

    public Long deleteTodo(Long id) {
        Todo todo = findTodo(id);
        todoRepository.delete(todo);
        return id;
    }

    private Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정이 존재하지 않습니다."));
    }


}