package cy.ac.ucy.epl441.model;

public class Patient {
	private int patientId;
	private String name;
	private String email;
	private String phone;
	private String status;
	private boolean selfHarm;
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isSelfHarm() {
		return selfHarm;
	}
	public void setSelfHarm(boolean selfHarm) {
		this.selfHarm = selfHarm;
	}
	public Patient(String name, String email, String phone, boolean selfHarm, String status) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.selfHarm = selfHarm;
		this.status = status;
	}
	public Patient(int patientId, String name, String email, String phone, boolean selfHarm, String status) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.selfHarm = selfHarm;
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
