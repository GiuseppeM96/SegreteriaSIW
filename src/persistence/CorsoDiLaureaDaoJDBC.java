package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Corso;
import model.CorsoDiLaurea;
import model.Dipartimento;
import model.Gruppo;
import persistence.dao.CorsoDiLaureaDao;

public class CorsoDiLaureaDaoJDBC implements CorsoDiLaureaDao {
	private DataSource dataSource;

	@Override
	public void save(CorsoDiLaurea corso) {
		// TODO Auto-generated method stub
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert INTO corsoDiLaurea (id, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, corso.getId());
			statement.setString(2, corso.getNome());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public CorsoDiLaurea findByPrimaryKey(Long id) {
		Connection connection = dataSource.getConnection();
		String select = "select * FROM corsoDiLaurea where id=? ;";
		CorsoDiLaurea cdl = null;
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result != null) {
				cdl = new CorsoDiLaurea();
				cdl.setId(result.getLong(1));
				cdl.setNome(result.getString(2));
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
		return cdl;
	}

	@Override
	public List<CorsoDiLaurea> findAll() {
		Connection connection = dataSource.getConnection();
		String select = "select * FROM corsoDiLaurea;";
		List<CorsoDiLaurea> list = new ArrayList<CorsoDiLaurea>();
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				
				CorsoDiLaurea cdl = new CorsoDiLaurea();
				cdl.setNome(result.getString(2));
				Dipartimento dipartimento = new Dipartimento();
				dipartimento.setId(result.getLong(3));
				cdl.setDipartimento(dipartimento);
				cdl.setId(result.getLong(1));
				list.add(cdl);
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
		return list;
	}

	@Override
	public void update(CorsoDiLaurea cdl) {
		Connection connection = dataSource.getConnection();
		String query = "update corsoDiLaurea where id=? SET nome=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, cdl.getId());
			statement.setString(2, cdl.getNome());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}		
	}

	@Override
	public void delete(CorsoDiLaurea cdl) {
		Connection connection = dataSource.getConnection();
		String delete = "delete FROM corsoDiLaurea where id=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, cdl.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}		
	}
}
