package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the PHASES database table.
 * 
 */
@Entity
@NamedQuery(name="Phase.getPhases", query="SELECT c FROM Phas c")
@Table(name="PHASES")
public class Phas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"ID\"", unique=true, nullable=false)
	private long id;

	@Column(name="\"NAME\"", nullable=false, length=50)
	private String name;
	
	//bi-directional many-to-one association to PhasesDetail
	@OneToMany(mappedBy="phas")
	private List<Phasesdetail> phasesDetails;

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

	public List<Phasesdetail> getPhasesDetails() {
		return phasesDetails;
	}

	public void setPhasesDetails(List<Phasesdetail> phasesDetails) {
		this.phasesDetails = phasesDetails;
	}

}