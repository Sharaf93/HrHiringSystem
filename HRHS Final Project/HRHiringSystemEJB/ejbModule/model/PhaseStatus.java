package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the PHASESTATUS database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "getPhaseStatusIdByPhaseName", query = "SELECT p.id FROM PhaseStatus p where p.name LIKE :name") })
@Table(name="PHASESTATUS", schema="HRHSSCHEMA")
public class PhaseStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private long id;

	@Column(name="NAME", nullable=false, length=30)
	private String name;
	
	//bi-directional many-to-one association to PhasesDetail
	@OneToMany(mappedBy="phaseStatus")
	private List<PhasesDetail> phasesDetails;


	public List<PhasesDetail> getPhasesDetails() {
		return phasesDetails;
	}

	public void setPhasesDetails(List<PhasesDetail> phasesDetails) {
		this.phasesDetails = phasesDetails;
	}

	public PhaseStatus() {
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