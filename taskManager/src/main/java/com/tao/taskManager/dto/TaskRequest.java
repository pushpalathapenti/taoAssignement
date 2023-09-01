package com.tao.taskManager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tao.taskManager.utils.Priority;
import com.tao.taskManager.utils.Status;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRequest {
	@NotBlank(message = "Task title is required")
	@NotNull(message = "Task title is required")
	private String title;
	
	private String description;
	
	@NotNull(message = "Task due date is required")
	@FutureOrPresent(message = "Due date must be today or in the future")
	//private String dueDate;
	private LocalDate dueDate;
	
	@FutureOrPresent(message = "Due date must be today or in the future")
	private LocalDate completionDate;
	
	private BigDecimal progress;
	private Status status;
	
	private Priority priority;
	
	@NotBlank(message = "Task userId is required")
	@NotNull(message = "Task userId is required")
	private String userId;
}
