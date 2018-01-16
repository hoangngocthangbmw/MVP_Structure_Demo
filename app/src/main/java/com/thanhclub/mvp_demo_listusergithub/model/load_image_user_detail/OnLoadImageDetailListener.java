package com.thanhclub.mvp_demo_listusergithub.model.load_image_user_detail;

import android.graphics.Bitmap;

/**
 * Created by MyPC on 16/01/2018.
 */

public interface OnLoadImageDetailListener {

    public void onError();

    public void onSuccess(Bitmap bitmap);
}
