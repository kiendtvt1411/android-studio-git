package dhbkhn.kien.doan.Presenter;

import java.util.List;

import dhbkhn.kien.doan.Model.Map.XuLyDichVu;
import dhbkhn.kien.doan.Model.Object.HienThiDichVu;
import dhbkhn.kien.doan.View.FragmentMap.IViewFragmentHienThiDanhMuc;

/**
 * Created by kiend on 12/30/2016.
 */
public class PresenterHienThiDanhMuc implements IPresenterHienThiDanhMuc {
    IViewFragmentHienThiDanhMuc iViewFragmentHienThiDanhMuc;
    XuLyDichVu xuLyDichVu;

    public PresenterHienThiDanhMuc(IViewFragmentHienThiDanhMuc iViewFragmentHienThiDanhMuc) {
        this.iViewFragmentHienThiDanhMuc = iViewFragmentHienThiDanhMuc;
        xuLyDichVu = new XuLyDichVu();
    }


    @Override
    public void layDanhMucHienThiTheoMaLoaiTopHot(int maLoai) {
        List<HienThiDichVu> dsHienThi =  xuLyDichVu.layDanhMucHienThi(maLoai);
        if (dsHienThi.size()>0) {
            iViewFragmentHienThiDanhMuc.hienThiDanhMucTopHot(dsHienThi);
        }
    }

    @Override
    public void layDanhMucHienThiTheoMaLoaiAmThuc(int maLoai) {
        List<HienThiDichVu> dsHienThi =  xuLyDichVu.layDanhMucHienThi(maLoai);
        if (dsHienThi.size()>0) {
            iViewFragmentHienThiDanhMuc.hienThiDanhMucAmThuc(dsHienThi);
        }
    }

    @Override
    public void layDanhMucHienThiTheoMaLoaiGiaiTri(int maLoai) {
        List<HienThiDichVu> dsHienThi =  xuLyDichVu.layDanhMucHienThi(maLoai);
        if (dsHienThi.size()>0) {
            iViewFragmentHienThiDanhMuc.hienThiDanhMucGiaiTri(dsHienThi);
        }
    }

    @Override
    public void layDanhMucHienThiTheoMaLoaiCongCong(int maLoai) {
        List<HienThiDichVu> dsHienThi =  xuLyDichVu.layDanhMucHienThi(maLoai);
        if (dsHienThi.size()>0) {
            iViewFragmentHienThiDanhMuc.hienThiDanhMucCongCong(dsHienThi);
        }
    }
}
