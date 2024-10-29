package todobackend.todoapi.controller;

import todobackend.todoapi.Entity.Task;

import lombok.Data;

@Data
public class ApiData {
    private Task task;
    private String errormsg;
    public ApiData(Task task){
        this.task = task;
    }
    public ApiData(String msg){
        this.errormsg = msg;
    }

}
