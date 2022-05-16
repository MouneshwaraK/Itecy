package com.cstech.itecy.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
/*import android.support.v4.app.Fragment;*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cstech.itecy.Adapters.MyJobsAdapter;
import com.cstech.itecy.ConnectionDetector;
import com.cstech.itecy.ModelClasess.AppliedJobs;
import com.cstech.itecy.ModelClasess.MyJobsAppliedJobs;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.utils.Utils;
import com.reginald.swiperefresh.CustomSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyJobs extends Fragment {


    RestService service;
    SharedPreferences preferences;
    String JobseekerId="";
    ListView myJobsListView;

    MyJobsAdapter adapter;

    List<MyJobsAppliedJobs> myJobsList;
    ProgressDialog dialog;

    public MyJobs() {
        // Required empty public constructor
    }
    CustomSwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View view= inflater.inflate(R.layout.fragment_my_jobs, container, false);


        preferences=getActivity().getSharedPreferences("ITecy", Context.MODE_PRIVATE);
        JobseekerId=preferences.getString("userId","");
        service=new RestService();
        myJobsListView= view.findViewById(R.id.myJobsList);
        myJobsList=new ArrayList<>();
        dialog=new ProgressDialog(getActivity());
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        dialog.show();
        swipeRefreshLayout = view.findViewById(R.id.swipelayout);
        callApi();
        swipeRefreshLayout.setRefreshMode(CustomSwipeRefreshLayout.REFRESH_MODE_SWIPE);

        swipeRefreshLayout.setOnRefreshListener(new CustomSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do something here when it starts to refresh
                // e.g. to request data from server
                callApi();
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
    private void callApi(){
        ConnectionDetector checkNetWork=new ConnectionDetector(getActivity());
        if (checkNetWork.isConnectingToInternet()){
            service.getService().MyAppliedJobs(JobseekerId, new Callback<AppliedJobs>() {
                @Override
                public void success(AppliedJobs appliedJobs, Response response) {

                    myJobsList=appliedJobs.getModel();
//                    String date = appliedJobs.getModel().get(0).getAppliedOn();
                    if (getActivity()!=null){

                        if (myJobsList.size()>0){
                            adapter=new MyJobsAdapter(getActivity(),myJobsList);
                            myJobsListView.setAdapter(adapter);
                            dialog.dismiss();
                        }else {
                            if (myJobsList.size()==0){
                                Utils.showAlertDialog(getActivity(),"No Data Available",false);
                                dialog.dismiss();
                            }else {
                                Utils.showAlertDialog(getActivity(),"No Data Available",false);
                                dialog.dismiss();
                            }

                        }

                    }


                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Failed",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            });

        }else {
            Utils.showAlertDialog(getActivity(), "check your internet connection!",false);
        }
    }
}
