package pagecode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Candidate;

import ejb.CandidateStatusEJB;
import ejb.CandidatesEJB;
import ejb.HRSourcesEJB;
import ejb.PhasesEJB;
import ejb.PositionsEJB;

@ManagedBean(name = "searchcandidatespage")
@SessionScoped
public class SearchCandidatesPage extends PageCodeBase {

	private String fullName;
	private String email;
	private long number;
	private String assignedHR;
	private String positionAppliedFor;
	private String status;
	private String selectedPhase;
	private List<String> HRSources;
	private List<String> positions;
	private String responseMessage;
	private List<Candidate> searchResultCandidates;
	private List<String> statuses;
	private List<String> phases;
	private List<String> phasesStatuses;
	private String selectedCandidate;

	@EJB
	private HRSourcesEJB hrSourcesEJB;
	@EJB
	private PositionsEJB positionsEJB;
	@EJB
	private CandidatesEJB candidatesEJB;
	@EJB
	private CandidateStatusEJB candidateStatusEJB;
	@EJB
	private PhasesEJB phasesEJB;

	@PostConstruct
	void init() {
		try {
			System.out.println("Here in Post Construct");
			HRSources = hrSourcesEJB.getAllHRSources();
			positions = positionsEJB.getAllPositions();
			statuses = candidateStatusEJB.getAllStatuses();
		    phases =  phasesEJB.getAllPhases();
			setPhases(phases);
			setHRSources(HRSources);
			setPositions(positions);
			searchResultCandidates = candidatesEJB.getAllCanditates();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getNumber() {

		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getAssignedHR() {
		return assignedHR;
	}

	public void setAssignedHR(String assignedHR) {
		this.assignedHR = assignedHR;
	}

	public String getPositionAppliedFor() {
		return positionAppliedFor;
	}

	public void setPositionAppliedFor(String positionAppliedFor) {
		this.positionAppliedFor = positionAppliedFor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getHRSources() {
		return HRSources;
	}

	public void setHRSources(List<String> hRSources) {
		HRSources = hRSources;
	}

	public List<String> getPositions() {
		return positions;
	}

	public void setPositions(List<String> positions) {
		this.positions = positions;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getSelectedPhase() {
		return selectedPhase;
	}

	public void setSelectedPhase(String selectedPhase) {
		this.selectedPhase = selectedPhase;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public List<Candidate> getSearchResultCandidates() {

		return searchResultCandidates;
	}

	public void setSearchResultCandidates(List<Candidate> searchResultCandidates) {
		this.searchResultCandidates = searchResultCandidates;
	}

	public List<String> getStatuses() {
		return statuses;
	}

	public List<String> getPhasesStatuses() {
		return phasesStatuses;
	}

	public void setPhasesStatuses(List<String> phasesStatuses) {
		this.phasesStatuses = phasesStatuses;
	}

	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}

	public List<String> getPhases() {
		return phases;
	}

	public void setPhases(List<String> phases) {
		this.phases = phases;
	}

	public String getSelectedCandidate() {
		return selectedCandidate;
	}

	public void setSelectedCandidate(String selectedCandidate) {
		this.selectedCandidate = selectedCandidate;
	}

	public String searchCandidates() {
		
		String fN = getFullName().toLowerCase();
		System.out.println("The full name written" + fN);
		String eM = getEmail().toLowerCase();
		String aHR = getAssignedHR();
		String statname = getStatus();
		String pAF = getPositionAppliedFor();
		long nM = getNumber();
		String sPhase = getSelectedPhase();
		List<Candidate> search1 = new ArrayList<Candidate>();
		if (fN.equals("") && eM.equals("") && aHR.equals("None")
				&& statname.equals("None") && pAF.equals("None") && nM == 0
				&& sPhase.equals("None")) {
			setResponseMessage("No Search Criteria Selected");
			return null;
		} else {
			String searchQuery = "SELECT * FROM HRHSSCHEMA.CANDIDATES c WHERE ";
			// FullName
			if (!(fN.equals(""))) {
				String fnQ = "UPPER(c.fullname) LIKE UPPER('%" + fN + "%') AND ";
				searchQuery = searchQuery + fnQ;
			}
			// Email
			if (!(eM.equals(""))) {
				String eQ = "c.email LIKE '%" + eM + "%' AND ";
				searchQuery = searchQuery + eQ;
			}
			// HRSource
			if (!(aHR.equals("None"))) {
				long hrID = hrSourcesEJB.getHRSourceID(aHR);
				String hrQ = "c.hrsourceid = " + hrID + " AND ";
				searchQuery = searchQuery + hrQ;
			}
			// Position Applied For
			if (!(pAF.equals("None"))) {
				long pID = positionsEJB.getPositionID(pAF);
				String pQ = "c.positionsid = " + pID + " AND ";
				searchQuery = searchQuery + pQ;
			}
			// Number
			if (nM != 0) {
				String mnQ = "c.mobilenumber =" + nM + " OR ";
				String pnQ = "c.phonenumber =" + nM;
				searchQuery = searchQuery + mnQ;
				searchQuery = searchQuery + pnQ;
			}
			
			//Chosen Phase
			if(!(sPhase.equals("None")))
			{
				long phaseId = phasesEJB.getCurrentPhaseID(sPhase);
				String phaseQ = "c.currentphaseid = " + phaseId + " AND ";
				searchQuery = searchQuery + phaseQ;
			}
			// Candidate Status
			if (!(statname.equals("None"))) {
				long statid = candidateStatusEJB.getCandidateStatusID(statname);
				String statQ = "c.statusid = " + statid + " AND ";
				searchQuery = searchQuery + statQ;
			}
			
			
			int resultSize = 0;
			if (searchQuery.endsWith("AND ")) {
				String finalQuery = searchQuery.substring(0,
						searchQuery.length() - 5);
				searchResultCandidates = new ArrayList<Candidate>();
				search1 = candidatesEJB
						.searchCandidates(finalQuery);
				resultSize = searchResultCandidates.size();
				System.out.println("here ahuu " + resultSize);
			} else {
				search1 = candidatesEJB
						.searchCandidates(searchQuery);
				resultSize = searchResultCandidates.size();

				System.out.println("here ahuu " + resultSize);
			}
			if (resultSize == 0) {
				setResponseMessage("No Results Found");

			}

			}
		    for(int i = 0; i < search1.size();i++)
		    {
		    	Candidate x = candidatesEJB.getCandidatebyID(search1.get(i).getId());
		    	searchResultCandidates.add(x);
		    }
			setFullName("");
			setAssignedHR("None");
			setEmail("");
			setNumber(0);
			setPositionAppliedFor("None");
			setStatus("None");
			setSelectedPhase("None");
			return "searchcandidatespage.faces";
		}
	public String ViewCandidate() throws IOException
	{
		String candidateName = getSelectedCandidate();
		System.out.println("The Selected Candidate is : " + candidateName);
		long id = candidatesEJB.getCandidateIDByName(candidateName);
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("candidateid", id);
		FacesContext.getCurrentInstance().getExternalContext().redirect("ViewCandidate.faces");
		return null;
	}
	public String EditCandidate() throws IOException
	{
		String candidateName = getSelectedCandidate();
		long id = candidatesEJB.getCandidateIDByName(candidateName);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("candidateid", id);
		FacesContext.getCurrentInstance().getExternalContext().redirect("ViewCandidate.faces");
		
		return null;
	}
	
	
	public String UpdateCandidate() throws IOException
	{
		String candidateName = getSelectedCandidate();
		long id = candidatesEJB.getCandidateIDByName(candidateName);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("candidateid", id);
		FacesContext.getCurrentInstance().getExternalContext().redirect("ViewCandidate.faces");
		return null;
	}
}

