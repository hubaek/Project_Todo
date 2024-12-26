package com.nbcamp.todoList.domain.comment.entity;

import com.nbcamp.todoList.common.entity.Timestamped;
import com.nbcamp.todoList.domain.comment.controller.dto.CommentRequestDto;
import com.nbcamp.todoList.domain.todo.entity.Todo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "name")
    private String name;

    // todo id값으로 받을것인가?
    @ManyToOne
    @JoinColumn(name = "todo_id")
    @Setter
    private Todo todo;

    public Comment(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
        this.name = commentRequestDto.getName();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
        this.name = commentRequestDto.getName();
    }
}
