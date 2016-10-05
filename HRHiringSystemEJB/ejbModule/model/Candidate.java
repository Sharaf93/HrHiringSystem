package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;


/**
 * The persistent class for the CANDIDATES database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Candidate.getCandidates", query="SELECT c FROM Candidate c"),
@NamedQuery(name="Candidate.getCandidatesModified", 
query="SELECT c FROM Candidate c WHERE c.hrsourceid = :hr AND c.datecreated BETWEEN :startDate AND :endDate"),
@NamedQuery(name="Candidate.getCandidatesModifiedNoHrId", 
query="SELECT c FROM Candidate c WHERE c.datecreated BETWEEN :startDate AND :endDate"),

//@NamedQuery(name="Candidate.getCandidatesPositionPhase", 
//query="SELECT c FROM Candidate c WHERE c.hrsourceid = :hr AND c.positionsid = :positionid" +
//		"AND c.currentPhaseId = :phaseid AND c.datecreated BETWEEN :startDate AND :endDate"),

//@NamedQuery(name="Candidate.getCandidatesPosition", 
//query="SELECT c FROM Candidate c WHERE c.hrsourceid = :hr AND c.positionsid = :positionid" +
//		"AND c.datecreated BETWEEN :startDate AND :endDate"),

//@NamedQuery(name="Candidate.getCandidatesPhase", 
//query="SELECT c FROM Candidate c WHERE c.hrsourceid = :hr" +
//		"AND c.currentPhaseId = :phaseid AND c.datecreated BETWEEN :startDate AND :endDate"),


//@NamedQuery(name="Candidate.getCandidatesPositionPhaseNoHrId", 
//query="SELECT c FROM Candidate c WHERE c.positionsid = :positionid" +
//		"AND c.currentPhaseId = :phaseid AND c.datecreated BETWEEN :startDate AND :endDate"),

//@NamedQuery(name="Candidate.getCandidatesPositionNoHrId", 
//query="SELECT c FROM Candidate c WHERE c.positionsid = :positionid" +
//		"AND c.datecreated BETWEEN :startDate AND :endDate"),

//@NamedQuery(name="Candidate.getCandidatesPhaseNoHrId", 
//query="SELECT c FROM Candidate c WHERE" +
//		"c.currentPhaseId = :phaseid AND c.datecreated BETWEEN :startDate AND :endDate")
})
@Table(name="CANDIDATES")
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"ID\"", unique=true, nullable=false)
	private long id;

	@Column(length=50)
	private String currentcompanyname;

	@Column(length=128)
	private String currentposition;

	@Column(precision=16, scale=2)
	private BigDecimal currentsalary;

	@Lob
	private byte[] cv;

	@Temporal(TemporalType.DATE)
	private Date datecreated;

	@Column(length=20)
	private String dateofbirth;

	@Column(nullable=false, length=128)
	private String email;

	@Column(nullable=false, length=128)
	private String fullname;

	@Column(nullable=false)
	private long hrsourceid;

	@Column(nullable=false)
	private long mobilenumber;

	@Column(length=128)
	private String offerrejectionreason;

	@Column(length=10)
	private String offerstatus;

	private long phonenumber;

	@Column(nullable=false)
	private long positionsid;

	private long reasonsoffailureid;

	@Column(nullable=false)
	private long sourceid;

	@Column(name="\"STATUS\"", nullable=false, length=10)
	private String status;

	@Column(nullable=false)
	private int yearsofexperience;
	
	//bi-directional many-to-one association to HRSource
	@ManyToOne
	@JoinColumn(name="HRSOURCEID")
	private Hrsource hrsource;

	//bi-directional many-to-one association to Position
	@ManyToOne
	@JoinColumn(name="POSITIONSID")
	private Position position;

	//bi-directional many-to-one association to ReasonsOfFailure
	@ManyToOne
	@JoinColumn(name = "REASONSOFFAILUREID")
	private Reasonsoffailure reasonsOfFailure;

	//bi-directional many-to-one association to Source
	@ManyToOne
	@JoinColumn(name ="SOURCEID")
	private Source source;

	//bi-directional many-to-one association to PhasesDetail
	@OneToMany(fetch = FetchType.EAGER, mappedBy="candidate", cascade=CascadeType.ALL)
	private List<Phasesdetail> phasesDetails;

	//bi-directional many-to-one association to TestsDetail
	@OneToMany(fetch = FetchType.EAGER, mappedBy="candidate", cascade=CascadeType.ALL)
	private List<Testsdetail> testsDetails;


	public Candidate() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCurrentcompanyname() {
		return this.currentcompanyname;
	}

	public void setCurrentcompanyname(String currentcompanyname) {
		this.currentcompanyname = currentcompanyname;
	}

	public String getCurrentposition() {
		return this.currentposition;
	}

	public void setCurrentposition(String currentposition) {
		this.currentposition = currentposition;
	}

	public BigDecimal getCurrentsalary() {
		return this.currentsalary;
	}

	public void setCurrentsalary(BigDecimal currentsalary) {
		this.currentsalary = currentsalary;
	}

	public byte[] getCv() {
		return this.cv;
	}

	public void setCv(byte[] cv) {
		this.cv = cv;
	}

	public Date getDatecreated() {
		return this.datecreated;
	}

	public void setDatecreated(Timestamp datecreated) {
		this.datecreated = datecreated;
	}

	public String getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public long getHrsourceid() {
		return this.hrsourceid;
	}

	public void setHrsourceid(long hrsourceid) {
		this.hrsourceid = hrsourceid;
	}

	public long getMobilenumber() {
		return this.mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getOfferrejectionreason() {
		return this.offerrejectionreason;
	}

	public void setOfferrejectionreason(String offerrejectionreason) {
		this.offerrejectionreason = offerrejectionreason;
	}

	public String getOfferstatus() {
		return this.offerstatus;
	}

	public void setOfferstatus(String offerstatus) {
		this.offerstatus = offerstatus;
	}

	public long getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public long getPositionsid() {
		return this.positionsid;
	}

	public void setPositionsid(long positionsid) {
		this.positionsid = positionsid;
	}

	public long getReasonsoffailureid() {
		return this.reasonsoffailureid;
	}

	public void setReasonsoffailureid(long reasonsoffailureid) {
		this.reasonsoffailureid = reasonsoffailureid;
	}

	public long getSourceid() {
		return this.sourceid;
	}

	public void setSourceid(long sourceid) {
		this.sourceid = sourceid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getYearsofexperience() {
		return this.yearsofexperience;
	}

	public void setYearsofexperience(int yearsofexperience) {
		this.yearsofexperience = yearsofexperience;
	}

	public Hrsource getHrsource() {
		return hrsource;
	}

	public void setHrsource(Hrsource hrsource) {
		this.hrsource = hrsource;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Reasonsoffailure getReasonsOfFailure() {
		return reasonsOfFailure;
	}

	public void setReasonsOfFailure(Reasonsoffailure reasonsOfFailure) {
		this.reasonsOfFailure = reasonsOfFailure;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public List<Phasesdetail> getPhasesDetails() {
		return phasesDetails;
	}

	public void setPhasesDetails(List<Phasesdetail> phasesDetails) {
		this.phasesDetails = phasesDetails;
	}

	public List<Testsdetail> getTestsDetails() {
		return testsDetails;
	}

	public void setTestsDetails(List<Testsdetail> testsDetails) {
		this.testsDetails = testsDetails;
	}

}