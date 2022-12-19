package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RacunarDAO;
import dao.impl.RacunarDAOImpl;
import model.Racunar;

public class RacunarService {
	private static final RacunarDAO racunarDAO = new RacunarDAOImpl();

	public ArrayList<Racunar> getAll() throws SQLException {
		return (ArrayList<Racunar>) racunarDAO.findAll();
	}

	public Racunar getById(int id) throws SQLException {
		return racunarDAO.findById(id);
	}

	public boolean existsById(int id) throws SQLException {
		return racunarDAO.existsById(id);
	}

	public boolean save(Racunar r) throws SQLException {
		return racunarDAO.save(r);
	}

	public boolean deleteById(int id) throws SQLException {
		return racunarDAO.deleteById(id);
	}

	public int saveAll(List<Racunar> racunarList) throws SQLException {
		return racunarDAO.saveAll(racunarList);
	}
	

}
