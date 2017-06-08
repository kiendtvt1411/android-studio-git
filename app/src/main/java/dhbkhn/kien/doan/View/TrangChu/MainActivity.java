package dhbkhn.kien.doan.View.TrangChu;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dhbkhn.kien.doan.View.TrangChu.Fragment.FragmentChinhSua;
import dhbkhn.kien.doan.View.TrangChu.Fragment.FragmentDanhSach;
import dhbkhn.kien.doan.View.TrangChu.Fragment.FragmentMap;
import dhbkhn.kien.doan.R;

public class MainActivity extends AppCompatActivity {
    FragmentTabHost tabHost;
    public static final String SERVER_NAME_URL = "http://192.168.1.13:88/chat/chat.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
    }

    private void addEvents() {
        customViewTab("Danh sách",R.drawable.home, 0);
        customViewTab("Map",R.drawable.earth, 1);
        customViewTab("Chỉnh sửa",R.drawable.ic_settings_black_24dp, 2);
    }

    private void customViewTab(String tentab, int idIcon, int soHieuTab){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View custom = inflater.inflate(R.layout.custom_item_notification, null);
        final TextView tvNotify = (TextView) custom.findViewById(R.id.tvSoLuongThongBao);
        ImageView imgIconTab = (ImageView) custom.findViewById(R.id.imgIconTab);
        imgIconTab.setImageDrawable(layAnh(idIcon));

        switch (soHieuTab) {
            case 0:
                tabHost.addTab(tabHost.newTabSpec(tentab).setIndicator(custom),FragmentDanhSach.class,null);
                break;
            case 1:
                tabHost.addTab(tabHost.newTabSpec(tentab).setIndicator(custom),FragmentMap.class,null);
                break;
            case 2:
                tabHost.addTab(tabHost.newTabSpec(tentab).setIndicator(custom),FragmentChinhSua.class,null);
                break;
        }
    }

    public Drawable layAnh(int idDw){
        Drawable drawble;
        if (Build.VERSION.SDK_INT > 21) {
            drawble = ContextCompat.getDrawable(MainActivity.this, idDw);
        }else {
            drawble = getResources().getDrawable(idDw);
        }
        return drawble;
    }
}
