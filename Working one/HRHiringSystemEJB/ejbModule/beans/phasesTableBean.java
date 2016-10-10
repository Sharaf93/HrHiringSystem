package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ibm.ws.batch.xJCL.beans.returnCodeExpression;

import model.Candidate;
import model.PhasesDetail;
import model.TestsDetail;

/**
 * Session Bean implementation class phasesTableBean
 */
@Stateless
@LocalBean
public class phasesTableBean {

	
	
    /**
     * Default constructor. 
     */
    public phasesTableBean() {
        // TODO Auto-generated constructor stub
    }

    
	@PersistenceContext(unitName="HRHiringSystemEJB")
	EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidatesOfCertainPhase (String phase)
	{
		Query query;
		
		Long phaseID;
		
		List<Long> candidateID;
		
		
		query = em.createQuery("Select p.id FROM Phas p where p.name = :name");
		
		query.setParameter("name", phase);
		
		phaseID = (Long) query.getSingleResult();
		
		System.out.println("phaseID = " + phaseID.toString());
		
		
		
		query = em.createQuery("Select k.candidate.id FROM PhasesDetail k where k.phas.id = :id");
		
		query.setParameter("id", phaseID);
		
		candidateID = query.getResultList();
		
		
		System.out.println("candidateID = " + candidateID);
		
		
		Iterator<Long> i = candidateID.iterator();
		
		List<Candidate> candidates = new ArrayList<Candidate>();
		
		
		while (i.hasNext())
		{
		query = em.createQuery("Select c FROM Candidate c where c.id = :id");
		
		query.setParameter("id", i.next());
		
		candidates.add((Candidate)query.getSingleResult());
		
		System.out.println("candidates = " + candidates);
		}
		
		
		//TypedQuery<Candidate> query = em.createQuery(
	//            "SELECT c FROM Candidate c where c.id = Select candi", Candidate.class);
	        //return query.getResultList();
		
		//return new ArrayList<Candidate>();
		
		return candidates;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllPhasesNames()
	{
		////TODO try and catch!!
		
		
		//Query query = em.createQuery("Select p.name FROM phases p");
		//return  query.getResultList();
		
		//Query query = em.createQuery("");
		
		//return query.getResultList();
		
		//System.out.println("gowwa elpersistence");
		
       //Query query = em.createQuery("Select p.id FROM Phas p where p.name = :name");
		
		Query query = em.createQuery("Select p.name FROM Phas p");
		
		//query.setParameter("name", phase);
		
		
		//Long x = (Long) query.getSingleResult();
		
		System.out.println( "gowwa elejb");
		
		//TypedQuery<Candidate> query = em.createQuery(
	//            "SELECT c FROM Candidate c where c.id = Select candi", Candidate.class);
	        return query.getResultList();

	}
	
	
	@SuppressWarnings("unchecked")
	public List<PhasesDetail> getPhasesOfCertainCandidate(long CandidateID)
	{
		System.out.println("da5al all function");
		
		//Query query = em.createQuery("select p from PhasesDetail p");
		Query query = em.createNamedQuery("PhasesDetail.selectAllPhasesOfCertainID");
		
		query.setParameter("id", CandidateID);
		
		List<PhasesDetail> temp = query.getResultList();
		
		System.out.println(temp);
		
		return query.getResultList();
	}

//	public MyPhases  getPhasesOfCertainCandidate ( long candidateID )
//	{		
//		Query query = em.createQuery("Select p FROM PhasesDetail p where p.candidate.id = :id");
//		query.setParameter("id", candidateID);
//		return (MyPhases) query.getSingleResult();
//	}
	@SuppressWarnings("unchecked")
	public List<MyPhases>   getPhases1 ( long candidateID )
	{
		Query query = em.createQuery("Select p FROM PhasesDetail p");
		return query.getResultList(); //(MyPhases) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TestsDetail> getTestsOfCertainCandidate ( long candidateID)
	{
		Query query = em.createNamedQuery("testsOfCertainCandidate");
		
		query.setParameter("id", candidateID);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Candidate> canl ()
	{
		Query q = em.createQuery("select c from Candidate c");
		
		return q.getResultList();
	}
	
	
	public void traill ( List<PhasesDetail> ph )
	{
		for ( int i = 0 ; i < ph.size(); ++i)
		{

		em.merge(ph.get(i));
		}
		em.flush();
	}
	
	public void trialwa7d (PhasesDetail PD)
	{
		em.merge(PD);
		em.flush();
	}
}