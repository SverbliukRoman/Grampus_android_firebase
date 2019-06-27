package com.example.user.keepsolid.ui.dialog;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.user.keepsolid.R;
import com.example.user.keepsolid.presentation.AddInfoPresenter;
import com.example.user.keepsolid.presentation.view.AddInfoView;

import static com.example.user.keepsolid.utils.Constants.ADD_INFO_ACTION;
import static com.example.user.keepsolid.utils.Constants.ADD_POSITION_ACTION;
import static com.example.user.keepsolid.utils.Constants.ADD_SKILLS_ACTION;
import static com.example.user.keepsolid.utils.Constants.BUNDLE_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddInfoDialog extends DialogFragment implements AddInfoView {
    AddInfoPresenter presenter;
    String action;

    @SuppressLint("ValidFragment")
    private AddInfoDialog() {
    }

    public static AddInfoDialog newInstance(String typeAction) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY, typeAction);
        AddInfoDialog fragment = new AddInfoDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        action = getArguments().getString(BUNDLE_KEY);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.dialog_add_info, container, false);
        presenter = new AddInfoPresenter();
        presenter.attachView(this);

        if(action.equals(ADD_INFO_ACTION)){
            view.findViewById(R.id.btn_save_info).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText text = view.findViewById(R.id.et_info);
                    presenter.setPersonalInfo(text.getText().toString());
                    dismiss();
                }
            });
        } else if(action.equals(ADD_SKILLS_ACTION)) {
            view.findViewById(R.id.btn_save_info).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText text = view.findViewById(R.id.et_info);
                    presenter.setPersonalSkills(text.getText().toString());
                    dismiss();
                }
            });
        } else if(action.equals(ADD_POSITION_ACTION)){
            view.findViewById(R.id.btn_save_info).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText text = view.findViewById(R.id.et_info);
                    presenter.setPersonalPosition(text.getText().toString());
                    dismiss();
                }
            });
        }

        return view;
    }

    @Override
    public void setUserInfo() {

    }

    @Override
    public void setUserSkills() {

    }
}
