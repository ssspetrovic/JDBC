package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionUtil_HikariCP;
import dao.RacunarDAO;
import model.Racunar;

public class RacunarDAOImpl implements RacunarDAO {

	@Override
	public int count() throws SQLException {
		String query = "SELECT COUNT(*) FROM racunar";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			
			if (!resultSet.next()) {
				return -1;
			} else {
				return resultSet.getInt(1);
			}
		}
	}

	@Override
	public boolean delete(Racunar entity) throws SQLException {
		return deleteById(entity.getId());
	}

	@Override
	public int deleteAll() throws SQLException {
		String command = "DELETE FROM racunar";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(command);) {
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String command = "DELETE FROM racunar WHERE idr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(command);) {
			
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			
			return rowsAffected > 0 ? true : false;
		}
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		String query = "SELECT * FROM racunar WHERE idr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();				
			}
		}		
	}

	@Override
	public Iterable<Racunar> findAll() throws SQLException {
		String query = "SELECT idr, nazivr FROM racunar";
		List<Racunar> racunarList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			
			if (!resultSet.isBeforeFirst()) {
				return null;
			} else {
				while (resultSet.next()) {
					Racunar racunar = new Racunar(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2));
					racunarList.add(racunar);
				}
				
				return racunarList;
			}
		}
	}

	@Override
	public Iterable<Racunar> findAllById(Iterable<Integer> ids) throws SQLException {	
		List<Racunar> racunarList = new ArrayList<>();
		
		StringBuilder stringBuilder = new StringBuilder();
		// queryBegin will be later filled with the required amount of "?"
		String queryBegin = "SELECT idr, nazivr FROM racunar WHERE idr IN (";
		
		// appending the beginning of the query to string builder
		stringBuilder.append(queryBegin);
		
		// for every id that is sent in list, a "?" is added to string builder
		for (@SuppressWarnings("unused") Integer id : ids) {
			stringBuilder.append("?,");
		}
		
		// string builder will contain one extra "," in the end, so it gets removed
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		// adding the ")" to finish the query
		stringBuilder.append(")");
		
		String query = stringBuilder.toString();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			int i = 1;
			
			for (Integer id : ids) {
				preparedStatement.setInt(i++, id);
			}
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				while (resultSet.next()) {
					Racunar racunar = new Racunar(resultSet.getInt(1), resultSet.getString(2));
					racunarList.add(racunar);
				}
			}
		}
		
		return racunarList;
	}

	@Override
	public Racunar findById(Integer id) throws SQLException {
		String query = "SELECT idr, nazivr FROM racunar WHERE idr = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (!resultSet.isBeforeFirst()) {
					return null;
				} else {
					resultSet.next();
					return new Racunar(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2));
				}
			}
		}
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
			
			for (Racunar racunar : entities) {
				if (saveTransactional(connection, racunar)) {
					++rowsSaved;
				}
			}
			
			connection.commit();
		}
		
		return rowsSaved;
	}

	private boolean saveTransactional(Connection connection, Racunar racunar) throws SQLException {
		String insertCommand = "INSERT INTO racunar (nazivr, idr) VALUES (?, ?)";
		String updateCommand = "UPDATE racunar SET nazivr = ? WHERE idr = ?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(existsById(racunar.getId()) ? updateCommand : insertCommand)) {
			int i = 1;
			
			preparedStatement.setString(i++, racunar.getNaziv());
			preparedStatement.setInt(i++, racunar.getId());
		
			int rowsAffected = preparedStatement.executeUpdate();
			
			return rowsAffected > 0 ? true : false;
		}
	}
	
}
