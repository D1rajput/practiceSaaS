package com.practice.practice.dto;

import com.practice.practice.model.Employer;
import com.practice.practice.model.Location;
import lombok.Data;

import java.util.List;

@Data
public class JobPostDTO {
    private Long id;
    private String description;
    private Employer employerID;
    private List<String> skillId;
    private Location location;
    private String title;
}
