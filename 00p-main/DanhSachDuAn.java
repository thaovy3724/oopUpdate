import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class DanhSachDuAn implements DanhSach{
	public Scanner sc = new Scanner(System.in);
	
//field
	private ArrayList<DuAn> ds;
	
//constructor
	public DanhSachDuAn() {
		ds = new ArrayList<DuAn>();
	}

	public DanhSachDuAn(ArrayList<DuAn> ds) {
		this.ds = ds;
	}

//get va set dsDUAn
public ArrayList<DuAn> getDanhSachDuAn(){
	return ds;
}

public void setDanhSachDuAn(ArrayList<DuAn> ds) {
	this.ds = ds;
}

//doc file
public void docFile() {
	try {
		BufferedReader input = new BufferedReader(new FileReader("dataDA.txt"));
		String line = input.readLine();
		DuAn da= new DuAn(); 
		while(line!=null) {
			String[] ar = line.split(","); // chia chuỗi thành các chuỗi con phân cách bởi dấu ,
			// vi tri 0 danh dau =1 la du an nhan vien chinh thuc
			if(ar[0].equalsIgnoreCase("1")) {  
				// 1.int maduan, 2.String tenduan, 3.double loi nhuan, 4.double ngan sach, 5.int newID, 6.String newHo, 7.String newTen, 8.String newNgaySinh, 9.int newMaPhongBan, 10.double newTienBaoHiem
				NhanVienChinhThuc nv = new NhanVienChinhThuc(Integer.parseInt(ar[5]), ar[6], ar[7], ar[8], Integer.parseInt(ar[9]), Double.parseDouble(ar[10]));
				//String maDuAn,String tenDuAn, int loiNhuan, int nganSach, NhanVien nv
				da = new DuAnCaNhan(Integer.parseInt(ar[1]), ar[2], Double.parseDouble(ar[3]), Double.parseDouble(ar[4]), nv);
			}
			 // vi tri 0 danh dau =2 la du an nhan vien thoi vu
			if(ar[0].equalsIgnoreCase("2")) {
				//1.int maduan, 2.String tenduan, 3.double loi nhuan, 4.double ngan sach, 5.int newID, 6.String newHo, 7.String newTen, 8.String newNgaySinh, 9.int newMaPhongBan, 10.thoiHanLamViec
				NhanVienThoiVu nv = new NhanVienThoiVu(Integer.parseInt(ar[5]), ar[6], ar[7], ar[8], Integer.parseInt(ar[9]), Integer.parseInt(ar[10]));
				//String maDuAn,String tenDuAn, int loiNhuan, int nganSach, NhanVien nv
				da = new DuAnCaNhan(Integer.parseInt(ar[1]), ar[2], Double.parseDouble(ar[3]), Double.parseDouble(ar[4]), nv);
			}
			// vi tri 0 danh dau =3 la du an phong ban
			if(ar[0].equalsIgnoreCase("3")) 
		//1.int maduan, 2.String tenduan, 3.double loi nhuan, 4.double ngan sach,5. mapb, 6.tenpb
				da = new DuAnPhongBan(Integer.parseInt(ar[1]), ar[2], Double.parseDouble(ar[3]), Double.parseDouble(ar[4]), Integer.parseInt(ar[5]), ar[6]);
			ds.add(da);
			line=input.readLine();
			}
			input.close();
			DuAn.init(da.getMaDuAn());
		}
		catch (Exception ex) { // mở file bị lỗi sẽ dô catch
			ex.printStackTrace();
		}	
	
	}
//ghi file
		public void ghiFile() {
				try {
					FileWriter f = new FileWriter("dataDA.txt");
					String s = new String();
					for(DuAn da : ds) {
						if(da instanceof DuAnCaNhan) {   
							 // du an ca nhan - nhan vien chinh thuc 
							DuAnCaNhan da1 = (DuAnCaNhan) da;
							if(da1.getNhanVien() instanceof NhanVienChinhThuc){
								NhanVienChinhThuc nv1 = (NhanVienChinhThuc) da1.getNhanVien();
								// 1.int maduan, 2.String tenduan, 3.double loi nhuan, 4.double ngan sach, 5.int newID, 6.String newHo, 7.String newTen, 8.String newNgaySinh, 9.int newMaPhongBan, 10.double newTienBaoHiem
								s = 1+","+da1.getMaDuAn()+","+da1.getTenDuAn()+","+da1.getLoiNhuan()+","+da1.getNganSach()+","+nv1.getID()+","+nv1.getHo()+","+nv1.getTen()+","+nv1.getNgaySinh()+","+nv1.getMaPhongBan()+","+nv1.getTienBaoHiem()+"\n";
							}
							else{
								// du an ca nhan - nhan vien thoi vu
								// 1.int maduan, 2.String tenduan, 3.double loi nhuan, 4.double ngan sach, 5.int newID, 6.String newHo, 7.String newTen, 8.String newNgaySinh, 9.int newMaPhongBan, 10.int thoiHanLamViec
								DuAnCaNhan da2 = (DuAnCaNhan) da;
								NhanVienThoiVu nv2 = (NhanVienThoiVu) da2.getNhanVien();
								s = 2+","+da2.getMaDuAn()+","+da2.getTenDuAn()+","+da2.getLoiNhuan()+","+da2.getNganSach()+","+nv2.getID()+","+nv2.getHo()+","+nv2.getTen()+","+nv2.getNgaySinh()+","+nv2.getMaPhongBan()+","+nv2.getThoiHanLamViec()+"\n";
							}
						}
						if(da instanceof DuAnPhongBan) {
							// du an phong ban
							//1.int maduan, 2.String tenduan, 3.double loi nhuan, 4.double ngan sach,5. mapb, 6.tenpb
							DuAnPhongBan da3 = (DuAnPhongBan) da;
							s = 3+","+da3.getMaDuAn()+","+da3.getTenDuAn()+","+da3.getLoiNhuan()+","+da3.getNganSach()+","+da3.getMaPb()+","+da3.getTenPb()+"\n";
						}
						f.write(s);
					}
					f.close();
					//note
					System.out.println("Da ghi du lieu vao file theo duong dan: dataDA.txt");
				}catch (Exception e) {
					System.out.println(e);
				}
		}

	// MENU
	public void menu(DanhSachNhanVien dsnv, DanhSachPhongBan dspb) {
		int luachon;  // bien trong dk vong lap phai khai bao ngoai do while
		do {
			System.out.println("================================");
			System.out.println("---MENU DANH SACH DU AN---");
			System.out.println("|1. Them");
			System.out.println("|2. Tim kiem");
			System.out.println("|3. Xoa");
			System.out.println("|4. Sua");
			System.out.println("|5. Xuat");
			System.out.println("Nhap so khac de thoat !!!"); 
			System.out.print("Nhap lua chon: ");
        	luachon=Integer.parseInt(sc.nextLine());
			switch(luachon) {
				case 1: them(dsnv, dspb); break;
				case 2:tim(); break;
				case 3:xoa(); break;
				case 4:sua(); break;
				case 5: xuat(); break;
				default:  // nhập số khác sẽ dô default xuất ra dòng thóat danh sách....
					System.out.println("---Thoat danh sach du an---"); break;
			}	
		}
		while(luachon>=1 && luachon<=5);
	}
	
	//xuat
	public void xuat() {
		if(ds.size()==0) {
			System.out.println("Danh sach rong!!!");
		}
		else {
			System.out.println("========================");
			System.out.println("Xuat danh sach du an: ");
			for(DuAn a : ds) {
				a.xuat();   // tinh da hinh
				System.out.println("------------------------");
			}
		}
	}

	//them
	public void them(DanhSachNhanVien dsnv, DanhSachPhongBan dspb) { //dspb
		int luachon;
		do {
			System.out.println("Nhap loai du an muon them: ");
			System.out.println("1. Du an ca nhan ");
			System.out.println("2. Du an phong ban ");
			System.out.print("Nhap lua chon: ");
			luachon = Integer.parseInt(sc.nextLine());
			if(luachon!=1 && luachon!=2) System.out.println("Lua chon khong hop le!");
		}
		while(luachon!=1 && luachon!=2); // khi lua chon khac 1 va 2 thi lam lai
		int stt;
		if(luachon==1) {
			DuAnCaNhan a = new DuAnCaNhan();
			ArrayList<NhanVien> dsNhanVien = dsnv.getDanhSachNhanVien();
			System.out.println("========================");
			System.out.println("Danh sach nhan vien :");
			int i = 0;
			for(NhanVien nv : dsNhanVien){
				System.out.println((i++)+". "); nv.xuat();
           		System.out.println("------------------------");
			}
			do{
			System.out.print("Chon so thu tu cua nhan vien: ");
			stt=Integer.parseInt(sc.nextLine());
			if(stt<0 || stt>=dsNhanVien.size()) System.out.println("STT khong hop le!");
			} while(stt<0 || stt>=dsNhanVien.size());
			a.nhap(dsNhanVien.get(stt));
			ds.add(a);
		}
		else {
			DuAnPhongBan a = new DuAnPhongBan();
			ArrayList<PhongBan> dsPhongBan = dspb.getDanhSachPhongBan();
			System.out.println("========================");
			System.out.println("Danh sach phong ban :");
			int i = 0;
			for(PhongBan pb : dsPhongBan){
				System.out.println((i++)+". ");
				System.out.println("ma phong: "+pb.getMaphong());
				System.out.println("ten phong: "+pb.getTenphong());
           		System.out.println("------------------------");
			}
			do{
				System.out.print("Chon so thu tu cua phong ban: ");
				stt=Integer.parseInt(sc.nextLine());
				if(stt<0 || stt>=dsPhongBan. size())
					System.out.println("STT khong hop le!");
			} while(stt<0 || stt>=dsPhongBan.size());
			a.nhap(dsPhongBan.get(stt).getMaphong(), dsPhongBan.get(stt).getTenphong());
			ds.add(a);
		}
		System.out.println("Da them thanh cong!!!");
	}
	
	// MENU Tim Kiem
	public int menuTim() {
		int luachon;
		if(ds.size()==0){
			System.out.println("Danh sach rong !");
			luachon = -1;
		}
		else {
			System.out.println("========================");
			System.out.println("---MENU TIM KIEM---");
			System.out.println("|1. Tim kiem theo ten du an");
			System.out.println("|2. Tim kiem theo ma du an");	
			System.out.println("Nhap so khac de thoat!!!");
			System.out.print("Nhap lua chon: ");
			luachon = Integer.parseInt(sc.nextLine());
		}
		return luachon;
	}
	
	//Tim kiem
	@Override public void tim() { // void : khong tra ve
		int luachon;
        ArrayList<DuAn> kq1 = new ArrayList<>(); //mang de hung ket qua
		DuAn kq2 = new DuAn();
		do{
            luachon = menuTim();
            //switch tren lua chon
            switch (luachon){
                case 1: 
					kq1 = timTenDuAn(); 
					if(kq1.size()==0) System.out.println("Khong tim thay!!!");
					else{
						//xuat ket qua
						System.out.println("========================");
						System.out.println("Ket qua tim kiem :");
						int i=0;
						for(DuAn a : kq1){
							System.out.println((i++)+".");
							a.xuat();
							System.out.println("------------------------");
						}
					}
					break;
				case 2: 
					kq2 = timMaDuAn(); 
					if(kq2==null) System.out.println("Khong tim thay!!!");
					else{
						System.out.println("========================");
						System.out.println("Ket qua tim kiem :");
						kq2.xuat();
						System.out.println("------------------------");
					}
					break;
				default: System.out.println("---Thoat---"); break;
			}
        } while(luachon>=1 && luachon<=2);
	}

	// Tim theo ten du an
	private ArrayList<DuAn> timTenDuAn() { 
		ArrayList<DuAn> kq = new ArrayList<DuAn>();
		String tenCanTim;
		do{
			System.out.print("Nhap ten du an can tim: ");
			tenCanTim = sc.nextLine();
		}while(DuAn.checkTenDuAn(tenCanTim));
		for(DuAn a : ds) {
			if(a.getTenDuAn().equalsIgnoreCase(tenCanTim)) {  
				kq.add(a);
			}
		}
		return kq;
	}

	private DuAn timMaDuAn(){
		int id;
		do{
			System.out.print("Nhap ma du an can tim: ");
			id = Integer.parseInt(sc.nextLine());
		}while(DuAn.checkMaDuAn(id));
		for(DuAn a : ds) {
			if(a.getMaDuAn()==id) {  
				return a;
			}
		}
		return null;
	}
	
	// Xoa
	public void xoa() {
		int luachon;
		ArrayList<DuAn> kq1 = new ArrayList<>(); //mang de hung ket qua
		DuAn kq2 = new DuAn();
		do{
			System.out.println("========================");
			System.out.println("---XOA KHOI DANH SACH---");
			luachon = menuTim();
			//switch tren lua chon
			switch (luachon){
				case 1: 
					kq1 = timTenDuAn(); 
					if(kq1.size()==0) System.out.println("Khong tim thay du an can xoa!!!");
					else{
						//xuat ket qua
						System.out.println("========================");
						System.out.println("Ket qua tim kiem :");
						int i=0;
						for(DuAn a : kq1){
							System.out.println((i++)+".");
							a.xuat();
							System.out.println("------------------------");
						}
						int stt;
						do{
							System.out.print("Chon STT cua du an muon xoa: ");
							stt = Integer.parseInt(sc.nextLine());
							if(stt<0 || stt>=kq1.size()) System.out.println("STT khong hop le!");
						}
						while(stt<0 || stt>=kq1.size());
						ds.remove(kq1.get(stt));
						System.out.println("Da xoa thanh cong!!!");
					}
					break;
				case 2: 
					kq2 = timMaDuAn(); 
					if(kq2==null) System.out.println("Khong tim thay du an can xoa!!!");
					else{
						System.out.println("========================");
						System.out.println("Ket qua tim kiem :");
						kq2.xuat();
						System.out.println("------------------------");
						ds.remove(kq2);
						System.out.println("Da xoa thanh cong!!!");
					}
					break;
				default: System.out.println("---Thoat---"); break;
			} 
		} while(luachon>=1 && luachon<=2);
	}
		
	
	// Sua
	public void sua() {
		int luachon;
		ArrayList<DuAn> kq1 = new ArrayList<>(); //mang de hung ket qua
		DuAn kq2 = new DuAn();
		do{
			DuAn k = null;
			System.out.println("========================");
			System.out.println("---SUA THONG TIN---");
			luachon = menuTim();
			switch (luachon){
				case 1: 
					kq1 = timTenDuAn(); 
					if(kq1.size()==0) System.out.println("Khong tim thay du an can sua!!!");
					else{
						//xuat ket qua
						System.out.println("========================");
						System.out.println("Ket qua tim kiem :");
						int i=0;
						for(DuAn a : kq1){
							System.out.println((i++)+".");
							a.xuat();
							System.out.println("------------------------");
						}
						int stt;
						do{
							System.out.print("Chon STT cua du an muon sua: ");
							stt = Integer.parseInt(sc.nextLine());
							if(stt<0 || stt>=kq1.size()) System.out.println("STT khong hop le!");
						} while(stt<0 || stt>=kq1.size());
						k=kq1.get(stt);
					}
					break;
				case 2: 
					kq2 = timMaDuAn(); 
					if(kq2==null) System.out.println("Khong tim thay du an can sua!!!");
					else{
						System.out.println("========================");
						System.out.println("Ket qua tim kiem :");
						kq2.xuat();
						System.out.println("------------------------");
						k=kq2;
					}
					break;
				default: System.out.println("---Thoat---"); break;
        	}
					//***nhap lua chon thong tin muon sua***
					int sua;
					/*sua truc tiep tren dsbangluong*/
					
					if(k!=null){
					do{
						System.out.println("========================");
						System.out.println("---MENU SUA---");
						System.out.println("|1. Sua ten du an ");
						System.out.println("|2. Sua loi nhuan ");
						System.out.println("|3. Sua ngan sach ");
						System.out.println("Nhap so khac de thoat !!!");
						System.out.print("Nhap lua chon : ");
						sua = Integer.parseInt(sc.nextLine());
						switch(sua) {
						case 1:
							String tenDuAn;
							do{
								System.out.print("Nhap ten du an moi: ");
								tenDuAn = sc.nextLine();
							} while(DuAn.checkTenDuAn(tenDuAn));
							k.setTenDuAn(tenDuAn);
							break;
						case 2:
							System.out.print("Nhap loi nhuan moi: ");
							Double loiNhuan = Double.parseDouble(sc.nextLine());
							k.setLoiNhuan(loiNhuan);
							break;
						case 3:
							Double nganSach;
							do{
								System.out.print("Nhap ngan sach moi: ");
								nganSach = Double.parseDouble(sc.nextLine());
							} while(k.checkNganSach(nganSach));
							k.setNganSach(nganSach);
							break;
						default: System.out.println("---Thoat---"); break;
						}
						if(sua>=1 && sua<=3){
							System.out.println("Da sua thanh cong!!!");
						}
					}
					while(sua>=1 && sua<=3);
				}
		}while(luachon>=1 && luachon<=2);
}
//ham cap nhat
	/*
	dsDuAn.xoaNhanVien(idCanXoa, idPBCanXoa):
		duyet mang du an:
			+ neu la duancannhan thi ktra idnv = idcanxoa -> remove
			+ neu la duanphongban thi ktra idpb = idpbcanxoa: //idpb=0 thi bo qua
				duyet qua nhan vien = idcanxoa ->remove + cap nhat so luong nhan vien
				*/
			
	public void xoaNhanVien(NhanVien nvCanXoa){
		int idCanXoa = nvCanXoa.getID();
		DuAnCaNhan da;
		for(int i=0; i<ds.size(); i++)
			if(ds.get(i) instanceof DuAnCaNhan){
				da = (DuAnCaNhan) ds.get(i);
				if(da.getNhanVien().getID() == idCanXoa){
					ds.remove(da);
					i--;
				}					
			}
	}
	
	/*
	dsDuAn.suaNhanVien(nvSauKhiSua);
		duyet mang du an:
			+ neu la duancannhan , ktra maid cua nhanvien == maIDCanSua -> da.setNhanVien(nvSauKhiSua)
			+ neu la duanphongban thi ktra idpb = idpbcanxoa: //idpb=0 thi bo qua
				duyet qua nhan vien = idcansua -> set*/

	public void suaNhanVien(NhanVien nv){
		DuAnCaNhan da;
		for(DuAn a : ds)
			if(a instanceof DuAnCaNhan){
				da = (DuAnCaNhan) a;
				if(da.getNhanVien().getID() == nv.getID())
					da.setNhanVien(nv);
			}
	}

	public void xoaIDPB(int idCanXoa){
		DuAnPhongBan da1;
		DuAnCaNhan da2;
		for(int i=0; i<ds.size(); i++)
			if(ds.get(i) instanceof DuAnPhongBan){
				da1 = (DuAnPhongBan) ds.get(i);
				if(da1.getMaPb() == idCanXoa){
					ds.remove(da1);
					i--;
				}					
			}
			else{
				da2 = (DuAnCaNhan) ds.get(i);
				if(da2.getNhanVien().getMaPhongBan() == idCanXoa)
					da2.getNhanVien().setMaPhongBan(0);
			}
	}

	public void suaTenPb(PhongBan pb){
		DuAnPhongBan da1;
		for(DuAn a: ds)
			if(a instanceof DuAnPhongBan){
				da1 = (DuAnPhongBan) a;
				if(da1.getMaPb() == pb.getMaphong()){
					da1.setTenPb(pb.getTenphong());
				}
			}
	}
}