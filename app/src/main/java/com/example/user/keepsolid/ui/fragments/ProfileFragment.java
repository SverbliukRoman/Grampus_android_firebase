package com.example.user.keepsolid.ui.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.keepsolid.R;
import com.example.user.keepsolid.SharedPrefHelper;
import com.example.user.keepsolid.databinding.FragmentProfileBinding;
import com.example.user.keepsolid.entity.UserModel;
import com.example.user.keepsolid.presentation.ProfilePresenter;
import com.example.user.keepsolid.presentation.view.ProfileView;
import com.example.user.keepsolid.ui.activity.MainActivity;
import com.example.user.keepsolid.ui.dialog.AddInfoDialog;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.example.user.keepsolid.utils.Constants.ADD_INFO_ACTION;
import static com.example.user.keepsolid.utils.Constants.ADD_POSITION_ACTION;
import static com.example.user.keepsolid.utils.Constants.ADD_SKILLS_ACTION;
import static com.example.user.keepsolid.utils.Constants.BUNDLE_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileView {
    static final int GALLERY_REQUEST = 1;
    FragmentProfileBinding binding;
    ProfilePresenter profilePresenter;
    private PieChart chart;
    UserModel user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(UserModel model) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY, new Gson().toJson(model));
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        if (SharedPrefHelper.getFirstStart(getContext())) {
            binding.achievements.setVisibility(View.INVISIBLE);
        }
        profilePresenter = new ProfilePresenter();
        profilePresenter.attachView(this);

        if (getArguments() != null && getArguments().getString(BUNDLE_KEY) != null) {

            user = new Gson().fromJson(getArguments().getString(BUNDLE_KEY), UserModel.class);
            getInfo(user);
            binding.btnAddInfo.setVisibility(View.GONE);
            binding.btnAddSkills.setVisibility(View.GONE);
            binding.btnAddPosition.setVisibility(View.GONE);
        } else {

            setClickToAddInfo();
            setClickToUploadPhoto();
            updatePersonalData();
        }

        return binding.getRoot();
    }

    private void setClickToUploadPhoto() {
        binding.personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

            }
        });
    }

    private void updatePersonalData() {
        if (getArguments() == null) {
            profilePresenter.getInfo();
        }
    }

    private void setClickToAddInfo() {
        binding.btnAddInfo.setVisibility(View.VISIBLE);
        binding.btnAddInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddInfoDialog.newInstance(ADD_INFO_ACTION).show(getChildFragmentManager(), null);
            }
        });
        binding.btnAddSkills.setVisibility(View.VISIBLE);
        binding.btnAddSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddInfoDialog.newInstance(ADD_SKILLS_ACTION).show(getChildFragmentManager(), null);
            }
        });
        binding.btnAddPosition.setVisibility(View.VISIBLE);
        binding.btnAddPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddInfoDialog.newInstance(ADD_POSITION_ACTION).show(getChildFragmentManager(), null);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.clear();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Bitmap bitmap = null;

        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = intent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    profilePresenter.uploadPhoto(bitmap);
                    binding.personImage.setImageBitmap(bitmap);
                }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //updatePersonalData();
    }

    @Override
    public void getInfo(UserModel userModel) {
        if (userModel != null && !TextUtils.isEmpty(userModel.getPhoto_url()))
            Picasso.get().load(userModel.getPhoto_url())
                    .into(binding.personImage);
        else {
            binding.personImage.setImageDrawable(getActivity().getDrawable(R.drawable.error_picasso));
        }
        binding.userName.setText(userModel.getName());
        binding.userPosition.setText(userModel.getPosition());
        binding.likes.setText("" + userModel.getHas_likes_count());
        binding.dislikes.setText("" + userModel.getHas_dis_likes_count());
        binding.userInfo.setText(userModel.getDescription());
        binding.skills.setText(userModel.getSkills());
        if (userModel.getId().equals(FirebaseAuth.getInstance().getUid()))
            ((MainActivity) getActivity()).setImage(userModel.getPhoto_url(), userModel.getName(), userModel.getPosition());
        //((MainActivity) getActivity()).setName(userModel.getName(), userModel.getPosition());
    }

    @Override
    public void getPhotoUri(Uri uri) {
//        if (uri != null && !TextUtils.isEmpty(uri.toString()))
//            Picasso.get().load(uri)
//                    .into(binding.personImage);
//        else {
//            binding.personImage.setImageDrawable(getActivity().getDrawable(R.drawable.error_picasso));
//        }
//        ((MainActivity) getActivity()).setPhoto(uri.toString());
    }
}
