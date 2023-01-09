package com.practice.practice.controller;

import com.practice.practice.dto.JobResultDTO;
import com.practice.practice.model.Employer;
import com.practice.practice.service.IEmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    IEmployerService employerService;

    @PostMapping(value = "/save")
    public ResponseEntity<Boolean> saveEmployer(@RequestBody Employer employer) throws Exception {
        try {
            Boolean res = employerService.saveEmployer(employer);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<JobResultDTO>> getCompanyJobs(@PathVariable(value = "name") String companyName) throws Exception {
        try {
            List<JobResultDTO> res = employerService.getCompanyJobs(companyName);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
