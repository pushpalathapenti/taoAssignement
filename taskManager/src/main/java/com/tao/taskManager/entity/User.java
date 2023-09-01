package com.tao.taskManager.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-string-id")
    @GenericGenerator(name = "custom-string-id", strategy = "com.tao.taskManager.utils.CustomStringIdGenerator")
    private String id;
	
	@Column(nullable = false)
    private String name;
    
	/*
	 * @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) private List<Task>
	 * tasks;
	 */
}
