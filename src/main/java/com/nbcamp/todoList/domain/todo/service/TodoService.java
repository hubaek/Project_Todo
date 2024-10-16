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

    public TodoResponseDto getTodo(Long todoId) {
        return new TodoResponseDto(findTodo(todoId));
    }

    @Transactional
    public Long updateTodo(Long todoId, TodoRequestDto requestDto) {
        Todo todo = findTodo(todoId);
        todo.update(requestDto);
        return todoId;
    }

    public Long deleteTodo(Long todoId) {
        Todo todo = findTodo(todoId);
        todoRepository.delete(todo);
        return todoId;
    }

    private Todo findTodo(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정이 존재하지 않습니다."));
    }


}
