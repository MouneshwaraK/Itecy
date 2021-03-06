package com.cstech.itecy;

/**
 * Created by rajibnayak on 01/18/2018.
 * Checks for Internet Connection
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class ConnectionDetector {

    private Context _context;

    /**
     * @param context
     */
    public ConnectionDetector(Context context) {
        this._context = context;
    }

    /**
     * Checking Internet Connection
     */
    /**
     * @return
     */
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }
}

