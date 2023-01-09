package com.practice.practice.dto;

import com.practice.practice.model.Employee;
import com.practice.practice.model.Location;
import com.practice.practice.model.Skill;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isEmployee;
    private Location location;
    private List<String> skillId;
}
