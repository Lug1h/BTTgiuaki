package com.example.btkso1;

class Employee {
    private int maNhanVien;
    private String hoTen;
    private String chucVu;
    private String email;
    private String soDienThoai;
    private String anhDaiDien;
    private int maDonVi;

    public Employee() {}

    public Employee(int maNhanVien, String hoTen, String chucVu, String email, String soDienThoai, String anhDaiDien, int maDonVi) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.anhDaiDien = anhDaiDien;
        this.maDonVi = maDonVi;
    }

}
