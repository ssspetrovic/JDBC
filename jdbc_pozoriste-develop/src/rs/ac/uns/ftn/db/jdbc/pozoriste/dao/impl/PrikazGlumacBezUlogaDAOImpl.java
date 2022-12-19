package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PrikazGlumacBezUlogaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazGlumacBezUlogaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazUlogeBezUpotrebeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Glumac;

public class PrikazGlumacBezUlogaDAOImpl implements PrikazGlumacBezUlogaDAO{

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Glumac entity) throws SQLException {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Glumac> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Glumac> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Glumac findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Glumac entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll(Iterable<Glumac> entities) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PrikazUlogeBezUpotrebeDTO> getUlogeBezupotrebe() throws SQLException {
		String query = "SELECT uloga.idul, uloga.imeulo, uloga.predstava_idpred FROM uloga\r\n"
				+ "WHERE uloga.idul NOT IN (\r\n"
				+ "    SELECT podela.uloga_idul\r\n"
				+ "    FROM podela\r\n"
				+ ")";
		List<PrikazUlogeBezUpotrebeDTO> uloge = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			
			
			if (!resultSet.isBeforeFirst()) {
				System.out.println("no results found");
			} else {
				while (resultSet.next()) {
					PrikazUlogeBezUpotrebeDTO prikazUloge = new PrikazUlogeBezUpotrebeDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
					uloge.add(prikazUloge);
				}
			}
		}
		return uloge;
	}

	@Override
	public List<PrikazGlumacBezUlogaDTO> getGlumcibezUloga(int idpoz) throws SQLException {
		String query = "SELECT glumac.mbg, glumac.imeg, pozoriste.idpoz, pozoriste.nazivpoz\r\n"
				+ "FROM glumac, pozoriste\r\n"
				+ "WHERE glumac.pozoriste_idpoz = pozoriste.idpoz\r\n"
				+ "AND pozoriste.idpoz IN (\r\n"
				+ "    SELECT glumac.pozoriste_idpoz\r\n"
				+ "    FROM podela, glumac\r\n"
				+ "    WHERE glumac.mbg = podela.glumac_mbg\r\n"
				+ "    AND glumac.pozoriste_idpoz IS NOT NULL\r\n"
				+ "    AND podela.uloga_idul IN (\r\n"
				+ "        SELECT idul\r\n"
				+ "        FROM uloga\r\n"
				+ "        WHERE uloga.predstava_idpred = ?\r\n"
				+ "    )\r\n"
				+ ")";
		
		List<PrikazGlumacBezUlogaDTO> glumciBezUloga = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setInt(1, idpoz);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (!resultSet.isBeforeFirst()) {
					System.out.println("No results found.");
				} else {
					PrikazGlumacBezUlogaDTO prikazGlumca;
					
					while (resultSet.next()) {
						prikazGlumca = new PrikazGlumacBezUlogaDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4));
						glumciBezUloga.add(prikazGlumca);
					}
				}
			}
		}
			
		return glumciBezUloga;
	}

}
