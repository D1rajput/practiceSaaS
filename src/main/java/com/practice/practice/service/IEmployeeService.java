package com.practice.practice.service;

import com.practice.practice.dto.EmployeeProfileDTO;
import com.practice.practice.dto.JobResultDTO;
import com.practice.practice.dto.JobseekerDTO;
import com.practice.practice.model.Employee;
import java.util.List;

public interface IEmployeeService {
    Boolean saveEmployee(EmployeeProfileDTO employee) throws Exception;
    List<JobseekerDTO> getAllEmployee() throws Exception;
    List<JobseekerDTO> getBySkill(String skillName) throws Exception;
    List<JobseekerDTO> getByLocation(String locationName) throws Exception;
    List<JobResultDTO> getDataByProfile(Long id) throws Exception;
}
