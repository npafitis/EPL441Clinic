package cy.ac.ucy.epl441.receptionist_view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.osgi.service.component.annotations.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
*  AppointmentDate component interface.
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/

public interface AppointmentDone {
	public void create();
	
	public void setVisible(boolean bool);
	}
