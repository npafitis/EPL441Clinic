package implementation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import cy.ac.ucy.epl441.clinicalstaff.TreatmentRecord;
import cy.ac.ucy.epl441.model.Treatment;
import cy.ac.ucy.epl441.model.service.DiagnosisService;
import cy.ac.ucy.epl441.model.service.PatientService;
import cy.ac.ucy.epl441.model.service.TreatmentService;

/**
 * This page implements the TreatmentRecord
 * and presents to the user all the treatments of the
 * patient
 * 
 * @author Kyriaki Kekkou
 *
 */
public class TreatmentRecordImp implements TreatmentRecord {
	
	ArrayList<Treatment> tr = new ArrayList<>();
	
	
	private TreatmentService treatmentservice;
	private int patientsid;
	private PatientService patientservice;
	private DiagnosisService consultationservice;
	
	
	
	
	/**
	 * This is the constructor of the class
	 * that take arguments that are needed from the
	 * page or from the forwarded pages
	 * 
	 * @param patientsid patient's id
	 * @param treatment treatment service tha page will use
	 * @param patientservice patience service
	 * @param consultation consultations service that will be forwarded
	 */
	public TreatmentRecordImp(int patientsid, TreatmentService treatment,PatientService patientservice, DiagnosisService consultation) {
		this.patientsid=patientsid;
		this.treatmentservice=treatment;
		this.patientservice=patientservice;
		this.consultationservice=consultation;
	}
	
	/**
	 * This method creates the graphical interface
	 * of the page
	 */
	public void createtreatmentrecord() {
		
		tr=treatmentservice.getAll();
		
				
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new GridBagLayout());

		panel.setLayout(new GridBagLayout());
		Rectangle bounds = env.getMaximumWindowBounds();
		int width = Math.max(frame.getWidth(), bounds.width);
		int height = Math.max(frame.getHeight(), bounds.height);
		panel.setPreferredSize(new Dimension(1900, 300));

		GridBagConstraints forpanel = new GridBagConstraints();
		forpanel.insets = new Insets(0, 0, 0, 0);

		forpanel.anchor = GridBagConstraints.PAGE_START;
		// forpanel.anchor = GridBagConstraints.WEST;
		forpanel.fill = GridBagConstraints.NONE;
		forpanel.weightx = 1;
		forpanel.weighty = 1;

		frame.setSize(width, height);

		JLabel title = new JLabel("Mental Health Management System");
		title.setFont(new Font("Serif", Font.PLAIN, height / 25));
		GridBagConstraints titl = new GridBagConstraints();
		titl.insets = new Insets(40, 40, 40, 40);
		titl.gridx = 0;
		titl.gridwidth = 0;

		titl.anchor = GridBagConstraints.PAGE_START;

		JLabel ho = new JLabel("Home");
		ho.setBounds(250, 250, 200, 50);
		ho.setFont(new Font("Serif", Font.PLAIN, height / 45));

