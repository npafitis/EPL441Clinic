package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Allergy;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.service.AllergyService;

@Component
public class AllergyServiceImpl implements AllergyService {
	public AllergyServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	private Connection con;
	
	@Override
	public void create(Allergy item) {
		String query = 	"INSERT INTO Allergy (name)\n" + 
				String.format("VALUES (%s)",
						item.getName()
				);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Allergy> getAll() {
		String query = "SELECT * FROM Allergy";
		ArrayList<Allergy> list = new ArrayList<Allergy>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Allergy allergy = 
						new Allergy(
								rs.getInt(0),
								rs.getString(1)
								);
				list.add(allergy);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Allergy get(int id) {
		String query = "SELECT * FROM Allergy WHERE allergyId = " + id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Allergy allergy = 
						new Allergy(
								rs.getInt(0),
								rs.getString(1)
								);
				return allergy;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Allergy item) {
		String query = 
				"UPDATE Allergy" +
				"Set 	name = " + item.getName() +
				"Where allergyId = " + item.getAllergyId();
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
				"DELETE FROM Allergy WHERE allergyId = " + id;
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Patient> getPatients(Allergy allergy) {
		PatientServiceImpl service = new PatientServiceImpl(con);
		ArrayList<Patient> patients = new ArrayList<Patient>();
		String query  = 
				"SELECT * FROM PatientAllergy"
			  + "WHERE allergyId = " + allergy.getAllergyId();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				patients.add(service.get(rs.getInt("patientId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;

	}

}
