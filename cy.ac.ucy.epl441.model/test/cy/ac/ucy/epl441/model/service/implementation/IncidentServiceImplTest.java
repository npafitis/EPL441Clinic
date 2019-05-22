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
import cy.ac.ucy.epl441.model.Incident;
import junit.framework.TestCase;

public class IncidentServiceImplTest extends TestCase {
	private Connection con;
	private IncidentServiceImpl service;
	
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
		
		this.service = new IncidentServiceImpl();
		this.con = ds.getConnection();
		this.service.setConnection(con);
    }
	
	@Test
	public void testGetAll() {
		ArrayList<Incident> incidents = this.service.getAll();
		assertEquals(2, incidents.size());
	}
	
	@Test
	public void testGet() {
		int id = 1;
		Incident incident = this.service.get(id);
		assertNotNull(incident);
		assertEquals(id, incident.getIncidentId());
	}
	
	@Test
	public void testUpdate() {
		Incident incident = this.service.get(1);
		incident.setDescription("Something Else");
		this.service.update(incident);
		try {
			TimeUnit.SECONDS.sleep(2);
			incident = this.service.get(1);
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
		Incident incident = new Incident(2, new Date(System.currentTimeMillis()),"Fallen Off");
		int size = this.service.getAll().size();
		this.service.create(incident);
		try {
			TimeUnit.SECONDS.sleep(2);
			assertEquals(size+1,this.service.getAll().size());
		} catch (InterruptedException e) {
			fail("Exception thrown");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
