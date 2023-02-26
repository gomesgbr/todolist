package com.todolist.todo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.todo.models.ToDo;
import com.todolist.todo.repositories.ToDoRepository;

@Service
public class ToDoService {
    
    @Autowired
    ToDoRepository toDoRepository;

    @Transactional
    public ToDo save(ToDo toDo){
      return toDoRepository.save(toDo);
    }
}

