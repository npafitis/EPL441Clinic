package cy.ac.ucy.epl441.receptionist_view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.osgi.service.component.annotations.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public interface Prescription {
	public void create();
	
	public void setVisible(boolean bool);
}
