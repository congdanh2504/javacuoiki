package OOP;

public class sinhvien {
	private String masv;
	private String hoten;
	private java.sql.Date ngaysinh;
	private String gioitinh;
	private String nganhhoc;
	private String lop;
	private String truong;
	private hoso hoso;
	
	public sinhvien(String masv, String hoten, java.sql.Date ngaysinh, String gioitinh, String nganhhoc, String lop,
			String truong) {
		super();
		this.masv = masv;
		this.hoten = hoten;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.nganhhoc = nganhhoc;
		this.lop = lop;
		this.truong = truong;
	}

	public sinhvien(String masv, String hoten, java.sql.Date ngaysinh, String gioitinh, String nganhhoc, String lop,
			String truong, hoso hoso) {
		super();
		this.masv = masv;
		this.hoten = hoten;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.nganhhoc = nganhhoc;
		this.lop = lop;
		this.truong = truong;
		this.hoso = hoso;
	}

	public String getMasv() {
		return masv;
	}
	public void setMasv(String masv) {
		this.masv = masv;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public java.sql.Date getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(java.sql.Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	
	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getNganhhoc() {
		return nganhhoc;
	}
	public void setNganhhoc(String nganhhoc) {
		this.nganhhoc = nganhhoc;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getTruong() {
		return truong;
	}
	public void setTruong(String truong) {
		this.truong = truong;
	}

	public hoso getHoso() {
		return hoso;
	}

	public void setHoso(hoso hoso) {
		this.hoso = hoso;
	}
	
	
}
