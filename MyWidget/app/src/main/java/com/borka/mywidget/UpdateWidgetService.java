package com.borka.mywidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UpdateWidgetService extends Service {
    private static final String LOG = "GST";

    @Override
    public void onStart(Intent intent, int startId) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());

        int[] allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

       HashMap hashMap = new HashMap();


        for(int i =0; i<15;i++)
        {
            hashMap.put(i,"Important Sentense #"+i);

            i++;
        }

        for (int widgetId : allWidgetIds) {
            // create some random data
            int number = (new Random().nextInt(14));
            String showStr= (String)hashMap.get(number);
            Toast.makeText(this, showStr, Toast.LENGTH_SHORT).show();

            RemoteViews remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(),
                    R.layout.widget_layout);
           // Log.w("WidgetExample", String.valueOf(number));
            // Set the text
          //  remoteViews.setTextViewText(R.id.update,"Random: " + String.valueOf(number));
//            Log.w("WidgetExample", showStr);
            remoteViews.setTextViewText(R.id.update,"Randomaly - " + showStr);

            // Register an onClickListener
            Intent clickIntent = new Intent(this.getApplicationContext(),WidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    getApplicationContext(), 0, clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();

        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
