package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the OFFERSTATUS database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="getAllOfferStatuses",query="SELECT c.statusname from OfferStatus c"),
@NamedQuery(name="getOfferStatusID",query = "SELECT c.id from OfferStatus c WHERE c.statusname LIKE :ofsName"
)})
@Table(name="OFFERSTATUS",schema= "HRHSSCHEMA")
public class OfferStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"ID\"", unique=true, nullable=false)
	private long id;

	@Column(length=128)
	private String statusname;

	//bi-directional many-to-one association to Candidate
	@OneToMany(fetch = FetchType.EAGER, mappedBy="offerstatus")
	private List<Candidate> candidatesOfferStatus;

	public OfferStatus() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatusname() {
		return this.statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public List<Candidate> getCandidatesOfferStatus() {
		return candidatesOfferStatus;
	}

	public void setCandidatesOfferStatus(List<Candidate> candidatesOfferStatus) {
		this.candidatesOfferStatus = candidatesOfferStatus;
	}

}