package cy.ac.ucy.epl441.records_view;

import org.osgi.service.component.annotations.*;


@Component
public class MedicalRecordView {
	public static void main(String [] args) {
		SelectPatient sr = new SelectPatient();
		sr.setVisible(true);
	}

}
