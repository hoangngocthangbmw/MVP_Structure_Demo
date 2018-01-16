package com.thanhclub.mvp_demo_listusergithub.view.display_detail_user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thanhclub.mvp_demo_listusergithub.R;
import com.thanhclub.mvp_demo_listusergithub.presenter.DetailUserPresenter;
import com.thanhclub.mvp_demo_listusergithub.view.display_load_user.SearchActivity;

/**
 * Created by MyPC on 16/01/2018.
 */

public class DetailUserActivity extends Activity implements OnDisplayDetailUserListener {
    private String user;
    private String urlImage;
    private DetailUserPresenter presenter;

    private TextView txtUser;
    private ImageView imgAvatar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        initView();
        receivIntent();
        presenter = new DetailUserPresenter(this);
        presenter.loadImage(urlImage);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showImageSuccess(Bitmap bitmap) {
        imgAvatar.setImageBitmap(bitmap);
        txtUser.setText(user.toString());
    }

    @Override
    public void showError() {
        imgAvatar.setImageResource(R.drawable.error);
    }

    private void receivIntent() {
        Intent intent = getIntent();
        urlImage = intent.getStringExtra(SearchActivity.KEY_IMAGE_USER);
        user = intent.getStringExtra(SearchActivity.KEY_NAME_USERS);
    }

    private void initView() {
        txtUser = (TextView) findViewById(R.id.name_user_detail);
        imgAvatar = (ImageView) findViewById(R.id.img_user_detail);
        progressBar = (ProgressBar) findViewById(R.id.pro_bar_detail);
    }
}

