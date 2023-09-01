package com.tao.taskManager.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.tao.taskManager.dto.TaskRequest;
import com.tao.taskManager.dto.TaskResponse;
import com.tao.taskManager.entity.Task;
import com.tao.taskManager.entity.User;
import com.tao.taskManager.utils.Status;
import com.tao.taskManager.utils.TaskStatistics;

public interface TaskService {
	
	TaskResponse createTask(TaskRequest task);
	
	Task updateTask(String id, TaskRequest taskRequest);
	
	TaskResponse deleteTask(String id);
	
	List<Task> getTasks();
	
	List<Task> getTasksForUser(String userId);
	
	Task assignTaskToUser(String taskId, String userId);
	
	Task updateTaskProgress(String taskId, BigDecimal progress);

	List<Task> getOverdueTasks();
	
	List<Task> getTasksWithStatus(Status taskStatus);
	
	List<Task> getCompletedTasksWithinDateRange(LocalDate startDate, LocalDate endDate);
	
	TaskStatistics getTaskStatistics();
	
	List<Task> getPriorityBasedTaskQueue();

	User createUser(User user);
	
}
