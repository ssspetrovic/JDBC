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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Konfiguracija> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Konfiguracija> findAllById(Iterable<Integer> ids) throws SQLException {
		
		return null;
	}

	@Override
	public Konfiguracija findById(Integer id) throws SQLException {
		String query = "SELECT idr, idk, komada FROM konfiguracija WHERE idr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (!resultSet.isBeforeFirst()) {
					return null;
				} else {
					resultSet.next();
					return new Konfiguracija(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));				
				}
			}
		}
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

	@Override
	public List<Konfiguracija> getAllConfigsById(int idr) throws SQLException {
		String query = "SELECT idr, idk, komada FROM konfiguracija WHERE idr = ?";
		List<Konfiguracija> konfiguracijaList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);){
				
			preparedStatement.setInt(1, idr);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (!resultSet.isBeforeFirst()) {
					System.out.println("No rows selected!");
					return null;
				} else {
					while (resultSet.next()) {
						Konfiguracija konfiguracija = new Konfiguracija(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
						konfiguracijaList.add(konfiguracija);
					}
				}
			}
		}
		
		return konfiguracijaList;
	}

}
