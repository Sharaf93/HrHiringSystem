package pagecode;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ejb.HRSourcesEJB;

@ManagedBean
@SessionScoped
public class HeaderAndNavigationBar {
	@EJB
	private HRSourcesEJB hrEJB;
	private String currentUserName;
	
	@PostConstruct
	void init()
	{
	String currentU = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentuserId").toString();
	Long currentUID = Long.parseLong(currentU);
	String userfullname = hrEJB.getHRNameByID(currentUID);
	System.out.println(userfullname);
	setCurrentUserName(userfullname);
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public String logout() {
		System.out.println("heree in logout");
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentuserId",null);
		return "success";
	}

	public String homepageredirect() {
		String currentuser = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("currentuserId")
				.toString();
		Long userId = Long.parseLong(currentuser);
		String position = hrEJB.getPositionByID(userId);
		if (position.equals("HR Employee")) {
			return "successe";
		} else {
			return "successm";
		}
	}

}
