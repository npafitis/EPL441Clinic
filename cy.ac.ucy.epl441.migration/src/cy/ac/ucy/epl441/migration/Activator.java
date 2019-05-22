package cy.ac.ucy.epl441.migration;

import org.osgi.framework.*;

/**
* Activator for the migration bundle
*
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

public class Activator implements BundleActivator {
	
	

	@Override
	public void start(BundleContext arg0) throws Exception {
		System.out.println("Migrating Database...");
	}


	@Override
	public void stop(BundleContext arg0) throws Exception {
		System.out.println("Migration Done");	
	}


}
