package model;

public class Komponenta {

	private int id;
	private String naziv;
	private String tip;
	private String proizvodjac;
	private Double cena;
	private int idAkcije;
	
	public Komponenta() {
		super();
	}

	public Komponenta(int id, String naziv, String tip, String proizvodjac, Double cena, int idAkcije) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.proizvodjac = proizvodjac;
		this.cena = cena;
		this.idAkcije = idAkcije;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cena == null) ? 0 : cena.hashCode());
		result = prime * result + id;
		result = prime * result + idAkcije;
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((proizvodjac == null) ? 0 : proizvodjac.hashCode());
		result = prime * result + ((tip == null) ? 0 : tip.hashCode());
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
		Komponenta other = (Komponenta) obj;
		if (cena == null) {
			if (other.cena != null)
				return false;
		} else if (!cena.equals(other.cena))
			return false;
		if (id != other.id)
			return false;
		if (idAkcije != other.idAkcije)
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (proizvodjac == null) {
			if (other.proizvodjac != null)
				return false;
		} else if (!proizvodjac.equals(other.proizvodjac))
			return false;
		if (tip == null) {
			if (other.tip != null)
				return false;
		} else if (!tip.equals(other.tip))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public int getIdAkcije() {
		return idAkcije;
	}

	public void setIdAkcije(int idAkcije) {
		this.idAkcije = idAkcije;
	}
	
	@Override
	public String toString() {
		return String.format("%-6d %-20.20s %-15s %-16d %-6s %-10d", id, naziv, tip, proizvodjac, cena, idAkcije);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-20.20s %-15s %-16s %-6s %-10s", "ID", "NAZIV", "TIP", "PROIZVODJAC", "CENA", "ID AKCIJE");
	}
}
