package com.example.user.keepsolid.ui.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.keepsolid.R;
import com.example.user.keepsolid.databinding.FragmentEmployeeRaitingBinding;
import com.example.user.keepsolid.entity.UserModel;
import com.example.user.keepsolid.manager.UserManager;
import com.example.user.keepsolid.presentation.RaitingEmployeePresenter;
import com.example.user.keepsolid.presentation.view.RaitingView;
import com.example.user.keepsolid.ui.adapters.EmployeeListAdapter;
import com.example.user.keepsolid.ui.dialog.CommentLikeDialog;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaitingEmployeeFragment extends Fragment implements RaitingView {
    FragmentEmployeeRaitingBinding binding;
    RaitingEmployeePresenter presenter;
    EmployeeListAdapter adapter;

    public RaitingEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_raiting, container, false);
        presenter = new RaitingEmployeePresenter();
        presenter.attachView(this);
        binding.rvEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<UserModel> arrayList = new ArrayList<>();
        adapter = new EmployeeListAdapter(arrayList);
        adapter.setListenerLike(new EmployeeListAdapter.Like() {
            @Override
            public void setLike(UserModel model) {
                CommentLikeDialog commentLikeDialog = new CommentLikeDialog();
                commentLikeDialog.show(getActivity().getSupportFragmentManager().beginTransaction(), null);
                UserManager.newInstance().setLike(model.getId(), model.getHas_likes_count() + 1);
            }
        });
        adapter.setListenerDislike(new EmployeeListAdapter.DisLike() {
            @Override
            public void setDislike(UserModel model) {
                UserManager.newInstance().setDislike(model.getId(), model.getHas_dis_likes_count() + 1);
            }
        });
        adapter.setViewListener(new EmployeeListAdapter.OnViewClick() {
            @Override
            public void onClick(UserModel model) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frgmCont, ProfileFragment.newInstance(model));
                transaction.commit();
            }
        });
        binding.rvEmployee.setAdapter(adapter);
        presenter.getUsers();
        return binding.getRoot();
    }

    @Override
    public void getAllUser(ArrayList<UserModel> listUser) {
        Iterator it = listUser.iterator();
        while (it.hasNext()){
            UserModel item = (UserModel) it.next();
            if(item.getId().equals(FirebaseAuth.getInstance().getUid())){
                it.remove();
            }
        }
        if (listUser.size() > 0) {
            adapter.setUsers(listUser);
            adapter.notifyDataSetChanged();
        }
    }
}
