package dto;

public class RacunarDTO {
	private int idr;
	private String nazivR;
	private int kom;
	private Double uCena;
	
	public RacunarDTO() {
		super();
	}
	public RacunarDTO(int idr, String nazivR, int kom, Double uCena) {
		super();
		this.idr = idr;
		this.nazivR = nazivR;
		this.kom = kom;
		this.uCena = uCena;
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
	public int getKom() {
		return kom;
	}
	public void setKom(int kom) {
		this.kom = kom;
	}
	public Double getuCena() {
		return uCena;
	}
	public void setuCena(Double uCena) {
		this.uCena = uCena;
	}
	
	@Override
	public String toString() {
		return String.format("%-20s %-30d %-20s %-15d %-10.2f ", " ", idr, nazivR, kom, uCena);
	}

	public static String getFormattedHeader() {
		return String.format("%-20s %-30s %-20s %-15s %-10s", " ", "ID RACUNARA", "NAZIV RACUNARA", "KOMADA", "CENA");
	}
	
	
	

}
