package cy.ac.ucy.epl441.model;

import java.sql.Date;

public class Treatment {
	private int treatmentId;
	private int patientId;
	private String description;
	private Date date;
	public Treatment(int patientId, String description, Date date) {
		super();
		this.patientId = patientId;
		this.description = description;
		this.date = date;
	}
	public Treatment(int treatmentId, int patientId, String description, Date date) {
		super();
		this.treatmentId = treatmentId;
		this.patientId = patientId;
		this.description = description;
		this.date = date;
	}
	public int getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
