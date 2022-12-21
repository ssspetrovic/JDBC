package service;

import java.sql.SQLException;
import java.util.List;

import dao.KonfiguracijaDAO;
import dao.RacunarDAO;
import dao.impl.KonfiguracijaDAOImpl;
import dao.impl.RacunarDAOImpl;
import model.Konfiguracija;
import model.Racunar;
import ui_handler.MainUIHandler;

public class ComplexFuncionalityService {
	RacunarDAO racunarDAO = new RacunarDAOImpl();
	KonfiguracijaDAO konfiguracijaDAO = new KonfiguracijaDAOImpl();
	
	public void configComputerById() {
		System.out.println("idr: ");
		int idr = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			Racunar racunar = racunarDAO.findById(idr);
			if (racunar == null) {
				System.out.println("Computer not found!");
			} else {
				System.out.println(racunar.getNaziv());				
				System.out.println("-----------------------");
				
				List<Konfiguracija> konfiguracijaList = konfiguracijaDAO.getAllConfigsById(idr);
				
				System.out.println(Konfiguracija.getFormattedHeader());
				
				for (Konfiguracija konfiguracija : konfiguracijaList) {
					System.out.println(konfiguracija);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showComponentsAndComputers() {
		
	}
}
