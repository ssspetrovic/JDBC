package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;
import java.util.HashMap;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PodelaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Podela;

public interface PodelaDAO extends CRUDDao<Podela, Integer> {
	HashMap<Integer, PodelaDTO> listActorStats() throws SQLException;

}
