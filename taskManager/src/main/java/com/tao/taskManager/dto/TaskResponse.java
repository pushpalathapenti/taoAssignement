package com.tao.taskManager.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tao.taskManager.utils.Priority;
import com.tao.taskManager.utils.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {
	private String id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private LocalDate completedDate;
    private Priority priority;
    private Status status;
    
    private String httpStatus;
    private String message;
}
