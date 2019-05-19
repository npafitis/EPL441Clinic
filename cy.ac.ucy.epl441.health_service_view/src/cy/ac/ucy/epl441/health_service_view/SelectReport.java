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


public interface SelectReport{
	public void create();
	
	public void setVisible(boolean bool);
}
