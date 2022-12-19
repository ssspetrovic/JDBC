package service;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.KomponentaDAO;
import dao.impl.KomponentaDAOImpl;
import model.Komponenta;



public class KomponentaService {
	private static final KomponentaDAO komponentaDAO = new KomponentaDAOImpl();
	public boolean existsById(int id) throws SQLException {
		return komponentaDAO.existsById(id);
	}
	
	public boolean existByTip(String tip) throws SQLException {
		return komponentaDAO.existByTip(tip);
	}
	
	public ArrayList<Komponenta> getAll() throws SQLException {
		return (ArrayList<Komponenta>) komponentaDAO.findAll();
	}
	
}
