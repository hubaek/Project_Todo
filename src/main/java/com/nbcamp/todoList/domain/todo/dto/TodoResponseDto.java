package com.nbcamp.todoList.domain.todo.dto;

import com.nbcamp.todoList.domain.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private Long id;
    private String title;
    private String content;
    private int commentCount;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.name = todo.getName();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
        this.commentCount = todo.getComments().size();
    }
}
