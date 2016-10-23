package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the PHASESDETAILS database table.
 * 
 */
@NamedQueries(
{
@NamedQuery(name = "getPhaesDetailsByCandidateId", query="SELECT p from PhasesDetail p where p.id.candidatesid = :id"),
@NamedQuery(name = "getCertainPhaseDetail", query="SELECT p from PhasesDetail p where p.id.candidatesid = :candidateId AND p.id.phasesid = :phaseId")
})
@Entity
@Table(name="PHASESDETAILS",schema="HRHSSCHEMA")
public class PhasesDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PhasesDetailPK id;

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
		private PhaseStatus phaseStatus;

		//bi-directional many-to-one association to Phas
		@ManyToOne
		@JoinColumn(name ="PHASESID")
		private Phas phas;

	

	public PhasesDetail() {
	}
    
	public PhaseStatus getPhaseStatus()
	{
		return this.phaseStatus;
	}
	
	public void setPhaseStatus(PhaseStatus ps)
	{
		this.phaseStatus = ps;
		
	}
	
	public Phas getPhas()
	{
		return this.phas;
	}
	
	public void setPhas(Phas phase)
	{
		this.phas = phase;
	}
	public PhasesDetailPK getId() {
		return this.id;
	}

	public void setId(PhasesDetailPK id) {
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
//	    Calendar cal = Calendar.getInstance();
//	    cal.set(Calendar.YEAR,dateofphase.getYear());
//		cal.set(Calendar.MONTH, dateofphase.getMonth());
//		cal.set(Calendar.DAY_OF_MONTH, dateofphase.getDay());    
//		Date date = cal.getTime();
//		System.out.println("Year: " + date.getYear() + "Month: " +date.getMonth() + "Day: " + date.getDay());
		System.out.println("ddddddddddddddddddddddddddddddddd : " + dateofphase);
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

}