package ejb;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Entity;

@Stateless
public class PhasesDetailEJB implements Serializable{
	
	//Injected Database Connection
	@PersistenceContext private EntityManager em;	

}
