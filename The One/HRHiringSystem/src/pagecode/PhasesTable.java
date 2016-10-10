/**
 * 
 */
package pagecode;

import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.UIColumn;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlDataTable;

/**
 * @author baraa.alaa
 *
 */
public class PhasesTable extends PageCodeBase {

	protected HtmlCommandLink linkToCandidate2;
	protected UIColumn col;
	protected HtmlForm form1;
	protected HtmlCommandButton button1;
	protected HtmlDataTable candidateDataTable;
	protected HtmlCommandButton button2;
	protected HtmlCommandLink getLinkToCandidate2() {
		if (linkToCandidate2 == null) {
			linkToCandidate2 = (HtmlCommandLink) findComponentInRoot("linkToCandidate2");
		}
		return linkToCandidate2;
	}

	protected UIColumn getCol() {
		if (col == null) {
			col = (UIColumn) findComponentInRoot("col");
		}
		return col;
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

	protected HtmlCommandButton getButton2() {
		if (button2 == null) {
			button2 = (HtmlCommandButton) findComponentInRoot("button2");
		}
		return button2;
	}

}