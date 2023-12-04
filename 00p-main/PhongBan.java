import java.util.ArrayList;
import java.util.Scanner;

public class PhongBan {
    Scanner sc = new Scanner(System.in);

    private static int idMP = 10001;
    private int maphong; 
    private String tenphong; // ten phong
    private int trgphong; // ma truong phong kieu int
    private int slnv;
    private ArrayList<NhanVien> dsNhanvien = new ArrayList<>();
    
    // danh sach nhan vien chua thuoc phong ban nao
    // số lượng nhân viên (cho tăng tự động mỗi khi thêm nhân viên)


    // constructor 
    public PhongBan () {
    }


    // constructor PhongBan không có ArrayList dsNhanVien
    public PhongBan (int maphong, String tenphong, int trgphong, int slnv)
    {
        this.maphong=idMP++;
        this.tenphong=tenphong;
        this.trgphong=trgphong;
        this.slnv = slnv;
    }

    // constructor PhongBan có ArrayList dsNhanVien
    public PhongBan (int maphong, String tenphong, int trgphong, int slnv, ArrayList<NhanVien> dsNhanvien)
    {
        this.maphong=maphong;
        this.tenphong=tenphong;
        this.trgphong=trgphong;
        this.slnv = slnv;
        this.dsNhanvien = dsNhanvien;
    }
    public void nhap(ArrayList<NhanVien> dsNhanVienRong, DanhSachBangLuong dsbl, DanhSachDuAn dsda)
    {
        // 
        maphong = idMP++;
        do {
            System.out.print("Nhap ten phong: ");
            tenphong = sc.nextLine();
            if (tenphong.trim().isEmpty())
            {
                System.out.println("Ten phong ban khong duoc de trong. . .");
            }
        }while(tenphong.trim().isEmpty());

        // xét trường hợp không có nhân viên nào để thêm vào phòng ban -> tạo phòng ban rồi để slnv = 0
        if(dsNhanVienRong.size()==0) {
            System.out.println("Hien tai khong co nhan vien nao de them vao phong ban. . .");
            trgphong = 0;
            slnv = 0;
            System.out.println("--- Da tao phong ban thanh cong ---");
            return;
        }
        System.out.println("=============================");
        System.out.println("Danh sach nhan vien chua thuoc phong ban nao: ");
        int i = 0;
        for (NhanVien nv : dsNhanVienRong) {
            System.out.println((i++) + ".");
            nv.xuat();
            System.out.println("=============================");
        }
        int stt;
        do {
            System.out.print("Nhap so luong nhan vien muon them: ");
            slnv = Integer.parseInt(sc.nextLine());
            if (slnv <= 0 || slnv > dsNhanVienRong.size()) {
                System.out.println("So luong nhan vien khong hop le\nSo luong nhan vien toi da co the nhap la " +dsNhanVienRong.size()+"\nVui long thu lai. . .");
            }
        }while(slnv <= 0 || slnv > dsNhanVienRong.size());
        for (int k=0; k<slnv; k++) {
            do {
                System.out.print("Nhap STT nhan vien muon them: ");
                stt = Integer.parseInt(sc.nextLine());
                if (stt < 0 || stt >= dsNhanVienRong.size() || dsNhanVienRong.get(stt).getMaPhongBan()!=0) {
                    System.out.println("STT nhan vien khong hop le, vui long thu lai. . .");
                }
            } while(stt < 0 || stt >= dsNhanVienRong.size() || dsNhanVienRong.get(stt).getMaPhongBan()!=0);
            
            NhanVien nhanvien = dsNhanVienRong.get(stt);
            nhanvien.setMaPhongBan(maphong);
            // note !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            dsbl.suaNhanVien(nhanvien);
            dsda.suaNhanVien(nhanvien);
            dsNhanvien.add(nhanvien);

            System.out.println("----- Da Them Nhan Vien Thanh Cong -----");
            System.out.println("========================================");
        }
        System.out.println("Danh sach nhan vien trong phong ban: ");
        int j = 0;
        for(NhanVien nv : dsNhanvien) {
            System.out.println((j++) + ".");
            nv.xuat();
            System.out.println("=============================");
        }
        int stt2;
        do {
            System.out.print("Nhap STT muon chon nhan vien lam truong phong: ");
            stt2 = Integer.parseInt(sc.nextLine());
            if(stt2 < 0 || stt2 >= dsNhanvien.size()) {
                System.out.println("STT nhan vien khong hop le, vui long thu lai. . .");
            }
        } while (stt2 < 0 || stt2 >= dsNhanvien.size());
        NhanVien nhanvien2 = new NhanVien();
        nhanvien2 = dsNhanvien.get(stt2);
        trgphong = nhanvien2.getID();
        System.out.println("----- Chon Truong Phong Thanh Cong -----");
        System.out.println("========================================");
        System.out.println("------ Da Tao Phong Ban Thanh Cong -----");
    }

    public static void init(int newID) {
        idMP = newID+1;
    }

    // xuat pb
    public void xuat() {
        System.out.println("========================================");
        System.out.println("Ma phong: " + maphong+ "\nTen phong: " +tenphong+ "\nTruongphong: " + trgphong + "\nSo luong nhan vien: " + slnv);
        System.out.println("---------------------");
        if (dsNhanvien.size()==0) {
            System.out.println("Hien chua co nhan vien nao trong phong ban. . .");
        }
        else {
            System.out.println("Danh sach nhan vien: ");
            for (NhanVien nv : dsNhanvien) {
                System.out.println("---------------------");
                nv.xuat();
            }
        }
    }
    // get&set maphong
    public int getMaphong () {
        return maphong;
    }
    public void setMaphong (int maphong) {
        this.maphong=maphong;
    }

    // get&set tenphong
    public String getTenphong () {
        return tenphong;
    }
    public void setTenphong (String tenphong) {
        this.tenphong=tenphong;
    }

    // get&set trgphong
    public int getTrgphong () {
        return trgphong;
    }
    public void setTrgphong (int trgphong) {
        this.trgphong=trgphong;
    }

    // get&set slnv
    public int getSLNV() {
        return slnv;
    }

    public void setSLNV(int slnv) {
        this.slnv = slnv;
    }

    // get&set dsNhanVien
    public ArrayList<NhanVien> getDSNhanVien() {
        return dsNhanvien;
    }

    public void setDSNhanVien(ArrayList<NhanVien> dsNhanVien) {
        this.dsNhanvien=dsNhanVien;
    }

    // kiem tra mapb
    public static boolean maPhongBanKhongHopLe(int newMaPhongBan) {
		if (newMaPhongBan < 0 || String.valueOf(newMaPhongBan).length() != NhanVien_CONST.NUMBEROFCHAR_MAPHONGBAN)
			return true;
		return false;
	}
}