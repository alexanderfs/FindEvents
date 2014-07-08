package com.alexan.findevents.me;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.HotEventListAdapter;
import com.alexan.findevents.R;
import com.alexan.findevents.dao.DBEvent;
import com.alexan.findevents.dao.DBEventDao;
import com.alexan.findevents.event.EventDetailActivity;
import com.alexan.findevents.event.EventSearchActivity;
import com.alexan.findevents.event.PublishEventActivity;
import com.alexan.findevents.util.DBHelper;

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

	private long getUserID() {
		return PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong("curr_user_id", 0);
	}
	
	private List<DBEvent> eventList;

	private void initView() {
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		vList = (ListView) findViewById(R.id.act_publishlist_list);
		eventList = DBHelper.getInstance(this).getEventDao().queryBuilder()
				.where(DBEventDao.Properties.UserID.eq(getUserID())).list();
		HotEventListAdapter hea = new HotEventListAdapter(this, eventList);
		vList.setAdapter(hea);
		vList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Bundle b = new Bundle();
				b.putLong("event_id", eventList.get(position).getId());
				Intent i = new Intent(PublishListActivity.this, EventDetailActivity.class);
				i.putExtras(b);
				startActivity(i);
			}
		});
	}
}
