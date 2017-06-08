package dhbkhn.kien.doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dhbkhn.kien.doan.Model.Object.HienThiDichVu;
import dhbkhn.kien.doan.R;
import dhbkhn.kien.doan.View.ChiTietDiaDiem.ChiTietDiaDiem;

/**
 * Created by kiend on 12/30/2016.
 */
public class AdapterXemDanhSachTheoMa extends RecyclerView.Adapter<AdapterXemDanhSachTheoMa.ViewHolderXem> {
    Context mContext;
    List<HienThiDichVu> dsDv;

    public AdapterXemDanhSachTheoMa(Context mContext, List<HienThiDichVu> dsDv) {
        this.mContext = mContext;
        this.dsDv = dsDv;
    }

    @Override
    public ViewHolderXem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_item_chi_tiet_danh_muc, parent, false);
        return new ViewHolderXem(row);
    }

    @Override
    public void onBindViewHolder(ViewHolderXem holder, int position) {
        final HienThiDichVu dv = dsDv.get(position);
        final float sosao = (position%2==0)?4f:5f;
        holder.ratingDiaDiem.setRating(sosao);
        Picasso.with(mContext).load(dv.getLink()).into(holder.imgHinhDiaDiaDanhMuc);
        holder.tvTenDiaDiemDanhMuc.setText(dv.getTen());
        holder.tvDiaChiDiaDiemDanhMuc.setText(dv.getDiachi());
        holder.tvXemThemDiaDiemDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTiet = new Intent(mContext, ChiTietDiaDiem.class);
                iChiTiet.putExtra("sao", sosao);
                iChiTiet.putExtra("madv", dv.getMa());
                mContext.startActivity(iChiTiet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsDv.size();
    }

    public class ViewHolderXem extends RecyclerView.ViewHolder {
        TextView tvTenDiaDiemDanhMuc, tvDiaChiDiaDiemDanhMuc, tvXemThemDiaDiemDanhMuc;
        RatingBar ratingDiaDiem;
        ImageView imgHinhDiaDiaDanhMuc;

        public ViewHolderXem(View itemView) {
            super(itemView);
            tvTenDiaDiemDanhMuc = (TextView) itemView.findViewById(R.id.tvTenDiaDiemDanhMuc);
            tvDiaChiDiaDiemDanhMuc = (TextView) itemView.findViewById(R.id.tvDiaChiDiaDiemDanhMuc);
            tvXemThemDiaDiemDanhMuc = (TextView) itemView.findViewById(R.id.tvXemThemDiaDiemDanhMuc);
            ratingDiaDiem = (RatingBar) itemView.findViewById(R.id.ratingDiaDiem);
            imgHinhDiaDiaDanhMuc = (ImageView) itemView.findViewById(R.id.imgHinhDiaDiaDanhMuc);
        }
    }
}
