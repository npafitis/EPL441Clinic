package implementation;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.clinicalstaff.OpenNewRecord;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.service.PatientService;




public class OpenNewRecordImp implements OpenNewRecord{
	//@Reference
	private DataSourceFactory dsFactory;
	//@Reference
	private PatientService patientService;
	
	{
	Properties properties = new Properties();
	properties.put(DataSourceFactory.JDBC_URL, "jdbc:mysql://localhost:33061/homestead");
	properties.put(DataSourceFactory.JDBC_USER, "homestead");
	properties.put(DataSourceFactory.JDBC_PASSWORD, "secret");
	try {
		DataSource ds = dsFactory.createDataSource(properties);
		patientService.setConnection(ds.getConnection());
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	
	
	
	public static void main(String []args) {
		OpenNewRecordImp panel= new OpenNewRecordImp();
		
		
	}
	public OpenNewRecordImp() {
	}
	
	
	public void OpenNewRecordcreate() {
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		JPanel panel = new JPanel();
		JFrame frame=new JFrame();
		panel.setLayout(null);
		Rectangle bounds = env.getMaximumWindowBounds();
		int width = Math.max(frame.getWidth(), bounds.width);
		int height = Math.max(frame.getHeight(), bounds.height);
		panel.setSize(width,height);
		frame.setSize(width,height);

		
		JLabel title = new JLabel("Mental Health Management System");
		title.setFont(new Font("Serif", Font.PLAIN, height/25));
		title.setBounds(650,1,600,250);

		
		JLabel find= new JLabel("Find Patient");
		find.setBounds(490,250,200,50);
		find.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		ImageIcon findpat = new ImageIcon("findpatient.png");
		JButton findp= new JButton (findpat);
		findp.setBounds(510,200,50,65);
		findp.addActionListener( new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				FindPatientImp panel= new FindPatientImp();
				frame.setVisible(false);

		      
		    }
		});
		
		
		
		
		
		
		JLabel search= new JLabel("Search Records");
		search.setBounds(1200,250,200,50);
		search.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		ImageIcon searchloc = new ImageIcon("searchrecords.png");
		JButton searchlo= new JButton (searchloc);
		searchlo.setBounds(1230,200,50,65);
		
		
		JLabel patient= new JLabel("Patient ID");
		patient.setBounds(800,400,200,50);
		patient.setFont(new Font("Serif", Font.PLAIN, height/35));
		JTextField getid=new JTextField();
		getid.setBounds(930,410,400,35);
		
		
		
		
		JButton openrecord = new JButton("Open Record");
		openrecord.setBounds(800,500,400,75);
		openrecord.setFont(new Font("Serif",Font.PLAIN, height/35));
		openrecord.setBackground(Color.LIGHT_GRAY);
		openrecord.addActionListener( new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				String id= getid.getText();
				int idnum= Integer.parseInt(id);
				//tha xrisimopoiso to service gia to patient
				Patient pat=patientService.get(idnum);
				if(pat!=null) {

		        DiagnosiImp page =new DiagnosiImp();
		        page.creatediagnosi(idnum);
				frame.setVisible(false);}

		      
		    }
		});
		
		
		
		

		
		
		panel.add(openrecord);
		panel.add(getid);
		panel.setOpaque(true);
	    panel.setBackground(Color.WHITE);
	    panel.add(patient);
		panel.add(searchlo);
		panel.add(search);
		panel.add(findp);
	    panel.add(find);
		panel.add(title);
		frame.add(panel);
		frame.setVisible(true);
		}
	
	
		
		
	

}
