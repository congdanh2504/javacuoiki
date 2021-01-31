package OOP;

public class nganhang {
	private String manh;
	private String tennganhang;
	private String laivay;
	private String[] hoso;
	public nganhang(String manh, String tennganhang, String laivay) {
		super();
		this.manh = manh;
		this.tennganhang = tennganhang;
		this.laivay = laivay;
	}
	public String getLaivay() {
		return laivay;
	}
	public void setLaivay(String laivay) {
		this.laivay = laivay;
	}
	public String getManh() {
		return manh;
	}
	public void setManh(String manh) {
		this.manh = manh;
	}
	public String getTennganhang() {
		return tennganhang;
	}
	public void setTennganhang(String tennganhang) {
		this.tennganhang = tennganhang;
	}
	public String[] getHoso() {
		return hoso;
	}
	public void setHoso(String[] hoso) {
		this.hoso = hoso;
	}
	
}
