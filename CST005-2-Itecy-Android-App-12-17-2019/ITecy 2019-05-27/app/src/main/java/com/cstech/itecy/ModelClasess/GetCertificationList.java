package com.cstech.itecy.ModelClasess;

import java.util.List;

public class GetCertificationList {

    private int jobseekerCertId,JobSeekerId;
    private String certificationName,instituteName,licenceNo,jobseeker,validity;


    public int getJobseekerCertId() {
        return jobseekerCertId;
    }

    public void setJobseekerCertId(int jobseekerCertId) {
        this.jobseekerCertId = jobseekerCertId;
    }

    public int getJobSeekerId() {
        return JobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        JobSeekerId = jobSeekerId;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(String jobseeker) {
        this.jobseeker = jobseeker;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "GetCertificationList{" +
                "jobseekerCertId=" + jobseekerCertId +
                ", JobSeekerId=" + JobSeekerId +
                ", certificationName='" + certificationName + '\'' +
                ", instituteName='" + instituteName + '\'' +
                ", licenceNo='" + licenceNo + '\'' +
                ", jobseeker='" + jobseeker + '\'' +
                ", validity='" + validity + '\'' +
                '}';
    }
}
