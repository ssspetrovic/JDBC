package dto;

public class KomponentaDTO {
	int idk;
	String nazivk;
	String tip;
	int brRacunara;
	double cena;
	int akc;

	public KomponentaDTO() {
		super();
	}

	public KomponentaDTO(int idk, String nazivk, String tip, int brRacunara, double cena, int akc) {
		super();
		this.idk = idk;
		this.nazivk = nazivk;
		this.tip = tip;
		this.brRacunara = brRacunara;
		this.cena = cena;
		this.akc = akc;
	}

	public int getIdk() {
		return idk;
	}

	public void setIdk(int idk) {
		this.idk = idk;
	}

	public String getNazivk() {
		return nazivk;
	}

	public void setNazivk(String nazivk) {
		this.nazivk = nazivk;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getBrRacunara() {
		return brRacunara;
	}

	public void setBrRacunara(int brRacunara) {
		this.brRacunara = brRacunara;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getAkc() {
		return akc;
	}

	public void setAkc(int akc) {
		this.akc = akc;
	}

	public static String getFormattedHeader() {
		return String.format("%-20s, %-20s, %-20s, %-20s", "ID komponente", "naziv komponente", "tip komponente", "broj racunara");
	}
	
	@Override
	public String toString() {
		return String.format("%-20d %-20s %-20s %20d", idk, nazivk, tip, brRacunara);
	}
	
}
