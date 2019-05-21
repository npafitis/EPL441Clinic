package cy.ac.ucy.epl441.model;

import java.sql.Date;

/**
* Object that represents a consultation.
*
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

public class Consultation {
	private int consultationId;
	private int userId;
	private int patientId;
	private Date date;
	private boolean attended;
	
	public Consultation(int userId, int patientId, Date date, boolean attended) {
		super();
		this.userId = userId;
		this.patientId = patientId;
		this.date = date;
		this.attended = attended;
	}
	
	public Consultation(int consultationId, int userId, int patientId, Date date, boolean attended) {
		super();
		this.consultationId = consultationId;
		this.userId = userId;
		this.patientId = patientId;
		this.date = date;
		this.attended = attended;
	}

	public int getConsultationId() {
		return consultationId;
	}
	public void setConsultationId(int consultationId) {
		this.consultationId = consultationId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public boolean isAttended() {
		return attended;
	}
	public void setAttended(boolean attended) {
		this.attended = attended;
	}
}
