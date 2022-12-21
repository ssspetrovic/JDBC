package dto;

public class RacunarKomponentaDTO {
	int idr;
	String nazivr;
	int komada;
	double ukupnaCena;
	
	public RacunarKomponentaDTO() {
		super();
	}

	public RacunarKomponentaDTO(int idr, String nazivr, int komada, double ukupnaCena) {
		super();
		this.idr = idr;
		this.nazivr = nazivr;
		this.komada = komada;
		this.ukupnaCena = ukupnaCena;
	}

	public int getIdr() {
		return idr;
	}

	public void setIdr(int idr) {
		this.idr = idr;
	}

	public String getNazivr() {
		return nazivr;
	}

	public void setNazivr(String nazivr) {
		this.nazivr = nazivr;
	}

	public int getKomada() {
		return komada;
	}

	public void setKomada(int komada) {
		this.komada = komada;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	@Override
	public String toString() {
		return "";
	}
	
	
}
