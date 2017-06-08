package dhbkhn.kien.doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dhbkhn.kien.doan.Model.Object.HienThiDichVu;
import dhbkhn.kien.doan.R;
import dhbkhn.kien.doan.View.ChiTietDiaDiem.ChiTietDiaDiem;

/**
 * Created by kiend on 12/30/2016.
 */
public class AdapterRVDanhSach extends RecyclerView.Adapter<AdapterRVDanhSach.ViewHolderDanhSach>{
    Context mContext;
    List<HienThiDichVu> dsHienThi;
    String loai;

    public AdapterRVDanhSach(Context mContext, List<HienThiDichVu> dsHienThi, String loai) {
        this.mContext = mContext;
        this.dsHienThi = dsHienThi;
        this.loai = loai;
    }

    @Override
    public ViewHolderDanhSach onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_item_hien_thi_danh_muc, parent, false);
        return new ViewHolderDanhSach(row);
    }

    @Override
    public void onBindViewHolder(ViewHolderDanhSach holder, final int position) {
        final HienThiDichVu htdv = dsHienThi.get(position);
        holder.tvTenDanhMucDiaDiem.setText(htdv.getTen());
        holder.tvDiaChiDanhMucDiaDiem.setText(htdv.getDiachi());
        Picasso.with(mContext).load(htdv.getLink()).resize(140,140).centerCrop().into(holder.imgHinhDanhMucDiaDiem);
        holder.cvDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTiet = new Intent(mContext, ChiTietDiaDiem.class);
                float sosao = (position%2==0)?4f:5f;
                iChiTiet.putExtra("sao", sosao);
                iChiTiet.putExtra("madv", htdv.getMa());
                mContext.startActivity(iChiTiet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsHienThi.size();
    }

    public class ViewHolderDanhSach extends RecyclerView.ViewHolder {
        TextView tvTenDanhMucDiaDiem,tvDiaChiDanhMucDiaDiem;
        ImageView imgHinhDanhMucDiaDiem;
        CardView cvDiaDiem;

        public ViewHolderDanhSach(View itemView) {
            super(itemView);
            imgHinhDanhMucDiaDiem = (ImageView) itemView.findViewById(R.id.imgHinhDanhMucDiaDiem);
            tvTenDanhMucDiaDiem = (TextView) itemView.findViewById(R.id.tvTenDanhMucDiaDiem);
            tvDiaChiDanhMucDiaDiem = (TextView) itemView.findViewById(R.id.tvDiaChiDanhMucDiaDiem);
            cvDiaDiem = (CardView) itemView.findViewById(R.id.cvDiaDiem);
        }
    }
}
