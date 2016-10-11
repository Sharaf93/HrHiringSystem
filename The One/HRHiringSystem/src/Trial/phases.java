package Trial;

import java.util.Date;


public class phases {

	Date date;
	
	private String name;
	private String comment;
	private String status;
	
	public void setPhase ( String Name,String Comment,String Status,Date date   )
	{
		this.name = Name; 
		this.comment = Comment;
		this.status = Status;
		this.date = date;
	}
	
	public phases getPhase()
	{
		return this;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}