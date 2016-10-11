package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PHASES database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Phase.getPhases", query="SELECT c FROM Phas c"),
@NamedQuery(name="getAllPhases",query="SELECT c.name from Phas c"),
@NamedQuery(name="getPhaseID",query = "SELECT c.id from Phas c WHERE c.name LIKE :pName"),
@NamedQuery(name="getPhaseOrder",query = "SELECT c.phaseorder from Phas c WHERE c.name LIKE :ppName")})
@Table(name = "PHASES", schema = "HRHSSCHEMA")
public class Phas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(nullable = false)
	private long phaseorder;

	// bi-directional many-to-one association to PhasesDetail
	@OneToMany(mappedBy = "phas")
	private List<PhasesDetail> phasesDetails;

	// bi-directional many-to-one association to Candidate
	@OneToMany(mappedBy = "phase")
	private List<Candidate> candidates;

	public List<PhasesDetail> getPhasesDetails() {
		return phasesDetails;
	}

	public void setPhasesDetails(List<PhasesDetail> phasesDetails) {
		this.phasesDetails = phasesDetails;
	}

	public Phas() {
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

}