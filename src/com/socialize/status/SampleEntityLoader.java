package com.socialize.status;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.socialize.entity.Entity;
import com.socialize.status.R;
import com.socialize.ui.SocializeEntityLoader;

public class SampleEntityLoader implements SocializeEntityLoader {

	private Toast toast;

	@Override
	public void loadEntity(Activity activity, Entity entity) {
		
		int index = -1;
		if(entity.getKey().equals("http-requests")){
				index = 0;
		}
		else {
			if(entity.getKey().equals("notifications")){
				index = 1;
			}
		}

		Intent intent = new Intent();
		if (index > -1)
		{
			intent.setClass(activity, Comments.class);
			Bundle params = new Bundle();
			params.putStringArray("entity_data", Main.entitiesData[index]); 
			intent.putExtras(params);
		}
		else{
			intent.setClass(activity, Main.class);
		}
		activity.startActivity(intent);
		
		// Demo only.. you would usually load your entity here.
		String msg = "Clicked on entity with key: " + entity.getKey();
		if(toast != null) {
			toast.cancel();
			toast.setText(msg);
		}
		else {
			toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
		}
		
		toast.show();
	}

	@Override
	public boolean canLoad(Context arg0, Entity arg1) {
		return true;
	}

}
