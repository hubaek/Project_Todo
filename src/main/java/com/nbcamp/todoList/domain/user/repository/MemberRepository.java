package com.nbcamp.todoList.domain.user.repository;

import com.nbcamp.todoList.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
