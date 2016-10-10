/**
 * 
 */
package pagecode;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.UIComponent;

/**
 * @author baraa.alaa
 *
 */
public class ViewCandidate extends PageCodeBase {

	protected HtmlCommandButton button1;
	protected HtmlCommandButton button2;
	protected HtmlForm form;
	protected HtmlOutputText outputID;
	protected UIComponent l;
	protected HtmlCommandButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandButton) findComponentInRoot("button1");
		}
		return button1;
	}

	protected HtmlCommandButton getButton2() {
		if (button2 == null) {
			button2 = (HtmlCommandButton) findComponentInRoot("button2");
		}
		return button2;
	}

	protected HtmlForm getForm() {
		if (form == null) {
			form = (HtmlForm) findComponentInRoot("form");
		}
		return form;
	}

	protected HtmlOutputText getOutputID() {
		if (outputID == null) {
			outputID = (HtmlOutputText) findComponentInRoot("outputID");
		}
		return outputID;
	}

	protected UIComponent getL() {
		if (l == null) {
			l = (UIComponent) findComponentInRoot("l");
		}
		return l;
	}

}