package rs.ac.uns.ftn.db.jdbc.pozoriste.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PodelaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PozoristeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PredstavaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PrikazivanjeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.ScenaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.UlogaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PodelaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PozoristeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PredstavaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PrikazivanjeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.ScenaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.UlogaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PodelaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PredstavaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeDeleteDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeScenaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Podela;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Uloga;
import rs.ac.uns.ftn.db.jdbc.pozoriste.ui_handler.MainUIHandler;

public class ComplexFuncionalityService {

	private static final PozoristeDAO pozoristeDAO = new PozoristeDAOImpl();
	private static final ScenaDAO scenaDAO = new ScenaDAOImpl();
	private static final PrikazivanjeDAO prikazivanjeDAO = new PrikazivanjeDAOImpl();
	private static final PredstavaDAO predstavaDAO = new PredstavaDAOImpl();
	private static final UlogaDAO ulogaDAO = new UlogaDAOImpl();
	private static final PodelaDAO podelaDAO = new PodelaDAOImpl();

	public void showSceneForTheatre() {

		System.out.println(Pozoriste.getFormattedHeader());

		try {
			for (Pozoriste pozoriste : pozoristeDAO.findAll()) {
				System.out.println(pozoriste);
				System.out.println("\t\t----------------------- SCENE -----------------------");

				if (scenaDAO.findSceneByTheatre(pozoriste.getId()).size() != 0) {
					System.out.println("\t\t" + Scena.getFormattedHeader());
					for (Scena scena : scenaDAO.findSceneByTheatre(pozoriste.getId())) {
						System.out.println("\t\t" + scena);
					}
				} else {
					System.out.println("\t\tNEMA SCENE");
				}
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void showReportingForShowingShows() {
		System.out.println(Predstava.getFormattedHeader());
		try {
			HashMap<Integer, PrikazivanjeDTO> resultMap = prikazivanjeDAO.findSumAvgCountForShowingShow();
			for (Integer predstavaId : prikazivanjeDAO.findAllDistinctShowFromShowing()) {
				Predstava predstava = predstavaDAO.findById(predstavaId);
				System.out.println(predstava);
				System.out.println("\t\t" + Prikazivanje.getFormattedHeader());
				for (Prikazivanje p : prikazivanjeDAO.findShowingByShowId(predstava.getIdpred())) {
					System.out.println("\t\t" + p);
				}
				System.out.println(
						"\t\t----- UKUPAN BROJ GLEDALACA ----- PROSECAN BROJ GLEDALACA ----- BROJ PRIKAZIVANJA -----");
				System.out.println("\t\t\t\t" + resultMap.get(predstava.getIdpred()).toString());

				System.out.println("\n\n");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showComplexQuery() {
		try {
			System.out.println(Scena.getFormattedHeader());
			for (Scena scena : scenaDAO.findSceneForSpecificNumberOfSeats()) {
				System.out.println(scena);
				if (prikazivanjeDAO.findBySceneId(scena.getIdsce()).size() != 0) {
					System.out.println("\t\t----------------------- PREDSTAVE -----------------------");
					System.out.println("\t\t" + Predstava.getFormattedHeader());
					for (PrikazivanjeScenaDTO prikazivanje : prikazivanjeDAO.findBySceneId(scena.getIdsce())) {
						if (prikazivanje.getSuma() > 700) {
							System.out.println("\t\t" + predstavaDAO.findById(prikazivanje.getIdpred()));
							System.out.println("\t\t----------------------UKUPNA SUMA-----------------------");
							System.out.println("\t\t" + prikazivanje.getSuma());
							System.out.println("\t\tUkupan broj uloga za predstavu: "
									+ predstavaDAO.findCountOfRoles(prikazivanje.getIdpred()));
							System.out.println();

						} else {
							System.out
									.println("\t\tNEMA PREDSTAVA ZA PRIKAZIVANJE NA OVOJ SCENI SA VISE OD 700 MESTA!");
						}
					}
					System.out.println();
				} else {
					System.out.println("\t\tNEMA PREDSTAVA ZA PRIKAZIVANJE NA OVOJ SCENI!");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showMostVisitedShow() {
		try {

			for (PredstavaDTO p : predstavaDAO.findMostVisitedShows()) {
				System.out.println("IDPRED \t NAZIV\t PROSECNO_TRAJANJE");
				System.out.println(p.toString());
				System.out.println("\t\t--------------------ULOGE------------------------");
				for (Uloga u : ulogaDAO.findRoleByTheatreId(p.getIdpred())) {
					System.out.println("\t\t" + Uloga.getFormattedHeader());
					System.out.println("\t\t" + u.toString());

				}

				System.out.println("\t\t-----------UKUPAN BROJ ZENSKIH ULOGA-------------");
				System.out.println("\t\t" + ulogaDAO.findCountForRoleGender(p.getIdpred(), "z"));
				System.out.println("\t\t-----------UKUPAN BROJ MUSKIH ULOGA--------------");
				System.out.println("\t\t" + ulogaDAO.findCountForRoleGender(p.getIdpred(), "m"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showShowingForDeleting() {

		try {
			for (PrikazivanjeDeleteDTO pd : prikazivanjeDAO.findShowingForDeleting()) {
				System.out.println(PrikazivanjeDeleteDTO.getFormattedHeader());
				System.out.println(pd);
				prikazivanjeDAO.deleteAndInsertIntoShowing(pd);
				System.out.println("--------------------Prikazivanje nakon dodavanja:---------------------");
				System.out.println(Prikazivanje.getFormattedHeader());
				for (Prikazivanje p : prikazivanjeDAO.findAll()) {
					System.out.println(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// "IDPOD", "HONORAR", "DATUMD", "DATUMP", "ULOGA_IDUL", "GLUMAC_MBG"

	public void showPodela() {
//		Scanner sc = new Scanner(System.in);

		String response;
		do {
			System.out.println("Da li zelite da unesete podatke u tabelu? (unesite Y kao potvrdu)");
			if (!MainUIHandler.sc.nextLine().equalsIgnoreCase("Y"))
				break;
			
			System.out.println("Unos podele u tabelu:");

			System.out.println("IDPOD: ");
			int idPod = Integer.parseInt(MainUIHandler.sc.nextLine());
			
			System.out.println("HONORAR: ");
			double honorar = Double.parseDouble(MainUIHandler.sc.nextLine());
			
			System.out.println("DATUMD: ");
			Date datumd = Date.valueOf(MainUIHandler.sc.nextLine());
			
			System.out.println("DATUMP: ");
			Date datump = Date.valueOf(MainUIHandler.sc.nextLine());
			
			System.out.println("ULOGA_IDUL: ");
			String uloga_idul = MainUIHandler.sc.nextLine();
			
			System.out.println("GLUMAC_MBG: ");
			int glumac_mbg = Integer.parseInt(MainUIHandler.sc.nextLine());
//			sc.close();
			
			try {
				podelaDAO.save(new Podela(idPod, honorar, datumd, datump, uloga_idul, glumac_mbg));
				System.out.println("Podela uspesno uneta!");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

			System.out.println("\nNastaviti sa unosom? (Pritisnuti X za prekid unosa)");
			response = MainUIHandler.sc.nextLine();

		} while (!response.equalsIgnoreCase("X"));
		
		System.out.println("\n----------------------------------------------- PODELE -----------------------------------------------");
		System.out.println(Podela.getFormattedHeader());
		try {
			for (Podela podela : podelaDAO.findAll()) {
				System.out.println(podela);
			}
			
			System.out.println("\n----------------------------------------------- GLUMCI -----------------------------------------------");
			HashMap<Integer, PodelaDTO> glumci = podelaDAO.listActorStats();
			
			System.out.println(PodelaDTO.getFormattedHeader());
			for (int key : glumci.keySet()) {
				System.out.println(glumci.get(key));
			}
			System.out.println("\n------------------------------------------------------------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void showUlogeGlumci() {
		
	}

}
