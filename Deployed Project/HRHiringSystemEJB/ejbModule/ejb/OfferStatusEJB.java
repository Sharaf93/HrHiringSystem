package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.OfferStatus;

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
	 
	 @SuppressWarnings("unchecked")
	public List<OfferStatus> getOfferStatuses()
	 {
		 Query query = em.createNamedQuery("getOfferStatuses");
		 
		 return query.getResultList();
		 
	 }
}
