package dhbkhn.kien.doan.Presenter;

import android.util.Log;

import dhbkhn.kien.doan.Model.Map.XuLyDichVu;
import dhbkhn.kien.doan.Model.Object.DichVu;
import dhbkhn.kien.doan.View.ChiTietDiaDiem.IViewChiTietDiaDiem;

/**
 * Created by kiend on 12/31/2016.
 */
public class PresenterChiTietDiaDiem implements IPresenterChiTietDiaDiem {
    IViewChiTietDiaDiem iViewChiTietDiaDiem;
    XuLyDichVu xuLyDichVu;

    public PresenterChiTietDiaDiem(IViewChiTietDiaDiem iViewChiTietDiaDiem) {
        this.iViewChiTietDiaDiem = iViewChiTietDiaDiem;
        xuLyDichVu = new XuLyDichVu();
    }

    @Override
    public void layRaChiTietDiaDiem(int madv) {
        DichVu dichVu = xuLyDichVu.layDichVuTheoMaDV(madv);
        Log.d("myLogMadv", dichVu.getTENDV() + " - " + dichVu.getDIACHIDV());
        if (dichVu != null) {
            iViewChiTietDiaDiem.hienThiDiaDiemChiTiet(dichVu);
        }
    }
}
