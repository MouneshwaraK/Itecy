package com.cstech.itecy.Adapters;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cstech.itecy.AdapterCallback;
import com.cstech.itecy.ModelClasess.GetCertificationList;
import com.cstech.itecy.ModelClasess.PasswordResponce;
import com.cstech.itecy.ModelClasess.UpdateCertification;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CertificationAdapter extends BaseAdapter {

    Activity activity;
    List<GetCertificationList> certificationLists;
    LayoutInflater inflater;
    AdapterCallback callback;
    String JobseekerId;
    Calendar calendar;
    TextView certificationValidity, tvErrorValidity;
    EditText certificationName1;
    EditText certificationLicenceNo,certificationInstituteName1;
    public CertificationAdapter(Activity activity, List<GetCertificationList> certificationLists, AdapterCallback callback, String jobseekerId) {

        this.activity = activity;
        this.certificationLists = certificationLists;
        this.callback = callback;
        this.JobseekerId = jobseekerId;
        calendar = Calendar.getInstance();
        inflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return certificationLists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = inflater.inflate(R.layout.certitifacationchild, null);
        final TextView certificationName, licenceNo, institute;
        certificationName = (TextView) v.findViewById(R.id.certificationName);
        final TextView validity = (TextView) v.findViewById(R.id.certificationValidity);
        licenceNo = (TextView) v.findViewById(R.id.certificationLicenceNo);
        institute = (TextView) v.findViewById(R.id.certificationInstituteName);
        ImageView editCertification = (ImageView) v.findViewById(R.id.editCertification);

        certificationName.setText(certificationLists.get(i).getCertificationName());
        String Validity = certificationLists.get(i).getValidity();
        //"T00:00:00"
        validity.setText(Validity.replace("T00:00:00", ""));
        licenceNo.setText(certificationLists.get(i).getLicenceNo());
        institute.setText(certificationLists.get(i).getInstituteName());

        editCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.editcertificationlayout);
                certificationName1 = (EditText) dialog.findViewById(R.id.certificationName);
                certificationValidity = (TextView) dialog.findViewById(R.id.certificationValidity);
                tvErrorValidity = dialog.findViewById(R.id.tv_errorValidity);
                final TextView upDateCertificationProfile = (TextView) dialog.findViewById(R.id.upDateCertificationProfile);
                TextView cancelCertificationfoProfile = (TextView) dialog.findViewById(R.id.cancelCertificationfoProfile);
                certificationLicenceNo = (EditText) dialog.findViewById(R.id.certificationLicenceNo);
                certificationInstituteName1 = (EditText) dialog.findViewById(R.id.certificationInstituteName);
                final LinearLayout llcertificate = (LinearLayout)dialog.findViewById(R.id.llupdate);

                certificationName1.setText(certificationLists.get(i).getCertificationName());
                String Validity = certificationLists.get(i).getValidity();
                //"T00:00:00"
                certificationValidity.setText(Validity.replace("T00:00:00", ""));
                certificationLicenceNo.setText(certificationLists.get(i).getLicenceNo());
                certificationInstituteName1.setText(certificationLists.get(i).getInstituteName());


                cancelCertificationfoProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                certificationValidity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDial();
                    }
                });


                upDateCertificationProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ValidateCertification()) {
                            llcertificate.setVisibility(View.GONE);
                            RestService service = new RestService();

                            UpdateCertification certification = new UpdateCertification();
                            certification.setJobseekerCertId(certificationLists.get(i).getJobseekerCertId());
                            certification.setJobSeekerId(Integer.parseInt(JobseekerId));
                            certification.setCertificationName(certificationName1.getText().toString());
                            certification.setValidity(certificationValidity.getText().toString());
                            certification.setLicenceNo(certificationLicenceNo.getText().toString());
                            certification.setInstituteName(certificationInstituteName1.getText().toString());
                            service.getService().UpdateCertificationByID(certification, new Callback<PasswordResponce>() {
                                @Override
                                public void success(PasswordResponce passwordResponce, Response response) {

                                    if (passwordResponce.getMessage() != null) {
                                        Utils.showAlertDialog(activity, passwordResponce.getMessage(), false);
                                        dialog.dismiss();
                                        callback.onMethodCallback();
                                    } else {
                                        Utils.showAlertDialog(activity, passwordResponce.getErrorMessage(), false);
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {

                                }
                            });
                        }
                    }
                });
                dialog.show();
            }
        });
        return v;
    }

    private void DatePickerDial() {

        final DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                view.setMaxDate(System.currentTimeMillis());
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM-dd-yyyy";//"MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                certificationValidity.setText(sdf.format(calendar.getTime()));

            }
        };

        new DatePickerDialog(activity, datePickerDialog, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private boolean ValidateCertification() {
        if (!ValidateCertificationName()) {
            return false;
        } else if (!ValidatecertificationValidity()) {
            return false;
        } else if (!ValidatecertificationLicenceNo()) {
            return false;
        } else if (!ValidateInstutName()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidateCertificationName() {
        if (certificationName1.getText().toString().equals("")) {
            certificationName1.setError("Certification Name Should Not Be Empty");
            certificationName1.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidatecertificationValidity() {
        if (certificationValidity.getText().toString().equals("")) {
            tvErrorValidity.setVisibility(View.VISIBLE);
            tvErrorValidity.setText("Certification Validity Should Not Be Empty");
            tvErrorValidity.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidatecertificationLicenceNo() {
        if (certificationLicenceNo.getText().toString().equals("")) {
            certificationLicenceNo.setError("Licence Number Should Not Be Empty");
            certificationLicenceNo.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidateInstutName() {
        if (certificationInstituteName1.getText().toString().equals("")) {
            certificationInstituteName1.setError("Institute Name Should Not Be Empty");
            certificationInstituteName1.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
