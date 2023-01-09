package com.practice.practice.service;

import com.practice.practice.dto.JobResultDTO;
import com.practice.practice.model.Employer;
import com.practice.practice.model.JobPost;
import com.practice.practice.model.Skill;
import com.practice.practice.repository.EmployerRepo;
import com.practice.practice.repository.JobPostRepo;
import com.practice.practice.repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IEmployerImpl implements IEmployerService {
    @Autowired
    EmployerRepo repo;

    @Autowired
    JobPostRepo jobPostRepo;

    @Autowired
    SkillRepo skillRepo;

    @Override
    public Boolean saveEmployer(Employer employer) throws Exception {
        if (employer != null) {
            Employer data = new Employer();
            if (employer.getId() != null) {
                data.setId(employer.getId());
            }
            data.setEmail(employer.getEmail());
            data.setPassword(employer.getPassword());
            data.setIsEmployer(true);
            data.setLocation(employer.getLocation());
            data.setCompanyName(employer.getCompanyName());
            Employer result = repo.save(data);
            if (result != null) {
                return true;
            }

        }
        throw new Exception("Data can not be empty");
    }

    @Override
    public List<JobResultDTO> getCompanyJobs(String companyName) throws Exception {
        List<JobPost> jobPostList = jobPostRepo.findAll();
        List<JobResultDTO> data = new ArrayList<>();

            for(JobPost post: jobPostList)
            {
                if(post.getEmployerID().getCompanyName().equals(companyName))
                {
                    JobResultDTO dto = new JobResultDTO();
                    dto.setTitle(post.getTitle());
                    dto.setCompanyName(post.getEmployerID().getCompanyName());
                    dto.setLocation(post.getLocation().getName());
                    dto.setDescription(post.getDescription());
                    dto.setSkillList(getAllSkills(post.getSkillId()));
                    data.add(dto);
                }
            }

        return data;
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
