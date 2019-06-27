package com.example.user.keepsolid.ui.dialog;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.user.keepsolid.R;
import com.example.user.keepsolid.databinding.DialogCommentLikeBinding;
import com.example.user.keepsolid.entity.ModelLike;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentLikeDialog extends DialogFragment {
    DialogCommentLikeBinding binding;

    public CommentLikeDialog() {
        // Required empty public constructor
    }

    public static CommentLikeDialog newInstance() {

        Bundle args = new Bundle();

        CommentLikeDialog fragment = new CommentLikeDialog();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_comment_like, container, false);
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, ModelLike.TypeLike.getArray());
        binding.spinner.setAdapter(adapter);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UserManager.newInstance().setLike(model.getId(), model.getHas_likes_count() + 1);
                dismiss();
            }
        });
        return binding.getRoot();
    }

}
