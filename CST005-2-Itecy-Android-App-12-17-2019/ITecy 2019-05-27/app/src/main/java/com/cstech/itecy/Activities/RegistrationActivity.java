package com.cstech.itecy.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cstech.itecy.Adapters.CountryCodeAdapter;
import com.cstech.itecy.ConnectionDetector;
import com.cstech.itecy.ModelClasess.AllCitiesList;
import com.cstech.itecy.ModelClasess.CountryCode;
import com.cstech.itecy.ModelClasess.GetAllCities;
import com.cstech.itecy.ModelClasess.RegistrationData;
import com.cstech.itecy.ModelClasess.RegistrationResponse;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.interFace.Countrycode;
import com.cstech.itecy.utils.ExpandableLayout;
import com.cstech.itecy.utils.Utils;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegistrationActivity extends AppCompatActivity implements Countrycode {


    EditText firstName, lastName, contactNumber, email, password, confirmPassword, countryCode;
    TextView signUp, backToPage, citySearch,errorCCode;
    RestService service;
    ProgressDialog dialog;

    private Integer CityId = null;
    List<AllCitiesList> preferredLocationsList;

    /**************** mounesh *********************/
    ArrayList<CountryCode> countryCodes = new ArrayList<>();
    CountryCodeAdapter countryCodeAdapter;
    RecyclerView rvCountryCode;
    ExpandableLayout expandableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        service = new RestService();
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        contactNumber = findViewById(R.id.contactNumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmpassword);
        countryCode = findViewById(R.id.countryCode);
        signUp = findViewById(R.id.signUp);
        backToPage = findViewById(R.id.backToPage);
        expandableLayout = (ExpandableLayout) findViewById(R.id.expandable_layout_select_country);
        citySearch = findViewById(R.id.citySearch);
        errorCCode = findViewById(R.id.tv_cCode);
        preferredLocationsList = new ArrayList<>();
        rvCountryCode = (RecyclerView) findViewById(R.id.rv_select_country);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ConnectionDetector checkNetWork = new ConnectionDetector(RegistrationActivity.this);
                if (checkNetWork.isConnectingToInternet()) {
                    if (Validations()) {
                        Registered();
                    }
                } else {
                    Utils.showAlertDialog(RegistrationActivity.this, "check your internet connection!", false);
                }

            }
        });

        backToPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
                onBackPressed();
            }
        });

        countryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                selectCountry();
                initializeCountrycodesRecycler();
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                errorCCode.setVisibility(View.GONE);

            }
        });
        //getPreferredLocations();


