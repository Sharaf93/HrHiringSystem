package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the REASONSOFFAILURE database table.
 * 
 */
@Entity
@Table(name="REASONSOFFAILURE")
public class Reasonsoffailure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"ID\"", unique=true, nullable=false)
	private long id;

	@Column(name="\"NAME\"", nullable=false, length=128)
	private String name;

	//bi-directional many-to-one association to Candidate
	@OneToMany(mappedBy="reasonsOfFailure")
	private List<Candidate> candidates;
	
	public Reasonsoffailure() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

}