package pagecode;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;
import javax.ejb.*;

import model.Candidate;

import ejb.CandidatesEJB;


@ManagedBean(name = "managerdashboard")
@RequestScoped
public class ManagerDashboard extends PageCodeBase{
	
	@EJB
	private CandidatesEJB candidateEJB;
	
	private int cvFail;
	private int phoneFail;
	private int examsFail;
	private int hrFail;
	private int technicalFail;
	private int presentationFail;
	private int totalFail;
	
	private int cvPending;
	private int phonePending;
	private int examsPending;
	private int hrPending;
	private int technicalPending;
	private int presentationPending;
	
	//init() method is executed once before the page is loaded
	@PostConstruct
	public void init() {
//	    failAnalysis();
//	    totalPhasesFail();
//	    pendingCandidates();
	}
	
	//Get All candidates from the database table
	public List<Candidate> getCandidates(){
		List<Candidate> candidates = new ArrayList<Candidate>();
		candidates = candidateEJB.getAllCanditates();
		return candidates;
	}
	
	//Graph(1) Fail Analysis per phases
	public void failAnalysis(){
		List<Candidate> candidates = getCandidates();
		//phaseID 1:CV 2:Phone 3:Exams 4:HR 5:Technical 6:Presentation  
		int phaseID;
		for(int i=0;i<candidates.size();i++){
			if(candidates.get(i).getPhasesDetails().get(0).getPhaseStatus().getName().equals("Failed")){
				
				phaseID = (int)candidates.get(i).getPhasesDetails().get(0).getPhase().getId();
				
				int x;
				switch(phaseID){
					case 1: x = getCvFail() + 1;
					setCvFail(x);
					break;
					case 2: x = getPhoneFail() + 1;
					setPhoneFail(x);
					break;
					case 3: x = getExamsFail() + 1;
					setExamsFail(x);
					break;
					case 4: x = getHrFail() + 1;
					setHrFail(x);
					break;
					case 5: x = getTechnicalFail() + 1;
					setTechnicalFail(x);
					break;
					case 6: x = getPresentationFail() + 1;
					setPresentationFail(x);
					break;
				}
			}
		}
	}
	
	//Calculating total Fail per phases
	public void totalPhasesFail(){
		int x = getCvFail() + getPhoneFail() + getExamsFail() + getHrFail() + getTechnicalFail() + getPresentationFail();
		setTotalFail(x);
	}
	
	//Graph(2) Pending Candidates per phases
	public void pendingCandidates(){
		List<Candidate> candidates = getCandidates();
		//phaseID 1:CV 2:Phone 3:Exams 4:HR 5:Technical 6:Presentation  
		int phaseID;
		for(int i=0;i<candidates.size();i++){
			if(candidates.get(i).getPhasesDetails().get(0).getPhaseStatus().getName().equals("Pending")){
				
				phaseID = (int)candidates.get(i).getPhasesDetails().get(0).getPhase().getId();
				
				int x;
				switch(phaseID){
					case 1: x = getCvPending() + 1;
					setCvPending(x);
					break;
					case 2: x = getPhonePending() + 1;
					setPhonePending(x);
					break;
					case 3: x = getExamsPending() + 1;
					setExamsPending(x);
					break;
					case 4: x = getHrPending() + 1;
					setHrPending(x);
					break;
					case 5: x = getTechnicalPending() + 1;
					setTechnicalPending(x);
					break;
					case 6: x = getPresentationPending() + 1;
					setPresentationPending(x);
					break;
				
				}
			}
		}
	}
	
	
	//Getters and Setters for all the attributes
	public int getCvFail() {
		return cvFail;
	}
	
	public int getTotalFail() {
		return totalFail;
	}

	public void setTotalFail(int totalFail) {
		this.totalFail = totalFail;
	}

	public void setCvFail(int cvFail) {
		this.cvFail = cvFail;
	}

	public int getPhoneFail() {
		return phoneFail;
	}

	public void setPhoneFail(int phoneFail) {
		this.phoneFail = phoneFail;
	}

	public int getExamsFail() {
		return examsFail;
	}

	public void setExamsFail(int examsFail) {
		this.examsFail = examsFail;
	}

	public int getHrFail() {
		return hrFail;
	}

	public void setHrFail(int hrFail) {
		this.hrFail = hrFail;
	}

	public int getTechnicalFail() {
		return technicalFail;
	}

	public void setTechnicalFail(int technicalFail) {
		this.technicalFail = technicalFail;
	}

	public int getPresentationFail() {
		return presentationFail;
	}

	public void setPresentationFail(int presentationFail) {
		this.presentationFail = presentationFail;
	}

	public int getCvPending() {
		return cvPending;
	}

	public void setCvPending(int cvPending) {
		this.cvPending = cvPending;
	}

	public int getPhonePending() {
		return phonePending;
	}

	public void setPhonePending(int phonePending) {
		this.phonePending = phonePending;
	}

	public int getExamsPending() {
		return examsPending;
	}

	public void setExamsPending(int examsPending) {
		this.examsPending = examsPending;
	}

	public int getHrPending() {
		return hrPending;
	}

	public void setHrPending(int hrPending) {
		this.hrPending = hrPending;
	}

	public int getTechnicalPending() {
		return technicalPending;
	}

	public void setTechnicalPending(int technicalPending) {
		this.technicalPending = technicalPending;
	}

	public int getPresentationPending() {
		return presentationPending;
	}

	public void setPresentationPending(int presentationPending) {
		this.presentationPending = presentationPending;
	}
	
}
