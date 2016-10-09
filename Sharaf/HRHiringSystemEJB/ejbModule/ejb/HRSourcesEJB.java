package ejb;
import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import model.Hrsource;


//import model.controller.HRSourceManager;




@Stateless
@LocalBean

public class HRSourcesEJB implements Serializable{
	
//	Entity Manager
	@PersistenceContext private EntityManager em;

   /**
    * Default constructor. 
    */
   public HRSourcesEJB() {
   }
	
   @SuppressWarnings("unchecked")
 	public List<Hrsource> getHrSources() {
     	return em.createNamedQuery("Hrsource.getHrsources").getResultList();
     }
     
	
 
 	



}
