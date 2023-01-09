package com.practice.practice.controller;

import com.practice.practice.dto.JobPostDTO;
import com.practice.practice.dto.JobResultDTO;
import com.practice.practice.service.IJobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/job")
public class JobPostController {

    @Autowired
    IJobPostService jobService;

    @PostMapping(value = "/save")
    public ResponseEntity<Boolean> saveJob(@RequestBody JobPostDTO jobPost) throws Exception {
        try {
            Boolean res = jobService.saveJobPosting(jobPost);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobResultDTO>> getAllJobs() throws Exception {
        try {
            List<JobResultDTO> res = jobService.getAllJobs();
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<JobResultDTO>> getJobsByTitle(@PathVariable(value = "title") String title) throws Exception {
        try {
            List<JobResultDTO> res = jobService.getJobsByTitle(title);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<JobResultDTO>> getJobsBySkill(@PathVariable(value = "skill") String skillName) throws Exception {
        try {
            List<JobResultDTO> res = jobService.getJobsBySkill(skillName);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<JobResultDTO>> getJobsByLocation(@PathVariable(value = "location") String locationName) throws Exception {
        try {
            List<JobResultDTO> res = jobService.getJobsByLocation(locationName);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
