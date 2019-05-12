package cy.ac.ucy.epl441.model;

import java.sql.Date;

public class InformationChanges {
	private int changeId;
	private int patientId;
	private Date date;
	private String newName;
	private String newEmail;
	private String newPhone;
	private String oldName;
	private String oldEmail;
	private String oldPhone;
	private boolean accepted;
	public int getChangeId() {
		return changeId;
	}
	public void setChangeId(int changeId) {
		this.changeId = changeId;
	}
	public InformationChanges(int patientId, Date date, String newName, String newEmail, String newPhone,
			String oldName, String oldEmail, String oldPhone, boolean accepted) {
		super();
		this.patientId = patientId;
		this.date = date;
		this.newName = newName;
		this.newEmail = newEmail;
		this.newPhone = newPhone;
		this.oldName = oldName;
		this.oldEmail = oldEmail;
		this.oldPhone = oldPhone;
		this.accepted = accepted;
	}
	public InformationChanges(int changeId, int patientId, Date date, String newName, String newEmail, String newPhone,
			String oldName, String oldEmail, String oldPhone, boolean accepted) {
		super();
		this.changeId = changeId;
		this.patientId = patientId;
		this.date = date;
		this.newName = newName;
		this.newEmail = newEmail;
		this.newPhone = newPhone;
		this.oldName = oldName;
		this.oldEmail = oldEmail;
		this.oldPhone = oldPhone;
		this.accepted = accepted;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
	public String getNewPhone() {
		return newPhone;
	}
	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getOldEmail() {
		return oldEmail;
	}
	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}
	public String getOldPhone() {
		return oldPhone;
	}
	public void setOldPhone(String oldPhone) {
		this.oldPhone = oldPhone;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
