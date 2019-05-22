package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.osgi.service.jdbc.DataSourceFactory;

import com.mysql.cj.jdbc.MysqlDataSource;

import cy.ac.ucy.epl441.model.Patient;
import junit.framework.TestCase;

public class PatientServiceImplTest extends TestCase {
	private Connection con;
	private PatientServiceImpl service;
	
	@Before
    protected void setUp() throws Exception
    {
        super.setUp();
        Properties properties = new Properties();
		properties.put(DataSourceFactory.JDBC_URL, "jdbc:mysql://localhost:33061/homestead");
		properties.put(DataSourceFactory.JDBC_USER, "homestead");
		properties.put(DataSourceFactory.JDBC_PASSWORD, "secret");
		
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL(properties.getProperty(DataSourceFactory.JDBC_URL));
		ds.setUser(properties.getProperty(DataSourceFactory.JDBC_USER));
		ds.setPassword(properties.getProperty(DataSourceFactory.JDBC_PASSWORD));
		
		this.service = new PatientServiceImpl();
		this.con = ds.getConnection();
		this.service.setConnection(con);
    }
	@Test
	public void testCreate() {
		Patient patient = new Patient("Giannis", "giannis@mail.com", "N/A", false, "Status 4");
		int size = this.service.getAll().size();
		this.service.create(patient);
		try {
			TimeUnit.SECONDS.sleep(2);
			assertEquals(size+1,this.service.getAll().size());
		} catch (InterruptedException e) {
			fail("Exception thrown");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testGetAll() {
		ArrayList<Patient> patients = this.service.getAll();
		assertEquals(3, patients.size());
	}
	
	@Test
	public void testGet() {
		int id = 1;
		Patient patient = this.service.get(id);
		assertNotNull(patient);
		assertEquals(id, patient.getPatientId());
	}
	
	@Test
	public void testUpdate() {
		Patient patient = this.service.get(3);
		patient.setName("Marios");
		this.service.update(patient);
		try {
			TimeUnit.SECONDS.sleep(2);
			patient = this.service.get(3);
		} catch (InterruptedException e) {
			fail("Exception thrown");
			e.printStackTrace();
		}
	
	}
}
