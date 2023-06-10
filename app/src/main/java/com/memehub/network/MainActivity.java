package com.memehub.network;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.widget.*;
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

import okhttp3.logging.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class MainActivity extends AppCompatActivity {
  
  
  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
  }
}
