package com.thanhclub.mvp_demo_listusergithub.model.fetch_user;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 16/01/2018.
 */

public class GetDataInteractor {
    public static final String NAME_USER = "login";
    public static final String ID_USER = "id";
    public static final String AVATAR_USER = "avatar_url";
    private OnResultGetDataListener listener;

    public GetDataInteractor(String urls, OnResultGetDataListener listener) {
        this.listener = listener;
        new FetchDataFromUrl().execute(urls);
    }

    class FetchDataFromUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setDoOutput(true);
                connection.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                String result = sb.toString();
                connection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                if (s.equals("") || s == null){
                    listener.onError();
                    return;
                }
                JSONObject jsonRoot = new JSONObject(s);
                JSONArray arrayItem = jsonRoot.optJSONArray("items");
                if (arrayItem.length() == 0){
                    listener.onNoData();
                }else {
                    List<User> users = new ArrayList<>();
                    String name, id, avatarUrl;
                    for (int i = 0; i < arrayItem.length(); i++) {
                        JSONObject items = arrayItem.optJSONObject(i);
                        id = items.optString(ID_USER);
                        name = items.optString(NAME_USER);
                        avatarUrl = items.optString(AVATAR_USER);
                        users.add(new User(avatarUrl,id,name));
                    }
                    listener.onSuccessData(users);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
