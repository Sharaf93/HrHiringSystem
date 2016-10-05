package ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;


import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Candidate;
import model.Phas;
import model.Phasesdetail;
import model.Position;

import dto.ReportsDTO;
import constants.ReportsConstants;

//Ask whether Statefull or Stateless 
@Stateful
@LocalBean
public class ReportsEJB {
	
//	Entity Manager
	@PersistenceContext private EntityManager em;
	
	public ReportsEJB(){
	}
	
	@EJB
	private CandidatesEJB candidatesEJB;
	
	@EJB
	private PhasesEJB phasesEJB;
	
	@EJB
	private PositionsEJB positionsEJB;
	
	//Get All candidates from the database table
	public List<Candidate> getCandidates(){
		List<Candidate> candidates = new ArrayList<Candidate>();
		candidates = candidatesEJB.getCanditates();
		return candidates;
	}
	
	//Get All positions from the database table
	public List<Position> getPositions(){
		List<Position> positions = new ArrayList<Position>();
		positions = positionsEJB.getPositions();
		return positions;
	}
	
	//Get All phases from the database table
	public List<Phas> getPhases(){
		List<Phas> phases = new ArrayList<Phas>();
		phases = phasesEJB.getPhases();
		return phases;
	}
	
	//Get Candidates with specified HrSource and Between certain dates
	public List<Candidate> getCandidatesModified(int hrSourceid, Date start, Date end){
		List<Candidate> candidates = new ArrayList<Candidate>();
		candidates = candidatesEJB.getCandidatesModified(hrSourceid, start, end);
		return candidates;
	}
	
	//Get Candidates with specified HrSource, position, phase, and Between certain dates
	public List<Candidate> getCandidatesPositionPhase(int positionid, int phaseid, int hrsourceid, 
			Date startDate, Date endDate ){
		List<Candidate> candidates = new ArrayList<Candidate>();
		candidates = candidatesEJB.getCandidatesPositionPhase(positionid,phaseid,hrsourceid,startDate,endDate);
		return candidates;
	}
	
	//Get Position ID by Name
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
	
	//Get Phase ID by Name
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
	
	//Graph(1) Fail Analysis per phases
	public HashMap<Integer, ReportsDTO> getReportInformationForFailAnalysis(int hrsourceid, Date startDate, Date endDate){
		List<Candidate> candidates = getCandidatesModified(hrsourceid,startDate,endDate);
		HashMap<Integer, ReportsDTO> phases = new HashMap<Integer, ReportsDTO>();
		
		//phaseID 1:CV 2:Phone 3:Exams 4:HR 5:Technical 6:Presentation  
		int phaseID;
		
		for(int i=0;i<candidates.size();i++){
			List<Phasesdetail> phaseDetails = candidates.get(i).getPhasesDetails();
			for(int j=0;j<phaseDetails.size();j++){
				if(phaseDetails.get(j).getPhaseStatus().getId() == ReportsConstants.PHASE_FAILED_ID){
					phaseID = (int)phaseDetails.get(j).getPhas().getId();
					if(phases.containsKey(phaseID)){
						ReportsDTO phaseDTO = phases.get(phaseID);
						phaseDTO.setCount(phaseDTO.getCount()+1);
					}
					else{
						String phaseName = phaseDetails.get(j).getPhas().getName();
						ReportsDTO phaseDTO = new ReportsDTO(phaseID, phaseName, 1);
						phases.put(phaseID, phaseDTO);
					}
				}
			}
		}
		return phases;
	}
	
