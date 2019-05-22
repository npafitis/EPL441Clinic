package cy.ac.ucy.epl441.receptionist_view.Implementation;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.receptionist_view.Prescription;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class PrescriptionImpl extends JInternalFrame implements Prescription{

	@Activate
	private void activate() {
		System.out.println("Activated Prescription");}

		public PrescriptionImpl(){}
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
		
		JLabel lblPrescriptionRegistered = new JLabel("Prescription registered");
		lblPrescriptionRegistered.setBounds(42, 36, 142, 16);
		getContentPane().add(lblPrescriptionRegistered);

	}
}
