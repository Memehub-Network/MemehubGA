package com.memehub.network.application;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;

public class AppLogger {

    /**
     * Logcat Reader Class
     * <p>
     * Uses:
     * <br>
     *  - "AppLogger.broadcastLog(String)" to manually send a debug log that's then viewable in Logcat Reader
     *  - "AppLogger.stopLogging()" to stop logging
     */

    private static Thread loggerThread = new Thread() {
        @Override
        public void run() {
            isRunning = true;

            try {
                Runtime.getRuntime().exec("logcat -c");
                Process process = Runtime.getRuntime().exec("logcat");

                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String logTxt = bufferedReader.readLine();
                    do {
                        broadcastLog(logTxt);
                    } while (isRunning && ((logTxt = bufferedReader.readLine()) != null));

                    // Thread got stopped, restart if not stopping wantedly
                    if (isRunning) {
                        broadcastLog("AppLogger got killed. Restarting.");
                        startLogging();
                    } else {
                        broadcastLog("AppLogger stopped.");
                    }
                }
            } catch (Exception e) {
                broadcastLog(e.toString());
            }
        }
    };

    private static volatile boolean isRunning = false;

    public static void startLogging() {
        /*
        if (!isRunning) {
            loggerThread.start();
        } else {
            throw new IllegalStateException("AppLogger already running");
        }
        */
        
        if (!isRunning) {
			loggerThread.start();
			isRunning = true;
			Log.d("AppAppLogger", "Logging engine is starting");
			return;
		} else {
            throw new IllegalStateException("AppLogger already running");
        }
		
		//Log.d("AppAppLogger", "AppLogger is already running");
    }

    public static void broadcastLog(String log) {
        Context context = BoilRoom.getContext();

        Intent intent = new Intent();
        intent.setAction("com.sketchware.remod.ACTION_NEW_DEBUG_LOG");
        intent.putExtra("log", log);
        intent.putExtra("packageName", context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static void stopLogging() {
        if (isRunning) {
            isRunning = false;
            broadcastLog("Stopping logger by user request.");
        } else {
            throw new IllegalStateException("AppLogger not running");
        }
    }
}
