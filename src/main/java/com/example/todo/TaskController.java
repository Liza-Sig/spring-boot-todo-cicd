package com.example.todo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody Task task) {
        return taskService.create(task);
    }

    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id,@Valid @RequestBody Task task) {
        Task updatedTask = taskService.update(id, task);
        if (updatedTask == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        } return updatedTask;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!taskService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }
}