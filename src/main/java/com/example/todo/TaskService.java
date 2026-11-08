package com.example.todo;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new HashMap<>();
    private long nextId = 1;

    public Task create(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task update(Long id, Task updatedTask) {
        Task task = tasks.get(id);

        if (task == null) {
            return null;
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());

        return task;
    }

    public boolean delete(Long id) {
        return tasks.remove(id) != null;
    }
}