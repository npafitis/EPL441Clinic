package cy.ac.ucy.epl441.model;

import java.sql.Date;

public class PerscriptionWarning {
	private int warningId;
	private int userId;
	private Date date;
	private String description;
	
	public PerscriptionWarning(int user, Date date, String description) {
		super();
		this.userId = user;
		this.date = date;
		this.description = description;
	}
	
	public PerscriptionWarning(int warningId, int user, Date date, String description) {
		super();
		this.warningId = warningId;
		this.userId = user;
		this.date = date;
		this.description = description;
	}

	public int getWarningId() {
		return warningId;
	}
	public void setWarningId(int warningId) {
		this.warningId = warningId;
	}
	public int getUser() {
		return userId;
	}
	public void setUser(int user) {
		this.userId = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
