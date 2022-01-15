package com.example.watchnext.models.entities;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.watchnext.ContextApplication;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class User {

    public static final String LAST_UPDATED = "UserLastUpdated";

    @PrimaryKey
    @NonNull
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String imageUrl;
    private Long updateDate;

    public User(@NonNull String id,
                String firstName,
                String lastName,
                String email,
                String password,
                String imageUrl,
                Long updateDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
        this.updateDate = updateDate;
    }

    public static User create(Map<String, Object> user) {
        String id = Objects.requireNonNull(user.get("id")).toString();
        String firstName = Objects.requireNonNull(user.get("firstName")).toString();
        String lastName = Objects.requireNonNull(user.get("lastName")).toString();
        String email = Objects.requireNonNull(user.get("email")).toString();
        String password = Objects.requireNonNull(user.get("password")).toString();
        String imageUrl = Objects.requireNonNull(user.get("imageUrl")).toString();
        Timestamp ts = (Timestamp) Objects.requireNonNull(user.get("updateDate"));
        Long updateDate = ts.getSeconds();
        return new User(id, firstName, lastName, email, password, imageUrl, updateDate);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("email", email);
        result.put("password", password);
        result.put("imageUrl", imageUrl);
        result.put("updateDate", FieldValue.serverTimestamp());
        return result;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public static void setLocalLastUpdated(Long timestamp) {
        SharedPreferences.Editor ed = ContextApplication.getContext().getSharedPreferences("TAG", Context.MODE_PRIVATE).edit();
        ed.putLong(User.LAST_UPDATED, timestamp);
        ed.apply();
    }

    public static Long getLocalLastUpdated() {
        SharedPreferences sp = ContextApplication
                .getContext().getSharedPreferences("TAG", Context.MODE_PRIVATE);
        return sp.getLong(User.LAST_UPDATED, 0);
    }
}