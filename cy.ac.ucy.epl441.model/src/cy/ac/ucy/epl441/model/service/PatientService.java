package cy.ac.ucy.epl441.model.service;

import java.util.ArrayList;

import cy.ac.ucy.epl441.model.*;

public interface PatientService extends CRUDService<Patient> {
	public ArrayList<Treatment> getTreatments(int id);
	
	public ArrayList<Allergy> getAllergies(int id);
	
	public ArrayList<Relative> getRelatives(int id);
	
	public ArrayList<InformationChanges> getInformationChanges(int id);
	
	public ArrayList<Incident> getIncidents(int id);
	
	public ArrayList<Consultation> getConsultations(int id);
	
	public ArrayList<Diagnosis> getDiagnosis(int id);
}
