package com.tao.taskManager.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tao.taskManager.dto.TaskRequest;
import com.tao.taskManager.dto.TaskResponse;
import com.tao.taskManager.entity.Task;
import com.tao.taskManager.entity.User;
import com.tao.taskManager.exception.ResourceNotFoundException;
import com.tao.taskManager.repository.TaskRepository;
import com.tao.taskManager.repository.UserRepository;
import com.tao.taskManager.utils.Priority;
import com.tao.taskManager.utils.Status;
import com.tao.taskManager.utils.TaskStatistics;
import com.tao.taskManager.utils.Utils;

import jakarta.validation.ValidationException;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public TaskResponse createTask(TaskRequest taskRequest) {
		TaskResponse taskResponse = new TaskResponse();
		// if (taskRequest.getUserId() != null) {
		Optional<User> user = userRepository.findById(taskRequest.getUserId());
		if (user.isPresent()) {
			taskRequest.setStatus(Status.OPEN);// By default OPEN status
			taskRequest.setPriority(Priority.LOW);// By default LOW priority
			Task task = Utils.copyRequestToEntity(taskRequest);
			task.setUser(user.get());
			Task result = taskRepository.save(task);
			taskResponse.setId(result.getId());
		} else {
			throw new ResourceNotFoundException(String.format("The userId (%s) not found", taskRequest.getUserId()));
			// taskResponse.setHttpStatus(HttpStatus.NOT_FOUND.value());
			// taskResponse.setMessage(String.format("The userId (%s) not found",
			// taskRequest.getUserId()));
		}
		/*
		 * } else {
		 * taskResponse.setMessage(String.format("The userId is mandatory field",
		 * taskRequest.getUserId())); }
		 */
		return taskResponse;
	}

	@Override
	public Task updateTask(String id, TaskRequest taskRequest) {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent() && Utils.updateTaskValidation(taskRequest)) {
			Task tempTask = task.get();
			if (taskRequest.getUserId() != null) {
				Optional<User> user = userRepository.findById(taskRequest.getUserId());
				if (user.isPresent()) {
					tempTask.setUser(user.get());
				} else {
					throw new ResourceNotFoundException(
							String.format("The user (%s) not found", taskRequest.getUserId()));
				}
			}
			Utils.getUpdatedTask(taskRequest, tempTask);
			return taskRepository.save(tempTask);
		} else {
			throw new ResourceNotFoundException("The task id is not found");
		}
	}

	@Override
	public TaskResponse deleteTask(String id) {

		if (taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
			TaskResponse taskResponse = new TaskResponse();
			taskResponse.setId(id);
			taskResponse.setHttpStatus("Success");
			taskResponse.setMessage(String.format("The task (%s) deleted successfully", id));
			return taskResponse;
		} else {
			throw new ResourceNotFoundException(String.format("The task (%s) not found, Please pass valide task id", id));
		}
	}

	@Override
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> getTasksForUser(String userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			return taskRepository.findByUser(userOptional.get());
		} else {
			throw new ResourceNotFoundException(String.format("The user (%s) not found, Please pass valide user id", userId));
		}
	}

	@Override
	public Task assignTaskToUser(String taskId, String userId) {
		Optional<Task> taskOptional = taskRepository.findById(taskId);
		if (taskOptional.isPresent()) {
			Task task = taskOptional.get();

			Optional<User> user = userRepository.findById(userId);
			if (user.isPresent()) {
				task.setUser(user.get());
				return taskRepository.save(task);
			} else {
				throw new ResourceNotFoundException(String.format("The User (%s) not found.", userId));
			}
		} else {
			throw new ResourceNotFoundException(String.format("The task (%s) not found.", taskId));
		}
	}

	@Override
	public Task updateTaskProgress(String taskId, BigDecimal progress) {
		Optional<Task> taskOptional = taskRepository.findById(taskId);
		if (taskOptional.isPresent()) {
			Task task = taskOptional.get();
			task.setProgress(progress);
			return taskRepository.save(task);
		} else {
			throw new ResourceNotFoundException(String.format("The task (%s) not found.", taskId));
		}
	}

	@Override
	public List<Task> getOverdueTasks() {
		LocalDate currentDate = LocalDate.now();
		return taskRepository.findByDueDateBefore(currentDate);
	}

	@Override
	public List<Task> getTasksWithStatus(Status taskStatus) {
		return taskRepository.findByStatus(taskStatus);
	}

	@Override
	public List<Task> getCompletedTasksWithinDateRange(LocalDate startDate, LocalDate endDate) {
		return taskRepository.findCompletedTasksWithinDateRange(startDate, endDate);
	}

	@Override
	public TaskStatistics getTaskStatistics() {
		return getStatistics();
	}

	@Override
	public List<Task> getPriorityBasedTaskQueue() {
		List<Task> tasks = taskRepository.findAll();
		tasks.sort((task1, task2) -> {
			if (task1.getPriority() == task2.getPriority()) {
				return task1.getDueDate().compareTo(task2.getDueDate());
			} else {
				return task1.getPriority().compareTo(task2.getPriority());
			}
		});
		return tasks;
	}

	public long getTotalTasks() {
		return taskRepository.count();
	}

	public long getCompletedTasks(Status status) {
		return taskRepository.countByStatus(status);
	}

	public TaskStatistics getStatistics() {
		long totalTasks = getTotalTasks();
		long completedTasks = getCompletedTasks(Status.COMPLETED);
		TaskStatistics taskStatistics = new TaskStatistics();
		taskStatistics.setCompletedTasks(completedTasks);
		taskStatistics.setTotalTasks(totalTasks);
		taskStatistics.setPercentageCompletedTasks((double) completedTasks / totalTasks * 100.0);
		return taskStatistics;
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

}
