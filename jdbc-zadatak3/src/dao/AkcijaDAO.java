package dao;

import java.sql.SQLException;
import java.util.List;

import dto.AkcijaDTO;
import model.Akcija;


public interface AkcijaDAO extends CRUDDao<Akcija, Integer> {
	public List<AkcijaDTO> findByNaziv(String naziv, String tip) throws SQLException;
	public boolean existByNaziv(String naziv) throws SQLException;
}
