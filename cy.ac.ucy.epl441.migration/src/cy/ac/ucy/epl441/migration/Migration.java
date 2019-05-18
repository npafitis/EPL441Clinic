package cy.ac.ucy.epl441.migration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.*;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.model.service.UserService;
import cy.ac.ucy.epl441.model.service.implementation.UserServiceImpl;

@Component
public class Migration {
	
	private static final String DROP_ROLE_TABLE = "DROP TABLE Role";
	private static final String DROP_USER_TABLE = "DROP TABLE User";
	private static final String DROP_WARNING_TABLE = "DROP TABLE Warning";
	private static final String DROP_PATIENT_TABLE = "DROP TABLE Patient";
	private static final String DROP_CONSULTATION_TABLE = "DROP TABLE Consultation";
	private static final String DROP_INCIDENT_TABLE = "DROP TABLE Incident";
	private static final String DROP_INFORMATIONCHANGE_TABLE = "DROP TABLE InformationChange";
	private static final String DROP_RELATIVE_TABLE = "DROP TABLE Relative";
	private static final String DROP_ALLERGY_TABLE = "DROP TABLE Allergy";
	private static final String DROP_TREATMENT_TABLE = "DROP TABLE Treatment";
	private static final String DROP_TREATMENTALLERGY_TABLE = "DROP TABLE TreatmentAllergy";
	private static final String DROP_PATIENTALLERGY_TABLE = "DROP TABLE PatientAllergy";
	private static final String DROP_DIAGNOSIS_TABLE = "DROP TABLE Diagnosis";


	
	private static final String CREATE_ROLE_TABLE = 
			"CREATE TABLE Role (\n" + 
			"    role VARCHAR(255) not NULL unique,\n" + 
			"    PRIMARY KEY (role)\n" + 
			")";
	
	
	private static final String CREATE_USER_TABLE = 
			"CREATE TABLE User (\n" + 
			"    userId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    name VARCHAR(255) not NULL,\n" + 
			"    email VARCHAR(255) not NULL unique,\n" + 
			"    phone VARCHAR(15) not NULL,\n" + 
			"    role VARCHAR(255) not NULL,\n" + 
			"    PRIMARY KEY (userId),\n" + 
			"    FOREIGN KEY (role) REFERENCES Role(role)\n" + 
			")";
	
	private static final String CREATE_WARNING_TABLE = 
			"CREATE TABLE Warning (\n" + 
			"    warningId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    userId INTEGER not NULL,\n" + 
			"    warningDate DATE not NULL,\n" + 
			"    description VARCHAR(255),\n" + 
			"    PRIMARY KEY (warningId),\n" + 
			"    FOREIGN KEY (userId) REFERENCES User(userId)\n" + 
			")";
	
	private static final String CREATE_PATIENT_TABLE = 
			"CREATE TABLE Patient (\n" + 
			"    patientId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    name VARCHAR(255) not NULL,\n" + 
			"    email VARCHAR(255) not NULL,\n" + 
			"    phone VARCHAR(15) not NULL,\n" + 
			"    selfHarm BOOLEAN DEFAULT false,\n" + 
			"    PRIMARY KEY (patientId)\n" + 
			")";
	
