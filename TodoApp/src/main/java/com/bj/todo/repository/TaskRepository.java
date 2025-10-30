package com.bj.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bj.todo.entities.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>{

	Task findByTaskTitle(String taskTitle);
	List<Task> findByCompleted(int completed);

}