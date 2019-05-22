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

/**
* An implementation of the allergyService which offers update, delete and retrieval from the database.
* 
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class AllergyServiceImpl implements AllergyService  {
	public AllergyServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	public Connection con;
	
	@Override
	public void create(Allergy item) {
		String query = 
				"INSERT INTO Allergy (name)\n" + 
				"values (\""+item.getName()+"\")";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public AllergyServiceImpl() {
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
	public ArrayList<Allergy> getAll() {
		String query = "SELECT * FROM Allergy";
		ArrayList<Allergy> list = new ArrayList<Allergy>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Allergy allergy = 
						new Allergy(
								rs.getInt(1),
								rs.getString(2)
								);
				list.add(allergy);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	* Retrieves an instance from the database using the id.
	* 
	* @author  npafitis
	* @version 1.0
	* @since   2019-05-21 
	*/

	@Override
	public Allergy get(int id) {
		String query = "SELECT * FROM Allergy WHERE allergyId = " + id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Allergy allergy = 
						new Allergy(
								rs.getInt(1),
								rs.getString(2)
								);
				return allergy;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	* Updates an instance on the database.
	* 
	* @author  npafitis
	* @version 1.0
	* @since   2019-05-21 
	*/
	@Override
	public void update(Allergy item) {
		String query = 
				"update Allergy\n" + 
				"set name = \""+item.getName()+"\"\n" + 
				"where allergyId = " + item.getAllergyId();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	* Deletes an instance from the database using the id.
	* 
	* @author  npafitis
	* @version 1.0
	* @since   2019-05-21 
	*/
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
	/**
	* Retrieves the patients for the given instance.
	* 
	* @author  npafitis
	* @version 1.0
	* @since   2019-05-21 
	*/
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

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

}
