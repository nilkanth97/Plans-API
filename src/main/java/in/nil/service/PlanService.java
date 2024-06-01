package in.nil.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import in.nil.entity.Plan;

@Service
public interface PlanService {
	
	public Map<Integer, String> getPlanCategories();
	
	public boolean savePlan(Plan plan);
	
	public boolean updatePlan(Plan plan);
	
	public List<Plan> getAllPlans();
	
	public Plan getPlanById(Integer planId);
	
	public boolean deletePlan(Integer planId);
	
	public boolean planStatusChange(Integer planId, String status);
	
	
}
