package com.win777.controller;

import com.win777.dto.JobRequestDTO;
import com.win777.dto.JobResponseDTO;
import com.win777.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@Tag(name = "Job Management", description = "APIs for initiating and tracking jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/user/{userId}")
    @Operation(summary = "Initiate a job", description = "Create and start a new job for a user")
    public ResponseEntity<JobResponseDTO> initiateJob(
            @PathVariable Long userId,
            @Valid @RequestBody JobRequestDTO request) {
        JobResponseDTO job = jobService.initiateJob(userId, request);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}")
    @Operation(summary = "Get job status", description = "Retrieve the current status of a job")
    public ResponseEntity<JobResponseDTO> getJobStatus(@PathVariable Long jobId) {
        JobResponseDTO job = jobService.getJobStatus(jobId);
        return ResponseEntity.ok(job);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user jobs", description = "Retrieve all jobs for a user")
    public ResponseEntity<List<JobResponseDTO>> getUserJobs(@PathVariable Long userId) {
        List<JobResponseDTO> jobs = jobService.getUserJobs(userId);
        return ResponseEntity.ok(jobs);
    }
}
