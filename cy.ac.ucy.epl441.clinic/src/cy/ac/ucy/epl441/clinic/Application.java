package cy.ac.ucy.epl441.clinic;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import cy.ac.ucy.epl441.records_view.SelectPatient;

/**
* Component that starts the application 
*
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

@Component
public class Application {
	
	@Reference
	private SelectPatient sp;
	
	
	@Activate
	private void activate() {
		System.out.println("Hello from Application");
		sp.create();
		sp.setVisible(true);
	}
}
