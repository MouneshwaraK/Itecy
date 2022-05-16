package com.cstech.itecy.Fragments;


import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;
import static android.net.Uri.parse;
import static android.os.Build.VERSION.SDK_INT;

import static com.cstech.itecy.FilePath.getPath;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
/*import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cstech.itecy.Activities.MainActivity;
import com.cstech.itecy.AdapterCallback;
import com.cstech.itecy.Adapters.CertificationAdapter;
import com.cstech.itecy.Adapters.ExperienceAdapter;
import com.cstech.itecy.Adapters.LanguagesAdapter;
import com.cstech.itecy.Adapters.PreferredLocationsAdapter;
import com.cstech.itecy.Adapters.SearchAdapter;
import com.cstech.itecy.Adapters.SearchCompanyAndIndustryAdapter;
import com.cstech.itecy.Adapters.SearchCountryStateCityAdapter;
import com.cstech.itecy.Adapters.SearchEducationAndSpecializationAdapter;
import com.cstech.itecy.Adapters.SearchFunctionalAreaAndDesignationAdapter;
import com.cstech.itecy.Adapters.SearchNationalityAdapter;
import com.cstech.itecy.Adapters.SkillsAdapter;
import com.cstech.itecy.Adapters.SpinnerAdapterForCity;
import com.cstech.itecy.Adapters.SpinnerAdapterForClass;
import com.cstech.itecy.Adapters.SpinnerAdapterForCountry;
import com.cstech.itecy.Adapters.SpinnerAdapterForStates;
import com.cstech.itecy.Adapters.SpinnerAdapterForWorkAuthorization;
import com.cstech.itecy.BuildConfig;
import com.cstech.itecy.ConnectionDetector;
import com.cstech.itecy.FilePath;
import com.cstech.itecy.ModelClasess.AddDegree;
import com.cstech.itecy.ModelClasess.AddSpecialization;
import com.cstech.itecy.ModelClasess.AllCitiesList;
import com.cstech.itecy.ModelClasess.Authorization;
import com.cstech.itecy.ModelClasess.AuthorizationList;
import com.cstech.itecy.ModelClasess.City;
import com.cstech.itecy.ModelClasess.CityList;
import com.cstech.itecy.ModelClasess.Companies;
import com.cstech.itecy.ModelClasess.CompaniesList;
import com.cstech.itecy.ModelClasess.Country;
import com.cstech.itecy.ModelClasess.CountryList;
import com.cstech.itecy.ModelClasess.Designations;
import com.cstech.itecy.ModelClasess.DesignationsList;
import com.cstech.itecy.ModelClasess.Education;
import com.cstech.itecy.ModelClasess.EducationDetailsAdapter;
import com.cstech.itecy.ModelClasess.EducationDetailsList;
import com.cstech.itecy.ModelClasess.EducationList;
import com.cstech.itecy.ModelClasess.Experience;
import com.cstech.itecy.ModelClasess.ExperienceList;
import com.cstech.itecy.ModelClasess.FunctionalAreas;
import com.cstech.itecy.ModelClasess.FunctionalAreasList;
import com.cstech.itecy.ModelClasess.GetAllCities;
import com.cstech.itecy.ModelClasess.GetCertification;
import com.cstech.itecy.ModelClasess.GetCertificationList;
import com.cstech.itecy.ModelClasess.GetEducationDetails;
import com.cstech.itecy.ModelClasess.Industries;
import com.cstech.itecy.ModelClasess.IndustriesList;
import com.cstech.itecy.ModelClasess.JobApplyResponse;
import com.cstech.itecy.ModelClasess.Languages;
import com.cstech.itecy.ModelClasess.LanguagesList;
import com.cstech.itecy.ModelClasess.Nationalities;
import com.cstech.itecy.ModelClasess.NationalitiesList;
import com.cstech.itecy.ModelClasess.ProfileData;
import com.cstech.itecy.ModelClasess.ProfileImageResp;
import com.cstech.itecy.ModelClasess.ProfileResponse;
import com.cstech.itecy.ModelClasess.Skills;
import com.cstech.itecy.ModelClasess.SkillsList;
import com.cstech.itecy.ModelClasess.SocialProfile;
import com.cstech.itecy.ModelClasess.SocialProfileList;
import com.cstech.itecy.ModelClasess.Specialization;
import com.cstech.itecy.ModelClasess.SpecializationList;
import com.cstech.itecy.ModelClasess.States;
import com.cstech.itecy.ModelClasess.StatesList;
import com.cstech.itecy.ModelClasess.UpdateBasicProfile;
import com.cstech.itecy.ModelClasess.UpdateCertification;
import com.cstech.itecy.ModelClasess.UpdateEducation;
import com.cstech.itecy.ModelClasess.UpdateExperience;
import com.cstech.itecy.ModelClasess.UpdateProfileResponse;
import com.cstech.itecy.PathUtil;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.ServiceApi;
import com.cstech.itecy.utils.Utils;
import com.reginald.swiperefresh.CustomSwipeRefreshLayout;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */

public class Profile extends Fragment implements AdapterCallback {


    private static final int STORAGE_PERMISSION_CODE = 23;
    ImageView userProfile;
    String ProfileImage;
    ProgressDialog progressDialog;
    private String imagepath = null;
    Integer AuthId = null;

    ImageView coverPhoto;
    private String coverPhotoImagePath = null;

    ListView educationListView, experienceListView, certificationListview;
    RecyclerView skillsListview, languagesListview, preferredLocationsListView;
    private static int RESULT_LOAD_IMAGE = 1;
    SpinnerAdapterForCountry countryAdapter;
    SpinnerAdapterForClass nationalityAdapte;
    SpinnerAdapterForStates statesAdapter;
    SpinnerAdapterForCity cityAdapter;
    Menu menuu;
    MenuItem editprofile, updateProfile;
    EditText firstName, lastName, email, countryCode, mobile, jobtitle, salary, companyName, Industry,
            monthlySalary,
            certificationName, certificationLicence, certificationInstitute, universityName, passingYear, facebook, twitter, linkedIn,
            gLink, instagram, dribbleLink, summary, experience;
    RestService service;
    CheckBox iscurrentCompan;
    TextView dateofBirt, upDateGeneralInfoProfile, cancelGeneralInfoProfile, addEducation, addexperience, addcertification,
            addsocialProfile, addgeneralInfo, upDateEducationProfile, cancelEducationProfile, upDateCertificationProfile,
            cancelCertificationProfile, certificationValidity, upDateSocialProfile, cancelSocialProfile, upDateExperienceProfile,
            cancelExperienceProfile, addResume, uploadResume, chooseFile, deleteResume, downloadResume, joiningDate;
    Spinner getGender, getMaritalStatus, getExperienceLevel, getNoticePeriodInExp,
            getExpectedsalaryInExp, getWorkAuthorization;

    /*Spinner getSkills,getLanguages,getPreferredLocations;*/
    View child;
    ProgressDialog dialog;
    LinearLayout languagesLayout, nationalityLayout, genderLayout, maritalstatusLayout, companyLayout, industryLayout,
            functionalAreaLayout, designationLayout, skillsLayout, preferredLocationsLayout, educationLayout, generalInfoLayout, experienceLayout, certificationLayout, certificationchilditems, socialProfileLayout, specializationLayout,
            countyLayout, stateLayout, cityLayout, experienceLevelLayout, educationChildLayout, EditexperienceLayout, resumeLayout,llNodataFound;
    List<FunctionalAreasList> FunctionalAreAlist;
    private String UserEmployeementStatus = "";

    public Profile() {
        // Required empty public constructor
    }

    SharedPreferences preferences;
    SharedPreferences.Editor spEditor;
    private static final int EXTERNAL_STORAGE_PERMISSION_CODE = 1;
    String JobseekerId;
    Calendar calendar;
    List<EducationList> educationLists;
    List<CountryList> countryLists;
    List<StatesList> statesLists;
    List<CityList> cityLists;
    List<NationalitiesList> nationalitiesLists;
    List<LanguagesList> languagesLists;
    List<SpecializationList> specializationLists;
    List<CompaniesList> companiesList;
    List<IndustriesList> industriesLists;
    List<DesignationsList> designationsLists;
    List<SkillsList> skillsList;
    List<String> Editlanguage;
    String Gender = null, permanentResident = "";
    String MaritalStatus = null;
    Integer NationalityId = null;
    Integer CityId = null;
    String ExperienceLevel;
    String ExpectedSalary, ExpectedSalaryInExp = "";
    String NoticePeroid = "", NoticePeroidInExp;
    List<Integer> Languages;
    List<Integer> Skills;
    List<Integer> preferedlocation;
    List<String> SkillsName;
    List<String> genderArray;
    List<String> maritalStatusArray;
    List<String> expectedsalaryArray;
    List<String> noticePeroidArray;
    List<String> experienceLevelArray;
    List<AuthorizationList> authorizationLists;
    List<AllCitiesList> preferredLocationsList;
    List<String> getLocationStringArrays;
    List<String> getLanguagesStringArrays;
    List<String> getSkillsStringArrays;
    int degreeId, specializationId;
    Integer ComanyId = 0;
    Integer IndustryId = 0;
    Integer FunctionalId = 0;
    Integer DesignationId = 0;
    Integer ExpLevelId;

    File files;
    private int REQ_PDF = 21;
    RequestBody reqFile;
    MultipartBody.Part body;
    RequestBody reqId;
    int file_size;
    String UserCountry = null;
    String UserState = null;
    String UserCity = null;
    String UserNationality = null;
    String DownloadResume = "";
    TextView preferredLocationSearch;
    TextView languagesSearch;
    TextView skillsSearch;
    TextView nationalitySearch, countrySearch, citySearch, stateSearch, educationSearch,
            specializationSearch, companySearch, industrySearch, functionalAreaSearch, designationSearch;

    TextView tvGenderError, tvErrorMarital, tvErrorNationlity, tvErrorCountry, tvErrorState, tvErrorCity,
            tvErrorPrerfearreLoacwtio, tvErrorLanduage, tvErrorSkill, tvErrorExperienceLevel, tvErrorPermanent,
            tvErrorDegree, tvErrorSpec, tvErrorCompanyName, tvErrorIndustry, tvEroorFunctionArea, tvErrorDesignation,
            tvErrorJoinDate, tvErrorExpSalary, tvErrorNoticePeriod, tvErrorValidty;
    View viewJoin;
    boolean checkValidate = false;
//    SwipeRefreshLayout swipeRefreshLayout, swipRefreshExperience,swipRefreshEducation,swipRefreshCertification;
    ConnectionDetector checkNetWork;
    ScrollView mScroll;
    CustomSwipeRefreshLayout swipeRefreshLayout;
    EducationDetailsAdapter educationDetailsAdapter;
    int i = 0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        service = new RestService();
        preferences = getActivity().getSharedPreferences("ITecy", Context.MODE_PRIVATE);
        spEditor = preferences.edit();
        JobseekerId = preferences.getString("userId", "");
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        FunctionalAreAlist = new ArrayList<>();
        countryLists = new ArrayList<>();
        educationLists = new ArrayList<>();
        statesLists = new ArrayList<>();
        nationalitiesLists = new ArrayList<>();
        cityLists = new ArrayList<>();
        languagesLists = new ArrayList<>();
        Languages = new ArrayList<>();
        Skills = new ArrayList<>();
        preferedlocation = new ArrayList<>();
        SkillsName = new ArrayList<>();
        skillsList = new ArrayList<>();
        specializationLists = new ArrayList<>();
        genderArray = new ArrayList<String>();
        companiesList = new ArrayList<>();
        industriesLists = new ArrayList<>();
        designationsLists = new ArrayList<>();
        authorizationLists = new ArrayList<>();
        preferredLocationsList = new ArrayList<>();
        Editlanguage = new ArrayList<>();
        getLocationStringArrays = new ArrayList<>();
        getLanguagesStringArrays = new ArrayList<>();
        getSkillsStringArrays = new ArrayList<>();
        maritalStatusArray = new ArrayList<String>();

        expectedsalaryArray = new ArrayList<String>();
        expectedsalaryArray.add("Select Expected salary");
        expectedsalaryArray.add("As per company standards");
        expectedsalaryArray.add("10% hike");
        expectedsalaryArray.add("20% hike");
        expectedsalaryArray.add("30% hike");
        expectedsalaryArray.add("40% hike");
        expectedsalaryArray.add("50% hike");
        noticePeroidArray = new ArrayList<String>();
        noticePeroidArray.add("Select notice period");
        noticePeroidArray.add("Immediate");
        noticePeroidArray.add("7 days");
        noticePeroidArray.add("15 days");
        noticePeroidArray.add("30 days");
        noticePeroidArray.add("60 days");
        experienceLevelArray = new ArrayList<String>();

        calendar = Calendar.getInstance();
        firstName = view.findViewById(R.id.firstName);
        firstName.setEnabled(false);
        firstName.clearFocus();
        firstName.setFocusable(false);
        lastName = view.findViewById(R.id.lastName);
        lastName.clearFocus();
        lastName.setFocusable(false);
        email = view.findViewById(R.id.emailAddress);
        email.setEnabled(false);
        mobile = (EditText) view.findViewById(R.id.mobile);
        countryCode = (EditText) view.findViewById(R.id.countryCode);
        jobtitle = view.findViewById(R.id.jobTitle);
        salary = view.findViewById(R.id.salary);
        dateofBirt = view.findViewById(R.id.dob);
        swipeRefreshLayout = view.findViewById(R.id.swipelayout);
        /*swipRefreshExperience = view.findViewById(R.id.swip1);
        swipRefreshEducation = view.findViewById(R.id.swipEducation);
        swipRefreshCertification = view.findViewById(R.id.swipeCertification);*/
        upDateGeneralInfoProfile = (TextView) view.findViewById(R.id.upDateGeneralInfoProfile);
        cancelGeneralInfoProfile = view.findViewById(R.id.cancelGeneralInfoProfile);
        upDateEducationProfile = view.findViewById(R.id.upDateEducationProfile);
        cancelEducationProfile = view.findViewById(R.id.cancelEducationInfoProfile);
        addEducation = view.findViewById(R.id.addEducation);
        addgeneralInfo = view.findViewById(R.id.addgeneralInfo);
        addResume = view.findViewById(R.id.addResume);
        uploadResume = view.findViewById(R.id.uploadResume);
        chooseFile = view.findViewById(R.id.chooseFile);
        deleteResume = view.findViewById(R.id.deleteResume);
        downloadResume = view.findViewById(R.id.downloadResume);
        addsocialProfile = view.findViewById(R.id.addSocialProfile);
        addexperience = view.findViewById(R.id.addExperience);
        addcertification = view.findViewById(R.id.addCertification);
        upDateCertificationProfile = view.findViewById(R.id.upDateCertificationProfile);
        cancelCertificationProfile = view.findViewById(R.id.cancelCertificationfoProfile);
        upDateSocialProfile = view.findViewById(R.id.upDateSocialProfile);
        cancelSocialProfile = view.findViewById(R.id.cancelSocialProfile);
        upDateExperienceProfile = view.findViewById(R.id.upDateExperienceProfile);
        cancelExperienceProfile = view.findViewById(R.id.cancelExperienceProfile);
        preferredLocationSearch = view.findViewById(R.id.preferredLocationSearch);
        languagesSearch = view.findViewById(R.id.languagesSearch);
        skillsSearch = view.findViewById(R.id.skillsSearch);
        nationalitySearch = view.findViewById(R.id.nationalitySearch);
        countrySearch = view.findViewById(R.id.countrySearch);
        stateSearch = view.findViewById(R.id.stateSearch);
        citySearch = view.findViewById(R.id.citySearch);
        educationSearch = view.findViewById(R.id.educationSearch);
        specializationSearch = view.findViewById(R.id.specializationSearch);
        companySearch = view.findViewById(R.id.companySearch);
        industrySearch = view.findViewById(R.id.industrySearch);
        functionalAreaSearch = view.findViewById(R.id.functionalAreaSearch);
        designationSearch = view.findViewById(R.id.designationSearch);
        tvGenderError = view.findViewById(R.id.tv_errorGender);
        facebook = view.findViewById(R.id.facebookLink);
        gLink = view.findViewById(R.id.gLink);
        instagram = view.findViewById(R.id.instagramLink);
        dribbleLink = view.findViewById(R.id.dribbleLink);
        summary = view.findViewById(R.id.summary);
        experience = view.findViewById(R.id.experience);

