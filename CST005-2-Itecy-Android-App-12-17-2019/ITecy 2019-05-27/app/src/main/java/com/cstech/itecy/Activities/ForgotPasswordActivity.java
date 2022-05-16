package com.cstech.itecy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cstech.itecy.ModelClasess.ForgotModle;
import com.cstech.itecy.ModelClasess.ForgotPasswordResponse;
import com.cstech.itecy.ModelClasess.JobApplyResponse;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.utils.Utils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    TextView tvForgetBtn;
    RestService service;
    String userMailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        username = findViewById(R.id.username);
        tvForgetBtn = findViewById(R.id.forgotPassword);
        tvForgetBtn.setOnClickListener(this);
        service=new RestService();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgotPassword:
                if (ValidadateUserName()) {
                    userMailId = username.getText().toString().trim();
                    ResetPassword(userMailId);
                }
                break;
        }
    }

    private boolean ValidadateUserName() {
        if (!UserName()) {
            return false;
        } else {
            return true;
        }

    }

    private boolean UserName() {
        if (username.getText().toString().trim().isEmpty()) {
            username.setError("Username should not be empty");
            username.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    private void ResetPassword(String username) {
        service.getService().ResetPassword(username, new Callback<ForgotModle>() {
            @Override
            public void success(ForgotModle jobApplyResponse, Response response) {
                if (!jobApplyResponse.getMessage().isEmpty()) {
//                    Intent i = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
//                    startActivity(i);
//                    finish();
                    Utils.showAlertDialogLogin(ForgotPasswordActivity.this, jobApplyResponse.getMessage(), false);
                } else {
                    Utils.showAlertDialog(ForgotPasswordActivity.this, String.valueOf(jobApplyResponse.getErrorMessage()), false);
                }
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

}