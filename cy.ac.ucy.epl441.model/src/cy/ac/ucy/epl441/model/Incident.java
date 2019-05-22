package cy.ac.ucy.epl441.model;

import java.sql.Date;

/**
* Object that represents an incident.
*
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

public class Incident {
	private int incidentId;
	private int patientId;
	private Date date;
	private String description;
	public int getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public Date getDate() {
		return date;
	}
	public Incident(int patientId, Date date, String description) {
		super();
		this.patientId = patientId;
		this.date = date;
		this.description = description;
	}
	public Incident(int incidentId, int patientId, Date date, String description) {
		super();
		this.incidentId = incidentId;
		this.patientId = patientId;
		this.date = date;
		this.description = description;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
