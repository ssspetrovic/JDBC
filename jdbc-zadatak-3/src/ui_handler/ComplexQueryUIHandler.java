package ui_handler;

import service.ComplexFuncionalityService;

public class ComplexQueryUIHandler {
	private static final ComplexFuncionalityService complexQueryService = new ComplexFuncionalityService();


	public void handleComplexQueryMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite funkcionalnost:");
			System.out.println("  1  - Konfiguracija racunara po ID-u");
			System.out.println("  2  - Prikaz komponenata i racunara u kojima se nalaze");
			System.out.println("  3  - Brisanje komponente po ID-u");
			System.out.println("  4  - Pretraga akcija i komponenti");

			System.out.println("\nX - Izlazak iz kompleksnih upita");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				complexQueryService.configComputerById();
				break;
			case "2":
				complexQueryService.showComponentsAndComputers();
				break;
			case "3":
				break;
			case "4":
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}
	
	
}
