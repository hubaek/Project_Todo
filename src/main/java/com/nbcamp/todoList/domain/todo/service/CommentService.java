package com.nbcamp.todoList.domain.todo.service;

import com.nbcamp.todoList.domain.todo.dto.CommentRequestDto;
import com.nbcamp.todoList.domain.todo.dto.CommentResponseDto;
import com.nbcamp.todoList.domain.todo.entity.Comment;
import com.nbcamp.todoList.domain.todo.entity.Todo;
import com.nbcamp.todoList.domain.todo.repository.CommentRepository;
import com.nbcamp.todoList.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, Long todoId) {
        Todo todo = findTodo(todoId);
        Comment comment = new Comment(commentRequestDto);

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    private Todo findTodo(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정이 존재하지 않습니다."));
    }

}
