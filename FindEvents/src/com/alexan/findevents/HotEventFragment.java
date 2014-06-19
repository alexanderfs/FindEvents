package com.alexan.findevents;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alexan.findevents.dao.DBEvent;
import com.alexan.findevents.dao.DBEventDao;
import com.alexan.findevents.dao.DBHotEvent;
import com.alexan.findevents.dao.DBImage;
import com.alexan.findevents.dao.DBImageDao.Properties;
import com.alexan.findevents.event.EventDetailActivity;
import com.alexan.findevents.util.DBHelper;
import com.alexan.findevents.util.DensityUtil;
import com.alexan.findevents.util.ImageUtil;
import com.alexan.findevents.util.StringFormatUtil;
import com.alexan.findevents.util.TimeUtil;

import de.greenrobot.dao.query.QueryBuilder;

public class HotEventFragment extends Fragment {
	private ListView vList;
	private View vHead;
	private TextView vTitle;
	private TextView vOtherdetail;
	private TextView vAddress;
	private TextView vTime;
	private SwipeRefreshLayout vRefresh;
	private LinearLayout vLL;
	private ImageView vImage;
	private List<DBHotEvent> hoteventList;
	private List<DBEvent> eventList;
	private DBEvent headEvent = new DBEvent();
	private View eventView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		eventView = inflater.inflate(R.layout.fragment_hotevents, container, false);
		initData();
		initView(eventView);
		((FrameworkActivity)getActivity()).setPFlag(1);
		((FrameworkActivity)getActivity()).supportInvalidateOptionsMenu();
		getActivity().setTitle("");
		return eventView;
	}
	
	private void initData() {
		/*hoteventList = DBHelper.getInstance(getActivity()).getHotEventDao().loadAll();
		if(hoteventList != null && hoteventList.size() > 0) {
			int i = 0;
			for(DBHotEvent he: hoteventList) {
				if(i == 0) {
					headEvent = DBHelper.getInstance(getActivity()).getEventDao().loadByRowId(he.getEventID());
				} else {
					eventList.add(DBHelper.getInstance(getActivity()).getEventDao().loadByRowId(he.getEventID()));
				}
			}
		} else {
			eventList = new ArrayList<DBEvent>();
		}*/
		eventList = DBHelper.getInstance(getActivity()).getEventDao().loadAll();
		if(eventList.size() > 0) {
			headEvent = eventList.get(0);
			eventList.remove(0);
		}
	}
	
	public void reloadData(String city) {
		QueryBuilder<DBEvent> qbEvent = DBHelper.getInstance(getActivity())
				.getEventDao().queryBuilder().where(DBEventDao.Properties.City.eq(city));
		eventList = qbEvent.list();
		if(eventList.size() > 0) {
			headEvent = eventList.get(0);
			eventList.remove(0);
		}
		vTitle.setText(headEvent.getTitle() == null ? "DEFAULT TITLE" : headEvent.getTitle());
		if(headEvent.getId() != null) {
			QueryBuilder<DBImage> qb = DBHelper.getInstance(getActivity()).getImageDao()
					.queryBuilder().where(Properties.EventID.eq(headEvent.getId()));
			if(qb.list().size() > 0) {
				Bitmap bm = ImageUtil.decodeSampledBitmapFromPath(qb.list().get(0).getImageUrl(), DensityUtil.dip2px(getActivity(), 96f), 
						DensityUtil.dip2px(getActivity(), 96f));
				vImage.setImageBitmap(bm);
			}
		}
		vTime.setText(TimeUtil.getDateSpanString(headEvent.getStarttime(), headEvent.getEndtime()));
		vAddress.setText(StringFormatUtil.buildAddrString(headEvent));
		vOtherdetail.setText(ImageUtil.getEventOtherDetail(headEvent, getActivity()));
		HotEventListAdapter hea = new HotEventListAdapter(getActivity(), eventList);
		vList.setAdapter(hea); 
	}
	
	private void initHead() {
		vHead = getActivity().getLayoutInflater().inflate(R.layout.list_hotevent_head, null);
		
		vLL = (LinearLayout) vHead.findViewById(R.id.list_event_head);
		vLL.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle b = new Bundle();
				b.putLong("event_id", headEvent.getId() == null ? -1 : headEvent.getId());
				Intent detailIntent = new Intent(getActivity(), EventDetailActivity.class);
				detailIntent.putExtras(b);
				startActivity(detailIntent);
			}
		});
		vTitle = (TextView) vHead.findViewById(R.id.list_hotevent_head_title);
		vTitle.setText(headEvent.getTitle() == null ? "DEFAULT TITLE" : headEvent.getTitle());
		vImage = (ImageView) vHead.findViewById(R.id.list_hotevent_head_image);
		
		if(headEvent.getId() != null) {
			QueryBuilder<DBImage> qb = DBHelper.getInstance(getActivity()).getImageDao()
					.queryBuilder().where(Properties.EventID.eq(headEvent.getId()));
			if(qb.list().size() > 0) {
				Bitmap bm = ImageUtil.decodeSampledBitmapFromPath(qb.list().get(0).getImageUrl(), DensityUtil.dip2px(getActivity(), 96f), 
						DensityUtil.dip2px(getActivity(), 96f));
				vImage.setImageBitmap(bm);
			}
		}
		
		vOtherdetail = (TextView) vHead.findViewById(R.id.list_hotevent_head_extra);
		vOtherdetail.setText(ImageUtil.getEventOtherDetail(headEvent, getActivity()));
		
		vAddress = (TextView) vHead.findViewById(R.id.list_hotevent_head_address);
		vAddress.setText(StringFormatUtil.buildAddrString(headEvent));
		
		vTime = (TextView) vHead.findViewById(R.id.list_hotevent_head_time);
		vTime.setText(TimeUtil.getDateSpanString(headEvent.getStarttime(), headEvent.getEndtime()));
	}
	
	private void initList(View mainView) {
		initHead();
		vList = (ListView) mainView.findViewById(R.id.fgt_event_list);
		vList.addHeaderView(vHead);
		if(eventList == null) {
			eventList = new ArrayList<DBEvent>();
		}
		HotEventListAdapter hea = new HotEventListAdapter(getActivity(), eventList);
		vList.setAdapter(hea); 
		vList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Bundle b = new Bundle();
				b.putLong("event_id", eventList.get(position).getId() == null ? -1 : eventList.get(position).getId());
				Intent detailIntent = new Intent(getActivity(), EventDetailActivity.class);
				detailIntent.putExtras(b);
				startActivity(detailIntent);
			}
			
		});
	}
	
	private void initView(View mainView) {
		
		vRefresh = (SwipeRefreshLayout) mainView.findViewById(R.id.fgt_event_swipe_container);
		vRefresh.setColorScheme(R.color.white,
                R.color.holo_green_light,  
                R.color.holo_orange_light, 
                R.color.holo_red_light);  
		vRefresh.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				new Handler().postDelayed(new Runnable() {  
		            public void run() {  
		                vRefresh.setRefreshing(false);  
		                  
		            }  
		        }, 3000);
			}
		});
		
		initList(mainView);
	}
}
