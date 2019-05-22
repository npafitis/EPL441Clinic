package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Allergy;
import cy.ac.ucy.epl441.model.Treatment;
import cy.ac.ucy.epl441.model.service.TreatmentService;

/**
* An implementation of the treatmentService which offers update, delete and retrieval from the database.
* 
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class TreatmentServiceImpl implements TreatmentService {
	public TreatmentServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	private Connection con;
	
	@Override
	public void create(Treatment item) {
		String query = 	"INSERT INTO Treatment (patientId, description, treatmentDate)\n" + 
				String.format("VALUES (%d, %s, %s)",
						item.getPatientId(),
						item.getDescription(),
						item.getDate().toString()
				);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public TreatmentServiceImpl() {
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
	public ArrayList<Treatment> getAll() {
		String query = "SELECT * FROM Treatment WHERE ";
		ArrayList<Treatment> list = new ArrayList<Treatment>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Treatment treatment = 
						new Treatment(
								rs.getInt(0),
								rs.getInt(1),
								rs.getString(2),
								rs.getDate(3)
								);
				list.add(treatment);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Treatment get(int id) {
		String query = "SELECT * FROM Treatment where treatmentId = "+id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Treatment treatment = 
						new Treatment(
								rs.getInt(0),
								rs.getInt(1),
								rs.getString(2),
								rs.getDate(3)
								);
				return treatment;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Treatment item) {
		String query = 
				"UPDATE Treatment" +
				"Set 	patientId = " + item.getPatientId() +
				"		description = " + item.getDescription() +
				"		treatmentDate = " + item.getDate().toString() +
				"Where treatmentId = " + item.getTreatmentId();
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
				"DELETE FROM Treatment WHERE treatmentId = " + id;
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
