package cy.ac.ucy.epl441.health_service_view;

import org.osgi.service.component.annotations.*;

/**
* Component that initiates the Service View
*
* @author  aphoti
* @version 1.0
* @since   2019-05-21 
*/
public class ServiceViewComponent {

	public static void main(String [] args) {
		SelectReport sr = new SelectReport();
		sr.frame.setVisible(true);
	}

}
