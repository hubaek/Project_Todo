package com.nbcamp.todoList.domain.todo.repository;

import com.nbcamp.todoList.domain.todo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
