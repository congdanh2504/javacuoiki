package OOP;

public class hoso {
	private java.sql.Date ngayvay;
	private String sotien;
	private String manh;
	private String masv;
	
	public hoso(java.sql.Date ngayvay, String sotien, String manh, String masv) {
		super();
		this.ngayvay = ngayvay;
		this.sotien = sotien;
		this.manh = manh;
		this.masv = masv;
	}
	
	public String getMasv() {
		return masv;
	}

	public void setMasv(String masv) {
		this.masv = masv;
	}

	public String getManh() {
		return manh;
	}

	public void setManh(String manh) {
		this.manh = manh;
	}

	public java.sql.Date getNgayvay() {
		return ngayvay;
	}
	public void setNgayvay(java.sql.Date ngayvay) {
		this.ngayvay = ngayvay;
	}
	public String getSotien() {
		return sotien;
	}
	public void setSotien(String sotien) {
		this.sotien = sotien;
	}
}
