package dhbkhn.kien.doan.View.FragmentMap;

import java.util.List;

import dhbkhn.kien.doan.Model.Object.HienThiDichVu;

/**
 * Created by kiend on 12/30/2016.
 */
public interface IViewFragmentHienThiDanhMuc {
    void hienThiDanhMucTopHot(List<HienThiDichVu> dsHienThiDVTopHot);
    void hienThiDanhMucAmThuc(List<HienThiDichVu> dsHienThiDVAmThuc);
    void hienThiDanhMucGiaiTri(List<HienThiDichVu> dsHienThiDVGiaiTri);
    void hienThiDanhMucCongCong(List<HienThiDichVu> dsHienThiDVCongCong);
}
