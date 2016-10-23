/**
 * 
 */
package pagecode;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputLabel;

/**
 * @author fatma
 *
 */
public class ViewCandidate extends PageCodeBase {

	protected HtmlInputText dateCalender;
	protected HtmlPanelGroup ROFDIV;
	protected HtmlInputHidden flag;
	protected HtmlInputText BirthDateCandidate;
	protected HtmlInputText ExperienceYearsCandidate;
	protected HtmlInputText CurrentCompanyCandidate;
	protected HtmlInputText Currentcandidateposition;
	protected HtmlPanelGroup OSID;
	protected HtmlInputText commentInputId2;
	protected HtmlInputText OfferRejectionReasonID;
	protected UIComponent phaseRepeat;
	protected HtmlOutputLabel PhaseStatus2;
	protected UIComponent testsRepeat;
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

	protected HtmlInputHidden getFlag() {
		if (flag == null) {
			flag = (HtmlInputHidden) findComponentInRoot("flag");
		}
		return flag;
	}

	protected HtmlInputText getBirthDateCandidate() {
		if (BirthDateCandidate == null) {
			BirthDateCandidate = (HtmlInputText) findComponentInRoot("BirthDateCandidate");
		}
		return BirthDateCandidate;
	}

	protected HtmlInputText getExperienceYearsCandidate() {
		if (ExperienceYearsCandidate == null) {
			ExperienceYearsCandidate = (HtmlInputText) findComponentInRoot("ExperienceYearsCandidate");
		}
		return ExperienceYearsCandidate;
	}

	protected HtmlInputText getCurrentCompanyCandidate() {
		if (CurrentCompanyCandidate == null) {
			CurrentCompanyCandidate = (HtmlInputText) findComponentInRoot("CurrentCompanyCandidate");
		}
		return CurrentCompanyCandidate;
	}

	protected HtmlInputText getCurrentcandidateposition() {
		if (Currentcandidateposition == null) {
			Currentcandidateposition = (HtmlInputText) findComponentInRoot("Currentcandidateposition");
		}
		return Currentcandidateposition;
	}

	protected HtmlPanelGroup getOSID() {
		if (OSID == null) {
			OSID = (HtmlPanelGroup) findComponentInRoot("OSID");
		}
		return OSID;
	}

	protected HtmlInputText getCommentInputId2() {
		if (commentInputId2 == null) {
			commentInputId2 = (HtmlInputText) findComponentInRoot("commentInputId2");
		}
		return commentInputId2;
	}

	protected HtmlInputText getOfferRejectionReasonID() {
		if (OfferRejectionReasonID == null) {
			OfferRejectionReasonID = (HtmlInputText) findComponentInRoot("OfferRejectionReasonID");
		}
		return OfferRejectionReasonID;
	}

	protected UIComponent getPhaseRepeat() {
		if (phaseRepeat == null) {
			phaseRepeat = (UIComponent) findComponentInRoot("phaseRepeat");
		}
		return phaseRepeat;
	}

	protected HtmlOutputLabel getPhaseStatus2() {
		if (PhaseStatus2 == null) {
			PhaseStatus2 = (HtmlOutputLabel) findComponentInRoot("PhaseStatus2");
		}
		return PhaseStatus2;
	}

	protected UIComponent getTestsRepeat() {
		if (testsRepeat == null) {
			testsRepeat = (UIComponent) findComponentInRoot("testsRepeat");
		}
		return testsRepeat;
	}

}