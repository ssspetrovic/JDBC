package dao;

import java.sql.SQLException;

import model.Komponenta;

public interface KomponentaDAO extends CRUDDao<Komponenta, Integer>{
	public boolean existByTip(String tip) throws SQLException;
	
}
