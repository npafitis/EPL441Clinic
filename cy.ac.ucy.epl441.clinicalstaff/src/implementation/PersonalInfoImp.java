package implementation;

import java.awt.Color;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.JTextField;



public class PersonalInfoImp {
	
	
	public void createpersonalinfo(int patientsid) {
		
		
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
				
				DiagnosiImp diagnosi= new DiagnosiImp();
				diagnosi.creatediagnosi(patientsid);
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
				TreatmentRecordImp treat= new TreatmentRecordImp();
				treat.createtretmentrecord(patientsid);
				frame.setVisible(false);
				

		      
		    }
		});
		
		
		
		
		JLabel rec= new JLabel("Consultation Records");
		rec.setBounds(1020,250,200,50);
		rec.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		ImageIcon consult = new ImageIcon("record.png");
		JButton consultation= new JButton (consult);
		consultation.setBounds(1090,200,40,45);
		
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
		

		JLabel id= new JLabel("545344");
		id.setBounds(260,350,200,50);
		id.setFont(new Font("Serif", Font.PLAIN, height/30));
		
		JLabel personal= new JLabel("Personal Information");
		personal.setBounds(100,400,250,50);
		personal.setFont(new Font("Serif", Font.PLAIN, height/35));
		
		JLabel name= new JLabel("Name");
		name.setBounds(100,450,250,50);
		name.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		JLabel surname= new JLabel("Surname");
		surname.setBounds(100,500,250,50);
		surname.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		JLabel date= new JLabel("Date of birth");
		date.setBounds(100,550,250,50);
		date.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		JLabel address= new JLabel("Address");
		address.setBounds(100,600,250,50);
		address.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		JLabel gender= new JLabel("Gender");
		gender.setBounds(100,650,250,50);
		gender.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		JLabel phone= new JLabel("Phone Number");
		phone.setBounds(100,700,250,50);
		phone.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		JLabel ephone= new JLabel(" Emergency Phone Number");
		ephone.setBounds(800,700,350,50);
		ephone.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		JTextField getname=new JTextField();
		getname.setBounds(340,450,400,35);
		JTextField getsurname=new JTextField();
		getsurname.setBounds(340,500,400,35);
		JTextField getdate=new JTextField();
		getdate.setBounds(340,550,400,35);
		JTextField getaddress=new JTextField();
		getaddress.setBounds(340,600,400,35);
		JTextField getgender=new JTextField();
		getgender.setBounds(340,650,400,35);
		JTextField getphone=new JTextField();
		getphone.setBounds(340,700,400,35);
		JTextField getephone=new JTextField();
		getephone.setBounds(1100,700,400,35);
		
		JLabel allergies= new JLabel("Known Allergies");
		allergies.setBounds(100,780,250,50);
		allergies.setFont(new Font("Serif", Font.PLAIN, height/35));
		
		JLabel drugs= new JLabel("Drugs");
		drugs.setBounds(100,830,350,50);
		drugs.setFont(new Font("Serif", Font.PLAIN, height/40));
		
		JTextArea getdrugs=new JTextArea();
		getdrugs.setBounds(340,830,400,105);
		getdrugs.setBackground(Color.LIGHT_GRAY);
		panel.add(getdrugs);
		panel.add(allergies);
		panel.add(drugs);
		panel.add(getephone);
		panel.add(getphone);
		panel.add(getgender);
		panel.add(getaddress);
		panel.add(getdate);
		panel.add(getsurname);
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
		panel.add(gender);
		panel.add(address);
		panel.add(date);
		panel.add(surname);
		panel.add(name);
		
		
		
		panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        frame.add(panel);
        
   	
		
		
		frame.setVisible(true);
		
		
	}

}
