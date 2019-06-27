package com.example.user.keepsolid.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mexanik on 29.09.2018.
 */

public class UserModel {
    String id;
    String name;
    String email;
    String email_verified_at = "";
    String position = "";
    String photo_url = "";
    String description = "";
    String skills = "";
    int has_likes_count = 0;
    int has_dis_likes_count = 0;

    public ArrayList<ModelLike> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<ModelLike> likes) {
        this.likes = likes;
    }

    ArrayList<ModelLike> likes;

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public int getHas_likes_count() {
        return has_likes_count;
    }

    public void setHas_likes_count(int has_likes_count) {
        this.has_likes_count = has_likes_count;
    }

    public int getHas_dis_likes_count() {
        return has_dis_likes_count;
    }

    public void setHas_dis_likes_count(int has_dis_likes_count) {
        this.has_dis_likes_count = has_dis_likes_count;
    }
}
