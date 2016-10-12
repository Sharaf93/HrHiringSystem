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

import org.omg.CORBA.PUBLIC_MEMBER;

import beans.Bean;

import model.Candidate;
import model.Phas;
import model.PhasesDetail;
import model.PhasesDetailPK;
import model.ReasonsOfFailure;
import model.TestsDetail;

@ManagedBean(name = "ManagedBean")
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
	List<PhasesDetail> phaseDetailsByCandidateIdList; // A List of phasedetail
														// objects (of the same
														// candidate) to show
														// phases details of a
														// certain candidate in
														// view candidate and
														// Edit candidate pages
														// ... this list is used
														// in <ui:repeat> to
														// show each phase
														// details
	List<ReasonsOfFailure> reasonsOfFailure;
	List<Candidate> currentCandidatesOfCertainPhase;
	List<TestsDetail> testDetailsList;
	Map<String, Long> reasonsDropDownMap;
	PhasesDetail currentPhaseDetail;
	PhasesDetail currentPhaseDetailOfEdit;
	Candidate candidateToBeViewed; // An object of candidate to be viewed as a
									// personal information of Candidate in View
									// Candidate and Edit Candidate pages
	String test;
	String reasonOfFailure;
	Long reasonOfFailureId;

	
	public Long getReasonOfFailureId() {
		return reasonOfFailureId;
	}

	public void setReasonOfFailureId(Long reasonOfFailureId) {
		this.reasonOfFailureId = reasonOfFailureId;
	}

	public String getReasonOfFailure() {
		return reasonOfFailure;
	}

	public void setReasonOfFailure(String reasonOfFailure) {
		this.reasonOfFailure = reasonOfFailure;
	}

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

	public Map<String, Long> getReasonsDropDownMap() {
		if (reasonsDropDownMap == null) {
			reasonsDropDownMap = new HashMap<String, Long>();
			reasonsOfFailure = mybean.getAllReasonsOfFailure();
			int size = reasonsOfFailure.size();
			
			
			for (int i = 0; i < size; ++i) {
				reasonsDropDownMap.put(reasonsOfFailure.get(i).getName().toString(),
						reasonsOfFailure.get(i).getId());
			}
		}
		return reasonsDropDownMap;
	}

	public void setReasonsDropDownMap(Map<String, Long> reasonsDropDownMap) {
		this.reasonsDropDownMap = reasonsDropDownMap;
	}


	public List<ReasonsOfFailure> getReasonsOfFailure() {
		if ( reasonsOfFailure == null)
		{
			reasonsOfFailure = mybean.getAllReasonsOfFailure();
		}
		return reasonsOfFailure;
	}

	public void setReasonsOfFailure(List<ReasonsOfFailure> reasonsOfFailure) {
		this.reasonsOfFailure = reasonsOfFailure;
	}

	public List<Phas> getPhasesList() {
		// System.out.println("visited getPhasesList function inside the managed bean");
		if (phasesList == null) {
			phasesList = mybean.getAllPhases();
			// System.out.println("phases size" + phasesList.size());
		}
		return phasesList;
	}

	public void setPhasesList(List<Phas> phasesList) {
		this.phasesList = phasesList;
	}

	public List<PhasesDetail> getPhaseDetailsByCandidateIdList() {
		if (phaseDetailsByCandidateIdList == null) {
			Long candidateId;
			String paramValue;
			Map<String, String> params;
			params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			paramValue = params.get("candidateId");
			candidateId = Long.valueOf(paramValue);
			System.out.println("candidateId in phasedetails = "
					+ Long.toString(candidateId));
			phaseDetailsByCandidateIdList = mybean
					.getPhaesDetailsByCandidateId(candidateId);

			System.out
					.println("size = " + phaseDetailsByCandidateIdList.size());
		}
		return phaseDetailsByCandidateIdList;
	}

	public void setPhaseDetailsByCandidateIdList(
			List<PhasesDetail> phaseDetailsByPhaseIdList) {
		this.phaseDetailsByCandidateIdList = phaseDetailsByPhaseIdList;
	}
	
	public List<TestsDetail> getTestDetailsList() {
		if ( testDetailsList == null)
		{	
			if ( candidateToBeViewed != null)
			{
				Long candidateId = candidateToBeViewed.getId();
				testDetailsList = mybean.getTestDetailsByCandidateId(candidateId);
			}
		}
		return testDetailsList;
	}

	public void setTestDetailsList(List<TestsDetail> testDetailsList) {
		this.testDetailsList = testDetailsList;
	}

	public PhasesDetail getCurrentPhaseDetail() {

		System.out.println("gowa elcurrentphaseDetail");
		
		if (currentPhaseDetail == null) {
			currentPhaseDetail = new PhasesDetail();

			Long candidateId;
			Long phaseId;
			String paramValue;
			Map<String, String> params;
			PhasesDetailPK PhasesDetailPKTemp;
			params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			paramValue = params.get("candidateId");
			candidateId = Long.valueOf(paramValue);
			candidateToBeViewed = mybean.getOneCandidate(candidateId);
			phaseId = candidateToBeViewed.getPhase().getId();
			PhasesDetailPKTemp = new PhasesDetailPK();
			PhasesDetailPKTemp.setCandidatesid(candidateId);
			PhasesDetailPKTemp.setPhasesid(phaseId);
			currentPhaseDetail.setId(PhasesDetailPKTemp);
		}
		return currentPhaseDetail;
	}

	public void setCurrentPhaseDetail(PhasesDetail currentPhaseDetail) {
		this.currentPhaseDetail = currentPhaseDetail;
	}

	public PhasesDetail getCurrentPhaseDetailOfEdit() {
		
		if (currentPhaseDetailOfEdit == null) {
			currentPhaseDetailOfEdit = new PhasesDetail();

			Long candidateId;
			Long phaseId;
			String paramValue;
			Map<String, String> params;
			params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			paramValue = params.get("candidateId");
			candidateId = Long.valueOf(paramValue);
			phaseId = candidateToBeViewed.getPhase().getId();
			currentPhaseDetailOfEdit = mybean.getCertainPhaseDetail(candidateId, phaseId);
			
			if(currentPhaseDetailOfEdit.getPhaseStatus().getName().equals( "Passed"))
			{
				phaseStatus = true;
				System.out.println("trueeeeeeeeeeeeeeeeeeeeeeeeeeeee"+currentPhaseDetailOfEdit.getPhaseStatus().getName());
			}
			else
			{
				phaseStatus = false;
				System.out.println("falseeeeeeeeeeeeeeeeeeeeeeeeeeeee"+currentPhaseDetailOfEdit.getPhaseStatus().getName());
			}
		}
		return currentPhaseDetailOfEdit;
	}

	public void setCurrentPhaseDetailOfEdit(PhasesDetail currentPhaseDetailOfEdit) {
		this.currentPhaseDetailOfEdit = currentPhaseDetailOfEdit;
	}

	public Candidate getCandidateToBeViewed() {
		if (candidateToBeViewed == null) {
			Long candidateId;
			String paramValue;
			Map<String, String> params;
			params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			paramValue = params.get("candidateId");
			System.out.println("paramValue = " + paramValue);
			candidateId = Long.valueOf(paramValue);
			System.out.println("long = " + Long.toString(candidateId));
			candidateToBeViewed = mybean.getOneCandidate(candidateId);
		}
		return candidateToBeViewed;
	}

	public void setCandidateToBeViewed(Candidate candidateToBeViewed) {
		System.out
				.println("da5let elseeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeet");
		this.candidateToBeViewed = candidateToBeViewed;
	}

	public List<Candidate> getCurrentCandidatesOfCertainPhase() {
		if (currentCandidatesOfCertainPhase == null) {
			// Long candidateId;
			Long currentPhaseId;
			String paramValue;
			Map<String, String> params;
			params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			paramValue = params.get("currentPhaseId");
			currentPhaseId = Long.valueOf(paramValue);
			// candidateId = Long.valueOf(candidatIdParamValue);

			currentCandidatesOfCertainPhase = mybean
					.getPendingCandidatesOfCertainPhase(currentPhaseId);
		}

		return currentCandidatesOfCertainPhase;
	}

	public void setCurrentCandidatesOfCertainPhase(
			List<Candidate> currentCandidatesOfCertainPhase) {
		this.currentCandidatesOfCertainPhase = currentCandidatesOfCertainPhase;
	}

	public String UpdatePhaseOfCandidate() {
		// Long candidateId;
		// Long phaseId;
		// String candidateIdParam;
		// //String phaseIdParam;
		// Map<String,String> params;
		// PhasesDetailPK PhasesDetailPKTemp;
		// params =
		// FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// candidateIdParam = params.get("candidateId");
		// candidateId = Long.valueOf(candidateIdParam);
		// candidateToBeViewed = mybean.getOneCandidate(candidateId);
		// phaseId = candidateToBeViewed.getPhase().getId();
		// PhasesDetailPKTemp = new PhasesDetailPK();
		// PhasesDetailPKTemp.setCandidatesid(candidateId);
		// PhasesDetailPKTemp.setPhasesid(phaseId);
		// currentPhaseDetail.setId(PhasesDetailPKTemp);
		// TODO some logic here of the next phase and next phase

		if (phaseStatus == true) {
			Long size = mybean.getCountOfPhases();

			Long currentPhaseOrder = candidateToBeViewed.getPhase()
					.getPhaseorder();

			Long currentPhaseStatusID = mybean
					.getPhaseStatusIdByPhaseName("Pass");

			System.out.println("currentPhaseStatusID = " + Long.toString(currentPhaseStatusID));
			currentPhaseDetail.setPhasestatusid(currentPhaseStatusID);

			if (currentPhaseOrder + 1 > size) {
				Long candidateStatusID = mybean.getCandidateStatusID("Pass");

				candidateToBeViewed.setStatusid(candidateStatusID);
			} else {
				Long nextPhaseId = mybean
						.getPhaseIdByPhaseOrder(currentPhaseOrder + 1);
				candidateToBeViewed.setCurrentphaseid(nextPhaseId);
			}
		}

		else {
			
			Long currentPhaseStatusID = mybean
					.getPhaseStatusIdByPhaseName("Fail");

			System.out.println("currentPhaseStatusID = " + Long.toString(currentPhaseStatusID));
			
			currentPhaseDetail.setPhasestatusid(currentPhaseStatusID);

			Long candidateStatusID = mybean.getCandidateStatusID("Archiv");

			candidateToBeViewed.setStatusid(candidateStatusID);

			// TODO ta7oot elreason of failureID
			 candidateToBeViewed.setReasonsoffailureid(reasonOfFailureId);
		}
		// mybean.updatePhaseOfCandidate(currentPhaseDetail);
		
		currentPhaseDetail.setLastmodifiedbyid(1);
		
		mybean.updateCandidateMerge(currentPhaseDetail, candidateToBeViewed);
		
		return "/View Candidate.xhtml?faces-redirect=true&candidateId="
				+ Long.toString(candidateToBeViewed.getId());
	}

	public String viewPhaseDetailByCandidateId() {
		Long candidateId;
		String paramValue;
		Map<String, String> params;
		params = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		paramValue = params.get("phaseName");
		candidateId = Long.valueOf(paramValue);
		phaseDetailsByCandidateIdList = mybean
				.getPhaesDetailsByCandidateId(candidateId);

		// if ( request != null )
		// {
		// //temp = request.getParameter("phaseName");
		// // if ( !temp.equals(""))
		// // {
		// // //phaseId = Long.valueOf(temp);
		// //
		// //System.out.println("value of HTTP request paramter after parsing = "
		// + phaseId);
		// // //phaseDetailsByPhaseIdList =
		// mybean.getPhaesDetailsByPhaseId(phaseId);
		// // }
		// // else
		// // {
		// // // TODO throw an exception
		// // System.out.println("HTTP request has been sent with no paramter");
		// // }
		// }
		// else
		// {
		// // TODO Throw an exception !
		// System.out.println("No HTTP Request Sent");
		// }

		return "View Candidate";
	}

	public String EditCandidate() {
		
		if (phaseStatus == true) {
			Long size = mybean.getCountOfPhases();

			Long currentPhaseOrder = candidateToBeViewed.getPhase()
					.getPhaseorder();

			Long currentPhaseStatusID = mybean
					.getPhaseStatusIdByPhaseName("Pass");

			//System.out.println("currentPhaseStatusID = " + Long.toString(currentPhaseStatusID));
			currentPhaseDetailOfEdit.setPhasestatusid(currentPhaseStatusID);

			if (currentPhaseOrder + 1 > size) {
				Long candidateStatusID = mybean.getCandidateStatusID("Pass");

				candidateToBeViewed.setStatusid(candidateStatusID);
			} else {
				Long nextPhaseId = mybean
						.getPhaseIdByPhaseOrder(currentPhaseOrder + 1);
				candidateToBeViewed.setCurrentphaseid(nextPhaseId);
			}
		}

		else {
			
			Long currentPhaseStatusID = mybean
					.getPhaseStatusIdByPhaseName("Fail");

			
			currentPhaseDetailOfEdit.setPhasestatusid(currentPhaseStatusID);

			Long candidateStatusID = mybean.getCandidateStatusID("Archiv");

			candidateToBeViewed.setStatusid(candidateStatusID);

			 candidateToBeViewed.setReasonsoffailureid(reasonOfFailureId);
		}
		// mybean.updatePhaseOfCandidate(currentPhaseDetailOfEdit);
		
		// TODO make last modified
		currentPhaseDetailOfEdit.setLastmodifiedbyid(1);
		
		mybean.editCandidateMerge(currentPhaseDetailOfEdit, candidateToBeViewed);

		return "/View Candidate.xhtml?faces-redirect=true&candidateId="
		+ Long.toString(candidateToBeViewed.getId());
	}

	public String viewPendingCandidatesOfCertainPhase() {
		Long currentPhaseId;
		String paramValue;
		Map<String, String> params;
		params = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		paramValue = params.get("currentPhaseId");
		// System.out.println(paramValue);
		currentPhaseId = Long.valueOf(paramValue);
		currentCandidatesOfCertainPhase = mybean
				.getPendingCandidatesOfCertainPhase(currentPhaseId);
		return "phasesTable";
	}

	public String cancel() {
		return "managerDashboard";
	}
}