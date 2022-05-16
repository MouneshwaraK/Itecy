package com.cstech.itecy.ModelClasess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JobseekerDetail implements Serializable
{

    @SerializedName("jobseekerDetailsId")
    @Expose
    private Integer jobseekerDetailsId;
    @SerializedName("jobseekerId")
    @Expose
    private Integer jobseekerId;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("applied")
    @Expose
    private Boolean applied;
    @SerializedName("appliedOn")
    @Expose
    private String appliedOn;
    @SerializedName("jobseeker")
    @Expose
    private Object jobseeker;
    @SerializedName("job")
    @Expose
    private Object job;
    private final static long serialVersionUID = 8438769614448180520L;

    public Integer getJobseekerDetailsId() {
        return jobseekerDetailsId;
    }

    public void setJobseekerDetailsId(Integer jobseekerDetailsId) {
        this.jobseekerDetailsId = jobseekerDetailsId;
    }

    public Integer getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(Integer jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Boolean getApplied() {
        return applied;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }

    public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }

    public Object getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(Object jobseeker) {
        this.jobseeker = jobseeker;
    }

    public Object getJob() {
        return job;
    }

    public void setJob(Object job) {
        this.job = job;
    }

}