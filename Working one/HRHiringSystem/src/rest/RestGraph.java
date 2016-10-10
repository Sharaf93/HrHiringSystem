package rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import model.HRSource;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

import dto.ReportsDTO;

import ejb.CandidatesEJB;
import ejb.HRSourcesEJB;
import ejb.ReportsEJB;

@Path("/graph")
public class RestGraph {
	
	@EJB
	private CandidatesEJB candidatesEJB;
	
	@EJB
	private HRSourcesEJB hrsourcesEJB; 
	
	@EJB
	private ReportsEJB reportsEJB;
	
	
	@GET
	@Path("/Test/{hrId}/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generate(@PathParam("hrId") String hrId, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws ParseException {
		
		int chosenEmployeeID = getHrEmployeeIdByName(hrId);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date start = format.parse(startDate);
		Date end = format.parse(endDate);
		
		System.out.println("StartDate: " +start);
		System.out.println("EndDate: " +end);
		
		JSONArray array = new JSONArray();
		array.add(getJsonFailAnalysis(chosenEmployeeID,start,end));//0
		array.add(getJsonPendingAnalysis(chosenEmployeeID,start,end));//1
		array.add(getJsonPassAnalysis(chosenEmployeeID,start,end));//2
		array.add(getJsonSourcesPass(chosenEmployeeID,start,end));//3
		
		
		//Needs editing in the managerDashboard 
//		array.add(getJsonReasonsOfFailure(chosenEmployeeID,start,end,position,phase));//4
//		array.add(getJsonSources(chosenEmployeeID,start,end,position,phase));//5
		
	
		return Response.status(200).entity(array).build();
		
	}
	
