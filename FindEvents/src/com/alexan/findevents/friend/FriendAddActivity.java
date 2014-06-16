package com.alexan.findevents.friend;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.R;

public class FriendAddActivity extends SherlockActivity {
	
	private EditText vSearch;
	private Button vSubmit;
	private LinearLayout vSearchLocal;
	private LinearLayout vSearchWeibo;
	private LinearLayout vSearchQQ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("添加好友");
		setContentView(R.layout.activity_friendadd);
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
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vSearch = (EditText) findViewById(R.id.act_friendadd_searchet);
		vSubmit = (Button) findViewById(R.id.act_friendadd_searchbtn);
		vSearchLocal = (LinearLayout) findViewById(R.id.act_friendadd_local);
		vSearchLocal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		vSearchWeibo = (LinearLayout) findViewById(R.id.act_friendadd_weibo);
		vSearchWeibo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		vSearchQQ = (LinearLayout) findViewById(R.id.act_friendadd_qq);
		vSearchQQ.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
