package com.practice.practice.controller;

import com.practice.practice.dto.EmployeeProfileDTO;
import com.practice.practice.dto.JobResultDTO;
import com.practice.practice.dto.JobseekerDTO;
import com.practice.practice.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<JobseekerDTO>> getAllEmployee() throws Exception {
        try {
            List<JobseekerDTO> response = employeeService.getAllEmployee();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/save")
    public ResponseEntity<Boolean> saveEmployee(@RequestBody EmployeeProfileDTO employee) throws Exception {
        try {
            Boolean res = employeeService.saveEmployee(employee);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<JobseekerDTO>> getJobsBySkill(@PathVariable(value = "skill") String skillName) throws Exception {
        try {
            List<JobseekerDTO> res = employeeService.getBySkill(skillName);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<JobseekerDTO>> getJobsByLocation(@PathVariable(value = "location") String locationName) throws Exception {
        try {
            List<JobseekerDTO> res = employeeService.getByLocation(locationName);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<List<JobResultDTO>> getDataByProfile(@PathVariable(value = "id") Long id) throws Exception {
        try {
            List<JobResultDTO> res = employeeService.getDataByProfile(id);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

