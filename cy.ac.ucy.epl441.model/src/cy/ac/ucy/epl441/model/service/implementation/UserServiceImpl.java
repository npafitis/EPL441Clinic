package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Consultation;
import cy.ac.ucy.epl441.model.PerscriptionWarning;
import cy.ac.ucy.epl441.model.Role;
import cy.ac.ucy.epl441.model.User;
import cy.ac.ucy.epl441.model.service.ConsultationService;
import cy.ac.ucy.epl441.model.service.UserService;
import cy.ac.ucy.epl441.model.service.WarningService;


/**
* An implementation of the userService which offers update, delete and retrieval from the database.
* 
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class UserServiceImpl implements UserService {
	public UserServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	private Connection con;
	@Override
	public void create(User item) {
		String query = 	"INSERT INTO User (name, email, phone, role)\n" + 
						String.format("VALUES (%s, %s, %s, %s,%s,%s)",
								item.getName(),
								item.getEmail(),
								item.getPhone(),
								item.getRole().name());
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	* Retrieves the whole table from the database in an Arraylist
	* 
	* @author  npafitis
	* @version 1.0
	* @since   2019-05-21 
	*/
	@Override
	public ArrayList<User> getAll() {
		String query = "SELECT * FROM User";
		ArrayList<User> list = new ArrayList<User>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int userId = rs.getInt("userId");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				Role role = Role.valueOf(rs.getString("role"));
				
				User user = new User(userId, name, email, phone, role);
				list.add(user);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public UserServiceImpl() {
		super();
	}

	@Override
	public User get(int id) {
		String query = "SELECT * FROM User WHERE userId = " + id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int userId = rs.getInt("userId");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				Role role = Role.valueOf(rs.getString("role"));
				
				User user = new User(userId, name, email, phone, role);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void update(User item) {
		String query = 
				"UPDATE User" +
				"Set 	name = " + item.getName() +
				"		email = " + item.getEmail() +
				"		phone = " + item.getPhone() +
				"		role = " + item.getRole() +
				"Where userId = " + item.getUserId();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String query  = 
				"DELETE FROM User WHERE userId = " + id;
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Consultation> getConsultations(int id) {
		ConsultationService service = new ConsultationServiceImpl(con);
		ArrayList<Consultation> consultations = service.getAll();
		List<Consultation> list = consultations
				.stream()
				.filter(consultation -> consultation.getUserId()==id)
				.collect(Collectors.toList());
		return new ArrayList<Consultation>(list);
	}

	@Override
	public Role getRole(int id) {
		User user = get(id);
		return user.getRole();
	}

	@Override
	public ArrayList<PerscriptionWarning> getWarnings(int id) {
		WarningService service = new WarningServiceImpl(con);
		ArrayList<PerscriptionWarning> consultations = service.getAll();
		List<PerscriptionWarning> list = consultations
				.stream()
				.filter(warning -> warning.getUser()==id)
				.collect(Collectors.toList());
		return new ArrayList<PerscriptionWarning>(list);
	}
	@Override
	public void setConnection(Connection con) {
		this.con = con;
		
	}
}
