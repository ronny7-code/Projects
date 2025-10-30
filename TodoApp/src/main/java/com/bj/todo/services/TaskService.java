package com.bj.todo.services;

import com.bj.todo.entities.Task;

import java.util.List;

public interface TaskService {

	Task addTodo(Task task);
	Task findTaskById(long id);
    List<Task> getAllTasks();
	void taskCompleted(Long id);
	List<Task> getIncompleteTasks();
}