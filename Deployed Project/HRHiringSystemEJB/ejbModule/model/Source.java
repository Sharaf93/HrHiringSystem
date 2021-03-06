package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the "SOURCE" database table.
 * 
 */
@Entity
@Table(name="SOURCE",schema="HRHSSCHEMA")
@NamedQueries({
@NamedQuery(name="getSources",query = "Select s from Source s"),
@NamedQuery(name="getAllSources",query="SELECT s.name from Source s"),
@NamedQuery(name="getSourceID", query="SELECT s.id from Source s WHERE s.name LIKE :srcName")})
public class Source implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private long id;

	@Column(name="NAME", nullable=false, length=128)
	private String name;
	
	//bi-directional many-to-one association to Candidate
		@OneToMany(mappedBy="source")
		private List<Candidate> candidates;


	public List<Candidate> getCandidates() {
			return candidates;
		}

		public void setCandidates(List<Candidate> candidates) {
			this.candidates = candidates;
		}

	public Source() {
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