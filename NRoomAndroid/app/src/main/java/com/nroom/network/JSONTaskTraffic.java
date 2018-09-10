package com.nroom.network;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONTaskTraffic extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... urls) {

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

            return builder.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

        } catch (MalformedURLException e) {
            e.printStackTrace();
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
        return null;
    }
}
