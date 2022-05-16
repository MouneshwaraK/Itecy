package com.cstech.itecy.ModelClasess;

public class SocialProfileList {

    int jobsocproId,jobseekerId;
    private String facebook,gplus,twitter,instagram,linkedin,dribble,jobseeker;

    public int getJobsocproId() {
        return jobsocproId;
    }

    public void setJobsocproId(int jobsocproId) {
        this.jobsocproId = jobsocproId;
    }

    public int getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(int jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGplus() {
        return gplus;
    }

    public void setGplus(String gplus) {
        this.gplus = gplus;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getDribble() {
        return dribble;
    }

    public void setDribble(String dribble) {
        this.dribble = dribble;
    }

    public String getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(String jobseeker) {
        this.jobseeker = jobseeker;
    }

    @Override
    public String toString() {
        return "SocialProfileList{" +
                "jobsocproId=" + jobsocproId +
                ", jobseekerId=" + jobseekerId +
                ", facebook='" + facebook + '\'' +
                ", gplus='" + gplus + '\'' +
                ", twitter='" + twitter + '\'' +
                ", instagram='" + instagram + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", dribble='" + dribble + '\'' +
                ", jobseeker='" + jobseeker + '\'' +
                '}';
    }
}
