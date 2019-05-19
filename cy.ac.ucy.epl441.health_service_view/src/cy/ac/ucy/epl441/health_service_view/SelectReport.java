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


public class SelectReport extends JFrame{

	public JFrame frame;
	public String week;
	public String center;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectReport window = new SelectReport();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectReport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMentalHealthManagement = new JLabel("Mental Health Management System");
		lblMentalHealthManagement.setBounds(112, 17, 225, 16);
		frame.getContentPane().add(lblMentalHealthManagement);
		class ComboItem
		{
		    private String key;

		    public ComboItem(String key)
		    {
		        this.key = key;
		    }

		    @Override
		    public String toString()
		    {
		        return key;
		    }
		}
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Select Health Centre");
		comboBox.setBounds(141, 77, 167, 27);
		comboBox.addItem(new ComboItem("Select Health Centre"));
		comboBox.addItem(new ComboItem("Test Health Centre"));
		//get info from db and add to combobox
		
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("Select Week");
		comboBox_1.setBounds(141, 116, 167, 27);
		comboBox_1.addItem(new ComboItem("Select Week"));
		long ts= 1546293600000L;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		final class Date {
			public int day,month,year;
			public int [] count = {31,28,31,30,31,31,30,31,30,31,30,31};
			public Date(int d,int m, int y) {
				day=d;
				month=m;
				year=y;
			}
			public Date nextWeek() {
				if (day+7>count[month]) {
					day=day+7-count[month];
					if (month+1>12) {
						month=1;
						year++;
					}
					else month++;
					
				}
				else day+=7;
				return this;
			}
			
			public String toString() {
				String sday;
				String smonth;
				if (day<10) sday="0"+day;
				else sday=Integer.toString(day);
				if (month<10) smonth="0"+month;
				else smonth=Integer.toString(month);
				return sday+"-"+smonth+"-"+year;
			}}
			Date from =new Date(1,1,2019);
		while (ts<timestamp.getTime()) {
			comboBox_1.addItem(new ComboItem(from.toString()));
			from=from.nextWeek();
			ts+=604800000;
		}
		//get info from db and add to combobox
		frame.getContentPane().add(comboBox_1);
		
		
		
		JButton btnNewButton = new JButton("Generate Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object item = comboBox.getSelectedItem();
				center = ((ComboItem)item).toString();
				Object item2 = comboBox_1.getSelectedItem();
				week = ((ComboItem)item2).toString();
				ReportWindow r = null;
				try {
					r = new ReportWindow(center,week);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				r.setVisible(true);
			}
		});
		btnNewButton.setBounds(154, 165, 142, 29);
		frame.getContentPane().add(btnNewButton);
	}
}
