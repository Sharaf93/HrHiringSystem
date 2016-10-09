package ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Phas;

@Stateless
@LocalBean
public class PhasesEJB  implements Serializable {
	
//	Entity Manager
	@PersistenceContext private EntityManager em;
	
	public PhasesEJB(){
	}
	
   @SuppressWarnings("unchecked")
	public List<Phas> getPhases() {
	   return em.createNamedQuery("Phase.getPhases").getResultList();
   }

}
