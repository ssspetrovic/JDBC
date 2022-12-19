package ui_handler;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.Racunar;
import model.Konfiguracija;
import service.ComplexFuncionalityService;
import service.KomponentaService;
import service.RacunarService;
import dto.KomponentaDTO;
import dto.RacunarDTO;


public class ComplexQueryUIHandler {

	private static final ComplexFuncionalityService complexQueryService = new ComplexFuncionalityService();
	private static final RacunarService racunarService = new RacunarService();
	private static final KomponentaService komponentaService = new KomponentaService();


	public void handleComplexQueryMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite funkcionalnost:");
			System.out.println("\n1 - Prikaz id-a, naziva i svih konfiguracija za odredjeni racunar");
			System.out.println(" 2  - Prikaz osnovnih podataka komponente, kao i racunara u kojima se komponenta nalazi");
			System.out.println(" 3  - Brisanje komponente");
			System.out.println(" 4  - Izvestaj koji ce za uneti naziv akcije i uneti naziv tipa komponente izlistati sve akcije sa tim nazivom");
			System.out.println("\n X - Izlazak iz kompleksnih upita");
			
			answer = MainUIHandler.sc.nextLine();
			
			switch (answer) {
			case "1":
				konfPoIdRac();
				break;
			case "2":
				prikazKomRac();
				break;
			case "3":
				brisanjeKomponenteId();
				break;
			case "4":
				
			}
		}while (!answer.equalsIgnoreCase("X"));

}
	
	public void konfPoIdRac(){
		System.out.println("ID racunara: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		try {
			if (!racunarService.existsById(id)) {
				System.out.println("Uneti racunar ne postoji!");
				return;
			}
			Racunar r = racunarService.getById(id);
			System.out.println("--------------------");
			System.out.println(r.getNazivR());
			System.out.println("--------------------");
			List<Konfiguracija> kon = complexQueryService.pretragaPoIdRac(id);
			System.out.println(Konfiguracija.getFormattedHeader());
			for(Konfiguracija k : kon) {
				System.out.println(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void brisanjeKomponenteId() {
		System.out.println("ID komponente: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		try {
			if (!komponentaService.existsById(id)) {
				System.out.println("Uneti ID ne postoji!");
				return;
			}
			complexQueryService.brisanjeKomponente(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void prikazKomRac() {
		try {
		List<KomponentaDTO> retVal = new ArrayList<KomponentaDTO>();
		retVal = complexQueryService.ispisKomponente();
		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.println(KomponentaDTO.getFormattedHeader());
		for(KomponentaDTO k : retVal) {
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println(k);
			List<RacunarDTO> racunari = complexQueryService.prikazKomRac(k.getIdk());
			System.out.println(RacunarDTO.getFormattedHeader());
			for(RacunarDTO r : racunari) {
				System.out.println(r);
			}
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}	
}
}
