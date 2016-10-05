package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TESTSDETAILS database table.
 * 
 */
@Entity
@Table(name="TESTSDETAILS")
public class Testsdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TestsdetailPK id;

	@Column(nullable=false)
	private int score;
	
	//bi-directional many-to-one association to Candidate
	@ManyToOne
	@JoinColumn( name = "CANDIDATESID")
	private Candidate candidate;

	//bi-directional many-to-one association to Test
	@ManyToOne
	@JoinColumn(name ="TESTSID")
	private Test test;

	public Testsdetail() {
	}

	public TestsdetailPK getId() {
		return this.id;
	}

	public void setId(TestsdetailPK id) {
		this.id = id;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}