package com.example.user.keepsolid.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.keepsolid.App;
import com.example.user.keepsolid.R;
import com.example.user.keepsolid.entity.UserModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import top.defaults.view.TextButton;


public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {

    private List<UserModel> users;

    private Like listenerLike;
    private DisLike listenerDislike;
    private OnViewClick viewListener;


    public EmployeeListAdapter(List<UserModel> users) {
        this.users = users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public void setViewListener(OnViewClick viewListener) {
        this.viewListener = viewListener;
    }

    public void setListenerLike(Like listenerLike) {
        this.listenerLike = listenerLike;
    }

    public void setListenerDislike(DisLike listenerDislike) {
        this.listenerDislike = listenerDislike;
    }

    @Override
    public EmployeeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_employees_card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(final EmployeeListAdapter.ViewHolder holder, final int position) {
        if (!TextUtils.isEmpty(users.get(position).getPhoto_url()))
            Picasso.get().load(users.get(position).getPhoto_url())
                    .into(holder.personPhoto);
        else {
            holder.personPhoto.setImageDrawable(App.getInstance().getDrawable(R.drawable.error_picasso));
        }
        holder.name.setText(users.get(position).getName());
        holder.position.setText(users.get(position).getPosition());
        if(listenerLike !=null){
            holder.like.setText("Like("+ users.get(position).getHas_likes_count() +")");
            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerLike.setLike(users.get(position));
                }
            });
        }
        if(listenerDislike !=null){
            holder.dislike.setText("Dislike("+ users.get(position).getHas_dis_likes_count() +")");
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerDislike.setDislike(users.get(position));
                }
            });
        }
        if(viewListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewListener.onClick(users.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView personPhoto;
        TextView name;
        TextView position;
        TextButton like;
        TextButton dislike;
        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            personPhoto = view.findViewById(R.id.person_image);
            name = view.findViewById(R.id.tv_name);
            position = view.findViewById(R.id.tv_position);
            like = view.findViewById(R.id.btn_like);
            dislike = view.findViewById(R.id.btn_dislike);
        }
    }

    public interface Like{
        void setLike(UserModel model);
    }
    public interface DisLike{
        void setDislike(UserModel model);
    }
    public interface OnViewClick{
        void onClick(UserModel model);
    }
}
