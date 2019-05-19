package cy.ac.ucy.epl441.records_view.implementation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import cy.ac.ucy.epl441.records_view.RecordOptions;
import cy.ac.ucy.epl441.records_view.SelectPatient;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

@Component
public class SelectPatientImpl extends JFrame implements SelectPatient {

	private JPanel contentPane;
	private JTextField textField;
	
	@Reference
	private RecordOptions r;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectPatientImpl frame = new SelectPatientImpl();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void create() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Mental Health Management System");
		label.setBounds(111, 17, 227, 16);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("Open Record");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.create(textField.getText());
				r.setVisible(true);
//				RecordOptionsImpl r= new RecordOptionsImpl(textField.getText());
//				r.setVisible(true);
//				contentPane.setVisible(false);
			}
		});
		btnNewButton.setBounds(166, 149, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(111, 103, 74, 16);
		contentPane.add(lblPatientId);
		
		textField = new JTextField();
		textField.setBounds(197, 98, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
	}

	/**
	 * Create the frame.
	 */
	public SelectPatientImpl() {
		
	}
}
