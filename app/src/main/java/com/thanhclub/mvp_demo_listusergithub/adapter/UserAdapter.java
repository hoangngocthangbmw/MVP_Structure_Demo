package com.thanhclub.mvp_demo_listusergithub.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thanhclub.mvp_demo_listusergithub.R;
import com.thanhclub.mvp_demo_listusergithub.interfaces.OnItemRecyclerClickListener;
import com.thanhclub.mvp_demo_listusergithub.model.fetch_user.User;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> users;
    private Context context;
    private OnItemRecyclerClickListener listener;

    public UserAdapter(Context context, List<User> users) {
        this.users = users;
        this.context = context;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.initDataView(users.get(position));
    }

    @Override
    public int getItemCount() {
        if (users.size() == 0 || users == null) {
            return 0;
        }
        return users.size();
    }

    public void setListener(OnItemRecyclerClickListener listener) {
        this.listener = listener;
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtId;

        public UserHolder(View itemView) {
            super(itemView);
            initViews();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }

        private void initViews() {
            txtName = (TextView) itemView.findViewById(R.id.txt_name_user);
            txtId = (TextView) itemView.findViewById(R.id.txt_id_user);
        }

        public void initDataView(User user) {
            txtId.setText(user.getIdUser().toString());
            txtName.setText(user.getNameUser().toString());
        }
    }
}
