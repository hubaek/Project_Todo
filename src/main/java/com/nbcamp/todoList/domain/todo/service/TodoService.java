package com.nbcamp.todoList.domain.todo.service;

import com.nbcamp.todoList.domain.todo.controller.dto.TodoCreateRequest;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoRequestDto;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoResponseDto;
import com.nbcamp.todoList.domain.todo.entity.Todo;
import com.nbcamp.todoList.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoResponseDto createTodo(TodoCreateRequest createRequest) {
        Todo todo = new Todo(createRequest);
        todoRepository.save(todo);
        return new TodoResponseDto(todo);
    }

//    public List<TodoResponseDto> getTodos() {
//        return todoRepository.findAllByOrderByUpdatedAtDesc().stream().map(TodoResponseDto::new).toList();
//    }

    @Transactional(readOnly = true)
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
