package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Corso;
import model.Corso;
import persistence.dao.CorsoDao;

public class CorsoDaoJDBC implements CorsoDao {

	private DataSource dataSource;

	public CorsoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Corso corso) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert INTO corso(id, nome) values (?,?)";
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
	public Corso findByPrimaryKey(Long id) {
		Connection connection = dataSource.getConnection();
		String select = "select * FROM corso where id=? ;";
		Corso corso = null;
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result != null) {
				corso = new Corso();
				corso.setId(result.getLong(1));
				corso.setNome(result.getString(2));
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
		return corso;

	}

	@Override
	public List<Corso> findAll() {
		Connection connection = dataSource.getConnection();
		String select = "select * FROM corso;";
		List<Corso> list = new ArrayList<Corso>();
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Corso corso = new Corso(result.getString(2));
				corso.setId(result.getLong(1));
				list.add(corso);
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
	public void update(Corso corso) {
		Connection connection = dataSource.getConnection();
		String query = "update corso where id=? SET nome=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
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
	public void delete(Corso corso) {
		Connection connection = dataSource.getConnection();
		String delete = "delete FROM Corso where id=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, corso.getId());
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
