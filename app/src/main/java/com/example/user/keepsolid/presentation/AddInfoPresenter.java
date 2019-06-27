package com.example.user.keepsolid.presentation;

import com.example.user.keepsolid.manager.UserManager;
import com.example.user.keepsolid.presentation.view.AddInfoView;

public class AddInfoPresenter extends BasePresenter<AddInfoView> {
    public void setPersonalInfo(String info) {
        UserManager.newInstance().setInfo(info);
    }

    public void setPersonalSkills(String skills) {
        UserManager.newInstance().setSkills(skills);
    }

    public void setPersonalPosition(String posotion) {
        UserManager.newInstance().setPosition(posotion);
    }
}
