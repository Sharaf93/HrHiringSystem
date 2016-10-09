/**
 * 
 */
package pagecode;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.UIColumn;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlCommandLink;

/**
 * @author baraa.alaa
 *
 */
public class TrialWebPage1 extends PageCodeBase {

	protected HtmlDataTable table1;
	protected UIColumn column1;
	protected HtmlOutputText text1;
	protected HtmlForm form1;
	protected HtmlCommandLink link1;

	protected HtmlDataTable getTable1() {
		if (table1 == null) {
			table1 = (HtmlDataTable) findComponentInRoot("table1");
		}
		return table1;
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

	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	protected HtmlCommandLink getLink1() {
		if (link1 == null) {
			link1 = (HtmlCommandLink) findComponentInRoot("link1");
		}
		return link1;
	}

}