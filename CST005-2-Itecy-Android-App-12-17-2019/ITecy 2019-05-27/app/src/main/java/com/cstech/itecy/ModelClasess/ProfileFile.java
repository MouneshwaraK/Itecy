package com.cstech.itecy.ModelClasess;

import java.io.File;

import okhttp3.MultipartBody;

public class ProfileFile {
    private int JobseekerId;
    private File uploadedFile;

    public int getJobseekerId() {
        return JobseekerId;
    }

    public void setJobseekerId(int jobseekerId) {
        JobseekerId = jobseekerId;
    }

    public File getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(File uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    @Override
    public String toString() {
        return "ProfileFile{" +
                "JobseekerId=" + JobseekerId +
                ", uploadedFile=" + uploadedFile +
                '}';
    }
}
