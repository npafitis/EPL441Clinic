package cy.ac.ucy.epl441.model;

public class Diagnosis {
	private int diagnosisId;
	private int patientId;
	private String details;
	private String comments;
	
	
	public int getPatientId() {
		return patientId;
	}
	
	
	public Diagnosis(int patientId, String details, String comments) {
		super();
		this.patientId = patientId;
		this.details = details;
		this.comments = comments;
	}


	public Diagnosis(int diagnosisId, int patientId, String details, String comments) {
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
		this.details = details;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
