package com.example.quachvandai_202602919;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class boardcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            if(isNetWordAvailable(context)){
                Toast.makeText(context,"Đã kết nối mạng",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context,"Đã Ngắt kết nối mạng",Toast.LENGTH_SHORT).show();

            }
        }
    }
    private  boolean isNetWordAvailable(Context context){
        ConnectivityManager conn = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(conn==null){
            return false;
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            Network network  = conn.getActiveNetwork();
            if(network == null){
                return false;
            }
            NetworkCapabilities cap = conn.getNetworkCapabilities(network);
            return cap !=null && cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        }
        else {
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();
            return networkInfo !=null && networkInfo.isConnected();
        }
    //    return true;
    }
}
