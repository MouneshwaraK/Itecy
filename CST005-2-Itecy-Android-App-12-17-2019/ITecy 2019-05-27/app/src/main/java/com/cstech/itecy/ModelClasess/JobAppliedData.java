package com.cstech.itecy.ModelClasess;

public class JobAppliedData {

    private int jobseekerDetailsId,jobseekerId,jobId;
    private boolean applied;
    private String appliedOn,jobseeker,job;

    public int getJobseekerDetailsId() {
        return jobseekerDetailsId;
    }

    public void setJobseekerDetailsId(int jobseekerDetailsId) {
        this.jobseekerDetailsId = jobseekerDetailsId;
    }

    public int getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(int jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }

    public String getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(String jobseeker) {
        this.jobseeker = jobseeker;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "JobAppliedData{" +
                "jobseekerDetailsId=" + jobseekerDetailsId +
                ", jobseekerId=" + jobseekerId +
                ", jobId=" + jobId +
                ", applied=" + applied +
                ", appliedOn='" + appliedOn + '\'' +
                ", jobseeker='" + jobseeker + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
