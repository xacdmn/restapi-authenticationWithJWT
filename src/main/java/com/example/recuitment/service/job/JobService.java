package com.example.recuitment.service.job;

import com.example.recuitment.model.response.job.JobResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobService {
    List<JobResponse> getJobs();
    JobResponse getDescriptionById(String id);
}
