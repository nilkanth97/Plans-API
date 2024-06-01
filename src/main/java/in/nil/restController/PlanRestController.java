package in.nil.restController;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.nil.entity.Plan;
import in.nil.messagefromyml.AppProperties;
import in.nil.service.PlanService;
import in.nil.stringsonstants.AppConstants;

@RestController
public class PlanRestController {

	//Autowiring
	private PlanService planService;
	private Map<String, String> messages;
	public PlanRestController(PlanService planservice, AppProperties appProperties) {
		this.planService = planService;
		this.messages = appProperties.getMessages();
	}
	
	
	
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories(){
			Map<Integer, String> categories = planService.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		String responseMsg = AppConstants.EMPTY_STR;
		boolean isSaved = planService.savePlan(plan);
		if(isSaved) {
			responseMsg = messages.get(AppConstants.PLAN_SAVE_SUCC);
		}else {
			responseMsg	= messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}
	
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		Plan planById = planService.getPlanById(planId);
		return new ResponseEntity<>(planById, HttpStatus.OK);
	}
	
	
	@PutMapping("/plan/{plan}")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		boolean isUpdated = planService.updatePlan(plan);
		String msg = "";
		if(isUpdated) {
			msg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		}else {
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		boolean isDeleted = planService.deletePlan(planId);
		String msg = "";
		if(isDeleted) {
			msg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		}else {
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
	@PutMapping("status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status){
		boolean isStatusChanged = planService.planStatusChange(planId, status);
		String msg = "";
		if(isStatusChanged) {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE);
		}else {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
}
