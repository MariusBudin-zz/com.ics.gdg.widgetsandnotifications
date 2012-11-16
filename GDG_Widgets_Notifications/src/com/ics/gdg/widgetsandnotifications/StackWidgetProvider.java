package com.ics.gdg.widgetsandnotifications;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

public class StackWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; ++i) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.stack_widget_layout);
 
            // set intent for widget service that will create the views
            Intent serviceIntent = new Intent(context, StackWidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME))); // embed extras so they don't get ignored
            remoteViews.setRemoteAdapter(appWidgetIds[i], R.id.stackWidgetView, serviceIntent);
            remoteViews.setEmptyView(R.id.stackWidgetView, R.id.stackWidgetEmptyView);
             
            // set intent for item click (opens main activity)
            Intent viewIntent = new Intent(context, MainActivity.class);
           
            viewIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
      
             
            PendingIntent viewPendingIntent = PendingIntent.getActivity(context, 0, viewIntent, 0);
            remoteViews.setPendingIntentTemplate(R.id.stackWidgetView, viewPendingIntent);
             
            // update widget
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}