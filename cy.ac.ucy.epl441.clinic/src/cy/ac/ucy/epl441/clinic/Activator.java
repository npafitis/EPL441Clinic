package cy.ac.ucy.epl441.clinic;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

import cy.ac.ucy.epl441.records_view.SelectPatient;

/**
* Activates the OSGi bundle
*
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

public class Activator implements BundleActivator {
	
	
	@Override
	public void start(BundleContext context) throws Exception {
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
