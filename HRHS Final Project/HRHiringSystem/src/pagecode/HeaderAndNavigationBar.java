package pagecode;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ejb.HRSourcesEJB;

@ManagedBean
@SessionScoped
public class HeaderAndNavigationBar {
	@EJB
	private HRSourcesEJB hrEJB;
	private String currentUserName;
	private int isLoggedIn = Integer.parseInt(FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap().get("logged").toString());
	
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

	public int getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(int isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	 public String logout() throws IOException {
		 System.out.println("hereee");
		    FacesContext context = FacesContext.getCurrentInstance();
		    HttpServletRequest request = (HttpServletRequest) 
		        context.getExternalContext().getRequest();
		    try {
		      request.logout();
		      FacesContext.getCurrentInstance().getExternalContext().redirect("login.faces");
		      return null;
		      
		    } catch (ServletException e) {
		 
		      context.addMessage(null, new FacesMessage("Logout failed."));
		      return null;
		    }
		  }
		

	public String homepageredirect() throws IOException {
		
			FacesContext.getCurrentInstance().getExternalContext().redirect("statisticsdashboard.faces");
			return null;
	
	}
	

}
