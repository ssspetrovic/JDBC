package model;

public class Akcija {
	private int ida;
	private String nazivA;
	private Double popust;
	
	public Akcija() {
		super();
	}

	public Akcija(int ida, String nazivA, Double popust) {
		super();
		this.ida = ida;
		this.nazivA = nazivA;
		this.popust = popust;
	}
	
	public Akcija(String nazivA) {
		super();
		this.nazivA = nazivA;
		
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ida;
		result = prime * result + ((nazivA == null) ? 0 : nazivA.hashCode());
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
		if (ida != other.ida)
			return false;
		if (nazivA == null) {
			if (other.nazivA != null)
				return false;
		} else if (!nazivA.equals(other.nazivA))
			return false;
		if (popust == null) {
			if (other.popust != null)
				return false;
		} else if (!popust.equals(other.popust))
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
	
	@Override
	public String toString() {
		return String.format("%-6d %-20s %-6f", ida, nazivA, popust);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-20s %-6s ", "IDA", "NAZIVA", "POPUST");
	}
	
	

}
