package cy.ac.ucy.epl441.migration;

import org.osgi.framework.*;

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
