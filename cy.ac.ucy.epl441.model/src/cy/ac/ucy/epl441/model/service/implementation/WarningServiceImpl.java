package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.PerscriptionWarning;
import cy.ac.ucy.epl441.model.service.WarningService;

/**
* An implementation of the warningService which offers update, delete and retrieval from the database.
* 
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class WarningServiceImpl implements WarningService {
	private Connection con;

	public WarningServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public void create(PerscriptionWarning item) {
		String query = 	"INSERT INTO Warning (userId, warningDate, description)\n" + 
				String.format("VALUES (%d, %s, %s)",
						item.getUser(),
						item.getDate().toString(),
						item.getDescription());
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public WarningServiceImpl() {
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
	public ArrayList<PerscriptionWarning> getAll() {
		String query = "SELECT * FROM Warning";
		ArrayList<PerscriptionWarning> list = new ArrayList<PerscriptionWarning>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int warningId = rs.getInt("warningId");
				int userId = rs.getInt("userId");
				Date date = rs.getDate("warningDate");
				String description = rs.getString("description");
				PerscriptionWarning warning = new PerscriptionWarning(warningId, userId, date,description);
				list.add(warning);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public PerscriptionWarning get(int id) {
		String query = "SELECT * FROM Warning Where warningId = "+id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int warningId = rs.getInt("warningId");
				int userId = rs.getInt("userId");
				Date date = rs.getDate("warningDate");
				String description = rs.getString("description");
				PerscriptionWarning warning = new PerscriptionWarning(warningId, userId, date,description);
				return warning;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(PerscriptionWarning item) {
		String query = 
				"UPDATE Warning" +
				"Set 	warningDate = " + item.getDate() +
				"		description = " + item.getDescription() +
				"Where warningId = " + item.getWarningId();
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
				"DELETE FROM Warning WHERE warningId = " + id;
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