	@GET
	@Path("/PositionPhase/{hrId}/{startDate}/{endDate}/{position}/{phase}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generatePositionPhase(@PathParam("hrId") String hrId, 
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
			@PathParam("position") String position, @PathParam("phase") String phase) throws ParseException {
		
		int chosenEmployeeID = getHrEmployeeIdByName(hrId);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date start = format.parse(startDate);
		Date end = format.parse(endDate);
		
		JSONArray array = new JSONArray();
		array.add(getJsonReasonsOfFailure(chosenEmployeeID,start,end,position,phase));
		array.add(getJsonSources(chosenEmployeeID,start,end,position,phase));
		
		return Response.status(200).entity(array).build();
		
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
	
	//Get JsonArray of ReportInformationForFailAnalysis
	public JSONArray getJsonFailAnalysis(int hrID, Date startDate, Date endDate){
		
		HashMap<Integer, ReportsDTO> phases = reportsEJB.getReportInformationForFailAnalysis(hrID, startDate, endDate);
		
		JSONArray array = new JSONArray();
		Iterator<Entry<Integer, ReportsDTO>> it = phases.entrySet().iterator();
		
		 while (it.hasNext()) {
	    	JSONObject jsonObject = new JSONObject();
	    	Map.Entry pair = (Map.Entry)it.next();
	    	
	    	jsonObject.put("key", pair.getKey());
	    	
	    	ReportsDTO tempReportsDTO = (ReportsDTO) pair.getValue();
	    	
	    	jsonObject.put("id", tempReportsDTO.getId());
	    	jsonObject.put("name", tempReportsDTO.getName());
	    	jsonObject.put("count", tempReportsDTO.getCount());
	    	
	    	
	    	array.add(jsonObject);
	    	
	//	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
		
		 System.out.println(" =========== result: "+array.toString() + "================");
		 
		return array;
		
	}
	
	//Get JsonArray of ReportInformationForPendingAnalysis
	public JSONArray getJsonPendingAnalysis(int hrID, Date startDate, Date endDate){
		HashMap<Integer, ReportsDTO> phases = reportsEJB.getReportInformationForPendingCandidates(hrID, startDate, endDate);
		
		JSONArray array = new JSONArray();
		Iterator<Entry<Integer, ReportsDTO>> it = phases.entrySet().iterator();
		
		 while (it.hasNext()) {
	    	JSONObject jsonObject = new JSONObject();
	    	Map.Entry pair = (Map.Entry)it.next();
	    	
	    	jsonObject.put("key", pair.getKey());
	    	
	    	ReportsDTO tempReportsDTO = (ReportsDTO) pair.getValue();
	    	
	    	jsonObject.put("id", tempReportsDTO.getId());
	    	jsonObject.put("name", tempReportsDTO.getName());
	    	jsonObject.put("count", tempReportsDTO.getCount());
	    	
	    	
	    	array.add(jsonObject);
	    }
		
		 System.out.println(" =========== result: "+array.toString() + "================");
		 
		return array;
		
	}
	
	//Get JsonArray of ReportInformationForPassAnalysis
	public JSONArray getJsonPassAnalysis(int hrID, Date startDate, Date endDate){
		HashMap<Integer, ReportsDTO> positions = reportsEJB.getReportInformationForPassAnalysis(hrID, startDate, endDate);
		
		JSONArray array = new JSONArray();
		Iterator<Entry<Integer, ReportsDTO>> it = positions.entrySet().iterator();
		
		 while (it.hasNext()) {
	    	JSONObject jsonObject = new JSONObject();
	    	Map.Entry pair = (Map.Entry)it.next();
	    	
	    	jsonObject.put("key", pair.getKey());
	    	
	    	ReportsDTO tempReportsDTO = (ReportsDTO) pair.getValue();
	    	
	    	jsonObject.put("id", tempReportsDTO.getId());
	    	jsonObject.put("name", tempReportsDTO.getName());
	    	jsonObject.put("count", tempReportsDTO.getCount());
	    	
	    	
	    	array.add(jsonObject);
	    }
		
		 System.out.println(" =========== result: "+array.toString() + "================");
		 
		return array;
		
	}
		
	//Get JsonArray of ReportInformationForSourcesPass
	public JSONArray getJsonSourcesPass(int hrID, Date startDate, Date endDate){
		HashMap<Integer, ReportsDTO> sources = reportsEJB.getReportInformationForSourcesPass(hrID, startDate, endDate);
		
		JSONArray array = new JSONArray();
		Iterator<Entry<Integer, ReportsDTO>> it = sources.entrySet().iterator();
		
		 while (it.hasNext()) {
	    	JSONObject jsonObject = new JSONObject();
	    	Map.Entry pair = (Map.Entry)it.next();
	    	
	    	jsonObject.put("key", pair.getKey());
	    	
	    	ReportsDTO tempReportsDTO = (ReportsDTO) pair.getValue();
	    	
	    	jsonObject.put("id", tempReportsDTO.getId());
	    	jsonObject.put("name", tempReportsDTO.getName());
	    	jsonObject.put("count", tempReportsDTO.getCount());
	    	
	    	
	    	array.add(jsonObject);
	    }
		
		 System.out.println(" =========== result: "+array.toString() + "================");
		 
		return array;	
	}
	
	//Get JsonArray of ReportInformationForReasonsOfFailure
	public JSONArray getJsonReasonsOfFailure(int hrID, Date startDate, Date endDate, 
			String position, String phase){
		
		HashMap<Integer, ReportsDTO> reasonsOfFailure = reportsEJB.getReportInformationForReasonsOfFailure(position, phase, hrID, startDate, endDate);
		
		JSONArray array = new JSONArray();
		Iterator<Entry<Integer, ReportsDTO>> it = reasonsOfFailure.entrySet().iterator();
		
		 while (it.hasNext()) {
	    	JSONObject jsonObject = new JSONObject();
	    	Map.Entry pair = (Map.Entry)it.next();
	    	
	    	jsonObject.put("key", pair.getKey());
	    	
	    	ReportsDTO tempReportsDTO = (ReportsDTO) pair.getValue();
	    	
	    	jsonObject.put("id", tempReportsDTO.getId());
	    	jsonObject.put("name", tempReportsDTO.getName());
	    	jsonObject.put("count", tempReportsDTO.getCount());
	    	
	    	
	    	array.add(jsonObject);
	    }
		
		 System.out.println(" =========== result: "+array.toString() + "================");
		 
		return array;	
	}
	
	//Get JsonArray of ReportInformationForSources
	public JSONArray getJsonSources(int hrID, Date startDate, Date endDate, 
			String position, String phase){
		
		HashMap<Integer, ReportsDTO> sources = reportsEJB.getReportInformationForSources(position, phase, hrID, startDate, endDate);
		
		JSONArray array = new JSONArray();
		Iterator<Entry<Integer, ReportsDTO>> it = sources.entrySet().iterator();
		
		 while (it.hasNext()) {
	    	JSONObject jsonObject = new JSONObject();
	    	Map.Entry pair = (Map.Entry)it.next();
	    	
	    	jsonObject.put("key", pair.getKey());
	    	
	    	ReportsDTO tempReportsDTO = (ReportsDTO) pair.getValue();
	    	
	    	jsonObject.put("id", tempReportsDTO.getId());
	    	jsonObject.put("name", tempReportsDTO.getName());
	    	jsonObject.put("count", tempReportsDTO.getCount());
	    	
	    	
	    	array.add(jsonObject);
	    }
		
		 System.out.println(" =========== result: "+array.toString() + "================");
		 
		return array;	
	}
}
