package model;

public class Akcija {
	
	private int id;
	private String naziv;
	private Double popust;
	
	public Akcija() {
		super();
	}

	public Akcija(int id, String naziv, Double popust) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.popust = popust;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((popust == null) ? 0 : popust.hashCode());
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
		Akcija other = (Akcija) obj;
		if (id != other.id)
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (popust == null) {
			if (other.popust != null)
				return false;
		} else if (!popust.equals(other.popust))
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

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}
	
	@Override
	public String toString() {
		return String.format("%-6d %-16s %-10s", id, naziv, popust);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-16s %-10s", "ID", "NAZIV", "POPUST");
	}
}
