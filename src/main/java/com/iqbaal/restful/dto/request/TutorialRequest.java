package com.iqbaal.restful.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TutorialRequest {
    private String title;
    private String description;
    private Boolean published;
}
