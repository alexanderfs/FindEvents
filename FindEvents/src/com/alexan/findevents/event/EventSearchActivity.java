package com.alexan.findevents.event;

import java.util.HashSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.R;
import com.alexan.findevents.dao.DBCategory;

public class EventSearchActivity extends SherlockActivity {
	
	private ActionBar vACBar;
	private EditText vSearchET;
	private Button vAdvanceBtn;
	private LinearLayout vLL;
	private Spinner vAddrList;
	private RadioGroup vTimeRG;
	private GridView vCategoryList;
	private Button vSubmit;
	
	private boolean isAdvanceFlag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventsearch);
		
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
		vACBar.setTitle("搜索活动");
		vACBar.setDisplayHomeAsUpEnabled(true);
		vSearchET = (EditText) findViewById(R.id.act_eventsearch_keyword);
		vAdvanceBtn = (Button) findViewById(R.id.act_eventsearch_advance);
		vAdvanceBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(isAdvanceFlag) {
					vLL.setVisibility(View.GONE);
					isAdvanceFlag = false;
				} else {
					vLL.setVisibility(View.VISIBLE);
					isAdvanceFlag = true;
				}
			}
		});
		vLL = (LinearLayout) findViewById(R.id.act_eventsearch_advcontent);
		vLL.setVisibility(View.GONE);
		
		vAddrList = (Spinner) findViewById(R.id.act_eventsearch_spots);
		ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.spots, R.layout.list_simple_item);
		aa = ArrayAdapter.createFromResource(this, R.array.spots, R.layout.list_simple_item);
		vAddrList.setAdapter(aa);
		
		vTimeRG = (RadioGroup) findViewById(R.id.act_eventsearch_timerg);
		vTimeRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				switch(arg1) {
				case R.id.act_eventsearch_timerb1:
					break;
				case R.id.act_eventsearch_timerb2:
					break;
				case R.id.act_eventsearch_timerb3:
					break;
				default:
					break;
				}
			}
		});
		vCategoryList = (GridView) findViewById(R.id.act_eventsearch_category);
		CategoryCBAdapter ca = new CategoryCBAdapter(this, new CategoryRecorder());
		vCategoryList.setAdapter(ca);
		
		vSubmit = (Button) findViewById(R.id.act_eventsearch_submit);
		vSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(EventSearchActivity.this, SearchResultActivity.class);
				startActivity(i);
			}
		});
	}
	
	private class CategoryRecorder implements CategorySelectListener {

		@Override
		public void setSelectedCategory(DBCategory category, boolean checked) {
			// TODO Auto-generated method stub
			if(checked) {
				categorySet.add(category);
			} else {
				categorySet.remove(category);
			}
		}
		
	}
	
	private HashSet<DBCategory> categorySet = new HashSet<DBCategory>();

}
