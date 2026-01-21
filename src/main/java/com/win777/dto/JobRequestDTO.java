package com.win777.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobRequestDTO {
    @NotBlank(message = "Job type is required")
    private String jobType;

    private String parameters;
}
