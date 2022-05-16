package com.cstech.itecy.ModelClasess;

public class UpdateCertification {

    private int JobSeekerId,jobseekerCertId;
    private String CertificationName,InstituteName,LicenceNo;
    private String Validity;

    public int getJobSeekerId() {
        return JobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        JobSeekerId = jobSeekerId;
    }

    public String getLicenceNo() {
        return LicenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        LicenceNo = licenceNo;
    }

    public String getCertificationName() {
        return CertificationName;
    }

    public void setCertificationName(String certificationName) {
        CertificationName = certificationName;
    }

    public String getInstituteName() {
        return InstituteName;
    }

    public void setInstituteName(String instituteName) {
        InstituteName = instituteName;
    }

    public String getValidity() {
        return Validity;
    }

    public void setValidity(String validity) {
        Validity = validity;
    }

    public int getJobseekerCertId() {
        return jobseekerCertId;
    }

    public void setJobseekerCertId(int jobseekerCertId) {
        this.jobseekerCertId = jobseekerCertId;
    }

    @Override
    public String toString() {
        return "UpdateCertification{" +
                "JobSeekerId=" + JobSeekerId +
                ", jobseekerCertId=" + jobseekerCertId +
                ", CertificationName='" + CertificationName + '\'' +
                ", InstituteName='" + InstituteName + '\'' +
                ", LicenceNo='" + LicenceNo + '\'' +
                ", Validity='" + Validity + '\'' +
                '}';
    }
}
