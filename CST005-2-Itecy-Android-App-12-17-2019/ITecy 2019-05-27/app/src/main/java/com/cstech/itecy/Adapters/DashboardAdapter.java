package com.cstech.itecy.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import com.cstech.itecy.Activities.CompanyInfo;
import com.cstech.itecy.ConnectionDetector;
import com.cstech.itecy.ModelClasess.JobApply;
import com.cstech.itecy.ModelClasess.JobApplyResponse;
import com.cstech.itecy.ModelClasess.JobModel;
import com.cstech.itecy.ModelClasess.MyJobsAppliedJobs;
import com.cstech.itecy.ModelClasess.ProfileData;
import com.cstech.itecy.ModelClasess.ProfileResponse;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.utils.Utils;
////import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.FullScreenContentCallback;
//import com.google.android.gms.ads.OnPaidEventListener;
//import com.google.android.gms.ads.ResponseInfo;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DashboardAdapter extends BaseAdapter {

    Activity context;
    List<JobModel> jobsDataList;
    List<JobModel> orginalList;
    LayoutInflater inflater;
    ProgressDialog dialog;
   // private Object Date;
   String jobseekerId;
//   InterstitialAd interstitialAd;
    private String DownloadResume="";
    List<MyJobsAppliedJobs> myJobsList1 = new ArrayList<>();
    public DashboardAdapter(Activity context, List<JobModel> jobsDataList, List<MyJobsAppliedJobs> myJobsList, String jobseekerId) {
        this.context=context;
        this.jobsDataList=jobsDataList;
        this.jobseekerId=jobseekerId;
        this.myJobsList1 = myJobsList;
        inflater=LayoutInflater.from(context);
        this.orginalList = new ArrayList<JobModel>();
        dialog=new ProgressDialog(context);
        try {
            this.orginalList.addAll(jobsDataList);

        } catch (Exception e) {

        }
    }

    @Override
    public int getCount() {
        return jobsDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(R.layout.jobs_child_data,null);
        ImageView companyLogo= view.findViewById(R.id.companyLogo);
        TextView companyName= view.findViewById(R.id.companyName);
        TextView experience= view.findViewById(R.id.experience);
        TextView jobType= view.findViewById(R.id.jobType);
        TextView jobTitle= view.findViewById(R.id.job);
        TextView location= view.findViewById(R.id.location);
        TextView postedOn= view.findViewById(R.id.postedOn);
        final TextView applyJob= view.findViewById(R.id.applyJob);
        CardView cardView = view.findViewById(R.id.card_jobDetails);
/*
        interstitialAd= new InterstitialAd(context) {
            @Nullable
            @Override
            public FullScreenContentCallback getFullScreenContentCallback() {
                return null;
            }

            @Nullable
            @Override
            public OnPaidEventListener getOnPaidEventListener() {
                return null;
            }

            @NonNull
            @Override
            public ResponseInfo getResponseInfo() {
                return null;
            }

            @NonNull
            @Override
            public String getAdUnitId() {
                return null;
            }

            @Override
            public void setFullScreenContentCallback(@Nullable FullScreenContentCallback fullScreenContentCallback) {

            }

            @Override
            public void setImmersiveMode(boolean b) {

            }

            @Override
            public void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener) {

            }

            @Override
            public void show(@NonNull Activity activity) {

            }
        };
*/
     /*   interstitialAd.setAdUnitId("ca-app-pub-7642728580115992/6812616281");
        interstitialAd.loadAd(new AdRequest.Builder().build());
*/
        String  Skill="";

        if (jobsDataList.get(position).getSkills()!=null){
            Skill="("+jobsDataList.get(position).getSkills()+")";
        }

        String JobType="";
        if (!jobsDataList.get(position).getJobType().equalsIgnoreCase("Job Type")){

            JobType="Job Type : "+jobsDataList.get(position).getJobType();

        }else {
            jobType.setVisibility(View.GONE);
        }

        ConnectionDetector checkNetWork=new ConnectionDetector(context);
        if (checkNetWork.isConnectingToInternet()){
            GetBasicProfileData();
        }else {
            Utils.showAlertDialog(context, "check your internet connection!",false);
        }




        postedOn.setText("Posted on : "+jobsDataList.get(position).getPostedOn());
        jobTitle.setText(jobsDataList.get(position).getJobTitle()+Skill);
        experience.setText("Experience : "+jobsDataList.get(position).getExperience()+"yrs");
        companyName.setText(jobsDataList.get(position).getCompanyName());
        jobType.setText(JobType);
        location.setText("Location : "+jobsDataList.get(position).getLocation());
        if (!jobseekerId.equals("") ){
            for(MyJobsAppliedJobs userlist: myJobsList1){
                //#2. Check if user id is matching
//            int ii = Integer.parseInt(jobsDataList.get(position).getJobId());
                if(userlist.getJobId() == Integer.parseInt(jobsDataList.get(position).getJobId())){
                    applyJob.setText("Applied");
                    //#3. Matches, user id exists, get the record and out from loop
//                existingUser = userlist;
                    break;
                }
            }
        }

        String url=jobsDataList.get(position).getCompanyLogo();
        final String JobId=jobsDataList.get(position).getJobId();
        Picasso.with(context)
                .load(url).error(R.drawable.itecysplash).placeholder(R.drawable.itecysplash)
                .into(companyLogo);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDetector checkNetWork=new ConnectionDetector(context);
                if (checkNetWork.isConnectingToInternet()){
                    Intent intent=new Intent(context, CompanyInfo.class);
                    intent.putExtra("JobId",JobId);
                    context.startActivity(intent);
                }
                else {
                    Utils.showAlertDialog(context, "check your internet connection!",false);
                }
            }
        });

   /*     jobTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDetector checkNetWork=new ConnectionDetector(context);
                if (checkNetWork.isConnectingToInternet()){
                    Intent intent=new Intent(context, CompanyInfo.class);
                    intent.putExtra("JobId",JobId);
                    context.startActivity(intent);
                }else {
                    Utils.showAlertDialog(context, "check your internet connection!",false);
                }

            }
        });*/

        applyJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   if (interstitialAd.isLoaded()){

                    interstitialAd.show();
                }*/
                RestService service=new RestService();
                JobApply jobApply=new JobApply();
                jobApply.setJobId(JobId);
                jobApply.setUserId(jobseekerId);
                jobApply.setPosAply(true);
                dialog.setMessage("Loading..");

                if (jobseekerId.equals("")){
                    Utils.showAlertDialogLogin(context,"Please Login And Apply the Job",false);
                }else {
                    if (DownloadResume!=null){

                        if (!DownloadResume.isEmpty()){

                            ConnectionDetector checkNetWork=new ConnectionDetector(context);
                            if (checkNetWork.isConnectingToInternet()){
                                dialog.show();
                                service.getService().JobApply(jobApply, new Callback<JobApplyResponse>() {
                                    @Override
                                    public void success(JobApplyResponse jobApplyResponse, Response response) {

                                        dialog.dismiss();
                                        if (jobApplyResponse.getMessage()!=null) {
                                            Utils.showAlertDialog(context, jobApplyResponse.getMessage(),false);
                                        }
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {

                                        Utils.showAlertDialog(context, "Job is already Applied by Jobseeker",false);
                                        dialog.dismiss();
                                    }
                                });

                            }else {
                                Utils.showAlertDialog(context, "check your internet connection!",false);
                            }


                        }
                        else {
                            Utils.showAlertDialog(context, "Please upload your resume in profile section ",false);
                            dialog.dismiss();
                        }
                    }
                    else {
                        Utils.showAlertDialog(context, "Please upload your resume in profile section ",false);
                        dialog.dismiss();
                    }
                }



                //Toast.makeText(context, "Application Testing",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void GetBasicProfileData() {


        RestService service=new RestService();
        service.getService().getProfile(jobseekerId, new Callback<ProfileResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void success(ProfileResponse profileResponse, Response response) {


                ProfileData data=profileResponse.getModel();

                if (data!=null){


                    if (!data.getResume().isEmpty()){

                        DownloadResume=data.getResume();

                    }

                }


            }

            @Override
            public void failure(RetrofitError error) {


            }
        });

    }


    public void filter(String charText) {
        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        jobsDataList.clear();
        if (charText.length() == 0) {
            jobsDataList.addAll(orginalList);
            notifyDataSetChanged();
        } else {
            for (JobModel Dsa : orginalList) {
                String Skills=Dsa.getSkills();
                String skills = "";
                if (Skills!=null){
                    skills=Skills;
                }
                if (Dsa.getJobTitle().toLowerCase(Locale.getDefault()).contains(charText)||
                        Dsa.getCompanyName().toLowerCase(Locale.getDefault()).contains(charText)||
                        Dsa.getLocation().toLowerCase(Locale.getDefault()).contains(charText)||
                        skills.toLowerCase(Locale.getDefault()).contains(charText)) {
                    jobsDataList.add(Dsa);

                }
                notifyDataSetChanged();
            }

            if (jobsDataList.size()==0){

                Utils.showAlertDialog(context, "No such job found",false);

            }
        }

    }
}
