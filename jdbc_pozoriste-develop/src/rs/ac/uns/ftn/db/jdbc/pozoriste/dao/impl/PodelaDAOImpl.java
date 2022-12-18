package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PodelaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PodelaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Podela;

public class PodelaDAOImpl implements PodelaDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Podela entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		String query = "SELECT * FROM podela WHERE idpod = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Podela> findAll() throws SQLException {
		String query = "SELECT idpod, honorar, datumd, datump, uloga_idul, glumac_mbg FROM podela";
		List<Podela> podelaList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			
			while (resultSet.next()) {
				
				Podela podela = new Podela(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getDate(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getInt(6));
				podelaList.add(podela);
			}
		}
		
		return podelaList;
	}

	@Override
	public Iterable<Podela> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Podela findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Podela entity) throws SQLException {
		String insertCommand = "INSERT INTO podela (honorar, datumd, datump, uloga_idul, glumac_mbg, idpod) VALUES (?, ?, ?, ?, ?, ?)";
		String updateCommand = "UPDATE podela SET honorar = ?, datumd = ?, datump = ?, uloga_idul = ?, glumac_mbg = ? WHERE idpod = ?";

		int i = 1;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(existsById(entity.getIdPod()) ? updateCommand : insertCommand);) {
				
			preparedStatement.setDouble(i++, entity.getHonorar());
			preparedStatement.setDate(i++, entity.getDatumd());
			preparedStatement.setDate(i++, entity.getDatump());
			preparedStatement.setString(i++, entity.getUloga_idul());
			preparedStatement.setInt(i++, entity.getGlumac_mbg());
			preparedStatement.setInt(i++, entity.getIdPod());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void saveAll(Iterable<Podela> entities) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public HashMap<Integer, PodelaDTO> listActorStats() throws SQLException {
		HashMap<Integer, PodelaDTO> hashMap = new HashMap<>();
		String query = "SELECT DISTINCT mbg, imeg, nazivpred, imeulo, honorar\r\n"
				+ "FROM glumac\r\n"
				+ "LEFT OUTER JOIN podela\r\n"
				+ "ON mbg = glumac_mbg\r\n"
				+ "LEFT OUTER JOIN uloga\r\n"
				+ "ON idul = uloga_idul\r\n"
				+ "LEFT OUTER JOIN predstava\r\n"
				+ "ON idpred = predstava_idpred\r\n"
				+ "ORDER BY mbg ASC";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			int mbg;
			String imeg;
			String nazivpred;
			String imeulo;
			double honorar;
			PodelaDTO podelaDTO;
			
			// unositi jedan po jedan dto u hes tabelu, i pre svakog unosa proveriti da li postoji glumac sa istim mbgom
			// ukoliko postoji, treba proveriti da li mu je honorar veci od postojeceg
			// ukoliko jeste, zameniti ga sa starim dto-om iz tabele, u suprotnom ignorisati
			
			while (resultSet.next()) {
				mbg = Integer.parseInt(resultSet.getString(1));
				imeg = resultSet.getString(2);
				
				if (resultSet.getString(3) == null || resultSet.getString(4) == null) {
					nazivpred = "NEMA";
					imeulo = "NEMA";
					honorar = 0;
				} else {
					nazivpred = resultSet.getString(3);
					imeulo = resultSet.getString(4);
					honorar = Double.parseDouble(resultSet.getString(5));
				}
				
				podelaDTO = new PodelaDTO(imeg, nazivpred, imeulo, honorar);
				
				if (hashMap.containsKey(mbg)) {
					// ukoliko se u tabeli vec nalazi ovaj glumac
					// proveravamo da li je njegov honorar najveci
					if (honorar > hashMap.get(mbg).getHonorar()) {
						// ukoliko jeste, unosimo novi dto na postojeci kljuc
						hashMap.put(mbg, podelaDTO);
					} // u suprotnom ne radimo nista
				} else {
					// ukoliko nije postojao samo ga dodamo u tabelu
					hashMap.put(mbg, podelaDTO);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hashMap;
	}
}
