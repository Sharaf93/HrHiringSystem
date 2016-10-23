package pagecode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.apache.wink.webdav.model.Getcontenttype;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.ibm.websphere.samples.TestLifeCycleSPI;

import ejb.CandidatesEJB;
import ejb.OfferStatusEJB;
import ejb.PositionsEJB;
import ejb.SourcesEJB;

import beans.Bean;

import model.Candidate;
import model.OfferStatus;
import model.Phas;
import model.PhasesDetail;
import model.PhasesDetailPK;
import model.Position;
import model.ReasonsOfFailure;
import model.Source;
import model.Test;
import model.TestsDetail;
import model.TestsDetailPK;

import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;

@ManagedBean(name = "ManagedBean")
@RequestScoped
public class HRHSManagedBean extends PageCodeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	Bean mybean;
	Boolean flag;
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
	Map<String, Long> sourcesDropDownMap;
	Map<String, Long> positionsDropDownMap;
	Map<String,Long> offerStatusDropDownMap;
	PhasesDetail currentPhaseDetail;
	PhasesDetail currentPhaseDetailOfEdit;
	Candidate candidateToBeViewed; // An object of candidate to be viewed as a
									// personal information of Candidate in View
									// Candidate and Edit Candidate pages
	String test;
	String reasonOfFailure;
	String offerRejectionReason;
	Long reasonOfFailureId;
	Long offerStatusID;
	private String emailExistsMsg;
	private String mobileNumberExistsMsg;
	@EJB
	CandidatesEJB candidateEJB;
	@EJB
	SourcesEJB sourcesEJB;
	@EJB
	PositionsEJB positionsEJB;
	@EJB
	OfferStatusEJB offerstatusEJB;

	private List<Test> testsList;
	private List<Position> positions;
	private List<Source> sources;
	private List<OfferStatus> offerStatusList; 
	private String selectedPosition;
	private String selectedSource;
	
	private String cvMsg;
	
	private UploadedFile uploadedFile;

	@PostConstruct
	void init() {
		try {
			sources = sourcesEJB.getSources();
			positions = positionsEJB.getPositions();
			// setSources(sources);
			// setPositions(positions);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	

	public Long getOfferStatusID() {
		return offerStatusID;
	}

	public void setOfferStatusID(Long offerStatusID) {
		this.offerStatusID = offerStatusID;
	}

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
	
	public String getOfferRejectionReason() {
		return offerRejectionReason;
	}

	public void setOfferRejectionReason(String offerRejectionReason) {
		this.offerRejectionReason = offerRejectionReason;
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
				reasonsDropDownMap.put(reasonsOfFailure.get(i).getName()
						.toString(), reasonsOfFailure.get(i).getId());
			}
		}
		return reasonsDropDownMap;
	}

	public void setReasonsDropDownMap(Map<String, Long> reasonsDropDownMap) {
		this.reasonsDropDownMap = reasonsDropDownMap;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public Map<String, Long> getSourcesDropDownMap() {
		if (sourcesDropDownMap == null) {
			sourcesDropDownMap = new HashMap<String, Long>();
			sources = sourcesEJB.getSources();
			int size = sources.size();

			for (int i = 0; i < size; ++i) {
				sourcesDropDownMap.put(sources.get(i).getName().toString(),
						sources.get(i).getId());
			}
		}
		return sourcesDropDownMap;
	}

	public void setSourcesDropDownMap(Map<String, Long> sourcesDropDownMap) {
		this.sourcesDropDownMap = sourcesDropDownMap;
	}

	public Map<String, Long> getPositionsDropDownMap() {
		if (positionsDropDownMap == null) {
			positionsDropDownMap = new HashMap<String, Long>();
			positions = positionsEJB.getPositions();
			int size = positions.size();

			for (int i = 0; i < size; ++i) {
				positionsDropDownMap.put(positions.get(i).getName().toString(),
						positions.get(i).getId());
			}
		}
		return positionsDropDownMap;
	}

	public String getCvMsg() {
		return cvMsg;
	}

	public void setCvMsg(String cvMsg) {
		this.cvMsg = cvMsg;
	}

	public void setPositionsDropDownMap(Map<String, Long> positionsDropDownMap) {
		this.positionsDropDownMap = positionsDropDownMap;
	}

	public Map<String, Long> getOfferStatusDropDownMap() {
		
		if (offerStatusDropDownMap == null)
		{
				offerStatusDropDownMap = new HashMap<String, Long>();
				offerStatusList = offerstatusEJB.getOfferStatuses();
				int size = offerStatusList.size();

				for (int i = 0; i < size; ++i) {
					offerStatusDropDownMap.put(offerStatusList.get(i).getStatusname().toString(),
							offerStatusList.get(i).getId());
				}
		}
		return offerStatusDropDownMap;
	}

	public void setOfferStatusDropDownMap(Map<String, Long> offerStatusDropDownMap) {
		this.offerStatusDropDownMap = offerStatusDropDownMap;
	}

	public List<Test> getTestsList() {
		if (testsList == null) {
			testsList = mybean.getTests();
		}
		return testsList;
	}

	public void setTestsList(List<Test> testsList) {
		this.testsList = testsList;
	}

	public List<ReasonsOfFailure> getReasonsOfFailure() {
		if (reasonsOfFailure == null) {
			reasonsOfFailure = mybean.getAllReasonsOfFailure();
		}
		return reasonsOfFailure;
	}

	public void setReasonsOfFailure(List<ReasonsOfFailure> reasonsOfFailure) {
		this.reasonsOfFailure = reasonsOfFailure;
	}

	public List<OfferStatus> getOfferStatusList() {
		return offerStatusList;
	}

	public void setOfferStatusList(List<OfferStatus> offerStatusList) {
		this.offerStatusList = offerStatusList;
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
			System.out.println("paramValue = " + paramValue);
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
	
	
	
	//Download CV
	public String downloadFile() throws MalformedURLException, IOException {
		String path = candidateToBeViewed.getCv();
		if(path != null){
			File file = new File(path);
			String[] fileNameArray = path.split(Matcher.quoteReplacement(System.getProperty("file.separator")));
			String fileName = fileNameArray[fileNameArray.length-1];
		    
		    FacesContext fc = FacesContext.getCurrentInstance();
		    ExternalContext ec = fc.getExternalContext();
		    
		    String mimeType = ec.getMimeType(path);
		    
		    int fileLength = (int) file.length();
		    
		    ec.responseReset();
		    ec.setResponseContentType(mimeType);
		    ec.setResponseContentLength(fileLength); 
		    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	
		    OutputStream output = ec.getResponseOutputStream();
		    
		    FileInputStream input = new FileInputStream(file);  
	    	byte[] buffer = new byte[1024];  
	    	int i = 0;  
	    	while ((i = input.read(buffer)) != -1) {  
	    		output.write(buffer);  
	    		output.flush();  
	    	}  
	
		    fc.responseComplete();
		}
	    return null;
	}
	//Download CV
	
	

	public List<TestsDetail> getTestDetailsList() {
		if (testDetailsList == null) {
			if (candidateToBeViewed != null) {
				Long candidateId = candidateToBeViewed.getId();
				testDetailsList = mybean
						.getTestDetailsByCandidateId(candidateId);

				if (testDetailsList == null || testDetailsList.size() == 0) {
					System.out.println("gowwaaaaaaaaaaaaaaaaaaaaaaaaaa");

					if (testsList == null) {
						testsList = mybean.getTests();
					}

					int size = testsList.size();
					System.out.println("test size: " + size);

					testDetailsList = new ArrayList<TestsDetail>();

					// System.out.println("mySize = " + );

					TestsDetailPK tdPK;
					TestsDetail tdTEMP;

					for (int i = 0; i < size; ++i) {
						tdPK = new TestsDetailPK();
						tdPK.setCandidatesid(candidateToBeViewed.getId());
						tdPK.setTestsid(testsList.get(i).getId());
						tdTEMP = new TestsDetail();
						tdTEMP.setTest(testsList.get(i));
						tdTEMP.setId(tdPK);
						testDetailsList.add(tdTEMP);
						System.out.println("Test Name: "
								+ tdTEMP.getTest().getName());
					}
				}
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

			System.out.println("gowwa elmanaged bean candidateId = "
					+ candidateId + " phaseID = " + phaseId);

			currentPhaseDetailOfEdit = mybean.getCertainPhaseDetail(
					candidateId, phaseId);

			if (currentPhaseDetailOfEdit.getPhaseStatus().getName()
					.equals("Passed")) {
				phaseStatus = true;
				System.out.println("trueeeeeeeeeeeeeeeeeeeeeeeeeeeee"
						+ currentPhaseDetailOfEdit.getPhaseStatus().getName());
			} else {
				phaseStatus = false;
				System.out.println("falseeeeeeeeeeeeeeeeeeeeeeeeeeeee"
						+ currentPhaseDetailOfEdit.getPhaseStatus().getName());
			}
		}
		return currentPhaseDetailOfEdit;
	}

	public void setCurrentPhaseDetailOfEdit(
			PhasesDetail currentPhaseDetailOfEdit) {
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

	public String getEmailExistsMsg() {
		return emailExistsMsg;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public List<Source> getSources() {
		return sources;
	}

	public void setSources(List<Source> sources) {
		this.sources = sources;
	}

	public String getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(String selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	public String getSelectedSource() {
		return selectedSource;
	}

	public void setSelectedSource(String selectedSource) {
		this.selectedSource = selectedSource;
	}

	public void setEmailExistsMsg(String emailExistsMsg) {
		this.emailExistsMsg = emailExistsMsg;
	}

	public String getMobileNumberExistsMsg() {
		return mobileNumberExistsMsg;
	}

	public void setMobileNumberExistsMsg(String mobileNumberExistsMsg) {
		this.mobileNumberExistsMsg = mobileNumberExistsMsg;
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

		String returnedPage = "/UpdateCandidate.xhtml?faces-redirect=true&candidateId="
				+ Long.toString(candidateToBeViewed.getId());

		if (phaseStatus == true) {
			
			if(candidateToBeViewed.getPhase().getName().equals("Presentation"))
			{
				candidateToBeViewed.setOfferstatusid(offerStatusID);
				
				if ( offerRejectionReason.equals(""))
				{
				candidateToBeViewed.setOfferrejectionreason("Not Specified");
				}
				
				else
				{
					candidateToBeViewed.setOfferrejectionreason(offerRejectionReason);
				}
			}
			
			Long size = mybean.getCountOfPhases();

			Long currentPhaseOrder = candidateToBeViewed.getPhase()
					.getPhaseorder();

			Long currentPhaseStatusID = mybean
					.getPhaseStatusIdByPhaseName("Pass");

			currentPhaseDetail.setPhasestatusid(currentPhaseStatusID);

			if (currentPhaseOrder + 1 > size) {
				Long candidateStatusID = mybean.getCandidateStatusID("Pass");

				candidateToBeViewed.setStatusid(candidateStatusID);

				returnedPage = "/ViewCandidate.xhtml?faces-redirect=true&candidateId="
						+ Long.toString(candidateToBeViewed.getId());

			} else {

				PhasesDetailPK PhasesDetailPKTemp;

				Long nextPhaseId = mybean
						.getPhaseIdByPhaseOrder(currentPhaseOrder + 1);
				candidateToBeViewed.setCurrentphaseid(nextPhaseId);

				Long candidateStatusID = mybean.getCandidateStatusID("Pend");
				candidateToBeViewed.setStatusid(candidateStatusID);

				PhasesDetailPKTemp = new PhasesDetailPK();
				PhasesDetailPKTemp.setCandidatesid(candidateToBeViewed.getId());
				PhasesDetailPKTemp.setPhasesid(nextPhaseId);

				PhasesDetail phD = new PhasesDetail();

				phD.setId(PhasesDetailPKTemp);

				String currentUser = FacesContext.getCurrentInstance()
						.getExternalContext().getSessionMap()
						.get("currentuserId").toString();
				long currentUserId = Long.parseLong(currentUser);
				phD.setLastmodifiedbyid(currentUserId);

				Long nextPhaseStatusID = mybean
						.getPhaseStatusIdByPhaseName("Pend");

				phD.setPhasestatusid(nextPhaseStatusID);

				mybean.persistNewPhaseDetail(phD);

			}
		}

		else {

			Long currentPhaseStatusID = mybean
					.getPhaseStatusIdByPhaseName("Fail");

			System.out.println("currentPhaseStatusID = "
					+ Long.toString(currentPhaseStatusID));

			currentPhaseDetail.setPhasestatusid(currentPhaseStatusID);

			Long candidateStatusID = mybean.getCandidateStatusID("Archiv");

			candidateToBeViewed.setStatusid(candidateStatusID);

			// TODO ta7oot elreason of failureID

			System.out.println("reasoooooon of failure ="
					+ Long.toString(reasonOfFailureId));
			candidateToBeViewed.setReasonsoffailureid(reasonOfFailureId);
		}
		// mybean.updatePhaseOfCandidate(currentPhaseDetail);

		// TODO handling LastModifiedByid
		String currentUser = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("currentuserId")
				.toString();
		long currentUserId = Long.parseLong(currentUser);
		System.out.println("current user Id : " + currentUserId);
		currentPhaseDetail.setLastmodifiedbyid(currentUserId);

		Long currentPhaseOrder = candidateToBeViewed.getPhase().getPhaseorder();

		mybean.editCandidateMerge(currentPhaseDetail, candidateToBeViewed);

		if (testDetailsList != null) {
			mybean.persistTestsDetails(testDetailsList);
		}

		return returnedPage;
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

		return "/ViewCandidate.xhtml?faces-redirect=true&candidateId="
				+ paramValue;
	}

	public String EditCandidate() throws IOException {
		long mobilenumber = candidateToBeViewed.getMobilenumber();
		String email = candidateToBeViewed.getEmail();
		boolean mobileExists = candidateEJB.checkMobileExists(mobilenumber);
		boolean emailExists = candidateEJB.checkEmailExists(email);

		if (mobileExists && emailExists)

		{
			System.out.println("Condition 1 E$M");
			Candidate u1 = candidateEJB.getHelperCandidateEmail();
			Candidate u2 = candidateEJB.getHelperCandidateMobile();
			long id1 = candidateToBeViewed.getId();
			long idEmail = u1.getId();
			long idMobile = u2.getId();
			System.out.println("view, email, mobile " + id1 + " " + idEmail
					+ " " + idMobile);
			if ((id1 != idEmail) && (id1 != idMobile)) {
				System.out.println("the same cand both");
				setMobileNumberExistsMsg("A Canidate Already Exists With The Same Mobile Number");
				setEmailExistsMsg("A Candidate Already Exists With The Same Email");
				return null;
			} else {
				if (!(id1 == idEmail)) {
					System.out.println("same mobile but not email");
					setEmailExistsMsg("A Candidate Already Exists With The Same Email");
					return null;

				} else {
					if ((id1 != idMobile)) {
						System.out.println("same email but not mobile");
						setMobileNumberExistsMsg("A Canidate Already Exists With The Same Mobile Number");
						return null;
					}
				}

			}
		} else {
			if (mobileExists) {
				System.out.println("mobile only");
				Candidate mobile = candidateEJB.getHelperCandidateMobile();
				long id1 = candidateToBeViewed.getId();
				long idm = mobile.getId();
				System.out.println("view, mobile " + id1 + " " + idm);
				if (!(id1 == idm)) {
					setMobileNumberExistsMsg("A Canidate Already Exists With The Same Mobile Number");
					return null;
				}
			} else {
				if (emailExists) {
					System.out.println("email only ");

					Candidate email1 = candidateEJB.getHelperCandidateEmail();
					long id1 = candidateToBeViewed.getId();
					long ide = email1.getId();
					System.out.println("view, email" + id1 + " " + ide);
					if (!(id1 == ide)) {
						setEmailExistsMsg("A Candidate Already Exists With The Same Email");
						return null;
					}
				}
			}
		}
		
		if(getUploadedFile() == null)
		{
			candidateToBeViewed.setCv(null);
		}else
		{
			String fileName = FilenameUtils.getName(uploadedFile.getName());
	        String contentType = uploadedFile.getContentType();
	        byte[] bytes = uploadedFile.getBytes();
	        if(fileName.equals("")|| fileName.equals(null) || contentType.equals(null) || contentType.equals("") 
	        		|| bytes.length == 0)
	        {
	        	setCvMsg("File Upload Failed. Please Try Again !");
	        	return null;
	        }
	        setCvMsg(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType));
	       // FacesContext.getCurrentInstance().addMessage(null, 
	                //new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));
//	        candidate.setCv(bytes);
	        
	        //Change Path if needed
	        String path = "C:" + File.separator + "Users" + File.separator + 
	        		"fatma" + File.separator + "hrmangmentrepo" + File.separator + 
	        		"HRHiringSystem" + File.separator + "WebContent" + 
	        		File.separator + "Cvs" + File.separator + fileName;
	        
	        try
	        {
		        File f = new File(path);
	
		        f.getParentFile().mkdirs(); 
		        f.createNewFile();
		        
		        FileOutputStream fos = new FileOutputStream(path);
		        fos.write(bytes);
		        fos.close();
	        }
	        catch(FileNotFoundException ex)
	        {
	         System.out.println("FileNotFoundException : " + ex);
	        }
	        catch(IOException ioe)
	        {
	         System.out.println("IOException : " + ioe);
	        }
	        
	        
	        System.out.println("CV Size is " + bytes.length);
			candidateToBeViewed.setCv(path);
		}

		if (flag == true) {
			if (phaseStatus == true) {
				
				if(candidateToBeViewed.getPhase().getName().equals("Presentation"))
				{
					candidateToBeViewed.setOfferstatusid(offerStatusID);
					if ( offerRejectionReason.equals(""))
					{
					candidateToBeViewed.setOfferrejectionreason("Not Specified");
					}
					
					else
					{
						candidateToBeViewed.setOfferrejectionreason(offerRejectionReason);
					}
				}
				
				Long size = mybean.getCountOfPhases();

				Long currentPhaseOrder = candidateToBeViewed.getPhase()
						.getPhaseorder();

				Long currentPhaseStatusID = mybean
						.getPhaseStatusIdByPhaseName("Pass");

				// System.out.println("currentPhaseStatusID = " +
				// Long.toString(currentPhaseStatusID));
				currentPhaseDetailOfEdit.setPhasestatusid(currentPhaseStatusID);

				if (currentPhaseOrder + 1 > size) {
					Long candidateStatusID = mybean
							.getCandidateStatusID("Pass");
					candidateToBeViewed.setStatusid(candidateStatusID);
				} else {

					PhasesDetailPK PhasesDetailPKTemp;

					Long nextPhaseId = mybean
							.getPhaseIdByPhaseOrder(currentPhaseOrder + 1);
					candidateToBeViewed.setCurrentphaseid(nextPhaseId);
					Long candidateStatusID = mybean
							.getCandidateStatusID("Pend");
					candidateToBeViewed.setStatusid(candidateStatusID);

					PhasesDetailPKTemp = new PhasesDetailPK();
					PhasesDetailPKTemp.setCandidatesid(candidateToBeViewed
							.getId());
					PhasesDetailPKTemp.setPhasesid(nextPhaseId);

					PhasesDetail phD = new PhasesDetail();

					phD.setId(PhasesDetailPKTemp);

					String currentUser = FacesContext.getCurrentInstance()
							.getExternalContext().getSessionMap()
							.get("currentuserId").toString();
					long currentUserId = Long.parseLong(currentUser);
					phD.setLastmodifiedbyid(currentUserId);

					Long nextPhaseStatusID = mybean
							.getPhaseStatusIdByPhaseName("Pend");

					phD.setPhasestatusid(nextPhaseStatusID);

					mybean.persistNewPhaseDetail(phD);
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
		}
		currentPhaseDetailOfEdit.setLastmodifiedbyid(1);

		mybean.editCandidateMerge(currentPhaseDetailOfEdit, candidateToBeViewed);
		mybean.phasesDetailsListMerge(phaseDetailsByCandidateIdList);

		if (testDetailsList != null) {
			mybean.mergeTestsDetails(testDetailsList);
		}

		return "/ViewCandidate.xhtml?faces-redirect=true&candidateId="
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
		return "/phasesTable.xhtml?faces-redirect=true&candidateId="
				+ paramValue;
	}

	public String cancel() {

		Long candidateId;
		String paramValue;
		Map<String, String> params;
		params = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		paramValue = params.get("candidateId");
		System.out.println("paramValue = " + paramValue);
		candidateId = Long.valueOf(paramValue);

		return "/ViewCandidate.xhtml?faces-redirect=true&candidateId="
				+ Long.toString(candidateId);
	}
}