package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Consultation;
import cy.ac.ucy.epl441.model.service.ConsultationService;

/**
* An implementation of the consultationService which offers update, delete and retrieval from the database.
* 
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class ConsultationServiceImpl implements ConsultationService {
	private Connection con;
	
	public ConsultationServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	public ConsultationServiceImpl() {
		super();
	}

	@Override
	public void create(Consultation item) {
		String query = 	"INSERT INTO Consultation (userId, patientId, consultationDate, attended)\n" + 
				String.format("VALUES (%d, %d, %s, %s,%s,%s)",
						item.getUserId(),
						item.getPatientId(),
						item.getDate().toString(),
						Boolean.valueOf(item.isAttended()).toString());
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
	public ArrayList<Consultation> getAll() {
		String query = "SELECT * FROM Consultation";
		ArrayList<Consultation> list = new ArrayList<Consultation>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int consultationId = rs.getInt("consultationId");
				int userId = rs.getInt("userId");
				int patientId = rs.getInt("patientId");
				Date date = rs.getDate("consultationDate");
				boolean attended = rs.getBoolean("attended");
				Consultation consultation = new Consultation(consultationId, userId, patientId, date, attended);
				list.add(consultation);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Consultation get(int id) {
		String query = "SELECT * FROM Consultation Where consultationId = " + id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int consultationId = rs.getInt("consultationId");
				int userId = rs.getInt("userId");
				int patientId = rs.getInt("patientId");
				Date date = rs.getDate("date");
				boolean attended = rs.getBoolean("attended");
				Consultation consultation = new Consultation(consultationId, userId, patientId, date, attended);
				return consultation;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Consultation item) {
		String query = 
				"UPDATE User" +
				"Set 	consultationDate = " + item.getDate() +
				"		attended = " + item.isAttended() +
				"Where consultationId = " + item.getConsultationId();
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
				"DELETE FROM Consultation WHERE consultationId = " + id;
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
