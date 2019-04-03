package com.ahe.jobschedulertest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;


public class Util {
    private static final int JOB_ID = 1001;
    private static final long REFRESH_INTERVAL  = 60*60*1000;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void scheduleJob(Context context)
    {
        ComponentName serviceComponent=new ComponentName(context,TestJobservice.class);
        /*JobInfo.Builder builder = new JobInfo.Builder(0,serviceComponent);
        builder.setMinimumLatency(60*60*1000);
        builder.setOverrideDeadline(60*60*1000);


        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());*/




        JobInfo jobInfo;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jobInfo = new JobInfo.Builder(JOB_ID, serviceComponent)
                    .setMinimumLatency(REFRESH_INTERVAL)
                    .setOverrideDeadline(REFRESH_INTERVAL)
                    //.setExtras(bundle)
                    .build();
        } else {
            jobInfo = new JobInfo.Builder(JOB_ID, serviceComponent)
                    .setPeriodic(REFRESH_INTERVAL)
                    //.setExtras(bundle)
                    .build();
        }
        JobScheduler jobScheduler = context.getApplicationContext().getSystemService(JobScheduler.class);
        jobScheduler.schedule(jobInfo);

    }




}
