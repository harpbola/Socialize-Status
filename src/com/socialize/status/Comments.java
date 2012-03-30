package com.socialize.status;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.socialize.Socialize;
import com.socialize.entity.Entity;
import com.socialize.entity.Like;
import com.socialize.entity.Share;
import com.socialize.status.R;
import com.socialize.ui.actionbar.ActionBarListener;
import com.socialize.ui.actionbar.ActionBarView;
import com.socialize.ui.actionbar.OnActionBarEventListener;

public class Comments extends Activity {
	static final String LOG_KEY = "Comments";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_KEY, "onCreate");
        // Bundle params
        Bundle params = getIntent().getExtras();
        String[] entityData = params.getStringArray("entity_data");
        
		// Create an entity object, including a name.
		final Entity entity = Entity.newInstance( entityData[1], entityData[0]);
		Log.d(LOG_KEY, "Launch comment view.");
		Socialize.getSocializeUI().showCommentView(this, entity);
    }

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(LOG_KEY, "onPause");
	}
    
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(LOG_KEY, "onResume");
		finish();
	}
    
}