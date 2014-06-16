package com.alexan.findevents.event;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.HotEventListAdapter;
import com.alexan.findevents.R;
import com.alexan.findevents.dao.DBEvent;

public class SearchResultActivity extends SherlockActivity {

	private ActionBar vACBar;
	private Spinner vTime;
	private Spinner vCategory;
	private ListView vList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchresult);
		initView();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case android.R.id.home: {
			finish();
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	private void initView() {
		vACBar = getSupportActionBar();
		vACBar.setTitle("搜索结果");
		vACBar.setDisplayHomeAsUpEnabled(true);
		vTime = (Spinner) findViewById(R.id.act_sresult_sp1);
		ArrayAdapter<CharSequence> ac2 = ArrayAdapter.createFromResource(this, R.array.sort_option, 
				R.layout.list_simple_item);
		vTime.setAdapter(ac2);
		
		vCategory = (Spinner) findViewById(R.id.act_sresult_sp2);
		ArrayAdapter<CharSequence> ac3 = ArrayAdapter.createFromResource(this, R.array.time, 
				R.layout.list_simple_item);
		vCategory.setAdapter(ac3);
		
		vList = (ListView) findViewById(R.id.act_sresult_list);
		List<DBEvent> eventList = new ArrayList<DBEvent>();
		HotEventListAdapter hea = new HotEventListAdapter(this, eventList);
		vList.setAdapter(hea);
	}
}
