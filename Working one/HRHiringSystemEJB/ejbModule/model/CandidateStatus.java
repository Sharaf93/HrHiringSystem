package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CANDIDATESTATUS database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="getAllCandidateStatuses",query="SELECT c.status from CandidateStatus c"),
@NamedQuery(name="getCandidateStatusID",query = "SELECT c.id from CandidateStatus c WHERE c.status LIKE :cssName"
)})
@Table(name="CANDIDATESTATUS", schema= "HRHSSCHEMA")
public class CandidateStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"ID\"", unique=true, nullable=false)
	private long id;

	@Column(name="\"STATUS\"", length=128)
	private String status;

	//bi-directional many-to-one association to Candidate
	@OneToMany(fetch = FetchType.EAGER, mappedBy="candidatestatus")
	private List<Candidate> candidates;

	public CandidateStatus() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Candidate> getCandidates() {
		return this.candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Candidate addCandidate(Candidate candidate) {
		getCandidates().add(candidate);
		candidate.setCandidatestatus(this);

		return candidate;
	}

	public Candidate removeCandidate(Candidate candidate) {
		getCandidates().remove(candidate);
		candidate.setCandidatestatus(null);

		return candidate;
	}

}