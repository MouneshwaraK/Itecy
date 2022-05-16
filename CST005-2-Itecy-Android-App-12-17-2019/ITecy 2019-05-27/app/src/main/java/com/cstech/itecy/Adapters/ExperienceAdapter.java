package com.cstech.itecy.Adapters;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.cstech.itecy.AdapterCallback;
import com.cstech.itecy.ModelClasess.CompaniesList;
import com.cstech.itecy.ModelClasess.Designations;
import com.cstech.itecy.ModelClasess.DesignationsList;
import com.cstech.itecy.ModelClasess.ExperienceList;
import com.cstech.itecy.ModelClasess.FunctionalAreasList;
import com.cstech.itecy.ModelClasess.IndustriesList;
import com.cstech.itecy.ModelClasess.PasswordResponce;
import com.cstech.itecy.ModelClasess.UpdateExperienceById;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ExperienceAdapter extends BaseAdapter {
    Context context;
    Activity activity;
    List<ExperienceList> experienceLists;
    LayoutInflater inflater;
    private AdapterCallback mAdapterCallback;
    List<CompaniesList> companiesList;
    List<IndustriesList> industriesLists;
    List<FunctionalAreasList> functionalAreAlist;
    List<String> expectedsalaryArray;
    List<String> noticePeroidArray;
    Calendar calendar;
    String ExpectedSalaryInExp = "";
    String NoticePeroidInExp = "",noticeCheck="";
    Integer CompanyId = 0;
    Integer IndustryId = 0;
    Integer FunctionalId = 0;
    Integer DesignationId = 0;
    List<DesignationsList> designationsLists;
    TextView companySearch, industrySearch, functionalAreaSearch, designationSearch;
    TextView tvErrorCompany, tvErrorIndustry,tvErrorFunctionalArea,tvErrorDesignation,tvErrorJoinDate,
    tvErrorExpectedSalary,tvErrorNoticeperid;
    EditText etMonthsalary;
    TextView joiningDate1,tvjoiningDate;
    boolean checkbox;
    public ExperienceAdapter(Activity activity, List<ExperienceList> experienceLists, AdapterCallback mAdapterCallback,
                             List<CompaniesList> companiesList, List<IndustriesList> industriesLists,
                             List<FunctionalAreasList> functionalAreAlist,
                             List<String> expectedsalaryArray, List<String> noticePeroidArray) {

        this.activity = activity;
        this.experienceLists = experienceLists;
        this.mAdapterCallback = mAdapterCallback;
        this.companiesList = companiesList;
        this.industriesLists = industriesLists;
        this.functionalAreAlist = functionalAreAlist;
        this.expectedsalaryArray = expectedsalaryArray;
        this.noticePeroidArray = noticePeroidArray;
        calendar = Calendar.getInstance();
        inflater = LayoutInflater.from(activity);
        designationsLists = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return experienceLists.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View view1 = inflater.inflate(R.layout.experiencechildview, null);
        TextView companyName = (TextView) view1.findViewById(R.id.comapnyName);
        TextView industry = (TextView) view1.findViewById(R.id.industry);
        final TextView functionalArea = (TextView) view1.findViewById(R.id.functionalArea);
        final TextView designation = (TextView) view1.findViewById(R.id.designation);
        TextView monthlySalary = (TextView) view1.findViewById(R.id.monthlySalary);
        final TextView expectedSalary = (TextView) view1.findViewById(R.id.expectedSalary);
        final TextView noticePeriod = (TextView) view1.findViewById(R.id.noticePeriod);
        tvjoiningDate = (TextView) view1.findViewById(R.id.joiningDate);
        ImageView editExperienceLayout = (ImageView) view1.findViewById(R.id.editExperienceLayout);
        CheckBox isChecked = (CheckBox) view1.findViewById(R.id.isCurrentCompany);
        LinearLayout expectedSalaryLayout = (LinearLayout) view1.findViewById(R.id.expectedSalaryLayout);
        LinearLayout noticePeriodLayout = (LinearLayout) view1.findViewById(R.id.noticePeriodLayout);

        final RestService service = new RestService();
        companyName.setText(experienceLists.get(i).getCompanyName());
        industry.setText(experienceLists.get(i).getIndustry());
        functionalArea.setText(experienceLists.get(i).getFunctionalArea());
        designation.setText(experienceLists.get(i).getDesignation());
//        if(experienceLists.get(i).getMonthlySalary()!=null){
//        }else {
//            monthlySalary.setText("N/A");
//        }
        monthlySalary.setText(experienceLists.get(i).getMonthlySalary() + "");


        tvjoiningDate.setText(experienceLists.get(i).getJoiningDate());
//
        isChecked.setClickable(false);


        if (experienceLists.get(i).getExpectedSalary() != null) {

            int ExpectedSalaryInExp=0;

            ExpectedSalaryInExp = experienceLists.get(i).getExpectedSalary();

            if (ExpectedSalaryInExp == 1) {
                expectedSalary.setText("As per company standards");
            } else if (ExpectedSalaryInExp == 2) {
                expectedSalary.setText("10% hike");
            } else if (ExpectedSalaryInExp == 3) {
                expectedSalary.setText("20% hike");
            } else if (ExpectedSalaryInExp == 4) {
                expectedSalary.setText("30% hike");
            } else if (ExpectedSalaryInExp == 5) {
                expectedSalary.setText("40% hike");
            } else if (ExpectedSalaryInExp == 6) {
                expectedSalary.setText("50% hike");
            }
        }
        if (expectedSalary.getText().toString().equals("")){
            expectedSalary.setText("N/A");
        }
        if (experienceLists.get(i).getNoticePeriod() != null) {

            int NoticePeroidInExp = experienceLists.get(i).getNoticePeriod();
            if (NoticePeroidInExp == 1) {

                noticePeriod.setText("Immediate");
            } else if (NoticePeroidInExp == 2) {

                noticePeriod.setText("7 days");
            } else if (NoticePeroidInExp == 3) {

                noticePeriod.setText("15 days");
            } else if (NoticePeroidInExp == 4) {

                noticePeriod.setText("30 days");
            } else if (NoticePeroidInExp == 5) {

                noticePeriod.setText("60 days");
            }

        }
        if (noticePeriod.getText().toString().equals("")){
            noticePeriod.setText("N/A");
        }
        if (experienceLists.get(i).isCurrentCompany() == true) {

            isChecked.setVisibility(View.VISIBLE);
            expectedSalary.setVisibility(View.VISIBLE);
            noticePeriod.setVisibility(View.VISIBLE);
            expectedSalaryLayout.setVisibility(View.VISIBLE);
            noticePeriodLayout.setVisibility(View.VISIBLE);
            isChecked.setChecked(true);
            checkbox  = true;
        } else {
            expectedSalaryLayout.setVisibility(View.GONE);
            noticePeriodLayout.setVisibility(View.GONE);
            isChecked.setVisibility(View.GONE);
            expectedSalary.setVisibility(View.GONE);
            noticePeriod.setVisibility(View.GONE);
            isChecked.setChecked(false);
            checkbox = false;
        }

        editExperienceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.editexperiencedetails);

                companySearch = dialog.findViewById(R.id.companySearch);
                industrySearch = dialog.findViewById(R.id.industrySearch);
                functionalAreaSearch = dialog.findViewById(R.id.functionalAreaSearch);
                designationSearch = dialog.findViewById(R.id.designationSearch);
                tvErrorCompany = dialog.findViewById(R.id.tv_errorCompanyName);
                tvErrorIndustry = dialog.findViewById(R.id.tv_errorIndustry);
                tvErrorFunctionalArea = dialog.findViewById(R.id.tv_errorFunctionalArea);
                tvErrorDesignation = dialog.findViewById(R.id.tv_errorDesignation);
                tvErrorJoinDate = dialog.findViewById(R.id.tv_errorJoinDate);
                etMonthsalary = (EditText) dialog.findViewById(R.id.monthlySalary);
                joiningDate1 = dialog.findViewById(R.id.joiningDate);
                tvErrorExpectedSalary = dialog.findViewById(R.id.tv_errorExpectedSalary);
                tvErrorNoticeperid = dialog.findViewById(R.id.tv_errorNoticePeriod);
                final TextView upDateExperienceProfile = dialog.findViewById(R.id.upDateExperienceProfile);
                final TextView cancelExperienceProfile = dialog.findViewById(R.id.cancelExperienceProfile);
                final CheckBox iscurrentCompany = (CheckBox) dialog.findViewById(R.id.isCurrentCompany);
                final Spinner getExpectedsalaryInExp = dialog.findViewById(R.id.getExpectedSalaryInExp);
                final Spinner getNoticePeriodInExp = dialog.findViewById(R.id.getNoticePeriodInExp);
                final LinearLayout expectedSalaryLayoutInExp = (LinearLayout) dialog.findViewById(R.id.expectedSalaryLayoutInExp);
                final LinearLayout noticePeriodLayoutInExp = (LinearLayout) dialog.findViewById(R.id.noticePeriodLayoutInExp);
                final  LinearLayout ll_UpadteExperince = (LinearLayout)dialog.findViewById(R.id.ll_editeExperince);
                cancelExperienceProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (tvErrorExpectedSalary.getText().toString().equals("N/A")){
                    tvErrorExpectedSalary.setText("Select Expected salary");
                }
                if (tvErrorNoticeperid.getText().toString().equals("N/A")){
                    tvErrorNoticeperid.setText("Select Notice Peried");
                }
                if (experienceLists.get(i).isCurrentCompany() == true) {


                    iscurrentCompany.setVisibility(View.VISIBLE);
                    getExpectedsalaryInExp.setVisibility(View.VISIBLE);
                    getNoticePeriodInExp.setVisibility(View.VISIBLE);
                    noticePeriodLayoutInExp.setVisibility(View.VISIBLE);
                    expectedSalaryLayoutInExp.setVisibility(View.VISIBLE);
                    iscurrentCompany.setChecked(true);

                } else {
//                    iscurrentCompany.setVisibility(View.GONE);
                    getExpectedsalaryInExp.setVisibility(View.GONE);
                    getNoticePeriodInExp.setVisibility(View.GONE);
                    noticePeriodLayoutInExp.setVisibility(View.GONE);
                    expectedSalaryLayoutInExp.setVisibility(View.GONE);
                    iscurrentCompany.setChecked(false);

                }
                iscurrentCompany.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                        if (isChecked) {
//                            checkValidate = true;
                            getExpectedsalaryInExp.setVisibility(View.VISIBLE);
                            getNoticePeriodInExp.setVisibility(View.VISIBLE);
                            noticePeriodLayoutInExp.setVisibility(View.VISIBLE);
                            expectedSalaryLayoutInExp.setVisibility(View.VISIBLE);
                        } else {
//                            checkValidate = false;
                            getExpectedsalaryInExp.setVisibility(View.GONE);
                            getNoticePeriodInExp.setVisibility(View.GONE);
                            noticePeriodLayoutInExp.setVisibility(View.GONE);
                            expectedSalaryLayoutInExp.setVisibility(View.GONE);
                        }
                    }
                });

                for (int j = 0; j < companiesList.size(); j++) {

                    if (experienceLists.get(i).getCompanyName() != null) {
                        if (experienceLists.get(i).getCompanyName().equalsIgnoreCase(companiesList.get(j).getCompanyName())) {
                            CompanyId = companiesList.get(j).getCompanyId();
                            companySearch.setText(experienceLists.get(i).getCompanyName());

                        }
                    }

                }


                for (int j = 0; j < industriesLists.size(); j++) {

                    if (experienceLists.get(i).getIndustry() != null) {
                        if (experienceLists.get(i).getIndustry().equalsIgnoreCase(industriesLists.get(j).getIndustryName())) {
                            IndustryId = industriesLists.get(j).getIndustryId();
                            industrySearch.setText(experienceLists.get(i).getIndustry());
                        }
                    }

                }


                for (int j = 0; j < functionalAreAlist.size(); j++) {

                    if (experienceLists.get(i).getFunctionalArea() != null) {
                        if (experienceLists.get(i).getFunctionalArea().equalsIgnoreCase(functionalAreAlist.get(j).getFunctionalAreaName())) {

                            FunctionalId = functionalAreAlist.get(j).getFunctionalAreaId();
                            functionalAreaSearch.setText(experienceLists.get(i).getFunctionalArea());
                        }
                    }

                }

                getDesignations(FunctionalId);


                joiningDate1.setText(experienceLists.get(i).getJoiningDate());
                joiningDate1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DatePickerDial();

                    }

                    private void DatePickerDial() {
                        final Calendar cldr = Calendar.getInstance();
//                cldr.add(Calendar.YEAR, -18);
                        final int day = cldr.get(Calendar.DAY_OF_MONTH);
                        final int month = cldr.get(Calendar.MONTH);
                        final int year = cldr.get(Calendar.YEAR);
                        // date picker dialog

                        DatePickerDialog datepicker = new DatePickerDialog(dialog.getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view,int year,int month,int day) {
                                        cldr.set(year, month, day);
                                        String myFormat = "MM-dd-yyyy";//"MM/dd/yy"; //In which you need put here
                                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                                        joiningDate1.setText(sdf.format(cldr.getTime()));
                                    }
                                }, year, month, day);
                        datepicker.getDatePicker().setMaxDate(cldr.getTimeInMillis());
