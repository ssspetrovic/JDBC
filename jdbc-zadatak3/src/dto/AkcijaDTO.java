package dto;

public class AkcijaDTO {
	private int ida;
	private String nazivA;
	private Double popust;
	private int idk;
	private String nazivK;
	private String tip;
	private String proizvodjac;
	private Double cena;
	
	public AkcijaDTO() {
		super();
	}

	public AkcijaDTO(int ida, String nazivA, Double popust, int idk, String nazivK, String tip, String proizvodjac,
			double cena) {
		super();
		this.ida = ida;
		this.nazivA = nazivA;
		this.popust = popust;
		this.idk = idk;
		this.nazivK = nazivK;
		this.tip = tip;
		this.proizvodjac = proizvodjac;
		this.cena = cena;
	}

	

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cena == null) ? 0 : cena.hashCode());
		result = prime * result + ida;
		result = prime * result + idk;
		result = prime * result + ((nazivA == null) ? 0 : nazivA.hashCode());
		result = prime * result + ((nazivK == null) ? 0 : nazivK.hashCode());
		result = prime * result + ((popust == null) ? 0 : popust.hashCode());
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
		AkcijaDTO other = (AkcijaDTO) obj;
		if (cena == null) {
			if (other.cena != null)
				return false;
		} else if (!cena.equals(other.cena))
			return false;
		if (ida != other.ida)
			return false;
		if (idk != other.idk)
			return false;
		if (nazivA == null) {
			if (other.nazivA != null)
				return false;
		} else if (!nazivA.equals(other.nazivA))
			return false;
		if (nazivK == null) {
			if (other.nazivK != null)
				return false;
		} else if (!nazivK.equals(other.nazivK))
			return false;
		if (popust == null) {
			if (other.popust != null)
				return false;
		} else if (!popust.equals(other.popust))
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

	public int getIda() {
		return ida;
	}

	public void setIda(int ida) {
		this.ida = ida;
	}

	public String getNazivA() {
		return nazivA;
	}

	public void setNazivA(String nazivA) {
		this.nazivA = nazivA;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
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

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	/*
	private int ida;
	private String nazivA;
	private Double popust;
	private int idk;
	private String nazivK;
	private String tip;
	private String proizvodjac;
	private Double cena;
	 
	 
	 */
	@Override
	public String toString() {
		return String.format("%-12d %-30s %-15f %-15d %-30s %-15s %-30s %-10.2f", ida, nazivA, popust, idk, nazivK, tip, proizvodjac, cena );
		
	}

	public static String getFormattedHeader() {
		return String.format("%-12s %-30s %-15s %-15s %-30s %-15s %-30s %-10s", "ID AKCIJE", "NAZIV AKCIJE", "POPUST", "ID KOMPONENTE", "TIP", "PROIZVODJAC","CENA");
	}
	
	
	
}
