package com.alexan.findevents;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexan.findevents.dao.DBCategoryDao.Properties;
import com.alexan.findevents.dao.DBEvent;
import com.alexan.findevents.dao.DBEventImage;
import com.alexan.findevents.dao.DBImage;
import com.alexan.findevents.R;
import com.alexan.findevents.util.DBHelper;
import com.alexan.findevents.util.DensityUtil;
import com.alexan.findevents.util.ImageUtil;
import com.alexan.findevents.util.StringFormatUtil;
import com.alexan.findevents.util.TimeUtil;

import de.greenrobot.dao.query.QueryBuilder;

public class HotEventListAdapter extends BaseAdapter {
	
	private Activity mCtx;
	private List<DBEvent> eventList;
	
	public HotEventListAdapter(Activity ctx, List<DBEvent> eventList) {
		mCtx = ctx;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vh;
		if(convertView == null) {
			convertView = mCtx.getLayoutInflater().inflate(R.layout.list_hotevent, null);
			vh = new ViewHolder();
			vh.img = (ImageView) convertView.findViewById(R.id.list_hotevent_img);
			vh.title = (TextView) convertView.findViewById(R.id.list_hotevent_title);
			vh.time = (TextView) convertView.findViewById(R.id.list_hotevent_time);
			vh.addr = (TextView) convertView.findViewById(R.id.list_hotevent_addr);
			vh.others = (TextView) convertView.findViewById(R.id.list_hotevent_others);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		
		if(eventList.get(position).getId() != null && eventList.get(position).getImages() != null 
				&& eventList.get(position).getImages().size() > 0) {
			DBEventImage ei = eventList.get(position).getImages().get(0);
			QueryBuilder<DBImage> qb = DBHelper.getInstance(mCtx).getImageDao()
					.queryBuilder().where(Properties.Id.eq(ei.getImageID()));
			if(qb.list().size() > 0) {
				Bitmap bm = ImageUtil.decodeSampledBitmapFromPath(qb.list().get(0).getImageUrl(), DensityUtil.dip2px(mCtx, 96f), 
						DensityUtil.dip2px(mCtx, 96f));
				vh.img.setImageBitmap(bm);
			}
		} else {
			//vh.img.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.u36));
		}
		
		String tmp = eventList.get(position).getTitle();
		vh.title.setText(tmp == null ? "DEFAULT TITLE" : tmp);
		
		vh.time.setText(TimeUtil.getDateSpanString(eventList.get(position).getStarttime(), 
				eventList.get(position).getEndtime()));
		vh.addr.setText(StringFormatUtil.buildAddrString(eventList.get(position)));
		vh.others.setText(ImageUtil.getEventOtherDetail(eventList.get(position), mCtx));
		return convertView;
	}

	private static class ViewHolder {
		ImageView img;
		TextView title;
		TextView time;
		TextView addr;
		TextView others;
	}
}
