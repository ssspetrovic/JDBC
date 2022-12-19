package dao;

import java.sql.SQLException;
import java.util.List;

import dto.RacunarDTO;
import model.Racunar;

public interface RacunarDAO extends CRUDDao<Racunar, Integer>{
	public List<RacunarDTO> findAllWPrice(int id) throws SQLException;
}
