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

   @SuppressWarnings("unchecked")
	public List<String> getAllPhases() {
		System.out.println(" in get all phases ");
		System.out.println("get all phases size ");
		//System.out.println(em.createNamedQuery("getAllPhases").getResultList().size());
		return em.createNamedQuery("getAllPhases").getResultList();
	}

	public long getCurrentPhaseID(String phaseName) {

		return (Long) em.createNamedQuery("getPhaseID")
				.setParameter("pName", phaseName).getSingleResult();

	}

	public long getPhaseOrder(String phaseName) {
		return (Long) em.createNamedQuery("getPhaseOrder")
				.setParameter("ppName", phaseName).getSingleResult();
	}

}
