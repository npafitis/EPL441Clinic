package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Allergy;
import cy.ac.ucy.epl441.model.Consultation;
import cy.ac.ucy.epl441.model.Diagnosis;
import cy.ac.ucy.epl441.model.Incident;
import cy.ac.ucy.epl441.model.InformationChanges;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.Relative;
import cy.ac.ucy.epl441.model.Treatment;
import cy.ac.ucy.epl441.model.User;
import cy.ac.ucy.epl441.model.service.PatientService;

/**
* An implementation of the patientService which offers update, delete and retrieval from the database.
* 
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class PatientServiceImpl implements PatientService {
	private Connection con;

	@Override
	public void create(Patient item) {
		String query = 	"INSERT INTO Patient (name, email, phone, selfHarm, status)\n" + 
				String.format("VALUES (\"%s\", \"%s\", \"%s\", %s, \"%s\")",
						item.getName(),
						item.getEmail(),
						item.getPhone(),
						new Boolean(item.isSelfHarm()).toString(),
						item.getStatus()
				);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public PatientServiceImpl() {
		super();
	}

	public PatientServiceImpl(Connection con) {
		super();
		this.con = con;
	}
	/**
	* Retrieves the whole table from the database in an Arraylist
	* 
	* @author  npafitis
	* @version 1.0
	* @since   2019-05-21 
	*/
	@Override
	public ArrayList<Patient> getAll() {
		String query = "SELECT * FROM Patient";
		ArrayList<Patient> list = new ArrayList<Patient>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Patient p = 
						new Patient(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getBoolean(5),
								rs.getString(6)
								);
				list.add(p);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Patient get(int id) {
		String query = "SELECT * FROM Patient";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Patient p = 
						new Patient(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getBoolean(5),
								rs.getString(6)
								);
				return p;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Patient item) {
		String query = 
				"UPDATE Treatment" +
				"Set 	name = \"" + item.getName()+ "\""+
				"		email = \"" + item.getEmail() +"\""+
				"		phone = \"" + item.getPhone() +"\""+
				"		selfHarm = " + new Boolean(item.isSelfHarm()).toString()+
				"		status= \"" + item.getStatus() + "\""+	
				"Where patientId = " + item.getPatientId();
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
				"DELETE FROM Patient WHERE patientId = " + id;
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Treatment> getTreatments(int id) {
		TreatmentServiceImpl tservice = new TreatmentServiceImpl(con);
		ArrayList<Treatment> list = tservice.getAll();
		ArrayList<Treatment> ret;
		ret = new ArrayList<Treatment>(list
				.stream()
				.filter(treatment -> treatment.getPatientId() == id)
				.collect(Collectors.toList()));
		return ret;
	}

	@Override
	public ArrayList<Allergy> getAllergies(int id) {
		AllergyServiceImpl service = new AllergyServiceImpl(con);
		ArrayList<Allergy> allergies = new ArrayList<Allergy>();
		String query  = 
				"SELECT * FROM PatientAllergy"
			  + "WHERE patientId = " + id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				allergies.add(service.get(rs.getInt("allergyId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allergies;
	}

	@Override
	public ArrayList<Relative> getRelatives(int id) {
		RelativeServiceImpl service = new RelativeServiceImpl(con);
		ArrayList<Relative> relatives = new ArrayList<Relative>(
				service
				.getAll()
				.stream()
				.filter(relative -> relative.getPatientId() == id)
				.collect(Collectors.toList())
				);
		return relatives;
	}

	@Override
	public ArrayList<InformationChanges> getInformationChanges(int id) {
		InformationChangesServiceImpl service = new InformationChangesServiceImpl(con);
		ArrayList<InformationChanges> data = new ArrayList<InformationChanges>(
				service
				.getAll()
				.stream()
				.filter(relative -> relative.getPatientId() == id)
				.collect(Collectors.toList())
				);
		return data;
	}

	@Override
	public ArrayList<Incident> getIncidents(int id) {
		IncidentServiceImpl service = new IncidentServiceImpl(con);
		ArrayList<Incident> data = new ArrayList<Incident>(
				service
				.getAll()
				.stream()
				.filter(relative -> relative.getPatientId() == id)
				.collect(Collectors.toList())
				);
		return data;
	}

	@Override
	public ArrayList<Consultation> getConsultations(int id) {
		ConsultationServiceImpl service = new ConsultationServiceImpl(con);
		ArrayList<Consultation> data = new ArrayList<Consultation>(
				service
				.getAll()
				.stream()
				.filter(relative -> relative.getPatientId() == id)
				.collect(Collectors.toList())
				);
		return data;
	}

	@Override
	public ArrayList<Diagnosis> getDiagnosis(int id) {
		DiagnosisServiceImpl service = new DiagnosisServiceImpl(con);
		ArrayList<Diagnosis> data = new ArrayList<Diagnosis>(
				service
				.getAll()
				.stream()
				.filter(diagnosis -> diagnosis.getPatientId() == id)
				.collect(Collectors.toList())
				);
		return data;

	}
	@Override
	public void setConnection(Connection con) {
		this.con = con;
		
	}

	@Override
	public void addAllergy(Patient patient, Allergy allergy) {
		String query = 
				String.format("INSERT INTO PatientAllergy (patientId, allergyId)\n" + 
						"values (%d,%d)", patient.getPatientId(), allergy.getAllergyId());
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
