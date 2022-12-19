package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionUtil_HikariCP;
import dao.AkcijaDAO;
import dto.AkcijaDTO;
import model.Akcija;



public class AkcijaDAOImpl implements AkcijaDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Akcija entity) throws SQLException {
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
	public Iterable<Akcija> findAll() throws SQLException {
		String query = "select ida, naziva, popust from akcija";
		List<Akcija> akList = new ArrayList<Akcija>();
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Akcija akcija = new Akcija(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
				akList.add(akcija);
			}

		}
		return akList;
	}

	@Override
	public Iterable<Akcija> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Akcija findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Akcija entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Akcija> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean existByNaziv(String naziv) throws SQLException {
			try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
				return existsByNazivTransactional(connection, naziv);
			}
		}

		
	private boolean existsByNazivTransactional(Connection connection, String naziv) throws SQLException {
		String query = "select * from akcija where naziva=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, naziv);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}
	
	@Override
	public List<AkcijaDTO> findByNaziv(String naziv, String tip) throws SQLException {
		String query = "select a.ida, a.naziva, a.popust, k.idk, k.nazivk, k.tip, k.proizvodjac, (k.cena - 0.01*a.popust*k.cena) cenaPopust "
					 + "from akcija a, komponenta k where k.akc = a.ida and naziva = ? and tip = ?" ;
		List<AkcijaDTO> akcijaList = new ArrayList<AkcijaDTO>();

		
			try (Connection connection = ConnectionUtil_HikariCP.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				
				preparedStatement.setString(1, naziv);
				preparedStatement.setString(2, tip);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while (resultSet.next()) {
						AkcijaDTO akcija = new AkcijaDTO( resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4), resultSet.getString(5),
				 				 resultSet.getString(6), resultSet.getString(7), resultSet.getDouble(8));
						akcijaList.add(akcija);
					}	
				}
			}
			
		return akcijaList;
	}

}
