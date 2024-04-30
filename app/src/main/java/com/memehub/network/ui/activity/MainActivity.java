package com.memehub.network.ui.activity;

import com.memehub.network.R;
import com.memehub.network.ui.base.BaseActivity;

import android.os.*;
import android.view.*;
import android.util.*;
import android.widget.*;

import com.google.android.material.transition.MaterialContainerTransform;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends BaseActivity {
	
	private Fragment currentFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		initViews();
		initEvents();
		
	}
	
	void initViews(){
		
	}
	
	void initEvents(){
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("started", true);
	}
	
}
