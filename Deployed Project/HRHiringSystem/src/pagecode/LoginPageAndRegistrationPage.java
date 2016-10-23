package pagecode;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import model.HRSource;

import ejb.HRSourcesEJB;

@ManagedBean
@ViewScoped
public class LoginPageAndRegistrationPage extends PageCodeBase {

	private String userName;
	private String password;
	private String fullName;
	private String position;
	private String userNameRegisteration;
	private String passwordRegisteration;
	private String usernameExists;
	private String loginMessage;
	private StringBuffer selectedURL;
	private String originalURI;
	private String originalQuery;
	@EJB
	private HRSourcesEJB hrSourceEJB;
	
	public String getPasswordRegisteration() {
		return passwordRegisteration;
	}

	public void setPasswordRegisteration(String passwordRegisteration) {
		this.passwordRegisteration = passwordRegisteration;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUserNameRegisteration() {
		return userNameRegisteration;
	}

	public void setUserNameRegisteration(String userNameRegisteration) {
		this.userNameRegisteration = userNameRegisteration;
	}

	public String getUsernameExists() {
		return usernameExists;
	}

	public void setUsernameExists(String usernameExists) {
		this.usernameExists = usernameExists;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	public StringBuffer getSelectedURL() {
		return selectedURL;
	}

	public void setSelectedURL(StringBuffer selectedURL) {
		this.selectedURL = selectedURL;
	}

	public String getOriginalURI() {
		return originalURI;
	}

	public void setOriginalURI(String originalURI) {
		this.originalURI = originalURI;
	}

	public String getOriginalQuery() {
		return originalQuery;
	}

	public void setOriginalQuery(String originalQuery) {
		this.originalQuery = originalQuery;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String Registeration() throws IOException {
		System.out.println("here in regeeeee");
		String usern = getUserNameRegisteration();
		String pass = getPasswordRegisteration();
		String fulln = getFullName();
		String position = getPosition();
		Boolean usernameExists = hrSourceEJB.checkUserName(usern);
		if (usernameExists) {
			String message = "Username: " + usern + " already exists";
			setUsernameExists(message);
			setUserNameRegisteration("");

			return null;
		} else {
			HRSource hrsource = new HRSource();
			hrsource.setName(fulln);
			hrsource.setPosition(position);
			hrsource.setUsername(usern);
			hrSourceEJB.createHRSource(hrsource);
			setFullName("");
			setPassword("");
			setUsernameExists("");
			setUserNameRegisteration("");
			// servletrequestpart
			long currentUserID = hrsource.getId();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("currentuserId", currentUserID);
			
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("statisticsDashboard.faces");
				return null;
			

		}

	}

	public String login() throws IOException {

		String user = getUsername().toLowerCase();
		boolean correctUN = hrSourceEJB.checkUserName(user);
		String password = getPassword();
		if (correctUN) {
			try {
				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				ExternalContext externalCon =  FacesContext
						.getCurrentInstance().getExternalContext();
				StringBuffer requestURI = request.getRequestURL();
			
				System.out.println("the requested url is  : " + originalURI);
				System.out.println("the requested url is  : " + originalQuery);
				request.login(user, password);
				int x = 0;
				long uId = hrSourceEJB.getHRSourceIDByUserName(user);
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().put("currentuserId", uId);
				FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put("logged", 1);
				FacesContext.getCurrentInstance().getExternalContext()
						.getSession(true);
				if(x == 1)
				{
					//FacesContext.getCurrentInstance().getExternalContext()
					//.redirect(request);
					return null;
				}
				else
				{
					FacesContext.getCurrentInstance().getExternalContext()
					.redirect("statisticsDashboard.faces");
					return null;
				}

			} catch (Exception e) {
				FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put("logged", 0);
				setLoginMessage("Incorrect Password. Please Try Again !");
				return null;
			}
		} else {
			FacesContext.getCurrentInstance().getExternalContext()
			.getSessionMap().put("logged", 0);
			setLoginMessage("Incorrect Username. Please Try Again !");
			return null;
		}
		
	

	}
	
}
