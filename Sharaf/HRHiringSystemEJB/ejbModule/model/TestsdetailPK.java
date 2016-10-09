package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TESTSDETAILS database table.
 * 
 */
@Embeddable
public class TestsdetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private long candidatesid;

	@Column(unique=true, nullable=false)
	private long testsid;

	public TestsdetailPK() {
	}
	public long getCandidatesid() {
		return this.candidatesid;
	}
	public void setCandidatesid(long candidatesid) {
		this.candidatesid = candidatesid;
	}
	public long getTestsid() {
		return this.testsid;
	}
	public void setTestsid(long testsid) {
		this.testsid = testsid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TestsdetailPK)) {
			return false;
		}
		TestsdetailPK castOther = (TestsdetailPK)other;
		return 
			(this.candidatesid == castOther.candidatesid)
			&& (this.testsid == castOther.testsid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.candidatesid ^ (this.candidatesid >>> 32)));
		hash = hash * prime + ((int) (this.testsid ^ (this.testsid >>> 32)));
		
		return hash;
	}
}