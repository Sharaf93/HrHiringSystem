/**
 * 
 */
package pagecode;

import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlDataTable;

/**
 * @author baraa.alaa
 *
 */
public class PhasesTable extends PageCodeBase {

	protected HtmlCommandLink linkToCandidate2;
	protected HtmlForm form1;
	protected HtmlCommandButton button1;
	protected HtmlDataTable candidateDataTable;
	protected HtmlCommandLink getLinkToCandidate2() {
		if (linkToCandidate2 == null) {
			linkToCandidate2 = (HtmlCommandLink) findComponentInRoot("linkToCandidate2");
		}
		return linkToCandidate2;
	}

	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	protected HtmlCommandButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandButton) findComponentInRoot("button1");
		}
		return button1;
	}

	protected HtmlDataTable getCandidateDataTable() {
		if (candidateDataTable == null) {
			candidateDataTable = (HtmlDataTable) findComponentInRoot("candidateDataTable");
		}
		return candidateDataTable;
	}

}