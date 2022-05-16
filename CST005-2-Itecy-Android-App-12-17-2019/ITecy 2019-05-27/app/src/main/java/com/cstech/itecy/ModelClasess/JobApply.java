package com.cstech.itecy.ModelClasess;

public class JobApply {

   private String JobId,
           userId;
   private boolean PosAply=true;

    public String getJobId() {
        return JobId;
    }

    public void setJobId(String jobId) {
        JobId = jobId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isPosAply() {
        return PosAply;
    }

    public void setPosAply(boolean posAply) {
        PosAply = posAply;
    }

    @Override
    public String toString() {
        return "JobApply{" +
                "JobId='" + JobId + '\'' +
                ", userId='" + userId + '\'' +
                ", PosAply=" + PosAply +
                '}';
    }
}
