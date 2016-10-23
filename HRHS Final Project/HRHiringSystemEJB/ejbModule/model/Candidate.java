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
		@NamedQuery(name = "getAllCandidates", query = "SELECT c from Candidate c"),
		@NamedQuery(name = "getCandidateID", query = "SELECT c.id from Candidate c WHERE c.fullname LIKE :candName"),
		@NamedQuery(name = "candEmailExists", query = "SELECT c from Candidate c WHERE c.email LIKE :Cemail"),
		@NamedQuery(name = "candMobileExists", query = "SELECT c from Candidate c WHERE c.mobilenumber LIKE :Cmob"),
		@NamedQuery(name = "getCandidateByID", query = "SELECT c From Candidate c Where c.id = :cid"),
		// Sharaf
		@NamedQuery(name = "Candidate.getCandidates", query = "SELECT c FROM Candidate c"),
		@NamedQuery(name = "Candidate.getCandidatesModified", query = "SELECT c FROM Candidate c WHERE c.hrsourceid = :hr AND c.datecreated BETWEEN :startDate AND :endDate"),
		@NamedQuery(name = "Candidate.getCandidatesModifiedNoHrId", query = "SELECT c FROM Candidate c WHERE c.datecreated BETWEEN :startDate AND :endDate"),
		@NamedQuery(name = "Candidate.getCandidatesPositionPhase", query = "SELECT c FROM Candidate c WHERE c.hrsourceid = :hr AND c.positionsid = :positionid AND c.currentphaseid = :phaseid AND c.datecreated BETWEEN :startDate AND :endDate"),
		@NamedQuery(name = "Candidate.getCandidatesPosition", query = "SELECT c FROM Candidate c WHERE c.hrsourceid = :hr AND c.positionsid = :positionid AND c.datecreated BETWEEN :startDate AND :endDate"),
		@NamedQuery(name = "Candidate.getCandidatesPhase", query = "SELECT c FROM Candidate c WHERE c.hrsourceid = :hr AND c.currentphaseid = :phaseid AND c.datecreated BETWEEN :startDate AND :endDate"),
		@NamedQuery(name = "Candidate.getCandidatesPositionPhaseNoHrId", query = "SELECT c FROM Candidate c WHERE c.positionsid = :positionid AND c.currentphaseid = :phaseid AND c.datecreated BETWEEN :startDate AND :endDate"),
		@NamedQuery(name = "Candidate.getCandidatesPositionNoHrId", query = "SELECT c FROM Candidate c WHERE c.positionsid = :positionid AND c.datecreated BETWEEN :startDate AND :endDate"),
		@NamedQuery(name = "Candidate.getCandidatesPhaseNoHrId", query = "SELECT c FROM Candidate c WHERE c.currentphaseid = :phaseid AND c.datecreated BETWEEN :startDate AND :endDate"),
		//baraa
		@NamedQuery(name="getPendingCandidatesOfCertainPhase", query="SELECT c from Candidate c WHERE c.candidatestatus.status LIKE '%Pend%' AND c.currentphaseid = :id"),
		@NamedQuery(name="getCertainCandidate",query="SELECT c from Candidate c WHERE c.id = :id")})
@Table(name = "CANDIDATES", schema = "HRHSSCHEMA")
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;

	@Column(length = 50)
	private String currentcompanyname;

	@Column(length = 128)
	private String currentposition;

	@Column(precision = 16, scale = 2)
	private BigDecimal currentsalary;

	@Lob
	@Column
	// (nullable=false)
	private byte[] cv;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date datecreated;

	@Column(length = 20)
	private String dateofbirth;

	@Column(nullable = false, length = 128, unique = true)
	private String email;

	@Column(nullable = false, length = 128)
	private String fullname;

	@Column(nullable = false)
	private long hrsourceid;

	@Column(nullable = false, unique = true)
	private long mobilenumber;

	@Column(length = 128)
	private String offerrejectionreason;

	@Column(nullable = false)
	private long offerstatusid;

	private long phonenumber;

	@Column(nullable = false)
	private long positionsid;

	private long reasonsoffailureid;

	@Column(nullable = false)
	private long sourceid;

	@Column(nullable = false)
	private int yearsofexperience;

	@Column(nullable = false)
	private long currentphaseid;

	@Column(nullable = false)
	private long statusid;

	// bi-directional many-to-one association to CandidateStatus
	@ManyToOne
	@JoinColumn(name = "STATUSID")
	private CandidateStatus candidatestatus;

	// bi-directional many-to-one association to currentphase
	@ManyToOne
	@JoinColumn(name = "CURRENTPHASEID")
	private Phas phase;

	// bi-directional many-to-one association to HRSource
	@ManyToOne
	@JoinColumn(name = "HRSOURCEID")
	private HRSource hrsource;

	// bi-directional many-to-one association to Position
	@ManyToOne
	@JoinColumn(name = "POSITIONSID")
	private Position position;

	// bi-directional many-to-one association to ReasonsOfFailure
	@ManyToOne
	@JoinColumn(name = "REASONSOFFAILUREID")
	private ReasonsOfFailure reasonsOfFailure;

	// bi-directional many-to-one association to Source
	@ManyToOne
	@JoinColumn(name = "SOURCEID")
	private Source source;

	// bi-directional many-to-one association to Source
	@ManyToOne
	@JoinColumn(name = "OFFERSTATUSID")
	private OfferStatus offerstatus;

	// bi-directional many-to-one association to PhasesDetail
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
	private List<PhasesDetail> phasesDetails;

	// bi-directional many-to-one association to TestsDetail
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
	private List<TestsDetail> testsDetails;

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

	public long getOfferstatusid() {
		return offerstatusid;
	}

	public void setOfferstatusid(long offerstatusid) {
		this.offerstatusid = offerstatusid;
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

	public long getCurrentphaseid() {
		return currentphaseid;
	}

	public void setCurrentphaseid(long currentphaseid) {
		this.currentphaseid = currentphaseid;
	}

	public long getStatusid() {
		return statusid;
	}

	public void setStatusid(long statusid) {
		this.statusid = statusid;
	}

	public String getFullname() {
		return fullname;
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

	public CandidateStatus getCandidatestatus() {
		return candidatestatus;
	}

	public Phas getPhase() {
		return phase;
	}

	public void setPhase(Phas phase) {
		this.phase = phase;
	}

	public void setCandidatestatus(CandidateStatus candidatestatus) {
		this.candidatestatus = candidatestatus;
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

	public long getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public HRSource getHrsource() {
		return hrsource;
	}

	public void setHrsource(HRSource hrsource) {
		this.hrsource = hrsource;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public ReasonsOfFailure getReasonsOfFailure() {
		return reasonsOfFailure;
	}

	public void setReasonsOfFailure(ReasonsOfFailure reasonsOfFailure) {
		this.reasonsOfFailure = reasonsOfFailure;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public List<PhasesDetail> getPhasesDetails() {
		return phasesDetails;
	}

	public void setPhasesDetails(List<PhasesDetail> phasesDetails) {
		this.phasesDetails = phasesDetails;
	}

	public List<TestsDetail> getTestsDetails() {
		return testsDetails;
	}

	public void setTestsDetails(List<TestsDetail> testsDetails) {
		this.testsDetails = testsDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public int getYearsofexperience() {
		return this.yearsofexperience;
	}

	public void setYearsofexperience(int yearsofexperience) {
		this.yearsofexperience = yearsofexperience;
	}

}