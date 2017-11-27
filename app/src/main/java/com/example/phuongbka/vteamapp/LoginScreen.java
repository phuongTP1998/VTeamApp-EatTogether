package com.example.phuongbka.vteamapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;

public class LoginScreen extends AppCompatActivity {
    TextInputEditText txtEmail, txtPassword;
    Button btnLogin, btnRegister;
    FirebaseAuth mFbAuth;
    FirebaseDatabase mFbDatabase;
    DatabaseReference mUsers;
    RelativeLayout loginScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        initView();
    }

    private void initView() {
        //firebase
        mFbAuth = FirebaseAuth.getInstance();
        mFbDatabase = FirebaseDatabase.getInstance();
        mUsers = mFbDatabase.getReference("Users");

        //screen init
        loginScreen = findViewById(R.id.login_screen);
        txtEmail = (findViewById(R.id.input_field).findViewById(R.id.txt_email));
        txtPassword = (findViewById(R.id.input_field).findViewById(R.id.txt_password));
        btnLogin = findViewById(R.id.bt_login);
        btnRegister = findViewById(R.id.bt_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterDialog();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checklogin();
            }
        });
    }

    private void checklogin() {
        btnLogin.setEnabled(false);
        if (TextUtils.isEmpty(txtEmail.getText().toString())) {
            Snackbar.make(loginScreen, "Please enter email address", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(txtPassword.getText().toString())) {
            Snackbar.make(loginScreen, "Please enter password", Snackbar.LENGTH_SHORT).show();
            return;
        }

        //waiting progress
        final android.app.AlertDialog waitingDialog = new SpotsDialog(LoginScreen.this);
        waitingDialog.show();

        mFbAuth.signInWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(LoginScreen.this, MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                waitingDialog.dismiss();
                Snackbar.make(loginScreen, "Failed " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
        btnLogin.setEnabled(true);
    }

    private void showRegisterDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("REGISTER");
        alertDialog.setMessage("Please use email to register");

        LayoutInflater inflater = LayoutInflater.from(this);
        View registerLayout = inflater.inflate(R.layout.layout_register, null);

        final TextInputEditText txtEmail = registerLayout.findViewById(R.id.register_txt_email);
        final TextInputEditText txtPassword = registerLayout.findViewById(R.id.register_txt_password);
        final TextInputEditText txtUsername = registerLayout.findViewById(R.id.register_txt_username);
        final TextInputEditText txtPhone = registerLayout.findViewById(R.id.register_txt_phone);

        alertDialog.setView(registerLayout);

        //Set Button
        alertDialog.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                if (TextUtils.isEmpty(txtEmail.getText().toString())) {
                    Snackbar.make(loginScreen, "Please enter your email", Snackbar.LENGTH_SHORT);
                    return;
                }
                if (TextUtils.isEmpty(txtPassword.getText().toString())) {
                    Snackbar.make(loginScreen, "Please enter password", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(txtUsername.getText().toString())) {
                    Snackbar.make(loginScreen, "Please enter password", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(txtPhone.getText().toString())) {
                    Snackbar.make(loginScreen, "Please enter password", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                //check password too short
                if (txtPassword.getText().toString().length() < 6) {
                    Snackbar.make(loginScreen, "Password enter too short", Snackbar.LENGTH_SHORT).show();
                }

                //Register
                mFbAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(txtEmail.getText().toString());
                                user.setName(txtUsername.getText().toString());
                                user.setPassword(txtPassword.getText().toString());
                                user.setPhone(txtPhone.getText().toString());

                                //Use email to key
                                mUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(loginScreen, "Register Succesfully", Snackbar.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(loginScreen, "Failed " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(loginScreen, "Failed " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });

        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }


}
