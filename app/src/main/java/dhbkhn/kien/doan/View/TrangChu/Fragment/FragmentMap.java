package dhbkhn.kien.doan.View.TrangChu.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dhbkhn.kien.doan.Adapter.AdapterListViewLoaiDichVu;
import dhbkhn.kien.doan.Model.Map.PicassoTarget;
import dhbkhn.kien.doan.Model.Map.XuLyDichVu;
import dhbkhn.kien.doan.Model.Object.DichVu;
import dhbkhn.kien.doan.Model.Object.LoaiDichVu;
import dhbkhn.kien.doan.Presenter.PresenterCapNhatViTri;
import dhbkhn.kien.doan.R;
import dhbkhn.kien.doan.View.FragmentMap.IViewMap;

/**
 * Created by kiend on 12/28/2016.
 */
public class FragmentMap extends Fragment implements OnMapReadyCallback, IViewMap {
    private GoogleMap mMap;
    TextView tvBanKinh;
    DrawerLayout drawerLayoutMap;
    ActionBarDrawerToggle toggle;
    AppBarLayout appBarMap;
    Toolbar toolBarMap;
    ListView lvDrawerMap;
    AppCompatSeekBar seekBanKinhMap;
    PresenterCapNhatViTri presenterCapNhatViTri;
    AdapterListViewLoaiDichVu adapter;
    List<LoaiDichVu> dsLoaiDichVu = new ArrayList<>();
    List<DichVu> dsDichVu = new ArrayList<>();
    List<Marker> dsMarker = new ArrayList<>();
    List<Marker> dsMarkerXungQuanh = new ArrayList<>();
    Button btnTuDongCapNhat;
    boolean isAuto = false;
    int soHieuDiaDiem = 0;
    boolean isPause = false;
    Thread thread;
    float[][] danhsachtoado = {{21.007992f, 105.845285f}, {21.008112f, 105.847130f}, {21.008593f, 105.849190f}
            , {21.010596f, 105.849062f}, {21.013040f, 105.849147f}, {21.015483f, 105.849147f}, {21.016982f, 105.849212f}
            , {21.016863f, 105.850284f}, {21.016816f, 105.851432f}, {21.017956f, 105.854132f}, {21.018316f, 105.855119f}
            , {21.018667f, 105.856632f}, {21.017665f, 105.857061f}, {21.016553f, 105.857522f}, {21.016844f, 105.858145f}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        drawerLayoutMap = (DrawerLayout) v.findViewById(R.id.drawerLayoutMap);
        appBarMap = (AppBarLayout) v.findViewById(R.id.appBarMap);
        toolBarMap = (Toolbar) v.findViewById(R.id.toolbarMap);
        tvBanKinh = (TextView) v.findViewById(R.id.tvBanKinh);
        lvDrawerMap = (ListView) v.findViewById(R.id.lvDrawerMap);
        seekBanKinhMap = (AppCompatSeekBar) v.findViewById(R.id.seekBanKinhMap);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolBarMap);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((AppCompatActivity) getActivity()).setTitle("");

