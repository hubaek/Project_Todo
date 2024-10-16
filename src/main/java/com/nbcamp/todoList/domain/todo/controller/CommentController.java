package com.nbcamp.todoList.domain.todo.controller;

import com.nbcamp.todoList.domain.todo.dto.CommentRequestDto;
import com.nbcamp.todoList.domain.todo.dto.CommentResponseDto;
import com.nbcamp.todoList.domain.todo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos/{todoId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long todoId) {
        return commentService.createComment(commentRequestDto, todoId);
    }

    @GetMapping
    public List<CommentResponseDto> getCommentsByTodoId(@PathVariable Long todoId) {
        return commentService.getCommentsByTodoId(todoId);
    }

    @PutMapping("/{commentId}")
    public Long updateComment(@PathVariable Long commentId, @PathVariable Long todoId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(todoId, commentId, commentRequestDto);
    }



}
