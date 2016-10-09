package ejb;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Entity;

@Stateless
public class PhasesDetailEJB implements Serializable{
	
	//Injected Database Connection
	@PersistenceContext private EntityManager em;
	
	
	// Count of Fail Candidates Per Phases
	public int getCvFail(){
		
		return 0;
	}
	public int getPhoneFail(){
		
		return 0;
	}
	public int getExamsFail(){
		
		return 0;
	}
	public int getHRFail(){
		
		return 0;
	}
	public int getTechnicalFail(){
		
		return 0;
	}
	public int getPresentationFail(){
		
		return 0;
	}
	public int getTotalPhasesFail(){
		return 0;
	}
	
	// Count of Pending Candidates Per Phases
	public int getCvPending(){
		
		return 0;
	}
	public int getPhonePending(){
		
		return 0;
	}
	public int getExamsPending(){
		
		return 0;
	}
	public int getHRPending(){
		
		return 0;
	}
	public int getTechnicalPending(){
		
		return 0;
	}
	public int getPresentationPending(){
		
		return 0;
	}
	

}
