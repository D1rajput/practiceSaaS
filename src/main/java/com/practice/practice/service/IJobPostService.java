package com.practice.practice.service;

import com.practice.practice.dto.JobPostDTO;
import com.practice.practice.dto.JobResultDTO;
import com.practice.practice.model.JobPost;

import java.util.List;

public interface IJobPostService {
    Boolean saveJobPosting(JobPostDTO jobPost) throws Exception;
    List<JobResultDTO> getAllJobs() throws Exception;
    List<JobResultDTO> getJobsByTitle(String title) throws Exception;
    List<JobResultDTO> getJobsBySkill(String skillName) throws Exception;
    List<JobResultDTO> getJobsByLocation(String locationName) throws Exception;
}
