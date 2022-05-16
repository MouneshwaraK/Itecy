package com.cstech.itecy;

import com.cstech.itecy.ModelClasess.AddDegree;
import com.cstech.itecy.ModelClasess.AddSpecialization;
import com.cstech.itecy.ModelClasess.AppliedJobs;
import com.cstech.itecy.ModelClasess.Authorization;
import com.cstech.itecy.ModelClasess.ChangePassword;
import com.cstech.itecy.ModelClasess.City;
import com.cstech.itecy.ModelClasess.Companies;
import com.cstech.itecy.ModelClasess.Country;
import com.cstech.itecy.ModelClasess.Designations;
import com.cstech.itecy.ModelClasess.Education;
import com.cstech.itecy.ModelClasess.Experience;
import com.cstech.itecy.ModelClasess.ForgotModle;
import com.cstech.itecy.ModelClasess.ForgotPasswordResponse;
import com.cstech.itecy.ModelClasess.FunctionalAreas;
import com.cstech.itecy.ModelClasess.GetAllCities;
import com.cstech.itecy.ModelClasess.GetCertification;
import com.cstech.itecy.ModelClasess.GetCertificationList;
import com.cstech.itecy.ModelClasess.GetEducationDetails;
import com.cstech.itecy.ModelClasess.Industries;
import com.cstech.itecy.ModelClasess.JobApply;
import com.cstech.itecy.ModelClasess.JobApplyResponse;
import com.cstech.itecy.ModelClasess.JobDetails;
import com.cstech.itecy.ModelClasess.JobsData;
import com.cstech.itecy.ModelClasess.Languages;
import com.cstech.itecy.ModelClasess.LoginData;
import com.cstech.itecy.ModelClasess.Nationalities;
import com.cstech.itecy.ModelClasess.PasswordResponce;
import com.cstech.itecy.ModelClasess.ProfileFile;
import com.cstech.itecy.ModelClasess.ProfileImageResp;
import com.cstech.itecy.ModelClasess.ProfileResponse;
import com.cstech.itecy.ModelClasess.RegistrationData;
import com.cstech.itecy.ModelClasess.RegistrationResponse;
import com.cstech.itecy.ModelClasess.Skills;
import com.cstech.itecy.ModelClasess.SocialProfile;
import com.cstech.itecy.ModelClasess.SocialProfileList;
import com.cstech.itecy.ModelClasess.Specialization;
import com.cstech.itecy.ModelClasess.States;
import com.cstech.itecy.ModelClasess.UpdateBasicProfile;
import com.cstech.itecy.ModelClasess.UpdateCertification;
import com.cstech.itecy.ModelClasess.UpdateEducation;
import com.cstech.itecy.ModelClasess.UpdateEducationById;
import com.cstech.itecy.ModelClasess.UpdateExperience;
import com.cstech.itecy.ModelClasess.UpdateExperienceById;
import com.cstech.itecy.ModelClasess.UpdateProfileResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import java.io.File;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

public interface Webservice {

    @GET("/Jobseeker/Joblist")
    void getJobs(Callback<JobsData> jobsDataCallback);

    //https://itecywebapi.azurewebsites.net/Jobseeker/Login/{username}/{password}
    @GET("/Jobseeker/Login/{username}/{password}")
    void getLogin(@Path("username") String username, @Path("password") String password, Callback<LoginData> loginDataCallback);

    @GET("/Jobseeker/JobDetails/{JobId}")
    void getJobDetails(@Path("JobId") String JobId, Callback<JobDetails> jobDetailsCallback);

