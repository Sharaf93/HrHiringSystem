package ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import model.controller.SourceManager;

@Stateless
@LocalBean
public class SourcesEJB implements Serializable{
	
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


}
