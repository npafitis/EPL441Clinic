package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Relative;
import cy.ac.ucy.epl441.model.service.RelativeService;

/**
* An implementation of the relativeService which offers update, delete and retrieval from the database.
* 
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class RelativeServiceImpl implements RelativeService {
	private Connection con;

	public RelativeServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public void create(Relative item) {
		String query = 	"INSERT INTO Relative (patientId,name, email, phone, role)\n" + 
				String.format("VALUES (%d, %s, %s, %s,%s,%s)",
						item.getPatientId(),
						item.getName(),
						item.getEmail(),
						item.getPhone()
				);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public RelativeServiceImpl() {
		super();
	}
	/**
	* Retrieves the whole table from the database in an Arraylist
	* 
	* @author  npafitis
	* @version 1.0
	* @since   2019-05-21 
	*/
	@Override
	public ArrayList<Relative> getAll() {
		String query = "SELECT * FROM Relative";
		ArrayList<Relative> list = new ArrayList<Relative>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Relative relative = 
						new Relative(
								rs.getInt(0),
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4)
								);
				list.add(relative);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Relative get(int id) {
		String query = "SELECT * FROM Relative where relativeId = " + id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Relative relative = 
						new Relative(
								rs.getInt(0),
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4)
								);
				return relative;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Relative item) {
		String query = 
				"UPDATE Relative" +
				"Set 	patientId = " + item.getPatientId() +
				"		name = " + item.getName() +
				"		email = " + item.getEmail() +
				"		phone = " + item.getPhone() +
				"Where relativeId = " + item.getRelativeId();
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
				"DELETE FROM Relative WHERE relativeId = " + id;
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setConnection(Connection con) {
		this.con = con;
		
	}

}
