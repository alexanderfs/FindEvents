package com.alexan.findevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class PickSpotActivity extends SherlockActivity {
	
	private ListView vList;
	private String[] spotList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickspot);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		spotList = getResources().getStringArray(R.array.hotspot);
		vList = (ListView) findViewById(R.id.act_pickspot);
		ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.hotspot, 
				android.R.layout.simple_list_item_1); 
		vList.setAdapter(aa);
		vList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.putExtra("spot_name", spotList[position]);
				PickSpotActivity.this.setResult(Activity.RESULT_OK, i);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case android.R.id.home: {
			this.setResult(Activity.RESULT_CANCELED);
			this.finish();
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}
	
}