	//Graph(2) Pending Candidates per phases
	public HashMap<Integer, ReportsDTO> getReportInformationForPendingCandidates(int hrsourceid, Date startDate, Date endDate){
		List<Candidate> candidates = getCandidatesModified(hrsourceid,startDate,endDate);
		HashMap<Integer, ReportsDTO> phases = new HashMap<Integer, ReportsDTO>();
		
		//phaseID 1:CV 2:Phone 3:Exams 4:HR 5:Technical 6:Presentation  
		int phaseID;
		
		for(int i=0;i<candidates.size();i++){
			List<Phasesdetail> phaseDetails = candidates.get(i).getPhasesDetails();
			for(int j=0;j<phaseDetails.size();j++){
				if(phaseDetails.get(j).getPhaseStatus().getId() == ReportsConstants.PHASE_PENDING_ID){
					phaseID = (int)phaseDetails.get(j).getPhas().getId();
					if(phases.containsKey(phaseID)){
						ReportsDTO phaseDTO = phases.get(phaseID);
						phaseDTO.setCount(phaseDTO.getCount()+1);
					}
					else{
						String phaseName = phaseDetails.get(j).getPhas().getName();
						ReportsDTO phaseDTO = new ReportsDTO(phaseID, phaseName, 1);
						phases.put(phaseID, phaseDTO);
					}
				}
			}
		}
		return phases;
	}
	
	//Graph(3) Pass Analysis per positions
	public HashMap<Integer, ReportsDTO> getReportInformationForPassAnalysis(int hrsourceid, Date startDate, Date endDate){
		List<Candidate> candidates = getCandidatesModified(hrsourceid,startDate,endDate);
		HashMap<Integer, ReportsDTO> positions = new HashMap<Integer, ReportsDTO>();

		//positionID 1:SoftwareEngineer 2:SoftwareSupportEngineer 3:StaffSoftwareEngineer
		//4:StaffSoftwareSupportEngineer 5:SeniorStaffSoftwareEngineer 6:BusinessAnalyst
		//7:AssociateProjectManager 8:PresalesEngineer 9:ProjectManager 10:HRAssociate
		//11:AssociateSoftwareTestEngineer 12:HRExecutive 13:SoftwareTestEngineer
		//14:HRSpecialist 15:AssociateSoftwareSupportEngineer 16:MarketingExecutive
		//17:MarketingSpecialist 18:SalesAccountantManager 19:JuniorAccountant
		//20:SeniorAccontant 21:ITAdmin 22:SeniorITAdmin 23:SystemsEngineer 24:HRIntern 25:SoftwareIntern
		int positionID;
		
		for(int i=0;i<candidates.size();i++){
			
			//SHOULD BE: if(candidates.get(i).getStatusId == ReportsConstants.CANDIDATE_STATUS_PASSED_ID)
			if(candidates.get(i).getStatus().equals("Passed")){
				
				positionID = (int)candidates.get(i).getPositionsid();
				
				if(positions.containsKey(positionID)){
					ReportsDTO positionDTO = positions.get(positionID);
					positionDTO.setCount(positionDTO.getCount()+1);
				}
				else{
					String positionName = candidates.get(i).getPosition().getName();
					ReportsDTO positionDTO = new ReportsDTO(positionID, positionName, 1);
					positions.put(positionID, positionDTO);
				}
			}
		}
		return positions;
	}
	
	//Graph(4)(A) Sources for Passed candidates
	public HashMap<Integer, ReportsDTO> getReportInformationForSourcesPass(int hrsourceid, Date startDate, Date endDate){
		List<Candidate> candidates = getCandidatesModified(hrsourceid,startDate,endDate);
		HashMap<Integer, ReportsDTO> sources = new HashMap<Integer, ReportsDTO>();
		
		//sourceID 1:Facebook 2:Wuzzuf 3:Referral 4:Linkedin 5:GUC 6:MSA
		//7:Scad 8:CompanyWebsite 9:AmericanChamber  
		int sourceID;
		
		for(int i=0;i<candidates.size();i++){
			
			//SHOULD BE: if(candidates.get(i).getStatusId == ReportsConstants.CANDIDATE_STATUS_PASSED_ID)
			if(candidates.get(i).getStatus().equals("Passed")){
				
				sourceID = (int)candidates.get(i).getSourceid();
				
				if(sources.containsKey(sourceID)){
					ReportsDTO sourceDTO = sources.get(sourceID);
					sourceDTO.setCount(sourceDTO.getCount()+1);
				}
				else{
					String sourceName = candidates.get(i).getSource().getName();
					ReportsDTO sourceDTO = new ReportsDTO(sourceID, sourceName, 1);
					sources.put(sourceID, sourceDTO);
				}
				
			}
		}
		return sources;
	}
	
