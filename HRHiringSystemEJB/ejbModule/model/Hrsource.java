package model;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.*;


/**
 * The persistent class for the HRSOURCE database table.
 * 
 */
@Entity
@Table(name="HRSOURCE",schema="HRHSSCHEMA")
@NamedQueries({
@NamedQuery(name="getAllHRSources",query="SELECT h.name from HRSource h"),
@NamedQuery(name="getHRSourceID",query ="SELECT h.id from HRSource h WHERE h.name LIKE :hrsrcName"),
@NamedQuery(name = "checkHRUserName",query = "SELECT h from HRSource h WHERE h.username LIKE :hrun"),
@NamedQuery(name = "getHRPositionByID",query ="SELECT h.position from HRSource h WHERE h.id = :hrid"),
@NamedQuery(name = "getHRSourceIDByUN",query = "SELECT h.id FROM HRSource h WHERE h.username LIKE :hrun"),
@NamedQuery(name = "getHRNameByID",query = "SELECT h.name FROM HRSource h WHERE h.id = :hrid")})
public class HRSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private long id;

	@Column(name="NAME", nullable=false, length=128)
	private String name;
	
	@Column(name="POSITION", nullable=false, length=128)
	private String position;
	
	@Column(name="USERNAME",unique=true, nullable=false, length=128)
	private String username;

	//bi-directional many-to-one association to Candidate
		@OneToMany(mappedBy="hrsource")
		private List<Candidate> candidates;

	public List<Candidate> getCandidates() {
			return candidates;
		}

		public void setCandidates(List<Candidate> candidates) {
			this.candidates = candidates;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	public HRSource() {
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}