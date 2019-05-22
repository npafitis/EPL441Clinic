package cy.ac.ucy.epl441.model.service;

import cy.ac.ucy.epl441.model.Treatment;

import java.util.ArrayList;

import cy.ac.ucy.epl441.model.Allergy;


public interface TreatmentService extends CRUDService<Treatment> {
	public ArrayList<Allergy> getAllergies(Treatment treatment);
	
	public void addAllergy(Treatment treatment, Allergy allergy);
}
