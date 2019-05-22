package cy.ac.ucy.epl441.model.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.osgi.service.jdbc.DataSourceFactory;

import com.mysql.cj.jdbc.MysqlDataSource;

import cy.ac.ucy.epl441.model.Allergy;
import junit.framework.TestCase;

public class AllergyServiceImplTest extends TestCase {
	
	private Connection con;
	private AllergyServiceImpl service;
	
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
		
		this.service = new AllergyServiceImpl();
		this.con = ds.getConnection();
		this.service.setConnection(con);
    }
	
	@Test
	public void testGetAll() {
		ArrayList<Allergy> allergies = this.service.getAll();
		assertEquals(3, allergies.size());
	}
	
	@Test
	public void testGet() {
		int allergyId = 1;
		Allergy allergy = this.service.get(allergyId);
		assertNotNull(allergy);
		assertEquals(allergyId, allergy.getAllergyId());
	}
	
	@Test
	public void testUpdate() {
		Allergy allergy = this.service.get(1);
		allergy.setName("Something Else");
		this.service.update(allergy);
		try {
			TimeUnit.SECONDS.sleep(2);
			allergy = this.service.get(1);
			assertEquals("Something Else", allergy.getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testDelete() {
		int size = this.service.getAll().size();
		this.service.delete(4);
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
		Allergy allergy = new Allergy("Peanuts");
		int size = this.service.getAll().size();
		this.service.create(allergy);
		try {
			TimeUnit.SECONDS.sleep(2);
			assertEquals(size+1,this.service.getAll().size());
			allergy = this.service.get(size+1);
			assertEquals(allergy.getName(),"Peanuts");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
