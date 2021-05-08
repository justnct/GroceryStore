package com.example.pj_grocerystore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.model.Account;
import com.example.pj_grocerystore.model.CustomToast;
import com.example.pj_grocerystore.model.MD5;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterAccountActivity extends AppCompatActivity {
    private TextInputLayout inputLayout_username_register, inputLayout_password_register, inputLayout_password2_register, inputLayout_email_register;
    private TextInputEditText editText_username_register, editText_password_register, editText_password2_register, editText_email_register;
    private TextView tv_backToLogIn;
    private Button btn_register;
    private ArrayList<TextInputLayout> listInputLayout;
    private ArrayList<TextInputEditText> listEditText;
    private Intent backToLogin;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        //declare
        addUi();


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check empty field
                boolean pass = true;
                for (int i = 0; i < listEditText.size(); i++) {
                    if (TextUtils.isEmpty(String.valueOf(listEditText.get(i).getText()))) {
                        String messenger_warning = getResources().getString(R.string.messenger_warning_empty_field);
                        listInputLayout.get(i).setError(messenger_warning);
                        pass = false;
                    }
                }
                if (pass) {
                    //full field
                    //delete messenger warning of text field
                    for (int i = 0; i < listInputLayout.size(); i++) {
                        listInputLayout.get(i).setError(null);
                    }
                    String username = String.valueOf(editText_username_register.getText());
                    String password = String.valueOf(editText_password_register.getText());
                    String re_password = String.valueOf(editText_password2_register.getText());
                    String email = String.valueOf(editText_email_register.getText());
                    //check pass and repass
                    //pass and repass match
                    if (password.equals(re_password)) {
                        //check email
                        //match email
                        if (validate(email)) {
                            //check username isExist
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Account");
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int count = 0;
                                    if (snapshot.getChildrenCount() == 0) {
                                        Account account1 = new Account(username, MD5.getMd5(password), email, 0, 0);
                                        databaseReference.child(username).setValue(account1);
                                        CustomToast.customToast(RegisterAccountActivity.this, "Tạo tài khoản thành công :3");
                                        startActivity(backToLogin);
                                    } else {
                                        for (DataSnapshot data : snapshot.getChildren()) {
                                            //username not exist
                                            if (!Objects.requireNonNull(data.getValue(Account.class)).getUsername().equals(username)) {
                                                //check email exist
                                                //email not exist
                                                if (!Objects.requireNonNull(data.getValue(Account.class)).getEmail().equals(email)) {
                                                    count++;
                                                    if(count == snapshot.getChildrenCount()){ //email haven't been register
                                                        Account account1 = new Account(username, MD5.getMd5(password), email, 0, 0);
                                                        databaseReference.child(username).setValue(account1);
                                                        CustomToast.customToast(RegisterAccountActivity.this, "Tạo tài khoản thành công :3");
                                                        startActivity(backToLogin);
                                                        finish();
                                                    }
                                                } else {
                                                    //email exist
                                                    String messenger_warning = getResources().getString(R.string.messenger_warning_exist_email);
                                                    inputLayout_email_register.setError(messenger_warning);
                                                }
                                            } else {
                                                //username exis
                                                String messenger_warning = getResources().getString(R.string.messenger_warning_exist_username);
                                                inputLayout_username_register.setError(messenger_warning);
                                            }
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            //not match regex email
                            //EMPTY (DON'T NEED)
                        }
                    } else {
                        //pass and repass not match
                        String messenger_warning = getResources().getString(R.string.messenger_warning_notmatch_pass_and_repass);
                        inputLayout_password_register.setError(messenger_warning);
                        inputLayout_password2_register.setError(messenger_warning);
                    }
                }
            }
        });


        tv_backToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(backToLogin);
            }
        });


    }

    private void addUi() {
        //from XML
        inputLayout_username_register = findViewById(R.id.inputLayout_username_register);
        inputLayout_password_register = findViewById(R.id.inputLayout_password_register);
        inputLayout_password2_register = findViewById(R.id.inputLayout_password2_register);
        inputLayout_email_register = findViewById(R.id.inputLayout_email_register);
        editText_username_register = findViewById(R.id.editText_username_register);
        editText_password_register = findViewById(R.id.editText_password_register);
        editText_password2_register = findViewById(R.id.editText_password2_register);
        editText_email_register = findViewById(R.id.editText_email_register);
        tv_backToLogIn = findViewById(R.id.tv_backToLogIn);
        btn_register = findViewById(R.id.btn_register);

        //free
        listInputLayout = new ArrayList<>();
        listInputLayout.add(inputLayout_username_register);
        listInputLayout.add(inputLayout_password_register);
        listInputLayout.add(inputLayout_password2_register);
        listInputLayout.add(inputLayout_email_register);

        listEditText = new ArrayList<>();
        listEditText.add(editText_username_register);
        listEditText.add(editText_password_register);
        listEditText.add(editText_password2_register);
        listEditText.add(editText_email_register);

        backToLogin = new Intent(RegisterAccountActivity.this, LogInActivity.class);

    }

    private boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}