package com.example.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void shouldCreateTask() {
        Task task = new Task(null, "Изучить Java", "Описание", false);

        Task created = taskService.create(task);

        assertNotNull(created.getId());
        assertEquals("Изучить Java", created.getTitle());
    }

    @Test
    void shouldFindAllTasks() {
        taskService.create(new Task(null, "Первая", "Описание", false));
        taskService.create(new Task(null, "Вторая", "Описание", true));

        List<Task> tasks = taskService.findAll();

        assertEquals(2, tasks.size());
    }

    @Test
    void shouldUpdateTask() {
        Task task = taskService.create(
                new Task(null, "Старое название", "Описание", false)
        );

        Task updated = taskService.update(
                task.getId(),
                new Task(null, "Новое название", "Новое описание", true)
        );

        assertEquals("Новое название", updated.getTitle());
        assertEquals("Новое описание", updated.getDescription());
        assertTrue(updated.isCompleted());
    }

    @Test
    void shouldReturnNullWhenUpdatingMissingTask() {
        Task updated = taskService.update(
                999L,
                new Task(null, "Название", "Описание", false)
        );

        assertNull(updated);
    }

    @Test
    void shouldDeleteTask() {
        Task task = taskService.create(
                new Task(null, "Удалить", "Описание", false)
        );

        boolean deleted = taskService.delete(task.getId());

        assertTrue(deleted);
        assertTrue(taskService.findAll().isEmpty());
    }

    @Test
    void shouldReturnFalseWhenDeletingMissingTask() {
        boolean deleted = taskService.delete(999L);

        assertFalse(deleted);
    }
}