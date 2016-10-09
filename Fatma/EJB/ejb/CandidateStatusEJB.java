package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class CandidateStatusEJB {

	@PersistenceContext
	private EntityManager em;

	public CandidateStatusEJB() {
	}
	
	 @SuppressWarnings("unchecked")
	public List <String> getAllStatuses()
	 {
		 return em.createNamedQuery("getAllCandidateStatuses").getResultList();
	 }
	 public long getCandidateStatusID(String csName)
 	 {
 		 
 			 return (Long) em.createNamedQuery("getCandidateStatusID").setParameter("cssName", csName).getSingleResult();	
 			 
 	}

}
