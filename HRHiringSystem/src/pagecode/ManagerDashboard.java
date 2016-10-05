package pagecode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import model.Candidate;
import model.Hrsource;
import model.Phas;
import model.Position;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

import dto.ReportsDTO;
import ejb.CandidatesEJB;
import ejb.HRSourcesEJB;
import ejb.PhasesEJB;
import ejb.PositionsEJB;
import ejb.ReportsEJB;


@ManagedBean(name = "managerdashboard")
@SessionScoped
public class ManagerDashboard extends PageCodeBase implements Serializable{
	
	@EJB
	private CandidatesEJB candidatesEJB;
	
	@EJB
	private PositionsEJB positionsEJB;
	
	@EJB
	private PhasesEJB phasesEJB;
	
	@EJB
	private HRSourcesEJB hrsourcesEJB; 
	
	@EJB
	private ReportsEJB reportsEJB;
	
	//Drop down positions
	private List<String> positionsDropdown;
	static private String chosenPosition;
	
	//Drop down phases
	private List<String> phasesDropdown;
	static private String chosenPhase;
	
	//Drop down HR Employees
	private List<String> employeesDropdown;
	private String chosenEmployee;
	
	private JSONObject json;
	
	private Date startDate;
	private Date endDate;
	
	
	//init() method is executed once before the page is loaded
	@SuppressWarnings("deprecation")
	@PostConstruct
	public void init() {
	    setPositionsDropdown(getPositionsString());
	    setPhasesDropdown(getPhasesString());
//		testNewGetCand();
		setEmployeesDropdown(getHrSoures());
		Date date = new Date();
//	    int thisYear = date.getYear();
		Date Q1A = new Date("January, 1 2016");
		Date Q4B = new Date("December, 31 2016");
		setStartDate(Q1A);
		setEndDate(Q4B);
	}	
	
	//Get All positions from the database table
	public List<Position> getPositions(){
		List<Position> positions = new ArrayList<Position>();
		positions = positionsEJB.getPositions();
		return positions;
	}
	
	//Same as getPositions() but instead return list of strings
	public List<String> getPositionsString(){
		List<Position> positions = getPositions();
		List<String> posResult = new ArrayList<String>();
		posResult.add(0,"None");
		for(int i=0;i<positions.size();i++){
			posResult.add(positions.get(i).getName());
		}
		return posResult;
	}
	
	//Get All HR sources in a list of strings
	public List<String> getHrSoures(){
		List<Hrsource> hrsources = new ArrayList<Hrsource>();
		List<String> hrsourcesString = new ArrayList<String>();
		hrsources = hrsourcesEJB.getHrSources();
		hrsourcesString.add(0,"None");
		for(int i=0;i<hrsources.size();i++){
			hrsourcesString.add(hrsources.get(i).getName());
		}
		return hrsourcesString;
	}
	
	//Setting HRSource according to dropdown value
	public void setHrSourceDropdownValue(ValueChangeEvent e){
		setChosenEmployee(e.getNewValue().toString());
	}
	
	//Get The Hr Employee ID given the Name
	public int getHrEmployeeIdByName(String HrName){
		List<Hrsource> hrSources = hrsourcesEJB.getHrSources();
		for(int i=0;i<hrSources.size();i++){
			if(hrSources.get(i).getName().equals(HrName)){
				return (int)hrSources.get(i).getId();
			}
		}
		return 0;
	}
	
	//Submit Employee Start and End Date
//	public String employeeAndDateSubmit(){
//		String chosenEmployee = getChosenEmployee();
//		int chosenEmployeeID = getHrEmployeeIdByName(chosenEmployee);
//		Date startDate = getStartDate();
//		Date endDate = getEndDate();
//		
//		HashMap<Integer, ReportsDTO> phases = reportsEJB.getReportInformationForFailAnalysis(chosenEmployeeID, startDate, endDate);
//		
//		JSONArray array = new JSONArray();
//		
//		Iterator it = phases.entrySet().iterator();
//		
//	    while (it.hasNext()) {
//	    	JSONObject jsonObject = new JSONObject();
//	    	Map.Entry pair = (Map.Entry)it.next();
//	    	
//	    	jsonObject.put("key", pair.getKey());
//	    	
//	    	ReportsDTO tempReportsDTO = (ReportsDTO) pair.getValue();
//	    	
//	    	jsonObject.put("id", tempReportsDTO.getId());
//	    	jsonObject.put("name", tempReportsDTO.getName());
//	    	jsonObject.put("count", tempReportsDTO.getCount());
//	    	
//	    	
//	    	array.add(jsonObject);
//	    	
//	        System.out.println(pair.getKey() + " = " + pair.getValue());
//	    }
//	    
//	    
//	    System.out.println(" =========== result: "+array.toString() + "================");
//		
//		
//		System.out.println( "The size of phases list: " + phases.size());
//		
////		System.out.println("chosen employee ID: " + chosenEmployeeID);
////		System.out.println("startDate: " + startDate);
////		System.out.println("endDate: " + endDate);
//
//		
////		JSONObject json = new JSONObject();
////		json.put("fail", phases);
////		setJson(json);
//		return null;
//	}
	
	//Get All phases from the database table
	public List<Phas> getPhases(){
		List<Phas> phases = new ArrayList<Phas>();
		phases = phasesEJB.getPhases();
		return phases;
	}
	
	//Same as getPositions() but instead return list of strings
	public List<String> getPhasesString(){
		List<Phas> phases = getPhases();
		List<String> phasResult = new ArrayList<String>();
		phasResult.add(0,"None");
		for(int i=0;i<phases.size();i++){
			phasResult.add(phases.get(i).getName());
		}
		return phasResult;
	}
	
