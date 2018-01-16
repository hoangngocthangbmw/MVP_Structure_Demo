package com.thanhclub.mvp_demo_listusergithub.view.display_detail_user;

import android.graphics.Bitmap;

/**
 * Created by MyPC on 16/01/2018.
 */

public interface OnDisplayDetailUserListener {

    public void showProgress();

    public void hideProgress();

    public void showImageSuccess(Bitmap bitmap);

    public void showError();
}
