package com.cstech.itecy;

import com.cstech.itecy.ModelClasess.ForgotPasswordResponse;
import com.cstech.itecy.ModelClasess.ProfileImageResp;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ServiceApi {
    @Multipart
    @POST("/Jobseeker/PhotoUpload/{jobseekerId}")
    Call<ProfileImageResp> postImage(
            @Path("jobseekerId") String jobSeekerId,
            @Part MultipartBody.Part image,
            @Part("JobseekerId") RequestBody id
    );

    @Multipart
    @POST("/Jobseeker/CoverImageUpload/{jobseekerId}")
    Call<ProfileImageResp> postCoverImage(
            @Path("jobseekerId") String jobSeekerId,
            @Part MultipartBody.Part image,
            @Part("JobseekerId") RequestBody id
    );

    @Multipart
    @POST("/Jobseeker/ResumeUpload/{jobseekerId}")
    Call<ProfileImageResp> uploadResume(
            @Path("jobseekerId") String jobSeekerId,
            @Part MultipartBody.Part image,
            @Part("jobseekerId") RequestBody id
    );
    @GET("/Jobseeker/forgotPasswordOtp/{emailId}")
    Call<ForgotPasswordResponse> ForgotPassword(@Path("emailId") String RegisteredUserEmailId);
    //void ForgotPassword(@Path("emailId") String RegisteredUserEmailId, Callback<ForgotPasswordResponse> responseCallback);

}
