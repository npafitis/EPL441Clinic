package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.InformationChanges;
import cy.ac.ucy.epl441.model.service.InformationChangeService;

/**
* An implementation of the informationChangesService which offers update, delete and retrieval from the database.
* 
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class InformationChangesServiceImpl implements InformationChangeService {
	private Connection con;
	
	public InformationChangesServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public void create(InformationChanges item) {
		String query = 	"INSERT INTO InformationChange ( patientId, changeDate, oldName, oldEmail, oldPhone, newName, newEmail, newPhone, accepted)\n" + 
				String.format("VALUES (%d, %s, %s, %s, %s, %s, %s, %s, %s, %s)",
						item.getPatientId(),
						item.getDate().toString(),
						item.getOldName(),
						item.getOldEmail(),
						item.getOldPhone(),
						item.getNewName(),
						item.getNewEmail(),
						item.getNewPhone(),
						new Boolean(item.isAccepted()).toString());
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public InformationChangesServiceImpl() {
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
	public ArrayList<InformationChanges> getAll() {
		String query = "SELECT * FROM InformationChange";
		ArrayList<InformationChanges> list = new ArrayList<InformationChanges>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				InformationChanges change = 
						new InformationChanges(
								rs.getInt(0),
								rs.getInt(1),
								rs.getDate(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getString(7),
								rs.getString(8),
								rs.getBoolean(9)
								);
				list.add(change);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public InformationChanges get(int id) {
		String query = "SELECT * FROM InformationChange where changeId = "+id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				InformationChanges change = 
						new InformationChanges(
								rs.getInt(0),
								rs.getInt(1),
								rs.getDate(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getString(7),
								rs.getString(8),
								rs.getBoolean(9)
								);
				return change;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(InformationChanges item) {
		return;
	}

	@Override
	public void delete(int id) {
		return;
	}
	@Override
	public void setConnection(Connection con) {
		this.con = con;
		
	}
}
