package com.nbcamp.todoList.domain.todo.entity;

import com.nbcamp.todoList.common.entity.Timestamped;
import com.nbcamp.todoList.domain.comment.entity.Comment;
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

    // todo 양방향 연관관계 해제 고려
    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setTodo(this);
    }

    // todo 정적팩토리 메서드 고려
    public Todo(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }

    public void update(TodoUpdateRequest updateRequest) {
        this.title = updateRequest.getTitle();
        this.content = updateRequest.getContent();
    }
}
