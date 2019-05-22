package cy.ac.ucy.epl441.health_service_view.Implementation;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Properties;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.health_service_view.ReportWindow;
import cy.ac.ucy.epl441.health_service_view.SelectReport;
import cy.ac.ucy.epl441.model.Consultation;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.Treatment;
import cy.ac.ucy.epl441.model.service.ConsultationService;
import cy.ac.ucy.epl441.model.service.PatientService;
import cy.ac.ucy.epl441.model.service.TreatmentService;

@Component
public class ReportWindowImpl extends JFrame implements ReportWindow{

	public JPanel contentPane;
	public java.util.Date date;
	public java.util.Date date2;
	
	@Reference
	private DataSourceFactory dsFactory;
	
	@Reference
	private TreatmentService treatmentService;
	
	@Reference
	private PatientService patientService;
	
	@Reference
	private ConsultationService consultationService;
	
//	@Reference
//	private SelectReport sr;
	
	{
		Properties properties = new Properties();
		properties.put(DataSourceFactory.JDBC_URL, "jdbc:mysql://localhost:33061/homestead");
		properties.put(DataSourceFactory.JDBC_USER, "homestead");
		properties.put(DataSourceFactory.JDBC_PASSWORD, "secret");
		try {
			DataSource ds = dsFactory.createDataSource(properties);
			consultationService.setConnection(ds.getConnection());
			patientService.setConnection(ds.getConnection());
			treatmentService.setConnection(ds.getConnection());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Activate
	private void activate() {
		System.out.println("Activated RecordOptions");}
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
		
	@Override	
	public void create(String hc, String week) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = format.format( new java.util.Date()   );
		date       = format.parse (week);
		int noOfDays = 7; 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);            
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		date2 = calendar.getTime();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHealthCentre = new JLabel("Health Centre:");
		lblHealthCentre.setBounds(6, 54, 90, 16);
		contentPane.add(lblHealthCentre);
		
		JLabel lblWeek = new JLabel("Week:");
		lblWeek.setBounds(277, 54, 47, 16);
		contentPane.add(lblWeek);
		
		JLabel lblNewLabel = new JLabel(hc);
		lblNewLabel.setText(hc);
		lblNewLabel.setBounds(120, 54, 110, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(week);
		lblNewLabel_1.setBounds(336, 54, 108, 16);
		lblNewLabel_1.setText(week);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPatientTotals = new JLabel("Patient Totals:");
		lblPatientTotals.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblPatientTotals.setBounds(6, 82, 79, 16);
		contentPane.add(lblPatientTotals);
		
		JLabel lblPatientsByCondition = new JLabel("Patients by Condition:");
		lblPatientsByCondition.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblPatientsByCondition.setBounds(151, 82, 124, 16);
		contentPane.add(lblPatientsByCondition);
		
		JLabel lblMedicationTotals = new JLabel("Medication Totals:");
		lblMedicationTotals.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblMedicationTotals.setBounds(297, 82, 110, 16);
		contentPane.add(lblMedicationTotals);
		
		JTextPane txtpnTotals = new JTextPane();
		txtpnTotals.setText("Totals");
		txtpnTotals.setText(getPatientTotals());
		txtpnTotals.setBounds(6, 110, 124, 135);
		contentPane.add(txtpnTotals);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(getConditions());
		textPane.setBounds(151, 110, 124, 135);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Totals");
		textPane_1.setText(getMedication());
		textPane_1.setBounds(297, 109, 124, 135);
		contentPane.add(textPane_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mental Health Management System");
		lblNewLabel_2.setBounds(111, 17, 227, 16);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
//				sr.create();
//				sr.setVisible(true);
			}
		});
		btnNewButton.setBounds(18, 12, 34, 29);
		contentPane.add(btnNewButton);
	}

	private String getMedication() {
	
		String result="";
		ArrayList<Treatment> c= treatmentService.getAll();
		ArrayList<String> treat=new ArrayList<String>();
		for (Treatment i:c){
			java.util.Date d = new java.util.Date(i.getDate().getTime());
			if (d.compareTo(date)>0&&d.compareTo(date2)<=0) {
				treat.add(i.getDescription());
			}
		}
		Collections.sort(treat);
		String t=treat.get(0);
		int count=0;
		for (String i:treat) {
			if (i.compareTo(t)==0) {
				count+=1;
			}
			else {
				result+=t.toString()+"\t"+count+"\n";
				count=1;
				t=i;
			}
		}
		return result;
	}

	private String getConditions() {
		
		String result="";
		ArrayList<Patient> c= patientService.getAll();
		ArrayList<String> status=new ArrayList<String>();
		for (Patient i:c) {
			status.add(i.getStatus());
		}
		Collections.sort(status);
		String st=status.get(0);
		int count=0;
		for (String i:status) {
			if (i.compareTo(st)==0) {
				count+=1;
			}
			else {
				result+=st.toString()+"\t"+count+"\n";
				count=1;
				st=i;
			}
		}
		return result;
	}

	private String getPatientTotals() {
	
		String result="";
		ArrayList<Consultation> c= consultationService.getAll();
		ArrayList<java.util.Date> week = new ArrayList<java.util.Date>();
		for (Consultation i:c){
			java.util.Date d = new java.util.Date(i.getDate().getTime());
			if (d.compareTo(date)>0&&d.compareTo(date2)<=0) {
				week.add(d);
			}
		}
		Collections.sort(week);
		java.util.Date k=week.get(0);
		int count=0;
		for (java.util.Date i:week) {
			if (i.compareTo(k)==0) {
				count+=1;
			}
			else {
				result+=k.toString()+"\t"+count+"\n";
				count=1;
				k=i;
			}
		}
		result+="Total\t"+week.size();
		return result;
	}
	
	public ReportWindowImpl(){}

}
