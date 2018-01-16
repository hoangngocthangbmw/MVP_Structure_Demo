package com.thanhclub.mvp_demo_listusergithub.model.load_image_user_detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by MyPC on 16/01/2018.
 */

public class LoadImageDetailInteractor {
    private OnLoadImageDetailListener listener;

    public LoadImageDetailInteractor(String link, OnLoadImageDetailListener onLoadImageDetailListener) {
        listener = onLoadImageDetailListener;
        new LoadImage().execute(link);
    }

    class LoadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap == null) {
                listener.onError();
            } else {
                listener.onSuccess(bitmap);
            }
        }
    }
}