		ImageIcon home = new ImageIcon("home.png");
		JButton bhome = new JButton(home);
		bhome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				


			}
		});

		JLabel treat = new JLabel("Treatment Records");
		treat.setFont(new Font("Serif", Font.PLAIN, height / 45));

		ImageIcon cross = new ImageIcon("cross.png");
		JButton treatment = new JButton(cross);
		treatment.setBounds(630, 200, 50, 45);

		JLabel rec = new JLabel("Consultation Records");
		rec.setFont(new Font("Serif", Font.PLAIN, height / 45));

		ImageIcon consult = new ImageIcon("record.png");
		JButton consultation = new JButton(consult);
		consultation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsultationsImp consultations= new ConsultationsImp(patientsid, treatmentservice, patientservice, consultationservice);
				consultations.createconsultations();
				frame.setVisible(false);

			}
		});

		JLabel patient = new JLabel("Patient Information");
		patient.setFont(new Font("Serif", Font.PLAIN, height / 45));

		ImageIcon patientinf = new ImageIcon("patientinfo.jpg");
		JButton patientinfo = new JButton(patientinf);
		patientinfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonalInfoImp info= new PersonalInfoImp(patientsid, treatmentservice, patientservice, consultationservice);
				info.createpersonalinfo();
				frame.setVisible(false);

			}
		});

		
		JLabel patientid = new JLabel("Patient ID:");
		patientid.setFont(new Font("Serif", Font.PLAIN, height / 30));

		JLabel id = new JLabel(Integer.toString(patientsid));
		id.setFont(new Font("Serif", Font.PLAIN, height / 30));

		GridBagConstraints rest = new GridBagConstraints();
		//rest.anchor = GridBagConstraints.WEST;
		rest.insets = new Insets(15,0 , 0, 1370);

		rest.gridwidth = GridBagConstraints.REMAINDER;

		GridBagConstraints brest = new GridBagConstraints();
		brest.insets = new Insets(5, 150, 5, 160);
		GridBagConstraints lastbrest = new GridBagConstraints();
		lastbrest.insets = new Insets(5, 100, 5, 70);
		lastbrest.gridwidth = GridBagConstraints.REMAINDER;

		panel.add(title, titl);

		panel.add(bhome, brest);
		panel.add(treatment, brest);
		panel.add(patientinfo, brest);
		panel.add(consultation, lastbrest);
		GridBagConstraints forrow = new GridBagConstraints();
		forrow.gridwidth = GridBagConstraints.REMAINDER;

		GridBagConstraints inforest = new GridBagConstraints();
		inforest.insets = new Insets(5, 150, 5, 60);
		inforest.anchor = GridBagConstraints.WEST;

		panel.add(ho, inforest);
		panel.add(treat);
		panel.add(rec,forrow);
		panel.add(patientid);
		panel.add(id, rest);

		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);

		JPanel testing = new JPanel();
		testing.setLayout(new GridBagLayout());
		testing.setBorder(LineBorder.createBlackLineBorder());
		
		GridBagConstraints fpanel = new GridBagConstraints();
		fpanel.gridwidth = GridBagConstraints.REMAINDER;
		fpanel.weightx = 1f;
		
		int i;
		JPanel insert=new JPanel();
		JLabel date;
		JTextArea drugs;
		GridBagConstraints forrow2 = new GridBagConstraints();
		forrow2.gridwidth = GridBagConstraints.REMAINDER;
		forrow2.insets = new Insets(0, 0, 55, 1670);

		GridBagConstraints forrow3 = new GridBagConstraints();
		forrow3.gridwidth = GridBagConstraints.REMAINDER;
		forrow3.insets = new Insets(0, 50, 55, 50);
		forrow3.weightx=50;		
		forrow3.fill = GridBagConstraints.HORIZONTAL;
		forrow3.gridx = 0;
		
		 

		for (i = 0; i < tr.size(); i++) {

			if(tr.get(i).getPatientId()==patientsid) {

			
			insert=new JPanel();
			insert.setOpaque(true);
			insert.setBackground(Color.white);
			insert.setLayout(new GridBagLayout());
			insert.setBorder(LineBorder.createBlackLineBorder());
			insert.setPreferredSize(new Dimension(1900, 150));
			
			date = new JLabel(tr.get(i).getDate().toString());
			date.setFont(new Font("Serif", Font.PLAIN, height / 35));
			
			
			
			drugs = new JTextArea(60, 500);
		
			drugs.setText(tr.get(i).getDescription());

		    

			drugs.setBackground(Color.LIGHT_GRAY);
			drugs.setFont(new Font("Serif", Font.PLAIN, height / 40));

			insert.add(date,forrow2);
			insert.add(drugs,forrow3);
			testing.add(insert,fpanel);
			}

		}

		JScrollPane scroll = new JScrollPane(testing, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints frameco = new GridBagConstraints();
		scroll.setPreferredSize(new Dimension(1900, 600));

		frameco.gridx = 0;
		frameco.gridy = 1;
		frameco.weightx = 0;
		frame.add(scroll, frameco);

		frame.add(panel, forpanel);
		frame.setVisible(true);

	}

}






