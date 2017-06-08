package dhbkhn.kien.doan.Presenter;

import dhbkhn.kien.doan.Model.Map.XuLyThemNguoiDung;
import dhbkhn.kien.doan.Model.Object.NguoiDung;
import dhbkhn.kien.doan.View.DangNhap_DangKy.IViewDangKy;

/**
 * Created by kiend on 12/31/2016.
 */
public class PresenterDangKy implements IPresenterDangKy {
    IViewDangKy iViewDangKy;
    XuLyThemNguoiDung xuLyThemNguoiDung;

    public PresenterDangKy(IViewDangKy iViewDangKy) {
        this.iViewDangKy = iViewDangKy;
        xuLyThemNguoiDung = new XuLyThemNguoiDung();
    }

    @Override
    public void dangKyTaiKhoan(NguoiDung nguoiDung) {
        boolean ketqua = xuLyThemNguoiDung.dangKyThanhVien(nguoiDung);
        if (ketqua) {
            iViewDangKy.dangKyThanhCong();
        }
    }
}
