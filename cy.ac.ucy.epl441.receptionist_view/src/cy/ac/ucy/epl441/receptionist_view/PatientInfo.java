package cy.ac.ucy.epl441.receptionist_view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import org.osgi.service.component.annotations.Component;

import cy.ac.ucy.epl441.model.Patient;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
*  PatientInfo component interface.
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/

public interface PatientInfo{
	public void create(Patient p);
	
	public void setVisible(boolean bool);

}
