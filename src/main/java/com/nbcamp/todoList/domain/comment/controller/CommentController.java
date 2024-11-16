package com.nbcamp.todoList.domain.comment.controller;

import com.nbcamp.todoList.domain.comment.controller.dto.CommentRequestDto;
import com.nbcamp.todoList.domain.comment.controller.dto.CommentResponseDto;
import com.nbcamp.todoList.domain.comment.service.CommentService;
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
    public Long updateComment(@PathVariable Long todoId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(todoId, commentId, commentRequestDto);
    }

    @DeleteMapping("/{commentId}")
    public Long deleteComment(@PathVariable Long todoId, @PathVariable Long commentId) {
        return commentService.deleteComment(todoId, commentId);
    }



}
