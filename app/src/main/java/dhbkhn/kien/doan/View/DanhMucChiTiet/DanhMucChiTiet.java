package dhbkhn.kien.doan.View.DanhMucChiTiet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import dhbkhn.kien.doan.Adapter.AdapterXemDanhSachTheoMa;
import dhbkhn.kien.doan.Model.Object.HienThiDichVu;
import dhbkhn.kien.doan.Presenter.PresenterDanhSachTheoMa;
import dhbkhn.kien.doan.R;

public class DanhMucChiTiet extends AppCompatActivity implements IViewDanhMucChiTiet{
    Toolbar toolbarDanhMucChiTiet;
    RecyclerView rvDanhMucChiTiet;
    AdapterXemDanhSachTheoMa adapterXemDanhSachTheoMa;
    PresenterDanhSachTheoMa presenterDanhSachTheoMa;
    int soLoai = 0;
    String chude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_chi_tiet);
        toolbarDanhMucChiTiet = (Toolbar) findViewById(R.id.toolbarDanhMucChiTiet);
        setSupportActionBar(toolbarDanhMucChiTiet);

        rvDanhMucChiTiet = (RecyclerView) findViewById(R.id.rvDanhMucChiTiet);
        rvDanhMucChiTiet.setLayoutManager(new LinearLayoutManager(DanhMucChiTiet.this));
        presenterDanhSachTheoMa = new PresenterDanhSachTheoMa(this);

        soLoai = getIntent().getIntExtra("soloai", 0);
        chude = getIntent().getStringExtra("chude");
        getSupportActionBar().setTitle(chude);
        presenterDanhSachTheoMa.layDanhSachTheoMaDeHienThi(soLoai);
    }

    @Override
    public void hienThiChiTietDanhMucTheoMa(List<HienThiDichVu> dsDv) {
        adapterXemDanhSachTheoMa = new AdapterXemDanhSachTheoMa(DanhMucChiTiet.this, dsDv);
        rvDanhMucChiTiet.setAdapter(adapterXemDanhSachTheoMa);
        adapterXemDanhSachTheoMa.notifyDataSetChanged();
    }
}
