package cy.ac.ucy.epl441.model;

public class User {
	private int userId;
	private String password;
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

	public User(String name, String email, String phone, Role role, String password) {
		super();
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
