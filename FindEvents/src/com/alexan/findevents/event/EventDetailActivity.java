package com.alexan.findevents.event;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.R;
import com.alexan.findevents.dao.DBCategory;
import com.alexan.findevents.dao.DBComment;
import com.alexan.findevents.dao.DBCommentDao;
import com.alexan.findevents.dao.DBEvent;
import com.alexan.findevents.dao.DBEventCategory;
import com.alexan.findevents.dao.DBImage;
import com.alexan.findevents.dao.DBImageDao.Properties;
import com.alexan.findevents.dao.DBPerson;
import com.alexan.findevents.dao.DBPersonDao;
import com.alexan.findevents.util.DBHelper;
import com.alexan.findevents.util.DensityUtil;
import com.alexan.findevents.util.ImageUtil;
import com.alexan.findevents.util.StringFormatUtil;
import com.alexan.findevents.util.TimeUtil;
import com.alexan.findevents.view.ListViewForScrollView;

import de.greenrobot.dao.query.QueryBuilder;

public class EventDetailActivity extends SherlockActivity {
	
	private ActionBar vACBar;
	private TextView vTitle;
	private TextView vOtherDetail;
	private View vCollect;
	private View vShare;
	private View vComment;
	private View vReport;
	private ListViewForScrollView vDetail;
	private TextView vDesc;
	private ListViewForScrollView vCommentsList;
	private ScrollView vSV;
	private ImageView vImage;
	private DBEvent currEvent = new DBEvent();
	private boolean isFake;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventdetail);
		
		initView();
		
		try {
			currEvent = DBHelper.getInstance(this).getEventDao().loadByRowId(getIntent().getExtras().getLong("event_id"));
			if(currEvent == null) {
				currEvent = new DBEvent();
			}
		} catch (Exception e) {
			currEvent = new DBEvent();
			e.printStackTrace();
		}
		
		initData();
	}
	
	private void initData() {
		vTitle.setText(currEvent.getTitle() == null ? "DEFAULT TITLE" : currEvent.getTitle());
		
		if(currEvent.getId() != null) {
			QueryBuilder<DBImage> qb = DBHelper.getInstance(this).getImageDao()
					.queryBuilder().where(Properties.EventID.eq(currEvent.getId()));
			if(qb.list().size() > 0) {
				Bitmap bm = ImageUtil.decodeSampledBitmapFromPath(qb.list().get(0).getImageUrl(), DensityUtil.dip2px(this, 96f), 
						DensityUtil.dip2px(this, 96f));
				vImage.setImageBitmap(bm);
			}
		}
		
		vOtherDetail.setText(ImageUtil.getEventOtherDetail(currEvent, this));
		DetailAdapter da = new DetailAdapter();
		vDetail.setAdapter(da);
		
		vDesc = (TextView) findViewById(R.id.act_eventdetail_desc);
		vDesc.setText(currEvent.getDescription());
		
		/*vCommentsList = (ListViewForScrollView) findViewById(R.id.act_eventdetail_comments);
		CommentsAdapter ca;
		if(currEvent.getId() == null) {
			ca = new CommentsAdapter(this, new ArrayList<DBComment>());
		} else {
			ca = new CommentsAdapter(this,
					DBHelper.getInstance(this).getCommentDao().queryBuilder().where(DBCommentDao.Properties.EventID.eq(currEvent.getId())).list());
		}
		
		vCommentsList.setAdapter(ca);*/
		
	}
	
	
	/*public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.menu_eventdetail, menu);
		MenuItem shareItem = menu.findItem(R.id.action_eventdetail_share);  
        ShareActionProvider mShareActionProvider = (ShareActionProvider)  
                shareItem.getActionProvider();  
        mShareActionProvider.setShareIntent(getDefaultIntent());  
		return true;
	}*/
	
	
	
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


	void initView() {
		vACBar = getSupportActionBar();
		setTitle("活动详情");
		vACBar.setDisplayHomeAsUpEnabled(true);
		vTitle = (TextView)findViewById(R.id.act_eventdetail_title);
		vImage = (ImageView) findViewById(R.id.act_eventdetail_image);
		vOtherDetail = (TextView)findViewById(R.id.act_eventdetail_others);
		vCollect = findViewById(R.id.act_eventdetail_btn1);
		vCollect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DBComment dc = new DBComment();
				dc.setEventID(currEvent.getId());
				dc.setCommentType(2);
				dc.setUserID(currEvent.getUserID());
				/*QueryBuilder<DBPerson> qbp = DBHelper.getInstance(EventDetailActivity.this).getPersonDao()
						.queryBuilder().where(DBPersonDao.Properties.UserID.eq(new Long(dc.getUserID())));
				dc.setUsername(qbp.list().get(0).getNickname());*/
				String username = DBHelper.getInstance(EventDetailActivity.this).getPersonDao().load(currEvent.getUserID()).getNickname();
				dc.setUsername(username);
				dc.setComentContent(username + "收藏了一个活动");
				dc.setTimestamp(System.currentTimeMillis());
				DBHelper.getInstance(EventDetailActivity.this).getCommentDao().insert(dc);
				currEvent.setCollectionNum((currEvent.getCollectionNum() == null ? 0 : currEvent.getCollectionNum()) + 1);
				DBHelper.getInstance(EventDetailActivity.this).getEventDao().update(currEvent);
				vOtherDetail.setText(ImageUtil.getEventOtherDetail(currEvent, EventDetailActivity.this));
				Toast.makeText(EventDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
			}
		});
		vShare = findViewById(R.id.act_eventdetail_btn2);
		vShare.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setAction(Intent.ACTION_SEND);
				i.putExtra(Intent.EXTRA_TEXT, currEvent.getTitle());
				i.setType("text/plain");
				startActivity(i);
			}
		});
		vComment = findViewById(R.id.act_eventdetail_btn3);
		vComment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(EventDetailActivity.this, EventCommentActivity.class);
				i.putExtra("eventID", currEvent.getId());
				startActivityForResult(i, 0);
			}
		});
		vReport = findViewById(R.id.act_eventdetail_btn4);
		vReport.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(EventDetailActivity.this, EventReportActivity.class);
				startActivityForResult(i, 1);
			}
		});
		vSV = (ScrollView) findViewById(R.id.act_eventdetail_scroll);
		
		vSV.smoothScrollTo(0, 0);
		vDetail = (ListViewForScrollView) findViewById(R.id.act_eventdetail_list);
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 0) {
			currEvent = DBHelper.getInstance(this).getEventDao().load(currEvent.getId());
			vOtherDetail.setText(ImageUtil.getEventOtherDetail(currEvent, this));
		}
	}



	private class DetailAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View currView;
			switch(position) {
			case 0: {
				currView = getLayoutInflater().inflate(R.layout.list_image_text_item, null);
				ImageView iv = (ImageView) currView.findViewById(R.id.list_it_icon);
				//iv.setImageDrawable(getResources().getDrawable(R.drawable.u77));
				TextView tv = (TextView) currView.findViewById(R.id.list_it_desc);
				tv.setText(TimeUtil.getDateSpanString(currEvent.getStarttime(), currEvent.getEndtime()));
			}
				break;
			case 1: {
				currView = getLayoutInflater().inflate(R.layout.list_image_text_item, null);
				ImageView iv = (ImageView) currView.findViewById(R.id.list_it_icon);
				//iv.setImageDrawable(getResources().getDrawable(R.drawable.u77));
				TextView tv = (TextView) currView.findViewById(R.id.list_it_desc);
				tv.setText(StringFormatUtil.buildAddrString(currEvent));
			}
				break;
			case 2: {
				currView = getLayoutInflater().inflate(R.layout.list_image_text_item, null);
				ImageView iv = (ImageView) currView.findViewById(R.id.list_it_icon);
				//iv.setImageDrawable(getResources().getDrawable(R.drawable.u77));
				TextView tv = (TextView) currView.findViewById(R.id.list_it_desc);
				tv.setText("NO TICKET PRICE ITEM IN PUBLISH");
			}
				break;
			case 3: {
				currView = getLayoutInflater().inflate(R.layout.list_image_text_item, null);
				ImageView iv = (ImageView) currView.findViewById(R.id.list_it_icon);
				//iv.setImageDrawable(getResources().getDrawable(R.drawable.u77));
				TextView tv = (TextView) currView.findViewById(R.id.list_it_desc);
				if(currEvent.getId() == null || currEvent.getCategories() == null) {
					tv.setText("DEFAULT CATEGORY");
				} else {
					StringBuilder sb = new StringBuilder();
					for(DBEventCategory eca: currEvent.getCategories()) {
						DBCategory ca = DBHelper.getInstance(EventDetailActivity.this).getCategoryDao().loadByRowId(eca.getCategoryID());
						sb.append(ca.getName()).append(" ");
					}
					tv.setText(sb.toString());
				}
				
			} 
				break;
			default: {
				currView = getLayoutInflater().inflate(R.layout.list_image_text_item, null);
				ImageView iv = (ImageView) currView.findViewById(R.id.list_it_icon);
				//iv.setImageDrawable(getResources().getDrawable(R.drawable.u77));
				TextView tv = (TextView) currView.findViewById(R.id.list_it_desc);
				if(currEvent.getId() == null) {
					tv.setText("DEFAULT USER");
				} else {
					DBPerson p = DBHelper.getInstance(EventDetailActivity.this).getPersonDao().loadByRowId(currEvent.getUserID());
					if(p != null) {
						tv.setText(p.getNickname());
					} else {
						tv.setText("load error");
					}
				}
				
			} 
				break;
			}
			return currView;
		}
		
	}
}
