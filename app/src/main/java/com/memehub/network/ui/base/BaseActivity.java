package com.memehub.network.ui.base;

import com.memehub.network.R;

import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(@Nullable Bundle bundle) {
		super.onCreate(bundle);
		ThemeOverlayUtils.setThemeOverlay(R.id.theme_feature_corner_size, R.style.ThemeOverlay_ShapeCornerSize_ExtraLarge);
	}
	
	/** Returns {@code true} if preferences option is enabled. */
	public boolean isPreferencesEnabled() {
		return false;
	}
	
	/** Returns {@code true} if color harmonization is enabled. */
	public boolean isColorHarmonizationEnabled() {
		return true;
	}
	
	protected void startActivity(Class<?> cls) {
		Intent intent = new Intent(this, cls);
		startActivity(intent);
	}
	
	public void showToast(String _s){
		Toast.makeText(this, _s, Toast.LENGTH_SHORT).show();
	}
	
	public void showSnackbar(@Nullable View v, String val) {
		
		Snackbar.make(findViewById(android.R.id.content), val, Snackbar.LENGTH_SHORT)
		.setAnchorView(v)
		.show();
	}
	
}
