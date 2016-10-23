package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Source;

@Stateless
@LocalBean
public class SourcesEJB {

	@PersistenceContext(unitName="HRHiringSystemEJB")
	private EntityManager em;
	
	public SourcesEJB() {
	  
	   }

	 
	 	
	 @SuppressWarnings("unchecked")
	public List<String> getAllSources() throws Exception {
		 return em.createNamedQuery("getAllSources").getResultList();	
	   	 
	  }
	 
	 public long getSourceID(String source)
	 {
		 return (Long) em.createNamedQuery("getSourceID").setParameter("srcName", source).getSingleResult();	
		 
	 }



	@SuppressWarnings("unchecked")
	public List<Source> getSources() {
		// TODO Auto-generated method stub
		return (List<Source>) em.createNamedQuery("getSources").getResultList();
	}



	


}
