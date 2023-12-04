public class BangLuongThoiVu extends BangLuong{
    //field
        private double tienThuong;
        private int soGio;
        private double mucLuongTheoGio;
    
    //constructor
        public BangLuongThoiVu(){
            super();
            tienThuong=0;
            soGio=0;
            mucLuongTheoGio=0;
        }
    
        public BangLuongThoiVu(NhanVienThoiVu nv, int soGio, double mucLuongTheoGio,  double tienThuong, double thanhToan){
            super(nv, thanhToan);
            this.tienThuong=tienThuong;
            this.soGio=soGio;
            this.mucLuongTheoGio=soGio;
        }
    
        public BangLuongThoiVu(NhanVienThoiVu nv, int soGio, double mucLuongTheoGio,  double tienThuong,
                            double thue, double thucLanh, double thanhToan){ //kiem tra dieu kien truoc khi pass parameter
            super(nv,thue, thucLanh, thanhToan);
            this.tienThuong=tienThuong;
            this.soGio=soGio;
            this.mucLuongTheoGio=soGio;
        }
    //nhap
        @Override public void nhap(NhanVien nv){
            do{
                System.out.print("Nhap so gio lam: ");
                soGio=Integer.parseInt(sc.nextLine());
            }while(checkSoGioLam(soGio));
            do{
                System.out.print("Nhap muc luong theo gio: ");
                mucLuongTheoGio=Double.parseDouble(sc.nextLine());
            }while(checkMucLuongTheoGio(mucLuongTheoGio));
            do{
                System.out.print("Nhap tien thuong: ");
                tienThuong=Double.parseDouble(sc.nextLine());
            }while(checktienThuong(tienThuong));
            super.nhap(nv);
        }
    
    //xuat
        @Override public void xuat(){
            super.getNhanVien().xuat();
            System.out.println("so gio lam: "+soGio);
            System.out.println("luong theo gio: "+mucLuongTheoGio);
            System.out.println("tien thuong: "+tienThuong);
            super.xuat();
        }
    
    //override abstract method
        @Override public double tongLuong(){
            return soGio*mucLuongTheoGio+tienThuong-getThue();
        }
    
        @Override public double tinhThue(){
            return soGio*mucLuongTheoGio*8/100;
        }
    //kiem tra dieu kien
        public boolean checktienThuong(double tienThuong){
            if(tienThuong<0){
                System.out.println("Can nhap lon hon bang 0 !!!");
                return true;
            }
            if(soGio==0 && tienThuong>0) {
                System.out.println("Nhan vien chua di lam nen khong co tien thuong");
                System.out.println("Nhap tien thuong bang 0");
                return true;
            }
            return false;
        }
    
        public static boolean checkSoGioLam(int soGio){
            if(soGio<0){
                System.out.println("Can nhap lon hon bang 0 !!!");
                return true;
            }
            return false;
        }
    
        public static boolean checkMucLuongTheoGio(double mucLuongTheoGio){
            if(mucLuongTheoGio<=0){
                System.out.println("Can nhap lon hon 0 !!!");
                return true;
            }
            return false;
        }
        //get - set
        public double getTienThuong(){
            return tienThuong;
        }
    
        public void setTienThuong(double tienThuong){
            this.tienThuong = tienThuong;
            setThue(tinhThue());
            setThucLanh(tongLuong());
        }
    
        public void setSoGioLam(int soGio){
            this.soGio=soGio;
            setThue(tinhThue());
            setThucLanh(tongLuong());
        }
    
        public int getSoGioLam(){
            return soGio;
        }
    
        public void setMucLuongTheoGio(double tien){
            mucLuongTheoGio=tien;
            setThue(tinhThue());
            setThucLanh(tongLuong());
        }
    
        public double getMucLuongTheoGio(){
            return mucLuongTheoGio;
        }
    }
    