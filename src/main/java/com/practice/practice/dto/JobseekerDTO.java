package com.practice.practice.dto;

import com.practice.practice.model.Skill;
import lombok.Data;

import java.util.List;

@Data
public class JobseekerDTO{
    private String name;
    private String location;
    private List<Skill> skills;
}
