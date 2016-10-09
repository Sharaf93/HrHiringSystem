package ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Position;

//import model.controller.PositionManager;


@Stateless
@LocalBean
public class PositionsEJB  implements Serializable {
	
//	Entity Manager
	@PersistenceContext private EntityManager em;
			

   /**
    * Default constructor. 
    */
   public PositionsEJB() {
   }

   @SuppressWarnings("unchecked")
	public List<Position> getPositions() {
	   return em.createNamedQuery("Position.getPositions").getResultList();
   }


     @SuppressWarnings("unchecked")
    public List<String> getAllPositions() throws Exception {
         return em.createNamedQuery("getAllPositions").getResultList(); 
       }
     
      public long getPositionID(String positionAppliedForName)
      {
       
       return (Long) em.createNamedQuery("getPositionID").setParameter("posName", positionAppliedForName).getSingleResult();  
        
      }

	}


