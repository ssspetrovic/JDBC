package rs.ac.uns.ftn.db.jdbc.pozoriste.dto;

public class PrikazGlumacBezUlogaDTO {
	int mbg;
	String imeg;
	int idpoz;
	String nazivpoz;

	public PrikazGlumacBezUlogaDTO() {
		super();
	}

	public PrikazGlumacBezUlogaDTO(int mbg, String imeg, int idpoz, String nazivpoz) {
		super();
		this.mbg = mbg;
		this.imeg = imeg;
		this.idpoz = idpoz;
		this.nazivpoz = nazivpoz;
	}

	public int getMbg() {
		return mbg;
	}

	public void setMbg(int mbg) {
		this.mbg = mbg;
	}

	public String getImeg() {
		return imeg;
	}

	public void setImeg(String imeg) {
		this.imeg = imeg;
	}

	public int getIdpoz() {
		return idpoz;
	}

	public void setIdpoz(int idpoz) {
		this.idpoz = idpoz;
	}

	public String getNazivpoz() {
		return nazivpoz;
	}

	public void setNazivpoz(String nazivpoz) {
		this.nazivpoz = nazivpoz;
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-12s %-4s %-30s", "MBG", "IMEG", "IDPOZ", "NAZIVPOZ");
	}
	
	@Override
	public String toString() {
		return String.format("\t%-6d %-12s %-4d %-30s", mbg, imeg, idpoz, nazivpoz);
	}

}
