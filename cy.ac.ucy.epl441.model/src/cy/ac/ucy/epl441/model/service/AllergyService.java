package cy.ac.ucy.epl441.model.service;

import java.util.ArrayList;

import cy.ac.ucy.epl441.model.Allergy;
import cy.ac.ucy.epl441.model.Patient;

public interface AllergyService extends CRUDService<Allergy> {
	
	public ArrayList<Patient> getPatients(Allergy allergy);
}
