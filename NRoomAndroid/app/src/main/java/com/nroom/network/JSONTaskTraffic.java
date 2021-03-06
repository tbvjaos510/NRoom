package com.nroom.network;

import android.content.Context;
import android.os.AsyncTask;

import com.nroom.data.TrafficItem;

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

public class JSONTaskTraffic extends AsyncTask<String, String, ArrayList<TrafficItem>> {
    private TaskListener taskListener;

    private WeakReference<Context> contextWeakReference;

    public JSONTaskTraffic(Context context) {
        this.contextWeakReference = new WeakReference<>(context);
    }

    @Override
    protected ArrayList<TrafficItem> doInBackground(String... urls) {

        HttpURLConnection con = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urls[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            InputStream stream = con.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String line = ""; // 나중에 클래스로 변환해야 될 듯
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            ArrayList<TrafficItem> traficList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(builder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                traficList.add(new TrafficItem(
                                object.getDouble("gpslati"),
                                object.getDouble("gpslong"),
                                object.getString("nodenm")
                        )
                );
            }
            return traficList;

        } catch (JSONException | IOException e) {
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
        return new ArrayList<>();

    }

    @Override
    protected void onPostExecute(ArrayList<TrafficItem> result) {
        if (taskListener != null)
            taskListener.onTaskFinished(result);
    }

    public JSONTaskTraffic setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
        return this;
    }

    public interface TaskListener {
        void onTaskFinished(ArrayList<TrafficItem> trafficItems);
    }
}
