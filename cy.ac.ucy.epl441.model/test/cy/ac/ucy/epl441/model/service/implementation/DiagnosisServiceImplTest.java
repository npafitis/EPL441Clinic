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

import cy.ac.ucy.epl441.model.Diagnosis;
import junit.framework.TestCase;

public class DiagnosisServiceImplTest extends TestCase {

	private Connection con;
	private DiagnosisServiceImpl service;
	
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
		
		this.service = new DiagnosisServiceImpl();
		this.con = ds.getConnection();
		this.service.setConnection(con);
    }
	
	@Test
	public void testGetAll() {
		ArrayList<Diagnosis> allergies = this.service.getAll();
		assertEquals(2, allergies.size());
	}
	
	@Test
	public void testGet() {
		int id = 1;
		Diagnosis diagnosis = this.service.get(id);
		assertNotNull(diagnosis);
		assertEquals(id, diagnosis.getDiagnosisId());
	}
	
	@Test
	public void testUpdate() {
		Diagnosis diagnosis = this.service.get(1);
		diagnosis.setDetails("Something Else");
		this.service.update(diagnosis);
		try {
			TimeUnit.SECONDS.sleep(2);
			diagnosis = this.service.get(1);
		} catch (InterruptedException e) {
			fail("Exception thrown");
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testDelete() {
		int size = this.service.getAll().size();
		this.service.delete(2);
		try {
			TimeUnit.SECONDS.sleep(2);
			assertEquals(size-1, this.service.getAll().size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testCreate() {
		Diagnosis diagnosis = new Diagnosis(2, "Lera", "Needs Bath", new Date(System.currentTimeMillis()));
		int size = this.service.getAll().size();
		this.service.create(diagnosis);
		try {
			TimeUnit.SECONDS.sleep(2);
			assertEquals(size+1,this.service.getAll().size());
			diagnosis = this.service.get(size+1);
			assertEquals(diagnosis.getDetails(),"Lera");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
