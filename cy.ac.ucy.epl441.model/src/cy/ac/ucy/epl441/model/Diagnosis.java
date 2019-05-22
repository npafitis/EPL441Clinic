package cy.ac.ucy.epl441.model;

import java.sql.Date;

public class Diagnosis {
	private int diagnosisId;
	private int patientId;
	private Date diagnosisDate;
	private String details;
	private String comments;
	
	
	public int getPatientId() {
		return patientId;
	}
	
	
	public Diagnosis(int patientId, String details, String comments, Date date) {
		super();
		this.patientId = patientId;
		this.details = details;
		this.comments = comments;
		this.diagnosisDate = date;
	}


	public Diagnosis(int diagnosisId, int patientId, String details, String comments, Date date) {
		super();
		this.diagnosisId = diagnosisId;
		this.patientId = patientId;
		this.details = details;
		this.comments = comments;
	}


	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	

	public int getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = new String(details);
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}


	public Date getDiagnosisDate() {
		return diagnosisDate;
	}


	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
	
	
}