        ///////////////// mounesh start /////////////////////
        ActivityCompat.requestPermissions(getActivity(), new String[]{READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);

        tvErrorMarital = view.findViewById(R.id.tv_errorMarital);
        tvErrorNationlity = view.findViewById(R.id.tv_errornationality);
        tvErrorCountry = view.findViewById(R.id.tv_errorCountry);
        tvErrorState = view.findViewById(R.id.tv_errorState);
        tvErrorCity = view.findViewById(R.id.tv_errorCity);
        tvErrorPrerfearreLoacwtio = view.findViewById(R.id.tv_errorPripreLocation);
        tvErrorLanduage = view.findViewById(R.id.tv_errorLanguage);
        tvErrorSkill = view.findViewById(R.id.tv_errorSkill);
        tvErrorExperienceLevel = view.findViewById(R.id.tv_errorExpLevel);
        tvErrorPermanent = view.findViewById(R.id.tv_errorPermanent);
        tvErrorDegree = view.findViewById(R.id.tv_errorDegree);
        tvErrorSpec = view.findViewById(R.id.tv_errorSpecialzation);
        tvErrorCompanyName = view.findViewById(R.id.tv_errorCompany);
        tvErrorIndustry = view.findViewById(R.id.tv_errorIndustry);
        tvEroorFunctionArea = view.findViewById(R.id.tv_errorFunctionalArea);
        tvErrorDesignation = view.findViewById(R.id.tv_errorDesignation);
        tvErrorJoinDate = view.findViewById(R.id.tv_errorJoinDate);
        viewJoin = view.findViewById(R.id.viewJoin);
        tvErrorExpSalary = view.findViewById(R.id.tv_errorExpectedSalary);
        tvErrorNoticePeriod = view.findViewById(R.id.tv_errorNoticePeriod);
        tvErrorValidty = view.findViewById(R.id.tv_errorCertification);
        mScroll = view.findViewById(R.id.scrollView);
        llNodataFound = view.findViewById(R.id.ll_nodataFound);
        ///////////////// mounesh end /////////////////////

        linkedIn = view.findViewById(R.id.linkedInLink);
        twitter = view.findViewById(R.id.twitterLink);

        companyName = view.findViewById(R.id.companyName);
        Industry = view.findViewById(R.id.industry);
        monthlySalary = (EditText) view.findViewById(R.id.monthlySalary);

        joiningDate = view.findViewById(R.id.joiningDate);


        joiningDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
//                cldr.add(Calendar.YEAR, -18);
                final int day = cldr.get(Calendar.DAY_OF_MONTH);
                final int month = cldr.get(Calendar.MONTH);
                final int year = cldr.get(Calendar.YEAR);
                // date picker dialog

                DatePickerDialog datepicker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                cldr.set(year, month, day);
                                String myFormat = "MM-dd-yyyy";//"MM/dd/yy"; //In which you need put here
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                                joiningDate.setText(sdf.format(cldr.getTime()));
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMaxDate(cldr.getTimeInMillis());
//                datepicker.getDatePicker().setMinDate(System.currentTimeMillis().minusYears(14));
//                datepicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datepicker.show();

            }
        });
        iscurrentCompan = (CheckBox) view.findViewById(R.id.isCurrentCompany);
        certificationName = view.findViewById(R.id.certificationName);
        certificationValidity = view.findViewById(R.id.certificationValidity);
        certificationLicence = view.findViewById(R.id.certificationLicenceNo);
        certificationInstitute = view.findViewById(R.id.certificationInstituteName);
        universityName = view.findViewById(R.id.universityName);
        passingYear = view.findViewById(R.id.passingYear);
        getWorkAuthorization = view.findViewById(R.id.getWorkAuthorization);
        getGender = view.findViewById(R.id.getGender);
        getMaritalStatus = view.findViewById(R.id.getMaritalStatus);
        getExperienceLevel = view.findViewById(R.id.getExperienceLevel);
        getNoticePeriodInExp = view.findViewById(R.id.getNoticePeriodInExp);
        getExpectedsalaryInExp = view.findViewById(R.id.getExpectedSalaryInExp);
        maritalstatusLayout = (LinearLayout) view.findViewById(R.id.maritalStatusLayout);
        genderLayout = (LinearLayout) view.findViewById(R.id.genderLayout);
        nationalityLayout = (LinearLayout) view.findViewById(R.id.nationalityLayout);
        languagesLayout = (LinearLayout) view.findViewById(R.id.languagesLayout);
        preferredLocationsLayout = (LinearLayout) view.findViewById(R.id.preferredLocationsLayout);
        companyLayout = (LinearLayout) view.findViewById(R.id.companiesLayout);
        industryLayout = (LinearLayout) view.findViewById(R.id.industryLayout);
        functionalAreaLayout = (LinearLayout) view.findViewById(R.id.functionalAreaLayout);
        designationLayout = (LinearLayout) view.findViewById(R.id.designationLayout);
        skillsLayout = (LinearLayout) view.findViewById(R.id.skillsLayout);
        educationLayout = (LinearLayout) view.findViewById(R.id.educationLayout);
        generalInfoLayout = (LinearLayout) view.findViewById(R.id.generalInfoLayout);
        experienceLayout = (LinearLayout) view.findViewById(R.id.experienceLayout);
        socialProfileLayout = (LinearLayout) view.findViewById(R.id.socialProfileLayout);
        certificationLayout = (LinearLayout) view.findViewById(R.id.certificationLayout);
        certificationchilditems = (LinearLayout) view.findViewById(R.id.certificationchilditems);
        specializationLayout = (LinearLayout) view.findViewById(R.id.specializationLayout);
        countyLayout = (LinearLayout) view.findViewById(R.id.countryLayout);
        stateLayout = (LinearLayout) view.findViewById(R.id.stateLayout);
        cityLayout = (LinearLayout) view.findViewById(R.id.cityLayout);
        experienceLevelLayout = (LinearLayout) view.findViewById(R.id.experienceLevelLayout);
        educationChildLayout = (LinearLayout) view.findViewById(R.id.educationChildLayout);
        EditexperienceLayout = (LinearLayout) view.findViewById(R.id.editExperienceLayout);
        resumeLayout = (LinearLayout) view.findViewById(R.id.resumeLayout);
        userProfile = (ImageView) view.findViewById(R.id.userProfile);
        coverPhoto = (ImageView) view.findViewById(R.id.coverImage);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        dialog.show();

        userProfile.setVisibility(View.GONE);
        String UserImage = preferences.getString("userProfileImage", "");
        if (UserImage != null) {
            if (!UserImage.isEmpty())
                Picasso.with(getActivity()).load(UserImage).noPlaceholder().centerCrop().fit()
                        .into((userProfile));
        }
        addgeneralInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        educationListView = (ListView) view.findViewById(R.id.educationList);
        experienceListView = (ListView) view.findViewById(R.id.experienceListView);
        certificationListview = (ListView) view.findViewById(R.id.certificationList);
        skillsListview = (RecyclerView) view.findViewById(R.id.skillsListview);
        languagesListview = (RecyclerView) view.findViewById(R.id.languagesListview);
        preferredLocationsListView = (RecyclerView) view.findViewById(R.id.preferredLocationsListView);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        skillsListview.setLayoutManager(horizontalLayoutManagaer);
        LinearLayoutManager horizontalLayout
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        languagesListview.setLayoutManager(horizontalLayout);

        LinearLayoutManager preferredLocations
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        preferredLocationsListView.setLayoutManager(preferredLocations);


        getGender.setEnabled(false);
        getGender.setClickable(false);
        getMaritalStatus.setEnabled(false);
        getMaritalStatus.setClickable(false);
        nationalitySearch.setClickable(false);
        nationalitySearch.setEnabled(false);
        countrySearch.setEnabled(false);
        countrySearch.setClickable(false);
        stateSearch.setClickable(false);
        stateSearch.setEnabled(false);
        citySearch.setEnabled(false);
        citySearch.setClickable(false);
        preferredLocationSearch.setClickable(false);
        preferredLocationSearch.setEnabled(false);
        languagesSearch.setEnabled(false);
        languagesSearch.setClickable(false);
        skillsSearch.setClickable(false);
        skillsSearch.setEnabled(false);
        preferredLocationsLayout.setClickable(false);
        preferredLocationsLayout.setEnabled(false);
        languagesLayout.setEnabled(false);
        languagesLayout.setClickable(false);
        skillsLayout.setClickable(false);
        skillsLayout.setEnabled(false);
        getExperienceLevel.setClickable(false);
        getExperienceLevel.setEnabled(false);
        getWorkAuthorization.setEnabled(false);
        getWorkAuthorization.setClickable(false);
        dateofBirt.setEnabled(false);
        dateofBirt.setClickable(false);
        upDateCertificationProfile.setClickable(false);
        upDateCertificationProfile.setEnabled(false);
        cancelExperienceProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditexperienceLayout.setVisibility(View.GONE);
                experienceListView.setVisibility(View.VISIBLE);
                ComanyId = null;
                IndustryId = null;
                FunctionalId = null;
                DesignationId = null;
                iscurrentCompan.setChecked(false);
                companySearch.setText("Select company");
                industrySearch.setText("Select industry");
                functionalAreaSearch.setText("Select functional area");
                designationSearch.setText("Select designation");
                joiningDate.setText("");
                monthlySalary.setText("");

            }
        });
        final CoordinatorLayout coordinatorLayout = view.findViewById(R.id.cordinat);
  /*      swipeRefreshLayout.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    swipeRefreshLayout.setEnabled((coordinatorLayout.getScrollY() == 0));
                }
            });*/



