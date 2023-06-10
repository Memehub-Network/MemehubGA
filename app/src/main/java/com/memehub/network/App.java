package com.memehub.network;

import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

public class App extends Application {
  
  private static Context context;
  public static Context getContext() {
    return context;
  }
  
  @Override
  public void onCreate() {
    super.onCreate();
  }
  
}
