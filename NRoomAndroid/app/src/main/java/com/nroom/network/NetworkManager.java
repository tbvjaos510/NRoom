package com.nroom.network;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
// https://code.tutsplus.com/ko/tutorials/android-from-scratch-using-rest-apis--cms-27117 네트워크 연결하는 방법 홈페이지
public class NetworkManager {

    private String result;
    private String URL;

    /*public URLConnector(String url){
        URL = url;
    }
*/
    private NetworkManager() {

    }
}

    /*public static class GetResponseTask extends AsyncTask<URL, Integer, String> {
        @Override
        protected String doInBackground(URL... uris) {
            try {
                HttpsURLConnection myConnection =
                        (HttpsURLConnection) uris[0].openConnection();
                if (myConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(myConnection.getInputStream(), "UTF-8");
                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                } else {

                }

            } catch (IOException ignore) {}
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
    public static void GetResponse(final URI uri){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection myConnection =
                        (HttpsURLConnection) uri.openConnection();
            }
        });
    }*/
