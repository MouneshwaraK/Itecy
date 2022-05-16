package com.cstech.itecy.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
/*import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;*/
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cstech.itecy.Adapters.DashboardAdapter;
import com.cstech.itecy.Adapters.MyJobsAdapter;
import com.cstech.itecy.ConnectionDetector;
import com.cstech.itecy.ModelClasess.AppliedJobs;
import com.cstech.itecy.ModelClasess.JobModel;
import com.cstech.itecy.ModelClasess.JobsData;
import com.cstech.itecy.ModelClasess.MyJobsAppliedJobs;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.utils.Utils;
import com.reginald.swiperefresh.CustomSwipeRefreshLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    ListView jobslistview;
    List<JobModel> jobsDataList = new ArrayList<>();
    ;
    List<JobModel> jobsDataList2 = new ArrayList<>();
    ;
    JobModel model;
    RestService restService;
    ProgressDialog dialog;
    TextView closeSearch;
    TextView clearText;
    EditText searchChild;
    DashboardAdapter adapter;
    SharedPreferences preferences;
    String JobseekerId;
    ScrollView scrollView;
    List<MyJobsAppliedJobs> myJobsList;

    public Dashboard() {
        // Required empty public constructor
    }

    CustomSwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jobs, container, false);
        restService = new RestService();
        scrollView = view.findViewById(R.id.scrollView);
        swipeRefreshLayout = view.findViewById(R.id.swipelayout);
        jobslistview = (ListView) view.findViewById(R.id.jobslistview);
        searchChild = (EditText) view.findViewById(R.id.searchChild);
        closeSearch = (TextView) view.findViewById(R.id.closeSearch);
        clearText = (TextView) view.findViewById(R.id.clearText);
        searchChild.setVisibility(View.GONE);
        closeSearch.setVisibility(View.GONE);
        clearText.setVisibility(View.GONE);

        preferences = getActivity().getSharedPreferences("ITecy", Context.MODE_PRIVATE);
        JobseekerId = preferences.getString("userId", "");
        closeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchChild.setVisibility(View.GONE);
                closeSearch.setVisibility(View.GONE);
                clearText.setVisibility(View.GONE);
                searchChild.setText("");
            }
        });

        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchChild.setText("");
            }
        });


        searchChild.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String text = charSequence.toString().toLowerCase(Locale.getDefault());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (adapter != null) {
                    adapter.filter(editable.toString());
                }
            }
        });
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();
        if (JobseekerId.equals("")){
            callApi();
        }else {
            callMyjobsApi();
        }
//        callApi();
        swipeRefreshLayout.setRefreshMode(CustomSwipeRefreshLayout.REFRESH_MODE_SWIPE);

        swipeRefreshLayout.setOnRefreshListener(new CustomSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do something here when it starts to refresh
                // e.g. to request data from server
                jobsDataList.clear();
                jobsDataList2.clear();
                if (JobseekerId.equals("")){
                    callApi();
                }else {
                    callMyjobsApi();
                }
                adapter.notifyDataSetChanged();
                jobsDataList.clear();
                jobsDataList2.clear();

                swipeRefreshLayout.setKeepTopRefreshingHead(false);
                swipeRefreshLayout.enableTopProgressBar(false);
                swipeRefreshLayout.enableTopProgressBar(false);
                if (swipeRefreshLayout.isKeepTopRefreshingHead()){
                    swipeRefreshLayout.setKeepTopRefreshingHead(false);
                }
                swipeRefreshLayout.refreshComplete();
            }
        });
        return view;
    }

    private void callApi() {
        jobsDataList.clear();
        jobsDataList2.clear();
        try {
            ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
            if (checkNetWork.isConnectingToInternet()) {
                restService.getService().getJobs(new Callback<JobsData>() {
                    @Override
                    public void success(JobsData jobsData, Response response) {
                        dialog.dismiss();
                        jobsDataList = jobsData.getModel();
                        for (int i = 0; i < jobsDataList.size(); i++) {
                            String dateInputString = jobsDataList.get(i).getPostedOn();
                  /*          DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                            Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
                            try {
                                date = dateFormat.parse(dateInputString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat formatter = new SimpleDateFormat("MMMM-dd-yyyy"); //If you need time just put specific format for time like 'HH:mm:ss'

                            String dateStr = formatter.format(date);*/
                            if (dateInputString != null) {
                                dateInputString = dateInputString.replace("T00:00:00", "");
                            }
                            model = new JobModel();
                            model.setCompanyLogo(jobsDataList.get(i).getCompanyLogo());
                            model.setCompanyName(jobsDataList.get(i).getCompanyName());
                            model.setExperience(jobsDataList.get(i).getExperience());
                            model.setJobId(jobsDataList.get(i).getJobId());
                            model.setJobTitle(jobsDataList.get(i).getJobTitle());
                            model.setJobType(jobsDataList.get(i).getJobType());
                            model.setLocation(jobsDataList.get(i).getLocation());

                            if (dateInputString != null) {
                                model.setPostedOn(dateInputString);
                            }

                            model.setSkills(jobsDataList.get(i).getSkills());
                            jobsDataList2.add(model);


                        }
                        Collections.sort(jobsDataList2, new CustomComparator());
                        adapter = new DashboardAdapter(getActivity(), jobsDataList2,myJobsList, JobseekerId);
                        jobslistview.setAdapter(adapter);

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        dialog.dismiss();
                    }
                });

            } else {
                Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
            }
        } catch (Exception e) {

            Utils.showAlertDialog(getActivity(), e.getMessage(), false);
        }
    }

    private void callMyjobsApi() {
        ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
        if (checkNetWork.isConnectingToInternet()) {
            restService.getService().MyAppliedJobs(JobseekerId, new Callback<AppliedJobs>() {
                @Override
                public void success(AppliedJobs appliedJobs, Response response) {
                    myJobsList = appliedJobs.getModel();
                    callApi();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            });

        } else {
            Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.dashboardmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.jobSearch) {
            searchChild.setVisibility(View.VISIBLE);
            closeSearch.setVisibility(View.VISIBLE);
            clearText.setVisibility(View.GONE);
        }
        return super.onOptionsItemSelected(item);
    }

    private class CustomComparator implements Comparator<JobModel> {
        @Override
        public int compare(JobModel lhs, JobModel rhs) {
            return (int) (Utils.convertLiveDateToMillis1(rhs.getPostedOn()).compareTo(Utils.convertLiveDateToMillis1(lhs.getPostedOn())));
        }
    }
}
