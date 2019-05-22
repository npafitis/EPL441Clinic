package cy.ac.ucy.epl441.clinicalstaff;

import cy.ac.ucy.epl441.model.service.PatientService;
import cy.ac.ucy.epl441.model.service.TreatmentService;

public interface TreatmentRecord {
	public void createtreatmentrecord(int patientsid,TreatmentService treatmentservice, PatientService patientservice);

}
