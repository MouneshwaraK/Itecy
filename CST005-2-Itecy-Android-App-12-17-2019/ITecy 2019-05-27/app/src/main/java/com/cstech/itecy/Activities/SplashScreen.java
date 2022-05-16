package com.cstech.itecy.Activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.cstech.itecy.ConnectionDetector;
import com.cstech.itecy.R;
import com.cstech.itecy.Session;
import com.cstech.itecy.utils.Utils;

public class SplashScreen extends AppCompatActivity {

    Session session;
    ConnectionDetector internetConnection;
    DrawerLayout drawer;
    MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        session=new Session(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        mainActivity = new MainActivity();
                internetConnection=new ConnectionDetector(SplashScreen.this);
                if (internetConnection.isConnectingToInternet()){

                    if (session.getLoggedIn()){

                        Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("listingJobs","16");
                        startActivity(intent);
                        finish();
//                        mainActivity.loadJobsFragment();
                    }
                }else {

                    Utils.showAlertDialog(SplashScreen.this, "Check Your Internet Connection",false);

                }


            }
        },1000);
    }
}
