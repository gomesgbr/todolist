package com.todolist.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.todo.models.ToDo;


public interface ToDoRepository extends JpaRepository<ToDo, Integer>{
    
}
