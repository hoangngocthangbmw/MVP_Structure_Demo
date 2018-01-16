package com.thanhclub.mvp_demo_listusergithub.presenter;


import com.thanhclub.mvp_demo_listusergithub.model.fetch_user.GetDataInteractor;
import com.thanhclub.mvp_demo_listusergithub.model.fetch_user.OnResultGetDataListener;
import com.thanhclub.mvp_demo_listusergithub.model.fetch_user.User;
import com.thanhclub.mvp_demo_listusergithub.view.display_load_user.OnShowDataListener;

import java.util.List;

public class ListUserPresenter implements OnResultGetDataListener {
    public static final String LINK_API = "https://api.github.com/search/users?per_page=";
    private OnShowDataListener showDataListener;
    private GetDataInteractor getDataInteractor;

    public ListUserPresenter(OnShowDataListener showDataListener) {
        this.showDataListener = showDataListener;
    }

    @Override
    public void onNoData() {
        showDataListener.hideProgress();
        showDataListener.showNoData();
    }

    @Override
    public void onError() {
        showDataListener.hideProgress();
    }

    @Override
    public void onSuccessData(List<User> users) {
        showDataListener.showListUser(users);
        showDataListener.hideProgress();
    }

    public void getData(String keyword, String limit) {
        getDataInteractor = new GetDataInteractor(LINK_API + limit + "&q=" + keyword, this);
        showDataListener.showProgress();
    }
}
