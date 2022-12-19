package model;

public class Konfiguracija {
	private int idr;
	private int idk;
	private int komada;
	
	public Konfiguracija() {
		super();
	}

	public Konfiguracija(int idr, int idk, int komada) {
		super();
		this.idr = idr;
		this.idk = idk;
		this.komada = komada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idk;
		result = prime * result + idr;
		result = prime * result + komada;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Konfiguracija other = (Konfiguracija) obj;
		if (idk != other.idk)
			return false;
		if (idr != other.idr)
			return false;
		if (komada != other.komada)
			return false;
		return true;
	}

	public int getIdr() {
		return idr;
	}

	public void setIdr(int idr) {
		this.idr = idr;
	}

	public int getIdk() {
		return idk;
	}

	public void setIdk(int idk) {
		this.idk = idk;
	}

	public int getKomada() {
		return komada;
	}

	public void setKomada(int komada) {
		this.komada = komada;
	}
	@Override
	public String toString() {
		return String.format("%-6d %-6d %-7d ", idr, idk, komada);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-6s %-7s ", "IDR", "IDK", "KOMADA");
	}
	
}
