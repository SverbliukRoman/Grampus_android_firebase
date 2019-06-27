package com.example.user.keepsolid.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mexanik on 29.09.2018.
 */

public class ModelLike {
    String type;
    String text;
    String countLikes;
    String typeLike;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(String countLikes) {
        this.countLikes = countLikes;
    }

    public String getTypeLike() {
        return typeLike;
    }

    public void setTypeLike(String typeLike) {
        this.typeLike = typeLike;
    }

    public enum TypeLike {
        HARDWORKER("hardworker"),
        AWARE("aware"),
        SOFTSKILL("softskills"),
        EXTRAVERT("extravert"),
        BESTLOOKER("bestlooker");

        String text;

        TypeLike(String s) {
            this.text = s;
        }

        public static List<String> getArray(){
            List<String> result = new ArrayList<>();
            for (TypeLike value : TypeLike.values()) {
                result.add(value.toString());
            }
            return result;
        }

        @Override public String toString() {
            return text;
        }
    }

    public enum TypeDislike {
        ANGRY("angry"),
        DEADLINER("deadliner"),
        INTROVERT("introvert");

        String text;

        TypeDislike(String s) {
            this.text = text;
        }

        @Override public String toString() {
            return text;
        }
    }
}
