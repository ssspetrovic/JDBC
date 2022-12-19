package dto;

public class KomponentaDTO {
	private int idk;
	private String nazivK;
	private String tipK;
	private int rBroj;
	
	public KomponentaDTO() {
		super();
	}

	public KomponentaDTO(int idk, String nazivK, String tipK, int rBroj) {
		super();
		this.idk = idk;
		this.nazivK = nazivK;
		this.tipK = tipK;
		this.rBroj = rBroj;
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

	public String getTipK() {
		return tipK;
	}

	public void setTipK(String tipK) {
		this.tipK = tipK;
	}

	public int getrBroj() {
		return rBroj;
	}

	public void setrBroj(int rBroj) {
		this.rBroj = rBroj;
	}
	
	@Override
	public String toString() {
		return String.format("%-20d %-30s %-20.20s %-15d ", idk, nazivK, tipK, rBroj);
	}

	public static String getFormattedHeader() {
		return String.format("%-20s %-30s %-20.20s %-15s ", "ID KOMPONENTE", "NAZIV KOMPONENTE", "TIP KOMPONENTE", "BROJ RACUNARA");
	}

}
