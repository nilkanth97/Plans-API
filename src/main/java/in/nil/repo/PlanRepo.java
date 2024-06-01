package in.nil.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nil.entity.Plan;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Integer>{

}
