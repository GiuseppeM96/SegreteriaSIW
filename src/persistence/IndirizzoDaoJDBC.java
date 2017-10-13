package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		Connection connection=dataSource.getConnection();
		try{
			
			try {
				String insert = "insert into indirizzo(id, nome) values (?,?)";
				PreparedStatement statement = connection.prepareStatement(insert);
				statement.setLong(1,indirizzo.getId());
				statement.setString(2,indirizzo.getNome());
				statement.executeUpdate();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Indirizzo findByPrimaryKey(Long id) {
		Connection connection=dataSource.getConnection();
		String select ="select * from Indirizzo where id=? ;";
		try{
			try {
				PreparedStatement statement = connection.prepareStatement(select);
				statement.setLong(1, id);
				ResultSet result =statement.executeQuery();
				if(result== null){
					return null;
				}
				Indirizzo indirizzo=new Indirizzo();
				indirizzo.setId(result.getLong(1));
				indirizzo.setNome(result.getString(2));
				return indirizzo;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		finally {
			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}

	@Override
	public List<Indirizzo> findAll() {
		return null;
	}

	@Override
	public void update(Indirizzo indirizzo) {
		
	}

	@Override
	public void delete(Indirizzo indirizzo) {
		
	}

}