	private static final String CREATE_CONSULTATION_TABLE = 
			"CREATE TABLE Consultation (\n" + 
			"    consultationId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    userId INTEGER not NULL,\n" + 
			"    patientId INTEGER not NULL,\n" + 
			"    consultationDate DATE not NULL,\n" + 
			"    attended BOOLEAN DEFAULT false,\n" + 
			"    PRIMARY KEY (consultationId),\n" + 
			"    FOREIGN KEY (userId) REFERENCES User(userId),\n" + 
			"    FOREIGN KEY (patientId) REFERENCES Patient(patientId)\n" + 
			")";
	private static final String CREATE_INCIDENT_TABLE = 
			"CREATE TABLE Incident (\n" + 
			"    incidentId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    patientId INTEGER not NULL,\n" + 
			"    incidentDate DATE not NULL,\n" + 
			"    description VARCHAR(255) not NULL,\n" + 
			"    PRIMARY KEY (incidentId),\n" + 
			"    FOREIGN KEY (patientId) REFERENCES Patient(patientId)\n" + 
			")";
	private static final String CREATE_INFORMATIONCHANGE_TABLE = 
			"CREATE TABLE InformationChange (\n" + 
			"    changeId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    patientId INTEGER not NULL,\n" + 
			"    changeDate DATE not NULL,\n" + 
			"    newName VARCHAR(255),\n" + 
			"    newEmail VARCHAR(255),\n" + 
			"    newPhone VARCHAR(15),\n" + 
			"    oldName VARCHAR(255),\n" + 
			"    oldEmail VARCHAR(255),\n" + 
			"    oldPhone VARCHAR(255),\n" + 
			"    accepted BOOLEAN DEFAULT false,\n" + 
			"    PRIMARY KEY (changeId),\n" + 
			"    FOREIGN KEY (patientId) REFERENCES Patient(patientId)\n" + 
			")";
	private static final String CREATE_RELATIVE_TABLE = 
			"CREATE TABLE Relative (\n" + 
			"    relativeId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    patientId INTEGER not NULL,\n" + 
			"    name VARCHAR(255) not NULL,\n" + 
			"    email VARCHAR(255) not NULL,\n" + 
			"    phone VARCHAR(15) not NULL,\n" + 
			"    PRIMARY KEY (relativeId),\n" + 
			"    FOREIGN KEY (patientId) REFERENCES Patient(patientId)\n" + 
			")";
	
	private static final String CREATE_ALLERGY_TABLE = 
			"CREATE TABLE Allergy (\n" + 
			"    allergyId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    name VARCHAR(255),\n" + 
			"    PRIMARY KEY (allergyId)\n" + 
			")";
	
	private static final String CREATE_TREATMENT_TABLE = 
			"CREATE TABLE Treatment (\n" + 
			"    treatmentId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    patientId INTEGER not NULL,\n" + 
			"    description VARCHAR(255),\n" + 
			"    treatmentDate DATE not NULL,\n" + 
			"    PRIMARY KEY (treatmentId),\n" + 
			"    FOREIGN KEY (patientId) REFERENCES Patient(patientId)\n" + 
			")";
	
	private static final String CREATE_TREATMENTALLERGY_TABLE = 
			"CREATE TABLE TreatmentAllergy (\n" + 
			"    treatmentId INTEGER not NULL,\n" + 
			"    allergyId INTEGER not NULL,\n" + 
			"    PRIMARY KEY (treatmentId,allergyId),\n" + 
			"    FOREIGN KEY (treatmentId) REFERENCES Treatment(treatmentId),\n" + 
			"    FOREIGN KEY (allergyId) REFERENCES Allergy(allergyId)\n" + 
			")";
	private static final String CREATE_PATIENTALLERGY_TABLE = 
			"CREATE TABLE PatientAllergy (\n" + 
			"    patientId INTEGER not NULL,\n" + 
			"    allergyId INTEGER not NULL,\n" + 
			"    PRIMARY KEY (patientId, allergyId),\n" + 
			"    FOREIGN KEY (allergyId) REFERENCES Allergy(allergyId),\n" + 
			"    FOREIGN KEY (patientId) REFERENCES Patient(patientId)\n" + 
			")";
	
	private static final String CREATE_DIAGNOSIS_TABLE = 
			"CREATE TABLE Diagnosis (\n" + 
			"    diagnosisId INTEGER not NULL AUTO_INCREMENT,\n" + 
			"    patientId INTEGER not NULL,\n" + 
			"    details VARCHAR(255),\n" + 
			"    comments VARCHAR(255),\n" + 
			"    PRIMARY KEY (diagnosisId),\n" + 
			"    FOREIGN KEY (patientId) REFERENCES Patient(patientId)\n" + 
			")";
	
	@Reference
	private DataSourceFactory dsFactory;
	
