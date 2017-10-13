package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Indirizzo;
import persistence.dao.IndirizzoDao;

public class IndirizzoDaoJDBC implements IndirizzoDao {

	private DataSource dataSource;

	public IndirizzoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Indirizzo indirizzo) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert INTO indirizzo(id, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, indirizzo.getId());
			statement.setString(2, indirizzo.getNome());
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
	public Indirizzo findByPrimaryKey(Long id) {
		Connection connection = dataSource.getConnection();
		String select = "select * FROM indirizzo where id=? ;";
		Indirizzo indirizzo = null;
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result != null) {
				indirizzo = new Indirizzo();
				indirizzo.setId(result.getLong(1));
				indirizzo.setNome(result.getString(2));
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
		return indirizzo;

	}

	@Override
	public List<Indirizzo> findAll() {
		Connection connection = dataSource.getConnection();
		String select = "select * FROM indirizzo;";
		List<Indirizzo> list = new ArrayList<Indirizzo>();
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Indirizzo indirizzo = new Indirizzo(result.getString(2));
				indirizzo.setId(result.getLong(1));
				list.add(indirizzo);
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
	public void update(Indirizzo indirizzo) {
		Connection connection = dataSource.getConnection();
		String query = "update indirizzo where id=? set nome=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, indirizzo.getId());
			statement.setString(2, indirizzo.getNome());
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
	public void delete(Indirizzo indirizzo) {
		Connection connection = dataSource.getConnection();
		String delete = "delete FROM indirizzo where id=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, indirizzo.getId());
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
