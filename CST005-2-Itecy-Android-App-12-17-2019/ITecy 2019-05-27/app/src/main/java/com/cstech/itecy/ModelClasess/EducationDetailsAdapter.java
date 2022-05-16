package com.cstech.itecy.ModelClasess;

import android.app.Dialog;
/*import android.content.Intent;
import android.support.v4.app.FragmentActivity;*/
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.cstech.itecy.AdapterCallback;
import com.cstech.itecy.Adapters.SearchEducationAndSpecializationAdapter;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EducationDetailsAdapter extends BaseAdapter {

    FragmentActivity activity;
    List<EducationDetailsList> educationDetails;
    LayoutInflater inflater;
    List<EducationList> educationLists;
    List<SpecializationList> specializationList;
    RestService service;
    int  degreeId;
    int  specializationId;
    private AdapterCallback mAdapterCallback;
    EditText passingYear,univercityName1;
    TextView upDateEducationProfile,educationSearch,specializationSearch;
    TextView tvErrorDgree,tvErrorSpec;
    public EducationDetailsAdapter(FragmentActivity activity, List<EducationDetailsList> educationDetails, List<EducationList> educationLists,
                                   List<SpecializationList> specializationLists, AdapterCallback callback) {
        this.activity=activity;
        this.educationDetails=educationDetails;
        this.educationLists=educationLists;
        specializationList=new ArrayList<>();
        inflater=LayoutInflater.from(activity);
        this.mAdapterCallback = callback;
        service=new RestService();

    }

    @Override
    public int getCount() {
        return educationDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View v, ViewGroup viewGroup) {
        View view=inflater.inflate(R.layout.educationdetails,null);

        TextView degree=(TextView)view.findViewById(R.id.degree);
        final TextView specialization=(TextView)view.findViewById(R.id.specialization);
        final TextView universityName=(TextView)view.findViewById(R.id.universityName);
        TextView passedOut=(TextView)view.findViewById(R.id.passwedOut);
        ImageView editRow=(ImageView) view.findViewById(R.id.editRow);
        LinearLayout headerLayout=(LinearLayout)view.findViewById(R.id.headerLayout);



        if (position==0){
            headerLayout.setVisibility(View.VISIBLE);
        }else {
            headerLayout.setVisibility(View.GONE);
        }

        universityName.setText(educationDetails.get(position).getUniversityName());
        passedOut.setText(educationDetails.get(position).getPassingYear());
        degree.setText(educationDetails.get(position).getDegree());
        specialization.setText(educationDetails.get(position).getSpeicalization());



        editRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog Mdialog=new Dialog(activity);
                Mdialog.setContentView(R.layout.editeducationdetails);
                passingYear=Mdialog.findViewById(R.id.passingYear);
                univercityName1=Mdialog.findViewById(R.id.universityName1);
                upDateEducationProfile=Mdialog.findViewById(R.id.upDateEducationProfile);
                TextView cancelEducationProfile=Mdialog.findViewById(R.id.cancelEducationProfile);
                educationSearch=Mdialog.findViewById(R.id.educationSearch);
                specializationSearch=Mdialog.findViewById(R.id.specializationSearch);
                tvErrorDgree = Mdialog.findViewById(R.id.tv_errorDegree);
                tvErrorSpec = Mdialog.findViewById(R.id.tv_errorspea);
                final LinearLayout llEducation = Mdialog.findViewById(R.id.ll_education);
                Mdialog.show();
                passingYear.setText(educationDetails.get(position).getPassingYear());
                univercityName1.setText(educationDetails.get(position).getUniversityName());

                cancelEducationProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Mdialog.dismiss();
                    }
                });
                for (int j=0; j<educationLists.size(); j++){

                    if (educationDetails.get(position).getDegree().equalsIgnoreCase(educationLists.get(j).getDegree())) {


                        degreeId=educationLists.get(j).getEducationId();
                        getSpecilization(degreeId);


                    }
                }



                educationSearch.setText(educationDetails.get(position).getDegree());
                specializationSearch.setText(educationDetails.get(position).getSpeicalization());



                educationSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Dialog dialog=new Dialog(activity);
                        dialog.setContentView(R.layout.searchitems);
                        final EditText editSearch=(EditText) dialog.findViewById(R.id.editSearch);
                        ListView list_item=(ListView) dialog.findViewById(R.id.list_item);


                        final SearchEducationAndSpecializationAdapter searchadapter=new SearchEducationAndSpecializationAdapter(activity, educationLists,"search degree");
                        list_item.setAdapter(searchadapter);
                        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                if(!educationLists.get(i).getDegree().equalsIgnoreCase("Other")){
                                    educationSearch.setText(educationLists.get(i).getDegree());
                                    degreeId=educationLists.get(i).getEducationId();
                                    getSpecilization(degreeId);
                                    editSearch.setText("");
                                    dialog.dismiss();

                                }else {

                                    editSearch.setText("");
                                    dialog.dismiss();
                                    final Dialog addDegreeDialog=new Dialog(activity);
                                    addDegreeDialog.setContentView(R.layout.adddegree);
                                    final EditText degree=(EditText)addDegreeDialog.findViewById(R.id.etDegree);
                                    TextView addDegree=(TextView) addDegreeDialog.findViewById(R.id.addDegree);

                                    addDegreeDialog.show();
                                    addDegree.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (degree.getText().toString().isEmpty()){

                                                degree.setError("Field should not be empty");
                                                degree.requestFocus();
                                            }else {
                                                AddDegree Degree=new AddDegree();
                                                Degree.setDegree(degree.getText().toString());

                                                service.getService().AddDegree(Degree, new Callback<JobApplyResponse>() {
                                                    @Override
                                                    public void success(JobApplyResponse jobApplyResponse, Response response) {

                                                        if (jobApplyResponse.getMessage()!=null){
                                                            if (!jobApplyResponse.getMessage().isEmpty()){

                                                                Mdialog.dismiss();
                                                                mAdapterCallback.onMethodCallback();
                                                                addDegreeDialog.dismiss();
                                                                Utils.showAlertDialog(activity, jobApplyResponse.getMessage(),false);
                                                            }
                                                        }else {
                                                            Utils.showAlertDialog(activity, "Already Added, please try again with different degree",false);

                                                        }

                                                    }

                                                    @Override
                                                    public void failure(RetrofitError error) {

                                                    }
                                                });
                                            }

                                        }
                                    });

                                }


                            }


                        });


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
                                    searchadapter.filterDegree(editable.toString());
                                }
                            }
                        });

                        dialog.show();
                    }
                });

                specializationSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Dialog dialog=new Dialog(activity);
                        dialog.setContentView(R.layout.searchitems);
                        final EditText editSearch=(EditText) dialog.findViewById(R.id.editSearch);
                        ListView list_item=(ListView) dialog.findViewById(R.id.list_item);
                        dialog.show();
                        if (specializationList.size()==0){

                            dialog.dismiss();
                            final Dialog specializationLayout=new Dialog(activity);
                            specializationLayout.setContentView(R.layout.adddegree);
                            final EditText specialization=(EditText)specializationLayout.findViewById(R.id.etDegree);
                            TextView addSpecialization=(TextView) specializationLayout.findViewById(R.id.addDegree);

                            addSpecialization.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AddSpecialization spdata=new AddSpecialization();
                                    spdata.setSpecialization(specialization.getText().toString());
                                    spdata.setEducationId(degreeId);

                                    service.getService().AddSpecialization(spdata, new Callback<JobApplyResponse>() {
                                        @Override
                                        public void success(JobApplyResponse jobApplyResponse, Response response) {

                                            if (jobApplyResponse.getMessage()!=null){
                                                if (!jobApplyResponse.getMessage().isEmpty()){


                                                    getSpecilization(degreeId);
                                                    specializationLayout.dismiss();
                                                    Utils.showAlertDialog(activity, jobApplyResponse.getMessage(),false);
                                                }
                                            }else {
                                                Utils.showAlertDialog(activity, "Already Added, please try again with different degree",false);

                                            }

                                        }

                                        @Override
                                        public void failure(RetrofitError error) {

                                        }
                                    });
                                }
                            });


                            specializationLayout.show();

                        }

                        final SearchEducationAndSpecializationAdapter searchadapter=new SearchEducationAndSpecializationAdapter(activity, specializationList,"search specialization");
                        list_item.setAdapter(searchadapter);

                        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                specializationSearch.setText(specializationList.get(i).getEducationTypeName());
                                specializationId=specializationList.get(i).getEducationTypeId();
                                editSearch.setText("");
                                dialog.dismiss();

                            }
                        });
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
                                    searchadapter.filterSpecialization(editable.toString());
                                }
                            }
                        });


                    }
                });



                upDateEducationProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (validateUpdated()){
                            llEducation.setVisibility(View.GONE);
                            UpdateEducationById educationById=new UpdateEducationById();
                            educationById.setJobseekerEduId(educationDetails.get(position).getJobseekerEduId());
                            educationById.setJobseekerId(educationDetails.get(position).getJobseekerId());
                            educationById.setEducationId(degreeId);
                            educationById.setEducationTypeId(specializationId);
                            educationById.setPassingYear(passingYear.getText().toString());
                            educationById.setUniversityName(univercityName1.getText().toString());
                            service.getService().UpdateEducationById(educationById, new Callback<PasswordResponce>() {
                                @Override
                                public void success(PasswordResponce passwordResponce, Response response) {

                                    if (passwordResponce.getMessage()!=null){
                                        Utils.showAlertDialog(activity, passwordResponce.getMessage(),false);
                                        mAdapterCallback.onMethodCallback();
                                        Mdialog.dismiss();

                                    }else {
                                        Utils.showAlertDialog(activity, passwordResponce.getErrorMessage(),false);
                                    }

                                }
                                @Override
                                public void failure(RetrofitError error) {
                                    Utils.showAlertDialog(activity, error.getMessage(),false);

                                }
                            });

                        }

                    }
                });
            }

            private boolean validateUpdated(){
                if (!ValidataDegree()) {
                    return false;
                } if (!ValidataSpec()) {
                    return false;
                } if (!ValidataYear()) {
                    return false;
                } if (!ValidataUniversity()) {
                    return false;
                }else {
                    return true;
                }
            }
            private boolean ValidataDegree() {
                if (educationSearch.getText().toString().equals("Select degree")) {
                    tvErrorDgree.setVisibility(View.VISIBLE);
                    tvErrorDgree.setText("Degree Should Not Be Empty");
                    tvErrorDgree.requestFocus();
                    return false;
                } else {
                    tvErrorDgree.setVisibility(View.GONE);
                    return true;
                }
            }
            private boolean ValidataSpec() {
                if (specializationSearch.getText().toString().equals("Select specialization")) {
                    tvErrorSpec.setVisibility(View.VISIBLE);
                    tvErrorSpec.setText("Degree Should Not Be Empty");
                    tvErrorSpec.requestFocus();
                    return false;
                } else {
                    tvErrorSpec.setVisibility(View.GONE);
                    return true;
                }
            }
            private boolean ValidataYear() {
                Integer maxYear=2023;
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                Integer minYear=1900;
                Integer str= Integer.valueOf(passingYear.getText().toString());
                if (passingYear.getText().toString().equals("")) {
                    passingYear.setError("Passing Year Should Not Be Empty");
                    passingYear.requestFocus();
                    return false;
                } if (passingYear.getText().toString().length()<4) {
                    passingYear.setError("Passing Year Must Be 4 Letters");
                    passingYear.requestFocus();
                    return false;
                }else if (str>year) {
                    passingYear.setError("Passing Year Should be less than CURRENT YEAR");
                    passingYear.requestFocus();
                    return false;
                }else if(str<minYear){
                    passingYear.setError("Passing Year Should be greater than 1900");
                    passingYear.requestFocus();
                    return false;
                }
                else {
                    return true;
                }
            }
            private boolean ValidataUniversity() {
                if (univercityName1.getText().toString().equals("")) {
                    univercityName1.setError("University Name Should Not Be Empty");
                    univercityName1.requestFocus();
                    return false;
                } else {
                    return true;
                }
            }
            private void getSpecilization(int educationId) {

                service.getService().getSpecialization(educationId,new Callback<Specialization>() {
                    @Override
                    public void success(Specialization companies, Response response) {

                        SpecializationList select=new SpecializationList();
                        select.setEducationTypeName("select specialization");

                        specializationList=companies.getModel();

                        for (int j=0; j<specializationList.size(); j++){

                            if (educationDetails.get(position).getSpeicalization().equalsIgnoreCase(specializationList.get(j).getEducationTypeName())) {


                                specializationId=specializationList.get(j).getEducationTypeId();

                            }
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

            }

        });


        return view;
    }
}
