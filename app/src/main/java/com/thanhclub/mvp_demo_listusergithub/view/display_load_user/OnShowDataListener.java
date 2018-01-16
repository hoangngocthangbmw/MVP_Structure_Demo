package com.thanhclub.mvp_demo_listusergithub.view.display_load_user;

import com.thanhclub.mvp_demo_listusergithub.model.fetch_user.User;

import java.util.List;

/**
 * Created by MyPC on 16/01/2018.
 */

public interface OnShowDataListener {

    public void showProgress();

    public void hideProgress();

    public void showNoData();

    public void showListUser(List<User> users);
}