//                datepicker.getDatePicker().setMinDate(System.currentTimeMillis().minusYears(14));
//                datepicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                        datepicker.show();

                    }
                });
                designationSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Dialog dialog = new Dialog(activity);
                        dialog.setContentView(R.layout.searchitems);
                        final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                        ListView list_item = (ListView) dialog.findViewById(R.id.list_item);
                        final SearchFunctionalAreaAndDesignationAdapter searchadapter = new SearchFunctionalAreaAndDesignationAdapter((FragmentActivity) activity, designationsLists, "search designation");
                        list_item.setAdapter(searchadapter);
                        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                designationSearch.setText(designationsLists.get(i).getDesignationName());
                                DesignationId = designationsLists.get(i).getDesignationId();
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
                                if (searchadapter != null) {
                                    searchadapter.filterDesignation(editable.toString());
                                }
                            }
                        });

                        dialog.show();
                    }
                });


                functionalAreaSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Dialog dialog = new Dialog(activity);
                        dialog.setContentView(R.layout.searchitems);
                        final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                        ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                        final SearchFunctionalAreaAndDesignationAdapter searchadapter = new SearchFunctionalAreaAndDesignationAdapter(activity, functionalAreAlist, "search functional area");
                        list_item.setAdapter(searchadapter);
                        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                functionalAreaSearch.setText(functionalAreAlist.get(i).getFunctionalAreaName());
                                FunctionalId = functionalAreAlist.get(i).getFunctionalAreaId();
                                getDesignations(FunctionalId);
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
                                if (searchadapter != null) {
                                    searchadapter.filterFunctional(editable.toString());
                                }
                            }
                        });

                        dialog.show();
                    }
                });


                industrySearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Dialog dialog = new Dialog(activity);
                        dialog.setContentView(R.layout.searchitems);
                        final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                        ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                        final SearchCompanyAndIndustryAdapter searchadapter = new SearchCompanyAndIndustryAdapter((FragmentActivity) activity, industriesLists, "search industry");
                        list_item.setAdapter(searchadapter);
                        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                industrySearch.setText(industriesLists.get(i).getIndustryName());
                                IndustryId = industriesLists.get(i).getIndustryId();
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
                                if (searchadapter != null) {
                                    searchadapter.filterIndustry(editable.toString());
                                }
                            }
                        });

                        dialog.show();
                    }
                });


                companySearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Dialog dialog = new Dialog(activity);
                        dialog.setContentView(R.layout.searchitems);
                        final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                        ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                        final SearchCompanyAndIndustryAdapter searchadapter = new SearchCompanyAndIndustryAdapter(activity, companiesList, "search company");
                        list_item.setAdapter(searchadapter);
                        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                companySearch.setText(companiesList.get(i).getCompanyName());
                                CompanyId = companiesList.get(i).getCompanyId();
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
                                if (searchadapter != null) {
                                    searchadapter.filterCompany(editable.toString());
                                }
                            }
                        });

                        dialog.show();
                    }
                });

               /* SpinnerAdapterForCompanies userAdapter = new SpinnerAdapterForCompanies(activity,
                        R.layout.custom_spinner, companiesList);
                getCompanyName.setAdapter(userAdapter);

                getCompanyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        CompanyId = companiesList.get(i).getCompanyId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });*/



               /* SpinnerAdapterForIndustry IndustryAdapter = new SpinnerAdapterForIndustry(activity,
                        R.layout.custom_spinner, industriesLists);*/
                /*getIndustryName.setAdapter(IndustryAdapter);

                getIndustryName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        IndustryId=industriesLists.get(i).getIndustryId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });*/



              /*  SpinnerAdapterForFunctionalArea functionalAdapter = new SpinnerAdapterForFunctionalArea(activity,
                        R.layout.custom_spinner, functionalAreAlist);
                getFunctionalArea.setAdapter(functionalAdapter);

                getFunctionalArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                       FunctionalId =functionalAreAlist.get(i).getFunctionalAreaId();
                        getDesignations(FunctionalId);
                    }

                    private void getDesignations(int functionalAreaId) {
                        service.getService().getDesignation(functionalAreaId,new Callback<Designations>() {
                            @Override
                            public void success(Designations disig, Response response) {

                                DesignationsList select=new DesignationsList();
                                select.setDesignationName("select Designation");

                                designationsLists=disig.getModel();
                                designationsLists.add(select);
                                Collections.reverse(designationsLists);
                                DesignationsList designationsList=new DesignationsList();
                                for (int j=0; j<designationsLists.size(); j++){

                                    if (experienceLists.get(i).getDesignation().equalsIgnoreCase(designationsLists.get(j).getDesignationName())){
                                        designationsList.setDesignationName(designationsLists.get(j).getDesignationName());
                                        designationsList.setDesignationId(designationsLists.get(j).getDesignationId());
                                    }

                                }
                                designationsLists.add(0,designationsList);
                                if (designationsLists.size()>0){
                                    SpinnerAdapterForDesignations userAdapter = new SpinnerAdapterForDesignations(activity,
                                            R.layout.custom_spinner, designationsLists);
                                    getDesignations.setAdapter(userAdapter);
                                }


                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                getDesignations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (!getDesignations.getSelectedItem().toString().equalsIgnoreCase("select Designation")){

                            DesignationId=designationsLists.get(i).getDesignationId();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
*/
//                if (expectedSalary.getText().toString().equals("N/A")){
//                    expectedsalaryArray.add(0, "Select Expected salary");
//                }
                expectedsalaryArray.add(0, expectedSalary.getText().toString());
                ArrayAdapter<String> Gadapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, expectedsalaryArray);

                Gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                getExpectedsalaryInExp.setAdapter(Gadapter);


                getExpectedsalaryInExp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (!getExpectedsalaryInExp.getSelectedItem().toString().equalsIgnoreCase("Select Expected salary")) {

                            ExpectedSalaryInExp = getExpectedsalaryInExp.getSelectedItem().toString();

                            if (ExpectedSalaryInExp.equalsIgnoreCase("As per company standards")) {

                                ExpectedSalaryInExp = "1";
                            } else if (ExpectedSalaryInExp.equalsIgnoreCase("10% hike")) {

                                ExpectedSalaryInExp = "2";
                            } else if (ExpectedSalaryInExp.equalsIgnoreCase("20% hike")) {

                                ExpectedSalaryInExp = "3";
                            } else if (ExpectedSalaryInExp.equalsIgnoreCase("30% hike")) {

                                ExpectedSalaryInExp = "4";
                            } else if (ExpectedSalaryInExp.equalsIgnoreCase("40% hike")) {

                                ExpectedSalaryInExp = "5";
                            } else if (ExpectedSalaryInExp.equalsIgnoreCase("50% hike")) {

                                ExpectedSalaryInExp = "6";
                            }

                        }else {
                            ExpectedSalaryInExp = null;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                noticePeroidArray.add(0, noticePeriod.getText().toString());
                ArrayAdapter<String> Nadapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, noticePeroidArray);

                Gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                getNoticePeriodInExp.setAdapter(Nadapter);

                getNoticePeriodInExp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (!getNoticePeriodInExp.getSelectedItem().toString().equalsIgnoreCase("select notice period")) {


                            NoticePeroidInExp = getNoticePeriodInExp.getSelectedItem().toString();

                            if (NoticePeroidInExp.equalsIgnoreCase("Immediate")) {
                                noticeCheck = "1";
                                NoticePeroidInExp = "1";
                            } else if (NoticePeroidInExp.equalsIgnoreCase("7 days")) {
                                noticeCheck = "2";
                                NoticePeroidInExp = "2";
                            } else if (NoticePeroidInExp.equalsIgnoreCase("15 days")) {
                                noticeCheck = "3";
                                NoticePeroidInExp = "3";
                            } else if (NoticePeroidInExp.equalsIgnoreCase("30 days")) {
                                noticeCheck = "4";
                                NoticePeroidInExp = "4";
                            } else if (NoticePeroidInExp.equalsIgnoreCase("60 days")) {
                                noticeCheck = "5";
                                NoticePeroidInExp = "5";
                            }
                        } else {
                            noticeCheck = "";
                            NoticePeroidInExp = null;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                etMonthsalary.setText(experienceLists.get(i).getMonthlySalary() + "");


                upDateExperienceProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ValidateEditeExperince()) {
                            ll_UpadteExperince.setVisibility(View.GONE);
                            UpdateExperienceById experience = new UpdateExperienceById();
                            experience.setJobseekerEmpId(experienceLists.get(i).getJobseekerEmpId());
                            experience.setJobseekerId(experienceLists.get(i).getJobseekerId());

                            if (CompanyId == 0) {
                                CompanyId = null;
                            }
                            experience.setCompanyId(CompanyId);
                            experience.setJoiningDate(joiningDate1.getText().toString().trim());
                            if (IndustryId == 0) {
                                IndustryId = null;
                            }
                            experience.setIndustryId(IndustryId);
                            if (FunctionalId == 0 ||FunctionalId == null) {
                                FunctionalId = null;
                            }
                            experience.setFunctionalAreaId(FunctionalId);
                            if (DesignationId == 0 && DesignationId == null) {
                                DesignationId = null;
                            }
                            experience.setDesignationId(DesignationId);
                            if (iscurrentCompany.isChecked()) {
                                experience.setCurrentCompany(true);
                            } else {
                                experience.setCurrentCompany(false);
                            }

                            experience.setMonthlySalary(etMonthsalary.getText().toString());


                            experience.setExpectedSalary(ExpectedSalaryInExp);

                            experience.setNoticePeriod(NoticePeroidInExp);


                            service.getService().UpdateExperienceById(experience, new Callback<PasswordResponce>() {
                                @Override
                                public void success(PasswordResponce result, Response response) {

                                    if (result.getMessage() != null) {
                                        if (!result.getMessage().trim().equalsIgnoreCase("")) {
                                            Utils.showAlertDialog(activity, result.getMessage(), false);
                                            mAdapterCallback.onMethodCallback();
                                            dialog.dismiss();
                                        }
                                    } else {
                                        Utils.showAlertDialog(activity, result.getErrorMessage(), false);
                                        dialog.dismiss();

                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {

                                }
                            });

                        }
                    }
                });
                dialog.show();

            }

            private void getDesignations(int functionalAreaId) {
                service.getService().getDesignation(functionalAreaId, new Callback<Designations>() {
                    @Override
                    public void success(Designations disig, Response response) {


                        designationsLists = disig.getModel();

                        for (int j = 0; j < designationsLists.size(); j++) {


                            String designationCheck = experienceLists.get(i).getDesignation();

                            if (designationCheck != null) {
                                if (experienceLists.get(i).getDesignation().equalsIgnoreCase(designationsLists.get(j).getDesignationName())) {
                                    designationSearch.setText(experienceLists.get(i).getDesignation());
                                    DesignationId = designationsLists.get(j).getDesignationId();
                                }
                            }


                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

            }

        });
        return view1;
    }

    private boolean ValidateEditeExperince() {
        if (!CompanyName()) {
            return false;
        } else if (!ValidateIndustry()) {
            return false;
        }else if (!ValidateFunctionalArea()) {
            return false;
        }else if (!ValidateDesignation()) {
            return false;
        }else if (!ValidateMonthlySalary()) {
            return false;
        }else if (!ValidateJoinDate()) {
            return false;
        }/*else if (checkbox == true) {

            if (!ValidateExpectedSalary()){
                return false;
            } else if (!ValidateNoticePeriod()){
                return false;
            }
            return true;
        }*/else {
            return true;
        }
    }
    private boolean ValidateNoticePeriod() {
        if (noticeCheck.equals("")) {
            tvErrorNoticeperid.setVisibility(View.VISIBLE);
            tvErrorNoticeperid.setText("Notice Period Should Not Be Empty");
            tvErrorNoticeperid.requestFocus();
            return false;
        } else {
            tvErrorNoticeperid.setVisibility(View.GONE);
            return true;
        }
    }
    private boolean ValidateExpectedSalary() {
        if (ExpectedSalaryInExp.equals("")) {
            tvErrorExpectedSalary.setVisibility(View.VISIBLE);
            tvErrorExpectedSalary.setText("Expected Salary Should Not Be Empty");
            tvErrorExpectedSalary.requestFocus();
            return false;
        } else {
            tvErrorExpectedSalary.setVisibility(View.GONE);
            return true;
        }
    }
    private boolean CompanyName() {
        if (companySearch.getText().toString().equals("Select company")) {
            tvErrorCompany.setVisibility(View.VISIBLE);
            tvErrorCompany.setText("Company Name Should Not Be Empty");
            tvErrorCompany.requestFocus();
            return false;
        } else {
            tvErrorCompany.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidateIndustry() {
        if (industrySearch.getText().toString().equals("Select industry")) {
            tvErrorIndustry.setVisibility(View.VISIBLE);
            tvErrorIndustry.setText("Industry Name Should Not Be Empty");
            tvErrorIndustry.requestFocus();
            return false;
        } else {
            tvErrorIndustry.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidateFunctionalArea() {
        if (functionalAreaSearch.getText().toString().equals("Select functional area")) {
            tvErrorFunctionalArea.setVisibility(View.VISIBLE);
            tvErrorFunctionalArea.setText("Functional Area Should Not Be Empty");
            tvErrorFunctionalArea.requestFocus();
            return false;
        } else {
            tvErrorFunctionalArea.setVisibility(View.GONE);
            return true;
        }
    }
    private boolean ValidateDesignation() {
        if (designationSearch.getText().toString().equals("Select designation")) {
            tvErrorDesignation.setVisibility(View.VISIBLE);
            tvErrorDesignation.setText("Designation Should Not Be Empty");
            tvErrorDesignation.requestFocus();
            return false;
        } else {
            tvErrorDesignation.setVisibility(View.GONE);
            return true;
        }
    }  private boolean ValidateMonthlySalary() {
        if (etMonthsalary.getText().toString().equals("")) {
            etMonthsalary.setError("Monthly Salary Should Not Be Empty");
            etMonthsalary.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    private boolean ValidateJoinDate() {
        if (joiningDate1.getText().toString().equals("")) {
            tvErrorJoinDate.setVisibility(View.VISIBLE);
//            viewJoin.setVisibility(View.VISIBLE);
            tvErrorJoinDate.setText("Joining Date Should Not Be Empty");
            return false;
        } else {
            tvErrorJoinDate.setVisibility(View.GONE);
            return true;
        }
    }
}