	//Get All candidates from the database table
	public List<Candidate> getCandidates(){
		List<Candidate> candidates = new ArrayList<Candidate>();
		candidates = candidatesEJB.getCanditates();
		return candidates;
	}
	
	//Get Candidates with specified HrSource and Between certain dates
	public List<Candidate> getCandidatesModified(int hrSourceid, Date startDate, Date endDate){
	List<Candidate> candidates = new ArrayList<Candidate>();
	candidates = candidatesEJB.getCandidatesModified(hrSourceid,startDate,endDate);
	return candidates;
	}

	//Setting the position according to the dropdown value 
	public void setPositionCompoundGraph(ValueChangeEvent e){
		setChosenPosition(e.getNewValue().toString());
	}
	
	//Setting the phase according to the dropdown value
	public void setPhaseCompoundGraph(ValueChangeEvent e){
		setChosenPhase(e.getNewValue().toString());
	}
	
	public int getPhaseIdByName(String phase){
		int phaseID = 0;
		List<Phas> phases = getPhases();
		for(int i=0;i<phases.size();i++){
			if(phases.get(i).getName().equals(phase)){
				phaseID = (int)phases.get(i).getId();
				break;
			}
		}
		return phaseID;
	}
	
	public int getPositionIdByName(String position){
		int positionID = 0;
		List<Position> positions = getPositions();
		for(int i=0;i<positions.size();i++){
		if(positions.get(i).getName().equals(position)){
			positionID = (int)positions.get(i).getId();
			break;
		}
	}
		return positionID;
	}
	
	
	
	//NEEDS MODIFICATIONS
//	public void reasonsOfFailure(String position, String phase){
//		List<Candidate> candidates = getCandidates();
//		int positionID = getPositionIdByName(position);
//		int phaseID = getPhaseIdByName(phase);
//		int reasonOfFailureID;
//		int sourceID;
//		if((position == null || position.equals("None")) && (phase == null || phase.equals("None"))){
//		}
//		else if(position == null || position.equals("None")){
//			int phasedetailsSize;
//			for(int i=0;i<candidates.size();i++){
//				if(candidates.get(i).getStatus().equals("Failed")){
//					phasedetailsSize = candidates.get(i).getPhasesDetails().size();
//					for(int j=0;j<phasedetailsSize;j++){
//						int conditionphaseID = (int)candidates.get(i).getPhasesDetails().get(j).getPhas().getId();
//						if(conditionphaseID == phaseID){
//							reasonOfFailureID = (int)candidates.get(i).getReasonsoffailureid();
//							incrementROFbyId(reasonOfFailureID);
//							sourceID = (int)candidates.get(i).getSourceid();
//							incrementSourcebyId(sourceID);
//						}
//					}
//				}
//			}
//			
//		}
//		else if(phase == null || phase.equals("None")){
//			for(int i=0;i<candidates.size();i++){
//				if(candidates.get(i).getStatus().equals("Failed")){
//					int conditionPositionId = (int)candidates.get(i).getPositionsid();
//					if(conditionPositionId == positionID){
//						reasonOfFailureID = (int)candidates.get(i).getReasonsoffailureid();
//						incrementROFbyId(reasonOfFailureID);
//						sourceID = (int)candidates.get(i).getSourceid();
//						incrementSourcebyId(sourceID);
//					}
//				}
//			}
//		}
//		else{
//			int phasedetailsSize;
//			for(int i=0;i<candidates.size();i++){
//				if(candidates.get(i).getStatus().equals("Failed")){
//					phasedetailsSize = candidates.get(i).getPhasesDetails().size();
//					for(int j=0;j<phasedetailsSize;j++){
//						int conditionphaseID = (int)candidates.get(i).getPhasesDetails().get(j).getPhas().getId();
//						if(conditionphaseID == phaseID){
//							reasonOfFailureID = (int)candidates.get(i).getReasonsoffailureid();
//							incrementROFbyId(reasonOfFailureID);
//							sourceID = (int)candidates.get(i).getSourceid();
//							incrementSourcebyId(sourceID);
//						}
//					}
//				}
//			}
//			for(int i=0;i<candidates.size();i++){
//				if(candidates.get(i).getStatus().equals("Failed")){
//					int conditionPositionId = (int)candidates.get(i).getPositionsid();
//					if(conditionPositionId == positionID){
//						reasonOfFailureID = (int)candidates.get(i).getReasonsoffailureid();
//						incrementROFbyId(reasonOfFailureID);
//						sourceID = (int)candidates.get(i).getSourceid();
//						incrementSourcebyId(sourceID);
//					}
//				}
//			}
//			
//		}
//	}
	
	
	
	//Getters and Setters for all the attributes
	public String getChosenPosition() {
		return chosenPosition;
	}

	public void setChosenPosition(String chosenPosition) {
		this.chosenPosition = chosenPosition;
	}

	public List<String> getPhasesDropdown() {
		return phasesDropdown;
	}

	public void setPhasesDropdown(List<String> phasesDropdown) {
		this.phasesDropdown = phasesDropdown;
	}

	public String getChosenPhase() {
		return chosenPhase;
	}

	public void setChosenPhase(String chosenPhase) {
		this.chosenPhase = chosenPhase;
	}

	public List<String> getPositionsDropdown() {
		return positionsDropdown;
	}

	public void setPositionsDropdown(List<String> positionsDropdown) {
		this.positionsDropdown = positionsDropdown;
	}

	

	public List<String> getEmployeesDropdown() {
		return employeesDropdown;
	}

	public void setEmployeesDropdown(List<String> employeesDropdown) {
		this.employeesDropdown = employeesDropdown;
	}

	public String getChosenEmployee() {
		return chosenEmployee;
	}

	public void setChosenEmployee(String chosenEmployee) {
		this.chosenEmployee = chosenEmployee;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}


	
}
