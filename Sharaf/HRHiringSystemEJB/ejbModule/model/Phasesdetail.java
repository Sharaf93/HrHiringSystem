package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PHASESDETAILS database table.
 * 
 */
@Entity
@Table(name="PHASESDETAILS")
public class Phasesdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PhasesdetailPK id;

	@Column(length=1)
	private String comments;

	@Temporal(TemporalType.DATE)
	private Date dateofphase;

	@Column(nullable=false)
	private long lastmodifiedbyid;

	@Column(nullable=false)
	private long phasestatusid;
	
	
	//bi-directional many-to-one association to Candidate
	@ManyToOne
	@JoinColumn( name = "CANDIDATESID")
	private Candidate candidate;

	//bi-directional many-to-one association to PhaseStatus
	@ManyToOne
	@JoinColumn(name ="PHASESTATUSID")
	private Phasestatus phaseStatus;

	//bi-directional many-to-one association to Phas
	@ManyToOne
	@JoinColumn(name ="PHASESID")
	private Phas phas;

	
	public Phasesdetail() {
	}

	public PhasesdetailPK getId() {
		return this.id;
	}

	public void setId(PhasesdetailPK id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateofphase() {
		return this.dateofphase;
	}

	public void setDateofphase(Date dateofphase) {
		this.dateofphase = dateofphase;
	}

	public long getLastmodifiedbyid() {
		return this.lastmodifiedbyid;
	}

	public void setLastmodifiedbyid(long lastmodifiedbyid) {
		this.lastmodifiedbyid = lastmodifiedbyid;
	}

	public long getPhasestatusid() {
		return this.phasestatusid;
	}

	public void setPhasestatusid(long phasestatusid) {
		this.phasestatusid = phasestatusid;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Phasestatus getPhaseStatus() {
		return phaseStatus;
	}

	public void setPhaseStatus(Phasestatus phaseStatus) {
		this.phaseStatus = phaseStatus;
	}

	public Phas getPhas() {
		return phas;
	}

	public void setPhas(Phas phas) {
		this.phas = phas;
	}

}