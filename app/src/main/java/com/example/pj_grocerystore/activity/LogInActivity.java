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
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.model.Account;
import com.example.pj_grocerystore.model.Internet;
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
    private SharedPreferences sharedPreferences;
    private Context context = this;
    private Intent intent;

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
                    Toast.makeText(LogInActivity.this, "You need Internet to LogIn", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(LogInActivity.this, "Sai thông tin !", Toast.LENGTH_SHORT).show();
                                } else {
                                    boolean pass = false;
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        if (dataSnapshot.getValue(Account.class).getUsername().equals(username) &&
                                                dataSnapshot.getValue(Account.class).getPassword().equals(password)) {
                                            if (dataSnapshot.getValue(Account.class).getIsActive() == 0) {
                                                pass = true;
                                                Account account1 = new Account(username, password, dataSnapshot.getValue(Account.class).getEmail(), 1, 0);
                                                databaseReference.child(username).setValue(account1);
                                                DataLocalManager.setStrng("Username", username);
                                                DataLocalManager.setStrng("Email", dataSnapshot.getValue(Account.class).getEmail());
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                pass = true;
                                                String messenger_warning = getResources().getString(R.string.messenger_warning_logged);
                                                Toast.makeText(context, "" + messenger_warning, Toast.LENGTH_SHORT).show();
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
        sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        intent = new Intent(LogInActivity.this, MainActivity.class);
    }

    private void checkLogged() {
        if (DataLocalManager.checkExitst("Username")) {
            startActivity(intent);
            finish();
        }
    }
}