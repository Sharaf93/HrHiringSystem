/**
 * 
 */
package pagecode;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlPanelGroup;

/**
 * @author baraa.alaa
 *
 */
public class ViewCandidate extends PageCodeBase {

	protected HtmlInputText FullNameCandidate;
	protected HtmlInputText EmailCandidate;
	protected HtmlInputText MobileNumberCandidate;
	protected HtmlInputText PhoneNumberCandidate;
	protected HtmlInputText BirthDateCandidate;
	protected HtmlInputText ExperienceYearsCandidate;
	protected HtmlInputText CurrentCompanyCandidate;
	protected HtmlInputText Currentcandidateposition;
	protected HtmlInputText CurrentCompanySalaryCandidate;
	protected HtmlInputText commentInputId;
	protected HtmlInputText dateCalender;
	protected UIComponent repeatPhases;
	protected HtmlForm myForm;
	protected HtmlSelectBooleanCheckbox myCheck;
	protected HtmlPanelGroup ReasonOfFailureId;
	protected HtmlInputText getFullNameCandidate() {
		if (FullNameCandidate == null) {
			FullNameCandidate = (HtmlInputText) findComponentInRoot("FullNameCandidate");
		}
		return FullNameCandidate;
	}
	protected HtmlInputText getEmailCandidate() {
		if (EmailCandidate == null) {
			EmailCandidate = (HtmlInputText) findComponentInRoot("EmailCandidate");
		}
		return EmailCandidate;
	}
	protected HtmlInputText getMobileNumberCandidate() {
		if (MobileNumberCandidate == null) {
			MobileNumberCandidate = (HtmlInputText) findComponentInRoot("MobileNumberCandidate");
		}
		return MobileNumberCandidate;
	}
	protected HtmlInputText getPhoneNumberCandidate() {
		if (PhoneNumberCandidate == null) {
			PhoneNumberCandidate = (HtmlInputText) findComponentInRoot("PhoneNumberCandidate");
		}
		return PhoneNumberCandidate;
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
	protected HtmlInputText getCurrentCompanySalaryCandidate() {
		if (CurrentCompanySalaryCandidate == null) {
			CurrentCompanySalaryCandidate = (HtmlInputText) findComponentInRoot("CurrentCompanySalaryCandidate");
		}
		return CurrentCompanySalaryCandidate;
	}
	protected HtmlInputText getCommentInputId() {
		if (commentInputId == null) {
			commentInputId = (HtmlInputText) findComponentInRoot("commentInputId");
		}
		return commentInputId;
	}
	protected HtmlInputText getDateCalender() {
		if (dateCalender == null) {
			dateCalender = (HtmlInputText) findComponentInRoot("dateCalender");
		}
		return dateCalender;
	}
	protected UIComponent getRepeatPhases() {
		if (repeatPhases == null) {
			repeatPhases = (UIComponent) findComponentInRoot("repeatPhases");
		}
		return repeatPhases;
	}
	protected HtmlForm getMyForm() {
		if (myForm == null) {
			myForm = (HtmlForm) findComponentInRoot("myForm");
		}
		return myForm;
	}
	protected HtmlSelectBooleanCheckbox getMyCheck() {
		if (myCheck == null) {
			myCheck = (HtmlSelectBooleanCheckbox) findComponentInRoot("myCheck");
		}
		return myCheck;
	}
	protected HtmlPanelGroup getReasonOfFailureId() {
		if (ReasonOfFailureId == null) {
			ReasonOfFailureId = (HtmlPanelGroup) findComponentInRoot("ReasonOfFailureId");
		}
		return ReasonOfFailureId;
	}

}