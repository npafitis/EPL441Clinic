package cy.ac.ucy.epl441.model.service;

import java.util.ArrayList;

import cy.ac.ucy.epl441.model.*;

public interface UserService extends CRUDService<User> {
	public ArrayList<Consultation> getConsultations(int id);
	
	public Role getRole(int id);
	
	public ArrayList<PerscriptionWarning> getWarnings(int id);
}
