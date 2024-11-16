package com.nbcamp.todoList.domain.comment.repository;

import com.nbcamp.todoList.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTodoId(Long todoId);
}
