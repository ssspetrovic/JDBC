package model;

public class Konfiguracija {
	private int idRacunara;
	private int id;
	private int komada;
	
	public Konfiguracija() {
		super();
	}
	public Konfiguracija(int idRacunara, int id, int komada) {
		super();
		this.idRacunara = idRacunara;
		this.id = id;
		this.komada = komada;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idRacunara;
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
		if (id != other.id)
			return false;
		if (idRacunara != other.idRacunara)
			return false;
		if (komada != other.komada)
			return false;
		return true;
	}
	public int getIdRacunara() {
		return idRacunara;
	}
	public void setIdRacunara(int idRacunara) {
		this.idRacunara = idRacunara;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKomada() {
		return komada;
	}
	public void setKomada(int komada) {
		this.komada = komada;
	}
	
	@Override
	public String toString() {
		return String.format("|%-6d %-6d %-6d|", idRacunara, id, komada);
	}

	public static String getFormattedHeader() {
		return String.format("|%-6s %-6s %-6s|", "ID R.", "ID K.", "KOMADA");
	}
}
