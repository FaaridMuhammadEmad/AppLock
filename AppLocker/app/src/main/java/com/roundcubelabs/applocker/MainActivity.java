package com.roundcubelabs.applocker;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getInstalledApps();
        try {
            System.out.println(getForegroundApp());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }



        Intent i = new Intent(getBaseContext(), AppLockActivity.class);

        startActivity(i);
    }

    //to be done in a service which will be called whenever any app is launched
    public String getForegroundApp() throws PackageManager.NameNotFoundException {
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
// The first in the list of RunningTasks is always the foreground task.
        ActivityManager.RunningTaskInfo foregroundTaskInfo = am.getRunningTasks(1).get(0);
        String foregroundTaskPackageName = foregroundTaskInfo .topActivity.getPackageName();
        PackageManager pm = this.getPackageManager();
        PackageInfo foregroundAppPackageInfo = pm.getPackageInfo(foregroundTaskPackageName, 0);
        String foregroundTaskAppName = foregroundAppPackageInfo.packageName.toString();
        return foregroundTaskAppName;
    }

    private void getInstalledApps() {

        final PackageManager pm = getPackageManager();
//get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Log.d("Test", "Installed package :" + packageInfo.packageName);
            Log.d("Test", "Source dir : " + packageInfo.sourceDir);
            Log.d("Test", "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }
    }
}
