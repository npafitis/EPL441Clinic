package cy.ac.ucy.epl441.health_service_view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Properties;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.model.Consultation;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.Treatment;
import cy.ac.ucy.epl441.model.service.ConsultationService;
import cy.ac.ucy.epl441.model.service.PatientService;
import cy.ac.ucy.epl441.model.service.TreatmentService;

/**
* Interface of the ReportWindow
*
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/

public interface ReportWindow{
	public void create(String str1,String str2);
	
	public void setVisible(boolean bool);
}
