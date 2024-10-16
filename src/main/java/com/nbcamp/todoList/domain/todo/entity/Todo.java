package com.nbcamp.todoList.domain.todo.entity;

import com.nbcamp.todoList.common.entity.Timestamped;
import com.nbcamp.todoList.domain.todo.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "todo")
@NoArgsConstructor
public class Todo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "todo")
    private List<Comment> comments = new ArrayList<>();



    public Todo(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.name = requestDto.getName();
    }

    public void update(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.name = requestDto.getName();
    }
}
