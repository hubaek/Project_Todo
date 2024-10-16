package com.nbcamp.todoList.domain.todo.service;

import com.nbcamp.todoList.domain.todo.dto.CommentRequestDto;
import com.nbcamp.todoList.domain.todo.dto.CommentResponseDto;
import com.nbcamp.todoList.domain.todo.entity.Comment;
import com.nbcamp.todoList.domain.todo.entity.Todo;
import com.nbcamp.todoList.domain.todo.repository.CommentRepository;
import com.nbcamp.todoList.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, Long todoId) {
        Todo todo = findTodo(todoId);
        Comment comment = new Comment(commentRequestDto);

        todo.addComment(comment);

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }


    public List<CommentResponseDto> getCommentsByTodoId(Long todoId) {
        findTodo(todoId);
        List<Comment> comments = commentRepository.findByTodoId(todoId);
        return comments.stream()
                .map(CommentResponseDto::new).toList();
    }

        private Todo findTodo(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정이 존재하지 않습니다."));
    }

}
