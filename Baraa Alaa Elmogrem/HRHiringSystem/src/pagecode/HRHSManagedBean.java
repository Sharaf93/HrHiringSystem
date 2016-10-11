package pagecode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.*;
import javax.servlet.http.HttpServletRequest;

import beans.Bean;

import model.Candidate;
import model.Phas;
import model.PhasesDetail;
import model.PhasesDetailPK;


@ManagedBean(name="ManagedBean")
@RequestScoped
public class HRHSManagedBean extends PageCodeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	Bean mybean;
	Boolean phaseStatus;
	List<Phas> phasesList;
	List<PhasesDetail>phaseDetailsByCandidateIdList; // A List of phasedetail objects (of the same candidate) to show phases details of a certain candidate in view candidate and Edit candidate pages ... this list is used in <ui:repeat> to show each phase details  
	List<String>reasonsOfFailure;
	List<Candidate>currentCandidatesOfCertainPhase;
	Map<String, String> reasonsDropDownMap;
	PhasesDetail currentPhaseDetail;
	Candidate candidateToBeViewed;  //An object of candidate to be viewed as a personal information of Candidate in View Candidate and Edit Candidate pages
	String test;
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Boolean getPhaseStatus() {
		return phaseStatus;
	}

	public void setPhaseStatus(Boolean phaseStatus) {
		this.phaseStatus = phaseStatus;
	}
	
	public Map<String, String> getReasonsDropDownMap() {
		if ( reasonsDropDownMap == null)
		{
			reasonsDropDownMap = new HashMap<String, String>();
			reasonsOfFailure = mybean.getAllReasonsOfFailure();
			int size = reasonsOfFailure.size();
			for ( int i = 0; i < size; ++i)
			{
				reasonsDropDownMap.put(reasonsOfFailure.get(i), reasonsOfFailure.get(i));
			}
		}
		return reasonsDropDownMap;
	}

	public void setReasonsDropDownMap(Map<String, String> reasonsDropDownMap) {
		this.reasonsDropDownMap = reasonsDropDownMap;
	}

	public List<String> getReasonsOfFailure() {
		if (reasonsOfFailure == null)
		{
			reasonsOfFailure = mybean.getAllReasonsOfFailure();
		}
		return reasonsOfFailure;
	}

	public void setReasonsOfFailure(List<String> reasonsOfFailure) {
		this.reasonsOfFailure = reasonsOfFailure;
	}

	public List<Phas> getPhasesList() {
		//System.out.println("visited getPhasesList function inside the managed bean");
		if ( phasesList == null)
		{
		phasesList = mybean.getAllPhases();
		//System.out.println("phases size" + phasesList.size());
		}
		return phasesList;
	}

	public void setPhasesList(List<Phas> phasesList) {
		this.phasesList = phasesList;
	}

	public List<PhasesDetail> getPhaseDetailsByCandidateIdList() {
		if ( phaseDetailsByCandidateIdList == null)
		{
			Long candidateId;
			String paramValue;
			Map<String,String> params;
	        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	        paramValue = params.get("candidateId");
	        candidateId = Long.valueOf(paramValue);
	        System.out.println("candidateId in phasedetails = " + Long.toString(candidateId));
	        phaseDetailsByCandidateIdList = mybean.getPhaesDetailsByCandidateId(candidateId);
	        
	        System.out.println("size = " + phaseDetailsByCandidateIdList.size());
		}
		return phaseDetailsByCandidateIdList;
	}

	public void setPhaseDetailsByCandidateIdList(
			List<PhasesDetail> phaseDetailsByPhaseIdList) {
		this.phaseDetailsByCandidateIdList = phaseDetailsByPhaseIdList;
	}
	
	public PhasesDetail getCurrentPhaseDetail() {
		if ( currentPhaseDetail == null )
		{
			currentPhaseDetail = new PhasesDetail();
		}
		return currentPhaseDetail;
	}

	public void setCurrentPhaseDetail(PhasesDetail currentPhaseDetail) {
		this.currentPhaseDetail = currentPhaseDetail;
	}

	public Candidate getCandidateToBeViewed() {
		if(candidateToBeViewed == null)
		{
			Long candidateId;
			String paramValue;
			Map<String,String> params;
	        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	        paramValue = params.get("candidateId");
	        System.out.println("paramValue = " + paramValue);
	        candidateId = Long.valueOf(paramValue);
	        System.out.println("long = " + Long.toString(candidateId) );
	        candidateToBeViewed = mybean.getOneCandidate(candidateId);
		}
		return candidateToBeViewed;
	}

	public void setCandidateToBeViewed(Candidate candidateToBeViewed) {
		System.out.println("da5let elseeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeet");
		this.candidateToBeViewed = candidateToBeViewed;
	}

	public List<Candidate> getCurrentCandidatesOfCertainPhase() {
		if (currentCandidatesOfCertainPhase == null)
		{
			Long currentPhaseId;
			String paramValue;
			Map<String,String> params;
	        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	        paramValue = params.get("currentPhaseId");
	        currentPhaseId = Long.valueOf(paramValue);
	        currentCandidatesOfCertainPhase = mybean.getPendingCandidatesOfCertainPhase(currentPhaseId);
		}
		
		return currentCandidatesOfCertainPhase;
	}

	public void setCurrentCandidatesOfCertainPhase(
			List<Candidate> currentCandidatesOfCertainPhase) {
		this.currentCandidatesOfCertainPhase = currentCandidatesOfCertainPhase;
	}

	public String UpdatePhaseOfCandidate()
	{
		Long candidateId;
		Long phaseId;
		String candidateIdParam;
		String phaseIdParam;
		Map<String,String> params;
		PhasesDetailPK PhasesDetailPKTemp;
		params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        candidateIdParam = params.get("candidateId");
        phaseIdParam = params.get("phaseId");
        candidateId = Long.valueOf(candidateIdParam);
        phaseId = Long.valueOf(phaseIdParam);
        PhasesDetailPKTemp = new PhasesDetailPK();
        PhasesDetailPKTemp.setCandidatesid(candidateId);
        PhasesDetailPKTemp.setPhasesid(phaseId);
        currentPhaseDetail.setId(PhasesDetailPKTemp);
        mybean.updatePhaseOfCandidate(currentPhaseDetail);
        
		return "UpdateCandidate";
	}

	public String viewPhaseDetailByCandidateId()
	{
		Long candidateId;
		String paramValue;
		Map<String,String> params;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        paramValue = params.get("phaseName");
        candidateId = Long.valueOf(paramValue);
        phaseDetailsByCandidateIdList = mybean.getPhaesDetailsByCandidateId(candidateId);
        
//		if ( request != null )
//		{
//			//temp = request.getParameter("phaseName");
////			if ( !temp.equals(""))
////			{
////				//phaseId = Long.valueOf(temp);
////				//System.out.println("value of HTTP request paramter after parsing = " + phaseId);
////				//phaseDetailsByPhaseIdList = mybean.getPhaesDetailsByPhaseId(phaseId);
////			}
////			else
////			{
////				// TODO throw an exception
////				System.out.println("HTTP request has been sent with no paramter");
////			}
//		}
//		else
//		{
//			// TODO Throw an exception !
//			System.out.println("No HTTP Request Sent");
//		}
		
		return "View Candidate";
	}

	public String viewAndEditCandidate()
	{
		System.out.println("visited viewAndEditCandidaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaate function");
//		
		Long candidateId;
		String paramValue;
		Map<String,String> params;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        paramValue = params.get("phaseName");
        candidateId = Long.valueOf(paramValue);
		
		return "/EditCandidate.xhtml?faces-redirect=true&candidateId=" + Long.toString(candidateId);
		//Long candidateId;
//		String paramValue;
//		String whichPageToView;
//		Map<String,String> params;
//        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //paramValue = params.get("candidateId");
        //whichPageToView = params.get("pageName");
        //System.out.println("paramValue = " + paramValue + " page to view = " + whichPageToView);
        //candidateId = Long.valueOf(paramValue);
        //candidateToBeViewed = mybean.getOneCandidate(candidateId);
       // phaseDetailsByCandidateIdList = mybean.getPhaesDetailsByCandidateId(candidateId);
		//return "View Candidate";
	}
	
	public String viewPendingCandidatesOfCertainPhase()
	{
		Long currentPhaseId;
		String paramValue;
		Map<String,String> params;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        paramValue = params.get("currentPhaseId");
        //System.out.println(paramValue);
        currentPhaseId = Long.valueOf(paramValue);
        currentCandidatesOfCertainPhase = mybean.getPendingCandidatesOfCertainPhase(currentPhaseId);
		return "phasesTable";
	}
}