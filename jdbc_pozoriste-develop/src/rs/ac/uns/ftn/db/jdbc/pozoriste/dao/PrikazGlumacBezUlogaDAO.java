package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazGlumacBezUlogaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazUlogeBezUpotrebeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Glumac;

public interface PrikazGlumacBezUlogaDAO extends CRUDDao<Glumac, Integer>  {
	List<PrikazUlogeBezUpotrebeDTO> getUlogeBezupotrebe() throws SQLException;
	List<PrikazGlumacBezUlogaDTO> getGlumcibezUloga(int idpoz) throws SQLException;
}
