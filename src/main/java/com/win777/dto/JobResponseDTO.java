package com.win777.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDTO {
    private Long id;
    private Long userId;
    private String jobType;
    private String status;
    private String result;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
