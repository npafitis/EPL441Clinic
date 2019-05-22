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

import cy.ac.ucy.epl441.model.Consultation;
import junit.framework.TestCase;

public class ConsultationServiceImplTest extends TestCase {

	private Connection con;
	private ConsultationServiceImpl service;
	
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
		this.service = new ConsultationServiceImpl();
		this.con = ds.getConnection();
		this.service.setConnection(this.con);
    }
	
//	@Test
//	public void testGetAll() {
//		ArrayList<Consultation> consultation = this.service.getAll();
//		assertEquals(3, consultation.size());
//	}
	
	
	
	@Test
	public void testGet() {
		int consultationId = 2;
		Consultation consultation = this.service.get(consultationId);
		assertNotNull(consultation);
		assertEquals(consultationId, consultation.getConsultationId());
	}
	
	@Test
	public void testUpdate() {
		Consultation consultation = this.service.get(2);
		boolean val = !consultation.isAttended();
		consultation.setAttended(val);
		this.service.update(consultation);
		try {
			TimeUnit.SECONDS.sleep(4);
			consultation = this.service.get(2);
			assertEquals(false, consultation.isAttended());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDelete() {
		int size = this.service.getAll().size();
		this.service.delete(1);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(size-1, this.service.getAll().size());
	}
	
	
	@Test
	public void testCreate() {
		Consultation consultation = new Consultation(1,1,new Date(System.currentTimeMillis()),false);
		int size = this.service.getAll().size();
		this.service.create(consultation);
		try {
			TimeUnit.SECONDS.sleep(2);
			assertEquals(size+1,this.service.getAll().size());
			consultation = this.service.get(size+1);
			assertEquals(consultation.getPatientId(), 1);
			assertEquals(consultation.getUserId(),1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
