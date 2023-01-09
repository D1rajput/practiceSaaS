package com.practice.practice.controller;

import com.practice.practice.model.Skill;
import com.practice.practice.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    ISkillService skillService;

    @GetMapping("/all")
    public ResponseEntity<List<Skill>> getAllSkills() throws Exception
    {
        try {
            List<Skill> response=skillService.getAllSkill();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/save")
    public ResponseEntity<Boolean> saveSkills(@RequestBody Skill skill) throws Exception {
        try {
            Boolean res = skillService.saveSkills(skill);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
