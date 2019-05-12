package cy.ac.ucy.epl441.model;

public class Relative {
	private int relativeId;
	private int patientId;
	private String name;
	private String email;
	private String phone;
	public int getRelativeId() {
		return relativeId;
	}
	public void setRelativeId(int relativeId) {
		this.relativeId = relativeId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public Relative(int patientId, String name, String email, String phone) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.email = email;
		this.phone = phone;
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
	public Relative(int relativeId, int patientId, String name, String email, String phone) {
		super();
		this.relativeId = relativeId;
		this.patientId = patientId;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	
}
