package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CorsoDiLaurea;
import model.Dipartimento;
import model.Indirizzo;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DipartimentoDao;

public class DipartimentoDaoJDBC implements DipartimentoDao {
	private DataSource dataSource;

	public DipartimentoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Dipartimento dipartimento) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert INTO dipartimento (id, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, dipartimento.getId());
			statement.setString(2, dipartimento.getNome());
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
	public Dipartimento findByPrimaryKey(Long id) {
		Connection connection = dataSource.getConnection();
		String select = "select * FROM dipartimento where id=? ;";
		Dipartimento dipartimento = null;
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result != null) {
				dipartimento = new Dipartimento();
				dipartimento.setId(result.getLong(1));
				dipartimento.setNome(result.getString(2));
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
		return dipartimento;

	}

	@Override
	public List<Dipartimento> findAll() {
		Connection connection = dataSource.getConnection();
		String select = "select * FROM dipartimento;";
		List<Dipartimento> list = new ArrayList<Dipartimento>();
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Dipartimento dipartimento = new Dipartimento(result.getString(2));
				dipartimento.setId(result.getLong(1));
				list.add(dipartimento);
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
	public void update(Dipartimento dipartimento) {
		Connection connection = dataSource.getConnection();
		String query = "update dipartimento  set nome=? where id=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(2, dipartimento.getId());
			statement.setString(1, dipartimento.getNome());
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
	public void delete(Dipartimento dipartimento) {
		
		Connection connection = dataSource.getConnection();
		String delete = "delete FROM dipartimento where id=?;";
		String updateCdl="update corsoDiLaurea set dipartimento_id=null where dipartimento_id=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, dipartimento.getId());
			PreparedStatement statementUpdate = connection.prepareStatement(updateCdl);
			statementUpdate.setLong(1, dipartimento.getId());
			statementUpdate.executeUpdate();
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
