package dhbkhn.kien.doan.View.DangNhap_DangKy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dhbkhn.kien.doan.Model.Object.NguoiDung;
import dhbkhn.kien.doan.Presenter.PresenterDangKy;
import dhbkhn.kien.doan.R;
import dhbkhn.kien.doan.View.TrangChu.MainActivity;

public class DangKy extends AppCompatActivity implements IViewDangKy {
    EditText edtDangKyUsername,edtEmailDangKy,edtDangNhapPassword,edtDangNhapPasswordNhapLai,edtHoTen;
    Button btnDangKy;
    PresenterDangKy presenterDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        addControls();
        addEvents();
    }

    private void addControls() {
        edtDangKyUsername = (EditText) findViewById(R.id.edtDangKyUsername);
        edtEmailDangKy = (EditText) findViewById(R.id.edtEmailDangKy);
        edtDangNhapPassword = (EditText) findViewById(R.id.edtDangNhapPassword);
        edtDangNhapPasswordNhapLai = (EditText) findViewById(R.id.edtDangNhapPasswordNhapLai);
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        presenterDangKy = new PresenterDangKy(this);
    }

    private void addEvents() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtDangNhapPassword.getText().toString().trim().equals(edtDangNhapPasswordNhapLai.getText().toString().trim())) {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setTENND(edtDangKyUsername.getText().toString().trim());
                    nguoiDung.setEMAIL(edtEmailDangKy.getText().toString().trim());
                    nguoiDung.setPASSWORD(edtDangNhapPassword.getText().toString().trim());
                    presenterDangKy.dangKyTaiKhoan(nguoiDung);
                }else {
                    Toast.makeText(DangKy.this, "Mật khẩu nhập lại cần trùng khớp!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void dangKyThanhCong() {
        Toast.makeText(DangKy.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
        Intent iTrangChu = new Intent(DangKy.this, MainActivity.class);
        iTrangChu.putExtra("hoten", edtHoTen.getText().toString());
        iTrangChu.putExtra("email", edtEmailDangKy.getText().toString());
        startActivity(iTrangChu);
    }
}
