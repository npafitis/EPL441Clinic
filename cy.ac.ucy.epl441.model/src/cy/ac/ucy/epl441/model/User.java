package cy.ac.ucy.epl441.model;

/**
* Object that represents a user of the system.
*
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

public class User {
	private int userId;
	private String name;
	private String email;
	private String phone;
	private Role role;
	
	
	public int getUserId() {
		return userId;
	}
	
	public User(int userId, String name, String email, String phone, Role role) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
	}

	public User(String name, String email, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = new String(name);
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = new String(email);
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = new String(phone);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
