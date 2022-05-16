package com.cstech.itecy.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.cstech.itecy.Activities.LoginActivity;
import com.cstech.itecy.Activities.MainActivity;
import com.cstech.itecy.ModelClasess.ChangePassword;
import com.cstech.itecy.ModelClasess.PasswordResponce;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.Session;
import com.cstech.itecy.utils.Utils;

import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {


    RestService service;
    EditText oldPassword,newPassword,confirmPassword;
    TextView updatePassword;
    SharedPreferences preferences;
    String JobseekerId;
    Session session;
    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_change_password, container, false);
        service=new RestService();
        session = new Session(getActivity().getApplicationContext());

        preferences=getActivity().getSharedPreferences("ITecy", Context.MODE_PRIVATE);
        JobseekerId=preferences.getString("userId","");
        oldPassword=view.findViewById(R.id.oldPassword);
        newPassword=view.findViewById(R.id.newPassword);
        confirmPassword=view.findViewById(R.id.confirmpassword);
        updatePassword=view.findViewById(R.id.updatePassword);

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Validate()){

                    UpdatePassword();
                }

            }
        });
        return view;
    }

    private void UpdatePassword() {

        ChangePassword changePassword=new ChangePassword();
        changePassword.setJobseekerId(Integer.valueOf(JobseekerId));
        changePassword.setOldPassword(oldPassword.getText().toString());
        changePassword.setNewPassword(newPassword.getText().toString());
        changePassword.setConfirmPassword(confirmPassword.getText().toString());

        service.getService().ChangePassword(changePassword, new Callback<PasswordResponce>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void success(PasswordResponce passwordResponce, Response response) {

                if (passwordResponce.getMessage()!=null){

                    if (!passwordResponce.getMessage().isEmpty()){

                        if (passwordResponce.getMessage().equalsIgnoreCase("Change Password Successfully! ")){

                            startActivity(new Intent(getContext(), MainActivity.class));
//                            Intent intent=new Intent(getActivity(), LoginActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
                            Toast.makeText(getActivity(),"Password Changed Successfully",Toast.LENGTH_LONG).show();
                            preferences.edit().remove("userId").commit();
                            preferences.edit().remove("userProfileImage").commit();
//                            session.logoutUser();
                            getActivity().finish();
//                            getActivity().finishAffinity();

                        }else {
                            Utils.showAlertDialog(getActivity(),passwordResponce.getMessage(),false);

                        }

                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private boolean Validate() {

        if (!OldPassword()){
           return false;
        }
        else if (!NewPassword()){
            return false;
        }else if (!ConfirmPassword()){
            return false;
        }else {
            return true;
        }

    }

    private boolean OldPassword() {
        if (oldPassword.getText().toString().isEmpty()){
            oldPassword.setError("field should not be empty");
            oldPassword.requestFocus();
            return false;
        }else {
            return true;
        }
    }

    private boolean ConfirmPassword() {

        if (!confirmPassword.getText().toString().equals(newPassword.getText().toString())){
            confirmPassword.setError("The new password and confirmation password does not match");
            confirmPassword.requestFocus();
            return false;
        }else {
            return true;
        }

    }

    private boolean NewPassword() {

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if (newPassword.getText().toString().trim().isEmpty()){
            newPassword.setError("password should not be empty");
            newPassword.requestFocus();
            return false;
        }
        else if (!UpperCasePatten.matcher(newPassword.getText().toString().trim()).find()) {
            newPassword.setError("Password must have at least one uppercase character !!");
            newPassword.requestFocus();
            return false;
        }
        else if (!lowerCasePatten.matcher(newPassword.getText().toString().trim()).find()) {
            newPassword.setError("Password must have at least one lowercase character !!");
            newPassword.requestFocus();
            return false;
        }
        else if (!specailCharPatten.matcher(newPassword.getText().toString().trim()).find()) {
            newPassword.setError("Password must have at least one specail character !!");
            newPassword.requestFocus();
            return false;
        }
        else if (!digitCasePatten.matcher(newPassword.getText().toString().trim()).find()) {
            newPassword.setError("Password must have at least one digit character !!");
            newPassword.requestFocus();
            return false;
        }
        else if (newPassword.getText().toString().trim().length()<6){
            newPassword.setError("password at least have 6 digits");
            newPassword.requestFocus();
            return false;
        }

        else {
            return true;
        }
    }

}
