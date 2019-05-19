package cy.ac.ucy.epl441.receptionist_view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.osgi.service.component.annotations.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class Prescription extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prescription frame = new Prescription();
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
	public Prescription() {
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
