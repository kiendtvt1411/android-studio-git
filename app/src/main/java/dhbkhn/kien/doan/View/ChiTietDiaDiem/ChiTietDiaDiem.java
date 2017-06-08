package dhbkhn.kien.doan.View.ChiTietDiaDiem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dhbkhn.kien.doan.Model.Object.DichVu;
import dhbkhn.kien.doan.Presenter.PresenterChiTietDiaDiem;
import dhbkhn.kien.doan.R;

public class ChiTietDiaDiem extends AppCompatActivity implements IViewChiTietDiaDiem{
    ImageView imgChiTietDiaDiem;
    TextView tvTenChiTietDiaDiem,tvMoTaChiTietDiaDiem,tvDiaChiChiTietDiaDiem;
    RatingBar ratingChiTietDiaDiem;
    LinearLayout llDiaChi,llChiDuong;
    PresenterChiTietDiaDiem presenterChiTietDiaDiem;
    DichVu dichvu = null;
    int madv = 0;
    String[] randomMota = {"có mặt tại thị trường Việt Nam từ năm 1998. Hiện nay, mang tầm vóc của doanh nghiệp quốc tế, Lotteria đang dẫn đầu ngành công nghiệp ăn uống quốc nội với hơn 210 nhà hàng tại hơn 30 tỉnh/thành trên cả nước",
    "Thương hiệu bún chả đã qua ba thế hệ gia đình, nơi Obama từng dừng chân\nThương hiệu bún chả đã qua ba thế hệ gia đình, nơi Obama từng dừng chân\n",
    "Không gian yên tĩnh, mang đậm chất cổ kính xưa cũ\n"
            ,"Được đánh giá là một trong những món ăn vặt không thể thiếu cho du khách tới Hà Nội\n"
            ,"Nơi nghỉ chân của các cặp đôi không nơi lương tựa mỗi đêm\n"
            ,"Món ăn vặt ưa thích của học sinh, sinh viên\n"
            ,"Món ăn nổi tiếng nam bộ có mặt tại thủ đô Hà Nội\n"
            ,"Hải san tươi ngon, chế biển bởi đầu bếp hàng đầu Việt Nam\n"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dia_diem);

        addControls();
        addEvents();
    }

    private void addControls() {
        imgChiTietDiaDiem = (ImageView) findViewById(R.id.imgChiTietDiaDiem);
        tvTenChiTietDiaDiem = (TextView) findViewById(R.id.tvTenChiTietDiaDiem);
        tvMoTaChiTietDiaDiem = (TextView) findViewById(R.id.tvMoTaChiTietDiaDiem);
        tvDiaChiChiTietDiaDiem = (TextView) findViewById(R.id.tvDiaChiChiTietDiaDiem);
        ratingChiTietDiaDiem = (RatingBar) findViewById(R.id.ratingChiTietDiaDiem);
        llDiaChi = (LinearLayout) findViewById(R.id.llDiaChi);
        llChiDuong = (LinearLayout) findViewById(R.id.llChiDuong);

        madv = getIntent().getIntExtra("madv", 1);

        presenterChiTietDiaDiem = new PresenterChiTietDiaDiem(this);
    }

    private View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.llDiaChi:
                    break;
                case R.id.llChiDuong:
                    if (dichvu != null) {
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?"
                                + "saddr=21.007992, 105.845285&"
                                + "daddr="+ dichvu.getLAT() + ","+dichvu.getLON()));
                        startActivity(intent);
                    }

                    break;
            }
        }
    };

    private void addEvents() {
        llChiDuong.setOnClickListener(myClick);
        llDiaChi.setOnClickListener(myClick);
        presenterChiTietDiaDiem.layRaChiTietDiaDiem(madv);
    }

    @Override
    public void hienThiDiaDiemChiTiet(DichVu dichVu) {
        dichvu = dichVu;
        Log.d("myLogMadv", madv + " - " + dichVu.getDIACHIDV());
        Picasso.with(ChiTietDiaDiem.this).load(dichVu.getHINHLONDV()).into(imgChiTietDiaDiem);
        tvTenChiTietDiaDiem.setText(dichVu.getTENDV());
        tvDiaChiChiTietDiaDiem.setText(dichVu.getDIACHIDV());
        float rating = getIntent().getFloatExtra("sao", 4f);
        ratingChiTietDiaDiem.setRating(rating);
        switch (dichVu.getMALOAIDV()) {
            case 1:
                tvMoTaChiTietDiaDiem.setText(randomMota[2]);
                break;
            case 3:
                tvMoTaChiTietDiaDiem.setText(randomMota[4]);
                break;
            case 5:
                tvMoTaChiTietDiaDiem.setText(randomMota[7]);
                break;
            case 4:
                tvMoTaChiTietDiaDiem.setText(randomMota[5]);
                break;
        }
    }
}
