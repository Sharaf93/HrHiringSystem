/**
 * 
 */
package pagecode;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlInputHidden;

/**
 * @author fatma
 *
 */
public class ViewCandidate extends PageCodeBase {

	protected HtmlInputText dateCalender;
	protected HtmlPanelGroup ROFDIV;
	protected HtmlInputText commentInputId2;
	protected HtmlInputHidden flag;
	protected HtmlInputText getDateCalender() {
		if (dateCalender == null) {
			dateCalender = (HtmlInputText) findComponentInRoot("dateCalender");
		}
		return dateCalender;
	}

	protected HtmlPanelGroup getROFDIV() {
		if (ROFDIV == null) {
			ROFDIV = (HtmlPanelGroup) findComponentInRoot("ROFDIV");
		}
		return ROFDIV;
	}

	protected HtmlInputText getCommentInputId2() {
		if (commentInputId2 == null) {
			commentInputId2 = (HtmlInputText) findComponentInRoot("commentInputId2");
		}
		return commentInputId2;
	}

	protected HtmlInputHidden getFlag() {
		if (flag == null) {
			flag = (HtmlInputHidden) findComponentInRoot("flag");
		}
		return flag;
	}

}