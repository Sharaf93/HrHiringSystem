package pagecode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import javax.annotation.ManagedBean;
import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;

import model.Candidate;
import model.PhaseStatus;
import model.PhasesDetail;
import model.TestsDetail;

import Trial.phases;

import beans.phasesTableBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ibm.ws.batch.xJCL.beans.listener;
import com.ibm.ws.webservices.xml.wassysapp.systemApp;


@ManagedBean(name="PTMangedBean")
@RequestScoped
public class PhaseTableMangedBean extends PageCodeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	phasesTableBean mybean;
	
	//List<List<Candidate>> phasesCandidates; // each list defines candidates of a certian phase
	//0 -> cvScanning  1 -> call  2 -> tests  3 -> hr  4 -> technical  5 -> presentation
	
	Boolean bolbol;
	
	
	public Boolean getBolbol() {
		return bolbol;
	}

	public void setBolbol(Boolean bolbol) {
		this.bolbol = bolbol;
	}


	List<Boolean>bol;
	
	public List<Boolean> getBol() {
		
		if ( bol == null)
		{
			bol = new ArrayList<Boolean>();
			
			for ( int i = 0 ; i < 7; ++i)
			{
				bol.add(false);
			}
		}
		
		return bol;
	}

	public void setBol(List<Boolean> bol) {
		this.bol = bol;
	}


	private List<Candidate>onephaseCandidates;//one list of candidates to push it in each phase
	
	Candidate ok;
	
	//phases phases;
	
	String klklkl;
	
	
	List<String> trialList;
	
	public List<String> getTrialList() {
		
		if (trialList == null)
		{
			trialList = new ArrayList<String>();
			for ( int i = 0 ; i < 3; ++i)
			{
				trialList.add("kk"+(i+1));
			}
		}
		
		return trialList;
	}

	public void setTrialList(List<String> trialList) {
		this.trialList = trialList;
	}
	
	public Test getTestObj() {
		if ( testObj == null)
			testObj = new Test();
		return testObj;
	}

	public void setTestObj(Test testObj) {
		this.testObj = testObj;
	}


	Test testObj;
	
	List<Test> lista;
	
	
	
	public List<Test> getLista() {
		
		if ( lista == null)
		{
		lista = new ArrayList<Test>();
		
		for ( int i = 0 ; i < 3; ++i)
		{
			testObj = new Test();
			
			testObj.obj = new Test();
			
			testObj.obj.s1 = "3";
			
			lista.add(testObj);
		}
		}
		
		return lista;
	}

	public void setLista(List<Test> lista) {
		this.lista = lista;
	}

	public void test()
	{
		for (int i = 0; i < lista.size() ; ++i )
		{
		System.out.println("obj = "  + lista.get(i).obj.s1 + " " + Integer.toString(i));
		}
	}
	
	public String getKlklkl() {
		return klklkl;
	}


	public void setKlklkl(String klklkl) {
		this.klklkl = klklkl;
	}


	List<phases> UIphases;

	List<Candidate> cc;
	
	
	public void triall()
	{
//		System.out.println("faseeeeeeeeeeeeeeeeeeeeeeeel");
		System.out.println("status = " + ph.get(0).getPhaseStatus().getName());
//		for ( int i = 0 ; i < ph.size(); ++i)
//		{
//			System.out.println("status = " + ph.get(i).getPhaseStatus().getName() + " " + ph.get(i).getPhas().getName());
//		//mybean.traill(ph);
//		}
		
	}
	
	public List<Candidate> getCc() {
		return cc;
	}


	public void setCc(List<Candidate> cc) {
		this.cc = cc;
	}
	
	List<String>idNames;
	

	public List<String> getIdNames() {
		if ( idNames == null)
		{
			idNames = new ArrayList<String>();
			
			for (int i = 0 ; i < 7; ++i )
			{
				idNames.add("h" + Integer.toString(i));
			}
		}
		return idNames;
	}

	public void setIdNames(List<String> idNames) {
		this.idNames = idNames;
	}

	public void trial()
	{
//		System.out.println("outpuuuuuuuuuuuuuuuuuuuuuuuuuut");
//		System.out.println(ph);
//		System.out.println(ph.get(0).getComment());
		
//		System.out.println("single boolean = " + Boolean.toString(bolbol));
//		
//		for ( int i = 0 ; i < 5 ; ++i)
//		{
//			System.out.println("boolean " + Integer.toString(i) + " = " + Boolean.toString(bol.get(i)));
//		}
//		
//		mybean.traill(ph);
		
		
//		Long k = Long.valueOf(FacesContext.getCurrentInstance()
//                .getExternalContext().getRequestParameterMap().get("id"));
//		
//		//System.out.println("soso = " + kk);
//		
//		System.out.println(Long.toString(k));
//		ph = mybean.getPhasesOfCertainCandidate(5);
//		testsDetails = mybean.getTestsOfCertainCandidate(4);
//		System.out.println(Integer.toString(testsDetails.size()));
//		return "EditCandidate";
		
		//System.out.println(Boolean.toString(bolbol));
	}
	
	
	public String  getAllPhasesOfCertainCandidate(long CandidateID)
	{
		return"View Candidate";
	}
	
	public List<Candidate> getOnephaseCandidates() {
		return onephaseCandidates;
	}


	public void setOnephaseCandidates(List<Candidate> onephaseCandidates) {
		this.onephaseCandidates = onephaseCandidates;
	}


	private Candidate[] lolo;
	
	Candidate can;
	
	
	
