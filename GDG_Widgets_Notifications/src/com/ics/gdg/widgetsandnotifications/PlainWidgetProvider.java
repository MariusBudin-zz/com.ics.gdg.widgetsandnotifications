package com.ics.gdg.widgetsandnotifications;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class PlainWidgetProvider extends AppWidgetProvider {

	public static String IMAGE_CLICKED_ACTION="com.ics.gdg.widgetsandnotifications.IMAGE_CLICKED";

	  @Override
	  public void onUpdate(Context context, AppWidgetManager appWidgetManager,
	      int[] appWidgetIds) {

	    // Get all ids
	    ComponentName thisWidget = new ComponentName(context,
	        PlainWidgetProvider.class);
	    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
	    for (int widgetId : allWidgetIds) {

	      RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
	          R.layout.plain_widget_layout);

	      //to set a textview you would do:
	      //remoteViews.setTextViewText(R.id.tv, "my text");

	      // Register an onClickListener
	      Intent intent = new Intent(context, PlainWidgetProvider.class);

	      intent.setAction(IMAGE_CLICKED_ACTION);
	      intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

	      PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
	          0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	      remoteViews.setOnClickPendingIntent(R.id.img, pendingIntent);
	      appWidgetManager.updateAppWidget(widgetId, remoteViews);
	    }
	  }

	@Override
	public void onReceive(Context context, Intent intent) {
		if(IMAGE_CLICKED_ACTION.equalsIgnoreCase(intent.getAction()))
			Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
		
		super.onReceive(context, intent);
	}
	  
	  
}
