package dhbkhn.kien.doan.View.TrangChu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dhbkhn.kien.doan.R;
import dhbkhn.kien.doan.View.DangNhap_DangKy.DangNhap;

/**
 * Created by kiend on 12/28/2016.
 */
public class FragmentChinhSua extends Fragment {
    TextView tvHoTen,tvEmail, tvDangXuat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chinh_sua, container, false);
        tvHoTen = (TextView) v.findViewById(R.id.tvHoTen);
        tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        tvDangXuat = (TextView) v.findViewById(R.id.tvDangXuat);

        tvHoTen.setText(getActivity().getIntent().getStringExtra("hoten"));
        tvEmail.setText(getActivity().getIntent().getStringExtra("email"));
        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDangXuat = new Intent(getContext(), DangNhap.class);
                startActivity(iDangXuat);
            }
        });
        return v;
    }
}
