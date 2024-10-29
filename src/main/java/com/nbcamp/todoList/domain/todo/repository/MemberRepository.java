package com.nbcamp.todoList.domain.todo.repository;

import com.nbcamp.todoList.domain.todo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
