package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.RacunarDAO;
import dao.KomponentaDAO;
import dao.KonfiguracijaDAO;
import dao.AkcijaDAO;
import dao.impl.KonfiguracijaDAOImpl;
import dao.impl.KomponentaDAOImpl;
import dao.impl.AkcijaDAOImpl;
import dao.impl.RacunarDAOImpl;
import model.Akcija;
import model.Komponenta;
import model.Konfiguracija;
import dto.KomponentaDTO;
import dto.RacunarDTO;

public class ComplexFuncionalityService {
	private static final RacunarDAO racunarDAO = new RacunarDAOImpl();
	private static final AkcijaDAO akcijaDAO = new AkcijaDAOImpl();
	private static final KomponentaDAO komponentaDAO = new KomponentaDAOImpl();
	private static final KonfiguracijaDAO konfiguracijaDAO = new KonfiguracijaDAOImpl();
	
	public List<Konfiguracija> pretragaPoIdRac(int id) throws SQLException{
		List<Konfiguracija> retVal = konfiguracijaDAO.findRacunar(id);
		return retVal;
	}
	
	public void brisanjeKomponente(int id) throws SQLException {
		konfiguracijaDAO.deleteById(id);
		if(komponentaDAO.deleteById(id))
			System.out.println("Komponenta uspesno obrisana!");
	}
	
	public  List<KomponentaDTO> ispisKomponente() throws SQLException {
		List<Komponenta> komList = new ArrayList<Komponenta>();
		komponentaDAO.findAll().forEach(komList::add);
		List<Konfiguracija> konList = new ArrayList<Konfiguracija>();
		konfiguracijaDAO.findAll().forEach(konList::add);
		List<KomponentaDTO> retVal = new ArrayList<KomponentaDTO>();
		
		for(Komponenta kom : komList) {
			int i=0;
			KomponentaDTO komDTO = new KomponentaDTO();
			komDTO.setIdk(kom.getIdk());
			komDTO.setNazivK(kom.getNazivK());
			komDTO.setTipK(kom.getTip());
			
			for(Konfiguracija kon : konList) {
				if(kon.getIdk() == kom.getIdk()) {
					i++;
				}
			} komDTO.setrBroj(i);
			retVal.add(komDTO);
		}
		return retVal;
	}
		
	
	public List<RacunarDTO> prikazKomRac(int id) throws SQLException {
		List<Komponenta> komList = new ArrayList<Komponenta>();
		komponentaDAO.findAll().forEach(komList::add);
		List<Akcija> akcijaList = new ArrayList<Akcija>();
		akcijaDAO.findAll().forEach(akcijaList::add);
		List<RacunarDTO> retVal = racunarDAO.findAllWPrice(id);
		Double popust = 0.0;
		for(Komponenta k : komList) {
			if(k.getAkc() != 0 && k.getIdk() == id) {
				
				for(Akcija a : akcijaList) {
					if(a.getIda() == k.getAkc()) {
						popust = a.getPopust();
					}
				}
	
			}
			
		}
		//System.out.println(popust);
		for(RacunarDTO rDTO: retVal) {
			
			rDTO.setuCena(rDTO.getKom()*rDTO.getuCena()*(1-0.01*popust));
			//System.out.println((rDTO.getNazivR()));
			
		}
		
		return retVal;
	}
		
		
		
	
}
