package cy.ac.ucy.epl441.records_view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.osgi.service.component.annotations.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
* Interface of the SelectPatient component
*
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/

public interface SelectPatient {
	
	public void create();
	
	public void setVisible(boolean bool);

}
