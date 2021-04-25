package com.example.pj_grocerystore.viewmodel;

import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.pj_grocerystore.BR;
import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.activity.LogInActivity;
import com.example.pj_grocerystore.model.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInViewModel extends BaseObservable {
    private String email;
    private String pass;
    private String emailError;
    private String passError;

    @Bindable
    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
        notifyPropertyChanged(BR.emailError);
    }

    @Bindable
    public String getPassError() {
        return passError;
    }

    public void setPassError(String passError) {
        this.passError = passError;
        notifyPropertyChanged(BR.passError);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
        notifyPropertyChanged(BR.pass);
    }

    public void LogIn() {

    }
}
