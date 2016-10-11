package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PHASESDETAILS database table.
 * 
 */
@Embeddable
public class PhasesDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private long candidatesid;

	@Column(unique=true, nullable=false)
	private long phasesid;

	public PhasesDetailPK() {
	}
	public long getCandidatesid() {
		return this.candidatesid;
	}
	public void setCandidatesid(long candidatesid) {
		this.candidatesid = candidatesid;
	}
	public long getPhasesid() {
		return this.phasesid;
	}
	public void setPhasesid(long phasesid) {
		this.phasesid = phasesid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PhasesDetailPK)) {
			return false;
		}
		PhasesDetailPK castOther = (PhasesDetailPK)other;
		return 
			(this.candidatesid == castOther.candidatesid)
			&& (this.phasesid == castOther.phasesid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.candidatesid ^ (this.candidatesid >>> 32)));
		hash = hash * prime + ((int) (this.phasesid ^ (this.phasesid >>> 32)));
		
		return hash;
	}
}