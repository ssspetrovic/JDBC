package model;

public class Komponenta {
	private int idk;
	private String nazivK;
	private String tip;
	private String proizvodjac;
	private int cena;
	private int akc;
	
	public Komponenta() {
		super();
	}

	public Komponenta(int idk, String nazivK, String tip, String proizvodjac, int cena, int akc) {
		super();
		this.idk = idk;
		this.nazivK = nazivK;
		this.tip = tip;
		this.proizvodjac = proizvodjac;
		this.cena = cena;
		this.akc = akc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + akc;
		result = prime * result + cena;
		result = prime * result + idk;
		result = prime * result + ((nazivK == null) ? 0 : nazivK.hashCode());
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
		if (akc != other.akc)
			return false;
		if (cena != other.cena)
			return false;
		if (idk != other.idk)
			return false;
		if (nazivK == null) {
			if (other.nazivK != null)
				return false;
		} else if (!nazivK.equals(other.nazivK))
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

	public int getIdk() {
		return idk;
	}

	public void setIdk(int idk) {
		this.idk = idk;
	}

	public String getNazivK() {
		return nazivK;
	}

	public void setNazivK(String nazivK) {
		this.nazivK = nazivK;
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

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getAkc() {
		return akc;
	}

	public void setAkc(int akc) {
		this.akc = akc;
	}
	@Override
	public String toString() {
		return String.format("%-6d %-30s %-10s %-18s %-6d %-5d", idk, nazivK, tip, proizvodjac, cena, akc);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-30s %-10s %-18s %-6s %-5s", "IDK", "NAZIVK", "TIP", "PROIZVODJAC", "CENA", "AKC");
	}
	
}
