package ui_handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Racunar;
import service.RacunarService;


public class RacunarUIHandler {

	private static final RacunarService racunarService = new RacunarService();

	public void handleRacunarMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad racunarima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jednog racunara");
			System.out.println("4 - Unos vise racunara");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja racunara");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showById();
				break;
			case "3":
				handleSingleInsert();
				break;
			case "4":
				handleMultipleInserts();
				break;
			case "5":
				handleUpdate();
				break;
			case "6":
				handleDelete();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	private void showAll() {
		System.out.println(Racunar.getFormattedHeader());

		try {
			for (Racunar r : racunarService.getAll()) {
				System.out.println(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void showById() {
		System.out.println("ID racunara: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			Racunar racunar = racunarService.getById(id);
			System.out.println(Racunar.getFormattedHeader());
			System.out.println(racunar);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleSingleInsert() {
		System.out.println("IDRAC: ");
		int idr = Integer.parseInt(MainUIHandler.sc.nextLine());

		System.out.println("NazivR: ");
		String nazivR = MainUIHandler.sc.nextLine();

		try {
			racunarService.save(new Racunar(idr, nazivR));
			System.out.println("Dodavanje uspesno.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleUpdate() {
		System.out.println("ID pozorista: ");
		int idr = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			if (!racunarService.existsById(idr)) {
				System.out.println("Uneta vrednost ne postoji!");
				return;
			}

			System.out.println("NazivR: ");
			String nazivR = MainUIHandler.sc.nextLine();

			boolean success = racunarService.save(new Racunar(idr, nazivR));
			if (success) {
				System.out.println("Racunar uspesno izmenjen.");
			} else {
				System.out.println("Izmena racunara nije uspela.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleDelete() {
		System.out.println("ID racunara: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			boolean success = racunarService.deleteById(id);
			if (success) {
				System.out.println("Racunar uspesno obrisan.");
			} else {
				System.out.println("Brisanje racunara nije uspelo.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleMultipleInserts() {
		List<Racunar> racunarList = new ArrayList<>();
		String answer;
		do {
			System.out.println("ID racunara: ");
			int idr = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("NazivR: ");
			String nazivR = MainUIHandler.sc.nextLine();


			racunarList.add(new Racunar(idr, nazivR));

			System.out.println("Prekinuti unos? X za potvrdu");
			answer = MainUIHandler.sc.nextLine();
		} while (!answer.equalsIgnoreCase("X"));

		try {
			int rowsSaved = racunarService.saveAll(racunarList);
			System.out.println("Uspesno ažurirano " + rowsSaved + " racunara.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
