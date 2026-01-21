package com.win777.service;

import com.win777.dto.JobRequestDTO;
import com.win777.dto.JobResponseDTO;
import com.win777.entity.JobEntity;
import com.win777.exception.ResourceNotFoundException;
import com.win777.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobResponseDTO initiateJob(Long userId, JobRequestDTO request) {
        JobEntity job = new JobEntity();
        job.setUserId(userId);
        job.setJobType(request.getJobType());
        job.setStatus("PENDING");
        
        JobEntity savedJob = jobRepository.save(job);
        return convertToDTO(savedJob);
    }

    public JobResponseDTO getJobStatus(Long jobId) {
        JobEntity job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));
        return convertToDTO(job);
    }

    public List<JobResponseDTO> getUserJobs(Long userId) {
        List<JobEntity> jobs = jobRepository.findByUserId(userId);
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private JobResponseDTO convertToDTO(JobEntity job) {
        return new JobResponseDTO(
                job.getId(),
                job.getUserId(),
                job.getJobType(),
                job.getStatus(),
                job.getResult(),
                job.getCreatedAt(),
                job.getUpdatedAt()
        );
    }
}
