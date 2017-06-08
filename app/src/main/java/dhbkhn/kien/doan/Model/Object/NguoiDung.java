package dhbkhn.kien.doan.Model.Object;

/**
 * Created by kiend on 12/31/2016.
 */
public class NguoiDung {
    private int MAND, MALOAIND;
    private float LAT,LON;
    private String TENND, EMAIL,PASSWORD;

    public int getMAND() {
        return MAND;
    }

    public void setMAND(int MAND) {
        this.MAND = MAND;
    }

    public int getMALOAIND() {
        return MALOAIND;
    }

    public void setMALOAIND(int MALOAIND) {
        this.MALOAIND = MALOAIND;
    }

    public float getLAT() {
        return LAT;
    }

    public void setLAT(float LAT) {
        this.LAT = LAT;
    }

    public float getLON() {
        return LON;
    }

    public void setLON(float LON) {
        this.LON = LON;
    }

    public String getTENND() {
        return TENND;
    }

    public void setTENND(String TENND) {
        this.TENND = TENND;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public NguoiDung(int MAND, int MALOAIND, float LAT, float LON, String TENND, String EMAIL, String PASSWORD) {
        this.MAND = MAND;
        this.MALOAIND = MALOAIND;
        this.LAT = LAT;
        this.LON = LON;
        this.TENND = TENND;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
    }

    public NguoiDung() {
    }
}
