package com.practice.practice.service;

import com.practice.practice.dto.JobPostDTO;
import com.practice.practice.dto.JobResultDTO;
import com.practice.practice.model.JobPost;
import com.practice.practice.model.Skill;
import com.practice.practice.repository.JobPostRepo;
import com.practice.practice.repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IJobPostIMpl implements IJobPostService {
    @Autowired
    JobPostRepo jobPostRepo;

    @Autowired
    SkillRepo skillRepo;

    @Override
    public Boolean saveJobPosting(JobPostDTO jobPost) throws Exception {
        if (jobPost != null) {
            JobPost post = new JobPost();
            post.setDescription(jobPost.getDescription());
            post.setEmployerID(jobPost.getEmployerID());
            post.setLocation(jobPost.getLocation());
            String skillIds = jobPost.getSkillId().stream().collect(Collectors.joining("|"));
            post.setSkillId(skillIds);
            post.setTitle(jobPost.getTitle());
            JobPost result = jobPostRepo.save(post);
            if (result != null) {
                return true;
            }

        }
        throw new Exception("Data can not be empty");
    }

    @Override
    public List<JobResultDTO> getAllJobs() throws Exception {
        List<JobPost> jobPostList = jobPostRepo.findAll();
        List<JobResultDTO> data = new ArrayList<>();
        for (JobPost post : jobPostList) {
            JobResultDTO dto = new JobResultDTO();
            dto.setTitle(post.getTitle());
            dto.setDescription(post.getDescription());
            dto.setCompanyName(post.getEmployerID().getCompanyName());
            dto.setLocation(post.getLocation().getName());
            dto.setSkillList(getAllSkills(post.getSkillId()));
            data.add(dto);
        }
        return data;
    }

    @Override
    public List<JobResultDTO> getJobsByTitle(String title) throws Exception {
        List<JobPost> jobPostList = jobPostRepo.findAll();
        List<JobResultDTO> data = new ArrayList<>();
        for (JobPost post : jobPostList) {
            if (post.getTitle().equals(title)) {
                JobResultDTO dto = new JobResultDTO();
                dto.setTitle(post.getTitle());
                dto.setDescription(post.getDescription());
                dto.setCompanyName(post.getEmployerID().getCompanyName());
                dto.setLocation(post.getLocation().getName());
                dto.setSkillList(getAllSkills(post.getSkillId()));
                data.add(dto);
            }
        }
        return data;
    }

    @Override
    public List<JobResultDTO> getJobsBySkill(String skillName) throws Exception {
        List<JobPost> jobPostList = jobPostRepo.findAll();
        List<JobResultDTO> data = new ArrayList<>();
        for (JobPost post : jobPostList) {
            List<Skill> skills = getAllSkills(post.getSkillId());
            for (Skill sk : skills) {
                if (sk.getName().equals(skillName)) {
                    JobResultDTO dto = new JobResultDTO();
                    dto.setTitle(post.getTitle());
                    dto.setDescription(post.getDescription());
                    dto.setCompanyName(post.getEmployerID().getCompanyName());
                    dto.setLocation(post.getLocation().getName());
                    dto.setSkillList(skills);
                    data.add(dto);
                }
            }
        }
        return data;
    }

    @Override
    public List<JobResultDTO> getJobsByLocation(String locationName) throws Exception {
        List<JobPost> jobPostList = jobPostRepo.findAll();
        List<JobResultDTO> data = new ArrayList<>();
        for (JobPost post : jobPostList) {
            if (post.getLocation().getName().equals(locationName)) {
                JobResultDTO dto = new JobResultDTO();
                dto.setTitle(post.getTitle());
                dto.setDescription(post.getDescription());
                dto.setCompanyName(post.getEmployerID().getCompanyName());
                dto.setLocation(post.getLocation().getName());
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
