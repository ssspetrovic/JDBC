package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionUtil_HikariCP;
import dao.KomponentaDAO;
import dto.KomponentaDTO;
import model.Komponenta;

public class KomponentaDAOImpl implements KomponentaDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Komponenta entity) throws SQLException {
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
	public Iterable<Komponenta> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Komponenta> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Komponenta findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Komponenta entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Komponenta> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KomponentaDTO> getKomponenteDTO() throws SQLException {
		String query = "SELECT komponenta.idk, komponenta.nazivk, komponenta.tip, komponenta.akc, COUNT(racunar.idr) AS br_racunara\r\n"
				+ "FROM komponenta\r\n"
				+ "LEFT OUTER JOIN konfiguracija\r\n"
				+ "ON komponenta.idk = konfiguracija.idk\r\n"
				+ "LEFT OUTER JOIN racunar\r\n"
				+ "ON konfiguracija.idr = racunar.idr\r\n"
				+ "GROUP BY komponenta.idk, komponenta.nazivk, komponenta.tip, komponenta.akc\r\n"
				+ "HAVING komponenta.idk IS NOT NULL\r\n"
				+ "ORDER BY idk";
		
		List<KomponentaDTO> komponentaDTOList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No rows returned!");
				return null;
			} else {
				while (resultSet.next()) {
//					if (resultSet.get)
					
					KomponentaDTO komponentaDTO = new KomponentaDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5), resultSet.getInt(6));
					
					komponentaDTOList.add(komponentaDTO);
				}
			}
		}
		
		return komponentaDTOList;
	}
		
}
