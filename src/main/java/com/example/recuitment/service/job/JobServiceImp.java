package com.example.recuitment.service.job;

import com.example.recuitment.model.response.job.JobResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class JobServiceImp implements JobService{

    private final String BASE_URL = "https://dev6.dansmultipro.com/api/recruitment/positions";

    @Override
    public List<JobResponse> getJobs() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JobResponse[]> response = restTemplate.getForEntity(BASE_URL + ".json", JobResponse[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public JobResponse getDescriptionById(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "/" + id;
        try {
            return restTemplate.getForObject(url, JobResponse.class);
        } catch (Exception e) {
            // Log error or handle exception as required
            return null;
        }
    }


}
