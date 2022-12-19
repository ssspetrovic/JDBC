package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RacunarDAO;
import dto.RacunarDTO;
import model.Racunar;
import connection.ConnectionUtil_HikariCP;


public class RacunarDAOImpl implements RacunarDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from racunar";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return -1;
			}
		}
	}

	@Override
	public boolean delete(Racunar entity) throws SQLException {
		return deleteById(entity.getIdr());
	}

	@Override
	public int deleteAll() throws SQLException {
		String query = "delete from racunar";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			int rowsAfffected = preparedStatement.executeUpdate();
			return rowsAfffected;
		}

	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from racunar where idr=?";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}

	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return existsByIdTransactional(connection, id);
		}
	}

	
	private boolean existsByIdTransactional(Connection connection, Integer id) throws SQLException {
		String query = "select * from racunar where idr=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Racunar> findAll() throws SQLException {
		String query = "select idr, nazivr from racunar";
		List<Racunar> racunarList = new ArrayList<Racunar>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Racunar racunar = new Racunar(resultSet.getInt(1), resultSet.getString(2));
				racunarList.add(racunar);
			}

		}
		return racunarList;
	}

	@Override
	public Iterable<Racunar> findAllById(Iterable<Integer> ids) throws SQLException {
		List<Racunar> racunarList = new ArrayList<>();

		StringBuilder stringBuilder = new StringBuilder();

		String queryBegin = "select idr, nazivr from racunar where idr in(";
		stringBuilder.append(queryBegin);

		for (@SuppressWarnings("unused")
		Integer id : ids) {
			stringBuilder.append("?,");
		}

		stringBuilder.deleteCharAt(stringBuilder.length() - 1); // delete last ','
		stringBuilder.append(")");

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());) {

			int i = 0;
			for (Integer id : ids) {
				preparedStatement.setInt(++i, id);
			}

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					racunarList.add(new Racunar(resultSet.getInt(1), resultSet.getString(2)));
				}
			}
		}

		return racunarList;
	}

	@Override
	public Racunar findById(Integer id) throws SQLException {
		String query = "select idr,nazivr from racunar where idr = ?";
		Racunar racunar = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					 racunar = new Racunar( resultSet.getInt(1), resultSet.getString(2));
				}
			}
		}

		return racunar;
	}

	@Override
	public boolean save(Racunar entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return saveTransactional(connection, entity);
		}
	}

	@Override
	public int saveAll(Iterable<Racunar> entities) throws SQLException {
		
		int rowsSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); 

		
			for (Racunar entity : entities) {
				boolean success = saveTransactional(connection, entity); 
				if (success) rowsSaved++;
			}

			connection.commit(); 
		}
		
		return rowsSaved;
	}

	
	private boolean saveTransactional(Connection connection, Racunar entity) throws SQLException {
		
		String insertCommand = "insert into racunar (nazivr, idr) values (?, ?)";
		String updateCommand = "update racunar set  nazivr=? where idr=?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				existsByIdTransactional(connection, entity.getIdr()) ? updateCommand : insertCommand)) {
			int i = 1;
			preparedStatement.setString(i++, entity.getNazivR());
			preparedStatement.setInt(i++, entity.getIdr());
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}
	
	public List<RacunarDTO> findAllWPrice(int id) throws SQLException{
		String query = "select r.idr, r.nazivr, ko.komada, k.cena from racunar r, konfiguracija ko, komponenta k where k.idk = ? and r.idr = ko.idr and ko.idk = k.idk";
		List<RacunarDTO> racunarList = new ArrayList<RacunarDTO>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, id);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				while (resultSet.next()) {
					RacunarDTO racunar = new RacunarDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4));
					racunarList.add(racunar);
				}	
			}
		}
		return racunarList;
		
	}

}
