package pagecode;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import model.Candidate;

import sun.util.calendar.BaseCalendar.Date;

@ManagedBean
@SessionScoped
public class CreateNewCandidateForm extends PageCodeBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author fatma
	 */
	
	private String fullName;
	private String email;
	private int phoneNumber;
	private int mobileNumber;
	private Date dateOfBirth;
	private String positionAppliedFor;
	private int yearsOfExperience;
	private String currentCompany;
	private String source;
	private String assignedHRSource;
	private BigDecimal currentSalary;
	private Candidate candidate;
	
	public CreateNewCandidateForm(String cFName, String cEmail, int cPNumber, int cMNumber,
			Date cDateOfBirth, String cPositionAppliedFor, int cYearsOfExp, String cCurrentCompany
			, String cSource, String cAssignedHR, BigDecimal cCurrentSalary)
	{
		this.fullName = cFName;
		this.email = cEmail;
		this.phoneNumber = cPNumber;
		this.mobileNumber = cMNumber;
		this.dateOfBirth = cDateOfBirth;
		this.positionAppliedFor = cPositionAppliedFor;
		this.yearsOfExperience = cYearsOfExp;
		this.currentCompany = cCurrentCompany;
		this.source = cSource;
		this.assignedHRSource = cAssignedHR;
		this.currentSalary = cCurrentSalary;
	}

	public BigDecimal getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(BigDecimal currentSalary) {
		this.currentSalary = currentSalary;
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPositionAppliedFor() {
		return positionAppliedFor;
	}

	public void setPositionAppliedFor(String positionAppliedFor) {
		this.positionAppliedFor = positionAppliedFor;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getCurrentCompany() {
		return currentCompany;
	}

	public void setCurrentCompany(String currentCompany) {
		this.currentCompany = currentCompany;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAssignedHRSource() {
		return assignedHRSource;
	}

	public void setAssignedHRSource(String assignedHRSource) {
		this.assignedHRSource = assignedHRSource;
	}
	
	public String createCandidate()
	{
		candidate = new Candidate();
		return "success";
	}
	public String cancelCreation()
	{
     return "success";		
	}
	
	

}
