package dhbkhn.kien.doan.Presenter;

import java.util.List;

import dhbkhn.kien.doan.Model.Map.XuLyDichVu;
import dhbkhn.kien.doan.Model.Object.HienThiDichVu;
import dhbkhn.kien.doan.View.DanhMucChiTiet.IViewDanhMucChiTiet;

/**
 * Created by kiend on 12/30/2016.
 */
public class PresenterDanhSachTheoMa implements IPresenterDanhSachTheoMa {
    IViewDanhMucChiTiet iViewDanhMucChiTiet;
    XuLyDichVu xuLyDichVu;

    public PresenterDanhSachTheoMa(IViewDanhMucChiTiet iViewDanhMucChiTiet) {
        this.iViewDanhMucChiTiet = iViewDanhMucChiTiet;
        xuLyDichVu = new XuLyDichVu();
    }

    @Override
    public void layDanhSachTheoMaDeHienThi(int maLoai) {
        List<HienThiDichVu> dsDv = xuLyDichVu.layDanhMucHienThi(maLoai);
        if (dsDv.size() > 0) {
            iViewDanhMucChiTiet.hienThiChiTietDanhMucTheoMa(dsDv);
        }
    }
}
