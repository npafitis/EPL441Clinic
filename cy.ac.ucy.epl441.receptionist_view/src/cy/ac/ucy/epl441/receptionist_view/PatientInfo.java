package cy.ac.ucy.epl441.receptionist_view;

import cy.ac.ucy.epl441.model.Patient;

/**
*  PatientInfo component interface.
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/

public interface PatientInfo{
	public void create(Patient p);
	
	public void setVisible(boolean bool);

}
