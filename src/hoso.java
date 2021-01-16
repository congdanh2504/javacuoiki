
public class hoso {
	private String ngayvay;
	private String sotien;
	private String manh;
	private String masv;
	
	public hoso(String ngayvay, String sotien, String manh, String masv) {
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

	public String getNgayvay() {
		return ngayvay;
	}
	public void setNgayvay(String ngayvay) {
		this.ngayvay = ngayvay;
	}
	public String getSotien() {
		return sotien;
	}
	public void setSotien(String sotien) {
		this.sotien = sotien;
	}
}
