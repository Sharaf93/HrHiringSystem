package pagecode;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class LoginPageAndRegistrationPage extends PageCodeBase{
	
	private final String userID = "admin";
	private final String pass = "password";
	
	String username;
	String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() throws IOException{
		
		String user = getUsername();
		String password = getPassword();
		
		if(user.equals(userID) && password.equals(pass)){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
			FacesContext.getCurrentInstance().getExternalContext().redirect("managerDashboard.faces");
		}
		else{
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or password is Incorrect"));
		}
			
		return null;
	}
	
}
