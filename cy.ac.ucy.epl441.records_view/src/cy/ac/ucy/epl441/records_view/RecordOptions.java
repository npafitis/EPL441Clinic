package cy.ac.ucy.epl441.records_view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.model.InformationChanges;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.service.InformationChangeService;
import cy.ac.ucy.epl441.model.service.PatientService;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Panel;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;

@Component
public class RecordOptions extends JFrame {

	private JPanel contentPane;
	private JTextField changename;
	private JTextField changeemail;
	private JTextField changenumber;
	public Patient p;
	
	@Reference
	private DataSourceFactory dsFactory;
	
	@Reference
	private InformationChangeService changeService;
	
	
	@Reference
	private PatientService patientService;
	
	{
		Properties properties = new Properties();
		properties.put(DataSourceFactory.JDBC_URL, "jdbc:mysql://localhost:33061/homestead");
		properties.put(DataSourceFactory.JDBC_USER, "homestead");
		properties.put(DataSourceFactory.JDBC_PASSWORD, "secret");
		try {
			DataSource ds = dsFactory.createDataSource(properties);
			changeService.setConnection(ds.getConnection());
			patientService.setConnection(ds.getConnection());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordOptions frame = new RecordOptions("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RecordOptions(String patientID) {
		p= patientService.get(Integer.parseInt(patientID));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Mental Health Management System");
		label.setBounds(111, 17, 227, 16);
		contentPane.add(label);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 40, 450, 238);
		contentPane.add(tabbedPane);
		
		Panel Home = new Panel();
		tabbedPane.addTab("Home", null, Home, null);
		Home.setLayout(null);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(6, 6, 72, 16);
		Home.add(lblPatientId);
		
		JLabel lblId = new JLabel(patientID);
		lblId.setBounds(90, 6, 79, 16);
		Home.add(lblId);
		
		JLabel lblAvailableRecords = new JLabel("Available Records:");
		lblAvailableRecords.setBounds(6, 34, 120, 16);
		Home.add(lblAvailableRecords);
		
		JTextPane txtpnMentalHealthRecord = new JTextPane();
		txtpnMentalHealthRecord.setText("Mental Health Record");
		txtpnMentalHealthRecord.setBounds(138, 34, 163, 67);
		Home.add(txtpnMentalHealthRecord);
		
		JButton btnNewButton_1 = new JButton("Clinic Session Transactions");
		btnNewButton_1.setBounds(6, 157, 210, 29);
		Home.add(btnNewButton_1);
		
		Panel RequestChanges = new Panel();
		tabbedPane.addTab("Request Changes", null, RequestChanges, null);
		RequestChanges.setLayout(null);
		
		JLabel lblPatientId_1 = new JLabel("Patient ID:");
		lblPatientId_1.setBounds(6, 6, 101, 16);
		RequestChanges.add(lblPatientId_1);
		
		JLabel lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setBounds(6, 34, 131, 16);
		RequestChanges.add(lblPersonalInformation);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 62, 61, 16);
		RequestChanges.add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Email:");
		lblDateOfBirth.setBounds(6, 99, 83, 16);
		RequestChanges.add(lblDateOfBirth);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(6, 139, 109, 16);
		RequestChanges.add(lblPhoneNumber);
		
		JLabel lblName_1 = new JLabel(p.getName());
		lblName_1.setBounds(121, 62, 61, 16);
		RequestChanges.add(lblName_1);
		
		JLabel lblEmail = new JLabel(p.getEmail());
		lblEmail.setBounds(121, 99, 61, 16);
		RequestChanges.add(lblEmail);
		
//		JLabel lblAddress = new JLabel(p.getAddress());
//		lblAddress.setBounds(121, 119, 61, 16);
//		RequestChanges.add(lblAddress);
		
		JLabel lblNumber = new JLabel(p.getPhone());
		lblNumber.setBounds(121, 139, 61, 16);
		RequestChanges.add(lblNumber);
		
		changename = new JTextField();
		changename.setBounds(195, 62, 130, 16);
		RequestChanges.add(changename);
		changename.setColumns(10);
		
		changeemail = new JTextField();
		changeemail.setColumns(10);
		changeemail.setBounds(195, 99, 130, 16);
		RequestChanges.add(changeemail);
		
		changenumber = new JTextField();
		changenumber.setColumns(10);
		changenumber.setBounds(195, 139, 130, 16);
		RequestChanges.add(changenumber);
		
		JButton btnRequestChanges = new JButton("Request Changes");
		btnRequestChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformationChanges ic= new InformationChanges(p.getPatientId(), new java.sql.Date(System.currentTimeMillis()), changename.getText(), changeemail.getText(), changenumber.getText(),p.getName(), p.getEmail(),p.getPhone(), false);
				changeService.update(ic);
			}
		});
		btnRequestChanges.setBounds(195, 157, 140, 29);
		RequestChanges.add(btnRequestChanges);
		
		JLabel label_1 = new JLabel(patientID);
		label_1.setBounds(89, 6, 79, 16);
		RequestChanges.add(label_1);
		
		Panel Integrate = new Panel();
		tabbedPane.addTab("Integrate Records", null, Integrate, null);
		Integrate.setLayout(null);
		class ComboItem
		{
		    private String key;

		    public ComboItem(String key)
		    {
		        this.key = key;
		    }

		    @Override
		    public String toString()
		    {
		        return key;
		    }
		}
		JComboBox SelectSystem = new JComboBox();
		SelectSystem.setBounds(139, 57, 150, 27);
		SelectSystem.setToolTipText("Select Record System");
		SelectSystem.addItem(new ComboItem("Pathological Records"));
		SelectSystem.addItem(new ComboItem("Cardiological Records"));
		SelectSystem.addItem(new ComboItem("Mental Health Records"));
		Integrate.add(SelectSystem);
		
		JButton btnNewButton = new JButton("Find Records");
		btnNewButton.setBounds(156, 96, 117, 29);
		Integrate.add(btnNewButton);
		
		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectPatient sp=new SelectPatient();
				sp.setVisible(true);
				contentPane.setVisible(false);
			}
		});
		button.setBounds(6, 12, 34, 29);
		contentPane.add(button);
	}
}
