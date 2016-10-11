package pagecode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import model.Candidate;
import model.HRSource;
import model.Phas;
import model.Position;

import ejb.CandidatesEJB;
import ejb.HRSourcesEJB;
import ejb.PhasesEJB;
import ejb.PositionsEJB;
import ejb.ReportsEJB;


@SuppressWarnings("serial")
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
	
	
	private Date startDate;
	private Date endDate;
	
	
	//init() method is executed once before the page is loaded
	@SuppressWarnings("deprecation")
	@PostConstruct
	public void init() {
	    setPositionsDropdown(getPositionsString());
	    setPhasesDropdown(getPhasesString());
		setEmployeesDropdown(getHrSoures());
		
		
		
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
	    Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, thisYear);
		cal.set(Calendar.DAY_OF_YEAR, 1);    
		Date start = cal.getTime();

		//set date to last day of thisYear
		cal.set(Calendar.YEAR, thisYear);
		cal.set(Calendar.MONTH, 11); // 11 = december
		cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve

		Date end = cal.getTime();
		
		setStartDate(start);
		setEndDate(end);
		
		
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
		List<HRSource> hrsources = new ArrayList<HRSource>();
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
		List<HRSource> hrSources = hrsourcesEJB.getHrSources();
		for(int i=0;i<hrSources.size();i++){
			if(hrSources.get(i).getName().equals(HrName)){
				return (int)hrSources.get(i).getId();
			}
		}
		return 0;
	}
	
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
	
	//Getters and Setters for all the attributes
	public String getChosenPosition() {
		return chosenPosition;
	}

	public void setChosenPosition(String chosenPosition) {
		ManagerDashboard.chosenPosition = chosenPosition;
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
		ManagerDashboard.chosenPhase = chosenPhase;
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


	
}