//        swipeRefreshLayout.setEnabled(false);
        checkNetWork = new ConnectionDetector(getActivity());
        if (checkNetWork.isConnectingToInternet()) {

            GetBasicProfileData();

            getEducation();
            getCompanies();

            getIndustries();

            getFunctionalAreas();


        } else {
            Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
        }
  /*      swipeRefreshLayout.setRefreshing(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY > 1) {
                        swipeRefreshLayout.setEnabled(false);
                    } else {
                        swipeRefreshLayout.setEnabled(true);
                    }

                }
            });
        }
*/
        experienceListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition = (experienceListView == null || experienceListView.getChildCount() == 0) ?
                        0 : experienceListView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled((topRowVerticalPosition >= 0));
            }
        });
        swipeRefreshLayout.setRefreshMode(CustomSwipeRefreshLayout.REFRESH_MODE_SWIPE);

        swipeRefreshLayout.setOnRefreshListener(new CustomSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do something here when it starts to refresh
                // e.g. to request data from server
                if (checkNetWork.isConnectingToInternet()) {
                    if (i==1){
                        GetBasicProfileData();
                        i=0;
                    }
                   if (i==2){
                       getEducation();
                       GetEducationDetails();
                       i=0;
                   }
                   if (i==3){
                       GetExperienceData();
                       i=0;
                   }
                    if (i==4){

                       i=0;
                    }
                    if (i==5){
                        getCertificationById();
                        i=0;
                    }

//                    getCompanies();
//
//                    GetEducationDetails();
//
//                    getFunctionalAreas();
//
//
//

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }
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


    @Override
    public void onMethodCallback() {
        getEducation();
        GetEducationDetails();
        GetExperienceData();
        getCertificationById();
    }

    @Override
    public void onStart() {
        super.onStart();


        designationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchFunctionalAreaAndDesignationAdapter searchadapter = new SearchFunctionalAreaAndDesignationAdapter(getActivity(), designationsLists, "search designation");
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

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchFunctionalAreaAndDesignationAdapter searchadapter = new SearchFunctionalAreaAndDesignationAdapter(getActivity(), FunctionalAreAlist, "search functional area");
                list_item.setAdapter(searchadapter);
                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        functionalAreaSearch.setText(FunctionalAreAlist.get(i).getFunctionalAreaName());
                        FunctionalId = FunctionalAreAlist.get(i).getFunctionalAreaId();
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

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchCompanyAndIndustryAdapter searchadapter = new SearchCompanyAndIndustryAdapter(getActivity(), industriesLists, "search industry");
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

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchCompanyAndIndustryAdapter searchadapter = new SearchCompanyAndIndustryAdapter(getActivity(), companiesList, "search company");
                list_item.setAdapter(searchadapter);
                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        companySearch.setText(companiesList.get(i).getCompanyName());
                        ComanyId = companiesList.get(i).getCompanyId();
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


        specializationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);

                dialog.show();
                final SearchEducationAndSpecializationAdapter searchadapter = new SearchEducationAndSpecializationAdapter(getActivity(), specializationLists, "search specialization");
                list_item.setAdapter(searchadapter);

                if (specializationLists.size() == 0) {

                    dialog.dismiss();
                    final Dialog specializationLayout = new Dialog(getActivity());
                    specializationLayout.setContentView(R.layout.adddegree);
                    final EditText specialization = (EditText) specializationLayout.findViewById(R.id.etDegree);
                    TextView addSpecialization = (TextView) specializationLayout.findViewById(R.id.addDegree);

                    addSpecialization.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AddSpecialization spdata = new AddSpecialization();
                            spdata.setSpecialization(specialization.getText().toString());
                            spdata.setEducationId(degreeId);

                            service.getService().AddSpecialization(spdata, new Callback<JobApplyResponse>() {
                                @Override
                                public void success(JobApplyResponse jobApplyResponse, Response response) {

                                    if (jobApplyResponse.getMessage() != null) {
                                        if (!jobApplyResponse.getMessage().isEmpty()) {


                                            getSpecilization(degreeId);
                                            specializationLayout.dismiss();
                                            Utils.showAlertDialog(getActivity(), jobApplyResponse.getMessage(), false);
                                        }
                                    } else {
                                        Utils.showAlertDialog(getActivity(), "Already Added, please try again with different degree", false);

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

                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        if (specializationLists.size() != 0) {
                            specializationSearch.setText(specializationLists.get(i).getEducationTypeName());
                            specializationId = specializationLists.get(i).getEducationTypeId();
                            editSearch.setText("");
                            dialog.dismiss();
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
                        if (searchadapter != null) {
                            searchadapter.filterSpecialization(editable.toString());
                        }
                    }
                });


            }
        });


        educationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);
                final SearchEducationAndSpecializationAdapter searchadapter = new SearchEducationAndSpecializationAdapter(getActivity(), educationLists, "search degree");
                list_item.setAdapter(searchadapter);
                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        if (!educationLists.get(i).getDegree().equalsIgnoreCase("Other")) {
                            educationSearch.setText(educationLists.get(i).getDegree());
                            degreeId = educationLists.get(i).getEducationId();
                            getSpecilization(degreeId);
                            editSearch.setText("");
                            dialog.dismiss();
                        } else {
                            editSearch.setText("");
                            dialog.dismiss();
                            final Dialog addDegreeDialog = new Dialog(getActivity());
                            addDegreeDialog.setContentView(R.layout.adddegree);
                            final EditText degree = (EditText) addDegreeDialog.findViewById(R.id.etDegree);
                            TextView addDegree = (TextView) addDegreeDialog.findViewById(R.id.addDegree);

                            addDegreeDialog.show();
                            addDegree.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if (degree.getText().toString().isEmpty()) {

                                        degree.setError("Field should not be empty");
                                        degree.requestFocus();
                                    } else {
                                        AddDegree Degree = new AddDegree();
                                        Degree.setDegree(degree.getText().toString());

                                        service.getService().AddDegree(Degree, new Callback<JobApplyResponse>() {
                                            @Override
                                            public void success(JobApplyResponse jobApplyResponse, Response response) {

                                                if (jobApplyResponse.getMessage() != null) {
                                                    if (!jobApplyResponse.getMessage().isEmpty()) {

                                                        getEducation();
                                                        addDegreeDialog.dismiss();
                                                        Utils.showAlertDialog(getActivity(), jobApplyResponse.getMessage(), false);
                                                    }
                                                } else {
                                                    Utils.showAlertDialog(getActivity(), "Already Added, please try again with different degree", false);

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
                        if (searchadapter != null) {
                            searchadapter.filterDegree(editable.toString());
                        }
                    }
                });

                dialog.show();
            }
        });


        citySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchCountryStateCityAdapter searchadapter = new SearchCountryStateCityAdapter(getActivity(), cityLists, "search city");
                list_item.setAdapter(searchadapter);
                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        citySearch.setText(cityLists.get(i).getCityName());
                        CityId = cityLists.get(i).getCityId();
                        /*if (City.contains("Select City")){
                            CityId=null;
                        }else {
                            CityId=cityLists.get(i).getCityId();
                        }*/
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
                            searchadapter.filterCities(editable.toString());
                        }
                    }
                });

                dialog.show();
            }
        });

        stateSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchCountryStateCityAdapter searchadapter = new SearchCountryStateCityAdapter(getActivity(), statesLists, "search state");
                list_item.setAdapter(searchadapter);
                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        getCitys(statesLists.get(i).getStateId());
                        stateSearch.setText(statesLists.get(i).getStateName());
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
                            searchadapter.filterStates(editable.toString());
                        }
                    }
                });

                dialog.show();
            }
        });


        countrySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchCountryStateCityAdapter searchadapter = new SearchCountryStateCityAdapter(getActivity(), countryLists);
                list_item.setAdapter(searchadapter);
                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        int countryId = countryLists.get(i).getCountryId();
                        countrySearch.setText(countryLists.get(i).getCountryName());
                        getState(countryId);
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
                            searchadapter.filter(editable.toString());
                        }
                    }
                });

                dialog.show();
            }
        });


        nationalitySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchNationalityAdapter searchadapter = new SearchNationalityAdapter(getActivity(), nationalitiesLists);
                list_item.setAdapter(searchadapter);
                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        nationalitySearch.setText(nationalitiesLists.get(i).getNationalityName());
                        NationalityId = nationalitiesLists.get(i).getNationalityId();
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
                            searchadapter.filter(editable.toString());
                        }
                    }
                });

                dialog.show();
            }
        });
        skillsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchAdapter searchadapter = new SearchAdapter(getActivity(), skillsList);
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
                        if (searchadapter != null) {
                            searchadapter.filterSkills(editable.toString());
                        }
                    }
                });


                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        SkillsList skill = new SkillsList();
                        skill.setSkillsName(skillsList.get(i).getSkillsName());
                        skill.setSkillsId(skillsList.get(i).getSkillsId());
                        Utils.skillName.add(skill);
                        SkillsAdapter adapter = new SkillsAdapter(getActivity(), Utils.skillName);
                        skillsListview.setAdapter(adapter);
                        editSearch.setText("");
                        dialog.dismiss();


                    }
                });
                dialog.show();

            }
        });

        languagesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchAdapter searchadapter = new SearchAdapter(getActivity(), languagesLists);
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
                        if (searchadapter != null) {
                            searchadapter.filterLanguages(editable.toString());
                        }
                    }
                });


                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        LanguagesList languagesList = new LanguagesList();
                        languagesList.setLanguageName(languagesLists.get(i).getLanguageName());
                        languagesList.setLanguageId(languagesLists.get(i).getLanguageId());
                        Utils.languagesLists.add(languagesList);
                        LanguagesAdapter adapter = new LanguagesAdapter(getActivity(), Utils.languagesLists);
                        languagesListview.setAdapter(adapter);
                        editSearch.setText("");
                        dialog.dismiss();


                    }
                });
                dialog.show();

            }
        });
        preferredLocationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.searchitems);
                final EditText editSearch = (EditText) dialog.findViewById(R.id.editSearch);
                ListView list_item = (ListView) dialog.findViewById(R.id.list_item);


                final SearchAdapter searchadapter = new SearchAdapter(getActivity(), preferredLocationsList);
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
                        if (searchadapter != null) {
                            searchadapter.filter(editable.toString());
                        }
                    }
                });


                list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        AllCitiesList allCitiesList = new AllCitiesList();
                        allCitiesList.setCityName(preferredLocationsList.get(i).getCityName());
                        allCitiesList.setCityId(preferredLocationsList.get(i).getCityId());
                        Utils.preferredLocations.add(allCitiesList);
                        PreferredLocationsAdapter adapter = new PreferredLocationsAdapter(getActivity(), Utils.preferredLocations);
                        preferredLocationsListView.setAdapter(adapter);
                        editSearch.setText("");
                        dialog.dismiss();

                    }
                });
                dialog.show();

            }
        });


        /*for (int i = 0; i < generalInfoLayout.getChildCount(); i++) {
            child = generalInfoLayout.getChildAt(i);
            child.setEnabled(false);
        }*/

        firstName.setEnabled(false);
        lastName.setEnabled(false);
        mobile.setEnabled(false);
        countryCode.setEnabled(false);
        jobtitle.setEnabled(false);
        summary.setEnabled(false);
        experience.setEnabled(false);

        for (int i = 0; i < socialProfileLayout.getChildCount(); i++) {
            child = socialProfileLayout.getChildAt(i);
            child.setEnabled(false);
        }


        upDateGeneralInfoProfile.setVisibility(View.GONE);
        cancelGeneralInfoProfile.setVisibility(View.GONE);

        upDateEducationProfile.setVisibility(View.GONE);
        cancelEducationProfile.setVisibility(View.GONE);

        upDateExperienceProfile.setVisibility(View.GONE);
        cancelExperienceProfile.setVisibility(View.GONE);

        upDateSocialProfile.setVisibility(View.GONE);
        cancelSocialProfile.setVisibility(View.GONE);

        upDateCertificationProfile.setVisibility(View.GONE);
        cancelCertificationProfile.setVisibility(View.GONE);

        educationChildLayout.setVisibility(View.GONE);
        educationListView.setVisibility(View.VISIBLE);

        dateofBirt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

//                DatePickerDial("Dob");
//                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//                inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
                final Calendar cldr = Calendar.getInstance();
                cldr.add(Calendar.YEAR, -21);
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog datepicker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                cldr.set(year, monthOfYear, dayOfMonth);
//                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                                String dateString = sdf.format(cldr.getTime());
                                String myFormat = "yyyy-MM-dd";//"MM/dd/yy"; //In which you need put here
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                                dateofBirt.setText(sdf.format(calendar.getTime()));
                                //   binding.tvDate.setText(dateString);
