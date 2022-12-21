package dao;

import java.sql.SQLException;
import java.util.List;

import dto.KomponentaDTO;
import model.Komponenta;

public interface KomponentaDAO extends CRUDDao<Komponenta, Integer> {
	List<KomponentaDTO> getKomponenteDTO() throws SQLException;
}
