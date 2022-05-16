package com.cstech.itecy.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cstech.itecy.BuildConfig;
import com.cstech.itecy.ConnectionDetector;
import com.cstech.itecy.ModelClasess.ForgotPasswordResponse;
import com.cstech.itecy.ModelClasess.JobApplyResponse;
import com.cstech.itecy.ModelClasess.LoginData;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.ServiceApi;
import com.cstech.itecy.Session;
import com.cstech.itecy.utils.Utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    TextView login,register,contactUs,aboutUs,forgotPassword;
    String UserName,Password;
    CheckBox RememberMe;
    ProgressDialog progressDialog;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor editor;
    String GetUsername="",GetPassword="";
    Session session;
    ConnectionDetector checkNetWork;
    RestService service;
    String UserId,DidError,errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        session=new Session(this);
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        login= findViewById(R.id.login);
        register= findViewById(R.id.signUp);
        contactUs= findViewById(R.id.contactUs);
        aboutUs= findViewById(R.id.aboutUs);
        forgotPassword= findViewById(R.id.forgotPassword);
        UserName=username.getText().toString();
        Password=password.getText().toString();
        RememberMe= findViewById(R.id.rememberMe);
        service=new RestService();

        loginPreferences=getSharedPreferences("ITecy",MODE_PRIVATE);
        editor=loginPreferences.edit();
        Boolean  saveLogin = loginPreferences.getBoolean("saveLogin", false);

        GetUsername=getIntent().getStringExtra("username");
        GetPassword=getIntent().getStringExtra("password");

        if (!(GetUsername==null)){
            username.setText(GetUsername);
            password.setText(GetPassword);
        }else {
            if (saveLogin == true) {
                username.setText(loginPreferences.getString("username", ""));
                password.setText(loginPreferences.getString("password", ""));
                RememberMe.setChecked(true);
            }
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkNetWork=new ConnectionDetector(LoginActivity.this);
                if (checkNetWork.isConnectingToInternet()){
                if (Validation()) {
                    Login();
                }
            }else {
                    Utils.showAlertDialog(LoginActivity.this, "check your internet connection!",false);
                }
            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(i);
//                if (ValidadateUserName()){
//
//                    ResetPassword(username.getText().toString().trim());
//                    //GenerateOTP();
//                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //https://www.itecy.com/JS/account/signUp
                //BrowseData("https://www.itecy.com/JS/account/signUp");

                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog aboutUsLayout=new Dialog(LoginActivity.this);
                aboutUsLayout.setContentView(R.layout.activity_about_us);
               TextView webSite=(TextView)aboutUsLayout.findViewById(R.id.webSite);
               TextView AppVersion=(TextView)aboutUsLayout.findViewById(R.id.AppVersion);

                AppVersion.setText(BuildConfig.VERSION_NAME);

                webSite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String companyUrl="http://www.clientservertech.com";
                        Intent intent=new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(companyUrl));
                        startActivity(intent);
                    }
                });

                aboutUsLayout.show();

                //BrowseData("https://www.itecy.com/Home/about");
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BrowseData("https://www.itecy.com/Home/contact");
            }
        });

    }


    private void GenerateOTP() {

        progressDialog.show();
        String EmailId=username.getText().toString().trim();

        Retrofit retrofit=new Retrofit.Builder().
                baseUrl(BuildConfig.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        ServiceApi services = retrofit.create(ServiceApi.class);
       // http://testitecywebapi.azurewebsites.net/Jobseeker/forgotPasswordOtp/sachin.kayithi@clientservertech.com
        services.ForgotPassword(username.getText().toString()).enqueue(new retrofit2.Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, retrofit2.Response<ForgotPasswordResponse> forgotPasswordResponse) {

                if (forgotPasswordResponse!=null){

                    if (forgotPasswordResponse.body().getMessage()!=null){
                        if (!forgotPasswordResponse.body().getMessage().isEmpty()){
                            progressDialog.dismiss();
                            // Utils.showAlertDialog(LoginActivity.this, forgotPasswordResponse.getMessage(),false);
                            Intent intent=new Intent(LoginActivity.this, OtpActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("otp",forgotPasswordResponse.body().getModel().getOtp());
                            intent.putExtra("successMessage",forgotPasswordResponse.body().getMessage());
                            intent.putExtra("jobseekerId",forgotPasswordResponse.body().getModel().getJobSeekerId());
                            intent.putExtra("emailId",forgotPasswordResponse.body().getModel().getEmailId());
                            startActivity(intent);
                            finish();
                        }
                        else {
                            progressDialog.dismiss();
                            Utils.showAlertDialog(LoginActivity.this, forgotPasswordResponse.body().getErrorMessage(),false);
                        }
                    }else {
                        progressDialog.dismiss();
                    }
                }else {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {

                progressDialog.dismiss();
            }
        });


/*
        service.getService().ForgotPassword(EmailId, new Callback<ForgotPasswordResponse>() {
            @Override
            public void success(ForgotPasswordResponse forgotPasswordResponse, Response response) {

                if (forgotPasswordResponse!=null){

                    if (forgotPasswordResponse.getMessage()!=null){
                        if (!forgotPasswordResponse.getMessage().isEmpty()){
                           // Utils.showAlertDialog(LoginActivity.this, forgotPasswordResponse.getMessage(),false);
                            Intent intent=new Intent(LoginActivity.this, ForgotPassword.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("otp",forgotPasswordResponse.getModel().get(0).getOtp());
                            intent.putExtra("successMessage",forgotPasswordResponse.getMessage());
                            intent.putExtra("jobseekerId",forgotPasswordResponse.getModel().get(0).getJobseekerId());
                            intent.putExtra("emailId",forgotPasswordResponse.getModel().get(0).getEmailId());
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Utils.showAlertDialog(LoginActivity.this, forgotPasswordResponse.getErrorMessage(),false);
                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
*/
    }

    private boolean ValidadateUserName() {
        if (!UserName()){
            return false;
        }else {
            return true;
        }

    }

    private boolean InternetConnection() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return false;

    }

    private void BrowseData(String url) {

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }


    private void Login() {
        progressDialog.show();
        UserName=username.getText().toString().trim();
        Password=password.getText().toString().trim();
        service.getService().getLogin(UserName, Password, new Callback<LoginData>() {
            @Override
            public void success(LoginData loginData, Response response) {

                UserId=loginData.getMessage();
                DidError=loginData.getDidError();
                errorMessage=loginData.getErrorMessage();



                if (!UserId.isEmpty()){
                    editor.putString("username", UserName);
                    editor.putString("userId",UserId);
                    if (RememberMe.isChecked()){
                        editor.putBoolean("saveLogin", true);
                        editor.putString("password", Password);

                    }else {
                        loginPreferences.edit().remove("username").commit();
                        loginPreferences.edit().remove("password").commit();
                        loginPreferences.edit().remove("saveLogin").commit();
                    }
                    editor.commit();

                    session.setLoging(true);
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("username",UserName);
                    startActivity(intent);
                    finish();
                    progressDialog.dismiss();

                }else {
                    Utils.showAlertDialog(LoginActivity.this, errorMessage,false);
                    progressDialog.dismiss();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();

            }
        });

    }

    private boolean Validation() {
        if (!UserName()){
            return false;
        }else if (!Password()){
            return false;
        }else {
            return true;
        }

    }

    private boolean Password() {

        if (password.getText().toString().trim().isEmpty()){
            password.setError("Password should not be empty");
            password.requestFocus();
            return false;
        }else {
            return true;
        }
    }

    private boolean UserName() {
        if (username.getText().toString().trim().isEmpty()){
            username.setError("Username should not be empty");
            username.requestFocus();
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        finishAffinity();
        finish();
    }
}
