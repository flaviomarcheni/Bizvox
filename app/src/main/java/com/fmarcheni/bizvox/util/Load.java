package com.fmarcheni.bizvox.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.fmarcheni.bizvox.R;


@SuppressLint("InflateParams")
public class Load {
	private View load;
	
	public Load(final Activity activity) {
		LayoutInflater inflater = activity.getLayoutInflater();
		Window window = activity.getWindow();
		
		load =  inflater.inflate(R.layout.componente_load, null);
		load.setVisibility(View.INVISIBLE);
		
		window.addContentView(load, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		
		load.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(activity, activity.getString(R.string.wait), Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void show() {
		if(load != null)
			load.setVisibility(View.VISIBLE);
	}
	
	public void hide() {
		if(load != null)
			load.setVisibility(View.GONE);
	}
	
	public void setOnClickListener(OnClickListener onClickListener) {
		if(load != null)
			load.setOnClickListener(onClickListener);
	}
}
