package service;

import java.sql.SQLException;

import dao.AkcijaDAO;
import dao.impl.AkcijaDAOImpl;

public class AkcijaService {
	private static final AkcijaDAO akcijaDAO = new AkcijaDAOImpl();
	public boolean existByNaziv(String naziv) throws SQLException {
		return akcijaDAO.existByNaziv(naziv);
	}

}
