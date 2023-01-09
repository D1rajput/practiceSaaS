package com.practice.practice.service;

import com.practice.practice.dto.EmployeeProfileDTO;
import com.practice.practice.dto.JobResultDTO;
import com.practice.practice.dto.JobseekerDTO;
import com.practice.practice.model.Employee;
import com.practice.practice.model.JobPost;
import com.practice.practice.model.Skill;
import com.practice.practice.repository.EmployeeRepo;
import com.practice.practice.repository.JobPostRepo;
import com.practice.practice.repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IEmployeeImpl implements IEmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    SkillRepo skillRepo;

    @Autowired
    JobPostRepo jobPostRepo;

    @Override
    public Boolean saveEmployee(EmployeeProfileDTO employee) throws Exception {
        if (employee != null) {
            Employee data = new Employee();
            data.setFirstName(employee.getFirstName());
            data.setLastName(employee.getLastName());
            data.setEmail(employee.getEmail());
            data.setPassword(employee.getPassword());
            data.setIsEmployee(true);
            String str = employee.getSkillId().stream().collect(Collectors.joining("|"));
            data.setSkillId(str);
            data.setLocation(employee.getLocation());
            Employee result = employeeRepo.save(data);
            if (result != null) {
                return true;
            }
        }
        throw new Exception("Data can not be empty");
    }

    @Override
    public List<JobseekerDTO> getAllEmployee() throws Exception {
        List<Employee> employeeList = employeeRepo.findAll();
        List<JobseekerDTO> data = new ArrayList<>();
        for (Employee employee : employeeList) {
            JobseekerDTO dto = new JobseekerDTO();
            dto.setName(employee.getFirstName().concat(" " + employee.getLastName()));
            dto.setLocation(employee.getLocation().getName());
            dto.setSkills(getAllSkills(employee.getSkillId()));
            data.add(dto);
        }
        return data;
    }

    @Override
    public List<JobseekerDTO> getBySkill(String skillName) throws Exception {
        List<Employee> employeeList = employeeRepo.findAll();
        List<JobseekerDTO> data = new ArrayList<>();
        for (Employee post : employeeList) {
            List<Skill> skills = getAllSkills(post.getSkillId());
            for (Skill sk : skills) {
                if (sk.getName().equals(skillName)) {
                    JobseekerDTO dto = new JobseekerDTO();
                    dto.setName(post.getFirstName().concat(" " + post.getLastName()));
                    dto.setLocation(post.getLocation().getName());
                    dto.setSkills(skills);
                    data.add(dto);
                }
            }
        }
        return data;
    }

    @Override
    public List<JobseekerDTO> getByLocation(String locationName) throws Exception {
        List<Employee> employeeList = employeeRepo.findAll();
        List<JobseekerDTO> data = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getLocation().getName().equals(locationName)) {
                JobseekerDTO dto = new JobseekerDTO();
                dto.setName(employee.getFirstName().concat(" " + employee.getLastName()));
                dto.setLocation(employee.getLocation().getName());
                dto.setSkills(getAllSkills(employee.getSkillId()));
                data.add(dto);
            }
        }
        return data;
    }

    @Override
    public List<JobResultDTO> getDataByProfile(Long id) throws Exception {
        Employee employeeData = employeeRepo.findById(id).get();
        List<JobPost> allJobs = jobPostRepo.findAll();
        List<JobResultDTO> resultList = new ArrayList<>();
        for(JobPost  post : allJobs)
        {
            if(post.getLocation().getName().equals(employeeData.getLocation().getName()) || getAllSkills(post.getSkillId()).equals(getAllSkills(employeeData.getSkillId())))
            {
                JobResultDTO dto = new JobResultDTO();
                dto.setTitle(post.getTitle());
                dto.setDescription(post.getDescription());
                dto.setCompanyName(post.getEmployerID().getCompanyName());
                dto.setLocation(post.getLocation().getName());
                dto.setSkillList(getAllSkills(post.getSkillId()));
                resultList.add(dto);
            }
        }
        return resultList;
    }

    List<Skill> getAllSkills(String skillIds) throws Exception {
        String[] skillSplit = skillIds.split("\\|");
        List<Skill> skillList = new ArrayList<>();
        for (int i = 0; i < skillSplit.length; i++) {
            Skill skill = skillRepo.findById(Long.valueOf(skillSplit[i]).longValue()).get();
            skillList.add(skill);
        }
        return skillList;
    }


}
