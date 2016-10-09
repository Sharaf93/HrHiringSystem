package ejb;
import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import model.HRSource;




@Stateless
@LocalBean

public class HRSourcesEJB {

	@PersistenceContext(unitName="HRHiringSystemEJB")
	private EntityManager em;

	
   /**
    * Default constructor. 
    */
   public HRSourcesEJB() {
       // TODO Auto-generated constructor stub
   }

 
 	
   @SuppressWarnings("unchecked")
 		public List<String> getAllHRSources() throws Exception {
 		 		 return em.createNamedQuery("getAllHRSources").getResultList();	
 	     }
 	  
 	 public long getHRSourceID(String hrSource)
 	 {
 		 
 			 return (Long) em.createNamedQuery("getHRSourceID").setParameter("hrsrcName", hrSource).getSingleResult();	
 			 
 	}
 	 public long getHRSourceIDByUserName(String username)
 	 {
 		 return (Long) em.createNamedQuery("getHRSourceIDByUN").setParameter("hrun", username).getSingleResult();
 	 }
 	 
 	public boolean checkUserName(String username)
 	{
 		try{
 		HRSource user = (HRSource) em.createNamedQuery("checkHRUserName").setParameter("hrun",username).getSingleResult();
 		}catch(NoResultException e){
 			return false;
 		}
 		return true;
 	}



	public void createHRSource(HRSource hrsource) {
		// TODO Auto-generated method stub
		em.persist(hrsource);
		
	}



	public String getPositionByID(long currentUserID) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("getHRPositionByID").setParameter("hrid", currentUserID).getSingleResult().toString();
	}



	public String getHRNameByID(Long currentUID) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("getHRNameByID").setParameter("hrid", currentUID).getSingleResult().toString();
	}
 	 



}
