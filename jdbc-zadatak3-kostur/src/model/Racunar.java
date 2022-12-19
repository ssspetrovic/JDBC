package model;

public class Racunar {
	private int idr;
	private String nazivR;
	
	public Racunar() {
		super();
	}

	public Racunar(int idr, String nazivR) {
		super();
		this.idr = idr;
		this.nazivR = nazivR;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idr;
		result = prime * result + ((nazivR == null) ? 0 : nazivR.hashCode());
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
		Racunar other = (Racunar) obj;
		if (idr != other.idr)
			return false;
		if (nazivR == null) {
			if (other.nazivR != null)
				return false;
		} else if (!nazivR.equals(other.nazivR))
			return false;
		return true;
	}

	public int getIdr() {
		return idr;
	}

	public void setIdr(int idr) {
		this.idr = idr;
	}

	public String getNazivR() {
		return nazivR;
	}

	public void setNazivR(String nazivR) {
		this.nazivR = nazivR;
	}
	@Override
	public String toString() {
		return String.format("%-6d %-20s", idr, nazivR);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-20s", "IDR", "NAZIVR");
	}
	
}
