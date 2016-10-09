package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the TESTS database table.
 * 
 */
@Entity
@Table(name="TESTS",schema="HRHSSCHEMA")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"ID\"", unique=true, nullable=false)
	private long id;

	@Column(name="NAME", nullable=false, length=30)
	private String name;
	
	//bi-directional many-to-one association to TestsDetail
	@OneToMany(mappedBy="test")
	private List<TestsDetail> testsDetails;


	public List<TestsDetail> getTestsDetails() {
		return testsDetails;
	}

	public void setTestsDetails(List<TestsDetail> testsDetails) {
		this.testsDetails = testsDetails;
	}

	public Test() {
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