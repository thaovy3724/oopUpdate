import java.util.Scanner;

public class DuAn {
	public Scanner sc = new Scanner(System.in);

//field
	private static int idMDA = 1001;
	private int maDuAn;   
	private String tenDuAn;
	private double loiNhuan ;           // doi thanh double
	private double nganSach;                 // doi thanh double
	
//constructor
	public DuAn() {
		maDuAn = 0;
		tenDuAn ="";
		loiNhuan = 0;
		nganSach = 0;
	}
	
	public DuAn(int maDuAn,String tenDuAn, double loiNhuan, double nganSach ) {
		//kiem tra dieu kien 
		this.maDuAn = maDuAn;
		this.tenDuAn = tenDuAn;
		this.loiNhuan = loiNhuan;
		this.nganSach = nganSach;
	}

//init
	public static void init(int initialNum){
		idMDA=initialNum+1;
	}
	
	// nhập
	public void nhap() {
		do{
		System.out.print("Nhap ten du an : ");
	 	tenDuAn = sc.nextLine();
		} while(checkTenDuAn(tenDuAn));

		System.out.print("Nhap loi nhuan: "); //Lợi nhuận có thể âm, = 0, > 0 nên ko kt điều kiện
	 	loiNhuan = Double.parseDouble(sc.nextLine());

	 	do{
			System.out.print("Nhap ngan sach: ");
			nganSach = Double.parseDouble(sc.nextLine());
		} while(checkNganSach(nganSach));
		
        maDuAn = idMDA++;
	}
	
	// xuất
	public void xuat() {
		System.out.println("ma du an: "+maDuAn);
		System.out.println("ten du an: "+tenDuAn);
		System.out.println("loi nhuan: "+loiNhuan);
		System.out.println("ngan sach: "+nganSach);
	}
	
	// Get - Set
	public void setMaDuAn(int maDuAn) {
		this.maDuAn = maDuAn;  // note
	}
	public int getMaDuAn() {
		return maDuAn;
	}
	public void setTenDuAn(String tenDuAn) {
		this.tenDuAn = tenDuAn;
	}
	public String getTenDuAn() {
		return tenDuAn;
	}
	public void setLoiNhuan(double loiNhuan) {
		this.loiNhuan = loiNhuan;
	}
	public double getLoiNhuan() {
		return loiNhuan;
	}
	public void setNganSach(double nganSach) {
		this.nganSach = nganSach;
	}
	public double getNganSach() {
		return nganSach;
	}
	
	//Kt điều kiện
	
	// ĐK ngân sách > 0
	public boolean checkNganSach(double nganSach) {
		if(nganSach<=0){
			System.out.println("Can nhap lon hon 0 !!!");
			return true;
		} 
		return false;
	}

	public static boolean checkTenDuAn(String tenDuAn) {
        // Biểu thức chính quy để kiểm tra tên dự án không để trống và có thể chứa số
        String regex = "^(?=.*[a-zA-Z])([a-zA-Z0-9\\s]+)$";

        // Kiểm tra xem chuỗi có phù hợp hay không
        if(!tenDuAn.matches(regex)){
			System.out.println("Ten khong hop le!");
			return true;
		}
		return false;
    }
	//note
	public static boolean checkMaDuAn(int id) {
		if(id<=1000 || id>9999){
			System.out.println("Ma du an khong hop le!");
			return true;
		} 
		return false;
	}
}