package cy.ac.ucy.epl441.receptionist_view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.osgi.service.component.annotations.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
* AppointmentDone Component, pops up a window if the given fields are valid to register an appointment.
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class AppointmentDoneImpl extends JInternalFrame implements AppointmentDone{

	@Activate
	private void activate() {
		System.out.println("Activated AppointmentDone");}

	/**
	 * Create the frame.
	 */
	@Override
	public void create() {
		setBounds(100, 100, 250, 250);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Dismiss");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setVisible(false);
			}
		});
		btnNewButton.setBounds(54, 135, 117, 29);
		getContentPane().add(btnNewButton);
		
		JLabel lblPrescriptionRegistered = new JLabel("Appointment Registered");
		lblPrescriptionRegistered.setBounds(42, 36, 142, 16);
		getContentPane().add(lblPrescriptionRegistered);

	}
	public AppointmentDoneImpl(){}
}
