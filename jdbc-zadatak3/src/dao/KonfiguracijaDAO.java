package dao;

import java.sql.SQLException;
import java.util.List;

import model.Konfiguracija;

public interface KonfiguracijaDAO extends CRUDDao<Konfiguracija, Integer> {
	public List<Konfiguracija> findRacunar(int id) throws SQLException;
}
