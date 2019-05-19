package cy.ac.ucy.epl441.receptionist_view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Patient;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class PatientInfoImpl extends JInternalFrame implements PatientInfo {

	@Activate
	private void activate() {
		System.out.println("Activated PatientInfo");}

	/**
	 * Create the frame.
	 */
		@Override
	public void create(Patient p) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(91, 43, 79, 16);
		getContentPane().add(lblPatientId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(91, 71, 61, 16);
		getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(91, 127, 61, 16);
		getContentPane().add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(91, 155, 61, 16);
		getContentPane().add(lblPhone);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(91, 183, 61, 16);
		getContentPane().add(lblStatus);
		
		JLabel pid = new JLabel(String.valueOf(p.getPatientId()));
		pid.setBounds(183, 43, 61, 16);
		getContentPane().add(pid);
		
		JLabel pname = new JLabel(p.getName());
		pname.setBounds(183, 71, 61, 16);
		getContentPane().add(pname);
		
		JLabel pemail = new JLabel(p.getEmail());
		pemail.setBounds(183, 127, 61, 16);
		getContentPane().add(pemail);
		
		JLabel pphone = new JLabel(p.getPhone());
		pphone.setBounds(183, 155, 61, 16);
		getContentPane().add(pphone);
		
		JLabel pstatus = new JLabel(p.getStatus());
		pstatus.setBounds(183, 183, 61, 16);
		getContentPane().add(pstatus);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setVisible(false);
			}
		});
		btnClose.setBounds(111, 211, 117, 29);
		getContentPane().add(btnClose);

	}
	public PatientInfoImpl(){}
}
