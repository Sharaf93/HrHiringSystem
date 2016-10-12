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
import model.ReasonsOfFailure;
import model.TestsDetail;

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
	public List<ReasonsOfFailure> getAllReasonsOfFailure()
    {
    	Query query = em.createNamedQuery("getAllReasonsOfFailure");
    	
    	return query.getResultList();
    }
    
    public Candidate getOneCandidate(Long candidateId) 
    {
    	Query query = em.createNamedQuery("getCertainCandidate");
    	query.setParameter("id", candidateId);
    	System.out.println("candidateID inside elbean = " + Long.toString(candidateId));
    	System.out.println("candidateObj gowwa elbean = " + (Candidate) query.getSingleResult());
    	return (Candidate) query.getSingleResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<Candidate> getPendingCandidatesOfCertainPhase(Long currentPhaseId)
    {
    	Query query = em.createNamedQuery("getPendingCandidatesOfCertainPhase");
    	query.setParameter("id", currentPhaseId);
    	return query.getResultList();
    }
    
    public Long getCountOfPhases ()
    {
    	Query query = em.createNamedQuery("getCountOfPhases");
    	
    	return (Long) query.getSingleResult();
    }
    
    public Long getCandidateStatusID (String cssName)
    {
    	Query query = em.createNamedQuery("getCandidateStatusID");
    	String param = "%" + cssName + "%";
    	query.setParameter("cssName", param);
    	return (Long) query.getSingleResult();
    }
    
    public Long getPhaseIdByPhaseOrder(Long order)
    {
    	Query query = em.createNamedQuery("getPhaseIdByPhaseOrder");
    	query.setParameter("order", order);
    	return (Long) query.getSingleResult();
    }
    
    public Long getPhaseStatusIdByPhaseName (String name)
    {
    	Query query = em.createNamedQuery("getPhaseStatusIdByPhaseName");
    	String param = "%" + name + "%";
    	query.setParameter("name", param);
    	return (Long) query.getSingleResult();
    }
    
    public void updateCandidateMerge(PhasesDetail phDetail, Candidate cand )
    {	
    	em.persist(phDetail);
    	em.merge(cand);
    	em.flush();
    }
    
    public void editCandidateMerge(PhasesDetail phDetail, Candidate cand )
    {	
    	em.merge(phDetail);
    	em.merge(cand);
    	em.flush();
    }
    
    @SuppressWarnings("unchecked")
	public List<TestsDetail> getTestDetailsByCandidateId (Long candidateId)
    {
    	Query query = em.createNamedQuery("getTestDetailsByCandidateId");
    	query.setParameter("id", candidateId);
    	return query.getResultList();
    }
    
    public PhasesDetail getCertainPhaseDetail(Long candidateId, Long phaseId )
    {
    	Query query = em.createNamedQuery("getCertainPhaseDetail");
    	query.setParameter("candidateId", candidateId);
    	query.setParameter("phaseId", phaseId);
    	return (PhasesDetail) query.getSingleResult();

    }
}
