package com.practice.practice.service;

import com.practice.practice.dto.JobResultDTO;
import com.practice.practice.model.Employer;

import java.util.List;

public interface IEmployerService {
    Boolean saveEmployer(Employer employer) throws Exception;

    List<JobResultDTO> getCompanyJobs(String companyName) throws Exception;
}
