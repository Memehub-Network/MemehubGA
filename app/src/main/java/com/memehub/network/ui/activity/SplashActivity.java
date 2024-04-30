package com.memehub.network.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.internal.EdgeToEdgeUtils;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
	
	private static int SLEEP = 2000;
	private static final String TAG = "SplashActivity";
	private Handler handler = new Handler();
	
	private Intent intent;
	
	private boolean started = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState == null) {
		}
		
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				initAuth();
			}
		}, SLEEP);
		
	}
	
	@Override
	public void onBackPressed() {
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		handler = null;
	}
	
	void initAuth(){
		intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	
}
