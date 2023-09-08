package com.zti.pantry_portal.service;

import com.zti.pantry_portal.model.Plan;
import com.zti.pantry_portal.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> findPlanByName(String name){
        return planRepository.findByName(name);
    }

    public void addPlan(Plan plan){
        planRepository.save(plan);
    }

    public void deletePlanByDate(String name, Date date){
        List<Plan> plans = findPlanByName(name);
        for (Plan p : plans) {
            if(p.getDateField() == date){
                planRepository.delete(p);
            }
        }
    }

    public void deleteRecipeFromPlan(String name, String date, String recipe){
        List<Plan> plans = findPlanByName(name);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Plan p : plans) {
            if(dateFormat.format(p.getDateField()).equals(date)){
                p.removeRecipe(recipe);
                planRepository.save(p);
            }
        }
    }

    public void addRecipeToPlan(String name, Date date, String recipe){
        List<Plan> plans = findPlanByName(name);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Plan p : plans) {
            if(dateFormat.format(p.getDateField()).equals(dateFormat.format(date))){
                p.appendRecipe(recipe);
                planRepository.save(p);
            }
        }
    }

}
