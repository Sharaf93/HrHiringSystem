package pagecode;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import beans.Bean;


import model.Candidate;
import model.PhasesDetail;
import model.PhasesDetailPK;
import ejb.CandidateStatusEJB;
import ejb.CandidatesEJB;
import ejb.HRSourcesEJB;
import ejb.OfferStatusEJB;
import ejb.PhasesEJB;
import ejb.PositionsEJB;
import ejb.ReasonsOfFailureEJB;
import ejb.SourcesEJB;

import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

@ManagedBean(name= "createcandidateform")
@SessionScoped
public class CreateNewCandidateForm extends PageCodeBase{

	private String fullName;
	private String email;
	private long phoneNumber;
	private long mobileNumber;
	private String dateOfBirth;
	private String positionAppliedFor;
	private int yearsOfExperience;
	private String currentCompany;
	private String currentPosition;
	private String source;
	private BigDecimal currentSalary;
	private Candidate candidate;
	private List <String> HRSources;
	private List <String> Positions;
	private List <String> Sources;
	private Part uploadedCV;
	private String emailExistsMsg;
	private String numberExistsMsg;
	private String birthdateMsg;
	private String cvMsg;
	private PhasesDetail initialPhase;
	@EJB
	private CandidatesEJB candidateEJB;
	@EJB
	private HRSourcesEJB  hrSourcesEJB;
	@EJB
	private SourcesEJB   sourcesEJB;
	@EJB
	private PositionsEJB positionsEJB;
	@EJB
	private ReasonsOfFailureEJB reasonsOfFailureEJB;
	@EJB
	private OfferStatusEJB offerStatusEJB;
	@EJB
	private CandidateStatusEJB candidateStatusEJB;
	@EJB
	private PhasesEJB phaseEJB;
	@EJB
	private Bean phaseDetails;
	
	private UploadedFile uploadedFile;
	
