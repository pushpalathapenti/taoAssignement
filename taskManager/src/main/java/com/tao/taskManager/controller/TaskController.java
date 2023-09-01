package com.tao.taskManager.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tao.taskManager.dto.TaskRequest;
import com.tao.taskManager.dto.TaskResponse;
import com.tao.taskManager.entity.Task;
import com.tao.taskManager.entity.User;
import com.tao.taskManager.service.TaskService;
import com.tao.taskManager.utils.Constants;
import com.tao.taskManager.utils.Status;
import com.tao.taskManager.utils.TaskStatistics;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tasks")
public class TaskController{
	
    private final TaskService taskService;
    
    @Autowired
    public TaskController(TaskService taskService) {
    	this.taskService = taskService;
    }
  
    //1.	Endpoint: POST /api/tasks
    @PostMapping
    public Mono<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
		/*
		 * return
		 * validationRequestService.validateCreateTask(taskRequest).flatMap(validation
		 * -> { if(validation.isValid()) {
		 */
    			TaskResponse taskResponse = taskService.createTask(taskRequest);
    			taskResponse.setMessage("The task created successfully");
    			taskResponse.setHttpStatus("Sucess");
    	        return Mono.just(taskResponse);
    		//}
			/*
			 * TaskResponse taskResponse=new TaskResponse();
			 * taskResponse.setHttpStatus(Constants.MINUS_ONE);
			 * taskResponse.setMessage(validation.getValidationMessage());
			 */
    		//return Mono.just(taskResponse);
    	//});
    	
    }
    
    //2.	Endpoint: PUT /api/tasks/{taskId}
    @PutMapping("/{taskId}")
    public Mono<Task> updateTask(@PathVariable String taskId, @RequestBody TaskRequest taskRequest) {
        return Mono.just(taskService.updateTask(taskId, taskRequest));
    }
    
    //3.	Endpoint: DELETE /api/tasks/{taskId}
    @DeleteMapping("/{taskId}")
    public Mono<TaskResponse> deleteTask(@PathVariable String taskId) {
    	return Mono.just(taskService.deleteTask(taskId));
    }
    
    //4.	Endpoint: GET /api/tasks
    @GetMapping
    public Flux<List<Task>> getTasks() {
    	return Flux.just(taskService.getTasks());
    }
    
    //5.	Endpoint: POST /api/tasks/{taskId}/assign
    @PostMapping("/{taskId}/assign")
    public Mono<Task> assignTask(@PathVariable String taskId, @RequestBody User user) {
    	return Mono.just(taskService.assignTaskToUser(taskId, user.getId()));
    }
    
    //7.	Endpoint: PUT /api/tasks/{taskId}/progress
    @PutMapping("/{taskId}/progress")
    public Mono<Task> updateTaskProgress(@PathVariable String taskId, @RequestBody TaskRequest taskRequest) {
        return Mono.just(taskService.updateTaskProgress(taskId, taskRequest.getProgress()));
    }

    //8.	Endpoint: GET /api/tasks/overdue
    @GetMapping("/overdue")
    public Flux<List<Task>> getOverdueTasks() {
    	return Flux.just(taskService.getOverdueTasks());
    }
    
    //9.	Endpoint: GET /api/tasks/status/{status}
    @GetMapping("/status/{status}")
    public Flux<List<Task>> getTasksWithStatus(@PathVariable Status status) {
    	System.out.println(status);
    	return Flux.just(taskService.getTasksWithStatus(status));
    }
    
    //10.	Endpoint: GET /api/tasks/completed
    @GetMapping("/completed")
    public Flux<List<Task>> getCompletedTasksWithinDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return Flux.just(taskService.getCompletedTasksWithinDateRange(startDate, endDate));
    }
    
    //11.	Endpoint: GET /api/tasks/statistics
    @GetMapping("/statistics")
    public Mono<TaskStatistics> getTaskStatistics() {
    	return Mono.just(taskService.getTaskStatistics());
    }
    
    //12 
    @GetMapping("/priorityTaskQueue")
    public Mono<List<Task>> getPriorityBasedTaskQueue() {
    	return Mono.just(taskService.getPriorityBasedTaskQueue());
    }
    
    
}