package cy.ac.ucy.epl441.receptionist_view.Implementation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.model.Consultation;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.User;
import cy.ac.ucy.epl441.model.service.ConsultationService;
import cy.ac.ucy.epl441.model.service.PatientService;
import cy.ac.ucy.epl441.model.service.UserService;
import cy.ac.ucy.epl441.receptionist_view.AppointmentDone;
import cy.ac.ucy.epl441.receptionist_view.Calendar;
import cy.ac.ucy.epl441.receptionist_view.PatientInfo;
import cy.ac.ucy.epl441.receptionist_view.Prescription;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.swing.JTable;
import javax.sql.DataSource;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
* Calendar Component is the receptionist's view with 3 panels. A panel for the todays appoinements.
* A panel for searching patients and a panel for registering appointments. The receptionist is able to repeat a previous prescription and note if the patient attended the consultaiton.
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class CalendarImpl extends JFrame implements Calendar{

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField pid;
	private JTextField time;
	private JTextField dat;
	private JTextField doctor;
	private JTextField patid;
	
	@Reference
	private DataSourceFactory dsFactory;
	
	@Reference
	private ConsultationService consultationService;
	
	@Reference
	private UserService userService;
	
	@Reference
	private PatientService patientService;
	
	@Reference
	private AppointmentDone apd;
	
	@Reference
	private PatientInfo pi;
	
	@Reference 
	private Prescription pr;
	
	@Activate
	private void activate() {
		System.out.println("Activated Calendar");}
	
	{
		Properties properties = new Properties();
		properties.put(DataSourceFactory.JDBC_URL, "jdbc:mysql://localhost:33061/homestead");
		properties.put(DataSourceFactory.JDBC_USER, "homestead");
		properties.put(DataSourceFactory.JDBC_PASSWORD, "secret");
		try {
			DataSource ds = dsFactory.createDataSource(properties);
			consultationService.setConnection(ds.getConnection());
			patientService.setConnection(ds.getConnection());
			userService.setConnection(ds.getConnection());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	@Override
	public void create() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Mental Health Management System");
		label.setBounds(159, 26, 231, 16);
		contentPane.add(label);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 62, 538, 310);
		contentPane.add(tabbedPane);
		
		Panel panel = new Panel();
		tabbedPane.addTab("Today", null, panel, null);
		java.util.Calendar currenttime = java.util.Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());
		Object[] columnNames = {"Name", "Time", "Doctor", "Visited"};
		
		
		ArrayList<Consultation> aps= new ArrayList<Consultation>(consultationService.getAll().stream()
				.filter(cons -> cons.getDate().getDate() == new Date(System.currentTimeMillis()).getDate() && 
							cons.getDate().getMonth()== new Date(System.currentTimeMillis()).getMonth())
				.collect(Collectors.toList())
				);
	
		Object [] [] data= new Object[aps.size()][4];
		int r=0;
		for (Consultation i:aps) {
			data[r][0]=patientService.get(i.getPatientId()).getName();
			data[r][1]=i.getDate().getTime();
			data[r][2]= userService.get(i.getUserId()).getName();
			data[r][3]= new Boolean(i.isAttended()).toString();
			r++;
		}
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        panel.setLayout(null);
        JTable table_1 = new JTable(model) {

            private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        table_1.setPreferredScrollableViewportSize(table_1.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table_1);
        scrollPane.setBounds(256, 5, 4, 4);
        panel.add(scrollPane);
        
        JButton btnNewButton = new JButton("Repeat Prescription");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		table_1.getSelectedRow();
        		pr.create();
        		pr.setVisible(true);
        	}
        });
        btnNewButton.setBounds(183, 229, 151, 29);
        panel.add(btnNewButton);
        
		Panel panel_1 = new Panel();
		tabbedPane.addTab("Schedule", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblPatientid = new JLabel("PatientID:");
		lblPatientid.setBounds(163, 39, 61, 16);
		panel_1.add(lblPatientid);
		
		JLabel lblTimehhmm = new JLabel("Time (HH:MM):");
		lblTimehhmm.setBounds(163, 67, 103, 16);
		panel_1.add(lblTimehhmm);
		
		JLabel lblDateddmmyyyy = new JLabel("Date (dd-mm-yyyy):");
		lblDateddmmyyyy.setBounds(163, 95, 129, 16);
		panel_1.add(lblDateddmmyyyy);
		
		JLabel lblDoctor = new JLabel("Doctor: ");
		lblDoctor.setBounds(163, 123, 61, 16);
		panel_1.add(lblDoctor);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date date = null;
				try {
					date = sdf1.parse(dat.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date dd = new java.sql.Date(date.getTime());
				User user = userService.getAll().stream()
						.filter(val -> val.getName().equals(doctor.getText()))
						.collect(Collectors.toList())
						.get(0);
				int patientId = Integer.parseInt(pid.getText());
				Consultation ap=new Consultation(
						user.getUserId(),
						patientId,
						dd,
						false
					);
				consultationService.create(ap);
				time.setText("");
				pid.setText("");
				doctor.setText("");
				dat.setText("");
				apd.create();
				apd.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(193, 161, 117, 29);
		panel_1.add(btnNewButton_1);
		
		pid = new JTextField();
		pid.setBounds(306, 34, 130, 26);
		panel_1.add(pid);
		pid.setColumns(10);
		
		time = new JTextField();
		time.setBounds(306, 62, 130, 26);
		panel_1.add(time);
		time.setColumns(10);
		
		dat = new JTextField();
		dat.setBounds(306, 90, 130, 26);
		panel_1.add(dat);
		dat.setColumns(10);
		
		doctor = new JTextField();
		doctor.setBounds(306, 118, 130, 26);
		panel_1.add(doctor);
		doctor.setColumns(10);
		
		Panel panel_2 = new Panel();
		tabbedPane.addTab("Search", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("View Record");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int patientId = Integer.parseInt(patid.getText());
				Patient p=patientService.get(patientId);
				pi.create(p);
				pi.setVisible(true);;
			}
		});
		btnNewButton_2.setBounds(200, 133, 117, 29);
		panel_2.add(btnNewButton_2);
		
		patid = new JTextField();
		patid.setBounds(269, 95, 130, 26);
		panel_2.add(patid);
		patid.setColumns(10);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(142, 100, 81, 16);
		panel_2.add(lblPatientId);
	}
	
	public CalendarImpl(){}
}
