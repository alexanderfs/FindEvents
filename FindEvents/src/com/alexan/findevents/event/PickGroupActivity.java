package com.alexan.findevents.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.R;

public class PickGroupActivity extends SherlockActivity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case android.R.id.home: {
			setResult(RESULT_CANCELED);
			finish();
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	
	private ListView vList;
	private String[] groupList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickaddr);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		groupList = getResources().getStringArray(R.array.circle);
		ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, 
				R.array.circle, android.R.layout.simple_list_item_1);
		vList = (ListView) findViewById(R.id.act_pickaddr_list);
		vList.setAdapter(aa);
		vList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.putExtra("addr", groupList[position]);
				setResult(RESULT_OK, i);
				finish();
			}
		});
	}

}
