package dhbkhn.kien.doan.Model.Object;

import java.io.Serializable;

/**
 * Created by kiend on 12/30/2016.
 */
public class HienThiDichVu implements Serializable{
    private String ten,diachi,link;
    private int ma;

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public HienThiDichVu(String ten, String diachi, String link, int ma) {
        this.ten = ten;
        this.diachi = diachi;
        this.link = link;
        this.ma = ma;
    }

    public HienThiDichVu() {
    }
}
