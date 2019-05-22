package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Diagnosis;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.service.DiagnosisService;


/**
* An implementation of the diagnosisService which offers update, delete and retrieval from the database.
* 
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

public class DiagnosisServiceImpl implements DiagnosisService {
	private Connection con;
	

	public DiagnosisServiceImpl() {
		super();
	}
	
	public DiagnosisServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public void create(Diagnosis item) {
		String query = 	"INSERT INTO Diagnosis (patientId, details, comments, date)\n" + 
				String.format("VALUES (%d, \"%s\", \"%s\", \"%s\")",
						item.getPatientId(),
						item.getDetails(),
						item.getComments(),
						item.getDiagnosisDate()
				);
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
	public ArrayList<Diagnosis> getAll() {
		String query = "SELECT * FROM Diagnosis";
		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Diagnosis diagnosis = 
						new Diagnosis(
								rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getString(4),
								rs.getDate(5)
								);
				list.add(diagnosis);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Diagnosis get(int id) {
		String query = "SELECT * FROM Diagnosis where diagnosisId ="+id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Diagnosis diagnosis = 
						new Diagnosis(
								rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getString(4),
								rs.getDate(5)
								);
				return diagnosis;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Diagnosis item) {
		String query = 
				"UPDATE Diagnosis\n" +
				"Set 	patientId = " + item.getPatientId() + "\n"+
				"		details = \"" + item.getDetails() +"\""+"\n"+
				"		comments = \"" + item.getComments() +"\""+"\n"+
				" 		date = \""+item.getDiagnosisId()+"\""+"\n"+
				"Where diagnosisId = " + item.getDiagnosisId();
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
				"DELETE FROM Diagnosis WHERE diagnosisId = " + id;
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Patient getPatient(Diagnosis diagnosis) {
		PatientServiceImpl service = new PatientServiceImpl(con);
		return service.get(diagnosis.getPatientId());
	}
	@Override
	public void setConnection(Connection con) {
		this.con = con;
		
	}
}
