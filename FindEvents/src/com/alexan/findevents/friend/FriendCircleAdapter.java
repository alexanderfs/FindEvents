package com.alexan.findevents.friend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexan.findevents.R;
import com.alexan.findevents.event.EventDetailActivity;
import com.alexan.findevents.me.MeMainPageActivity;

public class FriendCircleAdapter extends BaseAdapter {

	private Activity mCtx;
	private List<FCEntity> eventList;
	
	public FriendCircleAdapter(Activity ctx, List<FCEntity> eventList) {
		this.mCtx = ctx;
		if(eventList == null) {
			eventList = new ArrayList<FCEntity>();
		}
		this.eventList = eventList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return eventList.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vh;
		if(convertView == null) {
			convertView = mCtx.getLayoutInflater().inflate(R.layout.list_friendcircle_item, null);
			vh = new ViewHolder();
			vh.image = (ImageView) convertView.findViewById(R.id.list_fc_item_image);
			vh.title = (TextView) convertView.findViewById(R.id.list_fc_item_title);
			vh.comment = (TextView) convertView.findViewById(R.id.list_fc_item_comment);
			vh.event = (LinearLayout) convertView.findViewById(R.id.list_fc_item_event);
			vh.eventImage = (ImageView) convertView.findViewById(R.id.list_fc_item_eventimage);
			vh.eventDesc = (TextView) convertView.findViewById(R.id.list_fc_item_eventdesc);
			vh.time = (TextView) convertView.findViewById(R.id.list_fc_item_time);
			//vh.operation = (Button) convertView.findViewById(R.id.list_fc_item_operation);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.title.setText(eventList.get(position).getComment().getComentContent());
		vh.comment.setText(eventList.get(position).getEvent().getTitle());
		vh.eventDesc.setText(eventList.get(position).getEvent().getDescription());
		vh.time.setText(new SimpleDateFormat("yyyy-MM-DD HH:mm", Locale.CHINA).format(eventList.get(position).getComment().getTimestamp()));
		vh.event.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle b = new Bundle();
				b.putLong("event_id", eventList.get(position).getEvent().getId());
				Intent i = new Intent(mCtx, EventDetailActivity.class);
				i.putExtras(b);
				mCtx.startActivity(i);
			}
		});
		return convertView;
	}

	private static class ViewHolder {
		ImageView image;
		TextView title;
		TextView comment;
		LinearLayout event;
		ImageView eventImage;
		TextView eventDesc;
		TextView time;
		//Button operation;
	}
}
