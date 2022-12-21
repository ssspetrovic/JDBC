package service;

import java.sql.SQLException;

import dao.RacunarDAO;
import dao.impl.RacunarDAOImpl;
import model.Racunar;

public class RacunarService {
	private static final RacunarDAO racunarDAO = new RacunarDAOImpl();
	
	public int count() throws SQLException {
		return racunarDAO.count();
	}

	public boolean delete(Racunar entity) throws SQLException {
		return racunarDAO.delete(entity);
	}

	public int deleteAll() throws SQLException {
		return racunarDAO.deleteAll();
	}

	public boolean deleteById(Integer id) throws SQLException {
		return racunarDAO.deleteById(id);
	}

	public boolean existsById(Integer id) throws SQLException {
		return racunarDAO.existsById(id);
	}

	public Iterable<Racunar> findAll() throws SQLException {
		return racunarDAO.findAll();
	}

	public Iterable<Racunar> findAllById(Iterable<Integer> ids) throws SQLException {
		return racunarDAO.findAllById(ids);
	}

	public Racunar findById(Integer id) throws SQLException {
		return racunarDAO.findById(id);
	}

	public boolean save(Racunar entity) throws SQLException {
		return racunarDAO.save(entity);
	}

	public int saveAll(Iterable<Racunar> entities) throws SQLException {
		return racunarDAO.saveAll(entities);
	}
	
}
