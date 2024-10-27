package todobackend.todoapi.controller;

import todobackend.todoapi.Entity.Task;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import todobackend.todoapi.Services.taskServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class ApireqController {
    @Autowired
    taskServices services;

    // To save task in database
    @PostMapping("/saveTask")
    public ResponseEntity<ApiData> saveTask(@RequestBody Task task) {
        try {

            ApiData data = new ApiData(services.createTask(task));
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            ApiData data = new ApiData("Error while Saving task +" + ex);
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getTaskByID/{id}")
    public ResponseEntity<ApiData> getTaskByID(@PathVariable Long id){
        try{
            ApiData data = new ApiData(services.taskById(id));
            return new ResponseEntity<ApiData>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(new ApiData(ex.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Get all saved task from database
    @GetMapping("/getAllTask")
    public ResponseEntity<List<ApiData>> getAllTask() {
        List<ApiData> allTaskApi = new ArrayList<>();
        try {
            List<Task> allTask = services.getAllTask();
            for (Task t : allTask) {
                ApiData apidata = new ApiData(t);
                allTaskApi.add(apidata);
            }
            return new ResponseEntity<List<ApiData>>(allTaskApi, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    // update task
    @PostMapping("/updateTask/{id}")
    public ResponseEntity<ApiData> updateTask(@PathVariable Long id,@RequestBody Task task) {
        try {
            System.out.println("is taks is"+task.isCompleted());
            ApiData data = new ApiData(services.updatedTask(Long.parseLong(""+id), task));
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {

            return new ResponseEntity<>(new ApiData("unable to update Status \n " + ex),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // }

    // get all completed task
    @GetMapping("/completedTask")
    public ResponseEntity<List<ApiData>> getCompletedTask() {
        List<ApiData> allTaskApi = new ArrayList<>();

        try {
            List<Task> allTask = services.getTaskByStatus(true);

            for (Task t : allTask) {
                ApiData apidata = new ApiData(t);
                allTaskApi.add(apidata);
            }
            return new ResponseEntity<List<ApiData>>(allTaskApi, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/DeleteTask/{id}")
    public ResponseEntity<Boolean> deleteTaskEntity(@PathVariable Long id) {
        try {
            Task task = services.taskById(id);
            services.deleteTask(task);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}