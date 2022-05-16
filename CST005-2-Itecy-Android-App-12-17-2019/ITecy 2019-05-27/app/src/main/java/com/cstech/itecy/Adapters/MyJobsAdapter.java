package com.cstech.itecy.Adapters;

import android.app.Activity;
import android.app.Dialog;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cstech.itecy.Activities.CompanyInfo;
import com.cstech.itecy.ModelClasess.MyJobsAppliedJobs;
import com.cstech.itecy.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyJobsAdapter extends BaseAdapter {

    Activity context;
    List<MyJobsAppliedJobs> myJobsList;
    LayoutInflater inflater;
    public MyJobsAdapter(Activity context, List<MyJobsAppliedJobs> myJobsList) {

        this.context=context;
        this.myJobsList=myJobsList;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return myJobsList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(R.layout.myjobschilddata,null);

        TextView sno= view.findViewById(R.id.sno);
        TextView jobTitle= view.findViewById(R.id.jobTitle);
        TextView appliedOn= view.findViewById(R.id.appliedDate);
        TextView viewDetails= view.findViewById(R.id.viewDetails);

        RelativeLayout hedderLayout= view.findViewById(R.id.headerLayout);


        if (position==0){
            hedderLayout.setVisibility(View.VISIBLE);
        }else {
            hedderLayout.setVisibility(View.GONE);
        }

        sno.setText(position+1+"");
        jobTitle.setText(myJobsList.get(position).getJobTitle());

        String dateFormate = myJobsList.get(position).getJobseekerDetails().get(0).getAppliedOn();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date=dateFormat.parse(dateFormate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat1=new SimpleDateFormat("MMM-dd-yyyy");
        String AppliedDat=dateFormat1.format(date);
        appliedOn.setText(AppliedDat);


       /* String dateInputString = myJobsList.get(position).getJobseekerDetails().get(0).getAppliedOn();
        if (dateInputString != null) {
            dateInputString = dateInputString.replace("T00:00:00.000", "");
        }
        if (dateInputString != null){
            appliedOn.setText(dateInputString);
        }*/

        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.myjobinfo);
        dialog.setCancelable(false);

        final TextView companyName= dialog.findViewById(R.id.companyName);
        final TextView experience= dialog.findViewById(R.id.experience);
        final TextView location= dialog.findViewById(R.id.location);
        final TextView jobType= dialog.findViewById(R.id.jobType);
        final TextView skills= dialog.findViewById(R.id.skills);
        final TextView closeDialog= dialog.findViewById(R.id.closeDialog);
        final CircleImageView imageView= dialog.findViewById(R.id.companyLogo);


        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        if (myJobsList.get(position).getCompanyLogo()!=null){

            Picasso.with(context).load(myJobsList.get(position).getCompanyLogo()).into(imageView);
        }
        String ComapnyName=myJobsList.get(position).getCompanyName();
        if (ComapnyName!=null){
            companyName.setText(ComapnyName);
        }
        String Experience=myJobsList.get(position).getExperience();
        if (Experience!=null){
            experience.setText(Experience+"yrs");
        }

        String Location=myJobsList.get(position).getLocation();
        if (Location!=null){
            location.setText(Location);
        }

        if (!myJobsList.get(position).getJobType().equalsIgnoreCase("Job Type")){
            jobType.setText(myJobsList.get(position).getJobType());
        }

        String Skills=myJobsList.get(position).getSkills();
        if (Skills!=null){
            skills.setText(Skills);
        }


        final String JobId= String.valueOf(myJobsList.get(position).getJobId());

        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                    dialog.show();
                Intent intent=new Intent(context, CompanyInfo.class);
                intent.putExtra("JobId",JobId);
                context.startActivity(intent);

            }
        });
        return view;
    }
}