/*        citySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog=new Dialog(RegistrationActivity.this);
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch=(EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item=(ListView) dialog.findViewById(R.id.list_item);


                final SearchAdapter searchadapter=new SearchAdapter(RegistrationActivity.this, preferredLocationsList);
                list_item.setAdapter(searchadapter);


                editSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        String text = charSequence.toString().toLowerCase(Locale.getDefault());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (searchadapter!=null) {
                            searchadapter.filter(editable.toString());
                        }
                    }
                });


                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        citySearch.setText(preferredLocationsList.get(i).getCountryName()+","+preferredLocationsList.get(i).getStateName()+","+preferredLocationsList.get(i).getCityName());
                        CityId=preferredLocationsList.get(i).getCityId();
                        editSearch.setText("");
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        })*/
        ;


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getPreferredLocations() {

        //dialog.show();
        service.getService().GetAllCities(new Callback<GetAllCities>() {
            @Override
            public void success(GetAllCities getAllCities, Response response) {
                dialog.dismiss();
                preferredLocationsList = getAllCities.getModel();

            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismiss();
            }
        });
    }


    private void Registered() {

        dialog.show();
        final String UserName = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();
        String FirstName = firstName.getText().toString().trim();
        String LastName = lastName.getText().toString().trim();
        String Contact = contactNumber.getText().toString().trim();
        String CountryCode = countryCode.getText().toString().trim();
        CountryCode = CountryCode.replace("+", "");
        RegistrationData data = new RegistrationData();
        data.setJobseekerId(0);
        data.setFirstName(FirstName);
        data.setLastName(LastName);
        data.setContact(Contact);
        data.setEmailId(UserName);
        data.setPassword(Password);
        data.setCountryCode(CountryCode);
        service.getService().getRegistration(data, new Callback<RegistrationResponse>() {
            @Override
            public void success(RegistrationResponse ResponseData, Response response) {

                if (ResponseData.getMessage() != null) {
                    // Utils.showAlertDialog(RegistrationActivity.this, ResponseData.getMessage(),false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
                    builder.setMessage(ResponseData.getMessage());
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            intent.putExtra("username", UserName);
                            intent.putExtra("password", Password);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            dialogInterface.dismiss();

                        }
                    });
                    AlertDialog dialogs = builder.create();
                    dialogs.show();
                    dialog.dismiss();


                } else {
                    Utils.showAlertDialog(RegistrationActivity.this, ResponseData.getErrorMessage(), false);
                    dialog.dismiss();
                }
            }

            @Override
            public void failure(RetrofitError error) {

                String Error = "Net work problem! Please try again";
                Utils.showAlertDialog(RegistrationActivity.this, Error, false);
                dialog.dismiss();


            }
        });

    }

    private boolean Validations() {

        if (!validateEmail()) {
            return false;
        } else if (!validatePassword()) {
            return false;
        } else if (!validateConfirmPassword()) {
            return false;
        } else if (!validateFirstName()) {
            return false;
        } else if (!validateLastname()) {
            return false;
        }/*else if (!validateCityId()){
            return false;
        }*/ else if (!ValidateCountryCode()) {

            return false;
        } else if (!validateMobile()) {

            return false;
        } else {
            return true;
        }

    }

    private boolean ValidateCountryCode() {
        if (countryCode.getText().toString().trim().isEmpty()) {
            errorCCode.setVisibility(View.VISIBLE);
            errorCCode.setText("Country code should not be empty");
            countryCode.requestFocus();
            return false;
        } else if (!countryCode.getText().toString().trim().contains("+")) {
            errorCCode.setVisibility(View.VISIBLE);
            errorCCode.setText("Please add + before add country code");
            countryCode.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    private boolean validateCityId() {
        if (CityId != null) {

            return true;
        } else {

            Utils.showAlertDialog(RegistrationActivity.this, "City should not be empty", false);
            return false;
        }

    }

    private boolean validateEmail() {
        if (email.getText().toString().trim().isEmpty()) {
            email.setError("Email should not be empty");
            email.requestFocus();
            return false;
        } else {
            Pattern pattern;
            Matcher matcher;
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email.getText().toString().trim());
            if (matcher.matches()) {
                Log.i("matcher value", String.valueOf(matcher.matches()));
                return true;

            } else {
                Log.i("matcher value", String.valueOf(matcher.matches()));
                email.setError("Please enter valid email");
                email.requestFocus();
                return false;
            }
        }
    }

    private boolean validateMobile() {
        if (contactNumber.getText().toString().trim().isEmpty()) {
            contactNumber.setError("Mobile Number should not be empty");
            contactNumber.requestFocus();
            return false;
        } else if (contactNumber.getText().toString().trim().length() < 10) {
            contactNumber.setError("Mobile number should not be less than 10 digits");
            contactNumber.requestFocus();
            return false;
        } else if (countryCode.getText().toString().equalsIgnoreCase("+91") && (contactNumber.getText().toString().trim().length() > 10)) {

            contactNumber.setError("Mobile number should not be greater than 10 digits");
            contactNumber.requestFocus();
            return false;
        } else if (countryCode.getText().toString().equalsIgnoreCase("+1") && (contactNumber.getText().toString().trim().length() > 10)) {

            contactNumber.setError("Mobile number should not be greater than 10 digits");
            contactNumber.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateLastname() {

        if (lastName.getText().toString().trim().isEmpty()) {
            lastName.setError("Last name should not be empty");
            lastName.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    private boolean validateFirstName() {
        if (firstName.getText().toString().trim().isEmpty()) {
            firstName.setError("First name should not be empty");
            firstName.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        if (!confirmPassword.getText().toString().trim().equals(password.getText().toString().trim())) {
            confirmPassword.setError("Password should be match");
            confirmPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {


        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if (password.getText().toString().trim().isEmpty()) {
            password.setError("password should not be empty");
            password.requestFocus();
            return false;
        } else if (!UpperCasePatten.matcher(password.getText().toString().trim()).find()) {
            password.setError("Password must have atleast one uppercase character !!");
            password.requestFocus();
            return false;
        } else if (!lowerCasePatten.matcher(password.getText().toString().trim()).find()) {
            password.setError("Password must have atleast one lowercase character !!");
            password.requestFocus();
            return false;
        } else if (!specailCharPatten.matcher(password.getText().toString().trim()).find()) {
            password.setError("Password must have atleast one specail character !!");
            password.requestFocus();
            return false;
        } else if (!digitCasePatten.matcher(password.getText().toString().trim()).find()) {
            password.setError("Password must have atleast one digit character !!");
            password.requestFocus();
            return false;
        } else if (password.getText().toString().trim().length() < 8) {
            password.setError("password at least have 8 digits");
            password.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    /****************** mounesh *********************/
    private void initializeCountrycodesRecycler() {
        countryCodes.clear();
        countryCodeAdapter = new CountryCodeAdapter(getApplicationContext(), countryCodes, "Select Country", this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvCountryCode.setLayoutManager(mLayoutManager);
        rvCountryCode.setItemAnimator(new DefaultItemAnimator());
        countryCodes.add(new CountryCode("+1"));
        countryCodes.add(new CountryCode("+91"));
        rvCountryCode.setAdapter(countryCodeAdapter);
    }

    private void selectCountry() {
        if (expandableLayout.isExpanded()) {
//            binding.ivSelectClass.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_expend));
            expandableLayout.collapse();
        } else {
//            binding.ivSelectClass.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_collapse));
            expandableLayout.expand();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(expandableLayout.getApplicationWindowToken(), 0);
        }
    }


    @Override
    public void setSelectCountryCode(String name) {
        countryCode.setText(name);
        countryCode.setTypeface(firstName.getTypeface(), Typeface.BOLD);
        expandableLayout.collapse();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
