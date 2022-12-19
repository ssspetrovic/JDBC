package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionUtil_HikariCP;
import dao.KonfiguracijaDAO;
import model.Konfiguracija;

public class KonfiguracijaDAOImpl implements KonfiguracijaDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Konfiguracija entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from konfiguracija where idk=?";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Konfiguracija> findAll() throws SQLException {
		String query = "select idr, idk, komada from konfiguracija";
		List<Konfiguracija> konList = new ArrayList<Konfiguracija>();
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Konfiguracija konfiguracija = new Konfiguracija(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
				konList.add(konfiguracija);
			}

		}
		return konList;
	}

	@Override
	public Iterable<Konfiguracija> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Konfiguracija findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Konfiguracija entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Konfiguracija> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<Konfiguracija> findRacunar(int id) throws SQLException{

		String query = "select idr, idk, komada from konfiguracija where idr = ?";
		List<Konfiguracija> konList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					while(resultSet.next()) {
						Konfiguracija konfiguracija = new Konfiguracija(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
						konList.add(konfiguracija);	
					}

				}
			}
		}

		return konList;
	}

}
