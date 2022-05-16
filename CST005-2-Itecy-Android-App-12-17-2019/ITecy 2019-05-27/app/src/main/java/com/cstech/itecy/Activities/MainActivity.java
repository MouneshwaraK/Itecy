package com.cstech.itecy.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cstech.itecy.BuildConfig;
import com.cstech.itecy.ConnectionDetector;
import com.cstech.itecy.FilePath;
import com.cstech.itecy.Fragments.AboutUs;
import com.cstech.itecy.Fragments.ChangePasswordFragment;
import com.cstech.itecy.Fragments.Dashboard;
import com.cstech.itecy.Fragments.MyJobs;
import com.cstech.itecy.Fragments.PrivacyPolicyFragment;
import com.cstech.itecy.Fragments.Profile;
import com.cstech.itecy.Fragments.ContactUsFragment;
import com.cstech.itecy.ModelClasess.ProfileData;
import com.cstech.itecy.ModelClasess.ProfileImageResp;
import com.cstech.itecy.ModelClasess.ProfileResponse;
import com.cstech.itecy.R;
import com.cstech.itecy.RestService;
import com.cstech.itecy.ServiceApi;
import com.cstech.itecy.Session;
import com.cstech.itecy.utils.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

    String username;
    public static boolean ExitApp=false;
    SharedPreferences preferences;
    SharedPreferences.Editor spEditor;
    Session session;
    CircleImageView userProfile;
    TextView UserName,userEmail;
    DrawerLayout drawer;
    String JobseekerId;
    private AdView mAdView;
    String joblist;
    LinearLayout report,pPolicy,logIn,registration;
    private static final int STORAGE_PERMISSION_CODE = 23;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session=new Session(this);
        if (getIntent().getExtras()!=null){
            joblist  = getIntent().getStringExtra("listingJobs");
        }

/*
        MobileAds.initialize(this, "ca-app-pub-7642728580115992~9684852906");
*/

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        drawer = findViewById(R.id.drawer_layout);


