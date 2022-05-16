package com.cstech.itecy.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cstech.itecy.ConnectionDetector;
import com.cstech.itecy.ModelClasess.JobDetails;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.utils.Utils;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CompanyInfo extends AppCompatActivity {
    int JobId;
    String CompanyId;
    JobDetails jobDetail;
    RestService service;
    ImageView companyLogo;
    String companyUrl = "";
    ProgressDialog dialog;
    String MobileNum;
    TextView companyName, jobTitle, location, description, skills, industry, function, experience, contactNumber, url, emailAddress, salary, backToPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);
        service = new RestService();
        jobDetail = new JobDetails();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        dialog.show();
        companyName = findViewById(R.id.comapnyName);
        jobTitle = findViewById(R.id.jobTitle);
        location = findViewById(R.id.jobLocation);
        description = findViewById(R.id.description);
        skills = findViewById(R.id.skills);
        industry = findViewById(R.id.industry);
        function = findViewById(R.id.function);
        experience = findViewById(R.id.experience);
        contactNumber = findViewById(R.id.contactNumber);
        url = findViewById(R.id.website);
        backToPage = findViewById(R.id.backToPage);
        emailAddress = findViewById(R.id.emailAddress);
        salary = findViewById(R.id.salary);
        companyLogo = findViewById(R.id.companyLogo);
        description.setVisibility(View.GONE);
        url.setTextColor(getResources().getColor(R.color.blue));
        backToPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(CompanyInfo.this, MainActivity.class));
             /*   Intent i = new Intent(CompanyInfo.this,MainActivity.class);
                i.putExtra("job","16");
                startActivity(i);
                finish();*/
                onBackPressed();
            }
        });
        CompanyId = getIntent().getStringExtra("JobId");
        ConnectionDetector checkNetWork = new ConnectionDetector(this);
        if (checkNetWork.isConnectingToInternet()) {
            callapi();
        } else {
            Utils.showAlertDialog(this, "check your internet connection!", false);
        }
        nonOfTheAddress();
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!companyUrl.isEmpty()) {
                    if (!companyUrl.contains("http://")) {
                        companyUrl = "http://" + companyUrl;

                    }

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(companyUrl));
                    startActivity(intent);

                }
            }
        });

        contactNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MobileNum != null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + MobileNum));
                    startActivity(intent);
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void callapi() {
        service.getService().getJobDetails(CompanyId, new Callback<JobDetails>() {
            @Override
            public void success(JobDetails jobDetails, Response response) {
                dialog.dismiss();
                jobDetail = jobDetails;
                companyName.setText(jobDetails.getModel().getCompanyName());
                jobTitle.setText(jobDetails.getModel().getJobTitle());
                location.setText(jobDetails.getModel().getLocation());
                if (jobDetails.getModel().getDescription() != null) {
                    String mHtmlString = jobDetails.getModel().getDescription();
                    mHtmlString = Html.fromHtml(Html.fromHtml(mHtmlString).toString()) + "";

                    mHtmlString = mHtmlString.replace("Duration:", "\n" + "Duration:").trim();
                    mHtmlString = mHtmlString.replace("Work Authorization:", "\n" + "Work Authorization:").trim();
                    mHtmlString = mHtmlString.replace("Job Title:", "\n" + "Title:").trim();
                    mHtmlString = mHtmlString.replace("Title:", "\n" + "Title:").trim();
                    mHtmlString = mHtmlString.replace("Contract:", "\n" + "Contract:").trim();
                    mHtmlString = mHtmlString.replace("Skills Set:", "\n" + "Skills Set:").trim();
                    mHtmlString = mHtmlString.replace("Responsibilities :", "\n" + "Responsibilities:").trim();
                    mHtmlString = mHtmlString.replace("Location:", "\n" + "Location:").trim();
                    mHtmlString = mHtmlString.replace("Job Summary:", "\n" + "Job Summary:").trim();
                    mHtmlString = mHtmlString.replace("Qualifications:", "\n" + "Qualifications:").trim();
                    mHtmlString = mHtmlString.replace("Job description:", "\n" + "Job description:").trim();
                    mHtmlString = mHtmlString.replace("Job Description:", "\n" + "Job description:").trim();
                    mHtmlString = mHtmlString.replace("Good to have:", "\n" + "Good to have:").trim();
                    mHtmlString = mHtmlString.replace("Key JOB DUTIES:", "\n" + "JOB Duties:").trim();
                    mHtmlString = mHtmlString.replace(".", "." + "\n").trim();
                    if (mHtmlString.contains("•")) {
                        mHtmlString = mHtmlString.replace("•", "\n" + "•").trim();
                    }
                    mHtmlString = mHtmlString.replace("\r\n", "");

                    description.setVisibility(View.VISIBLE);
                    description.setText(mHtmlString.trim());

                }

                if ("System.Collections.Generic.List`1[System.String]".equalsIgnoreCase(jobDetails.getModel().getSkills())) {
                    skills.setVisibility(View.GONE);
                } else {
                    skills.setText(jobDetails.getModel().getSkills());

                    String s = jobDetails.getModel().getSkills();
                }

                MobileNum = jobDetails.getModel().getContactNumber();
                industry.setText(jobDetails.getModel().getIndustry());

                function.setText(jobDetails.getModel().getFunction());

                experience.setText(jobDetails.getModel().getExperience());

                contactNumber.setText(MobileNum);

                url.setText(jobDetails.getModel().getUrl());

                emailAddress.setText(jobDetails.getModel().getEmailAddress());

                salary.setText(jobDetails.getModel().getSalary());

                String CompanyLogo = jobDetails.getModel().getCompanyLogo();
                Picasso.with(CompanyInfo.this).load(CompanyLogo).error(R.drawable.itecylogoo).placeholder(R.drawable.itecylogoo).into(companyLogo);


                companyUrl = jobDetails.getModel().getUrl();
                if (!url.getText().toString().isEmpty()) {

                    url.setPaintFlags(url.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                }
                nonOfTheAddress();
            }

            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();

            }
        });

    }

    private void nonOfTheAddress() {
        if (description.getText().toString().equals("")) {
            description.setVisibility(View.VISIBLE);
            description.setText("N/A");
        }
        String s = skills.getText().toString();
        if (skills.getText().toString().equals("") || skills.getText().toString().equals("N/A") ) {
            skills.setVisibility(View.VISIBLE);
            skills.setText("N/A");
        }
        if (industry.getText().toString().equals("") || industry.getText().toString().equals("N/A")) {
            industry.setText("N/A");
        }
        if (function.getText().toString().equals("") || function.getText().toString().equals("N/A")) {
            function.setText("N/A");
        }
        if (experience.getText().toString().equals("") || experience.getText().toString().equals("N/A")) {
            experience.setText("N/A");
        }
        if (contactNumber.getText().toString().equals("") || contactNumber.getText().toString().equals("N/A")) {
            contactNumber.setText("N/A");
        }
        if (url.getText().toString().equals("") || url.getText().toString().equals("N/A")) {
            url.setText("N/A");
        }
        if (emailAddress.getText().toString().equals("") || emailAddress.getText().toString().equals("N/A")) {
            emailAddress.setText("N/A");
        }
        if (salary.getText().toString().equals("") || salary.getText().toString().equals("N/A")) {
            salary.setText("N/A");
        }
    }

}
