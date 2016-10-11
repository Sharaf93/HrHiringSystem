package pagecode;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	setCurrentUserName(userfullname);
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public String logout() throws IOException {
		
		 ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
		         .getSession(false)).invalidate();
		 FacesContext.getCurrentInstance().getExternalContext().redirect("login.faces");
	  
		return null;
	}

	public String homepageredirect() throws IOException {
		String currentuser = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("currentuserId")
				.toString();
		Long userId = Long.parseLong(currentuser);
		String position = hrEJB.getPositionByID(userId);
		if (position.equals("HR Employee")) {
		FacesContext.getCurrentInstance().getExternalContext().redirect("employeesdashboard.faces");
			  
			return null;
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("managerdashboard.faces");
			return null;
		}
	}

}
