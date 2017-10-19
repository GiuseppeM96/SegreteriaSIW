package persistence.dao;

import java.util.List;

import model.CorsoDiLaurea;
import model.Gruppo;

public interface CorsoDiLaureaDao {
	public void save(CorsoDiLaurea cdl);  // Create
	public CorsoDiLaurea findByPrimaryKey(Long id);     // Retrieve
	public List<CorsoDiLaurea> findAll();       
	public void update(CorsoDiLaurea cdl); //Update
	public void delete(CorsoDiLaurea cdl); //Delete
}
