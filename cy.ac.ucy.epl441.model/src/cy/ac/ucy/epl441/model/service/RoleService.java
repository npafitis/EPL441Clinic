package cy.ac.ucy.epl441.model.service;

import cy.ac.ucy.epl441.model.Role;

public interface RoleService extends CRUDService<Role> {
	public void delete(Role role);
}
