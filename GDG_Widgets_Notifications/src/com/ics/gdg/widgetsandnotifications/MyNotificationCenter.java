package com.ics.gdg.widgetsandnotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class MyNotificationCenter {
	static NotificationManager notificationManager;

	public static void showPlainNotification(Context ctx) {

		Intent intent = new Intent(ctx, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);

		// Build notification, actions are fake
		Notification.Builder notification = new Notification.Builder(ctx)
				.setContentTitle("Awesome notification")
				.setContentText("Important").setContentIntent(pIntent)
				.setSmallIcon(R.drawable.ic_launcher);

		notificationManager = (NotificationManager) ctx
				.getSystemService(ctx.NOTIFICATION_SERVICE);

		// Hide the notification after its selected
		notification.build().flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, notification.build());

	}

	public static void showExpandableNotification(Context ctx) {

		Intent intent = new Intent(ctx, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);
		String txt = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra tristique velit, a consequat tortor interdum vulputate. Quisque lacus felis, interdum at sodales in, molestie a eros. Vivamus auctor feugiat laoreet. Nulla adipiscing, nunc quis dictum aliquet, est nisi ultrices augue, id blandit tortor ante nec nunc.";
		// Build notification, actions are fake
		Notification.Builder notification = new Notification.Builder(ctx)
				.setContentTitle("Awesome notification")
				.setContentText("Important").setContentIntent(pIntent)
				.setSmallIcon(R.drawable.ic_launcher)
				.setStyle(new Notification.BigTextStyle().bigText(txt));

		notificationManager = (NotificationManager) ctx
				.getSystemService(ctx.NOTIFICATION_SERVICE);

		// Hide the notification after its selected
		notification.build().flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, notification.build());

	}

	public static void showActionNotification(Context ctx) {

		Intent intent = new Intent(ctx, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);

		// Build notification, actions are fake
		Notification.Builder notification = new Notification.Builder(ctx)
				.setContentTitle("Awesome notification")
				.setContentText("Important").setContentIntent(pIntent)
				.setSmallIcon(R.drawable.ic_launcher)
				.addAction(R.drawable.googleplus, "G+", pIntent)
				.addAction(R.drawable.twitter, "Twitter", pIntent);

		notificationManager = (NotificationManager) ctx
				.getSystemService(ctx.NOTIFICATION_SERVICE);

		// Hide the notification after its selected
		notification.build().flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, notification.build());

	}
	
	public static void showIndetProgressNotification(Context ctx) {

		Intent intent = new Intent(ctx, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);

		// Build notification, actions are fake
		final Notification.Builder notification = new Notification.Builder(ctx)
				.setContentTitle("Awesome notification")
				.setContentText("Downloading").setContentIntent(pIntent)
				.setSmallIcon(R.drawable.ic_launcher);

		notificationManager = (NotificationManager) ctx
				.getSystemService(ctx.NOTIFICATION_SERVICE);
		
		new Thread(
			    new Runnable() {
			        @Override
			        public void run() {
			            int incr;
			            // Do the "lengthy" operation 20 times
			            for (incr = 0; incr <= 100; incr+=5) {
			                    // Sets the progress indicator as indeterminate
			                    notification.setProgress(0, 0, true);
			                    // Displays the progress bar for the first time.
			                    notificationManager.notify(0, notification.build());
			                        // Sleeps the thread, simulating an operation
			                        // that takes time
			                        try {
			                            Thread.sleep(1*1000);
			                        } catch (InterruptedException e) {
			        
			                        }
			            }
			            // When the loop is finished, updates the notification
			            notification.setContentText("Download complete")
			            // Removes the progress bar
			                    .setProgress(0,0,false);
			            notificationManager.notify(0, notification.build());
			        }
			    }
			).start();

	}

	public static void showProgressNotification(Context ctx) {

		Intent intent = new Intent(ctx, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);

		// Build notification, actions are fake
		final Notification.Builder notification = new Notification.Builder(ctx)
				.setContentTitle("Awesome notification")
				.setContentText("Downloading").setContentIntent(pIntent)
				.setSmallIcon(R.drawable.ic_launcher);
		
		notificationManager = (NotificationManager) ctx
				.getSystemService(ctx.NOTIFICATION_SERVICE);
		
		new Thread(
			    new Runnable() {
			        @Override
			        public void run() {
			            int incr;
			            // Do the "lengthy" operation 20 times
			            for (incr = 0; incr <= 100; incr+=5) {
			                    // Sets the progress indicator to a max value, the
			                    // current completion percentage, and "determinate"
			                    // state
			                    notification.setProgress(100, incr, false);
			                    // Displays the progress bar for the first time.
			                    notificationManager.notify(0, notification.build());
			                        // Sleeps the thread, simulating an operation
			                        // that takes time
			                        try {
			                            Thread.sleep(1*1000);
			                        } catch (InterruptedException e) {
			        
			                        }
			            }
			            // When the loop is finished, updates the notification
			            notification.setContentText("Download complete")
			            // Removes the progress bar
			                    .setProgress(0,0,false);
			            notificationManager.notify(0, notification.build());
			        }
			    }
			).start();

	}
	
	
	public static void cancelNotification() {
		notificationManager.cancelAll();
	}
}
