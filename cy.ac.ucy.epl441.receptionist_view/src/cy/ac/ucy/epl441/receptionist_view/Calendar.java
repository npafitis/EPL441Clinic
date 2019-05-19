package cy.ac.ucy.epl441.receptionist_view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.model.Consultation;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.User;
import cy.ac.ucy.epl441.model.service.ConsultationService;
import cy.ac.ucy.epl441.model.service.PatientService;
import cy.ac.ucy.epl441.model.service.UserService;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.swing.JTable;
import javax.sql.DataSource;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public interface Calendar{
	public void create();
	
	public void setVisible(boolean bool);

}
