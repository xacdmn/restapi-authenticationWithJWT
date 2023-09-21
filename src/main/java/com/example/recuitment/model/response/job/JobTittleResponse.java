package com.example.recuitment.model.response.job;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobTittleResponse {

    private String title;

    public JobTittleResponse(String title) {
        this.title = title;
    }
}
