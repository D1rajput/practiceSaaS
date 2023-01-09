package com.practice.practice.service;


import com.practice.practice.model.Skill;

import java.util.List;

public interface ISkillService {
    List<Skill> getAllSkill() throws Exception;
    Boolean saveSkills(Skill skill) throws Exception;
}
