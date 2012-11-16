package com.ics.gdg.widgetsandnotifications;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService {
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
	}
}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
	private List<Integer> images = new ArrayList<Integer>();
	private Context mContext;
	private int mAppWidgetId;

	public StackRemoteViewsFactory(Context context, Intent intent) {
		mContext = context;
		images.add(R.drawable.googleplus);
		images.add(R.drawable.twitter);
		images.add(R.drawable.and2);
		images.add(R.drawable.ic_launcher);

		mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
	}

	public void onCreate() {
	}

	public void onDestroy() {
		images.clear();
	}

	public int getCount() {
		return images.size();
	}

	public RemoteViews getViewAt(int position) {
		RemoteViews rv = new RemoteViews(mContext.getPackageName(),
				R.layout.stack_view);

		if (position <= getCount()) {
			rv.setImageViewBitmap(R.id.img, BitmapFactory
					.decodeResource(mContext.getResources(),
							images.get(position)));

			rv.setTextViewText(R.id.txt_name, "GDG Barcelona");

			// store the ID in the extras so the main activity can use it
			Bundle extras = new Bundle();
			// extras.putString(EXTRA_ID, id);
			Intent fillInIntent = new Intent();
			fillInIntent.putExtras(extras);
			rv.setOnClickFillInIntent(R.id.stackWidgetItem, fillInIntent);

		}
		return rv;
	}

	public RemoteViews getLoadingView() {
		return null;
	}

	public int getViewTypeCount() {
		return 1;
	}

	public long getItemId(int position) {
		return position;
	}

	public boolean hasStableIds() {
		return true;
	}

	@Override
	public void onDataSetChanged() {
		// TODO Auto-generated method stub

	}
}