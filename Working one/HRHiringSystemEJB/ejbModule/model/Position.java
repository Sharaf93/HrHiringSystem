package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the POSITIONS database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Position.getPositions", query="SELECT c FROM Position c"),
@NamedQuery(name="getAllPositions",query="SELECT c.name from Position c"),
@NamedQuery(name="getPositionID",query = "SELECT c.id from Position c WHERE c.name LIKE :posName"
)})
@Table(name="POSITIONS", schema="HRHSSCHEMA")
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private long id;

	@Column(name="NAME", nullable=false, length=128)
	private String name;
	
	//bi-directional many-to-one association to Candidate
		@OneToMany(mappedBy="position")
		private List<Candidate> candidates;


	public List<Candidate> getCandidates() {
			return candidates;
		}

		public void setCandidates(List<Candidate> candidates) {
			this.candidates = candidates;
		}

	public Position() {
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