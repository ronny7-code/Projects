package com.bj.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bj.todo.entities.Task;
import com.bj.todo.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServicesImpl implements TaskService{

	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public Task addTodo(Task task) {
	    Task existingTask = taskRepository.findByTaskTitle(task.getTaskTitle());
	    if(existingTask != null) {
	        return null;  // Duplicate, don’t save
	    }
	    return taskRepository.save(task);
	}


	@Override
	public Task findTaskById(long id) {
		return taskRepository.findById(id).orElse(null);
	}

	@Override
	public void taskCompleted(Long id) {
		Task task = taskRepository.findById(id).orElse(null);
		if (task != null) {
			task.setCompleted(1);
			taskRepository.save(task);
		}
	}


	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> getIncompleteTasks() {
		return taskRepository.findByCompleted(0);
	}

}