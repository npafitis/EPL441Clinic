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
				String.format("VALUES (%d, \"%s\", \"%s\")",
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

	@Override
	public ArrayList<Treatment> getAll() {
		String query = "SELECT * FROM Treatment";
		ArrayList<Treatment> list = new ArrayList<Treatment>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Treatment treatment = 
						new Treatment(
								rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getDate(4)
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
								rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getDate(4)
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
				"		description = \"" + item.getDescription() + "\""+
				"		treatmentDate = \"" + item.getDate().toString() +"\""+
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

	@Override
	public ArrayList<Allergy> getAllergies(Treatment treatment) {
		String query = "SELECT allergyId FROM TreatmentAllergy where treatmentId="+treatment.getTreatmentId();
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int allergyId = rs.getInt(0);
				list.add(allergyId);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<Allergy> allergies = new ArrayList<Allergy>();
		AllergyServiceImpl service = new AllergyServiceImpl(con);
		for(int i:list) {
			allergies.add(service.get(i));
		}
		return allergies;
	}

	@Override
	public void addAllergy(Treatment treatment, Allergy allergy) {
		String query = 
				String.format("INSERT INTO TreatmentAllergy (treatmentId, allergyId)\n" + 
						"values (%d,%d)", treatment.getTreatmentId(), allergy.getAllergyId());
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