    @GET("/Jobseeker/getMyProfile/{jobseekerId}")
    void getProfile(@Path("jobseekerId") String jobSeekerId, Callback<ProfileResponse> responseCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/MyJobs/{jobseekerId }
    @GET("/Jobseeker/MyJobs/{jobseekerId}")
    void MyAppliedJobs(@Path("jobseekerId") String jobseekerId, Callback<AppliedJobs> appliedJobsCallback);

//http://itecywebapi.azurewebsites.net/Jobseeker/getNationalities
    @GET("/Jobseeker/getNationalities")
    void getNationalities(Callback<Nationalities> nationalitiesCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getLanguages
    @GET("/Jobseeker/getLanguages")
    void getLanguages(Callback<Languages> languagesCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getCompanies
    @GET("/Jobseeker/getCompanies")
    void getCompanies(Callback<Companies>companiesCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getIndustries
    @GET("/Jobseeker/getIndustries")
    void getIndustries(Callback<Industries>industriesCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getFunctionalArea
    @GET("/Jobseeker/getFunctionalArea")
    void getFunctionalArea(Callback<FunctionalAreas>functionalAreasCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getDesignations/{FunctionalAreaId}
    @GET("/Jobseeker/getDesignations/{FunctionalAreaId}")
    void getDesignation(@Path("FunctionalAreaId") int functionalAreaId, Callback<Designations>designationsCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getSkills
    @GET("/Jobseeker/getSkills")
    void getSkills(Callback<Skills>companiesCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getEducation
    @GET("/Jobseeker/getEducation")
    void getEducation(Callback<Education>educationcallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getSpecialization/{EducationId}
    @GET("/Jobseeker/getSpecialization/{EducationId}")
    void getSpecialization(@Path("EducationId") int functionalAreaId, Callback<Specialization>designationsCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getCountries
    @GET("/Jobseeker/getCountries")
    void getCountries(Callback<Country>educationcallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getStates/{CountryId}
    @GET("/Jobseeker/getStates/{CountryId}")
    void getStates(@Path("CountryId") int countryId, Callback<States>designationsCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getCities/{StateId}
    @GET("/Jobseeker/getCities/{StateId}")
    void getCities(@Path("StateId") int cityId, Callback<City>designationsCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getEducationbyid/326
    @GET("/Jobseeker/getEducationbyid/{jobseekerId}")
    void getEducationDetails(@Path("jobseekerId") String jobseekerId, Callback<GetEducationDetails>designationsCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getSocialProfileById/326
    @GET("/Jobseeker/getSocialProfileById/{jobseekerId}")
    void getSocilaProfile(@Path("jobseekerId") String jobseekerId,Callback<SocialProfile>socialProfileCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getCertificationbyid/343
    @GET("/Jobseeker/getCertificationbyid/{jobseekerId}")
    void getCertificationPrifile(@Path("jobseekerId") String jobseekerId,Callback<GetCertification>socialProfileCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getExperience/349
    @GET("/Jobseeker/getExperience/{jobseekerId}")
    void getExperiencePrifile(@Path("jobseekerId") String jobseekerId,Callback<Experience>socialProfileCallback);


    //http://itecywebapi.azurewebsites.net//Jobseeker/forgotPasswordOtp/{emailId}
    @GET("/Jobseeker/forgotPasswordOtp/{emailId}")
    void ForgotPassword(@Path("emailId") String jobSeekerId, Callback<ForgotPasswordResponse> responseCallback);


    //http://itecywebapi.azurewebsites.net/Jobseeker/getAuthorization
    @GET("/Jobseeker/getAuthorization")
    void GetAuthorization(Callback<Authorization>authorizationCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/getAllCities
    @GET("/Jobseeker/getAllCities")
    void GetAllCities(Callback<GetAllCities>authorizationCallback);

    //http://testitecywebapi.azurewebsites.net/Jobseeker/forgotPassword/{jobseekerEmailId}
    @GET("/Jobseeker/forgotPassword/{jobseekerEmailId}")
    void ResetPassword(@Path("jobseekerEmailId") String useremail, Callback<ForgotModle> responseCallback);

    //post services
    @POST("/Jobseeker/register")
    void getRegistration(@Body RegistrationData registration, Callback<RegistrationResponse> callback);

    @POST("/Jobseeker/JobApply")
    void JobApply(@Body JobApply jobApply, Callback<JobApplyResponse> responseCallback);

    @POST("/Jobseeker/addEducation")
    void UpdateEducation(@Body UpdateEducation jobApply,
                         Callback<JobApplyResponse> responseCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/addCertification
    @POST("/Jobseeker/addCertification")
    void UpdateCertification(@Body UpdateCertification jobApply, Callback<JobApplyResponse> responseCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/addSocialProfile
    @POST("/Jobseeker/addSocialProfile")
    void UpdateSocialProfile(@Body SocialProfileList jobApply, Callback<JobApplyResponse> responseCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/addExperience
    @POST("/Jobseeker/addExperience")
    void UpdateExperience(@Body UpdateExperience jobApply, Callback<JobApplyResponse> responseCallback);

    //https://itecywebapi.azurewebsites.net/Jobseeker/ResumeDelete/{jobseekerId}
    @POST("/Jobseeker/ResumeDelete/{jobseekerId}")
    void DeleteResume(@Path("jobseekerId") String jobseekerId, Callback<JobApplyResponse> responseCallback);

    //http://testitecywebapi.azurewebsites.net/Jobseeker/addDegree
    @POST("/Jobseeker/addDegree")
    void AddDegree(@Body AddDegree addDegree, Callback<JobApplyResponse> responseCallback);

    //http://testitecywebapi.azurewebsites.net/Jobseeker/addSpecialization
    @POST("/Jobseeker/addSpecialization")
    void AddSpecialization(@Body AddSpecialization addSpecialization, Callback<JobApplyResponse> responseCallback);


    //http://itecywebapi.azurewebsites.net/Jobseeker/putMyProfile/352
    @PUT("/Jobseeker/putMyProfile/{jobseekerId}")
    void getUpdateBasicProfile(@Path("jobseekerId") String jobSeekerId,@Body UpdateBasicProfile updateProfile,Callback<UpdateProfileResponse> updateProfileResponseCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/changePassword
    @PUT("/Jobseeker/changePassword")
    void ChangePassword(@Body ChangePassword changePassword, Callback<PasswordResponce> passwordResponceCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/putEducationById
    @PUT("/Jobseeker/putEducationById")
    void UpdateEducationById(@Body UpdateEducationById updateEducationById, Callback<PasswordResponce> responceCallback);

    // http://itecywebapi.azurewebsites.net/Jobseeker/putCertificationById
    @PUT("/Jobseeker/putCertificationById")
    void UpdateCertificationByID(@Body UpdateCertification certification, Callback<PasswordResponce> responceCallback);

    //http://itecywebapi.azurewebsites.net/Jobseeker/putExperienceById
    @PUT("/Jobseeker/putExperienceById")
    void UpdateExperienceById(@Body UpdateExperienceById updateExperienceById, Callback<PasswordResponce> responceCallback);
}
