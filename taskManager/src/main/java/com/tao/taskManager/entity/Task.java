package com.tao.taskManager.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import com.tao.taskManager.utils.Priority;
import com.tao.taskManager.utils.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "task")
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Data
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-string-id")
    @GenericGenerator(name = "custom-string-id", strategy = "com.tao.taskManager.utils.CustomStringIdGenerator")
    private String id;
	
	@Column(nullable = false, length = 20)
    private String title;
	
	@Column(nullable = false, length = 250)
    private String description;
	
	@Column(nullable = false)
    private LocalDate dueDate;
	
    private LocalDate completionDate;
    
    //@Column(nullable = false)
    private BigDecimal progress;
    
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private Status status;
    
    @Enumerated(EnumType.STRING)
    @Column(name="priority", nullable = false)
    private Priority priority;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
}
