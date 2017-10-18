package persistence.dao;

import java.util.List;

import model.Corso;
import model.Studente;

public interface StudenteDao {
	
	public void save(Studente studente);  // Create
	public Studente findByPrimaryKey(String matricola);     // Retrieve
	public List<Studente> findAll();
	public void update(Studente studente); //Update
	public void delete(Studente studente); //Delete	
	public void updateStudente_Indirizzo(Studente studente, Long id); // Set indirizzo
	public void updateStudente_Segue(Studente studente,Corso corso);

}
