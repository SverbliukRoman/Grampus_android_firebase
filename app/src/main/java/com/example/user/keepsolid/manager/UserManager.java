package com.example.user.keepsolid.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.user.keepsolid.entity.UserModel;
import com.example.user.keepsolid.entity.callbacks.GenericCallback;
import com.example.user.keepsolid.entity.callbacks.SimpleGenericCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public class UserManager {
    private FirebaseAuth mAuth;
    private static final String TAG = UserManager.class.getName();

    private UserManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    public static UserManager newInstance() {
        UserManager fragment = new UserManager();
        return fragment;
    }

    public void getUserInfo(final GenericCallback<UserModel, String> listener) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                listener.onResult(userModel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getAllUserInfo(final GenericCallback<ArrayList<UserModel>, String> listener) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<UserModel> model = new ArrayList<>();
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    UserModel userModel = user.getValue(UserModel.class);
                    model.add(userModel);
                    //model.add(user.getValue(UserModel.class));
                }
                listener.onResult(model);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setLike(String uid, int likeValue) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");
        myRef.child(uid).child("has_likes_count").setValue(likeValue);
    }

    public void setDislike(String uid, int dislikeValue) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");
        myRef.child(uid).child("has_dis_likes_count").setValue(dislikeValue);
    }

    public void setInfo(String info) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("user");
        reference
                .child(mAuth.getUid())
                .child("description")
                .setValue(info);
    }

    public void setSkills(String skills) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("user");
        reference
                .child(mAuth.getUid())
                .child("skills")
                .setValue(skills);
    }

    public void setPosition(String position) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("user");
        reference
                .child(mAuth.getUid())
                .child("position")
                .setValue(position);
    }

    public void setPhotoUrl(String uri) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("user");
        reference
                .child(mAuth.getUid())
                .child("photo_url")
                .setValue(uri);
    }

    public void getUserPhoto(String uid, final SimpleGenericCallback<Uri> listener) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference load = storage.getReference("users").child(uid);

        load.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                listener.onResult(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    public void uploadPhoto(Bitmap bitmap, final SimpleGenericCallback<String> litener) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        final StorageReference mountainsRef = storageRef.child("users").child(mAuth.getUid()+".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        final UploadTask uploadTask = mountainsRef.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        litener.onResult(uri.toString());
                    }
                });

            }
        });
    }
}
