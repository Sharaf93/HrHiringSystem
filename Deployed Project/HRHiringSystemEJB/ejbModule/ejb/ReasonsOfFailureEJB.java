package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class ReasonsOfFailureEJB {
	
	@PersistenceContext (unitName="HRHiringSystemEJB")
	private EntityManager em;
	
	public ReasonsOfFailureEJB()
	{
		
	}
	
	 public long getReasonOfFailureID(String roFName)
 	 {
 		 
 			 return (Long) em.createNamedQuery("getReasonOfFailureID").setParameter("rofName", roFName).getSingleResult();	
 			 
 	}

}