//                                binding.etDob.setText(dateString);
////                                binding.tvDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
//                                Dob = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMaxDate(cldr.getTimeInMillis());
//                datepicker.getDatePicker().setMinDate(System.currentTimeMillis().minusYears(14));
//                datepicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datepicker.show();
            }
        });

        certificationValidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDial("Validity");

            }
        });


        upDateGeneralInfoProfile.setVisibility(View.GONE);
        cancelGeneralInfoProfile.setVisibility(View.GONE);

        addEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llNodataFound.setVisibility(View.GONE);
                educationLayout.setVisibility(View.GONE);
                i=2;
                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {

                    educationLayout.setVisibility(View.VISIBLE);
                    generalInfoLayout.setVisibility(View.GONE);
                    experienceLayout.setVisibility(View.GONE);
                    socialProfileLayout.setVisibility(View.GONE);
                    certificationLayout.setVisibility(View.GONE);
                    resumeLayout.setVisibility(View.GONE);

                    addgeneralInfo.setBackgroundColor(getResources().getColor(R.color.black));
                    addEducation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    addcertification.setBackgroundColor(getResources().getColor(R.color.black));
                    addexperience.setBackgroundColor(getResources().getColor(R.color.black));
                    addsocialProfile.setBackgroundColor(getResources().getColor(R.color.black));
                    addResume.setBackgroundColor(getResources().getColor(R.color.black));

                    GetEducationDetails();

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }


            }
        });

        addgeneralInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=1;
                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {

                    educationLayout.setVisibility(View.GONE);
                    generalInfoLayout.setVisibility(View.VISIBLE);
                    experienceLayout.setVisibility(View.GONE);
                    socialProfileLayout.setVisibility(View.GONE);
                    certificationLayout.setVisibility(View.GONE);
                    resumeLayout.setVisibility(View.GONE);

                    addgeneralInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    addEducation.setBackgroundColor(getResources().getColor(R.color.black));
                    addcertification.setBackgroundColor(getResources().getColor(R.color.black));
                    addexperience.setBackgroundColor(getResources().getColor(R.color.black));
                    addsocialProfile.setBackgroundColor(getResources().getColor(R.color.black));
                    addResume.setBackgroundColor(getResources().getColor(R.color.black));

                    GetBasicProfileData();

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }


            }
        });
        addcertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=5;
                certificationchilditems.setVisibility(View.GONE);
                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {
                    educationLayout.setVisibility(View.GONE);
                    generalInfoLayout.setVisibility(View.GONE);
                    experienceLayout.setVisibility(View.GONE);
                    socialProfileLayout.setVisibility(View.GONE);
                    certificationLayout.setVisibility(View.VISIBLE);

                    resumeLayout.setVisibility(View.GONE);
                    addgeneralInfo.setBackgroundColor(getResources().getColor(R.color.black));
                    addEducation.setBackgroundColor(getResources().getColor(R.color.black));
                    addcertification.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    addexperience.setBackgroundColor(getResources().getColor(R.color.black));
                    addsocialProfile.setBackgroundColor(getResources().getColor(R.color.black));
                    addResume.setBackgroundColor(getResources().getColor(R.color.black));
                    getCertificationById();

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }


            }
        });


        addsocialProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=4;
                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {
                    educationLayout.setVisibility(View.GONE);
                    generalInfoLayout.setVisibility(View.GONE);
                    experienceLayout.setVisibility(View.GONE);
                    socialProfileLayout.setVisibility(View.VISIBLE);
                    certificationLayout.setVisibility(View.GONE);
                    resumeLayout.setVisibility(View.GONE);

                    addgeneralInfo.setBackgroundColor(getResources().getColor(R.color.black));
                    addEducation.setBackgroundColor(getResources().getColor(R.color.black));
                    addcertification.setBackgroundColor(getResources().getColor(R.color.black));
                    addexperience.setBackgroundColor(getResources().getColor(R.color.black));
                    addResume.setBackgroundColor(getResources().getColor(R.color.black));
                    addsocialProfile.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    llNodataFound.setVisibility(View.GONE);

                    getSocilaProfile();

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }

            }
        });

        addexperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=3;
                EditexperienceLayout.setVisibility(View.GONE);
                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {
                    educationLayout.setVisibility(View.GONE);
                    generalInfoLayout.setVisibility(View.GONE);
                    experienceLayout.setVisibility(View.VISIBLE);
                    socialProfileLayout.setVisibility(View.GONE);
                    certificationLayout.setVisibility(View.GONE);
                    resumeLayout.setVisibility(View.GONE);

                    addgeneralInfo.setBackgroundColor(getResources().getColor(R.color.black));
                    addEducation.setBackgroundColor(getResources().getColor(R.color.black));
                    addcertification.setBackgroundColor(getResources().getColor(R.color.black));
                    addexperience.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    addsocialProfile.setBackgroundColor(getResources().getColor(R.color.black));
                    addResume.setBackgroundColor(getResources().getColor(R.color.black));

                    GetExperienceData();

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }

            }
        });

        addResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=6;
                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {
                    educationLayout.setVisibility(View.GONE);
                    generalInfoLayout.setVisibility(View.GONE);
                    experienceLayout.setVisibility(View.GONE);
                    socialProfileLayout.setVisibility(View.GONE);
                    certificationLayout.setVisibility(View.GONE);
                    resumeLayout.setVisibility(View.VISIBLE);

                    addgeneralInfo.setBackgroundColor(getResources().getColor(R.color.black));
                    addEducation.setBackgroundColor(getResources().getColor(R.color.black));
                    addcertification.setBackgroundColor(getResources().getColor(R.color.black));
                    addexperience.setBackgroundColor(getResources().getColor(R.color.black));
                    addsocialProfile.setBackgroundColor(getResources().getColor(R.color.black));
                    addResume.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    llNodataFound.setVisibility(View.GONE);

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }


            }
        });

        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isReadStorageAllowed()) {
                    if (SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getContext(),
                            READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{READ_EXTERNAL_STORAGE},
                                EXTERNAL_STORAGE_PERMISSION_CODE);
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WAKE_LOCK},
                                EXTERNAL_STORAGE_PERMISSION_CODE);

                        //  ActivityCompat.requestPermissions(getActivity(), new String[]{ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION},1);
        /*          Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                  Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                  intent.setData(uri);
                  startActivity(intent);*/

                        //  requestPermission();
                        //startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, Uri.parse("package:${BuildConfig.APPLICATION_ID}")));
                        return;
                    }
                    try {
                   /*       if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                        if (Environment.isExternalStorageManager()) {
                            //todo when permission is granted
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.addCategory(Intent.CATEGORY_OPENABLE);*/
                        //  intent.setType("*/*");
                           /* String[] mimetypes = {"image/*", "application/*"};
                            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                            startActivityForResult(intent, REQ_PDF);
                        } else {
                            //request for the permission
                            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                            Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    }else {*/
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("*/*");
                        String[] mimetypes = {"image/*", "application/*"};
                        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                        startActivityForResult(intent, REQ_PDF);
                        // }

                    }catch (Exception e){
                        Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                    }

                    //startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
                } else {
                    requestStoragePermission();
                }
            }
        });

        downloadResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {

                    String imageUri;
                    if (!DownloadResume.isEmpty()) {

                        if (BuildConfig.BASE_URL.equalsIgnoreCase("http://testitecywebapi.azurewebsites.net/")) {
                            imageUri = "http://testitecy.azurewebsites.net/JS/Jobseekers/download?img=" + DownloadResume;
                        } else {
                            imageUri = "http://www.itecy.com/JS/Jobseekers/download?img=" + DownloadResume;
                        }
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(parse(imageUri));
                        startActivity(intent);
                    }

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }


            }
        });
        deleteResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {

                    DeleteResume();
                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }

            }
        });


        uploadResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {

//                    UploadResume();
                    try {
                        raiseRequest();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }

            }
        });


        upDateCertificationProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {

                    if (validationCertification()) {
                        upDateCertificationProfile.setVisibility(View.GONE);
                        cancelCertificationProfile.setVisibility(View.GONE);
                        upDateCertificationProfile.setEnabled(false);
                        certificationchilditems.setVisibility(View.GONE);
                        certificationListview.setVisibility(View.VISIBLE);
                        UpdateCertification();
                    }

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }

            }
        });

        cancelCertificationProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                certificationListview.setVisibility(View.VISIBLE);
                certificationchilditems.setVisibility(View.GONE);
                certificationName.setText("");
                certificationValidity.setText("");
                certificationLicence.setText("");
                certificationInstitute.setText("");
            }
        });

        cancelEducationProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                passingYear.setText("");
                universityName.setText("");
                degreeId = 0;
                specializationId = 0;
                educationChildLayout.setVisibility(View.GONE);
                educationListView.setVisibility(View.VISIBLE);

            }
        });

        cancelSocialProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDateSocialProfile.setVisibility(View.GONE);
                cancelSocialProfile.setVisibility(View.GONE);
                facebook.setText("");
                twitter.setText("");
                linkedIn.setText("");
                gLink.setText("");
                instagram.setText("");
                dribbleLink.setText("");
            }
        });


        upDateSocialProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {

                    if (ValidatSocialLinks()) {
                        upDateSocialProfile.setVisibility(View.GONE);
                        cancelSocialProfile.setVisibility(View.GONE);
                        UpdateSocialProfil();
                    }
                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }

            }
        });

        upDateExperienceProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {
                    if (ValidateEcperince()) {
                        upDateExperienceProfile.setVisibility(View.GONE);
                        cancelExperienceProfile.setVisibility(View.GONE);
                        UpdateExperienceProfile();
                    }

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }

            }
        });


        upDateEducationProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {


                    if (ValidateEducation()) {
                        upDateEducationProfile.setVisibility(View.GONE);
                        cancelEducationProfile.setVisibility(View.GONE);
                        UpdateEducationDetails();
                    }

                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }


            }
        });

        upDateGeneralInfoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ConnectionDetector checkNetWork = new ConnectionDetector(getActivity());
                if (checkNetWork.isConnectingToInternet()) {

                    if (Validate()) {
                        upDateGeneralInfoProfile.setVisibility(View.GONE);
                        cancelGeneralInfoProfile.setVisibility(View.GONE);
                        UpdateBasicInfo();
                    }
                } else {
                    Utils.showAlertDialog(getActivity(), "check your internet connection!", false);
                }


            }
        });

        cancelGeneralInfoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editprofile.setVisible(true);
                updateProfile.setVisible(false);
                cancelGeneralInfoProfile.setVisibility(View.GONE);
                upDateGeneralInfoProfile.setVisibility(View.GONE);
                getGender.setEnabled(false);
                getGender.setClickable(false);
                getMaritalStatus.setEnabled(false);
                getMaritalStatus.setClickable(false);
                nationalitySearch.setClickable(false);
                nationalitySearch.setEnabled(false);
                countrySearch.setEnabled(false);
                countrySearch.setClickable(false);
                stateSearch.setClickable(false);
                stateSearch.setEnabled(false);
                citySearch.setEnabled(false);
                citySearch.setClickable(false);
                preferredLocationSearch.setClickable(false);
                preferredLocationSearch.setEnabled(false);
                languagesSearch.setEnabled(false);
                languagesSearch.setClickable(false);
                skillsSearch.setClickable(false);
                skillsSearch.setEnabled(false);
                preferredLocationsLayout.setClickable(false);
                preferredLocationsLayout.setEnabled(false);
                languagesLayout.setEnabled(false);
                languagesLayout.setClickable(false);
                skillsLayout.setClickable(false);
                skillsLayout.setEnabled(false);
                getExperienceLevel.setClickable(false);
                getExperienceLevel.setEnabled(false);
                getWorkAuthorization.setEnabled(false);
                getWorkAuthorization.setClickable(false);
                dateofBirt.setEnabled(false);
                dateofBirt.setClickable(false);

               /* for (int i = 0; i < generalInfoLayout.getChildCount(); i++) {
                    child = generalInfoLayout.getChildAt(i);
                    child.setEnabled(false);
                }*/

               /* Skills.clear();
                SkillsName.clear();
                Languages.clear();
                Editlanguage.clear();*/


            }
        });

        getGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Gender = getGender.getSelectedItem().toString();

                if (Gender.equalsIgnoreCase("Select gender")) {

                    Gender = "";
                }
                if (Gender.equalsIgnoreCase("Male")) {

                    Gender = "1";
                }
                if (Gender.equalsIgnoreCase("Female")) {

                    Gender = "2";
                }
                if (Gender.equalsIgnoreCase("Others")) {

                    Gender = "3";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getMaritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                MaritalStatus = getMaritalStatus.getSelectedItem().toString().trim();

                if (MaritalStatus.equalsIgnoreCase("Select marital status")) {

                    MaritalStatus = " ";
                }

                if (MaritalStatus.equalsIgnoreCase("Married")) {

                    MaritalStatus = "1";
                }
                if (MaritalStatus.equalsIgnoreCase("Single")) {

                    MaritalStatus = "2";
                }
                if (MaritalStatus.equalsIgnoreCase("Divorce")) {


                    MaritalStatus = "3";
                }
                if (MaritalStatus.equalsIgnoreCase("Widowed")) {

                    MaritalStatus = "4";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        coverPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isReadStorageAllowed()) {


                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    //photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(photoPickerIntent, "Complete action using"), 2);
                    //startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
                } else {
                    requestStoragePermission();
                }

            }
        });


        getExperienceLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ExperienceLevel = getExperienceLevel.getSelectedItem().toString();

                if (ExperienceLevel.equalsIgnoreCase("Select Experience Level")) {
                    ExpLevelId = null;
                }
                if (ExperienceLevel.equalsIgnoreCase("Less than 1 Year")) {
                    ExpLevelId = 1;
                }
                if (ExperienceLevel.equalsIgnoreCase("1-2 Years")) {
                    ExpLevelId = 2;
                }
                if (ExperienceLevel.equalsIgnoreCase("3-5 Years")) {
                    ExpLevelId = 3;
                }
                if (ExperienceLevel.equalsIgnoreCase("6-10 Years")) {
                    ExpLevelId = 4;
                }
                if (ExperienceLevel.equalsIgnoreCase("More than 10 Years")) {
                    ExpLevelId = 5;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


        getExpectedsalaryInExp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!getExpectedsalaryInExp.getSelectedItem().toString().equalsIgnoreCase("Select expected salary")) {
                    ExpectedSalaryInExp = getExpectedsalaryInExp.getSelectedItem().toString();
                    //ExpectedSalaryInExp = ExpectedSalaryInExp.replaceAll("0% hike", "");
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
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        getNoticePeriodInExp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!getNoticePeriodInExp.getSelectedItem().toString().equalsIgnoreCase("Select notice period")) {

                    NoticePeroidInExp = getNoticePeriodInExp.getSelectedItem().toString();
                    if (NoticePeroidInExp.equalsIgnoreCase("Immediate")) {
                        NoticePeroid = "1";
                        NoticePeroidInExp = "1";
                    } else if (NoticePeroidInExp.equalsIgnoreCase("7 days")) {
                        NoticePeroid = "2";
                        NoticePeroidInExp = "2";
                    } else if (NoticePeroidInExp.equalsIgnoreCase("15 days")) {
                        NoticePeroid = "3";
                        NoticePeroidInExp = "3";
                    } else if (NoticePeroidInExp.equalsIgnoreCase("30 days")) {
                        NoticePeroid = "4";
                        NoticePeroidInExp = "4";
                    } else if (NoticePeroidInExp.equalsIgnoreCase("60 days")) {
                        NoticePeroid = "5";
                        NoticePeroidInExp = "5";
                    }

                } else {
                    NoticePeroid = "";
                    NoticePeroidInExp = null;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        if (iscurrentCompan.isChecked()) {

            getExpectedsalaryInExp.setVisibility(View.VISIBLE);
            getNoticePeriodInExp.setVisibility(View.VISIBLE);
        } else {
            getExpectedsalaryInExp.setVisibility(View.GONE);
            getNoticePeriodInExp.setVisibility(View.GONE);
        }

        iscurrentCompan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    checkValidate = true;
                    getExpectedsalaryInExp.setVisibility(View.VISIBLE);
                    getNoticePeriodInExp.setVisibility(View.VISIBLE);
                } else {
                    checkValidate = false;
                    getExpectedsalaryInExp.setVisibility(View.GONE);
                    getNoticePeriodInExp.setVisibility(View.GONE);
                }
            }
        });


        getWorkAuthorization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AuthId = authorizationLists.get(i).getAuthId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean ValidatSocialLinks() {
        if (!FaceBookLink()) {
            return false;
        } else if (!TwitterLink()) {
            return false;
        } else if (!LinkedinLink()) {
            return false;
        } else if (!GoogleLink()) {
            return false;
        } else if (!InstaLink()) {
            return false;
        } else if (!DribbleLink()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean FaceBookLink() {
        if (facebook.getText().toString().equals("")) {
            return true;
        } else if (!facebook.getText().toString().startsWith("http") || !facebook.getText().toString().startsWith("https")) {
            facebook.setError("Please Provide Valid Link");
            facebook.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean TwitterLink() {
        if (twitter.getText().toString().equals("")) {
            return true;
        } else if (!twitter.getText().toString().startsWith("http") || !twitter.getText().toString().startsWith("https")) {
            twitter.setError("Please Provide Valid Link");
            twitter.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean LinkedinLink() {
        if (linkedIn.getText().toString().equals("")) {
            return true;
        } else if (!linkedIn.getText().toString().startsWith("http") || !linkedIn.getText().toString().startsWith("https")) {
            linkedIn.setError("Please Provide Valid Link");
            linkedIn.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean GoogleLink() {
        if (gLink.getText().toString().equals("")) {
            return true;
        } else if (!gLink.getText().toString().startsWith("http") || !gLink.getText().toString().startsWith("https")) {
            gLink.setError("Please Provide Valid Link");
            gLink.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean InstaLink() {
        if (instagram.getText().toString().equals("")) {
            return true;
        } else if (!instagram.getText().toString().startsWith("http") || !instagram.getText().toString().startsWith("https")) {
            instagram.setError("Please Provide Valid Link");
            instagram.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean DribbleLink() {
        if (dribbleLink.getText().toString().equals("")) {
            return true;
        } else if (!dribbleLink.getText().toString().startsWith("http") || !dribbleLink.getText().toString().startsWith("https")) {
            dribbleLink.setError("Please Provide Valid Link");
            dribbleLink.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    private boolean validationCertification() {
        if (!ValidateCertification()) {
            return false;
        } else if (!ValidateValidity()) {
            return false;
        } else if (!ValidateLicenceNo()) {
            return false;
        } else if (!ValidateInstitute()) {
            return false;
        } else
            return true;
    }

    private boolean ValidateLicenceNo() {
        if (certificationLicence.getText().toString().equals("")) {
            certificationLicence.setError("Licence Number Should Not Be Empty");
            certificationLicence.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidateInstitute() {
        if (certificationInstitute.getText().toString().equals("")) {
            certificationInstitute.setError("Institute Name Should Not Be Empty");
            certificationInstitute.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidateCertification() {
        if (certificationName.getText().toString().equals("")) {
            certificationName.setError("Certification Name Should Not Be Empty");
            certificationName.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidateValidity() {
        if (certificationValidity.getText().toString().equals("")) {
            tvErrorValidty.setVisibility(View.VISIBLE);
            tvErrorValidty.setText("Validity Should Not Be Empty");
            tvErrorValidty.requestFocus();
            return false;
        } else {
            tvErrorValidty.setVisibility(View.GONE);
            return true;
        }
    }


    private boolean ValidateEcperince() {
        if (!ValidateCompany()) {
            return false;
        } else if (!ValidateIndustry()) {
            return false;
        } else if (!ValidateFunctionalAre()) {
            return false;
        } else if (!ValidateDesignation()) {
            return false;
        } else if (!ValidateMonthlySalary()) {
            return false;
        } else if (!ValidateJoinDate()) {
            return false;
        }/* else if (checkValidate == true) {
            if (!ValidateExpectedSalary()) {
                return false;
            } else if (!ValidateNoticePeriod()) {
                return false;
            }
            return true;
        }*/ else {
            return true;
        }
    }

    private boolean ValidateExpectedSalary() {
        if (ExpectedSalaryInExp.equals("")) {
            tvErrorExpSalary.setVisibility(View.VISIBLE);
            tvErrorExpSalary.setText("Expected Salary Should Not Be Empty");
            tvErrorExpSalary.requestFocus();
            return false;
        } else {
            tvErrorExpSalary.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidateNoticePeriod() {
        if (NoticePeroid.equals("")) {
            tvErrorNoticePeriod.setVisibility(View.VISIBLE);
            tvErrorNoticePeriod.setText("Notice Period Should Not Be Empty");
            tvErrorNoticePeriod.requestFocus();
            return false;
        } else {
            tvErrorNoticePeriod.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidateCompany() {
        if (companySearch.getText().toString().equals("Select company")) {
            tvErrorCompanyName.setVisibility(View.VISIBLE);
            tvErrorCompanyName.setText("Company Name Should Not Be Empty");
            tvErrorCompanyName.requestFocus();
            return false;
        } else {
            tvErrorCompanyName.setVisibility(View.GONE);
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

    private boolean ValidateFunctionalAre() {
        if (functionalAreaSearch.getText().toString().equals("Select functional area")) {
            tvEroorFunctionArea.setVisibility(View.VISIBLE);
            tvEroorFunctionArea.setText("Functional Area Should Not Be Empty");
            tvEroorFunctionArea.requestFocus();
            return false;
        } else {
            tvEroorFunctionArea.setVisibility(View.GONE);
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
    }

    private boolean ValidateMonthlySalary() {
        if (monthlySalary.getText().toString().equals("")) {
            monthlySalary.setError("Monthly Salary Should Not Be Empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidateJoinDate() {
        if (joiningDate.getText().toString().equals("")) {
            tvErrorJoinDate.setVisibility(View.VISIBLE);
            viewJoin.setVisibility(View.VISIBLE);
            tvErrorJoinDate.setText("Joining Date Should Not Be Empty");
            return false;
        } else {
            tvErrorJoinDate.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidateEducation() {
        if (!ValidataDegree()) {
            return false;
        } else if (!ValidataSpecialization()) {
            return false;
        } else if (!ValidataPassingYear()) {
            return false;
        } else if (!ValidataUniversityName()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidataDegree() {
        if (educationSearch.getText().toString().equals("Select degree")) {
            tvErrorDegree.setVisibility(View.VISIBLE);
            tvErrorDegree.setText("Degree Should Not Be Empty");
            tvErrorDegree.requestFocus();
            return false;
        } else {
            tvErrorDegree.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidataPassingYear() {
        int age = Integer.parseInt(passingYear.getText().toString());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        if (passingYear.getText().toString().equals("")) {
            passingYear.setError("Passing Year Should Not Be Empty");
            passingYear.requestFocus();
            return false;
        }
        if (passingYear.getText().toString().length() < 4) {
            passingYear.setError("Passing Year Must Be 4 Letters");
            passingYear.requestFocus();
            return false;

        } if (age < 1900 ) {
            passingYear.setError("Passing year should be greatr than 1900");
            passingYear.requestFocus();
            return false;
        }if (age > year ) {
            passingYear.setError("Passing Year Should be less than CURRENT YEAR");
            passingYear.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidataUniversityName() {
        if (universityName.getText().toString().equals("")) {
            universityName.setError("University Name Should Not Be Empty");
            universityName.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidataSpecialization() {
        if (specializationSearch.getText().toString().equals("Select specialization")) {
            tvErrorSpec.setVisibility(View.VISIBLE);
            tvErrorSpec.setText("Specialization Should Not Be Empty");
            tvErrorSpec.requestFocus();
            return false;
        } else {
            tvErrorSpec.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean Validate() {

        if (!ValidateFirstName()) {
            return false;
        } else if (!ValidteLastname()) {
            return false;
        } else if (!validateEmail()) {
            return false;
        } else if (!ValidteCountryCode()) {
            return false;
        } else if (!ValidteMobile()) {
            return false;
        } else if (!ValidateDob()) {
            return false;
        }/*else if (!ValidateCity()){
            return false;
        }*/ else if (!ValidteJobTitle()) {
            return false;
        } else if (!ValidteGender()) {
            return false;
        } else if (!ValidteMarital()) {
            return false;
        } else if (!ValidteNationality()) {
            return false;
        } else if (!ValidteCountry()) {
            return false;
        } else if (!ValidteState()) {
            return false;
        } else if (!ValidteCity()) {
            return false;
        } else if (!ValidteLoaction()) {
            return false;
        } else if (!ValidteLanguage()) {
            return false;
        } else if (!ValidteSkill()) {
            return false;
        } else if (!ValidteExperience()) {
            return false;
        } else if (!ValidteExperienceLevel()) {
            return false;
        } else if (!ValidtePermanentResidency()) {
            return false;
        } else if (!ValidteSummary()) {
            return false;
        } else {
            return true;
        }

    }

    private boolean ValidteSummary() {
        if (summary.getText().toString().trim().isEmpty()) {
            summary.setError("Summary Should Not Be Empty");
            summary.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidtePermanentResidency() {
        if (AuthId == null) {
            tvErrorPermanent.setVisibility(View.VISIBLE);
            tvErrorPermanent.setText("Work Authorization Should Not Be Empty");
            tvErrorPermanent.requestFocus();
            return false;
        } else {
            tvErrorPermanent.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidteExperienceLevel() {
        if (ExperienceLevel.equals("Select experience level")) {
            tvErrorExperienceLevel.setVisibility(View.VISIBLE);
            tvErrorExperienceLevel.setText("Experience Level Should Not Be Empty");
            tvErrorExperienceLevel.requestFocus();
            return false;
        } else {
            tvErrorExperienceLevel.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidteExperience() {
        if (experience.getText().toString().equals("")) {
            experience.setError("Experience Should Not Be Empty");
            experience.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidteSkill() {
        if (Utils.skillName.isEmpty()) {
            tvErrorSkill.setVisibility(View.VISIBLE);
            tvErrorSkill.setText("Please Select Skill");
            tvErrorSkill.requestFocus();
            return false;
        } else {
            tvErrorSkill.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidteLanguage() {
        if (Utils.languagesLists.isEmpty()) {
            tvErrorLanduage.setVisibility(View.VISIBLE);
            tvErrorLanduage.setText("Please Select Language");
            tvErrorLanduage.requestFocus();
            return false;
        } else {
            tvErrorLanduage.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidteLoaction() {
        if (Utils.preferredLocations.isEmpty()) {
            tvErrorPrerfearreLoacwtio.setVisibility(View.VISIBLE);
            tvErrorPrerfearreLoacwtio.setText("Please Select Preferred Locations");
            tvErrorPrerfearreLoacwtio.requestFocus();
            return false;
        } else {
            tvErrorPrerfearreLoacwtio.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidteCity() {
        if (citySearch.getText().toString().equals("Select city")) {
            tvErrorCity.setVisibility(View.VISIBLE);
            tvErrorCity.setText("Please Select Any One City");
            tvErrorCity.requestFocus();
            return false;
        } else {
            tvErrorCity.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidteState() {
        if (stateSearch.getText().toString().equals("Select state")) {
            tvErrorState.setVisibility(View.VISIBLE);
            tvErrorState.setText("Please Select Any One State");
            tvErrorState.requestFocus();
            return false;
        } else {
            tvErrorState.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidteCountry() {
        if (countrySearch.getText().toString().equals("Select Country")) {
            tvErrorCountry.setVisibility(View.VISIBLE);
            tvErrorCountry.setText("Please Select Any One Country");
            tvErrorCountry.requestFocus();
            return false;
        } else {
            tvErrorCountry.setVisibility(View.GONE);
            return true;
        }
    }

    private boolean ValidteNationality() {
        if (nationalitySearch.getText().toString().equals("Select nationality")) {
            tvErrorNationlity.setVisibility(View.VISIBLE);
            tvErrorNationlity.setText("Please Select Any One Nationality");
            tvErrorNationlity.requestFocus();
            return false;
        } else {
            tvErrorNationlity.setVisibility(View.GONE);
            return true;
        }

    }

    private boolean ValidteMarital() {
        if (MaritalStatus.equals(" ")) {
            tvErrorMarital.setVisibility(View.VISIBLE);
            tvErrorMarital.setText("Marital Status Should Not Be Empty");
            tvErrorMarital.requestFocus();
            return false;
        } else {
            tvErrorMarital.setVisibility(View.GONE);
            return true;
        }

    }

    private boolean ValidteGender() {
        if (Gender.equals("")) {
            tvGenderError.setVisibility(View.VISIBLE);
            tvGenderError.setText("Gender Should Not Be Empty");
            tvGenderError.requestFocus();
            return false;
        } else {
            tvGenderError.setVisibility(View.GONE);
            return true;
        }

    }

    private boolean ValidteJobTitle() {
        if (jobtitle.getText().toString().trim().isEmpty()) {
            jobtitle.setError("Job Title Should Not Be Empty");
            jobtitle.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidteCountryCode() {
        if (countryCode.getText().toString().trim().isEmpty()) {
            countryCode.setError("Country Code Should Not Be Empty");
            countryCode.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    private boolean ValidateCity() {


        if (CityId == null) {

            for (int i = 0; i < cityLists.size(); i++) {

                String City = citySearch.getText().toString().trim();

                if (!City.equalsIgnoreCase("Select City")) {
                    if (City.equalsIgnoreCase(cityLists.get(i).getCityName())) {

                        CityId = cityLists.get(i).getCityId();
                        return true;
                    }
                }


            }
            Utils.showAlertDialog(getActivity(), "City should not be empty", false);
            return false;

        } else {

            return true;

        }

    }

    private boolean ValidateDob() {

        if (dateofBirt.getText().toString().equalsIgnoreCase("Date of Birth")) {
            Utils.showAlertDialog(getActivity(), "Date of birth should not be empty", false);
            return false;
        } else {
            return true;
        }

    }

    private boolean ValidteMobile() {
        if (mobile.getText().toString().trim().equalsIgnoreCase("")) {
            mobile.setError("mobile number should not be empty");
            mobile.requestFocus();
            return false;
        } else if (mobile.getText().toString().trim().length() < 10) {
            mobile.setError("mobile number should not be less than 10 digits");
            mobile.requestFocus();
            return false;
        } else {
            return true;
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

    private boolean ValidteLastname() {
        if (lastName.getText().toString().trim().equalsIgnoreCase("")) {
            lastName.setError("Last name should not be empty");
            lastName.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    private boolean ValidateFirstName() {

        if (firstName.getText().toString().trim().equalsIgnoreCase("")) {
            firstName.setError("First name should not be empty");
            firstName.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    private void getPreferredLocations() {

        service.getService().GetAllCities(new Callback<GetAllCities>() {
            @Override
            public void success(GetAllCities getAllCities, Response response) {

                preferredLocationsList = getAllCities.getModel();


                if (getLocationStringArrays.size() > 0) {

                    String str = getLocationStringArrays.get(0).toString();
                    ArrayList aList = new ArrayList(Arrays.asList(str.split(",")));
                    List<AllCitiesList> citiesLists = new ArrayList<>();

                    for (int k = 0; k < aList.size(); k++) {
                        String filterdata = aList.get(k).toString().trim();
                        for (int j = 0; j < preferredLocationsList.size(); j++) {

                            String filterdata2 = (preferredLocationsList.get(j).getCityId() + "").trim();

                            if (filterdata2.equalsIgnoreCase(filterdata)) {

                                AllCitiesList list = new AllCitiesList();
                                list.setCityName(preferredLocationsList.get(j).getCityName());
                                list.setCityId(preferredLocationsList.get(j).getCityId());

                                citiesLists.add(list);

                            }

                        }
                    }
                    if (citiesLists.size() > 0) {

                        Utils.preferredLocations = citiesLists;
                        if (getActivity() != null) {
                            PreferredLocationsAdapter adapter = new PreferredLocationsAdapter(getActivity(), Utils.preferredLocations);
                            preferredLocationsListView.setAdapter(adapter);
                        }

                    }
                }


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getWorkAuthorization() {

        service.getService().GetAuthorization(new Callback<Authorization>() {
            @Override
            public void success(Authorization authorization, Response response) {

                authorizationLists = authorization.getModel();
                AuthorizationList list = new AuthorizationList();
                list.setAuthorizationName("Select work authorization");
                authorizationLists.add(list);
                AuthorizationList authorizationList = new AuthorizationList();
                if (!UserEmployeementStatus.isEmpty()) {

                    authorizationList.setAuthorizationName(UserEmployeementStatus);

                    for (int i = 0; i < authorizationLists.size(); i++) {
                        if (authorizationLists.get(i).getAuthorizationName().equalsIgnoreCase(UserEmployeementStatus)) {
                            authorizationList.setAuthId(authorizationLists.get(i).getAuthId());
                        }
                    }

                    authorizationLists.add(authorizationList);
                }


                Collections.reverse(authorizationLists);
                if (authorizationLists.size() > 0) {

                    SpinnerAdapterForWorkAuthorization adapterForWorkAuthorization = new SpinnerAdapterForWorkAuthorization(getActivity(), R.layout.custom_spinner, authorizationLists);
                    getWorkAuthorization.setAdapter(adapterForWorkAuthorization);
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void DeleteResume() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do You Want To Delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                service.getService().DeleteResume(JobseekerId, new Callback<JobApplyResponse>() {
                    @Override
                    public void success(JobApplyResponse jobApplyResponse, Response response) {

                        Utils.showAlertDialog(getActivity(), jobApplyResponse.getMessage(), false);
                        chooseFile.setText("Choose file");
                        deleteResume.setVisibility(View.GONE);
                        downloadResume.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Utils.showAlertDialog(getActivity(), "No File To Delete", false);
                    }
                });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void UploadResume() {

        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        ServiceApi services = retrofit.create(ServiceApi.class);

        if (files != null) {
            Pattern specailCharPatten = Pattern.compile("[$&+,:;=\\\\?@#|/'<>^*()%!]");
            String Fname = files.getName().trim();
            if (!specailCharPatten.matcher(Fname).find()) {
                //Resume();
                services.uploadResume(JobseekerId, body, reqId).enqueue(new retrofit2.Callback<ProfileImageResp>() {
                    @Override
                    public void onResponse(Call<ProfileImageResp> call, retrofit2.Response<ProfileImageResp> response) {
                        progressDialog.dismiss();
                        if (response.body() != null) {
                            if (response.body().getMessage() != null) {
                                Utils.showAlertDialog(getActivity(), response.body().getMessage(), false);
                                deleteResume.setVisibility(View.VISIBLE);
                                downloadResume.setVisibility(View.VISIBLE);
                                files = null;
                                GetBasicProfileData();
                            } else {
                                Utils.showAlertDialog(getActivity(), response.body().getErrorMessage(), false);
                            }

                        } else {
                            Utils.showAlertDialog(getActivity(), "File Extension Is InValid - Upload Only Doc/ Docx / PDF File", false);
                        }

                    }

                    @Override
                    public void onFailure(Call<ProfileImageResp> call, Throwable t) {
                        Utils.showAlertDialog(getActivity(), t.getMessage(), false);
                        progressDialog.dismiss();
                    }
                });


            } else {
                progressDialog.dismiss();
                Utils.showAlertDialog(getActivity(), "File name should not contain special characters", false);
            }

        } else {
            Utils.showAlertDialog(getActivity(), "Please select file !", false);
            progressDialog.dismiss();
        }
    }

    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }


    private void UpdateExperienceProfile() {

        UpdateExperience experiences = new UpdateExperience();
        experiences.setJobseekerId(Integer.parseInt(JobseekerId));
        if (ComanyId == 0) {
            ComanyId = null;
        }
        experiences.setCompanyId(ComanyId);
        experiences.setJoiningDate(joiningDate.getText().toString().trim());
        if (IndustryId == 0) {
            IndustryId = null;
        }
        experiences.setIndustryId(IndustryId);
        if (FunctionalId == 0) {
            FunctionalId = null;
        }
        experiences.setFunctionalAreaId(FunctionalId);
        if (DesignationId == 0) {
            DesignationId = null;
        }
        experiences.setDesignationId(DesignationId);
        if (iscurrentCompan.isChecked()) {
            experiences.setCurrentCompany(true);
        } else {
            experiences.setCurrentCompany(false);
        }

        experiences.setMonthlySalary(monthlySalary.getText().toString());

        experiences.setExpectedSalary(ExpectedSalaryInExp);

        experiences.setNoticePeriod(NoticePeroidInExp);

        service.getService().UpdateExperience(experiences, new Callback<JobApplyResponse>() {
            @Override
            public void success(JobApplyResponse jobApplyResponse, Response response) {

                if (jobApplyResponse.getMessage() != null) {

                    ComanyId = null;
                    IndustryId = null;
                    FunctionalId = null;
                    DesignationId = null;
                    iscurrentCompan.setChecked(false);
                    companySearch.setText("Select company");
                    industrySearch.setText("Select industry");
                    functionalAreaSearch.setText("Select functional area");
                    designationSearch.setText("Select designation");
                    joiningDate.setText("");
                    monthlySalary.setText("");



                    Utils.showAlertDialog(getActivity(), jobApplyResponse.getMessage(), false);
                    GetExperienceData();

                    editprofile.setVisible(true);
                    updateProfile.setVisible(false);
                    /*for (int i = 0; i < generalInfoLayout.getChildCount(); i++) {
                        child = generalInfoLayout.getChildAt(i);
                        child.setEnabled(false);
                    }*/

                    firstName.setEnabled(false);
                    lastName.setEnabled(false);
                    mobile.setEnabled(false);
                    countryCode.setEnabled(false);
                    jobtitle.setEnabled(false);
                    summary.setEnabled(false);
                    experience.setEnabled(false);

                    for (int i = 0; i < socialProfileLayout.getChildCount(); i++) {
                        child = socialProfileLayout.getChildAt(i);
                        child.setEnabled(false);
                    }


                    upDateGeneralInfoProfile.setVisibility(View.GONE);
                    cancelGeneralInfoProfile.setVisibility(View.GONE);

                    upDateEducationProfile.setVisibility(View.GONE);
                    cancelEducationProfile.setVisibility(View.GONE);

                    upDateExperienceProfile.setVisibility(View.GONE);
                    cancelExperienceProfile.setVisibility(View.GONE);

                    upDateSocialProfile.setVisibility(View.GONE);
                    cancelSocialProfile.setVisibility(View.GONE);

                    upDateCertificationProfile.setVisibility(View.GONE);
                    cancelSocialProfile.setVisibility(View.GONE);

                    educationChildLayout.setVisibility(View.GONE);
                    educationListView.setVisibility(View.VISIBLE);
                    EditexperienceLayout.setVisibility(View.GONE);
                    experienceListView.setVisibility(View.VISIBLE);

                    certificationchilditems.setVisibility(View.GONE);
                    certificationListview.setVisibility(View.VISIBLE);
                } else {
                    ComanyId = null;
                    IndustryId = null;
                    FunctionalId = null;
                    DesignationId = null;
                    iscurrentCompan.setChecked(false);
                    companySearch.setText("Select company");
                    industrySearch.setText("Select industry");
                    functionalAreaSearch.setText("Select functional area");
                    designationSearch.setText("Select designation");
                    joiningDate.setText("");
                    monthlySalary.setText("");
                    Utils.showAlertDialog(getActivity(), jobApplyResponse.getErrorMessage(), false);
                }


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void GetExperienceData() {

        dialog.show();
        service.getService().getExperiencePrifile(JobseekerId, new Callback<Experience>() {
            @Override
            public void success(Experience experience, Response response) {

                dialog.dismiss();
                List<ExperienceList> experienceLists = new ArrayList<>();
                experienceLists = experience.getModel();
                if (experienceLists.size() > 0) {
                    Collections.reverse(experienceLists);
                    if (experienceLists!=null){
                        ExperienceAdapter adapter = new ExperienceAdapter(getActivity(), experienceLists, Profile.this,
                                companiesList, industriesLists, FunctionalAreAlist, expectedsalaryArray, noticePeroidArray);
                        experienceListView.setAdapter(adapter);
                    }


                    monthlySalary.setText("");
                    joiningDate.setText("");
                    llNodataFound.setVisibility(View.GONE);
                }
                else {
                    llNodataFound.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"No Data Found",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();
            }
        });

    }

    private void UpdateSocialProfil() {

        SocialProfileList socialProfileList = new SocialProfileList();
        socialProfileList.setJobseekerId(Integer.parseInt(JobseekerId));
        socialProfileList.setFacebook(facebook.getText().toString());
        socialProfileList.setDribble(dribbleLink.getText().toString());
        socialProfileList.setGplus(gLink.getText().toString());
        socialProfileList.setInstagram(instagram.getText().toString());
        socialProfileList.setLinkedin(linkedIn.getText().toString());
        socialProfileList.setTwitter(twitter.getText().toString());
        service.getService().UpdateSocialProfile(socialProfileList, new Callback<JobApplyResponse>() {
            @Override
            public void success(JobApplyResponse jobApplyResponse, Response response) {

                if (jobApplyResponse.getMessage() != null) {
                    Utils.showAlertDialog(getActivity(), jobApplyResponse.getMessage(), false);
                    getSocilaProfile();
                    editprofile.setVisible(true);
                    updateProfile.setVisible(false);
                    /*for (int i = 0; i < generalInfoLayout.getChildCount(); i++) {
                        child = generalInfoLayout.getChildAt(i);
                        child.setEnabled(false);
                    }*/
                    firstName.setEnabled(false);
                    lastName.setEnabled(false);
                    mobile.setEnabled(false);
                    countryCode.setEnabled(false);
                    jobtitle.setEnabled(false);
                    summary.setEnabled(false);
                    experience.setEnabled(false);

                    for (int i = 0; i < socialProfileLayout.getChildCount(); i++) {
                        child = socialProfileLayout.getChildAt(i);
                        child.setEnabled(false);
                    }


                    upDateGeneralInfoProfile.setVisibility(View.GONE);
                    cancelGeneralInfoProfile.setVisibility(View.GONE);

                    upDateEducationProfile.setVisibility(View.GONE);
                    cancelEducationProfile.setVisibility(View.GONE);

                    upDateExperienceProfile.setVisibility(View.GONE);
                    cancelExperienceProfile.setVisibility(View.GONE);

                    upDateSocialProfile.setVisibility(View.GONE);
                    cancelSocialProfile.setVisibility(View.GONE);

                    upDateCertificationProfile.setVisibility(View.GONE);
                    cancelSocialProfile.setVisibility(View.GONE);

                    educationChildLayout.setVisibility(View.GONE);
                    educationListView.setVisibility(View.VISIBLE);
                    EditexperienceLayout.setVisibility(View.GONE);
                    experienceListView.setVisibility(View.VISIBLE);

                    certificationchilditems.setVisibility(View.GONE);
                    certificationListview.setVisibility(View.VISIBLE);
                } else {
                    Utils.showAlertDialog(getActivity(), jobApplyResponse.getErrorMessage(), false);
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    private void DatePickerDial(final String Validate) {

        final DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                view.setMaxDate(System.currentTimeMillis());
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                if (Validate.equalsIgnoreCase("Dob")) {
                    String myFormat = "yyyy-MM-dd";//"MM/dd/yy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    dateofBirt.setText(sdf.format(calendar.getTime()));
                }
                if (Validate.equalsIgnoreCase("Validity")) {
                    String myFormat = "yyyy-MM-dd";//"MM/dd/yy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    certificationValidity.setText(sdf.format(calendar.getTime()));
                }

//                if (Validate.equalsIgnoreCase("joiningDate")) {
//
//                    String myFormat = "MM-dd-yyyy";//"MM/dd/yy"; //In which you need put here
//                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//                    joiningDate.setText(sdf.format(calendar.getTime()));
//                }

            }
        };

        new DatePickerDialog(getActivity(), datePickerDialog, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void getCertificationById() {
        llNodataFound.setVisibility(View.GONE);

        dialog.show();
        service.getService().getCertificationPrifile(JobseekerId, new Callback<GetCertification>() {
            @Override
            public void success(GetCertification updateCertification, Response response) {

                dialog.dismiss();
                List<GetCertificationList> certificationLists = new ArrayList<>();


                certificationLists = updateCertification.getModel();
                Collections.reverse(certificationLists);
                if (certificationLists.size() > 0) {

                    CertificationAdapter adapter = new CertificationAdapter(getActivity(), certificationLists, Profile.this, JobseekerId);
                    certificationListview.setAdapter(adapter);

                }else {
                    llNodataFound.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"No Data Found..!",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();
            }
        });
    }

    private void UpdateCertification() {

        UpdateCertification certification = new UpdateCertification();
        certification.setJobSeekerId(Integer.parseInt(JobseekerId));
        certification.setCertificationName(certificationName.getText().toString());
        certification.setValidity(certificationValidity.getText().toString());
        certification.setLicenceNo(certificationLicence.getText().toString());
        certification.setInstituteName(certificationInstitute.getText().toString());
        service.getService().UpdateCertification(certification, new Callback<JobApplyResponse>() {
            @Override
            public void success(JobApplyResponse certificationResponse, Response response) {
                if (certificationResponse.getMessage() != null) {

                    getCertificationById();
                    Utils.showAlertDialog(getActivity(), certificationResponse.getMessage(), false);
                    editprofile.setVisible(true);
                    updateProfile.setVisible(false);
                    /*for (int i = 0; i < generalInfoLayout.getChildCount(); i++) {
                        child = generalInfoLayout.getChildAt(i);
                        child.setEnabled(false);
                    }*/
                    certificationName.setText("");
                    certificationValidity.setText("");
                    certificationLicence.setText("");
                    certificationInstitute.setText("");
                    firstName.setEnabled(false);
                    lastName.setEnabled(false);
                    mobile.setEnabled(false);
                    countryCode.setEnabled(false);
                    jobtitle.setEnabled(false);
                    summary.setEnabled(false);
                    experience.setEnabled(false);
                    for (int i = 0; i < socialProfileLayout.getChildCount(); i++) {
                        child = socialProfileLayout.getChildAt(i);
                        child.setEnabled(false);
                    }


                    upDateGeneralInfoProfile.setVisibility(View.GONE);
                    cancelGeneralInfoProfile.setVisibility(View.GONE);

                    upDateEducationProfile.setVisibility(View.GONE);
                    cancelEducationProfile.setVisibility(View.GONE);

                    upDateExperienceProfile.setVisibility(View.GONE);
                    cancelExperienceProfile.setVisibility(View.GONE);

                    upDateSocialProfile.setVisibility(View.GONE);
                    cancelSocialProfile.setVisibility(View.GONE);

                    upDateCertificationProfile.setVisibility(View.GONE);
                    cancelSocialProfile.setVisibility(View.GONE);

                    educationChildLayout.setVisibility(View.GONE);
                    educationListView.setVisibility(View.VISIBLE);
                    EditexperienceLayout.setVisibility(View.GONE);
                    experienceListView.setVisibility(View.VISIBLE);

                    certificationchilditems.setVisibility(View.GONE);
                    certificationListview.setVisibility(View.VISIBLE);
                } else {
                    certificationName.setText("");
                    certificationValidity.setText("");
                    certificationLicence.setText("");
                    certificationInstitute.setText("");
                    Utils.showAlertDialog(getActivity(), certificationResponse.getErrorMessage(), false);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getSocilaProfile() {
        llNodataFound.setVisibility(View.GONE);

        dialog.show();
        service.getService().getSocilaProfile(JobseekerId, new Callback<SocialProfile>() {
            @Override
            public void success(SocialProfile socialProfile, Response response) {

                dialog.dismiss();
                //Collections.reverse(socialProfile.getModel());
                if (socialProfile.getModel().size() > 0) {

                    facebook.setText(socialProfile.getModel().get(0).getFacebook());
                    twitter.setText(socialProfile.getModel().get(0).getTwitter());
                    instagram.setText(socialProfile.getModel().get(0).getInstagram());
                    dribbleLink.setText(socialProfile.getModel().get(0).getDribble());
                    gLink.setText(socialProfile.getModel().get(0).getGplus());
                    linkedIn.setText(socialProfile.getModel().get(0).getLinkedin());
                }

            }

            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();
            }
        });
    }

    public void GetEducationDetails() {
        llNodataFound.setVisibility(View.GONE);

        dialog.show();
        service.getService().getEducationDetails(JobseekerId, new Callback<GetEducationDetails>() {
            @Override
            public void success(GetEducationDetails getEducationDetails, Response response) {
                dialog.dismiss();
                List<EducationDetailsList> educationDetails = new ArrayList<>();
                educationDetails = getEducationDetails.getModel();

                Collections.reverse(educationDetails);

                if (educationDetails.size() > 0) {
                    educationDetailsAdapter = new EducationDetailsAdapter(getActivity(), educationDetails, educationLists, specializationLists, Profile.this);
                    educationListView.setAdapter(educationDetailsAdapter);
                }else {
                    llNodataFound.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"No Data Found..!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismiss();
            }
        });
    }

    private void UpdateEducationDetails() {

        UpdateEducation education = new UpdateEducation();
        education.setJobSeekerId(Integer.parseInt(JobseekerId));
        education.setEducationId(degreeId);
        education.setEducationTypeId(specializationId);
        education.setPassingYear(passingYear.getText().toString());
        education.setUniversityName(universityName.getText().toString());
        service.getService().UpdateEducation(education, new Callback<JobApplyResponse>() {
            @Override
            public void success(JobApplyResponse jobApplyResponse, Response response) {

                if (jobApplyResponse.getMessage() != null) {
                    degreeId = 0;
                    specializationId = 0;
                    passingYear.setText("");
                    universityName.setText("");
                    educationSearch.setText("Select degree");
                    specializationSearch.setText("Select specialization");
                    Utils.showAlertDialog(getActivity(), jobApplyResponse.getMessage(), false);
                    GetEducationDetails();
                    editprofile.setVisible(true);
                    updateProfile.setVisible(false);
                    /*for (int i = 0; i < generalInfoLayout.getChildCount(); i++) {
                        child = generalInfoLayout.getChildAt(i);
                        child.setEnabled(false);
                    }*/
                    firstName.setEnabled(false);
                    lastName.setEnabled(false);
                    mobile.setEnabled(false);
                    countryCode.setEnabled(false);
                    jobtitle.setEnabled(false);
                    summary.setEnabled(false);
                    experience.setEnabled(false);

                    for (int i = 0; i < socialProfileLayout.getChildCount(); i++) {
                        child = socialProfileLayout.getChildAt(i);
                        child.setEnabled(false);
                    }

                    upDateGeneralInfoProfile.setVisibility(View.GONE);
                    cancelGeneralInfoProfile.setVisibility(View.GONE);

                    upDateEducationProfile.setVisibility(View.GONE);
                    cancelEducationProfile.setVisibility(View.GONE);

                    upDateExperienceProfile.setVisibility(View.GONE);
                    cancelExperienceProfile.setVisibility(View.GONE);

                    upDateSocialProfile.setVisibility(View.GONE);
                    cancelSocialProfile.setVisibility(View.GONE);

                    upDateCertificationProfile.setVisibility(View.GONE);
                    cancelSocialProfile.setVisibility(View.GONE);

                    educationChildLayout.setVisibility(View.GONE);
                    educationListView.setVisibility(View.VISIBLE);
                    EditexperienceLayout.setVisibility(View.GONE);
                    experienceListView.setVisibility(View.VISIBLE);

                    certificationchilditems.setVisibility(View.GONE);
                    certificationListview.setVisibility(View.VISIBLE);

                } else {
                    degreeId = 0;
                    specializationId = 0;
                    passingYear.setText("");
                    universityName.setText("");
                    educationSearch.setText("Select degree");
                    specializationSearch.setText("Select specialization");

                    Utils.showAlertDialog(getActivity(), jobApplyResponse.getErrorMessage(), false);
//                    Toast.makeText(getActivity(), ""+jobApplyResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void UpdateBasicInfo() {
        dialog.show();
        UpdateBasicProfile basicProfile = new UpdateBasicProfile();
        basicProfile.setJobSeekerId(Integer.parseInt(JobseekerId));
        basicProfile.setFirstName(firstName.getText().toString().trim());
        basicProfile.setLastName(lastName.getText().toString().trim());
        basicProfile.setContact(mobile.getText().toString().trim());
        basicProfile.setEmailId(email.getText().toString().trim());
        basicProfile.setDateOfBirth(dateofBirt.getText().toString());
        basicProfile.setGender(Gender);
        basicProfile.setMaritalStatus(MaritalStatus);

       /* String CountryCode = countryCode.getText().toString().trim();
        CountryCode = CountryCode.replace("+", "");
        basicProfile.setCountryCode(CountryCode);*/
        basicProfile.setCountryCode(countryCode.getText().toString());
        if (nationalitySearch.getText().toString().trim().equalsIgnoreCase("Select nationality")) {
            basicProfile.setNationalityId(null);
        } else {
            basicProfile.setNationalityId(NationalityId);
        }


        if (CityId != null) {
            basicProfile.setCityId(CityId);
        } else {
            for (int i = 0; i < cityLists.size(); i++) {

                if (citySearch.getText().toString().equalsIgnoreCase(cityLists.get(i).getCityName())) {

                    CityId = cityLists.get(i).getCityId();
                    basicProfile.setCityId(CityId);
                } else {
                    CityId = null;
                }
            }

        }

        basicProfile.setAuthId(AuthId);
        basicProfile.setSummary(summary.getText().toString());
        basicProfile.setResumeTitle(jobtitle.getText().toString().trim());

        if (Utils.languagesLists.size() == 0) {
            Languages.add(0);
            basicProfile.setSelectedLangsArray(Languages);
        }
        if (Utils.preferredLocations.size() == 0) {
            preferedlocation.add(0);
            basicProfile.setSelectedLocArray(preferedlocation);
        }
        if (Utils.skillName.size() == 0) {
            Skills.add(0);
            basicProfile.setSelectedSkillsArray(Skills);
        }

        for (int j = 0; j < Utils.languagesLists.size(); j++) {
            Languages.add(Utils.languagesLists.get(j).getLanguageId());
            basicProfile.setSelectedLangsArray(Languages);
        }

        for (int j = 0; j < Utils.skillName.size(); j++) {
            Skills.add(Utils.skillName.get(j).getSkillsId());
            basicProfile.setSelectedSkillsArray(Skills);
        }


        for (int p = 0; p < Utils.preferredLocations.size(); p++) {
            preferedlocation.add(Utils.preferredLocations.get(p).getCityId());
            basicProfile.setSelectedLocArray(preferedlocation);
        }


        basicProfile.setExpLevel(ExpLevelId);
        String Exp = experience.getText().toString();
        Exp = Exp.replace(".00 Years", "");
        if (!Exp.isEmpty()) {
            basicProfile.setExp(Integer.parseInt(Exp));
        } else {
            basicProfile.setExp(null);
        }

        service.getService().getUpdateBasicProfile(JobseekerId, basicProfile, new Callback<UpdateProfileResponse>() {
            @Override
            public void success(UpdateProfileResponse updateProfileResponse, Response response) {

                dialog.dismiss();
                if (updateProfileResponse.getMessage() != null) {

                    Skills.clear();
                    Languages.clear();
                    preferedlocation.clear();

                    Utils.languagesLists.clear();
                    LanguagesAdapter adapter = new LanguagesAdapter(getActivity(), Utils.languagesLists);
                    languagesListview.setAdapter(adapter);

                    Utils.skillName.clear();
                    SkillsAdapter Sadapter = new SkillsAdapter(getActivity(), Utils.skillName);
                    skillsListview.setAdapter(Sadapter);

                    Utils.preferredLocations.clear();
                    PreferredLocationsAdapter adapter1 = new PreferredLocationsAdapter(getActivity(), Utils.preferredLocations);
                    preferredLocationsListView.setAdapter(adapter1);

                    GetBasicProfileData();
                    Utils.showAlertDialog(getActivity(), updateProfileResponse.getMessage(), false);

                } else {
                    Utils.showAlertDialog(getActivity(), updateProfileResponse.getErrorMessage(), false);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Utils.showAlertDialog(getActivity(), error.getMessage(), false);
                dialog.dismiss();
            }
        });

    }

    private void getCitys(int stateId) {

        service.getService().getCities(stateId, new Callback<City>() {
            @Override
            public void success(City companies, Response response) {

                CityList select = new CityList();
                select.setCityName("Select City");
                select.setCityId(null);
                cityLists = companies.getModel();

                cityLists.add(select);

                Collections.reverse(cityLists);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getState(int countryId) {

        service.getService().getStates(countryId, new Callback<States>() {
            @Override
            public void success(States companies, Response response) {


                StatesList select = new StatesList();
                select.setStateName("Select states");
                statesLists = companies.getModel();
                statesLists.add(select);
                if (UserState != null) {

                    if (!UserState.isEmpty()) {

                        StatesList list = new StatesList();
                        for (int i = 0; i < statesLists.size(); i++) {

                            if (UserState.equalsIgnoreCase(statesLists.get(i).getStateName())) {
                                getCitys(statesLists.get(i).getStateId());
                            }
                        }


                    }

                }

                Collections.reverse(statesLists);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getCountrys() {

        service.getService().getCountries(new Callback<Country>() {
            @Override
            public void success(Country companies, Response response) {

                CountryList select = new CountryList();
                select.setCountryName("Select country");

                countryLists = companies.getModel();
                countryLists.add(select);

                if (UserCountry != null) {

                    if (!UserCountry.isEmpty()) {

                        CountryList list = new CountryList();
                        for (int i = 0; i < countryLists.size(); i++) {

                            if (UserCountry.equalsIgnoreCase(countryLists.get(i).getCountryName())) {
                                // list.setCountryId(countryLists.get(i).getCountryId());
                                getState(countryLists.get(i).getCountryId());
                            }
                        }


                    }

                }

                Collections.reverse(countryLists);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getSpecilization(int educationId) {

        service.getService().getSpecialization(educationId, new Callback<Specialization>() {
            @Override
            public void success(Specialization companies, Response response) {

                specializationLists = companies.getModel();

                specializationSearch.setText("Select specialization");


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    public void getEducation() {

        service.getService().getEducation(new Callback<Education>() {
            @Override
            public void success(Education companies, Response response) {

                educationLists = companies.getModel();

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getSkills() {

        service.getService().getSkills(new Callback<Skills>() {
            @Override
            public void success(Skills companies, Response response) {


                skillsList = new ArrayList<>();
                skillsList = companies.getModel();

                if (getSkillsStringArrays.size() > 0) {

                    String str = getSkillsStringArrays.get(0).toString();
                    ArrayList aList = new ArrayList(Arrays.asList(str.split(",")));
                    List<SkillsList> skillsLi = new ArrayList<>();

                    for (int i = 0; i < aList.size(); i++) {

                        String filter1 = aList.get(i).toString().trim();
                        for (int j = 0; j < skillsList.size(); j++) {

                            String filter2 = (skillsList.get(j).getSkillsId() + "").trim();
                            if (filter2.equalsIgnoreCase(filter1)) {

                                SkillsList list = new SkillsList();
                                list.setSkillsName(skillsList.get(j).getSkillsName());
                                list.setSkillsId(skillsList.get(j).getSkillsId());
                                skillsLi.add(list);

                            }
                        }

                    }
                    if (skillsLi.size() > 0) {

                        Utils.skillName = skillsLi;
                        SkillsAdapter adapter = new SkillsAdapter(getActivity(), Utils.skillName);
                        skillsListview.setAdapter(adapter);
                    }
                }


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getDesignations(int functionalAreaId) {
        service.getService().getDesignation(functionalAreaId, new Callback<Designations>() {
            @Override
            public void success(Designations disig, Response response) {

                DesignationsList select = new DesignationsList();
                select.setDesignationName("Select Designation");

                designationsLists = disig.getModel();
                designationsLists.add(select);
                Collections.reverse(designationsLists);


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void getFunctionalAreas() {
        service.getService().getFunctionalArea(new Callback<FunctionalAreas>() {
            @Override
            public void success(FunctionalAreas functionalAreas, Response response) {

                FunctionalAreasList select = new FunctionalAreasList();
                select.setFunctionalAreaName("Select functional");

                FunctionalAreAlist = functionalAreas.getModel();

                FunctionalAreAlist.add(select);
                Collections.reverse(FunctionalAreAlist);


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getIndustries() {
        service.getService().getIndustries(new Callback<Industries>() {
            @Override
            public void success(Industries companies, Response response) {

                IndustriesList select = new IndustriesList();
                select.setIndustryName("Select industry");

                industriesLists = companies.getModel();
                industriesLists.add(select);
                Collections.reverse(industriesLists);


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getCompanies() {

        service.getService().getCompanies(new Callback<Companies>() {
            @Override
            public void success(Companies companies, Response response) {

                CompaniesList select = new CompaniesList();
                select.setCompanyName("Select company");

                companiesList = companies.getModel();
                companiesList.add(select);
                Collections.reverse(companiesList);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void getLanguages() {

        service.getService().getLanguages(new Callback<Languages>() {
            @Override
            public void success(Languages languages, Response response) {
                languagesLists = new ArrayList<>();

                languagesLists = languages.getModel();

                if (getLanguagesStringArrays.size() > 0) {

                    String str = getLanguagesStringArrays.get(0).toString();
                    ArrayList aList = new ArrayList(Arrays.asList(str.split(",")));
                    List<LanguagesList> languagesLi = new ArrayList<>();


                    for (int k = 0; k < aList.size(); k++) {

                        String filter1 = aList.get(k).toString().trim();

                        for (int j = 0; j < languagesLists.size(); j++) {

                            String filter2 = (languagesLists.get(j).getLanguageId() + "").trim();

                            if (filter2.equalsIgnoreCase(filter1)) {

                                LanguagesList list = new LanguagesList();
                                list.setLanguageName(languagesLists.get(j).getLanguageName());
                                list.setLanguageId(languagesLists.get(j).getLanguageId());
                                languagesLi.add(list);

                            }
                        }
                    }
                    if (languagesLi.size() > 0) {

                        Utils.languagesLists = languagesLi;
                        LanguagesAdapter adapter = new LanguagesAdapter(getActivity(), Utils.languagesLists);
                        languagesListview.setAdapter(adapter);
                    }
                }


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void LoadNationalities() {
        service.getService().getNationalities(new Callback<Nationalities>() {
            @Override
            public void success(Nationalities nationalities, Response response) {

                nationalitiesLists = nationalities.getModel();

                NationalitiesList list = new NationalitiesList();
                list.setNationalityName("Select nationality");
                list.setNationalityId(null);
                nationalitiesLists.add(list);

                Collections.reverse(nationalitiesLists);

                if (UserNationality != null) {

                    if (!UserNationality.isEmpty()) {

                        for (int i = 0; i < nationalitiesLists.size(); i++) {

                            if (UserNationality.trim().equalsIgnoreCase(nationalitiesLists.get(i).getNationalityName())) {
                                nationalitySearch.setText(UserNationality);
                                NationalityId = nationalitiesLists.get(i).getNationalityId();
                            }

                        }


                    }
                }


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void GetBasicProfileData() {
        llNodataFound.setVisibility(View.GONE);

        dialog.show();

        service.getService().getProfile(JobseekerId, new Callback<ProfileResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void success(ProfileResponse profileResponse, Response response) {


                ProfileData data = profileResponse.getModel();

                if (data != null) {
                    genderArray.clear();
                    genderArray.add("Select gender");
                    genderArray.add("Male");
                    genderArray.add("Female");
                    genderArray.add("Others");

                    if (!data.getGender().isEmpty()) {

                        genderArray.add(0, data.getGender());

                    }
                    ArrayAdapter<String> Gadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, genderArray);

                    Gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    getGender.setAdapter(Gadapter);

                    maritalStatusArray.clear();
                    maritalStatusArray.add("Select marital status");
                    maritalStatusArray.add("Single");
                    maritalStatusArray.add("Married");
                    maritalStatusArray.add("Divorce");
                    maritalStatusArray.add("Widowed");
                    if (!data.getMaritalStatus().isEmpty()) {

                        maritalStatusArray.add(0, data.getMaritalStatus());
                    }
                    ArrayAdapter<String> Madapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, maritalStatusArray);

                    Madapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    getMaritalStatus.setAdapter(Madapter);


                    if (!data.getFirstName().isEmpty()) {

                        spEditor.putString("userProfileName", data.getFirstName());
                        spEditor.apply();
                        String ProfileName = preferences.getString("userProfileName", "");
                        firstName.setText(ProfileName);
                    } else {

                        preferences.edit().remove("userProfileName").commit();
                    }
                    if (!data.getLastName().isEmpty()) {
                        lastName.setText(data.getLastName());
                    }
                    if (data.getEmailId() != null) {
                        email.setText(data.getEmailId());
                    }
                    if (!data.getContact().isEmpty()) {
                        mobile.setText(data.getContact());
                    }
                    if (!data.getResumeTitle().isEmpty()) {
                        jobtitle.setText(data.getResumeTitle());
                    }

                    if (!data.getDateOfBirth().isEmpty()) {

                        String regex = "12:00:00 AM";
                        String content = data.getDateOfBirth();
                        content = content.replaceAll(regex, "");
                        dateofBirt.setText(content);
                    }
                    if (!data.getNationality().isEmpty()) {

                        UserNationality = data.getNationality();
                        nationalitySearch.setText(UserNationality);


                    } else {
                        UserNationality = "";
                        LoadNationalities();
                    }


                    if (data.getEmploymentStatus() != null) {

                        if (!data.getEmploymentStatus().isEmpty()) {


                            UserEmployeementStatus = data.getEmploymentStatus();

                        } else {

                            UserEmployeementStatus = "";
                            getWorkAuthorization();


                        }
                    }
                    if (data.getCountryCode() != null) {

                        if (!data.getCountryCode().isEmpty()) {
                            countryCode.setText(data.getCountryCode());
                        }
                    }


                    if (!data.getCountry().equals("")) {
                        UserCountry = data.getCountry();
                        countrySearch.setText(UserCountry);

                    } else {
                        countrySearch.setText("Select Country");
                        UserCountry = "";
                        getCountrys();
                    }
                    if (!data.getState().isEmpty()) {
                        UserState = data.getState();
                        stateSearch.setText(UserState);

                    } else {
                        UserState = "";

                    }
                    if (!data.getCity().isEmpty()) {

                        UserCity = data.getCity();
                        citySearch.setText(UserCity);
                    } else {
                        UserCity = "";
                        citySearch.setText("Select city");
                    }
                    if (!data.getSelectedLocArray().isEmpty()) {

                        getLocationStringArrays.clear();
                        if (!data.getSelectedLocArray().get(0).isEmpty()) {

                            for (int i = 0; i < data.getSelectedLocArray().size(); i++) {
                                getLocationStringArrays.add(data.getSelectedLocArray().get(i).toString());
                            }
                        } else {

                            getPreferredLocations();
                        }


                        //preferredLocations.setText(data.getSelectedLocArray().toString().replace("[", "").replace("]", ""));
                    }
                    if (!data.getSelectedLangsArray().isEmpty()) {
                        getLanguagesStringArrays.clear();
                        if (!data.getSelectedLangsArray().get(0).isEmpty()) {

                            for (int i = 0; i < data.getSelectedLangsArray().size(); i++) {
                                getLanguagesStringArrays.add(data.getSelectedLangsArray().get(i).toString());
                            }
                        } else {

                            getLanguages();
                        }


                    }
                    if (!data.getSelectedSkillsArray().isEmpty()) {

                        getSkillsStringArrays.clear();
                        if (!data.getSelectedSkillsArray().get(0).isEmpty()) {

                            for (int i = 0; i < data.getSelectedSkillsArray().size(); i++) {
                                getSkillsStringArrays.add(data.getSelectedSkillsArray().get(i).toString());
                            }
                        } else {

                            getSkills();
                        }


                    }
                    if (!data.getExp().isEmpty()) {
                        String exp = data.getExp();
                        experience.setText(exp);
                    } else {
                        experience.setText("");
                    }

                    experienceLevelArray.clear();
                    experienceLevelArray.add("Select experience level");
                    experienceLevelArray.add("Less than 1 Year");
                    experienceLevelArray.add("1-2 Years");
                    experienceLevelArray.add("3-5 Years");
                    experienceLevelArray.add("6-10 Years");
                    experienceLevelArray.add("More than 10 Years");
                    if (!data.getExpLevel().isEmpty()) {
                        experienceLevelArray.add(0, data.getExpLevel());
                    }
                    ArrayAdapter<String> ELadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, experienceLevelArray);
                    ELadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    getExperienceLevel.setAdapter(ELadapter);
                    if (!data.getSummary().isEmpty()) {
                        summary.setText(data.getSummary());
                    } else {
                        summary.setText("");
                    }

                    if (!data.getPhoto().isEmpty()) {

                        String Image = data.getPhoto();
                        Image = Image.replace(" ", "%20");
                        String imageUri;
                        if (BuildConfig.BASE_URL.equalsIgnoreCase("http://testitecywebapi.azurewebsites.net/")) {
                            imageUri = "https://testitecy.azurewebsites.net/images/upload/" + Image;
                        } else {
                            imageUri = "https://www.itecy.com/images/upload/" + Image;
                        }
                        spEditor.putString("userProfileImage", imageUri);
                        spEditor.apply();
                        String UserImage = preferences.getString("userProfileImage", "");
                        Picasso.with(getActivity()).load(UserImage).noPlaceholder().centerCrop().fit()
                                .into((userProfile));
                    } else {
                        preferences.edit().remove("userProfileImage").commit();

                    }

                    if (!data.getCoverImage().isEmpty()) {
                        String imageUri;
                        if (BuildConfig.BASE_URL.equalsIgnoreCase("http://testitecywebapi.azurewebsites.net/")) {
                            imageUri = "https://testitecy.azurewebsites.net/images/upload/" + data.getCoverImage();
                        } else {
                            imageUri = "https://www.itecy.com/images/upload/" + data.getCoverImage();
                        }
                        Picasso.with(getActivity()).load(imageUri).into((coverPhoto));
                    }
                    if (!data.getResume().isEmpty()) {
                        DownloadResume = data.getResume();
                        chooseFile.setText(data.getResume());
                        deleteResume.setVisibility(View.VISIBLE);
                        downloadResume.setVisibility(View.VISIBLE);
                    } else {
                        deleteResume.setVisibility(View.GONE);
                        downloadResume.setVisibility(View.GONE);
                    }

                }

                LoadNationalities();

                getCountrys();

                getPreferredLocations();

                getLanguages();

                getSkills();

                getWorkAuthorization();
                dialog.dismiss();

            }

            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();

                LoadNationalities();

                getCountrys();

                getPreferredLocations();

                getLanguages();

                getSkills();

                getWorkAuthorization();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.editprofile, menu);
        menuu = menu;
        editprofile = menuu.findItem(R.id.editProfile);
        updateProfile = menuu.findItem(R.id.updateProfile);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editProfile:
                i=0;
                llNodataFound.setVisibility(View.GONE);
                updateProfile.setVisible(true);
                editprofile.setVisible(false);
                for (int i = 0; i < socialProfileLayout.getChildCount(); i++) {
                    child = socialProfileLayout.getChildAt(i);
                    child.setEnabled(true);
                }
                firstName.setEnabled(true);
                lastName.setEnabled(true);
                mobile.setEnabled(true);
                countryCode.setEnabled(true);
                jobtitle.setEnabled(true);
                summary.setEnabled(true);
                experience.setEnabled(true);
                getGender.setEnabled(true);
                getGender.setClickable(true);
                getMaritalStatus.setEnabled(true);
                getMaritalStatus.setClickable(true);
                nationalitySearch.setClickable(true);
                nationalitySearch.setEnabled(true);
                countrySearch.setEnabled(true);
                countrySearch.setClickable(true);
                stateSearch.setClickable(true);
                stateSearch.setEnabled(true);
                citySearch.setEnabled(true);
                citySearch.setClickable(true);
                preferredLocationSearch.setClickable(true);
                preferredLocationSearch.setEnabled(true);
                languagesSearch.setEnabled(true);
                languagesSearch.setClickable(true);
                skillsSearch.setClickable(true);
                skillsSearch.setEnabled(true);
                preferredLocationsLayout.setClickable(true);
                preferredLocationsLayout.setEnabled(true);
                languagesLayout.setEnabled(true);
                languagesLayout.setClickable(true);
                skillsLayout.setClickable(true);
                skillsLayout.setEnabled(true);
                getExperienceLevel.setClickable(true);
                getExperienceLevel.setEnabled(true);
                getWorkAuthorization.setEnabled(true);
                getWorkAuthorization.setClickable(true);
                dateofBirt.setEnabled(true);
                dateofBirt.setClickable(true);
                upDateCertificationProfile.setClickable(true);
                upDateCertificationProfile.setEnabled(true);

                upDateGeneralInfoProfile.setVisibility(View.VISIBLE);
                cancelGeneralInfoProfile.setVisibility(View.VISIBLE);

                upDateEducationProfile.setVisibility(View.VISIBLE);
                cancelEducationProfile.setVisibility(View.VISIBLE);

                upDateExperienceProfile.setVisibility(View.VISIBLE);
                cancelExperienceProfile.setVisibility(View.VISIBLE);

                upDateSocialProfile.setVisibility(View.VISIBLE);
                cancelSocialProfile.setVisibility(View.VISIBLE);

                upDateCertificationProfile.setVisibility(View.VISIBLE);
                cancelCertificationProfile.setVisibility(View.VISIBLE);


                certificationchilditems.setVisibility(View.VISIBLE);
                certificationListview.setVisibility(View.GONE);

                educationChildLayout.setVisibility(View.VISIBLE);
                educationListView.setVisibility(View.GONE);

                EditexperienceLayout.setVisibility(View.VISIBLE);
                experienceListView.setVisibility(View.GONE);


                break;
            case R.id.updateProfile:
                editprofile.setVisible(true);
                updateProfile.setVisible(false);
                /*for (int i = 0; i < generalInfoLayout.getChildCount(); i++) {
                    child = generalInfoLayout.getChildAt(i);
                    child.setEnabled(false);
                }*/

                firstName.setEnabled(false);
                lastName.setEnabled(false);
                mobile.setEnabled(false);
                countryCode.setEnabled(false);
                jobtitle.setEnabled(false);
                summary.setEnabled(false);
                experience.setEnabled(false);
                getGender.setEnabled(false);
                getGender.setClickable(false);
                getMaritalStatus.setEnabled(false);
                getMaritalStatus.setClickable(false);
                nationalitySearch.setClickable(false);
                nationalitySearch.setEnabled(false);
                countrySearch.setEnabled(false);
                countrySearch.setClickable(false);
                stateSearch.setClickable(false);
                stateSearch.setEnabled(false);
                citySearch.setEnabled(false);
                citySearch.setClickable(false);
                preferredLocationSearch.setClickable(false);
                preferredLocationSearch.setEnabled(false);
                languagesSearch.setEnabled(false);
                languagesSearch.setClickable(false);
                skillsSearch.setClickable(false);
                skillsSearch.setEnabled(false);
                preferredLocationsLayout.setClickable(false);
                preferredLocationsLayout.setEnabled(false);
                languagesLayout.setEnabled(false);
                languagesLayout.setClickable(false);
                skillsLayout.setClickable(false);
                skillsLayout.setEnabled(false);
                getExperienceLevel.setClickable(false);
                getExperienceLevel.setEnabled(false);
                getWorkAuthorization.setEnabled(false);
                getWorkAuthorization.setClickable(false);
                dateofBirt.setEnabled(false);
                dateofBirt.setClickable(false);
                upDateCertificationProfile.setClickable(false);
                upDateCertificationProfile.setEnabled(false);


                for (int i = 0; i < socialProfileLayout.getChildCount(); i++) {
                    child = socialProfileLayout.getChildAt(i);
                    child.setEnabled(false);
                }


                upDateGeneralInfoProfile.setVisibility(View.GONE);
                cancelGeneralInfoProfile.setVisibility(View.GONE);

                upDateEducationProfile.setVisibility(View.GONE);
                cancelEducationProfile.setVisibility(View.GONE);

                upDateExperienceProfile.setVisibility(View.GONE);
                cancelExperienceProfile.setVisibility(View.GONE);

                upDateSocialProfile.setVisibility(View.GONE);
                cancelSocialProfile.setVisibility(View.GONE);

                upDateCertificationProfile.setVisibility(View.GONE);
                cancelSocialProfile.setVisibility(View.GONE);

                educationChildLayout.setVisibility(View.GONE);
                educationListView.setVisibility(View.VISIBLE);
                EditexperienceLayout.setVisibility(View.GONE);
                experienceListView.setVisibility(View.VISIBLE);

                certificationchilditems.setVisibility(View.GONE);
                certificationListview.setVisibility(View.VISIBLE);


                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            progressDialog.setMessage("Uploading...");
            progressDialog.show();
            final Uri selectedImageUri = data.getData();
            //Log.e("SelectedImageUri", selectedImageUri.toString());
            imagepath = getPath(getActivity(), selectedImageUri);
            //Log.e("image path is", imagepath);
            File files = new File(imagepath);

            if (files != null) {
                long Length = imagepath.length();

                Length = Length / 1024;

                if (Length < 2) {


                    Retrofit retrofit = new Retrofit.Builder().
                            baseUrl(BuildConfig.BASE_URL).
                            addConverterFactory(GsonConverterFactory.create()).
                            build();
                    ServiceApi services = retrofit.create(ServiceApi.class);


                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), files);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("uploadedFile", files.getName(), reqFile);
                    RequestBody reqId = RequestBody.create(MediaType.parse("text/plain"), JobseekerId);

                    services.postImage(JobseekerId, body, reqId).enqueue(new retrofit2.Callback<ProfileImageResp>() {
                        @Override
                        public void onResponse(Call<ProfileImageResp> call, retrofit2.Response<ProfileImageResp> response) {
                            progressDialog.dismiss();
                            if (response.body() != null) {
                                if (response.body().getMessage() != null) {
                                    if (!response.body().getMessage().equalsIgnoreCase("File size Should Be UpTo 2000KB")) {
                                        userProfile.setImageURI(selectedImageUri);
                                        Utils.showAlertDialog(getActivity(), response.body().getMessage(), false);
                                        GetBasicProfileData();
                                    } else {
                                        Utils.showAlertDialog(getActivity(), response.body().getMessage(), false);
                                    }
                                } else {
                                    Utils.showAlertDialog(getActivity(), response.body().getErrorMessage(), false);
                                }

                            } else {


                                Utils.showAlertDialog(getActivity(), response.message(), false);
                            }

                        }

                        @Override
                        public void onFailure(Call<ProfileImageResp> call, Throwable t) {
                            Utils.showAlertDialog(getActivity(), "Net work problem! Please try again", false);
                            progressDialog.dismiss();
                        }
                    });
                } else {

                    Utils.showAlertDialog(getActivity(), "Image size should not be more than 2Mb", false);
                }

            }


        }
        if (requestCode == 2 && resultCode == RESULT_OK &&  data != null) {

            progressDialog.setMessage("Uploading...");
            progressDialog.show();
            final Uri selectedImageUri = data.getData();
            // Log.e("SelectedImageUri", selectedImageUri.toString());
            imagepath = getPath(getActivity(), selectedImageUri);
            //Log.e("image path is", imagepath);

            long lenght = imagepath.length();

            if (lenght / 1024 < 2) {

                //

                Retrofit retrofit = new Retrofit.Builder().
                        baseUrl(BuildConfig.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).
                        build();
                ServiceApi services = retrofit.create(ServiceApi.class);

                File files = new File(imagepath);
                if (files != null) {
                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), files);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("uploadedFile", files.getName(), reqFile);
                    RequestBody reqId = RequestBody.create(MediaType.parse("text/plain"), JobseekerId);

                    services.postCoverImage(JobseekerId, body, reqId).enqueue(new retrofit2.Callback<ProfileImageResp>() {
                        @Override
                        public void onResponse(Call<ProfileImageResp> call, retrofit2.Response<ProfileImageResp> response) {
                            progressDialog.dismiss();
                            if (response.body() != null) {
                                if (response.body().getMessage() != null) {

                                    if (!response.body().getMessage().equalsIgnoreCase("File size Should Be UpTo 2000KB")) {
                                        coverPhoto.setImageURI(selectedImageUri);
                                        Utils.showAlertDialog(getActivity(), response.body().getMessage(), false);
                                    } else {
                                        Utils.showAlertDialog(getActivity(), response.body().getMessage(), false);
                                    }

                                } else {
                                    Utils.showAlertDialog(getActivity(), response.body().getErrorMessage(), false);
                                }

                            } else {
                                Utils.showAlertDialog(getActivity(), response.message(), false);
                            }

                        }

                        @Override
                        public void onFailure(Call<ProfileImageResp> call, Throwable t) {
                            Utils.showAlertDialog(getActivity(), "Net work problem! Please try again", false);
                            progressDialog.dismiss();
                        }
                    });
                }


            } else {
                Utils.showAlertDialog(getActivity(), "Image size should not be more than 2Mb", false);
            }


        }

        if (requestCode == REQ_PDF && resultCode == RESULT_OK  &&  data != null) {
            InputStream inputStream=null;
            String encodedCode;

            Uri selectedImageUri = data.getData();

            try {
                inputStream = getContext().getContentResolver().openInputStream(selectedImageUri);
                byte[] pdfInBytes = new byte[inputStream.available()];
                inputStream.read(pdfInBytes);
                // Toast.makeText(getContext(), ""+value, Toast.LENGTH_SHORT).show();
                encodedCode = Base64.encodeToString(pdfInBytes, Base64.DEFAULT);
                if (selectedImageUri!=null)
                    imagepath = PathUtil.getPath(getActivity(), selectedImageUri);
//                    imagepath = FilePath.getPath(getActivity(), selectedImageUri);
                if (imagepath == null) {
                    Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                } else {

                    files = new File(imagepath);
                    file_size = Integer.parseInt(String.valueOf(files.length() / 1024));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Toast.makeText(getContext(), ""+value, Toast.LENGTH_SHORT).show();

            if (files != null){
                if (!imagepath.isEmpty()) {
//            files = new File(contentUri.getPath());

                    files = new File(imagepath);
                    reqFile = RequestBody.create(MediaType.parse("*/*"), files);
                    body = MultipartBody.Part.createFormData("file", files.getName(), reqFile);
                    reqId = RequestBody.create(MediaType.parse("text/plain"), JobseekerId);
                    chooseFile.setText(files.getName());

                }
            }

        }


    }

    private void raiseRequest() throws URISyntaxException {


//        purpose = et_purpose.getText().toString().trim();

        if (file_size != 0) {
            if (file_size > 2000) {
                uploadResume.requestFocus();
                Utils.showAlertDialog(getContext(), "Alert!", "File size should not more than 2MB", true);
                return;
            }

        }
        if (files == null) {
            Utils.showAlertDialog(getContext(), "Alert!", "Please Upload a File", true);
            return;
        }
        UploadResume();

    }


    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            userProfile.setImageBitmap(bitmap);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    private static int fixOrientation(Bitmap bitmap) {
        if (bitmap.getWidth() > bitmap.getHeight()) {
            return 90;
        }
        return 0;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getView() == null) {
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
}
