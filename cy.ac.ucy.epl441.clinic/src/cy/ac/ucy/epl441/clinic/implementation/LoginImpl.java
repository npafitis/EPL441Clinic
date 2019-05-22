package cy.ac.ucy.epl441.clinic.implementation;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.clinic.Login;
import cy.ac.ucy.epl441.clinical_staff.OpenNewRecord;
import cy.ac.ucy.epl441.health_service_view.SelectReport;
import cy.ac.ucy.epl441.model.Role;
import cy.ac.ucy.epl441.model.User;
import cy.ac.ucy.epl441.model.service.UserService;
import cy.ac.ucy.epl441.receptionist_view.Calendar;
import cy.ac.ucy.epl441.records_view.SelectPatient;


@Component 
public class LoginImpl implements Login {
	
@Reference 
private UserService userservice;

@Reference 
private  DataSourceFactory dsFactory;

@Reference
private OpenNewRecord openNewRecord;

@Reference 
private SelectReport selectReport;

@Reference
private SelectPatient selectPatient;

@Reference
private Calendar calendar;

private ArrayList<User> users= new ArrayList<>();
private Role role;

public JFrame frame;
	

	@Activate
	private void activate() {
		Properties properties = new Properties();
		properties.put(DataSourceFactory.JDBC_URL, "jdbc:mysql://localhost:33061/homestead");
		properties.put(DataSourceFactory.JDBC_USER, "homestead");
		properties.put(DataSourceFactory.JDBC_PASSWORD, "secret");
		try {
			DataSource ds = dsFactory.createDataSource(properties);
			userservice.setConnection(ds.getConnection());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void create() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		JPanel panel = new JPanel();
		JFrame frame=new JFrame();
		this.frame = frame;
		panel.setLayout(null);
		Rectangle bounds = env.getMaximumWindowBounds();
		int width = Math.max(frame.getWidth(), bounds.width);
		int height = Math.max(frame.getHeight(), bounds.height);
		panel.setSize(width,height);
		frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		JLabel title = new JLabel("Mental Health Management System");
		title.setFont(new Font("Serif", Font.PLAIN, height/25));
		title.setBounds(650,1,600,250);
		
		JLabel username= new JLabel("Username:");
		username.setBounds(750,400,200,50);
		username.setFont(new Font("Serif", Font.PLAIN, height/35));
		JTextField getusername=new JTextField();
		getusername.setBounds(930,410,400,35);
		
		JLabel password= new JLabel("Password:");
		password.setBounds(750,500,200,50);
		password.setFont(new Font("Serif", Font.PLAIN, height/35));
		JTextField getpass=new JTextField();
		getpass.setBounds(930,510,400,35);
		
		
		
		JButton login = new JButton("Login");
		login.setBounds(800,600,400,75);
		login.setFont(new Font("Serif",Font.PLAIN, height/35));
		login.setBackground(Color.LIGHT_GRAY);
		login.addActionListener( new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				String username= getusername.getText();
				String pass = getpass.getText();
				int id = Integer.parseInt(username);
				
				User user = userservice.get(id);
//				int i; 
//				for(i=0; i<users.size(); i++) {
//					if(users.get(i).getUserId()==user && users.get(i).getPhone()==pass) {
//						role=users.get(i).getRole();
//						break;
//					}
//				}
				if(user.getRole()==Role.ClinicalStaff){
					openNewRecord.OpenNewRecordcreate();
				}
				else if(user.getRole()==Role.HealthServiceManagement) {
					//ksekina health service
					selectReport.create();
					selectReport.setVisible(true);
				}
				else if(user.getRole()==role.MedicalRecordsStaff) {
					//ksekina medicalstaff
					selectPatient.create();
					selectPatient.setVisible(true);
					
				}
				else if(user.getRole()==role.Receptionist) {
					//
					calendar.create();
					calendar.setVisible(true);
				}
				
		      
		    }
		});
		
		panel.setOpaque(true);
		panel.setBackground(Color.white);
		panel.add(login);
		panel.add(getpass);
		panel.add(password);
		panel.add(getusername);
		panel.add(username);
		panel.add(title);
		frame.add(panel);
		frame.setVisible(true);
		
	}
	
//	public void setVisible(boolean visible) {
//		this.frame.setVisible(visible);
//	}

}
