package com.thanhclub.mvp_demo_listusergithub.view.display_load_user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.thanhclub.mvp_demo_listusergithub.R;
import com.thanhclub.mvp_demo_listusergithub.adapter.UserAdapter;
import com.thanhclub.mvp_demo_listusergithub.interfaces.OnItemRecyclerClickListener;
import com.thanhclub.mvp_demo_listusergithub.model.fetch_user.User;
import com.thanhclub.mvp_demo_listusergithub.presenter.ListUserPresenter;
import com.thanhclub.mvp_demo_listusergithub.view.display_detail_user.DetailUserActivity;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements
        OnShowDataListener, View.OnClickListener, OnItemRecyclerClickListener {
    public static final String KEY_NAME_USERS = "NAME_USER";
    public static final String KEY_IMAGE_USER = "IMAGE_USER";
    private Button btnSearch;
    private EditText edtKeyword;
    private EditText edtLimit;
    private ProgressBar progressBar;
    private ListUserPresenter presenter;
    private RecyclerView rc_user;
    private RecyclerView.LayoutManager layoutManager;
    private List<User> userList;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        initActions();
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
    public void showNoData() {
        Toast.makeText(this, "Không có user!", Toast.LENGTH_SHORT).show();
        if (userList != null) userList.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showListUser(List<User> users) {
        userList = users;
        adapter = new UserAdapter(this, userList);
        rc_user.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_user:
                presenter.getData(edtKeyword.getText().toString(), edtLimit.getText().toString());
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailUserActivity.class);
        intent.putExtra(KEY_NAME_USERS,userList.get(position).getNameUser());
        intent.putExtra(KEY_IMAGE_USER,userList.get(position).getUrlImage());
        startActivity(intent);
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.pro_bar_search);
        edtKeyword = (EditText) findViewById(R.id.edt_keyword);
        edtLimit = (EditText) findViewById(R.id.edt_limit_user);
        btnSearch = (Button) findViewById(R.id.btn_search_user);
        rc_user = (RecyclerView) findViewById(R.id.rc_user);
        layoutManager = new LinearLayoutManager(this);
        rc_user.setLayoutManager(layoutManager);
    }

    private void initActions() {
        presenter = new ListUserPresenter(this);
        btnSearch.setOnClickListener(this);
        progressBar.setVisibility(View.GONE);
    }
}