//        mAdView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                // Code to be executed when an ad finishes loading.
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                // Code to be executed when an ad request fails.
//                Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onAdOpened() {
//                // Code to be executed when an ad opens an overlay that
//                // covers the screen.
//            }
//
//            @Override
//            public void onAdClicked() {
//                // Code to be executed when the user clicks on an ad.
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                // Code to be executed when the user has left the app.
//            }
//
//            @Override
//            public void onAdClosed() {
//                // Code to be executed when the user is about to return
//                // to the app after tapping on an ad.
//            }
//        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

       // navigationView.setNavigationItemSelectedListener(this);

        preferences=getSharedPreferences("ITecy",MODE_PRIVATE);
        spEditor=preferences.edit();

        username=preferences.getString("username", "");
        View header = navigationView.getHeaderView(0);
          UserName= header.findViewById(R.id.userName);
          userEmail= header.findViewById(R.id.userEmail);
         userProfile=(CircleImageView)header.findViewById(R.id.userProfile);



        LinearLayout dashboard= header.findViewById(R.id.dashboard);
        LinearLayout createProfile= header.findViewById(R.id.createProfile);
        LinearLayout logout= header.findViewById(R.id.logout);
        LinearLayout myjobs= header.findViewById(R.id.myjobs);
        LinearLayout aboutUs= header.findViewById(R.id.aboutUs);
        LinearLayout changePassword= header.findViewById(R.id.changePassword);
        report = header.findViewById(R.id.report);
        pPolicy = header.findViewById(R.id.pPolicy);
        logIn = header.findViewById(R.id.logIn);
        registration = header.findViewById(R.id.registrstion);
        TextView AppVersion=(TextView)header.findViewById(R.id.AppVersion);
        JobseekerId=preferences.getString("userId","");

        if (JobseekerId.isEmpty()){
            report.setVisibility(View.VISIBLE);
            pPolicy.setVisibility(View.VISIBLE);
            logIn.setVisibility(View.VISIBLE);
            registration.setVisibility(View.VISIBLE);
            userProfile.setVisibility(View.VISIBLE);
            userProfile.setImageResource(R.drawable.itecy);
            userProfile.setClickable(false);
        }else {
            dashboard.setVisibility(View.VISIBLE);
            createProfile.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);
            myjobs.setVisibility(View.VISIBLE);
            changePassword.setVisibility(View.VISIBLE);
            userProfile.setVisibility(View.VISIBLE);
            userEmail.setVisibility(View.VISIBLE);
            UserName.setVisibility(View.VISIBLE);
        }



        AppVersion.setText("About Us");
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setTitle("About Us");
                Utils.navigationFragmentReplace(MainActivity.this, new AboutUs(), "About Us", null);
                drawer.closeDrawer(GravityCompat.START);
               /* ConnectionDetector checkNetWork=new ConnectionDetector(MainActivity.this);
                if (checkNetWork.isConnectingToInternet()){
                    Intent intent=new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://clientservertech.com/about/"));
                    startActivity(intent);
                }else {
                    Utils.showAlertDialog(MainActivity.this, "check your internet connection!",false);
                }
*/
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTitle("Contact Us");
                Utils.navigationFragmentReplace(MainActivity.this, new ContactUsFragment(), "Report", null);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        pPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTitle("Privacy Policy");
                Utils.navigationFragmentReplace(MainActivity.this, new PrivacyPolicyFragment(), "Privacy Policy", null);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        myjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*setTitle("My Jobs");
                Fragment dashboard1=new MyJobs();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout,dashboard1);
                transaction.commit();
                drawer.closeDrawer(GravityCompat.START);*/

                ConnectionDetector checkNetWork=new ConnectionDetector(MainActivity.this);
                if (checkNetWork.isConnectingToInternet()){
                    setTitle("MyJobs");
                    Utils.navigationFragmentReplace(MainActivity.this, new MyJobs(), "MyJobs", null);
                    drawer.closeDrawer(GravityCompat.START);
                }else {
                    Utils.showAlertDialog(MainActivity.this, "check your internet connection!",false);
                }

            }
        });


        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ConnectionDetector checkNetWork=new ConnectionDetector(MainActivity.this);
                if (checkNetWork.isConnectingToInternet()){
                    GetBasicProfileData();
                    setTitle("Dashboard");
                    Utils.navigationFragmentReplace(MainActivity.this, new Dashboard(), "Dashboard", null);
                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }else {
                    Utils.showAlertDialog(MainActivity.this, "check your internet connection!",false);
                }

            }
        });


        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionDetector checkNetWork=new ConnectionDetector(MainActivity.this);
                if (checkNetWork.isConnectingToInternet()){
                    setTitle("Profile");
                    Utils.navigationFragmentReplace(MainActivity.this, new Profile(), "Profile", null);
                    drawer.closeDrawer(GravityCompat.START);
                }else {
                    Utils.showAlertDialog(MainActivity.this, "check your internet connection!",false);
                }

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do You Want To Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        preferences.edit().remove("userId").commit();
                        preferences.edit().remove("userProfileImage").commit();

