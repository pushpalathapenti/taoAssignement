package com.tao.taskManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tao.taskManager.entity.Task;
import com.tao.taskManager.entity.User;
import com.tao.taskManager.service.TaskService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final TaskService taskService;
	
	@Autowired
	public UserController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	//6.	Endpoint: GET /api/users/{userId}/tasks
	@GetMapping("/{userId}/tasks")
	public Mono<List<Task>> getTasksForUser(@Valid @PathVariable String userId) {
		return Mono.just(taskService.getTasksForUser(userId));
	}
	
	@PostMapping
	public Mono<User> createUser(@RequestBody User user) {
		return Mono.just(taskService.createUser(user));
	}

}
