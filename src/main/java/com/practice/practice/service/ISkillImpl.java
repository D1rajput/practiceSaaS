package com.practice.practice.service;

import com.practice.practice.model.Skill;
import com.practice.practice.repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISkillImpl implements ISkillService {

    @Autowired
    SkillRepo skillRepo;

    @Override
    public List<Skill> getAllSkill() throws Exception {
        return skillRepo.findAll();
    }

    @Override
    public Boolean saveSkills(Skill skill) throws Exception {
        Skill data = new Skill();
        if (skill.getId() != null) {
            data.setId(skill.getId());
        }
        data.setName(skill.getName());
        Skill result = skillRepo.save(data);
        if (result != null) {
            return true;
        }
        throw new Exception("Technology is not saved");
    }
}
