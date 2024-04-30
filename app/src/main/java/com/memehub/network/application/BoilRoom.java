package com.memehub.network.application;

import com.memehub.network.R;
import com.memehub.network.ui.activity.SplashActivity;

import android.app.Application;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.provider.FontRequest;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.FontRequestEmojiCompatConfig;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

public class BoilRoom extends MultiDexApplication {
	
	public static final String TAG = "BoilRoom";
	
	public Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
	
	private static Context mApplicationContext;
	
	private static BoilRoom instance;
	
	public static BoilRoom getInstance() {
		return instance;
	}
	
	public static Context getContext() {
		return instance;
	}
	
	@Override
	public void onCreate() {
		
		instance = this;
		mApplicationContext = getApplicationContext();
		
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
		
		initializeEmojiCompat();
        AppLogger.startLogging();
		
		Log.d(TAG, "BoilRoom Initialized!");
		
		
		AppExceptionHandler exceptionHandler = new AppExceptionHandler(
		new DebugActivityErrorDisplayer(),
		new Logger(),
		(AlarmManager) getSystemService(Context.ALARM_SERVICE),
		Thread.getDefaultUncaughtExceptionHandler()
		);
		Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
		
		super.onCreate();
		
	}
	
	public void initializeEmojiCompat() {
		
		EmojiCompat.init(new FontRequestEmojiCompatConfig(getApplicationContext(), new FontRequest("com.google.android.gms.fonts", "com.google.android.gms", "Noto Color Emoji Compat", R.array.com_google_android_gms_fonts_certs))
		.setReplaceAll(true)
		.registerInitCallback(new EmojiCompat.InitCallback() {
			public void onInitialized() {
				Log.d(TAG, "EmojiCompat initialized");
			}
			
			public void onFailed(@Nullable Throwable th) {
				Log.e(TAG, "EmojiCompat initialization failed", th);
			}
		}));
	}
	
	class AppExceptionHandler implements Thread.UncaughtExceptionHandler {
		
		private static final int PENDING_INTENT_REQUEST_CODE = 11111;
		private static final long RESTART_DELAY_MS = 1000L;
		
		private ErrorDisplayer errorDisplayer;
		private Logger logger;
		private AlarmManager alarmManager;
		private Thread.UncaughtExceptionHandler defaultExceptionHandler;
		
		public AppExceptionHandler(ErrorDisplayer errorDisplayer, Logger logger,
		AlarmManager alarmManager, Thread.UncaughtExceptionHandler defaultExceptionHandler) {
			this.errorDisplayer = errorDisplayer;
			this.logger = logger;
			this.alarmManager = alarmManager;
			this.defaultExceptionHandler = defaultExceptionHandler;
		}
		
		@Override
		public void uncaughtException(Thread thread, Throwable throwable) {
			String stackTraceString = Log.getStackTraceString(throwable);
			//logger.log("UNCAUGHT_EXCEPTION", stackTraceString);
			AppLogger.broadcastLog(stackTraceString);
			
			errorDisplayer.displayError(throwable, PENDING_INTENT_REQUEST_CODE);
			
			PendingIntent restartPendingIntent = errorDisplayer.getPendingIntent(PENDING_INTENT_REQUEST_CODE);
			
			alarmManager.setExactAndAllowWhileIdle(
			AlarmManager.ELAPSED_REALTIME_WAKEUP,
			SystemClock.elapsedRealtime() + RESTART_DELAY_MS,
			restartPendingIntent
			);
			
			defaultExceptionHandler.uncaughtException(thread, throwable);
		}
	}
	
	interface ErrorDisplayer {
		void displayError(Throwable throwable, int requestCode);
		PendingIntent getPendingIntent(int requestCode);
	}
	
	class DebugActivityErrorDisplayer implements ErrorDisplayer {
		
		@Override
		public void displayError(Throwable throwable, int requestCode) {
			Intent restartIntent = new Intent(BoilRoom.getContext(), SplashActivity.class);
			restartIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			restartIntent.putExtra("error", Log.getStackTraceString(throwable));
		}
		
		@Override
		public PendingIntent getPendingIntent(int requestCode) {
			return PendingIntent.getActivity(
			BoilRoom.getContext(),
			requestCode,
			new Intent(BoilRoom.getContext(), DebugActivity.class),
			PendingIntent.FLAG_ONE_SHOT
			);
		}
	}
	
	class Logger {
		public void log(String tag, String message) {
			Log.e(tag, message);
		}
	}
}