//                        session.logoutUser();
//                        session.setLoging(false);
//                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();

            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionDetector checkNetWork=new ConnectionDetector(MainActivity.this);
                if (checkNetWork.isConnectingToInternet()){
                    setTitle("Change Password");
                    Utils.navigationFragmentReplace(MainActivity.this, new ChangePasswordFragment(), "Change Password", null);
                    drawer.closeDrawer(GravityCompat.START);
                }else {
                    Utils.showAlertDialog(MainActivity.this, "check your internet connection!",false);
                }


            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });
        ConnectionDetector checkNetWork=new ConnectionDetector(MainActivity.this);
        if (checkNetWork.isConnectingToInternet()){
            setTitle("Dashboard");
            Utils.navigationFragmentReplace(MainActivity.this, new Dashboard(), "Dashboard", null);
            GetBasicProfileData();
        }else {
            Utils.showAlertDialog(MainActivity.this, "check your internet connection!",false);
        }



    }

    private void GetBasicProfileData() {

        RestService service=new RestService();
        service.getService().getProfile(JobseekerId, new Callback<ProfileResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void success(ProfileResponse profileResponse, Response response) {


                ProfileData data=profileResponse.getModel();

                if (data!=null){


                    UserName.setText(data.getFirstName()+" "+data.getLastName());
                    userEmail.setText(data.getEmailId());

                    if (!data.getPhoto().isEmpty()){

                        String Image=data.getPhoto();
                        Image=Image.replace(" ","%20");
                        String imageUri;
                        if (BuildConfig.BASE_URL.equalsIgnoreCase("http://testitecywebapi.azurewebsites.net/")){
                             imageUri="https://testitecy.azurewebsites.net/images/upload/"+Image;
                        }else {
                             imageUri="https://www.itecy.com/images/upload/"+Image;
                        }
                        //"https://testitecy.azurewebsites.net/images/upload/"

                       /* spEditor.putString("userProfileImage",imageUri);
                        spEditor.apply();
                        String UserImage=preferences.getString("userProfileImage","");
                        */
                        Picasso.with(MainActivity.this).load(imageUri).noPlaceholder().centerCrop().fit()
                                .into((userProfile));
                    }


                }


            }

            @Override
            public void failure(RetrofitError error) {


            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {


            FragmentManager fragmentManager = getSupportFragmentManager();

            if (!ExitApp) {

                Dashboard dashboard = (Dashboard) getSupportFragmentManager().findFragmentByTag("Dashboard");
                setTitle("Dashboard");


                if (dashboard != null && !dashboard.isVisible()) {
                    // add your code here
                    Utils.navigationFragmentReplace(MainActivity.this, new Dashboard(), "Dashboard", null);


                } else {
                    Toast.makeText(getApplicationContext(), "Tap again to Exit", Toast.LENGTH_LONG).show();
                    ExitApp = true;
                }

            } else {
                super.finish();
                finishAffinity();
            }
            new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                    ExitApp = false;
                    // finish();

                }
            }.start();

        }





        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       /* int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();


        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isReadStorageAllowed()) {


                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    // photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(photoPickerIntent,"Complete action using"), 1);
                    //startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
                } else {
                    requestStoragePermission();
                }


            }
        });


            GetBasicProfileData();
    }

    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Uploading...");
            progressDialog.show();
            final Uri selectedImageUri = data.getData();
            //Log.e("selectedImageUri", selectedImageUri.toString());
            if (selectedImageUri != null) {
                String imagepath = FilePath.getPath(MainActivity.this, selectedImageUri);

                //Log.e("image path is", imagepath);
                File files = new File(imagepath);

                if (files != null) {
                    long Length = imagepath.length();

                    Length = Length / 1024;

                    if (Length < 2) {

                        Retrofit retrofit = new Retrofit.Builder().
                                baseUrl(BuildConfig.BASE_URL).
                                addConverterFactory(GsonConverterFactory.create()).
                                build();
                        ServiceApi services = retrofit.create(ServiceApi.class);


                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), files);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadedFile", files.getName(), reqFile);
                        RequestBody reqId = RequestBody.create(MediaType.parse("text/plain"), JobseekerId);

                        services.postImage(JobseekerId, body, reqId).enqueue(new retrofit2.Callback<ProfileImageResp>() {
                            @Override
                            public void onResponse(Call<ProfileImageResp> call, retrofit2.Response<ProfileImageResp> response) {
                                progressDialog.dismiss();
                                if (response.body() != null) {
                                    if (response.body().getMessage() != null) {
                                        if (!response.body().getMessage().equalsIgnoreCase("File size Should Be UpTo 2000KB")) {
                                            userProfile.setImageURI(selectedImageUri);
                                            Utils.showAlertDialog(MainActivity.this, response.body().getMessage(), false);
                                            GetBasicProfileData();
                                        } else {
                                            Utils.showAlertDialog(MainActivity.this, response.body().getMessage(), false);
                                        }

                                    } else {

                                        Utils.showAlertDialog(MainActivity.this, String.valueOf(response.body().getErrorMessage()), false);
                                    }

                                } else {


                                    Utils.showAlertDialog(MainActivity.this, response.message(), false);
                                }

                            }

                            @Override
                            public void onFailure(Call<ProfileImageResp> call, Throwable t) {
                                Utils.showAlertDialog(MainActivity.this, t.getMessage(), false);
                                progressDialog.dismiss();
                            }
                        });
                    } else {

                        Utils.showAlertDialog(MainActivity.this, "Image size should not be more than 2Mb", false);
                    }

                }


            }
        }
    }


}
