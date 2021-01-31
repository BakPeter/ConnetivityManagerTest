package com.bpapps.connectivitymanagertest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ConnectivityManagement.IConnectivityChangedListener {
    private static final String TAG = "TAG.MainActivity";

    public static final int REQUEST_CONNECTIVITY_SETTINGS = 17;

    private ConnectivityManagement mConnectivityManagement;

    protected AlertDialog mDialogNoConnectivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConnectivityManagement = new ConnectivityManagement(getApplicationContext());
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_connectivity_container, ConnectivityManagementFragment.newInstance())
//                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!mConnectivityManagement.getCurrentConnectivityStatus()) {
            onConnectivityStatusChanged(false);
        }
        mConnectivityManagement.registerForOnLineChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mConnectivityManagement.unRegisterForOnLineChangedListener();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == REQUEST_CONNECTIVITY_SETTINGS) {
//            Log.d(TAG, "onActivityResult: requestCode == REQUEST_CONNECTIVITY_SETTINGS");
//            onConnectivityStatusChanged(mConnectivityManagement.getCurrentConnectivityStatus());
//        } else {
//            Log.d(TAG, "onActivityResult: requestCode != REQUEST_CONNECTIVITY_SETTINGS");
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

    @Override
    public void onConnectivityStatusChanged(boolean isConnected) {
        Log.d(TAG, "onLineStatusChanged: " + isConnected);

        if (!isConnected) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No internet connection!!!")
                    .setMessage("The needs an internet connection to run.\nPress 'SETTINGS' to go to internet settings and connect.\nPress 'FINISH' to exit the app.")
                    .setCancelable(false)
                    .setPositiveButton("CONNECT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Log.d(TAG, "go to internet settings");
                            startActivityForResult(new Intent(Settings.ACTION_WIRELESS_SETTINGS), REQUEST_CONNECTIVITY_SETTINGS);
                        }
                    })
                    .setNegativeButton("FINISH", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finishAndRemoveTask();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
//                            requireActivity().finishAndRemoveTask();
                            finishAndRemoveTask();
                            System.exit(0);
                        }
                    });

            mDialogNoConnectivity = builder.create();
            mDialogNoConnectivity.show();
        } else {
            if (mDialogNoConnectivity != null) {
                mDialogNoConnectivity.dismiss();
                mDialogNoConnectivity = null;
            }
        }
    }
}