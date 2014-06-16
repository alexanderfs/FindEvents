package com.alexan.findevents.event;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.R;

public class EventCommentActivity extends SherlockActivity {
	
	private ListView vCommentList;
	private EditText vTextContent;
	private Button vSubmit;

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case android.R.id.home: {
			finish();
			break;
		}
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventcomment);
		setTitle("评论列表");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		vCommentList = (ListView) findViewById(R.id.act_eventcomm_list);
		CommentsAdapter cs = new CommentsAdapter(this, null);
		vCommentList.setAdapter(cs);
		
		vSubmit = (Button) findViewById(R.id.act_eventcomm_submit);
		vSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(EventCommentActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
