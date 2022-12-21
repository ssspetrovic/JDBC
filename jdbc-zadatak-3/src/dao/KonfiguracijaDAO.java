package dao;

import java.sql.SQLException;
import java.util.List;

import model.Konfiguracija;

public interface KonfiguracijaDAO extends CRUDDao<Konfiguracija, Integer>{
	List<Konfiguracija> getAllConfigsById(int idr) throws SQLException;
}