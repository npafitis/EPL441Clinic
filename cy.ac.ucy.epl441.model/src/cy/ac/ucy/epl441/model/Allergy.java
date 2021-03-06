package cy.ac.ucy.epl441.model;

/**
* Object that represents an alergy.
*
* @author  npafitis
* @version 1.0
* @since   2019-05-21 
*/

public class Allergy {
	private int allergyId;
	private String name;
	public int getAllergyId() {
		return allergyId;
	}
	public void setAllergyId(int allergyId) {
		this.allergyId = allergyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Allergy(String name) {
		super();
		this.name = name;
	}
	public Allergy(int allergyId, String name) {
		super();
		this.allergyId = allergyId;
		this.name = name;
	}
}
