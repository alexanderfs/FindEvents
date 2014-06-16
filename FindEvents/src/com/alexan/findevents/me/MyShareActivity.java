package com.alexan.findevents.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.R;
import com.alexan.findevents.event.EventDetailActivity;
import com.alexan.findevents.friend.FriendCircleAdapter;

public class MyShareActivity extends SherlockActivity {
	private ListView vMainList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("我的分享");
		setContentView(R.layout.activity_myshare);
		initView();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case android.R.id.home: {
			finish();
			return true;
		}
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void initView() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vMainList = (ListView) findViewById(R.id.act_myshare_mainlist);
		FriendCircleAdapter fca = new FriendCircleAdapter(this);
		vMainList.setAdapter(fca);
		vMainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MyShareActivity.this, EventDetailActivity.class);
				startActivity(i);
			}
		});
	}
}
