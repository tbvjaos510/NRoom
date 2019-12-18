package com.nroom.network;

import android.content.Context;
import android.os.AsyncTask;

import com.nroom.R;
import com.nroom.data.HouseItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;

public class JSONTaskAll extends AsyncTask<String, String, ArrayList<HouseItem>> {
    private TaskListener taskListener;

    private WeakReference<Context> contextWeakReference;

    public JSONTaskAll(Context context) {
        this.contextWeakReference = new WeakReference<>(context);
    }

    @NonNull
    @Override
    protected ArrayList<HouseItem> doInBackground(String... urls) {
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

            ArrayList<HouseItem> houseList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(builder.toString());
            JSONArray jsonArrayJun = jsonObject.getJSONArray("jun");
            JSONArray jsonArrayReal = jsonObject.getJSONArray("real");

            for (int i = 0; i < jsonArrayJun.length(); i++) {
                JSONObject object = jsonArrayJun.getJSONObject(i);
                houseList.add(new HouseItem(
                        contextWeakReference.get().getDrawable(R.drawable.ic_house),
                        object.getString("시군구코드"),
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
                        object.getString("도로명코드"),
                        object.getInt("월세금액"),
                        object.getInt("보증금액"),
                        //FIXME SERVER inverted DATA
                        object.getDouble("lng"),
                        object.getDouble("lat"),
                        object.getInt("대피소수")));
            }

            for (int i = 0; i < jsonArrayReal.length(); i++) {
                JSONObject object = jsonArrayReal.getJSONObject(i);
                houseList.add(new HouseItem(
                        contextWeakReference.get().getDrawable(R.drawable.ic_house),
                        object.getString("시군구코드"),
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
                        object.getString("도로명코드"),
                        //FIXME SERVER inverted DATA
                        object.getDouble("lng"),
                        object.getDouble("lat"),
                        object.getInt("대피소수")));
            }
            return houseList;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(@NonNull ArrayList<HouseItem> result) {
        if (taskListener != null)
            taskListener.onTaskFinished(result);
    }

    public JSONTaskAll setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
        return this;
    }

    public interface TaskListener {
        void onTaskFinished(ArrayList<HouseItem> houseList);
    }
}
