package com.memehub.network;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.core.widget.*;
import androidx.appcompat.widget.Toolbar.*;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.text.*;
import android.util.*;
import android.widget.*;

//User Libraries
import de.hdodenhof.circleimageview.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.regex.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class MainActivity extends AppCompatActivity {
  
  /*final Fragment fragment1 = new FeedFragment();
	final Fragment fragment2 = new CometsFragment();
	final Fragment fragment3 = new NotificationFragment();
	final Fragment fragment4 = new ProfileFragment();
	final FragmentManager fm = getSupportFragmentManager();
	
	Fragment active = fragment1;*/

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    initialize(savedInstanceState);
    initializeLogic();
    
  }
  
  private void initialize(Bundle savedInstanceState) {
    
  }
  
  private void initializeLogic() {
    
  }
}
