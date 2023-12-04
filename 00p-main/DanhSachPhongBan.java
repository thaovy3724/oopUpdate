import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class DanhSachPhongBan implements DanhSach {
    public Scanner sc = new Scanner(System.in);
    private ArrayList<PhongBan> danhSachPB = new ArrayList<PhongBan>();
    
    // constructor
    public DanhSachPhongBan (){
        danhSachPB = new ArrayList<PhongBan>();
    }
    public DanhSachPhongBan (ArrayList<PhongBan> danhSachPB) {
        this.danhSachPB = danhSachPB;
    }

    // get & set dsPB
    public ArrayList<PhongBan> getDanhSachPhongBan() {
        return danhSachPB;
    }
    
    public void setDanhSachPhongBan(ArrayList<PhongBan> danhSachPB) {
        this.danhSachPB = danhSachPB;
    }
    // xuat pb
    @Override public void xuat() {
        if (danhSachPB.size()==0)
            System.out.println("----- DS RONG -----"); 
        else {
            System.out.println("========================================");
            System.out.println("------------- DS PHONG BAN -------------");
            for(PhongBan pb : danhSachPB){
                pb.xuat();
            }
        }
    }

    // menu 
    public void menu(DanhSachNhanVien dsnv, DanhSachBangLuong dsbl, DanhSachDuAn dsda) {
        int choice;
        do {
            System.out.println("========================================");
            System.out.println(" ------ MENU Danh Sach Phong Ban ------");
            System.out.println("========================================");
            System.out.println("1. Them \n2. Sua \n3. Xoa \n4. Tim kiem \n5. Xuat DS \n0. Thoat");
            System.out.print("Vui long chon chuc nang thuc hien: ");
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("==========================================");
                    System.out.println("Da Thoat Khoi Quan Ly Doi Tuong Phong Ban");
                    System.out.println("==========================================");
                    break;
                case 1:
                    them(dsnv, dsbl, dsda);
                    break;
                case 2:
                    sua(dsnv, dsbl, dsda);
                    break;
                case 3:
                    xoa(dsnv, dsbl, dsda);
                    break;
                case 4:
                    tim();
                    break;
                case 5:
                    xuat();
                    break;
                default:
                    System.out.println("Chuc nang khong hop le, vui long chon lai. . .");
                    break;
            }
        }while(choice!=0);
    }
    
    // menu tim kiem
    @Override public int menuTim(){

        // kiểm tra nếu danh sách rỗng, nếu rỗng trả về trừ 1
        int choice;
        if (danhSachPB.size()==0) {
            System.out.println("---- Khong Con Phong Ban Nao Trong DS -----"); 
            choice=-1;
        }
        // nếu danh sách không rỗng -> cho người dùng nhập lựa chọn tìm
        else{
            do {
                System.out.println("========================================");
                System.out.println("1. Tim theo ma phong \n2. Tim theo ten phong\n--Nhap so khac de thoat!--");
                System.out.print("\nVui long nhap lua chon: ");
                choice = sc.nextInt();
                sc.nextLine();
                if (choice < 0 && choice > 2) {
                    System.out.println("Lua chon khong hop le, vui long chon lai. . .");
                }
            } while (choice < 0 && choice > 2);
        }
        return choice;
    }

    // them
    public void them (DanhSachNhanVien dsnv, DanhSachBangLuong dsbl, DanhSachDuAn dsda){
        PhongBan pb = new PhongBan();

        // danh sách phòng ban rỗng
        ArrayList<NhanVien> dsNVChuaCoPB = new ArrayList<>();
        // xem trong danh sách nhân viên có nhân viên nào chưa có phòng ban
        for (NhanVien nv : dsnv.getDanhSachNhanVien()) {
            if (nv.getMaPhongBan() == 0)
                dsNVChuaCoPB.add(nv);
        }
        // cho người dùng thêm các nhân viên chưa có phòng ban vào phòng ban mới
        pb.nhap(dsNVChuaCoPB, dsbl, dsda);
        danhSachPB.add(pb);
    }

    // tim kiem
    @Override public void tim(){
        int choice;
        do {
            // xuất menu tìm kiếm
            choice = menuTim();
            PhongBan ch = new PhongBan();
            // cho người dùng chọn tìm theo mã phòng hoặc tên phòng
            switch (choice) {
                case 0:
                    System.out.println(" ---------------- THOAT ---------------");
                    break;
                case 1:
                    ch = timMaPhong();
                    break;
                case 2:
                    ch = timTenPhong();
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long thu lai. . .");
                    break;
            }
            if (choice >=1 && choice <=2)
            {
                // so sánh nội dung cần tìm và các đối tượng có sẵn trong danh sách nếu không tìm thấy thì thực hiện dòng if
                if(ch == null) {
                    System.out.println("Khong tim thay noi dung can tim. . .");
                    System.out.println("========================================");
                }
                // nếu tìm thấy thì thực hiện dòng else
                else {
                    System.out.println("========================================");
                    System.out.println("Ket Qua Tim Kiem: ");
                    // xuất ra kết quả tìm được
                    ch.xuat();
                }
            }
        } while(choice!=0);
    }

    // tìm mã phòng
    private PhongBan timMaPhong(){ 
        int maPBCanTim;
        do {
            System.out.print("Nhap ma phong ban can tim (5 chu so): ");
            maPBCanTim=sc.nextInt();
            String maPBCanTimString = Integer.toString(maPBCanTim);
            if (maPBCanTimString.length()!=5) {
                System.out.println("Ma phong nhap vao khong hop le, vui long thu lai. . .");
            }
        } while(Integer.toString(maPBCanTim).length()!=5);

        for(PhongBan pb : danhSachPB){
            int mapb = pb.getMaphong();
            if(mapb == maPBCanTim) 
                return pb;
        }
        return null;
    }

    // tìm tên phòng
    private PhongBan timTenPhong(){ 
        String tenPBCanTim;
        do {
            System.out.print("Nhap ten phong can tim: ");
            tenPBCanTim = sc.nextLine();
            if (tenPBCanTim.trim().isEmpty())
            {
                System.out.println("Ten phong ban khong duoc de trong. . .");
            }
        }while (tenPBCanTim.trim().isEmpty());
        for (PhongBan pb : danhSachPB) {
            String tenpb = pb.getTenphong();
                if(tenpb.equalsIgnoreCase(tenPBCanTim))  {
                    return pb;
                }
        }
        return null;


        // ArrayList<PhongBan> ch = new ArrayList<>();
        // System.out.print("Nhap ten phong can tim: ");
        // String tenPBCanTim=sc.nextLine();
        // for(PhongBan pb : danhSachPB){
        //     String tenpb = pb.getTenphong();
        //     if(tenpb.equals(tenPBCanTim)) // so sánh 2 chuỗi, trả về true / false
        //         ch.add(pb);
        // }
        // return ch;
    }
    
    // xóa
    public void xoa(DanhSachNhanVien dsnv, DanhSachBangLuong dsbl, DanhSachDuAn dsda){
        int choice;
        do{
            System.out.println("========================================");
            System.out.println(" ----------- Xoa Phong Ban ------------");
            // xuất menu tìm kiếm
            choice = menuTim();
            PhongBan ch = new PhongBan();
            // cho người dùng nhập lựa chọn tìm theo mã phòng hay tên phòng rồi mới thực hiện chức năng xóa
            switch (choice) {
                case 1:
                    ch = timMaPhong();
                    break;
                case 2:
                    ch = timTenPhong();
                    break;
                default:
                    System.out.println(" ---------------- THOAT ---------------");
                    return;
            }

            if (choice >= 1 && choice <= 2) {
                // nếu như không tìm thấy mã phòng hoặc tên phòng thì thực hiện câu if bên dưới
                if(ch==null)
                    System.out.println("Khong tim thay phong ban can tim . . . ");
                // nếu tìm thấy sẽ xuất ra kết quả tìm kiếm được
                else{
                    System.out.println("========================================");
                    System.out.println("Ket qua tim kiem: ");
                    ch.xuat();
                    // thực hiện xóa phòng ban và mã phòng ban ở 3 đối tượng còn lại (vì ba đối tượng còn lại chứa các nhân viên mang mã phòng ban của phòng vừa xóa)
                    danhSachPB.remove(ch);
                    dsnv.xoaIDPB(ch.getMaphong());
                    dsda.xoaIDPB(ch.getMaphong());
                    dsbl.xoaIDPB(ch.getMaphong());
                    // xuất thông báo đã xóa thành công
                    System.out.println("========================================");
                    System.out.println(" --------- Da Xoa Thanh Cong ----------");
                }
            }
        } while(choice!=0);
    }
    
    // sua
    public void sua(DanhSachNhanVien dsnv, DanhSachBangLuong dsbl, DanhSachDuAn dsda){
        int choice;
    do{
        System.out.println("========================================");
        System.out.println("----- Sua Thong Tin Phong Ban -----");
        choice = menuTim();
        PhongBan ch = new PhongBan();
        switch (choice) {
            case 1:
                ch = timMaPhong();
                break;
            case 2: 
                ch = timTenPhong();
                break;
            default:
                System.out.println(" ---------------- THOAT ---------------");        
                break;
        }

        if(choice >= 1 && choice <= 2){
            // nếu như không tìm thấy mã phòng hoặc tên phòng thì thực hiện câu if bên dưới
            if(ch==null)
                System.out.println("Khong tim thay phong ban can tim. . . ");
            // nếu tìm thấy thì thực hiện dòng else bên dưới
            else{
                // xuất phòng ban vừa tìm được
                System.out.println("========================================");
                System.out.println("Ket qua tim kiem: ");
                System.out.println("----------------------"); 
                ch.xuat();
                
                int sua;
                PhongBan pb = ch;
 
                do {
                    // menu thông tin cần sửa
                    System.out.println("========================================");
                    System.out.println("----- Menu Sua Thong Tin Phong Ban -----");
                    System.out.println("1. Sua ten phong \n2. Sua ma truong phong \n3. Them nhan vien\n4. Xoa nhan vien\n0. Thoat");
                    // nhập lựa chọn thông tin muốn sửa
                    System.out.print("Nhap lua chon: ");
                    sua = sc.nextInt();
                    sc.nextLine();
                    System.out.println("-------------------------");
                    // thực hiện sửa các thông tin 
                    switch (sua) {
                        case 0:
                            System.out.println(" ---------------- THOAT ---------------");
                            break;
                        case 1:
                            String newTenPhong;
                            // kiểm tra điều kiện nhập tên phòng ban mới, không được để tên phòng rỗng
                            do {
                                System.out.print("Nhap ten phong moi: ");
                                newTenPhong = sc.nextLine();
                                if (newTenPhong.trim().isEmpty()) {
                                    System.out.println("Ten phong ban khong duoc de trong. . .");
                                }
                            }while(newTenPhong.trim().isEmpty());
                            // thực hiện sửa tên phòng
                            pb.setTenphong(newTenPhong);
                            // thực hiện sửa tên đề án phòng ban
                            dsda.suaTenPb(pb);
                            System.out.println("---Da sua ten phong ban thanh cong---");
                            break;
                        case 2:
                            int newTruongphong;
                            int j=0;
                            // xuất danh sách nhân viên hiện có trong phòng ban để chọn ra trưởng phòng mới
                            for(NhanVien nv : pb.getDSNhanVien()) {
                                System.out.println((j++) + "."); nv.xuat();
                                System.out.println("=============================");
                            }
                            int stt2;
                            // cho người dùng nhập STT nhân viên có trong danh sách vừa xuất ra
                            // kiểm tra điều kiện nhập STT trong danh sách trên
                            do {
                                System.out.print("Chon STT cua nhan vien muon chon lam truong phong: ");
                                stt2 = Integer.parseInt(sc.nextLine());
                                if (stt2<0 || stt2>=pb.getDSNhanVien().size()) {
                                    System.out.println("STT nhan vien khong hop le, vui long thu lai. . .");
                                }
                            } while(stt2<0 || stt2>=pb.getDSNhanVien().size());
                            // thực hiện set lại trưởng phòng mới cho phòng ban
                            newTruongphong = pb.getDSNhanVien().get(stt2).getID();
                            pb.setTrgphong(newTruongphong);
                            System.out.println("========================================");
                            System.out.println("--- Thay Doi Truong Phong Thanh Cong ---");
                            break;
                        case 3:
                            // tạo danh sách các nhân viên rỗng (danh sách nhân viên chưa có phòng ban)
                            ArrayList<NhanVien> dsNhanVienRong = new ArrayList<>();
                            // lấy các nhân viên chưa có phòng ban trong danh sách nhân viên để thêm vào danh sách nhân viên rỗng
                            for (NhanVien nv : dsnv.getDanhSachNhanVien()) {
                                if (nv.getMaPhongBan() == 0)
                                    dsNhanVienRong.add(nv);
                            } 

                            // xét nếu tất cả các nhân viên đều đã có phòng ban thì xuất thông báo rồi thoát
                            if (dsNhanVienRong.size() == 0)
                            {
                                // note!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                System.out.println("Hien tai khong co nhan vien nao de them vao phong ban. . .");
                                break;
                            }

                            // xuất danh sách các nhân viên chưa có phòng ban
                            int n = 0;
                            for(NhanVien nv : dsNhanVienRong) {
                                System.out.println((n++) + ".");
                                nv.xuat();
                                System.out.println("=============================");
                            }
                            // cho người dùng chọn STT nhân viên muốn thêm vào các phòng ban đã có sẵn
                            int stt3;
                            // kiểm tra điều kiện nếu STT nhập vào hợp lệ
                            do {
                                System.out.print("Chon STT cua nhan vien muon them vao phong ban: ");
                                stt3 = Integer.parseInt(sc.nextLine());
                                if (stt3<0 || stt3>=dsNhanVienRong.size() || dsNhanVienRong.get(stt3).getMaPhongBan()!=0) {
                                    System.out.println("STT nhan vien khong hop le, vui long thu lai. . .");
                                }
                            } while(stt3<0 || stt3>=dsNhanVienRong.size());
                            
                            // thực hiện thêm nhân viên vào phòng ban
                            NhanVien nhanvien = dsNhanVienRong.get(stt3);
                            nhanvien.setMaPhongBan(pb.getMaphong());
                            // thực hiện thêm mã phòng ban ở các đối tượng có nhân viên vừa được thêm vào phòng ban
                            dsda.suaNhanVien(nhanvien);
                            dsbl.suaNhanVien(nhanvien);
                            pb.getDSNhanVien().add(nhanvien);
                            pb.setSLNV(pb.getDSNhanVien().size());
                            System.out.println("--- Da Them Nhan Vien Vao Phong Ban ---");
                            break;
                        case 4:
                            // kiểm tra hiện tại có nhân viên nào trong phòng ban để xóa không
                            if(pb.getDSNhanVien().size() == 0) {
                                System.out.println("Hien tai khong co nhan vien nao de xoa trong phong ban. . .");
                                break;
                            }
                            // xuất các nhân viên đã có trong phòng ban để thực hiện chức năng xóa
                            int m = 0;
                            for(NhanVien nv : pb.getDSNhanVien()) {
                                System.out.println((m++) + ".");
                                nv.xuat();
                                System.out.println("=============================");
                            }
                            // cho người dùng chọn STT của nhân viên muốn xóa khỏi phòng ben trong danh sách trên
                            int stt4;
                            do {
                                System.out.print("Chon STT cua nhan vien muon xoa khoi phong ban: ");
                                stt4 = Integer.parseInt(sc.nextLine());
                                // tránh việc xóa nhân viên đó 2 lần
                                if(stt4<0 || stt4>=pb.getDSNhanVien().size()) {
                                    System.out.println("STT nhan vien khong hop le, vui long thu lai. . .");
                                }
                            } while(stt4<0 || stt4>=pb.getDSNhanVien().size());
                            // thực hiện xóa nhân viên ra khỏi phòng ban
                            NhanVien nvDuocChon = pb.getDSNhanVien().get(stt4);
                            // cap nhat
                            nvDuocChon.setMaPhongBan(0);
                            // và xóa mã phòng ban ở các đối tượng có nhân viên vừa bị xóa ở phòng ban
                            dsda.suaNhanVien(nvDuocChon);
                            dsbl.suaNhanVien(nvDuocChon);
                            dsnv.suaNhanVien(nvDuocChon.getID(), nvDuocChon.getMaPhongBan());
                            // xóa nhân viên khỏi phòng ban
                            pb.getDSNhanVien().remove(nvDuocChon);
                            // xét trường hợp nếu nhân viên vừa xóa là trưởng phòng set lại mã trưởng phòng cho phòng ban = 0 (chưa có ai là trưởng phòng trong phòng ban đó)
                            if (nvDuocChon.getID() == pb.getTrgphong()) {
                                pb.setTrgphong(0);
                                System.out.println("--- Da xoa truong phong ---");
                            }
                            System.out.println("--- Da xoa thanh cong ---");
                            // thực hiện cập nhật số lượng nhân viên trong phòng ban
                            pb.setSLNV(pb.getDSNhanVien().size());
                            break;
                        default:
                            System.out.println("Lua chon khong hop le, vui long thu lai. . .");
                            break;
                    }
                }while(sua != 0);
            }
        }
    }
        while(choice!=0);
    }

    // DOC FILE 
    public void docFile() {
        try {
            BufferedReader input = new BufferedReader(new FileReader("dataPB.txt"));
            String line = input.readLine();
            PhongBan phongban = new PhongBan();
            while(line!=null) {
                String ar[] = line.split(",");
                int maphong = Integer.parseInt(ar[0]);
                String tenphong = ar[1];
                int truongphong = Integer.parseInt(ar[2]);
                int slnv = Integer.parseInt(ar[3]);
                ArrayList<NhanVien> dsnv = new ArrayList<>();
                for (int i = 0; i<slnv; i++) {
                    line = input.readLine();
                    ar = line.split(",");
                    if (ar[0].equalsIgnoreCase("1")) {
                        NhanVienChinhThuc nv = new NhanVienChinhThuc(Integer.parseInt(ar[1]), (ar[2]), (ar[3]), (ar[4]), Integer.parseInt(ar[5]), Double.parseDouble(ar[6]));
                        dsnv.add(nv);
                    }
                    else if (ar[0].equalsIgnoreCase("2")) {
                        NhanVienThoiVu nv = new NhanVienThoiVu(Integer.parseInt(ar[1]), (ar[2]), (ar[3]), (ar[4]), Integer.parseInt(ar[5]), Integer.parseInt(ar[6]));
                        dsnv.add(nv);
                    }
                }
                // int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan, double newTienBaoHiem
                //int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan, int newThoiHanLamViec 
                phongban= new PhongBan(maphong, tenphong, truongphong, slnv, dsnv);
                danhSachPB.add(phongban);
                line = input.readLine();
            }
            input.close();
            PhongBan.init(phongban.getMaphong());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    // GHI FILE
    @Override public void ghiFile() {
        try {
            File f = new File("dataPB.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            String line = "";
            for (PhongBan p : danhSachPB) {
                line = p.getMaphong() + "," + p.getTenphong() + "," + p.getTrgphong() + "," + p.getSLNV() + "\n";
                bw.write(line); 
                NhanVienChinhThuc nv1;
                NhanVienThoiVu nv2;
                ArrayList<NhanVien> dsnv = p.getDSNhanVien();
                if (dsnv.size() > 0) {
                    for(NhanVien nv : dsnv) {
                        // int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan, double newTienBaoHiem
                        //int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan, int newThoiHanLamViec 
                        if(nv instanceof NhanVienChinhThuc) {
                            nv1 = (NhanVienChinhThuc)nv;
                            line = 1 + "," + nv1.getID() + "," + nv1.getHo() + "," + nv1.getTen() + "," + nv1.getNgaySinh() + "," + nv1.getMaPhongBan() + "," + nv1.getTienBaoHiem() + "\n";
                        }
                        else if (nv instanceof NhanVienThoiVu) {
                            nv2 = (NhanVienThoiVu)nv;
                            line = 2 + "," + nv2.getID() + "," + nv2.getHo() + "," + nv2.getTen() + "," + nv2.getNgaySinh() + "," + nv2.getMaPhongBan() + "," + nv2.getThoiHanLamViec() + "\n";
                        }
                        bw.write(line);
                    }
                }
            }
            bw.close();
            fw.close(); 
            System.out.println("Da ghi du lieu vao file theo duong dan: "+f);
        } catch(Exception e) {
        }
    }

    // hàm phụ: xóa nhân viên trong danh sách nhân viên
    public void xoaNhanVien(NhanVien nvCanXoa) {
        int idPhongBan = nvCanXoa.getMaPhongBan();
        int idCanXoa = nvCanXoa.getID();
        ArrayList<NhanVien> ds;
        if(idPhongBan != 0) {
            for (PhongBan pb : danhSachPB) {
                if (idPhongBan == pb.getMaphong()) {
                    ds = pb.getDSNhanVien();
                    for(int i=0; i<ds.size(); i++) {
                        if(idCanXoa == ds.get(i).getID()) {
                            ds.remove(ds.get(i));
                            pb.setSLNV(ds.size());
                        }
                    }
                }
            }
        }
    }

    // hàm phụ: sửa nhân viên trong danh sách nhân viên
    public void suaNhanVien(NhanVien nv) {
        int id = nv.getID();
        ArrayList<NhanVien> ds;
        int mapb = nv.getMaPhongBan();
        for (int i = 0; i<danhSachPB.size(); i++) {
            if(danhSachPB.get(i).getMaphong() == mapb) {
                ds = danhSachPB.get(i).getDSNhanVien();
                for(int j = 0; j<ds.size(); j++) {
                    if(ds.get(j).getID() == id) {
                        ds.set(j, nv);
                    }
                }
            }
        }
    }
}