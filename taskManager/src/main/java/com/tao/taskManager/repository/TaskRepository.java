package com.tao.taskManager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tao.taskManager.entity.Task;
import com.tao.taskManager.entity.User;
import com.tao.taskManager.utils.Status;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByStatus(Status status);
    List<Task> findByDueDateBefore(LocalDate date);
    
    @Query(value = "SELECT * FROM task WHERE status = 'COMPLETED' AND completion_date >= ?1 AND completion_date <= ?2", nativeQuery = true)
    List<Task> findCompletedTasksWithinDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    //List<Task> findByTaskStatusCompletedDateBetween(LocalDate startDate, LocalDate endDate);
    List<Task> findByUser(User user);
    long count();
    
    long countByStatus(Status status);
}