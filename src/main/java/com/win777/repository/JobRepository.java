package com.win777.repository;

import com.win777.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    List<JobEntity> findByUserId(Long userId);
}
