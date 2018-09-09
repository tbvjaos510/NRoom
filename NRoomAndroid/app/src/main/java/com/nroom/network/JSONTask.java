package com.nroom.network;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.widget.TextView;

import com.nroom.data.HouseItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONTask extends AsyncTask<String, String, String> {
    private List<HouseItem> lstHouse = new ArrayList<>();
    @Override
    protected String doInBackground(String... urls) {
        try {
            HttpURLConnection con = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urls[0]);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                //con.setDoInput(true);
                con.connect();

                InputStream stream = con.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String line = ""; // 나중에 클래스로 변환해야 될 듯
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                return builder.toString();


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();//버퍼를 닫아줌
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result){
        //15
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                lstHouse.add(new HouseItem(object.getString("시군구코드"),
                        object.getString("시도명"),
                        object.getString("시군구명"),
                        object.getString("id"),
                        object.getString("지역번호"),
                        object.getString("법정동"),
                        object.getString("집종류"),
                        object.getString("건물명"),
                        object.getString("전용면적"),
                        object.getString("층"),
                        object.getString("건축년도"),
                        object.getString("일"),
                        object.getString("지번"),
                        object.getString("거래금액"),
                        object.getString("도로명코드")));
              /*  object.getString("시군구코드");
                object.getString("시도명");
                object.getString("시군구명");
                object.getString("id");
                object.getString("지역번호");
                object.getString("법정동");
                object.getString("집종류");
                object.getString("건물명");
                object.getString("전용면적");
                object.getString("층");
                object.getString("건축년도");
                object.getString("일");
                object.getString("지번");
                object.getString("거래금액");
                object.getString("도로명코드");
                Log.v("12312", object.toString() + " ,");*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

      //  String subString = result.substring(0,result.indexOf('[',1));
      //  String info = result.substring(subString.length() + 1, result.indexOf(']', 1));

       // String[] splitInfo = info.substring("1");
        // 데이터 이동
    }
}

