package dhbkhn.kien.doan.Presenter;

import java.util.List;

import dhbkhn.kien.doan.Model.Map.XuLyDichVu;
import dhbkhn.kien.doan.Model.Object.DichVu;
import dhbkhn.kien.doan.Model.Object.LoaiDichVu;
import dhbkhn.kien.doan.View.FragmentMap.IViewMap;


/**
 * Created by kiend on 10/9/2016.
 */
public class PresenterCapNhatViTri implements IPresenterCapNhatViTri {
    IViewMap iViewMap;
    XuLyDichVu xuLyDichVu;

    public PresenterCapNhatViTri(IViewMap iViewMap) {
        this.iViewMap = iViewMap;
        xuLyDichVu = new XuLyDichVu();
    }

    @Override
    public void layDanhSachDichVuTheoMaLoai(int maloaidv) {
        List<DichVu>dsDV = xuLyDichVu.layDanhSachDichVuTheoMa(maloaidv);
        if (dsDV.size() > 0) {
            iViewMap.hienThiDiaDiemDichVuTheoLoai(dsDV);
        }
    }

    @Override
    public void layTatCaDichVu() {
        List<DichVu>dsDV = xuLyDichVu.layTatCaDichVu();
        if (dsDV.size() > 0) {
            iViewMap.hienThiTatCaDiaDiem(dsDV);
        }
    }

    @Override
    public void layDanhSachLoaiDichVu() {
        List<LoaiDichVu>dsLDV = xuLyDichVu.layDanhSachLoaiDichVu();
        if (dsLDV.size() > 0) {
            iViewMap.hienThiDanhSachLoaiDichVu(dsLDV);
        }
    }
}
