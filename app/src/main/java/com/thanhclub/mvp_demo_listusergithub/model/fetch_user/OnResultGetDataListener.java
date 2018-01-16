package com.thanhclub.mvp_demo_listusergithub.model.fetch_user;

import java.util.List;

/**
 * Created by MyPC on 16/01/2018.
 */

public interface OnResultGetDataListener {

    public void onNoData();

    public void onError();

    public void onSuccessData(List<User> users);
}
