package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@LocalBean
@Stateless
public class OfferStatusEJB {

	@PersistenceContext  (unitName="HRHiringSystemEJB")
	private EntityManager em;
	
	public OfferStatusEJB()
	{
		
	}
	
	 public long getOfferStatusID(String osName)
 	 {
 		 
 			 return (Long) em.createNamedQuery("getOfferStatusID").setParameter("ofsName", osName).getSingleResult();	
 			 
 	}
}
