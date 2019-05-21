package cy.ac.ucy.epl441.records_view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.model.InformationChanges;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.service.InformationChangeService;
import cy.ac.ucy.epl441.model.service.PatientService;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Panel;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;

/**
* Interface of the RecordOptions component
*
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/

public interface RecordOptions {

	public void create(String patientID);
	
	public void setVisible(boolean bool);

}
