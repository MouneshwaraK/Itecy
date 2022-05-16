package com.cstech.itecy.Activities;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cstech.itecy.R;
import com.cstech.itecy.utils.Utils;

public class OtpActivity extends AppCompatActivity {

    String OTP;
    String successMessage="";
    EditText otp;
    TextView sendOTP;
    String EditOTP="";
    int jobseekerId;
    String emailId="";
    SharedPreferences loginPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        loginPreferences=getSharedPreferences("ITecy",MODE_PRIVATE);
        editor=loginPreferences.edit();

        OTP=getIntent().getStringExtra("otp");
        successMessage=getIntent().getStringExtra("successMessage");
        jobseekerId=getIntent().getIntExtra("jobseekerId",0);
        emailId=getIntent().getStringExtra("emailId");
        if (!successMessage.isEmpty()){
            Utils.showAlertDialog(this, successMessage,false);
        }


        otp=(EditText)findViewById(R.id.otp);
        sendOTP=(TextView)findViewById(R.id.sendOTP);
       TextView cancel=(TextView)findViewById(R.id.cancel);

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditOTP=otp.getText().toString().trim();
                if (!EditOTP.isEmpty()){
                    if (otp.getText().toString().equalsIgnoreCase(OTP)){

                        editor.putString("userId", String.valueOf(jobseekerId));
                        editor.putString("username", emailId);
                        editor.apply();
                        Intent intent=new Intent(OtpActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        Utils.showAlertDialog(OtpActivity.this, "Login successfully",false);
                    } else {
                        Utils.showAlertDialog(OtpActivity.this, "OTP is not matched",false);
                    }
                }else {
                    otp.setError("Field should not be empty");
                    otp.requestFocus();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(OtpActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }
}
