/**
 * 
 */
package pagecode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import model.Candidate;

import beans.phasesTableBean;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.UIColumn;
import javax.faces.component.html.HtmlDataTable;

/**
 * @author baraa.alaa
 *
 */
public class Klkl1 extends PageCodeBase {

	@EJB
	phasesTableBean mybean;
	
	public String getOS()
	{
		return "kk";
	}

	List<String> phasesNames;
	List<List<Candidate>> phasesCandidates; // each list defines candidates of a certian phase
	//0 -> cvScanning  1 -> call  2 -> tests  3 -> hr  4 -> technical  5 -> presentation
	
	List<Candidate>onephaseCandidates;//one list of candidates to push it in each phase
	protected HtmlSelectBooleanCheckbox myCheck;
	protected HtmlForm form1;
	protected HtmlPanelGrid panelId;
	protected HtmlOutputText p;
	protected HtmlInputText single_cal4;
	protected HtmlCommandButton button1;
	protected HtmlCommandLink link1;
	protected UIColumn column1;
	protected HtmlOutputText text1;
	protected HtmlDataTable table1;
	protected HtmlCommandLink link2;
	public String hoba()
	{
		//System.out.println(mybean.getAllPhasesNames("CV Screening").toString());
		System.out.println("press again");
		//System.out.println(mybean.getAllPhasesNames("CV Screening").toString());
		
		
		phasesNames = mybean.getAllPhasesNames();
		
		Iterator<String> i = phasesNames.iterator();
		
//		for ( int i = 0 ; i < phasesNames.size(); ++i)
//		{
		//Candidate cand;
		
		while (i.hasNext()){
			//System.out.println(i.next());
			
			onephaseCandidates = new ArrayList<Candidate>();
			
			
			onephaseCandidates =  mybean.getCandidatesOfCertainPhase(i.next());
			
			if ( onephaseCandidates.size() == 0)
				System.out.println("fady");
			
			else
				System.out.println("msh fady");
			 
			
			if (onephaseCandidates == null)
				System.out.println("null");
			
			else
				System.out.println("not null");
			
			//if ( onephaseCandidates != null)
				//phasesCandidates.add(onephaseCandidates);
		}
		
		//System.out.println("size = " + Integer.toString( phasesCandidates.size()));
		
		return "LoginPageAndRegistrationPage";
	}
	protected HtmlSelectBooleanCheckbox getMyCheck() {
		if (myCheck == null) {
			myCheck = (HtmlSelectBooleanCheckbox) findComponentInRoot("myCheck");
		}
		return myCheck;
	}
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	protected HtmlPanelGrid getPanelId() {
		if (panelId == null) {
			panelId = (HtmlPanelGrid) findComponentInRoot("panelId");
		}
		return panelId;
	}
	protected HtmlOutputText getP() {
		if (p == null) {
			p = (HtmlOutputText) findComponentInRoot("p");
		}
		return p;
	}
	protected HtmlInputText getSingle_cal4() {
		if (single_cal4 == null) {
			single_cal4 = (HtmlInputText) findComponentInRoot("single_cal4");
		}
		return single_cal4;
	}
	protected HtmlCommandButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandButton) findComponentInRoot("button1");
		}
		return button1;
	}
	protected HtmlCommandLink getLink1() {
		if (link1 == null) {
			link1 = (HtmlCommandLink) findComponentInRoot("link1");
		}
		return link1;
	}
	protected UIColumn getColumn1() {
		if (column1 == null) {
			column1 = (UIColumn) findComponentInRoot("column1");
		}
		return column1;
	}
	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}
	protected HtmlDataTable getTable1() {
		if (table1 == null) {
			table1 = (HtmlDataTable) findComponentInRoot("table1");
		}
		return table1;
	}
	protected HtmlCommandLink getLink2() {
		if (link2 == null) {
			link2 = (HtmlCommandLink) findComponentInRoot("link2");
		}
		return link2;
	}

}