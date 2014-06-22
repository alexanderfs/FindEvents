package com.alexan.findevents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexan.findevents.dao.DBCategory;
import com.alexan.findevents.dao.DBCategoryDao;
import com.alexan.findevents.dao.DBCategoryEvent;
import com.alexan.findevents.dao.DBEvent;
import com.alexan.findevents.dao.DBEventCategory;
import com.alexan.findevents.dao.DBEventDao;
import com.alexan.findevents.R;
import com.alexan.findevents.event.EventDetailActivity;
import com.alexan.findevents.util.DBHelper;

import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

public class RealtimeFragment extends Fragment {
	
	private Spinner vTime;
	private Spinner vCategory;
	private Spinner vArea;
	private ListView vList;
	private SwipeRefreshLayout vRefresh;
	private int sp1;
	private int sp2;
	private int sp3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		currCity = getArguments().getString("curr_city");
		View eventView = inflater.inflate(R.layout.activity_rtevent, container, false);
		initView(eventView);
		getActivity().setTitle("");
		((FrameworkActivity)getActivity()).setPFlag(2);
		((FrameworkActivity)getActivity()).supportInvalidateOptionsMenu();
		categoryList = DBHelper.getInstance(getActivity()).getCategoryDao().loadAll();
		System.out.println(categoryList);
		return eventView;
	}
	
	private Date today, tomorrow;
	

	private List<DBEvent> currEventList = new ArrayList<DBEvent>();
	private List<DBCategory> categoryList;
	
	private class CategoryAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(categoryList == null) {
				categoryList = new ArrayList<DBCategory>();
			}
			return categoryList.size();
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
			View v = getActivity().getLayoutInflater().inflate(R.layout.list_simple_item, null);
			((TextView)v.findViewById(android.R.id.text1)).setText(categoryList.get(position).getName());
			return v;
		}
		
	}
	
	private String[] districtArray;

	private void initView(View v) {
		vTime = (Spinner) v.findViewById(R.id.act_rtevent_sp1);
		ArrayAdapter<CharSequence> ac2 = ArrayAdapter.createFromResource(getActivity(), R.array.time, 
				R.layout.list_simple_item);
		vTime.setAdapter(ac2);
		vTime.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				sp1 = position;
				reloadData();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vCategory = (Spinner) v.findViewById(R.id.act_rtevent_sp2);
		CategoryAdapter ca = new CategoryAdapter();
		vCategory.setAdapter(ca);
		vCategory.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				sp2 = position;
				reloadData();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		districtArray = getActivity().getResources().getStringArray(R.array.district);
		vArea = (Spinner) v.findViewById(R.id.act_rtevent_sp3);
		ArrayAdapter<CharSequence> ac4 = ArrayAdapter.createFromResource(getActivity(), R.array.district, 
				R.layout.list_simple_item);
		vArea.setAdapter(ac4);
		vArea.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				sp3 = position;
				reloadData();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vRefresh = (SwipeRefreshLayout) v.findViewById(R.id.act_rtevent_refresh);
		vRefresh.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						vRefresh.setRefreshing(false);
					}
				}, 3000);
			}
		});
		
		vList = (ListView) v.findViewById(R.id.act_rtevent_list);
		reloadData();
		
	}
	
	private HotEventListAdapter hea;
	private String currCity;
	
	public void reloadData() {
		reloadData(currCity);
	}
	
	public void reloadData(String city) {
		currCity = city;
		today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
		tomorrow = c.getTime();
		QueryBuilder<DBEvent> qbEvent = DBHelper.getInstance(getActivity()).getEventDao()
				.queryBuilder().where(DBEventDao.Properties.City.eq(currCity));
		switch(sp1) {
		case 1: {
			qbEvent.where(DBEventDao.Properties.Starttime.ge(today.getTime()), 
					DBEventDao.Properties.Starttime.le(tomorrow.getTime()));
			break;
		}
		case 2: {
			qbEvent.where(DBEventDao.Properties.Starttime.ge(tomorrow.getTime()));
			break;
		}
		}
		
		if(sp3 != 0) {
			qbEvent.where(DBEventDao.Properties.District.eq(districtArray[sp3]));
		}
		if(sp2 != 0) {
			currEventList.clear();
			List<DBEvent> tmpList = qbEvent.list();
			for(DBEvent e: tmpList) {
				boolean isFound = false;
				for(DBEventCategory ec: e.getCategories()) {
					DBCategory ca = DBHelper.getInstance(getActivity())
							.getCategoryDao().load(ec.getCategoryID());
					if(ca.getName().equals(categoryList.get(sp2).getName())) {
						isFound = true;
						break;
					}
				}
				if(isFound) {
					currEventList.add(e);
				}
			}
		} else {
			currEventList = qbEvent.list();
		}
		
		hea = new HotEventListAdapter(getActivity(), currEventList);
		vList.setAdapter(hea);
		
		vList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Bundle b = new Bundle();
				b.putLong("event_id", currEventList.get(position).getId());
				Intent i = new Intent(getActivity(), EventDetailActivity.class);
				i.putExtras(b);
				startActivity(i);
			}
		});
	}
	

}
