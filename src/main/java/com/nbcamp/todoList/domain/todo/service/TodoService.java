package com.nbcamp.todoList.domain.todo.service;

import com.nbcamp.todoList.domain.todo.controller.dto.TodoCreateRequest;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoResponseDto;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoUpdateRequest;
import com.nbcamp.todoList.domain.todo.entity.Todo;
import com.nbcamp.todoList.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponseDto createTodo(TodoCreateRequest createRequest, Long memberId) {
        Todo todo = new Todo(createRequest.getTitle(), createRequest.getContent(), memberId);
        Todo savedTodo = todoRepository.save(todo);
        return new TodoResponseDto(savedTodo);
    }

    // todo page객체대신 타입 만들기
    public Page<TodoResponseDto> getTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt"));
        Page<Todo> todos = todoRepository.findAll(pageable);
        return todos.map(TodoResponseDto::new);
    }

    public TodoResponseDto getTodo(Long todoId) {
        Todo todo = findTodo(todoId);
        return new TodoResponseDto(todo);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long todoId, TodoUpdateRequest updateRequest, Long memberId) {
        Todo todo = findTodo(todoId);
        if (todo.getId().equals(memberId)) {
            todo.update(updateRequest);
        }
        Todo updatedTodo = todoRepository.save(todo);
        return new TodoResponseDto(updatedTodo);
    }

    public TodoResponseDto deleteTodo(Long todoId, Long memberId) {
        Todo todo = findTodo(todoId);
        if (todo.getId().equals(memberId)) {
            todoRepository.delete(todo);
        }
        return new TodoResponseDto(todo);
    }

    private Todo findTodo(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정이 존재하지 않습니다."));
    }


}
