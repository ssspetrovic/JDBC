package rs.ac.uns.ftn.db.jdbc.pozoriste.dto;

public class PodelaDTO {
	String imeg;
	String nazivPredstave;
	String uloga;
	double honorar;
	
	public PodelaDTO() {
		super();
	}

	public PodelaDTO(String imeg, String nazivPredstave, String uloga, double honorar) {
		super();
		this.imeg = imeg;
		this.nazivPredstave = nazivPredstave;
		this.uloga = uloga;
		this.honorar = honorar;
	}

	public String getImeg() {
		return imeg;
	}

	public void setImeg(String imeg) {
		this.imeg = imeg;
	}

	public String getNazivPredstave() {
		return nazivPredstave;
	}

	public void setNazivPredstave(String nazivPredstave) {
		this.nazivPredstave = nazivPredstave;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public double getHonorar() {
		return honorar;
	}

	public void setHonorar(int honorar) {
		this.honorar = honorar;
	}
	
	
	@Override
	public String toString() {
		return String.format("%-12s %-30s %-35s %10f", imeg, nazivPredstave, uloga, honorar);
	}

	public static String getFormattedHeader() {
		return String.format("%-12s %-30s %-35s %-10s", "Ime glumca", "Naziv predstave", "Uloga", "Honorar");
	}
}

