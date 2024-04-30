package com.memehub.network.application;

import com.memehub.network.R;

import android.content.Context;
import androidx.multidex.MultiDex;

public class LegacyMultidexBoilRoom extends BoilRoom {
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
