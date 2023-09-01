package com.tao.taskManager.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskStatistics {
	private long totalTasks;
	private long completedTasks;
	private Double percentageCompletedTasks;
}
