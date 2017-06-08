package dhbkhn.kien.doan.Model.Object;

import java.util.List;

/**
 * Created by kiend on 12/30/2016.
 */
public class DanhMucCha {
    private String tieude;
    private List<HienThiDichVu> dsDanhMucHienThi;

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public List<HienThiDichVu> getDsDanhMucHienThi() {
        return dsDanhMucHienThi;
    }

    public void setDsDanhMucHienThi(List<HienThiDichVu> dsDanhMucHienThi) {
        this.dsDanhMucHienThi = dsDanhMucHienThi;
    }

    public DanhMucCha() {
    }

    public DanhMucCha(String tieude, List<HienThiDichVu> dsDanhMucHienThi) {
        this.tieude = tieude;
        this.dsDanhMucHienThi = dsDanhMucHienThi;
    }
}