	// On initialization load the dropdown lists
	@PostConstruct
    public void init() {
       try {
		HRSources = hrSourcesEJB.getAllHRSources();
		Sources =   sourcesEJB.getAllSources();
		Positions = positionsEJB.getAllPositions();
		setHRSources(HRSources);
		setSources(Sources);
		setPositions(Positions);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	
	
	public Part getUploadedCV() {
		return uploadedCV;
	}

	public void setUploadedCV(Part uploadedCV) {
		
		this.uploadedCV = uploadedCV;
	}

	public SourcesEJB getSourcesEJB() {
		return sourcesEJB;
	}

	public void setSourcesEJB(SourcesEJB sourcesEJB) {
		this.sourcesEJB = sourcesEJB;
	}

	public PositionsEJB getPositionsEJB() {
		return positionsEJB;
	}

	public void setPositionsEJB(PositionsEJB positionsEJB) {
		this.positionsEJB = positionsEJB;
	}
	
	//HRSources Getters and Setters
	public List<String> getHRSources() {
		return HRSources;
	}
	public void setHRSources(List<String> hRSources) {
		HRSources = hRSources;
	}
	
	public HRSourcesEJB getHrSourcesEJB() {
		return hrSourcesEJB;
	}
	public void setHrSourcesEJB(HRSourcesEJB hrSourcesEJB) {
		this.hrSourcesEJB = hrSourcesEJB;
	}
	
	//Positions Getter and Setter
	public List<String> getPositions() {
		return Positions;
	}
	public void setPositions(List<String> positions) {
		Positions = positions;
	}
	public String getPositionAppliedFor() {
		return positionAppliedFor;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void setPositionAppliedFor(String positionAppliedFor) {
		this.positionAppliedFor = positionAppliedFor;
	}
	
	public String getEmailExistsMsg() {
		return emailExistsMsg;
	}

	public void setEmailExistsMsg(String emailExistsMsg) {
		this.emailExistsMsg = emailExistsMsg;
	}




	

	public String getNumberExistsMsg() {
		return numberExistsMsg;
	}

	public void setNumberExistsMsg(String numberExistsMsg) {
		this.numberExistsMsg = numberExistsMsg;
	}

	//Sources Getters and Setters
	public List<String> getSources() {
		return Sources;
	}
	public void setSources(List<String> sources) {
		Sources = sources;
	}
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	//Candidate Getters and Setters
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public CandidatesEJB getCandidateEJB() {
		return candidateEJB;
	}

	public void setCandidateEJB(CandidatesEJB candidateEJB) {
		this.candidateEJB = candidateEJB;
	}

	
    // Current Salary Getter and Setter
	public BigDecimal getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(BigDecimal currentSalary) {
		this.currentSalary = currentSalary;
	}

	 // Full Name Getter and Setter
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	 // Email Getter and Setter
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	 // Phone Number Getter and Setter
	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	 // Mobile Number Getter and Setter
	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	 // Date Of Birth Getter and Setter
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	 // Years Of Experience Getter and Setter	
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	 public String getBirthdateMsg() {
		return birthdateMsg;
	}


	public void setBirthdateMsg(String birthdateMsg) {
		this.birthdateMsg = birthdateMsg;
	}


	public String getCvMsg() {
		return cvMsg;
	}


	public void setCvMsg(String cvMsg) {
		this.cvMsg = cvMsg;
	}


	// Current Company Getter and Setter
	public String getCurrentCompany() {
		return currentCompany;
	}

	public void setCurrentCompany(String currentCompany) {
		this.currentCompany = currentCompany;
	}

	


	//Creating new candidate to persist in database table
	public String createCandidate() throws Exception
	{  // new candidate attributes
		String fullN = getFullName();
		String email = getEmail().toLowerCase();
		long mobileN = getMobileNumber();
		String dOB = getDateOfBirth();
		String positionAF = getPositionAppliedFor();
		String source = getSource();
		int yearsOfExp = getYearsOfExperience();;
		long createdById = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentuserId").toString());
		System.out.println("created by id = " + createdById);
		String createdbyName = hrSourcesEJB.getHRNameByID(createdById);
		System.out.println("HR Name " + createdbyName);

		//nullable attributes
				Long phoneN1 = getPhoneNumber();
				String cmpN = getCurrentCompany();
				BigDecimal cSalary = getCurrentSalary();
				java.util.Date date= new java.util.Date();
				Timestamp dateCreated = new Timestamp(date.getTime());
				
				// TODO those columns in restart to be set
				
	   //finding the selected position applied for, source and assigned
	   //hr source IDS
		long positionId = positionsEJB.getPositionID(positionAF);
        long sourceId =   sourcesEJB.getSourceID(source);
	    long reasonOfFailureId = reasonsOfFailureEJB.getReasonOfFailureID("None");
	    long offerStatusId = offerStatusEJB.getOfferStatusID("Pending");
	    long candidateStatusId = candidateStatusEJB.getCandidateStatusID("Pending");
	    long currentPhaseId = phaseEJB.getCurrentPhaseID("CV Screening");
	    
	    //checking for email and mobile number's validation(unique)
	    boolean emailExists = candidateEJB.checkEmailExists(email);
	    boolean mobileNumberExists = candidateEJB.checkMobileExists(mobileN);
	    if(emailExists && mobileNumberExists)
	    {
	    
	    	setEmailExistsMsg("A Candidate Already Exists With The Same Email");
	    	setNumberExistsMsg("A Candidate Already Exists With The Same Mobile Number");
	    	return null;
	    }
	    if(mobileNumberExists)
	    {
	    	setNumberExistsMsg("A Candidate Already Exists With The Same Mobile Number");
	    	return null;
	    }
	    if(emailExists)
	    {
	    	setEmailExistsMsg("A Candidate Already Exists With The Same Email");
	    	return null;
	    }
	    candidate = new Candidate();
	    if(getUploadedFile() == null)
		{
			candidate.setCv(null);
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
			candidate.setCv(path);
		}
		
		candidate.setFullname(fullN);
		candidate.setEmail(email);
		candidate.setMobilenumber(mobileN);
		candidate.setPositionsid(positionId);
		candidate.setSourceid(sourceId);
		candidate.setYearsofexperience(yearsOfExp);
		candidate.setHrsourceid(createdById);
	    candidate.setOfferstatusid(offerStatusId);
		candidate.setOfferrejectionreason("None");
		candidate.setStatusid(candidateStatusId);
		candidate.setReasonsoffailureid(reasonOfFailureId);
		candidate.setDatecreated(dateCreated);
		candidate.setCurrentphaseid(currentPhaseId);
		candidate.setCurrentposition(currentPosition);
		
		
		
		
		if(!(phoneN1.equals(null)))
		{
			candidate.setPhonenumber(phoneN1);
		}
		if(cmpN.equals(null))
		{
			candidate.setCurrentcompanyname("None");
		}else
		{
			candidate.setCurrentcompanyname(cmpN);
		}
		if(cSalary.equals(null))
		{
			candidate.setCurrentsalary(BigDecimal.ZERO);
		}else
		{ 
			candidate.setCurrentsalary(cSalary);
		}
		if(dateOfBirth.equals(null))
		{
		candidate.setDateofbirth("");	
		}else
		{
		candidate.setDateofbirth(dOB);
			
		}
		
        
        
		candidateEJB.createCandidate(candidate);
		long candidateID = candidate.getId();
		initialPhase = new PhasesDetail();
		PhasesDetailPK keys = new PhasesDetailPK();
		long phasestatusid = 2;
		keys.setCandidatesid(candidateID);
		keys.setPhasesid(currentPhaseId);
		initialPhase.setId(keys);
		initialPhase.setDateofphase(dateCreated);
		initialPhase.setLastmodifiedbyid(createdById);
		initialPhase.setPhasestatusid(phasestatusid);
	    phaseDetails.updatePhaseOfCandidate(initialPhase);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("candidateid", candidateID);
		FacesContext.getCurrentInstance().getExternalContext().redirect("updatecandidate.faces?candidateId=" + candidateID);
		return null;
	}
	
	public String submitCv() throws IOException{
		//CV Upload
		String fileName = FilenameUtils.getName(uploadedFile.getName());
        String contentType = uploadedFile.getContentType();
        byte[] bytes = uploadedFile.getBytes();
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));
		//CV Upload
        return null;
	}
	
	public String cancelCreation() throws IOException
	{
	
	
		FacesContext.getCurrentInstance().getExternalContext().redirect("statisticsDashboard.faces");
		 return null;
	
	
 	
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	

	
	

}
