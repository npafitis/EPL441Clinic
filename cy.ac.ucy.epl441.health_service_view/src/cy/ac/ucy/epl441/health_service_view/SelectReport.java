package cy.ac.ucy.epl441.health_service_view;

import java.awt.EventQueue;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.osgi.service.component.annotations.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
/**
* Interface of the SelectReport
*
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/

public interface SelectReport{
	public void create();
	
	public void setVisible(boolean bool);
}
