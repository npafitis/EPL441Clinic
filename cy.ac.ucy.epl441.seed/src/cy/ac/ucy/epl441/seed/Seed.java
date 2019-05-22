package cy.ac.ucy.epl441.seed;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.model.*;
import cy.ac.ucy.epl441.model.service.*;

@Component
public class Seed {
	
	@Reference
	private DataSourceFactory dsFactory;
	
	@Reference
	private UserService userService;
	
	@Reference
	private PatientService patientService;
	
	@Reference
	private RoleService roleService;
	
	@Reference
	private RelativeService relativeService;
	
	@Reference
	private ConsultationService consultationService;
	
	@Reference
	private AllergyService allergyService;
	
	@Reference
	private TreatmentService treatmentService;
	
	@Reference
	private IncidentService incidentService;
	
	@Reference
	private DiagnosisService diagnosisService;
	
	private Connection con;
	
	@Activate
	private void activate() {
		Properties properties = new Properties();
		properties.put(DataSourceFactory.JDBC_URL, "jdbc:mysql://localhost:33061/homestead");
		properties.put(DataSourceFactory.JDBC_USER, "homestead");
		properties.put(DataSourceFactory.JDBC_PASSWORD, "secret");


		try {
			DataSource ds = this.dsFactory.createDataSource(properties);
			Connection con = ds.getConnection();
			this.con = con;
			this.userService.setConnection(con);
			this.patientService.setConnection(con);
			this.roleService.setConnection(con);
			this.relativeService.setConnection(con);
			this.consultationService.setConnection(con);
			this.allergyService.setConnection(con);
			this.treatmentService.setConnection(con);
			this.incidentService.setConnection(con);
			this.diagnosisService.setConnection(con);
			
			System.out.println("Connection ok");
			this.seedAllergy();
			System.out.println("Seeded Allergies");
			this.seedRole();
			System.out.println("Seeded Roles");
			this.seedUser();
			System.out.println("Seeded Users");
			this.seedPatient();
			System.out.println("Seeded Patients");
			this.seedRelative();
			System.out.println("Seeded Relatives");
			this.seedConsultation();
			System.out.println("Seeded Consultations");
			this.seedTreatment();
			System.out.println("Seeded Treatments");
			this.seedIncident();
			System.out.println("Seeded Incidents");
			this.seedDiagnosis();
			System.out.println("Seeded Diagnosis");
			this.seedRelations();
			System.out.println("Seeded Relations");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private void seedRole() {
		for(Role role:Role.values()) {
			this.roleService.create(role);
		}
	}
	
	private void seedUser() {
		User user = new User("Dr Chris", "chris@mail.com", "99123123", Role.ClinicalStaff, "1234");
		this.userService.create(user);
		user = new User("Mary", "mary@mail.com", "99123456", Role.Receptionist,"1234");
		this.userService.create(user);
		user = new User("Kotsios", "kotsios@mail.com", "99112233", Role.MedicalRecordsStaff, "1234");
		this.userService.create(user);
		user = new User("Andri", "andri@mail.com", "99111222", Role.HealthServiceManagement, "1234");
		this.userService.create(user);
	}
	
	private void seedPatient() {
		Patient patient = new Patient("Nikolas", "nikolas@mail.com", "99112233", true, "Status 1");
		this.patientService.create(patient);
		patient = new Patient("Koulla", "koulla@mail.com", "99121212", false,  "Status 2");
		this.patientService.create(patient);
		patient= new Patient("Antonis", "antonis@mail.com", "99129129", true, "Status 3");
		this.patientService.create(patient);
	}
	
	private void seedRelative() {
		Relative relative = new Relative(1, "Antonakis", "antonakis@mail.com", "99000111");
		this.relativeService.create(relative);
		relative = new Relative(2, "Maroulla", "maroulla@mail.com", "999333444");
		this.relativeService.create(relative);
		relative = new Relative(3, "Kyriakos", "kyriakos@mail.com", "97121212");
		this.relativeService.create(relative);
	}
	
	private void seedConsultation() {
		Consultation consultation = new Consultation(1, 1, new Date(System.currentTimeMillis()-24*60*60*1000), true);
		this.consultationService.create(consultation);
		consultation = new Consultation(1, 2, new Date(System.currentTimeMillis()-24*60*60*1000), false);
		this.consultationService.create(consultation);
		consultation = new Consultation(1, 2, new Date(System.currentTimeMillis()+24*60*60*1000), false);
		this.consultationService.create(consultation);		
	}
	
	private void seedAllergy() {
		Allergy allergy = new Allergy("Penicilin");
		this.allergyService.create(allergy);
		allergy = new Allergy("Sulfonamides");
		this.allergyService.create(allergy);
		allergy = new Allergy("NSAIDs");
		this.allergyService.create(allergy);
	}
	
	private void seedTreatment() {
		Treatment treatment = new Treatment(1,"Psychofarmaka", new Date(System.currentTimeMillis()));
		this.treatmentService.create(treatment);
		treatment = new Treatment(2, "Antibiosis", new Date(System.currentTimeMillis()-24*60*60*1000));
		this.treatmentService.create(treatment);
		treatment = new Treatment(3, "Aspirin", new Date(System.currentTimeMillis()+24*60*60*1000));
		this.treatmentService.create(treatment);
	}
	
	private void seedIncident() {
		Incident incident = new Incident(1, new Date(System.currentTimeMillis()),"Escaped");
		this.incidentService.create(incident);
		incident = new Incident(3, new Date(System.currentTimeMillis()),"Got electrocuted");
		this.incidentService.create(incident);
	}
	
	private void seedRelations() {
		this.patientService.addAllergy(this.patientService.get(1), this.allergyService.get(1));
		this.treatmentService.addAllergy(this.treatmentService.get(1), this.allergyService.get(1));
		this.treatmentService.addAllergy(this.treatmentService.get(3), this.allergyService.get(3));

	}
	
	private void seedDiagnosis() {
		this.diagnosisService.create(new Diagnosis(1, "Crazy", "Needs Psychofarmaka", new Date(System.currentTimeMillis())));
		this.diagnosisService.create(new Diagnosis(3, "Beard", "Needs Shaving", new Date(System.currentTimeMillis())));
	}
}
