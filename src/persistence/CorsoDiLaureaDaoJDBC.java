package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

import model.Corso;
import model.CorsoDiLaurea;
import model.Dipartimento;
import model.Gruppo;
import persistence.dao.CorsoDiLaureaDao;

public class CorsoDiLaureaDaoJDBC implements CorsoDiLaureaDao {
	private DataSource dataSource;

	public CorsoDiLaureaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(CorsoDiLaurea corso) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert INTO corsoDiLaurea (id, nome, dipartimento_id) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, corso.getId());
			statement.setString(2, corso.getNome());
			statement.setLong(3, corso.getDipartimento().getId());
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
		String select = "select C.*,D.nome FROM corsoDiLaurea as C,dipartimento as D WHERE C.dipartimento_id=D.id and C.id=?;";
		CorsoDiLaurea cdl = null;
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				cdl = new CorsoDiLaurea();
				cdl.setId(result.getLong(1));
				cdl.setNome(result.getString(2));
				Dipartimento dipartimento = new Dipartimento(result.getString(4));
				dipartimento.setId(result.getLong(3));
				cdl.setDipartimento(dipartimento);
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
		String select = "select C.*,D.nome FROM corsoDiLaurea as C left join dipartimento as D on C.dipartimento_id=D.id;";
		List<CorsoDiLaurea> list = new ArrayList<CorsoDiLaurea>();
		try {
			PreparedStatement statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				CorsoDiLaurea cdl = new CorsoDiLaurea();
				cdl.setId(result.getLong(1));
				cdl.setNome(result.getString(2));
				Dipartimento dipartimento = new Dipartimento(result.getString(4));
				dipartimento.setId(result.getLong(3));
				cdl.setDipartimento(dipartimento);
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
		String query = "update corsoDiLaurea SET nome=?, dipartimento_id=? where id=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(3, cdl.getId());
			statement.setString(1, cdl.getNome());
			statement.setLong(2, cdl.getDipartimento().getId());
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
