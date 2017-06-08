package dhbkhn.kien.doan.View.DangNhap_DangKy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dhbkhn.kien.doan.R;
import dhbkhn.kien.doan.View.TrangChu.MainActivity;

public class DangNhap extends AppCompatActivity {
    EditText edTenDangNhap,edMatKhau;
    Button btnDangNhap, btnDangKi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControls();
        addEvents();
    }

    private void addControls() {
        edTenDangNhap = (EditText) findViewById(R.id.edTenDangNhap);
        edMatKhau = (EditText) findViewById(R.id.edMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKi = (Button) findViewById(R.id.btnDangKi);
    }


    private void addEvents() {
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDangKi = new Intent(DangNhap.this, DangKy.class);
                startActivity(iDangKi);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DangNhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent iTrangChu = new Intent(DangNhap.this, MainActivity.class);
                iTrangChu.putExtra("hoten", "Đinh Tuấn Anh");
                iTrangChu.putExtra("email", "changhamchoi@gmail.com");
                startActivity(iTrangChu);
            }
        });
    }
}
