package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Incident;
import cy.ac.ucy.epl441.model.service.IncidentService;

@Component
public class IncidentServiceImpl implements IncidentService {
	private Connection con;

	public IncidentServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public void create(Incident item) {
		String query = 	"INSERT INTO Incident ( patientId, incidentDate, description)\n" + 
				String.format("VALUES (%d, %s, %s)",
						item.getPatientId(),
						item.getDate().toString(),
						item.getDescription());
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Incident> getAll() {
		String query = "SELECT * FROM Incident";
		ArrayList<Incident> list = new ArrayList<Incident>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int incidentId = rs.getInt("incidentId");
				int patientId = rs.getInt("patientId");
				Date date = rs.getDate("incidentDate");
				String description = rs.getString("description");
				Incident incident = new Incident(incidentId, patientId, date,description);
				list.add(incident);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Incident get(int id) {
		String query = "SELECT * FROM Incident Where incident = "+id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int incidentId = rs.getInt("incidentId");
				int patientId = rs.getInt("patientId");
				Date date = rs.getDate("incidentDate");
				String description = rs.getString("description");
				Incident incident = new Incident(incidentId, patientId, date,description);
				return incident;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Incident item) {
		String query = 
				"UPDATE Incident" +
				"Set 	incidentDate = " + item.getDate() +
				"		description = " + item.getDescription() +
				"Where incidentId = " + item.getIncidentId();
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
				"DELETE FROM Incident WHERE incidentId = " + id;
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
