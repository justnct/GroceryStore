package com.example.pj_grocerystore.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.model.Account;
import com.example.pj_grocerystore.model.CustomToast;
import com.example.pj_grocerystore.model.Internet;
import com.example.pj_grocerystore.model.MD5;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity {
    private RelativeLayout main;
    private TextView tv_slogan1, tv_slogan2, tv_register;
    private TextInputLayout inputLayout_username, inputLayout_password;
    private TextInputEditText editText_username, editText_password;
    private Button btn_LogIn;
    private DatabaseReference databaseReference;
    private Context context = this;
    private Intent intent;
    private CheckBox cb_rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //declare
        addUI();
        checkLogged();

        //button LogIn
        btn_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Internet.isNetworkAvailable(LogInActivity.this)) {
                    CustomToast.customToast(LogInActivity.this, "You need Internet to LogIn");
                } else {
                    String username = String.valueOf(editText_username.getText());
                    String password = String.valueOf(editText_password.getText());
                    String messerger_warning = getResources().getString(R.string.messenger_warning_empty_field);
                    //check empty field
                    //is empty
                    if (TextUtils.isEmpty(username)) {
                        inputLayout_username.setError(messerger_warning);
                    }
                    if (TextUtils.isEmpty(password)) {
                        inputLayout_password.setError(messerger_warning);
                    }
                    //not empty
                    if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                        inputLayout_username.setError(null);
                        inputLayout_password.setError(null);
                        //check
                        databaseReference = FirebaseDatabase.getInstance().getReference("Account");
                        //Need CONTINUE
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.getChildrenCount() == 0) {
                                    CustomToast.customToast(LogInActivity.this, "Sai thông tin !");
                                } else {
                                    boolean pass = false;
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        Account test = dataSnapshot.getValue(Account.class);
                                        if (test.getUsername().equals(username) &&
                                                test.getPassword().equals(MD5.getMd5(password))) {
                                            if (test.getIsActive() == 0) {
                                                pass = true;
                                                test.setIsActive(1);
                                                databaseReference.child(username).setValue(test);
                                                DataLocalManager.setStrng("Username", test.getUsername());
                                                DataLocalManager.setStrng("Password", password);
                                                DataLocalManager.setAccount("Account", test);
                                                checkRememberMe();
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                pass = true;
                                                String messenger_warning = getResources().getString(R.string.messenger_warning_logged);
                                                CustomToast.customToast(LogInActivity.this, messenger_warning);
                                            }
                                        }
                                    }
                                    if (!pass) {
                                        String messenger_warning = getResources().getString(R.string.messenger_warning_wrong_infor);
                                        inputLayout_username.setError(messenger_warning);
                                        inputLayout_password.setError(messenger_warning);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegisterAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkRememberMe() {
        if(cb_rememberMe.isChecked()){
            DataLocalManager.setBoolean("remember_me", true);
        } else {
            DataLocalManager.removeKey("remember_me");
            DataLocalManager.removeKey("Username");
            DataLocalManager.removeKey("Password");
        }
    }


    private void addUI() {
        //declare
        main = findViewById(R.id.main_logIn);
        tv_slogan1 = findViewById(R.id.tv_slogan1);
        tv_slogan2 = findViewById(R.id.tv_slogan2);
        tv_register = findViewById(R.id.tv_registerAccount);
        inputLayout_username = findViewById(R.id.inputLayout_username);
        inputLayout_password = findViewById(R.id.inputLayout_password);
        editText_username = findViewById(R.id.editText_username);
        editText_password = findViewById(R.id.editText_password);
        btn_LogIn = findViewById(R.id.btn_logIn);
        intent = new Intent(LogInActivity.this, MainActivity.class);
        cb_rememberMe = findViewById(R.id.checkbox_remember_me);

        if (DataLocalManager.checkExitst("remember_me")){
            editText_username.setText(DataLocalManager.getString("Username1"));
            editText_password.setText(DataLocalManager.getString("Password"));
            cb_rememberMe.setChecked(true);
        }
    }

    private void checkLogged() {
        if (DataLocalManager.checkExitst("Username")) {
            startActivity(intent);
            finish();
        }
    }
}