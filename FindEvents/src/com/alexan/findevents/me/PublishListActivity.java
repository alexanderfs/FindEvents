package com.alexan.findevents.me;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.HotEventListAdapter;
import com.alexan.findevents.R;
import com.alexan.findevents.event.EventSearchActivity;
import com.alexan.findevents.event.PublishEventActivity;
import com.alexan.findevents.dao.DBEvent;

public class PublishListActivity extends SherlockActivity {
	private ListView vList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("我的发布");
		setContentView(R.layout.activity_publishlist);
		initView();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case android.R.id.home: {
			finish();
			return true;
		}
		case R.id.action_framework_add: {
			Intent i = new Intent(this, PublishEventActivity.class);
			startActivity(i);
			return true;
		}
		case R.id.action_framework_search: {
			Intent i = new Intent(this, EventSearchActivity.class);
			startActivity(i);
			return true;
		}
		}
		return super.onOptionsItemSelected(item);
	}



	private void initView() {
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		vList = (ListView) findViewById(R.id.act_publishlist_list);
		List<DBEvent> eventList = new ArrayList<DBEvent>();
		for(int i = 0; i < 5; i++) {
			DBEvent ee = new DBEvent();
			eventList.add(ee);
		}
		HotEventListAdapter hea = new HotEventListAdapter(this, eventList);
		vList.setAdapter(hea);
	}
}
