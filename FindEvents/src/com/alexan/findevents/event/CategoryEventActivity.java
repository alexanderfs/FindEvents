package com.alexan.findevents.event;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.HotEventListAdapter;
import com.alexan.findevents.R;
import com.alexan.findevents.dao.DBEvent;

public class CategoryEventActivity extends SherlockActivity {

	private ActionBar vACBar;
	private Spinner vSort;
	private TextView vCategory1;
	private ListView vList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_category);
		initView();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.menu_rtevent, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case android.R.id.home: {
			finish();
			break;
		}
		case R.id.action_rtevent_search: {
			Intent i = new Intent(this, EventSearchActivity.class);
			startActivity(i);
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}
	
	private String[] hotSpotsList;
	
	void initView() {
		vACBar = getSupportActionBar();
		vACBar.setTitle("�������");
		vACBar.setDisplayHomeAsUpEnabled(true);
		hotSpotsList = getResources().getStringArray(R.array.hotspot);
		SpinnerAdapter sa = ArrayAdapter.createFromResource(this, R.array.hotspot, android.R.layout.simple_spinner_dropdown_item);
		vACBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		vACBar.setListNavigationCallbacks(sa, new OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int arg0, long arg1) {
				// TODO Auto-generated method stub
				if(arg0 == hotSpotsList.length - 1) {
					
				} else {
					
				}
				return false;
			}
		});
		
		vCategory1 = (TextView) findViewById(R.id.act_ca_ca1);
		vCategory1.setTextColor(Color.BLUE);
		
		vSort = (Spinner) findViewById(R.id.act_ca_sort);
		ArrayAdapter<CharSequence> aa2 = ArrayAdapter.createFromResource(this, R.array.sort_option, 
				R.layout.list_simple_item);
		vSort.setAdapter(aa2);
		
		vList = (ListView) findViewById(R.id.act_ca_list);
		
		HotEventListAdapter hea = new HotEventListAdapter(this, new ArrayList<DBEvent>());
		vList.setAdapter(hea);
	}

}
