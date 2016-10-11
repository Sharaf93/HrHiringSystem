package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.omg.Security.Public;

import model.Candidate;
import model.Phas;
import model.PhasesDetail;

/**
 * Session Bean implementation class Bean
 */
@Stateless
@LocalBean
public class Bean {

	// TODO try and catch all the functions
    /**
     * Default constructor. 
     */
    public Bean() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName="HRHiringSystemEJB")
	EntityManager em;
    
    @SuppressWarnings("unchecked")
	public List<Phas> getAllPhases ()
    {
    	System.out.println("visited getAllPhases Function in EJB called bean");
    	Query query = em.createNamedQuery("getAllPhases");
    	return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<PhasesDetail> getPhaesDetailsByCandidateId(long candidateId)
    {
    	Query query = em.createNamedQuery("getPhaesDetailsByCandidateId");
    	query.setParameter("id", candidateId);
    	return query.getResultList();
    }
    
    public void updatePhaseOfCandidate(PhasesDetail currentPhaseDetail)
    {
    	em.persist(currentPhaseDetail);
    }
    
    @SuppressWarnings("unchecked")
	public List<String> getAllReasonsOfFailure()
    {
    	Query query = em.createNamedQuery("getAllReasonsOfFailure");
    	
    	return query.getResultList();
    }
    
    public Candidate getOneCandidate(long candidateId) 
    {
    	Query query = em.createQuery("SELECT c from Candidate c WHERE c.id = :id");
    	query.setParameter("id", candidateId);
    	return (Candidate) query.getSingleResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<Candidate> getPendingCandidatesOfCertainPhase(Long currentPhaseId)
    {
    	Query query = em.createNamedQuery("getPendingCandidatesOfCertainPhase");
    	query.setParameter("id", currentPhaseId);
    	return query.getResultList();
    }
}
