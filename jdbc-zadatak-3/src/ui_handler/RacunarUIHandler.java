package ui_handler;

import java.sql.SQLException;

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
			System.out.println("X - Izlazak iz rukovanja racunarima");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showById();
				break;
			case "3":
				singleInsert();
				break;
			case "4":
				multipleInsert();
				break;
			case "5":
				updateById();
				break;
			case "6":
				deleteById();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}
	
	private static void showAll() {
		System.out.println((Racunar.getFormattedHeader()));
		
		try {
			Iterable<Racunar> racunarList = racunarService.findAll();
			for (Racunar racunar : racunarList) {
				System.out.println(racunar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void showById() {
		System.out.println("idr: ");
		int idr = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println(Racunar.getFormattedHeader());
		
		try {
			System.out.println(racunarService.findById(idr));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void singleInsert() {
		System.out.println("idr: ");
		int idr = Integer.parseInt(MainUIHandler.sc.nextLine());
		System.out.println("nazivr: ");
		String nazivr = MainUIHandler.sc.nextLine();
		
		try {
			if (racunarService.save(new Racunar(idr, nazivr))) {
				System.out.println("Successfully inserted!");
			} else {
				System.out.println("Insertion failed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void multipleInsert() {		
		do {
			System.out.println("idr: ");
			int idr = Integer.parseInt(MainUIHandler.sc.nextLine());
			System.out.println("nazivr: ");
			String nazivr = MainUIHandler.sc.nextLine();
			
			try {
				if (racunarService.save(new Racunar(idr, nazivr))) {
					System.out.println("Successfully inserted!");
				} else {
					System.out.println("Insertion failed!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Do you want to continue insertion? (Y)");
		} while (MainUIHandler.sc.nextLine().equalsIgnoreCase("y"));
	}
	
		
	private static void updateById() {
		System.out.println("idr: ");
		int idr = Integer.parseInt(MainUIHandler.sc.nextLine());
		System.out.println("nazivr: ");
		String nazivr = MainUIHandler.sc.nextLine();
		
		try {
			if (racunarService.save(new Racunar(idr, nazivr))) {
				System.out.println("Successfully updated!");
			} else {
				System.out.println("Update failed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteById() {
		System.out.println("idr: ");
		int idr = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			if (racunarService.deleteById(idr)) {
				System.out.println("Removal was successful!");
			} else {
				System.out.println("Removal failed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
