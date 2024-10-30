package com.nbcamp.todoList.domain.todo.entity;

import com.nbcamp.todoList.common.entity.Timestamped;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoCreateRequest;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoRequestDto;
import com.nbcamp.todoList.domain.todo.controller.dto.TodoUpdateRequest;
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
    @Column(name = "member_id")
    private Long memberId;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setTodo(this);
    }



    public Todo(TodoCreateRequest createRequest) {
        this.title = createRequest.getTitle();
        this.content = createRequest.getContent();
    }

    public void update(TodoUpdateRequest updateRequest) {
        this.title = updateRequest.getTitle();
        this.content = updateRequest.getContent();
    }
}
