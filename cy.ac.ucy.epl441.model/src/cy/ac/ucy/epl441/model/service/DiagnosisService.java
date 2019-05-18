package cy.ac.ucy.epl441.model.service;

import cy.ac.ucy.epl441.model.Diagnosis;
import cy.ac.ucy.epl441.model.Patient;

public interface DiagnosisService extends CRUDService<Diagnosis> {
	public Patient getPatient(Diagnosis diagnosis);
}