	@Activate
	private void activate() {
		Properties properties = new Properties();
		properties.put(DataSourceFactory.JDBC_URL, "jdbc:mysql://localhost:33061/homestead");
		properties.put(DataSourceFactory.JDBC_USER, "homestead");
		properties.put(DataSourceFactory.JDBC_PASSWORD, "secret");


		try {
			DataSource ds = this.dsFactory.createDataSource(properties);
			Connection con = ds.getConnection();
			this.dropTables(con);
			this.createTables(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private void dropTables(Connection conn) {
		try {
			Statement stmt = conn.createStatement();

			if(tableExist(conn, "TreatmentAllergy")) {
				stmt.executeUpdate(DROP_TREATMENTALLERGY_TABLE);
				System.out.println("Dropped TreatmentAllergy Table.");
			}
			if(tableExist(conn, "PatientAllergy")) {
				stmt.executeUpdate(DROP_PATIENTALLERGY_TABLE);
				System.out.println("Dropped PatientAllergy Table.");
			}

			if(tableExist(conn, "Warning")) {
				stmt.executeUpdate(DROP_WARNING_TABLE);
				System.out.println("Dropped Warning Table.");
			}
			if(tableExist(conn, "Consultation")) {
				stmt.executeUpdate(DROP_CONSULTATION_TABLE);
				System.out.println("Dropped Consultation Table.");
			}
			if(tableExist(conn, "Incident")) {
				stmt.executeUpdate(DROP_INCIDENT_TABLE);
				System.out.println("Dropped Incident Table.");
			}
			if(tableExist(conn, "Treatment")) {
				stmt.executeUpdate(DROP_TREATMENT_TABLE);
				System.out.println("Dropped Treatment Table.");
			}
			if(tableExist(conn, "Diagnosis")) {
				stmt.executeUpdate(DROP_DIAGNOSIS_TABLE);
				System.out.println("Dropped Diagnosis Table.");
			}
			if(tableExist(conn, "InformationChange")) {
				stmt.executeUpdate(DROP_INFORMATIONCHANGE_TABLE);
				System.out.println("Dropped InformationChange Table.");
			}
			if(tableExist(conn, "Relative")) {
				stmt.executeUpdate(DROP_RELATIVE_TABLE);
				System.out.println("Dropped Relative Table.");
			}

			if(tableExist(conn, "User")) {
				stmt.executeUpdate(DROP_USER_TABLE);
				System.out.println("Dropped User Table.");
			}
			if(tableExist(conn, "Role")) {
				stmt.executeUpdate(DROP_ROLE_TABLE);
				System.out.println("Dropped Role Table.");
			}
			if(tableExist(conn, "Allergy")) {
				stmt.executeUpdate(DROP_ALLERGY_TABLE);
				System.out.println("Dropped Allergy Table.");
			}

			if(tableExist(conn, "Patient")) {
				stmt.executeUpdate(DROP_PATIENT_TABLE);
				System.out.println("Dropped Patient Table.");
			}

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createTables(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(CREATE_PATIENT_TABLE);
			System.out.println("Created Patient Table.");
			stmt.executeUpdate(CREATE_ALLERGY_TABLE);
			System.out.println("Created Allergy Table.");
			stmt.executeUpdate(CREATE_ROLE_TABLE);
			System.out.println("Created Role Table.");
			stmt.executeUpdate(CREATE_USER_TABLE);
			System.out.println("Created User Table.");
			stmt.executeUpdate(CREATE_WARNING_TABLE);
			System.out.println("Created Warning Table.");
			stmt.executeUpdate(CREATE_CONSULTATION_TABLE);
			System.out.println("Created Consultation Table.");
			stmt.executeUpdate(CREATE_INCIDENT_TABLE);
			System.out.println("Created Incident Table.");
			stmt.executeUpdate(CREATE_TREATMENT_TABLE);
			System.out.println("Created Treatment Table.");
			stmt.executeUpdate(CREATE_DIAGNOSIS_TABLE);
			System.out.println("Created Diagnosis Table.");
			stmt.executeUpdate(CREATE_INFORMATIONCHANGE_TABLE);
			System.out.println("Created InformationChange Table.");
			stmt.executeUpdate(CREATE_RELATIVE_TABLE);
			System.out.println("Created Relative Table.");
			stmt.executeUpdate(CREATE_TREATMENTALLERGY_TABLE);
			System.out.println("Created TreatmentAllergy Table.");
			stmt.executeUpdate(CREATE_PATIENTALLERGY_TABLE);
			System.out.println("Created PatientAllergy Table.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static boolean tableExist(Connection conn, String tableName) throws SQLException {
	    boolean tExists = false;
	    try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
	        while (rs.next()) { 
	            String tName = rs.getString("TABLE_NAME");
	            if (tName != null && tName.equals(tableName)) {
	                tExists = true;
	                break;
	            }
	        }
	    }
	    return tExists;
	}

}
