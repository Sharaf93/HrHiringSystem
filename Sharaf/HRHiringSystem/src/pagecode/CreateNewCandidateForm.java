//package pagecode;
//
//
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
////import javax.annotation.ManagedBean;
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//
//import ejb.CandidatesEJB;
//import ejb.HRSourcesEJB;
//import ejb.PositionsEJB;
//import ejb.SourcesEJB;
//
//import model.Candidate;
//
//@ManagedBean(name= "createcandidateform")
//@RequestScoped
//public class CreateNewCandidateForm extends PageCodeBase{
//
//	private String fullName;
//	private String email;
//	private BigInteger phoneNumber;
//	private BigInteger mobileNumber;
//	private String dateOfBirth;
//	private String positionAppliedFor;
//	private int yearsOfExperience;
//	private String currentCompany;
//	private String source;
//	private String assignedHRSource;
//	private BigDecimal currentSalary;
//	private Candidate candidate;
//	private List <String> HRSources;
//	private List <String> Positions;
//	private List <String> Sources;
//	@EJB
//	private CandidatesEJB candidateEJB;
//	@EJB
//	private HRSourcesEJB  hrSourcesEJB;
//	@EJB
//	private SourcesEJB   sourcesEJB;
//	@EJB
//	private PositionsEJB positionsEJB;
//	@PostConstruct
//    public void init() {
//       try {
//		HRSources = hrSourcesEJB.getAllHRSources();
//		Sources =   sourcesEJB.getAllSources();
//		Positions = positionsEJB.getAllPositions();
//		setHRSources(HRSources);
//		setSources(Sources);
//		setPositions(Positions);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//    }
//	public List<String> getHRSources() {
//		return HRSources;
//	}
//	public void setHRSources(List<String> hRSources) {
//		HRSources = hRSources;
//	}
//	public List<String> getPositions() {
//		return Positions;
//	}
//	public void setPositions(List<String> positions) {
//		Positions = positions;
//	}
//	public List<String> getSources() {
//		return Sources;
//	}
//	public void setSources(List<String> sources) {
//		Sources = sources;
//	}
//	public HRSourcesEJB getHrSourcesEJB() {
//		return hrSourcesEJB;
//	}
//	public void setHrSourcesEJB(HRSourcesEJB hrSourcesEJB) {
//		this.hrSourcesEJB = hrSourcesEJB;
//	}
//	public Candidate getCandidate() {
//		return candidate;
//	}
//
//	public void setCandidate(Candidate candidate) {
//		this.candidate = candidate;
//	}
//
//	public CandidatesEJB getCandidateEJB() {
//		return candidateEJB;
//	}
//
//	public void setCandidateEJB(CandidatesEJB candidateEJB) {
//		this.candidateEJB = candidateEJB;
//	}
//
//	
//
//	public BigDecimal getCurrentSalary() {
//		return currentSalary;
//	}
//
//	public void setCurrentSalary(BigDecimal currentSalary) {
//		this.currentSalary = currentSalary;
//	}
//
//	public String getFullName() {
//		return fullName;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public BigInteger getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(BigInteger phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public BigInteger getMobileNumber() {
//		return mobileNumber;
//	}
//
//	public void setMobileNumber(BigInteger mobileNumber) {
//		this.mobileNumber = mobileNumber;
//	}
//
//	public String getDateOfBirth() {
//		return dateOfBirth;
//	}
//
//	public void setDateOfBirth(String dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}
//
//	public String getPositionAppliedFor() {
//		return positionAppliedFor;
//	}
//
//	public void setPositionAppliedFor(String positionAppliedFor) {
//		this.positionAppliedFor = positionAppliedFor;
//	}
//
//	public int getYearsOfExperience() {
//		return yearsOfExperience;
//	}
//
//	public void setYearsOfExperience(int yearsOfExperience) {
//		this.yearsOfExperience = yearsOfExperience;
//	}
//
//	public String getCurrentCompany() {
//		return currentCompany;
//	}
//
//	public void setCurrentCompany(String currentCompany) {
//		this.currentCompany = currentCompany;
//	}
//
//	public String getSource() {
//		return source;
//	}
//
//	public void setSource(String source) {
//		this.source = source;
//	}
//
//	public String getAssignedHRSource() {
//		return assignedHRSource;
//	}
//
//	public void setAssignedHRSource(String assignedHRSource) {
//		this.assignedHRSource = assignedHRSource;
//	}
//	
//	public String createCandidate() throws Exception
//	{  // new candidate attributes
//		String fullN = getFullName();
//		System.out.println("hereee !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ************:" + "Name "+ fullN);
//		String email = getEmail();
//		System.out.println("Email " + email);
//		BigInteger mobileN = getMobileNumber();
//		System.out.println("Mobile " + mobileN);
//		String dOB = getDateOfBirth();
//		System.out.println("Date " + dOB);
//		String positionAF = getPositionAppliedFor();
//		System.out.println("Position " + positionAF);
//		String source = getSource();
//		System.out.println("Source " + source);
//		int yearsOfExp = getYearsOfExperience();
//		System.out.println("years " + yearsOfExp);
//		String assignedHR = getAssignedHRSource();
//		System.out.println("assigned hr" + assignedHR);
//		
//		candidate = new Candidate();
//		candidate.setFullName(fullN);
//		candidate.setEmail(email);
//		candidate.setMobileNumber(mobileN);
//		candidate.setDateOfBirth(dOB);
//		candidate.setPositionsID(1);
//		candidate.setSourceID(1);
//		candidate.setYearsOfExperience(yearsOfExp);
//		candidate.setHRSourceID(1);
//		candidate.setStatus("Pending");
//		candidate.setOfferRejectionReason("None");
//		candidate.setReasonsOfFailureID(1);
//		candidate.setOfferStatus("Awaiting");
//		
//		//nullable 
//		BigInteger phoneN1 = getPhoneNumber();
//		String cmpN = getCurrentCompany();
//		BigDecimal cSalary = getCurrentSalary();
//		if(phoneN1.equals(null))
//		{
//			candidate.setPhoneNumber(BigInteger.ZERO);
//		}else
//		{
//			candidate.setPhoneNumber(phoneN1);
//		}
//		if(cmpN.equals(null))
//		{
//			candidate.setCurrentCompanyName("None");
//		}else
//		{
//			candidate.setCurrentCompanyName(cmpN);
//		}
//		if(cSalary.equals(null))
//		{
//			candidate.setCurrentSalary(BigDecimal.ZERO);
//		}else
//		{
//			candidate.setCurrentSalary(cSalary);
//		}
//		System.out.println("Candidate********");
//		System.out.println(candidate.toString());
//		
//		candidateEJB.createCandidate(candidate);
//		return "success";
//	}
//	public String cancelCreation()
//	{
//     return "success";		
//	}
//	
//	
//
//}
