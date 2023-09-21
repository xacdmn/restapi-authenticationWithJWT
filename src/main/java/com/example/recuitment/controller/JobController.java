package com.example.recuitment.controller;

import com.example.recuitment.model.response.ApiResponse;
import com.example.recuitment.model.response.job.JobResponse;
import com.example.recuitment.model.response.job.JobTittleResponse;
import com.example.recuitment.service.job.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Authentication", description = "API for Job")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Operation(summary = "Get job list with key and value")
    @GetMapping("/get-list-tittle")
    public ResponseEntity<ApiResponse<List<JobTittleResponse>>> getJobs() {
        List<JobResponse> jobs = jobService.getJobs();
        List<JobTittleResponse> title = jobs.stream()
                .map(job -> new JobTittleResponse(job.getTitle()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", title), HttpStatus.OK);
    }

    @Operation(summary = "Get job list without key and value")
    @GetMapping("/get-list")
    public ResponseEntity<ApiResponse<List<String>>> getJobTitles() {
        List<JobResponse> positions = jobService.getJobs();
        List<String> titles = positions.stream().map(JobResponse::getTitle).collect(Collectors.toList());
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", titles), HttpStatus.OK);
    }

    @Operation(summary = "Get job detail with id")
    @GetMapping("/{id}/description")
    public ResponseEntity<ApiResponse<String>> getDescriptionById(@PathVariable
                                                                      @Parameter(description = "ID of the job to get details for.", example = "32bf67e5-4971-47ce-985c-44b6b3860cdb", required = true)
                                                                      String id) {
        JobResponse jobs = jobService.getDescriptionById(id);
        if (jobs == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Job not found for ID: " + id, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", jobs.getDescription()), HttpStatus.OK);
    }
}

