package implementation;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.clinicalstaff.Diagnosi;
import cy.ac.ucy.epl441.model.Diagnosis;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.Treatment;
import cy.ac.ucy.epl441.model.service.DiagnosisService;
import cy.ac.ucy.epl441.model.service.PatientService;
import cy.ac.ucy.epl441.model.service.TreatmentService;

public class DiagnosiImp implements Diagnosi{
	
	private DataSourceFactory dsFactory;
	private PatientService patientService;
	private DiagnosisService diagnosisservice;
	private TreatmentService treatmentservice;
	
	{
	Properties properties = new Properties();
	properties.put(DataSourceFactory.JDBC_URL, "jdbc:mysql://localhost:33061/homestead");
	properties.put(DataSourceFactory.JDBC_USER, "homestead");
	properties.put(DataSourceFactory.JDBC_PASSWORD, "secret");
	try {
		DataSource ds = dsFactory.createDataSource(properties);
		patientService.setConnection(ds.getConnection());
		diagnosisservice.setConnection(ds.getConnection());
		treatmentservice.setConnection(ds.getConnection());

		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	
		
		//theoro pos exo ena connection 
		public DiagnosiImp() {
			
		}
		
		public void creatediagnosi(int patientsid) {
			JFrame frame = new JFrame();
			JPanel panel = new JPanel();
			panel.setLayout(null);
			

			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Rectangle bounds = env.getMaximumWindowBounds();
			int width = Math.max(frame.getWidth(), bounds.width);
			int height = Math.max(frame.getHeight(), bounds.height);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize( width, height);
			panel.setSize(width,height);
			
			JLabel title = new JLabel("Mental Health Management System");
			title.setFont(new Font("Serif", Font.PLAIN, height/25));
			
			title.setBounds(650,1,600,250);
			
			JLabel ho= new JLabel("Home");
				ho.setBounds(250,250,200,50);
				ho.setFont(new Font("Serif", Font.PLAIN, height/45));
				
				
				JPanel smallwindow= new JPanel();
				smallwindow.setBounds(600,400,800,400);
				panel.add(smallwindow);
				smallwindow.setVisible(false);
				smallwindow.setLayout(null);
				JButton dismiss= new JButton("Dismiss");
				dismiss.setBounds(350,350,150,45);
				dismiss.addActionListener( new ActionListener(){
					@Override
				    public void actionPerformed(ActionEvent e) {
						smallwindow.setVisible(false);
						

				      
				    }
				});
				
				
				smallwindow.add(dismiss);
				JLabel prescr= new JLabel("Given prescription side effects:");
				prescr.setBounds(52,40, 355,50);
				prescr.setFont(new Font("Serif", Font.PLAIN, height/40));
				smallwindow.add(prescr);
				
				JTextArea seffects = new JTextArea();
				seffects.setBounds(25,100,750,230);
				smallwindow.add(seffects);

				
				ImageIcon home = new ImageIcon("home.png");
				JButton bhome= new JButton (home);
				bhome.setBounds(250,200,50,45);
				bhome.addActionListener( new ActionListener(){
					@Override
				    public void actionPerformed(ActionEvent e) {
						OpenNewRecordImp rec= new OpenNewRecordImp();
						rec.OpenNewRecordcreate();
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
				patient.setBounds(1380,250,200,50);
				patient.setFont(new Font("Serif", Font.PLAIN, height/45));
				
				ImageIcon patientinf = new ImageIcon("patientinfo.jpg");
				JButton patientinfo= new JButton (patientinf);
				patientinfo.setBounds(1450,200,50,45);
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
						OpenNewRecordImp exit= new OpenNewRecordImp();
						frame.setVisible(false);
						

				      
				    }
				});
			
				panel.add(exit);
				panel.add(bex);
			
			
			JLabel patientid = new JLabel("Patient ID:");
			//Font font = patientid.getFont();
			//Map attributes = font.getAttributes();
			//attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON );
			//patientid.setFont(font.deriveFont(attributes));
			patientid.setFont(new Font("Serif", Font.PLAIN, height/35));
			patientid.setBounds(52,300,200,50);
			
			JLabel id=new JLabel(Integer.toString(patientsid));
			id.setFont(new Font("Serif", Font.PLAIN, height/35));
			id.setBounds(patientid.getWidth()+5,300,200,50);
			JLabel risk= new JLabel("Risk Status:");
			risk.setFont(new Font("Serif", Font.PLAIN, height/35));
			risk.setBounds(52,320,200,100);
			
			Patient pat=patientService.get(patientsid);
			String actualstatus=pat.getStatus();
			
			
			JLabel status = new JLabel(actualstatus);
			status.setFont(new Font("Serif", Font.PLAIN, height/35));
			status.setBounds(risk.getWidth()+5,305,200,100);
			JButton changestatus = new JButton("Change Status");
			
			changestatus.setBounds(335, 350, 155,50);
			changestatus.setFont(new Font("Serif", Font.PLAIN, height/40));
			changestatus.setHorizontalAlignment(SwingConstants.LEFT);
			changestatus.setVerticalAlignment(SwingConstants.TOP);
			changestatus.setBackground(Color.LIGHT_GRAY);
			changestatus.setBorder(BorderFactory.createEtchedBorder(0));
			changestatus.addActionListener( new ActionListener(){
				@Override
			    public void actionPerformed(ActionEvent e) {
					if(actualstatus.equals("Moderate")) {
						status.setText("Dangerous");
						pat.setStatus("Dangerous");
					}
					else { 
						status.setText("Moderate");
						pat.setStatus("Moderate");

					}
			      
			    }
			});
			
			
			
			
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy"); 
			String dateString = format.format( new Date() );
			JLabel today = new JLabel(dateString);
			today.setFont(new Font("Serif", Font.PLAIN, height/40));

			today.setBounds(width-200, 350, 155,50);
			JLabel diagnosis = new JLabel("Diagnosis:");
			diagnosis.setBounds(52, 395, 155,50);
			diagnosis.setFont(new Font("Serif", Font.PLAIN, height/35));
			
			JLabel diagn= new JLabel("Diagnosis:");
			diagn.setBounds(52, 465, 155,50);
			diagn.setFont(new Font("Serif", Font.PLAIN, height/45));
			
			JLabel details= new JLabel("Details:");
			details.setBounds(52, 565, 155,50);
			details.setFont(new Font("Serif", Font.PLAIN, height/45));
			
			JLabel comments= new JLabel("Comments:");
			comments.setBounds(52, 665, 155,50);
			comments.setFont(new Font("Serif", Font.PLAIN, height/45));
			
			JTextArea diagnspace= new JTextArea();
			diagnspace.setBackground(Color.LIGHT_GRAY);
			diagnspace.setBounds(250,465,515,80);
			
			JTextArea detspace= new JTextArea();
			detspace.setBackground(Color.LIGHT_GRAY);
			detspace.setBounds(250,565,515,80);

			JTextArea commspace= new JTextArea();
			commspace.setBackground(Color.LIGHT_GRAY);
			commspace.setBounds(250,665,515,80);
			
			JLabel previous = new JLabel("Previous Consultation:");
			previous.setFont(new Font("Serif", Font.PLAIN, height/35));
			previous.setForeground(Color.RED);
			previous.setBounds(350,395,355,80);
			
			JLabel previousdate= new JLabel("");
			previousdate.setFont(new Font("Serif", Font.PLAIN, height/35));
			previousdate.setForeground(Color.RED);
			previousdate.setBounds(620,395,355,80);
			
			JButton registerd = new JButton("Register Diagnosis");
			registerd.setBackground(Color.GREEN);
			registerd.setBounds(500,765,255,80);
			registerd.setFont(new Font("Serif", Font.PLAIN, height/40));
			
			registerd.addActionListener( new ActionListener(){
				@Override
			    public void actionPerformed(ActionEvent e) {
					String diagnosis=diagnspace.getText();
					String details=detspace.getText();
					String comments= commspace.getText();
					diagnosisservice.create(new Diagnosis(patientsid,details,comments));

			      
			    }
			});

			
			JLabel prescription= new JLabel("Prescription:");
			prescription.setFont(new Font("Serif", Font.PLAIN, height/35));
			prescription.setBounds(950,355,355,80);
			

			JLabel repeat= new JLabel("Repeat Previous Prescription:");
			repeat.setFont(new Font("Serif", Font.PLAIN, height/45));
			repeat.setBounds(950,400,355,80);
			
			JCheckBox box = new JCheckBox();
			box.setBounds(1250, 415, 50, 50);
			box.setOpaque(false);
			
			JLabel drug1= new JLabel("Drug 1:");
			drug1.setFont(new Font("Serif", Font.PLAIN, height/45));
			drug1.setBounds(950,455,355,80);
			
			JLabel drug2= new JLabel("Drug 2:");
			drug2.setFont(new Font("Serif", Font.PLAIN, height/45));
			drug2.setBounds(950,515,355,80);
			
			JLabel drug3= new JLabel("Drug 3:");
			drug3.setFont(new Font("Serif", Font.PLAIN, height/45));
			drug3.setBounds(950,575,355,80);
			
			JTextArea putdrug1= new JTextArea();
			putdrug1.setBackground(Color.LIGHT_GRAY);
			putdrug1.setBounds(1200,485,315,65);
			
			JTextArea putdrug2= new JTextArea();
			putdrug2.setBackground(Color.LIGHT_GRAY);
			putdrug2.setBounds(1200,555,315,65);
			
			JTextArea putdrug3= new JTextArea();
			putdrug3.setBackground(Color.LIGHT_GRAY);
			putdrug3.setBounds(1200,625,315,65);
			
			ImageIcon implus = new ImageIcon("plus.png");
			JButton plus= new JButton (implus);
			plus.setBounds(1200,695,50,65);
			
			JButton registerp = new JButton("Register Prescription");
			registerp.setBackground(Color.GREEN);
			registerp.setBounds(1250,765,255,80);
			registerp.setFont(new Font("Serif", Font.PLAIN, height/40));
			
			registerd.addActionListener( new ActionListener(){
				@Override
			    public void actionPerformed(ActionEvent e) {
					if(box.isSelected()){
						ArrayList<Treatment> getAll= treatmentservice.getAll();
						//for(int i=0; i<getAll.size(); i++) 

					}
					else {
						String drug	=putdrug1.getText();
						String drug2=putdrug2.getText();
						String drug3=putdrug3.getText();
						treatmentservice.create(new Treatment(patientsid,drug+","+drug2+","+drug3,(java.sql.Date) new Date()));

					}

					

			      
			    }
			});

			
			JButton effects = new JButton("Show side-effects");
			effects.setBackground(Color.RED);
			effects.setBounds(950,765,255,80);
			effects.setFont(new Font("Serif", Font.PLAIN, height/40));
			effects.addActionListener( new ActionListener(){
				@Override
			    public void actionPerformed(ActionEvent e) {
					smallwindow.setVisible(true);
					
					

			      
			    }
			});


			panel.add(patientinfo);
			panel.add(patient);
			panel.add(consultation);
			panel.add(rec);
			panel.add(treatment);
			panel.add(treat);
			panel.add(bhome);
			panel.add(ho);
			panel.add(effects);
			panel.add(registerp);
			panel.add(plus);
			panel.add(putdrug2);
			panel.add(putdrug3);
			panel.add(putdrug1);
			panel.add(drug1);
			panel.add(drug2);
			panel.add(drug3);
			panel.add(box);
			panel.add(repeat);
			panel.add(prescription);
			panel.add(registerd);
			panel.add(previousdate);
			panel.add(previous);
			panel.add(commspace);
			panel.add(detspace);
			panel.add(diagnspace);
			panel.add(comments);
			panel.add(diagn);
			panel.add(details);
			panel.add(diagnosis);
			panel.add(id);
			panel.add(title);
			panel.add(patientid);
			panel.add(status);
			panel.add(risk);
			panel.add(changestatus);
			panel.add(today);
		
			
	       
			
			
			
			panel.setOpaque(true);
	        panel.setBackground(Color.WHITE);
	        frame.add(panel);
	        //frame.add(takep.takepanel());

			frame.setVisible(true);
			
			
		}


}
