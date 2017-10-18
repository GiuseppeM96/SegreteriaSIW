package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import model.Corso;
import model.Studente;

public class StudenteProxy extends Studente {
	private DataSource dataSource;

	public StudenteProxy(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Set<Corso> getCorsi() { 
		Set<Corso> corsi = new HashSet<>();
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select c.nome , c.id from corso as c, segue as s where c.id= s.corso_id && s.matricola=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, this.getMatricola());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Corso corso = new Corso(result.getString(1));
				corso.setId(result.getLong(2));				
				corsi.add(corso);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		this.setCorsi(corsi);
		return super.getCorsi();
	}
}
