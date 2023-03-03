package com.todolist.todo.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.todo.dtos.ToDoDTO;
import com.todolist.todo.models.ToDo;
import com.todolist.todo.service.ToDoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/toDo")
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @PostMapping
    public ResponseEntity<Object> saveToDo(@RequestBody @Valid ToDoDTO toDoDTO) {
        var toDo = new ToDo();
        BeanUtils.copyProperties(toDoDTO, toDo);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoService.save(toDo));
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> getAllToDo() {
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getToDoById(@PathVariable(value = "id") int id) {
        Optional<ToDo> toDoOpt = toDoService.findById(id);
        if (!toDoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }

        return ResponseEntity.ok().body(toDoOpt.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id")int id){
        Optional<ToDo> toDoOpt = toDoService.findById(id);
        if (!toDoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        toDoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateToDo(@PathVariable(value = "id") int id, @RequestBody @Valid ToDoDTO obj){
        Optional<ToDo> toDoOpt = toDoService.findById(id);
        if (!toDoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        var toDo = new ToDo();
        BeanUtils.copyProperties(obj, toDo);
        toDo.setId(toDoOpt.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.save(toDo));
    }
}
