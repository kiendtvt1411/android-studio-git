package dhbkhn.kien.doan.Model.Map;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dhbkhn.kien.doan.Model.ConnectInternet;
import dhbkhn.kien.doan.Model.Object.NguoiDung;
import dhbkhn.kien.doan.View.TrangChu.MainActivity;

/**
 * Created by kiend on 12/31/2016.
 */
public class XuLyThemNguoiDung {
    public Boolean dangKyThanhVien(NguoiDung nguoiDung){
        String path = MainActivity.SERVER_NAME_URL;
        Boolean ktra = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJson = "";

        HashMap<String, String> hmHam = new HashMap<>();
        hmHam.put("ham","ThemNguoiDung");

        HashMap<String, String> hmTenND = new HashMap<>();
        hmTenND.put("tennd",nguoiDung.getTENND());

        HashMap<String, String> hmEmail = new HashMap<>();
        hmEmail.put("email",nguoiDung.getEMAIL());

        HashMap<String, String> hmPass = new HashMap<>();
        hmPass.put("password",nguoiDung.getPASSWORD());

        attrs.add(hmHam);
        attrs.add(hmTenND);
        attrs.add(hmEmail);
        attrs.add(hmPass);

        ConnectInternet downloadDuLieu = new ConnectInternet(path,attrs);
        downloadDuLieu.execute();

        try {
            dataJson = downloadDuLieu.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            String data = jsonObject.getString("ketqua");

            Log.d("Json", dataJson);
            if (data.trim().equals("true")) {
                ktra = true;
            }else {
                ktra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ktra;
    }
}
