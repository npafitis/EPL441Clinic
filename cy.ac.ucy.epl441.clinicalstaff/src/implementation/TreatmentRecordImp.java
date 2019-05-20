package implementation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

public class TreatmentRecordImp {
	public void createtretmentrecord(int patientsid) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		JPanel panel = new JPanel();
		JFrame frame=new JFrame();
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
		patientinfo.addActionListener( new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				PersonalInfoImp info= new PersonalInfoImp();
				info.createpersonalinfo(patientsid);
				frame.setVisible(false);
				

		      
		    }
		});
		
		JLabel exit= new JLabel("Exit Patient Record");
		exit.setBounds(1600,250,200,50);
		exit.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		ImageIcon ex = new ImageIcon("exit.png");
		JButton bex= new JButton (ex);
		bex.setBounds(1650,200,50,45);

		
		
		bex.addActionListener( new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				OpenNewRecordImp newrec= new OpenNewRecordImp();
				newrec.OpenNewRecordcreate();
				frame.setVisible(false);
				

		      
		    }
		});
		
		panel.add(bex);
		panel.add(exit);
		
		JLabel patientid= new JLabel("Patient ID:");
		patientid.setBounds(100,350,200,50);
		patientid.setFont(new Font("Serif", Font.PLAIN, height/30));
		

		JLabel id= new JLabel("545344");
		id.setBounds(260,350,200,50);
		id.setFont(new Font("Serif", Font.PLAIN, height/30));
		
		
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
		
		
		
		panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        frame.add(panel);
        
       
        frame.add(panel);
		frame.setVisible(true);
		

	}

}
