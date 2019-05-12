package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Role;
import cy.ac.ucy.epl441.model.service.RoleService;

@Component
public class RoleServiceImpl implements RoleService {
	private Connection con;
	public RoleServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public void create(Role item) {
		String query = 	"INSERT INTO Role (role)\n" + 
						String.format("VALUES (%s)", item.name());
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Role> getAll() {
		String query = "SELECT * FROM Role";
		ArrayList<Role> list = new ArrayList<Role>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Role role = Role.valueOf(rs.getString("role"));
				list.add(role);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Role get(int id) {
		return null;
	}

	@Override
	public void update(Role item) {
		return;
	}

	@Override
	public void delete(Role role) {
		String query  = 
				"DELETE FROM Role WHERE role = " + role.name();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
