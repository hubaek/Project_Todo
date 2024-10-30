package com.nbcamp.todoList.domain.todo.entity;

import com.nbcamp.todoList.common.entity.Timestamped;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    public void update(String name) {
        this.name = name;
    }

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
