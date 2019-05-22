package implementation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cy.ac.ucy.epl441.clinicalstaff.FindPatient;
/**
 * This class implements FindPatient interface
 * and constructs the page for it 
 * 
 * @author Kyriaki Kekkou
 *
 */
public class FindPatientImp implements FindPatient{
		
	
	public FindPatientImp(){	
		
		
	}
	/**
	 * This method create the graphical interface
	 * of the FindPatient page
	 * 
	 */
	@Override
	public void FindPatientcreateview() {
		// TODO Auto-generated method stub
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		JPanel panel = new JPanel();
		JFrame frame=new JFrame();
		panel.setLayout(null);
		Rectangle bounds = env.getMaximumWindowBounds();
		int width = Math.max(frame.getWidth(), bounds.width);
		int height = Math.max(frame.getHeight(), bounds.height);
		panel.setSize(width,height);
		frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		
		JLabel title = new JLabel("Mental Health Management System");
		title.setFont(new Font("Serif", Font.PLAIN, height/25));
		title.setBounds(650,1,600,250);

		
		JLabel find= new JLabel("Find Patient");
		find.setBounds(490,250,200,50);
		find.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		
		ImageIcon findpat = new ImageIcon("findpatient.png");
		JButton findp= new JButton (findpat);
		findp.setBounds(510,200,50,65);
		
		JLabel search= new JLabel("Search Records");
		search.setBounds(1200,250,200,50);
		search.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		ImageIcon searchloc = new ImageIcon("searchrecords.png");
		JButton searchlo= new JButton (searchloc);
		searchlo.setBounds(1230,200,50,65);
		
		searchlo.addActionListener( new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);

		      
		    }
		});
		
		
		
		
		JLabel searchattr= new JLabel("Search Attributes");
		searchattr.setBounds(150,350,200,50);
		searchattr.setFont(new Font("Serif", Font.PLAIN, height/35));
		
		JLabel name= new JLabel("Name");
		name.setBounds(150,400,200,50);
		name.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		JLabel surname= new JLabel("Surname");
		surname.setBounds(150,450,200,50);
		surname.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		JLabel date= new JLabel("Date of birth");
		date.setBounds(150,500,200,50);
		date.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		JLabel location= new JLabel("Location");
		location.setBounds(150,550,200,50);
		location.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		JLabel allergy= new JLabel("Allergy");
		allergy.setBounds(150,600,200,50);
		allergy.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		JLabel mentalstatus= new JLabel("Mental Status");
		mentalstatus.setBounds(150,650,200,50);
		mentalstatus.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		JLabel drugs= new JLabel("Drugs Prescribed");
		drugs.setBounds(150,700,200,50);
		drugs.setFont(new Font("Serif", Font.PLAIN, height/45));
		
		JTextField getname=new JTextField();
		getname.setBounds(340,410,400,35);
		JTextField getsurname=new JTextField();
		getsurname.setBounds(340,460,400,35);
		JTextField getdate=new JTextField();
		getdate.setBounds(340,510,400,35);
		JTextField getlocation=new JTextField();
		getlocation.setBounds(340,560,400,35);
		JTextField getallergy=new JTextField();
		getallergy.setBounds(340,610,400,35);
		JTextField getmental=new JTextField();
		getmental.setBounds(340,660,400,35);
		JTextField getdrugs=new JTextField();
		getdrugs.setBounds(340,710,400,35);
		
		JButton showrecords = new JButton("Show Results");
		showrecords.setBounds(340,800,400,75);
		showrecords.setFont(new Font("Serif",Font.PLAIN, height/35));
		showrecords.setBackground(Color.LIGHT_GRAY);
		
		
		
		JLabel results= new JLabel("");
		results.setBounds(900,400,800,550);
		results.setHorizontalAlignment(SwingConstants.LEFT);
		results.setVerticalAlignment(SwingConstants.TOP);
		results.setBackground(Color.LIGHT_GRAY);
		results.setBorder(BorderFactory.createEtchedBorder(0));
		results.setVisible(false);
		JLabel searchres= new JLabel("Search Results");
		searchres.setBounds(900,350,200,50);
		searchres.setFont(new Font("Serif", Font.PLAIN, height/35));
		searchres.setVisible(false);
		
		showrecords.addActionListener(new ActionListener(){
			
			@Override
		    public void actionPerformed(ActionEvent e) {
				
				//prepi na vro ta apotelesmata
				results.setVisible(true);
				searchres.setVisible(true);

		      
		    }
		});

		
		panel.add(searchres);
		panel.add(results);
		panel.add(getdrugs);
		panel.add(getmental);
		panel.add(getallergy);
		panel.add(getlocation);
		panel.add(getdate);
		panel.add(getsurname);
		panel.add(showrecords);
		panel.add(getname);
		panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        panel.add(drugs);
        panel.add(mentalstatus);
        panel.add(allergy);
        panel.add(location);
        panel.add(date);
        panel.add(searchattr);
		panel.add(name);
		panel.add(surname);
		panel.add(searchlo);
		panel.add(search);
		panel.add(findp);
        panel.add(find);
		panel.add(title);

		frame.add(panel);
		frame.setVisible(true);
	}

}

	

