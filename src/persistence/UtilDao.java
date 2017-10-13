package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilDao {

	private DataSource dataSource;

	public UtilDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void dropDatabase() {

		Connection connection = dataSource.getConnection();
		try {
			String delete = "drop SEQUENCE if EXISTS sequenza_id;" + "drop table if exists segue;"
					+ "drop table if EXISTS studente;" + "drop table if exists gruppo;" + "drop table if exists corso;"
					+ "drop table if exists indirizzo;";
			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();
			System.out.println("Executed drop database");

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

	public void createDatabase() {

		Connection connection = dataSource.getConnection();
		try {

			String delete = "create SEQUENCE sequenza_id;"
					+ "create table gruppo (\"id\" bigint primary key, nome varchar(255));"
					+ "create table indirizzo (\"id\" bigint primary key, nome varchar(255));"
					+ "create table corso (\"id\" bigint primary key, nome varchar(255));"
					+ "create table studente(matricola CHARACTER(8) primary key,"
					+ "nome VARCHAR(255),cognome VARCHAR(255),"
					+ "data_nascita DATE, gruppo_id bigint REFERENCES gruppo(\"id\"), indirizzo_id bigint REFERENCES indirizzo(\"id\"));"
					+ "create table segue(indirizzo_id bigint REFERENCES indirizzo(\"id\"), matricola CHARACTER(8) REFERENCES studente(matricola));";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.executeUpdate();
			System.out.println("Executed create database");

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

	public void resetDatabase() {

		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM studente";
			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();

			delete = "delete FROM gruppo";
			statement = connection.prepareStatement(delete);

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
