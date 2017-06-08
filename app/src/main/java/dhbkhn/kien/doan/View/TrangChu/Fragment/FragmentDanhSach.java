package dhbkhn.kien.doan.View.TrangChu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dhbkhn.kien.doan.Adapter.AdapterRVDanhSach;
import dhbkhn.kien.doan.Model.Object.HienThiDichVu;
import dhbkhn.kien.doan.Presenter.PresenterHienThiDanhMuc;
import dhbkhn.kien.doan.R;
import dhbkhn.kien.doan.View.DanhMucChiTiet.DanhMucChiTiet;
import dhbkhn.kien.doan.View.FragmentMap.IViewFragmentHienThiDanhMuc;

/**
 * Created by kiend on 12/28/2016.
 */
public class FragmentDanhSach extends Fragment implements IViewFragmentHienThiDanhMuc{
    RecyclerView rvTopHot,rvAmThuc,rvGiaiTri,rvCongCong;
    AdapterRVDanhSach adapterTopHot;
    AdapterRVDanhSach adapterAmThuc;
    AdapterRVDanhSach adapterGiaiTri;
    AdapterRVDanhSach adapterCongCong;
    PresenterHienThiDanhMuc presenterHienThiDanhMuc;
    TextView tvXemThemCongCong,tvXemThemGiaiTri,tvXemThemAmThuc,tvXemThemTopHot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_danh_sach, container, false);

        rvTopHot = (RecyclerView) v.findViewById(R.id.rvTopHot);
        rvAmThuc = (RecyclerView) v.findViewById(R.id.rvAmThuc);
        rvGiaiTri = (RecyclerView) v.findViewById(R.id.rvGiaiTri);
        rvCongCong = (RecyclerView) v.findViewById(R.id.rvCongCong);

        tvXemThemTopHot = (TextView) v.findViewById(R.id.tvXemThemTopHot);
        tvXemThemAmThuc = (TextView) v.findViewById(R.id.tvXemThemAmThuc);
        tvXemThemGiaiTri = (TextView) v.findViewById(R.id.tvXemThemGiaiTri);
        tvXemThemCongCong = (TextView) v.findViewById(R.id.tvXemThemCongCong);

        rvTopHot.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvAmThuc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvGiaiTri.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvCongCong.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        presenterHienThiDanhMuc = new PresenterHienThiDanhMuc(this);

        addEvents();
        return v;
    }

    private void addEvents() {
        tvXemThemTopHot.setOnClickListener(myClick);
        tvXemThemAmThuc.setOnClickListener(myClick);
        tvXemThemGiaiTri.setOnClickListener(myClick);
        tvXemThemCongCong.setOnClickListener(myClick);

        presenterHienThiDanhMuc.layDanhMucHienThiTheoMaLoaiTopHot(1);
        presenterHienThiDanhMuc.layDanhMucHienThiTheoMaLoaiAmThuc(5);
        presenterHienThiDanhMuc.layDanhMucHienThiTheoMaLoaiGiaiTri(3);
        presenterHienThiDanhMuc.layDanhMucHienThiTheoMaLoaiCongCong(4);
    }

    private View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tvXemThemTopHot:
                    Intent iTopHot = new Intent(getContext(), DanhMucChiTiet.class);
                    iTopHot.putExtra("soloai", 1);
                    iTopHot.putExtra("chude", "TOP HOT");
                    startActivity(iTopHot);
                    break;
                case R.id.tvXemThemAmThuc:
                    Intent iAmThuc = new Intent(getContext(), DanhMucChiTiet.class);
                    iAmThuc.putExtra("soloai", 5);
                    iAmThuc.putExtra("chude", "ẨM THỰC");
                    startActivity(iAmThuc);
                    break;
                case R.id.tvXemThemGiaiTri:
                    Intent iGiaiTri = new Intent(getContext(), DanhMucChiTiet.class);
                    iGiaiTri.putExtra("soloai", 3);
                    iGiaiTri.putExtra("chude", "GIẢI TRÍ");
                    startActivity(iGiaiTri);
                    break;
                case R.id.tvXemThemCongCong:
                    Intent iCongCong = new Intent(getContext(), DanhMucChiTiet.class);
                    iCongCong.putExtra("soloai", 4);
                    iCongCong.putExtra("chude", "CÔNG CỘNG");
                    startActivity(iCongCong);
                    break;
            }
        }
    };


    @Override
    public void hienThiDanhMucTopHot(List<HienThiDichVu> dsHienThiDVTopHot) {
        if (dsHienThiDVTopHot.size() > 0) {
            adapterTopHot = new AdapterRVDanhSach(getContext(), dsHienThiDVTopHot, "TOPHOT");
            rvTopHot.setAdapter(adapterTopHot);
            adapterTopHot.notifyDataSetChanged();
        }
    }

    @Override
    public void hienThiDanhMucAmThuc(List<HienThiDichVu> dsHienThiDVAmThuc) {
        if (dsHienThiDVAmThuc.size() > 0) {
            adapterAmThuc = new AdapterRVDanhSach(getContext(), dsHienThiDVAmThuc, "AMTHUC");
            rvAmThuc.setAdapter(adapterAmThuc);
            adapterAmThuc.notifyDataSetChanged();
        }
    }

    @Override
    public void hienThiDanhMucGiaiTri(List<HienThiDichVu> dsHienThiDVGiaiTri) {
        if (dsHienThiDVGiaiTri.size() > 0) {
            adapterGiaiTri = new AdapterRVDanhSach(getContext(), dsHienThiDVGiaiTri, "GIAITRI");
            rvGiaiTri.setAdapter(adapterGiaiTri);
            adapterGiaiTri.notifyDataSetChanged();
        }
    }

    @Override
    public void hienThiDanhMucCongCong(List<HienThiDichVu> dsHienThiDVCongCong) {
        if (dsHienThiDVCongCong.size() > 0) {
            adapterCongCong = new AdapterRVDanhSach(getContext(), dsHienThiDVCongCong, "CONGCONG");
            rvCongCong.setAdapter(adapterCongCong);
            adapterCongCong.notifyDataSetChanged();
        }
    }
}
