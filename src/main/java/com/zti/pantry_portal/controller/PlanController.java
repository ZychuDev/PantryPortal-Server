package com.zti.pantry_portal.controller;

import com.zti.pantry_portal.model.Plan;
import com.zti.pantry_portal.service.PlanService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


//DTO object
class RequestPlan{
    private String recipe_name;
    private Date date;

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

@CrossOrigin
@RestController
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/{date}")
    public ResponseEntity getPlan(Authentication authentication, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        List<Plan> plans = planService.findPlanByName(authentication.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Plan p : plans) {
            if(dateFormat.format(p.getDateField()).equals(dateFormat.format(date))){
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//    public ResponseEntity getPlans(Authentication authentication){
//        return ResponseEntity.ok(planService.findPlanByName(authentication.getName()));
//    }



    @DeleteMapping("{date}")
    public ResponseEntity deletePlan(Authentication authentication, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        planService.deletePlanByDate(authentication.getName(), date);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity addPlan(Authentication authentication, @RequestBody Plan plan){
        plan.setName(authentication.getName());
        planService.addPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/recipe")
    public ResponseEntity appendRecipe(Authentication authentication, @RequestBody RequestPlan request){
        System.out.println(request.toString());
        planService.addRecipeToPlan(authentication.getName(), request.getDate(), request.getRecipe_name());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/recipe/{recipe}/{date}")
    public ResponseEntity deleteRecipe(Authentication authentication, @PathVariable String recipe, @PathVariable String date){
        System.out.println(recipe);
        planService.deleteRecipeFromPlan(authentication.getName(), date, recipe);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
