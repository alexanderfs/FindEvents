package com.alexan.findevents.settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.FrameworkActivity;
import com.alexan.findevents.LoginActivity;
import com.alexan.findevents.R;

public class AccountInfoActivity extends SherlockActivity {
	
	private RelativeLayout vIcon;
	private Button vChangePwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accountinfo);
		initView();
		
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case android.R.id.home: {
			Intent mainInt = new Intent(this,
					FrameworkActivity.class);
			mainInt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(mainInt);
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}



	private void initView() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vIcon = (RelativeLayout) findViewById(R.id.act_accountinfo_icon);
		vIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		vChangePwd = (Button) findViewById(R.id.act_accountinfo_logout);
		vChangePwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
					.edit().putString("curr_user", "none").commit();
				Toast.makeText(AccountInfoActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
}