	//Graph(4)(B) Sources for Failed candidates
	public HashMap<Integer, ReportsDTO> getReportInformationForSourcesFail(int hrsourceid, Date startDate, Date endDate){
		List<Candidate> candidates = getCandidatesModified(hrsourceid,startDate,endDate);
		HashMap<Integer, ReportsDTO> sources = new HashMap<Integer, ReportsDTO>();
		
		//sourceID 1:Facebook 2:Wuzzuf 3:Referral 4:Linkedin 5:GUC 6:MSA
		//7:Scad 8:CompanyWebsite 9:AmericanChamber  
		int sourceID;
		
		for(int i=0;i<candidates.size();i++){
			
			//SHOULD BE: if(candidates.get(i).getStatusId == ReportsConstants.CANDIDATE_STATUS_FAILED_ID)
			if(candidates.get(i).getStatus().equals("Failed")){
				
				sourceID = (int)candidates.get(i).getSourceid();
				
				if(sources.containsKey(sourceID)){
					ReportsDTO sourceDTO = sources.get(sourceID);
					sourceDTO.setCount(sourceDTO.getCount()+1);
				}
				else{
					String sourceName = candidates.get(i).getSource().getName();
					ReportsDTO sourceDTO = new ReportsDTO(sourceID, sourceName, 1);
					sources.put(sourceID, sourceDTO);
				}
				
			}
		}
		return sources;
	}
	
	//Graph(5)(A) Reasons Of Failure per positions and phases
	public HashMap<Integer, ReportsDTO> getReportInformationForReasonsOfFailure(String position, String phase, int hrsourceid,
			Date startDate, Date endDate){
		
		int positionID = getPositionIdByName(position);
		int phaseID = getPhaseIdByName(phase);
		List<Candidate> candidates = getCandidatesPositionPhase(positionID,phaseID,hrsourceid,startDate,endDate);
		HashMap<Integer, ReportsDTO> reasonsOfFailure = new HashMap<Integer, ReportsDTO>();
		int reasonsOfFailureID;
		
		for(int i=0;i<candidates.size();i++){
			
			reasonsOfFailureID = (int)candidates.get(i).getReasonsoffailureid();
			
			if(reasonsOfFailure.containsKey(reasonsOfFailureID)){
				ReportsDTO reasonsOfFailureDTO = reasonsOfFailure.get(reasonsOfFailureID);
				reasonsOfFailureDTO.setCount(reasonsOfFailureDTO.getCount()+1);
			}
			else{
				String reasonOfFailureName = candidates.get(i).getReasonsOfFailure().getName();
				ReportsDTO reasonsOfFailureDTO = new ReportsDTO(reasonsOfFailureID, reasonOfFailureName, 1);
				reasonsOfFailure.put(reasonsOfFailureID, reasonsOfFailureDTO);
			}
		}
		
		return reasonsOfFailure;
	}
	
	//Graph(5)(B) Sources per positions and phases
	public HashMap<Integer, ReportsDTO> getReportInformationForSources(String position, String phase, int hrsourceid,
			Date startDate, Date endDate){

		int positionID = getPositionIdByName(position);
		int phaseID = getPhaseIdByName(phase);
		List<Candidate> candidates = getCandidatesPositionPhase(positionID,phaseID,hrsourceid,startDate,endDate);
		HashMap<Integer, ReportsDTO> sources = new HashMap<Integer, ReportsDTO>();
		int sourcesID;
		
		for(int i=0;i<candidates.size();i++){
			
			sourcesID = (int)candidates.get(i).getSourceid();
			
			if(sources.containsKey(sourcesID)){
				ReportsDTO sourcesDTO = sources.get(sourcesID);
				sourcesDTO.setCount(sourcesDTO.getCount()+1);
			}
			else{
				String sourcesName = candidates.get(i).getSource().getName();
				ReportsDTO sourcesDTO = new ReportsDTO(sourcesID, sourcesName, 1);
				sources.put(sourcesID, sourcesDTO);
			}
		}
		
		return sources;
	}
	
	
	

}
