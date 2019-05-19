package cy.ac.ucy.epl441.model.service;

import java.sql.Connection;
import java.util.ArrayList;

public interface CRUDService<T> {
	
	public void create(T item);
	
	public ArrayList<T> getAll();
	
	public T get(int id);
	
	public void update(T item);
	
	public void delete(int id);
	
	public void setConnection(Connection con);
}
