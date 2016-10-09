package pagecode;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.HRSource;

import ejb.HRSourcesEJB;
@ManagedBean
@SessionScoped
public class LoginPageAndRegistrationPage extends PageCodeBase{
	
	private String userName;
	private String password;
	private String fullName;
	private String position;
	private String userNameRegisteration;
	private String passwordRegisteration;
	private String usernameExists;
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
	public String Registeration () throws IOException
	{
		String usern = getUserNameRegisteration();
		String pass = getPasswordRegisteration();
		String fulln = getFullName();
		String position = getPosition();
		Boolean usernameExists = hrSourceEJB.checkUserName(usern);
		if(usernameExists)
		{
			String message = "Username: " + usern + " already exists";
			setUsernameExists(message);
			setUserNameRegisteration("");
		   // FacesContext.getCurrentInstance().getExternalContext().redirect("LoginPageAndRegistrationPage.faces#signup");
			return null;
		}else{
		   HRSource hrsource = new HRSource();
		   hrsource.setName(fulln);
		   hrsource.setPosition(position);
		   hrsource.setUsername(usern);
		   hrSourceEJB.createHRSource(hrsource);
		   setFullName("");
		   setPassword("");
		   setUsernameExists("");
		   setUserNameRegisteration("");
		   //servletrequestpart
		   long currentUserID = hrsource.getId();
		   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentuserId", currentUserID);
		   if(position.equals("HR Employee")){
		  return "successe";
		   }
		   else
		   {

			  return "successm";
		   }
		   
		}
	
	}
	public String login() throws IOException{
		
		String user = getUsername();
		boolean correctUN = hrSourceEJB.checkUserName(user);
		
		String password = getPassword();
		
		if(correctUN && password.equals("password")){
			long uId = hrSourceEJB.getHRSourceIDByUserName(user);
			String position = hrSourceEJB.getPositionByID(uId);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentuserId",uId);
			FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			if(position.equals("HR Employee"))
			{
				return "successe";
			}else{
			return "successm";
			}
			
		}
		else{			
			return null;
		}
			
	
	}
	


	
}
