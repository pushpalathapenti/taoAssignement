package com.tao.taskManager.utils;

import java.time.LocalDate;

public class StringUtilities {
	
	public static boolean isNotEmptyOrNull(String data) {
		return data == null || data.isEmpty() || data.trim().isEmpty();
	}
	
	public static boolean isNotEmptyOrNull(Priority priority) {
		return priority == null || priority.name().isEmpty();
	}
	
	public static boolean isValidDueDate(String dueDate) {
		LocalDate tempDate = Utils.getFormattedDate(dueDate);
		return dueDate == null || LocalDate.now().isEqual(tempDate) || LocalDate.now().isAfter(tempDate);
	}
	
	public static boolean isDescrSizeCrossed(String description) {
		return description != null && description.length()>Constants.DESC_MAX_SIZE;
	}

}
