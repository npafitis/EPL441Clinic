package implementation;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cy.ac.ucy.epl441.clinicalstaff.Personalinfo;
import cy.ac.ucy.epl441.model.Allergy;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.Relative;
import cy.ac.ucy.epl441.model.service.DiagnosisService;
import cy.ac.ucy.epl441.model.service.PatientService;
import cy.ac.ucy.epl441.model.service.TreatmentService;


/**
 * This class implements the Personalinfo
 * interface and create the page with the patients info
 * 
 * @author Kyriaki Kekkou
 *
 */
public class PersonalInfoImp implements Personalinfo{
	
	
	private PatientService patientservice;
	private int patientsid;
	private TreatmentService treatmentservice;
	private DiagnosisService consultationserv;
	
	/**
	 * This is the constructor of the class
	 * that takes all the needed arguments
	 * for the page's functionalities or
	 * the pages that this page use
	 * 
	 * @param patientsid patient's id
	 * @param treatmentservice the treatment service
	 * @param patientservice patient's service with all the needed information
	 * @param consultation consultation service that will be forwarded 
	 */
	public PersonalInfoImp(int patientsid, TreatmentService treatmentservice, PatientService patientservice, DiagnosisService consultation) {
		this.treatmentservice=treatmentservice;
		this.patientsid=patientsid;
		this.patientservice=patientservice;
		this.consultationserv=consultation;
		
	}
	/**
	 * This method creates the graphical interface of the page
	 * and represents all the information about the patient
	 */
	@Override
	public void createpersonalinfo() {
		
		Patient pat=patientservice.get(patientsid);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		JPanel panel = new JPanel();
		JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setLayout(null);
		Rectangle bounds = env.getMaximumWindowBounds();
		int width = Math.max(frame.getWidth(), bounds.width);
		int height = Math.max(frame.getHeight(), bounds.height);
		panel.setSize(width,height);
		frame.setSize(width,height);
		
		JLabel title = new JLabel("Mental Health Management System");
		title.setFont(new Font("Serif", Font.PLAIN, height/25));
		title.setBounds(650,1,600,250);

		
		JLabel ho= new JLabel("Home");
		ho.setBounds(250,250,200,50);
		ho.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		
		ImageIcon home = new ImageIcon("home.png");
		JButton bhome= new JButton (home);
		bhome.setBounds(250,200,50,45);
		bhome.addActionListener( new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				

		      
		    }
		});
		
		JLabel treat= new JLabel("Treatment Records");
		treat.setBounds(600,250,200,50);
		treat.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		ImageIcon cross = new ImageIcon("cross.png");
		JButton treatment= new JButton (cross);
		treatment.setBounds(630,200,50,45);
		
		treatment.addActionListener( new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				TreatmentRecordImp treat= new TreatmentRecordImp(patientsid,treatmentservice,patientservice,consultationserv);
				treat.createtreatmentrecord();
				frame.setVisible(false);
				

		      
		    }
		});
		
		
		
		
		JLabel rec= new JLabel("Consultation Records");
		rec.setBounds(1020,250,200,50);
		rec.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		ImageIcon consult = new ImageIcon("record.png");
		JButton consultation= new JButton (consult);
		consultation.setBounds(1090,200,40,45);
		consultation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsultationsImp consultations = new ConsultationsImp(patientsid, treatmentservice, patientservice, consultationserv);
				consultations.createconsultations();
				frame.setVisible(false);

			}
		});
		
		JLabel patient= new JLabel("Patient Information");
		patient.setBounds(1420,250,200,50);
		patient.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		ImageIcon patientinf = new ImageIcon("patientinfo.jpg");
		JButton patientinfo= new JButton (patientinf);
		patientinfo.setBounds(1490,200,50,45);
		
		
		JLabel exit= new JLabel("Exit Patient Record");
		exit.setBounds(1600,250,200,50);
		exit.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		ImageIcon ex = new ImageIcon("exit.png");
		JButton bex= new JButton (ex);
		bex.setBounds(1650,200,50,45);
		bex.addActionListener( new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				OpenNewRecordImp exit= new OpenNewRecordImp();
				exit.OpenNewRecordcreate();
				frame.setVisible(false);
				

		      
		    }
		});
	
		panel.add(exit);
		panel.add(bex);
	
		
		
		
		JLabel patientid= new JLabel("Patient ID:");
		patientid.setBounds(100,350,200,50);
		patientid.setFont(new Font("Serif", Font.PLAIN, height/30));
		

		JLabel id= new JLabel(Integer.toString(patientsid));
		id.setBounds(260,350,200,50);
		id.setFont(new Font("Serif", Font.PLAIN, height/30));
		
		JLabel personal= new JLabel("Personal Information");
		personal.setBounds(100,400,250,50);
		personal.setFont(new Font("Serif", Font.PLAIN, height/35));
		
		JLabel name= new JLabel("Name");
		name.setBounds(100,450,250,50);
		name.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		
		
		JLabel email= new JLabel("Email");
		email.setBounds(100,500,250,50);
		email.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		
		JLabel phone= new JLabel("Phone Number");
		phone.setBounds(100,550,250,50);
		phone.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		JLabel ephone= new JLabel(" Emergency Phone Number");
		ephone.setBounds(800,550,350,50);
		ephone.setFont(new Font("Serif", Font.PLAIN, height/40));
		ArrayList<Relative> rel=patientservice.getRelatives(patientsid);
		int i,emerg=0;
		 for(i=0;i<rel.size(); i++) {
			 if(rel.get(i).getPatientId()==patientsid) {
				 emerg=Integer.parseInt(rel.get(i).getPhone());
				 break;
			 }
		 }
		
		JTextField getname=new JTextField(pat.getName());
		getname.setBounds(340,450,400,35);
		JTextField getemail=new JTextField(pat.getEmail());
		getemail.setBounds(340,500,400,35);
		JTextField getphone=new JTextField(pat.getPhone());
		getphone.setBounds(340,550,400,35);
		JTextField getephone=new JTextField(emerg);
		getephone.setBounds(1100,550,400,35);
		
		JLabel allergies= new JLabel("Known Allergies");
		allergies.setBounds(100,670,250,50);
		allergies.setFont(new Font("Serif", Font.PLAIN, height/35));
		
		JLabel drugs= new JLabel("Drugs");
		drugs.setBounds(100,730,350,50);
		drugs.setFont(new Font("Serif", Font.PLAIN, height/40));
		ArrayList<Allergy> aler =patientservice.getAllergies(patientsid);
		String stringallergies="";
		for(i=0; i< aler.size(); i++) {
			stringallergies=stringallergies+ ","+aler.get(i).getName();
		}
		
		
		JTextArea getdrugs=new JTextArea(stringallergies);
		getdrugs.setBounds(340,730,400,105);
		getdrugs.setBackground(Color.LIGHT_GRAY);
		panel.add(getdrugs);
		panel.add(allergies);
		panel.add(drugs);
		panel.add(getephone);
		panel.add(getphone);
		panel.add(getemail);
		
		panel.add(getname);
		
		
		panel.add(personal);
		panel.add(id);
		panel.add(patientid);
		panel.add(title);
		panel.add(patientinfo);
		panel.add(patient);
		panel.add(consultation);
		panel.add(rec);
		panel.add(treatment);
		panel.add(treat);
		panel.add(bhome);
		panel.add(ho);
		panel.add(ephone);
		panel.add(phone);
		panel.add(email);
		panel.add(name);	
		
		panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        frame.add(panel);
        
   	
		
		
		frame.setVisible(true);
		
		
	}

}
