package dhbkhn.kien.doan.View.FragmentMap;

import java.util.List;

import dhbkhn.kien.doan.Model.Object.DichVu;
import dhbkhn.kien.doan.Model.Object.LoaiDichVu;


/**
 * Created by kiend on 10/9/2016.
 */
public interface IViewMap {
    void hienThiDiaDiemDichVuTheoLoai(List<DichVu> dsDV);
    void hienThiTatCaDiaDiem(List<DichVu> dsDV);
    void hienThiDanhSachLoaiDichVu(List<LoaiDichVu> dsLDV);
}