public Candidate getCan() {
		return can;
	}


List<Candidate> ll;

	public void setCan(Candidate can) {
		//this.can = can;
		
		can = new Candidate();
	}

	private String typee = "TEX";
	
	
	

	public String getTypee() {
		return typee;
	}


	public void setTypee(String typee) {
		this.typee = typee;
	}
	
	

 	public String getROF() {
		return ROF;
	}

	public void setROF(String rOF) {
		ROF = rOF;
	}


	PhasesDetail phtemp;
	List<PhasesDetail> ph;
	
	public List<PhasesDetail> getPh() {
		
		
		if(ph == null)
		{
			
			System.out.println("ezay be null ???????????");
			
			ph = new ArrayList<PhasesDetail>();
			
//			ph = new ArrayList<PhasesDetail>();
//			
//			for ( int i = 0 ; i < 3 ; ++i)
//			{
//				phtemp = new PhasesDetail();
//				
//				phtemp.setPhaseStatus(new PhaseStatus());
//				
//				phtemp.getPhaseStatus().setName("kolkol");
//				
//				ph.add(phtemp);
//			}
//			
		ph = mybean.getPhasesOfCertainCandidate(5);
		
		System.out.println();
		System.out.println();
		System.out.println(ph);
		System.out.println();
		System.out.println();
		//testsDetails = mybean.getTestsOfCertainCandidate(4);
		}
		
		return this.ph;
	}


	public void setPh(List<PhasesDetail> ph) {
		System.out.println("ahlan beek gowwa elset bta3t phhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		System.out.println("gowwa elset bta3t ph status = " + ph.get(0).getPhaseStatus().getName());
		this.ph = ph;
	}


	public List<TestsDetail> getTestsDetails() {
		return testsDetails;
	}

	public void setTestsDetails(List<TestsDetail> testsDetails) {
		this.testsDetails = testsDetails;
	}


	List<TestsDetail> testsDetails;
 	


	public void setUIphases(List<phases> uIphases) {
		UIphases = uIphases;
	}


	Iterator<PhasesDetail> itr;// = ph.iterator();
	
 	String ROF="lllll",tempStr;
 	phases phasestemp;
 	PhasesDetail phasesDetailTemp;
	
 	
 	
	//mybean.getAllPhasesNames("CV Screening").toString()
	public String getOnePhaseCandidate(String Phase)
	{
		
		System.out.println("eih el8abawa deh " + Phase);
		
		//onephaseCandidates = new ArrayList<Candidate>();
		
		List<Candidate>temp;
		
		//onephaseCandidates
		 temp =  mybean.getCandidatesOfCertainPhase(Phase);
		

		 System.out.println("hwwaa dah "+temp);
		 
		setOnephaseCandidates(temp);
		
		return "phasesTable";
	}
	
	
	

}