        btnTuDongCapNhat = (Button) v.findViewById(R.id.btnTuDongCapNhat);

        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayoutMap, R.string.open, R.string.close);
        toggle.syncState();

        thread = new Thread(longTask);

        if (isAuto) {
            btnTuDongCapNhat.setText("CANCEL");
        } else {
            btnTuDongCapNhat.setText("AUTO");
        }

        presenterCapNhatViTri = new PresenterCapNhatViTri(this);
        presenterCapNhatViTri.layDanhSachLoaiDichVu();
        dsDichVu = new XuLyDichVu().layTatCaDichVu();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnTuDongCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAuto = !isAuto;
                isPause = false;
                if (isAuto && !isPause) {
                    btnTuDongCapNhat.setText("CANCEL");
                    thread.start();
                } else {
                    btnTuDongCapNhat.setText("AUTO");
                    isPause = true;
                }
            }
        });
        addEvents();
        return v;
    }

    private Runnable longTask = new Runnable() {
        @Override
        public void run() {
            while (!isPause) {
                if (soHieuDiaDiem >= 0 && soHieuDiaDiem < danhsachtoado.length) {
                    ++soHieuDiaDiem;
                    if (soHieuDiaDiem == danhsachtoado.length - 1) {
                        isPause = false;
                        soHieuDiaDiem=0;
                    }
                    if (soHieuDiaDiem < danhsachtoado.length) {
                        SystemClock.sleep(3000);
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);
                    }
                }
            }
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            float[] toadohientai = danhsachtoado[soHieuDiaDiem];

            if (dsMarkerXungQuanh.size() > 0) {
                dsMarkerXungQuanh.remove(0);
            }

            LatLng currentLatLng = new LatLng(toadohientai[0], toadohientai[1]);
            Marker marker = mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Đường đi tới trường " + soHieuDiaDiem + " km!"));
            dsMarkerXungQuanh.add(0,marker);
            Log.d("myLogAfter2", dsMarkerXungQuanh.get(0).getTitle());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14.0f));
        }
    };

    private void addEvents() {
        lvDrawerMap.setOnItemClickListener(myListViewClick);
        seekBanKinhMap.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final int bankinh = progress + 1000;
                tvBanKinh.setText(String.valueOf(bankinh));
                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        LatLng doican = new LatLng(location.getLatitude(), location.getLongitude());
                        if (location != null) {
                            for (Marker marker : dsMarker) {
                                float[] distance = new float[2];
                                Location.distanceBetween(doican.latitude, doican.longitude,
                                        marker.getPosition().latitude, marker.getPosition().longitude, distance);
                                if (distance[0] > bankinh) {
                                    marker.setVisible(false);
                                } else {
                                    marker.setVisible(true);
                                }
                            }
                        }
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_drawer_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemDrawerFragment:
                if (drawerLayoutMap.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayoutMap.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayoutMap.openDrawer(Gravity.RIGHT);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        LatLng dhbk = new LatLng(21.007386, 105.842839);
        mMap.addMarker(new MarkerOptions().position(dhbk).snippet("Xuất phát").title("Chỗ này"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(dhbk.latitude, dhbk.longitude))      // Sets the center of the map to location user
                .zoom(15)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void hienThiDiaDiemDichVuTheoLoai(List<DichVu> dsDV) {
        for (Marker marker : dsMarker) {
            marker.remove();
        }

        if (dsMarkerXungQuanh.size() >= 2) {
            for(int i = 1;i<dsMarkerXungQuanh.size();i++) {
                dsMarkerXungQuanh.get(i).remove();
            }
        }

        for (DichVu dv : dsDV) {
            MarkerOptions option = new MarkerOptions().title(dv.getTENDV()).snippet(dv.getDIACHIDV())
                    .position(new LatLng(dv.getLAT(),dv.getLON()));
            Marker marker = mMap.addMarker(option);
            Picasso.with(getContext()).load(dv.getICONDV()).resize(50,50).centerCrop().into(new PicassoTarget(marker));
            dsMarker.add(marker);
            dsMarkerXungQuanh.add(marker);
        }
    }

    @Override
    public void hienThiTatCaDiaDiem(List<DichVu> dsDV) {
        for (Marker marker : dsMarker) {
            marker.remove();
        }
        if (dsMarkerXungQuanh.size() >= 2) {
            for(int i = 1;i<dsMarkerXungQuanh.size();i++) {
                dsMarkerXungQuanh.get(i).remove();
            }
        }

        for (DichVu dv : dsDV) {
            MarkerOptions option = new MarkerOptions().title(dv.getTENDV()).snippet(dv.getDIACHIDV())
                    .position(new LatLng(dv.getLAT(),dv.getLON()));
            Marker marker = mMap.addMarker(option);
            Picasso.with(getContext()).load(dv.getICONDV()).resize(50,50).centerCrop().into(new PicassoTarget(marker));
            dsMarker.add(marker);
            dsMarkerXungQuanh.add(marker);
        }
    }

    @Override
    public void hienThiDanhSachLoaiDichVu(List<LoaiDichVu> dsLDV) {
        LoaiDichVu ldv = new LoaiDichVu();
        ldv.setMALOAIDV(100);
        ldv.setTENLOAIDV("Tất cả các địa điểm");
        dsLoaiDichVu.add(ldv);
        dsLoaiDichVu.addAll(dsLDV);
        adapter = new AdapterListViewLoaiDichVu(getContext(),R.layout.custom_drawer_layout_loai_dich_vu,dsLoaiDichVu);
        lvDrawerMap.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private AdapterView.OnItemClickListener myListViewClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                presenterCapNhatViTri.layTatCaDichVu();
            }else {
                LoaiDichVu loaiDichVu = dsLoaiDichVu.get(position);
                int maloaidv = loaiDichVu.getMALOAIDV();
                presenterCapNhatViTri.layDanhSachDichVuTheoMaLoai(maloaidv);
            }
        }
    };
}
