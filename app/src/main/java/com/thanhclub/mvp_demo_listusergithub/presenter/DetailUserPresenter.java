package com.thanhclub.mvp_demo_listusergithub.presenter;

import android.graphics.Bitmap;

import com.thanhclub.mvp_demo_listusergithub.model.load_image_user_detail.LoadImageDetailInteractor;
import com.thanhclub.mvp_demo_listusergithub.model.load_image_user_detail.OnLoadImageDetailListener;
import com.thanhclub.mvp_demo_listusergithub.view.display_detail_user.OnDisplayDetailUserListener;

/**
 * Created by MyPC on 16/01/2018.
 */

public class DetailUserPresenter implements OnLoadImageDetailListener{
    private OnDisplayDetailUserListener onDisplayDetailUser;
    private LoadImageDetailInteractor interactor;

    public DetailUserPresenter(OnDisplayDetailUserListener onDisplayDetailUser){
        this.onDisplayDetailUser = onDisplayDetailUser;
    }

   @Override
    public void onError() {
        onDisplayDetailUser.showError();
        onDisplayDetailUser.hideProgress();
    }

    @Override
    public void onSuccess(Bitmap bitmap) {
        onDisplayDetailUser.showImageSuccess(bitmap);
        onDisplayDetailUser.hideProgress();
    }

    public void loadImage(String url){
       interactor = new LoadImageDetailInteractor(url, this);
       onDisplayDetailUser.showProgress();
    }
}
