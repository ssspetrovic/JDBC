package rs.ac.uns.ftn.db.jdbc.pozoriste.dto;

public class PrikazUlogeBezUpotrebeDTO {
	int idul;
	String imeulo;
	int idpred;

	public PrikazUlogeBezUpotrebeDTO() {
		super();
	}

	public PrikazUlogeBezUpotrebeDTO(int idul, String imeulo, int idpred) {
		super();
		this.idul = idul;
		this.imeulo = imeulo;
		this.idpred = idpred;
	}

	public int getIdul() {
		return idul;
	}

	public void setIdul(int idul) {
		this.idul = idul;
	}

	public String getImeulo() {
		return imeulo;
	}

	public void setImeulo(String imeulo) {
		this.imeulo = imeulo;
	}

	public int getIdpred() {
		return idpred;
	}

	public void setIdpred(int idpred) {
		this.idpred = idpred;
	}

	public static String getFormattedHeader() {
		return String.format("\n%-6s %-15s %-6s", "IDUL", "IMEULO", "IDPRED");
	}

	@Override
	public String toString() {
		return String.format("%-6d %-15s %-6d", idul, imeulo, idpred);
	}

}
