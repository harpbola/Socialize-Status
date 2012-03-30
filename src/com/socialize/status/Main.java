package com.socialize.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.flurry.android.FlurryAgent;
import com.socialize.Socialize;
import com.socialize.entity.Entity;
import com.socialize.status.R;

public class Main extends ListActivity {
	static final String LOG_KEY = "Main";
	
	ListAdapter adapter;
	public static String[][] entitiesData = new String[][]{
			{"Http Requests", "http-requests"},
			{"Notifications", "notifications"},
			{"Latest Android Build", "android-builds"},
			{"Latest iOS Build" , "ios-builds"}
	};
	private ListAdapter getAdapter(){
	     List<HashMap<String,String>> entitiesList = new ArrayList<HashMap<String,String>>();
	     for(int i = 0; i < entitiesData.length; i++) {
	     	HashMap<String,String> item = new HashMap<String,String>();
	     	item.put("text1", entitiesData[i][0]);
	     	item.put("text2", entitiesData[i][1]);
	     	entitiesList.add(item);
	     }
	     return new SimpleAdapter(this,  
	     		entitiesList, 
	     		android.R.layout.two_line_list_item,
	     		new String[] {"text1","text2"}, 
	     		new int[] {android.R.id.text1, android.R.id.text2 });
	}
	
	@Override
	protected void onListItemClick(ListView list, View source, int position, long id) {
		Intent intent = new Intent();
		intent.setClass(this, Comments.class);
		
		Bundle params = new Bundle();
		params.putStringArray("entity_data", entitiesData[position]); 
		intent.putExtras(params);
		
		startActivity(intent);
	}


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = this.getAdapter();
        setListAdapter(adapter);

		// Create an entity object, including a name.
		final Entity entity = Entity.newInstance("socialize-status-main", "Socialize Status");
		Socialize.getSocialize().setEntityLoader(new SampleEntityLoader());
		View actionBarWrapped = Socialize.getSocializeUI().showActionBar(this, R.layout.entities, entity);

		// Now set the view for your activity to be the wrapped view.
		setContentView(actionBarWrapped);		 
    }
    
    public void onStart()
    {
       super.onStart();
       FlurryAgent.enableAppCircle();
       FlurryAgent.onStartSession(this, "I6CY6JBQJU2NJFAEH6AG");
    }
    
    public void onStop()
    {
       super.onStop();
       FlurryAgent.onEndSession(this);
    }
}