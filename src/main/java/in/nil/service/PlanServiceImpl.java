package in.nil.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nil.entity.Plan;
import in.nil.entity.PlanCategory;
import in.nil.repo.PlanCategoryRepo;
import in.nil.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService{
	
	@Autowired
	private PlanRepo planRepo;
	@Autowired
	private PlanCategoryRepo planCategoryRepo;
	
	
	
	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> categories = planCategoryRepo.findAll();	
		Map<Integer, String> categoryMap = new HashMap<>();
		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoryMap;
	}

	
	
	
	@Override
	public boolean savePlan(Plan plan) {
			Plan saved = planRepo.save(plan);						// save() is upsert method ( save and update )
		return saved.getPlanId()!=null;
		
		/*if(saved.getPlanId()!=null) {								Fresher Level
			return true;
		}else {
			return false;
		}*/
		
		//	return saved.getPlanId()!=null ? true : false ;			1 year Experience Level
	}

	
	
	
	@Override
	public boolean updatePlan(Plan plan) {
		Plan saved = planRepo.save(plan);							// save() is upsert method ( save and update )
		return plan.getPlanId()!=null ? true : false;
	}

	
	
	
	
	@Override
	public List<Plan> getAllPlans() {
		return planRepo.findAll();
	}

	
	
	
	
	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> findById = planRepo.findById(planId);        //Optional mens record may available or not
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	
	
	
	
	@Override
	public boolean deletePlan(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);							//if id is not null
			status = true;											// return true
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	
	
	
	
	
	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);
		if(findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			return true;
		}
		return false;
	}

}
