package com.mina.doctors_fadfadly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mina.doctors_fadfadly.Base.BaseActivity;
import com.mina.doctors_fadfadly.Model.User;

public class RegisterActivity extends BaseActivity implements View.OnClickListener,
        OnFailureListener, OnCompleteListener<AuthResult> {


    protected ImageView log;
    protected ImageView icon;
    protected TextView title;
    protected TextView username;
    protected EditText usernameText;
    protected TextView age;
    protected EditText ageText;
    protected TextView email;
    protected EditText emailText;
    protected TextView password;
    protected EditText passwordText;
    protected TextView gender;
    protected RadioButton male;
    protected RadioButton female;
    protected RadioGroup radio;
    protected EditText nationalIdRegisterEdittext;
    protected TextView nationalIdRegister;
    protected EditText addressRegisterEdittext;
    protected TextView addressRegister;
    protected EditText bachelorEdit;
    protected TextView bachelor;
    protected Button buttonRegister;
    protected CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_register);
        initView();

    }
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        hideProgressDialog();
        if(task.isSuccessful()){
            DataUtil.user =
                    FirebaseAuth.getInstance().getCurrentUser();
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            finish();
        }
    }


    @Override
    public void onFailure(@NonNull Exception e) {
        hideProgressDialog();
        showMessage("User already exist","ok");

    }

    public void onRadioButtonClicked(View view) {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_Register) {
            if (view.getId() == R.id.login) {
                String EmailText=email.getText().toString();
                String passwordText=password.getText().toString();
                String UserName=username.getText().toString();
                if(EmailText.trim().isEmpty()){
                    email.setError("requried");
                    return;
                }
                if(passwordText.trim().isEmpty()){
                    password.setError("requried");
                    return;
                }

                //authontification
                User user=new User();
                user.setName(EmailText);
                user.setPassword(passwordText);
                FirebaseAuth auth=FirebaseAuth.getInstance();

                auth.signInWithEmailAndPassword(EmailText,passwordText)
                        .addOnCompleteListener(this)
                        .addOnFailureListener(this);






            }
            else if (view.getId()==R.id.sign_up){
                startActivity(new Intent(this,RegisterActivity.class));
            }
        }
    }

    private void initView() {
        log = (ImageView) findViewById(R.id.log);
        icon = (ImageView) findViewById(R.id.icon);
        title = (TextView) findViewById(R.id.title);
        username = (TextView) findViewById(R.id.username);
        usernameText = (EditText) findViewById(R.id.username_text);
        age = (TextView) findViewById(R.id.age);
        ageText = (EditText) findViewById(R.id.age_text);
        email = (TextView) findViewById(R.id.email);
        emailText = (EditText) findViewById(R.id.email_text);
        password = (TextView) findViewById(R.id.password);
        passwordText = (EditText) findViewById(R.id.password_text);
        gender = (TextView) findViewById(R.id.gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        radio = (RadioGroup) findViewById(R.id.radio);
        nationalIdRegisterEdittext = (EditText) findViewById(R.id.nationalId_register_edittext);
        nationalIdRegister = (TextView) findViewById(R.id.nationalId_register);
        addressRegisterEdittext = (EditText) findViewById(R.id.address_register_edittext);
        addressRegister = (TextView) findViewById(R.id.address_register);
        bachelorEdit = (EditText) findViewById(R.id.bachelor_edit);
        bachelor = (TextView) findViewById(R.id.bachelor);
        buttonRegister = (Button) findViewById(R.id.button_Register);
        buttonRegister.setOnClickListener(RegisterActivity.this);
        card = (CardView) findViewById(R.id.card);
    }
}

