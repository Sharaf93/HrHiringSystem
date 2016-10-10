package ejb;

import java.util.List;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.LocalBean;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


import model.Candidate;

/**
 * Session Bean implementation class CandidatesEJB
 */
@Stateless
@LocalBean
public class CandidatesEJB {

	@PersistenceContext 
	private EntityManager em;
	

    /**
     * Default constructor. 
     */
    public CandidatesEJB() {
  
    }
 
  
  	
  	

      public void createCandidate(Candidate newCandidate) throws Exception {
          em.persist(newCandidate);
      }
      
      @SuppressWarnings("unchecked")
  	public List<Candidate> getAllCanditates() {
      	return em.createNamedQuery("getAllCandidates").getResultList();
      }
      public Long getCandidateIDByName(String candidatename)
      {
    	  return (Long)em.createNamedQuery("getCandidateID").setParameter("candName",candidatename).getSingleResult();
      }
    @SuppressWarnings("unchecked")
	public List<Candidate> searchCandidates(String searchQuery)
    {
    	
    	return em.createNativeQuery(searchQuery, Candidate.class).getResultList();
    }
  	//Count of Pass Candidates Per Positions
  	public int getSoftwareEngineerPass(){
  		return 0;
  	}
  	public int getStaffSoftwareEngineerPass(){
  		
  		return 0;
  	}
  	public int getSeniorSoftwareEngineerPass(){
  		
  		return 0;
  	}
  	public int getAssociateProjectManagerPass(){
  		
  		return 0;
  	}
  	public int getProjectManagerPass(){
  		
  		return 0;
  	}
  	public int getAssociateSoftwareTestEngineerPass(){
  		
  		return 0;
  	}
  	public int getSoftwareTestEngineerPass(){
  		
  		return 0;
  	}
  	public int getAssociateSoftwareSupportEngineerPass(){
  		
  		return 0;
  	}
  	public int getMarketingSpecialistPass(){
  		
  		return 0;
  	}
  	public int getJuniorAccountantPass(){
  		
  		return 0;
  	}
  	public int getItAdminPass(){
  		
  		return 0;
  	}
  	public int getSystemsEngineerPass(){
  		
  		return 0;
  	}
  	public int getSoftwareInternPass(){
  		
  		return 0;
  	}
  	public int getSoftwareSupportEngineerPass(){
  		
  		return 0;
  	}
  	public int getStaffSoftwareSupportEngineerPass(){
  		
  		return 0;
  	}
  	public int getBusinessAnalystPass(){
  		
  		return 0;
  	}
  	public int getPresalesEngineerPass(){
  		
  		return 0;
  	}
  	public int getHrAssociatePass(){
  		
  		return 0;
  	}
  	public int getHrExecutivePass(){
  		
  		return 0;
  	}
  	public int getHrSpecialistPass(){
  		
  		return 0;
  	}
  	public int getMarketingExecutivePass(){
  		
  		return 0;
  	}
  	public int getSalesAccountManagerPass(){
  		
  		return 0;
  	}
  	public int getSeniorAccountantPass(){
  		
  		return 0;
  	}
  	public int getSrItAdminPass(){
  		
  		return 0;
  	}
  	public int getHrInternPass(){
  		
  		return 0;
  	}





	public boolean checkEmailExists(String Cemail) {
		
		try{
	 		Candidate user = (Candidate) em.createNamedQuery("candEmailExists").setParameter("Cemail",Cemail).getSingleResult();
	 		}catch(NoResultException e){
	 			return false;
	 		}
	 		return true;
	}
  	
  	
public boolean checkMobileExists(Long Cmobile) {
		
		try{
	 		Candidate user = (Candidate) em.createNamedQuery("candMobileExists").setParameter("Cmob",Cmobile).getSingleResult();
	 		}catch(NoResultException e){
	 			return false;
	 		}
	 		return true;
	}





public Candidate getCandidatebyID(long id) {
	// TODO Auto-generated method stub
	return (Candidate)em.createNamedQuery("getCandidateByID").setParameter("cid", id).getSingleResult();
}


@SuppressWarnings("unchecked")
public List<Candidate> getCanditates() {
	return em.createNamedQuery("Candidate.getCandidates").getResultList();
}

@SuppressWarnings("unchecked")
public List<Candidate> getCandidatesModified(int hrsourceid, Date startDate, Date endDate ){
	  if(hrsourceid == 0){
		  return em.createNamedQuery("Candidate.getCandidatesModifiedNoHrId").setParameter("startDate", startDate)
				  .setParameter("endDate", endDate).getResultList();
	  }
	  else{
		  try {
  	      return em.createNamedQuery("Candidate.getCandidatesModified").setParameter("hr", hrsourceid)
  	    		  .setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
  	    } catch (NoResultException e) {
  	      return null;
  	    }
	  }
	  
}

@SuppressWarnings("unchecked")
public List<Candidate> getCandidatesPositionPhase(int positionid, int phaseid, int hrsourceid, 
		Date startDate, Date endDate ){
	  if(hrsourceid == 0){
		  	if((positionid == 0) && (phaseid == 0)){
	    		  return em.createNamedQuery("Candidate.getCandidatesModifiedNoHrId").setParameter("startDate", startDate)
	    				  .setParameter("endDate", endDate).getResultList();
			}
			else if(positionid == 0){
				return em.createNamedQuery("Candidate.getCandidatesPhaseNoHrId").setParameter("phaseid", phaseid).setParameter("startDate", startDate)
    				  .setParameter("endDate", endDate).getResultList();
			}
			else if(phaseid == 0){
				return em.createNamedQuery("Candidate.getCandidatesPositionNoHrId").setParameter("positionid", positionid).setParameter("startDate", startDate)
	    				  .setParameter("endDate", endDate).getResultList();
			}
			else{
				return em.createNamedQuery("Candidate.getCandidatesPositionPhaseNoHrId").setParameter("positionid", positionid).setParameter("phaseid", phaseid).setParameter("startDate", startDate)
    				  .setParameter("endDate", endDate).getResultList();
			}
	  }
	  else{
		  	if((positionid == 0) && (phaseid == 0)){
		  		return em.createNamedQuery("Candidate.getCandidatesModified").setParameter("hr", hrsourceid)
    	    		  .setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
			}
			else if(positionid == 0){
				return em.createNamedQuery("Candidate.getCandidatesPhase").setParameter("hr", hrsourceid).setParameter("phaseid", phaseid).setParameter("startDate", startDate)
	    				  .setParameter("endDate", endDate).getResultList();
			}
			else if(phaseid == 0){
				return em.createNamedQuery("Candidate.getCandidatesPosition").setParameter("hr", hrsourceid).setParameter("positionid", positionid).setParameter("startDate", startDate)
    				  .setParameter("endDate", endDate).getResultList();
			}
			else{
				return em.createNamedQuery("Candidate.getCandidatesPositionPhase").setParameter("hr", hrsourceid).setParameter("positionid", positionid).setParameter("phaseid", phaseid).setParameter("startDate", startDate)
	    				  .setParameter("endDate", endDate).getResultList();
			}
	  }
	  
}

  }




