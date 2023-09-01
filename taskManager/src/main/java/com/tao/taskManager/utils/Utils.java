package com.tao.taskManager.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.tao.taskManager.dto.TaskRequest;
import com.tao.taskManager.entity.Task;

import jakarta.validation.ValidationException;

public class Utils {
	public static final String VALIDATION_MSG = "Please pass mandatory parameters";

	public static final String TECHNICAL_MSG = "Something went wrong, please try again later";

	public static LocalDate getFormattedDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = null;
		try {
			localDate = LocalDate.parse(dateStr, formatter);
			System.out.println("Parsed LocalDate: " + localDate);
		} catch (java.time.format.DateTimeParseException e) {
			System.out.println("Failed to parse date: " + e.getMessage());
		}
		return localDate;
	}
	
	public static boolean isFutureOrPresent(LocalDate dateToCheck) {
        LocalDate currentDate = LocalDate.now();
        return !dateToCheck.isBefore(currentDate);
    }
	
	public static Task getUpdatedTask(TaskRequest taskRequest, Task tempTask) {
		if (taskRequest.getTitle() != null)
			tempTask.setTitle(taskRequest.getTitle());
		if (taskRequest.getDescription() != null)
			tempTask.setDescription(taskRequest.getDescription());
		if (taskRequest.getDueDate() != null)
			tempTask.setDueDate(taskRequest.getDueDate());
		if (taskRequest.getCompletionDate() != null)
			tempTask.setCompletionDate(taskRequest.getCompletionDate());
		if (taskRequest.getProgress() != null)
			tempTask.setProgress(taskRequest.getProgress());
		if (taskRequest.getPriority() != null)
			tempTask.setPriority(taskRequest.getPriority());
		if (taskRequest.getStatus() != null)
			tempTask.setStatus(taskRequest.getStatus());
		return tempTask;
	}
	
	public static boolean updateTaskValidation(TaskRequest taskRequest) {
		boolean isValidated = false;
		if (taskRequest.getDueDate() != null && Utils.isFutureOrPresent(taskRequest.getDueDate())) {
			isValidated = true;
		} else if (taskRequest.getDueDate() == null) {
			isValidated = true;
		} else {
			throw new ValidationException("Due date must be today or in the future");
		}
		/*
		 * if(taskRequest.getCompletionDate()!=null &&
		 * Utils.isFutureOrPresent(taskRequest.getCompletionDate())) { isValidated =
		 * true; } else { throw new
		 * ValidationException("Completion date must be today or in the future"); }
		 */
		return isValidated;
	}
	
	public static Task copyRequestToEntity(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setCompletionDate(taskRequest.getCompletionDate());
        task.setPriority(taskRequest.getPriority());
        task.setProgress(taskRequest.getProgress());
        task.setStatus(taskRequest.getStatus());
        return task;
    }

}
