package com.practice.practice.dto;

import com.practice.practice.model.Skill;
import lombok.Data;

import java.util.List;

@Data
public class JobResultDTO {
    private String Title;
    private String companyName;
    private String description;
    private String location;
    private List<Skill> skillList;
}
