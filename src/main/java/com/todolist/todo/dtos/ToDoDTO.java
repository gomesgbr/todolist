package com.todolist.todo.dtos;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ToDoDTO {
    
    @NotBlank
    @Size(max = 250)
    private String descricao;
    @NotNull
    @AssertFalse
    private boolean feito;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFeito() {
        return feito;
    }

    public void setFeito(boolean feito) {
        this.feito = feito;
    }


 

    
}
