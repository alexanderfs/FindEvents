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
import android.widget.ListView;
import android.widget.Spinner;

import com.alexan.findevents.dao.DBEvent;
import com.alexan.findevents.dao.DBEventDao;
import com.alexan.findevents.R;
import com.alexan.findevents.event.EventDetailActivity;
import com.alexan.findevents.util.DBHelper;

import de.greenrobot.dao.query.QueryBuilder;

public class RealtimeFragment extends Fragment {
	
	private Spinner vTime;
	private Spinner vCategory;
	private Spinner vArea;
	private ListView vList;
	private SwipeRefreshLayout vRefresh;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		currCity = getArguments().getString("curr_city");
		View eventView = inflater.inflate(R.layout.activity_rtevent, container, false);
		initView(eventView);
		getActivity().setTitle("");
		((FrameworkActivity)getActivity()).setPFlag(2);
		((FrameworkActivity)getActivity()).supportInvalidateOptionsMenu();
		return eventView;
	}
	
	
	private List<DBEvent> currEventList = new ArrayList<DBEvent>();

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
				Date dtoday = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(dtoday);
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
				Date dtomorrow = c.getTime();
				switch(position) {
				case 0: {
					reloadData(currCity);
					break;
				}
				case 1: {
					reloadData(currCity, dtoday, dtomorrow);
					break;
				}
				case 2: {
					reloadData(currCity, dtomorrow);
					break;
				}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vCategory = (Spinner) v.findViewById(R.id.act_rtevent_sp2);
		ArrayAdapter<CharSequence> ac3 = ArrayAdapter.createFromResource(getActivity(), R.array.category, 
				R.layout.list_simple_item);
		vCategory.setAdapter(ac3);
		
		vArea = (Spinner) v.findViewById(R.id.act_rtevent_sp3);
		ArrayAdapter<CharSequence> ac4 = ArrayAdapter.createFromResource(getActivity(), R.array.district, 
				R.layout.list_simple_item);
		vArea.setAdapter(ac4);
		
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
		currEventList = DBHelper.getInstance(getActivity()).getEventDao().loadAll();
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
	
	private HotEventListAdapter hea;
	private String currCity;
	
	public void reloadData(String city) {
		currCity = city;
		QueryBuilder<DBEvent> qbEvent = DBHelper.getInstance(getActivity()).getEventDao()
				.queryBuilder().where(DBEventDao.Properties.City.eq(city));
		currEventList = qbEvent.list();
		hea = new HotEventListAdapter(getActivity(), currEventList);
		vList.setAdapter(hea);
	}
	
	public void reloadData(String city, Date tomorrow) {
		currCity = city;
		QueryBuilder<DBEvent> qbEvent = DBHelper.getInstance(getActivity()).getEventDao()
				.queryBuilder().where(DBEventDao.Properties.City.eq(city),
				DBEventDao.Properties.Starttime.ge(tomorrow.getTime()));
		currEventList = qbEvent.list();
		hea = new HotEventListAdapter(getActivity(), currEventList);
		vList.setAdapter(hea);
	}
	
	public void reloadData(String city, Date today, Date tomorrow) {
		currCity = city;
		QueryBuilder<DBEvent> qbEvent = DBHelper.getInstance(getActivity()).getEventDao()
				.queryBuilder().where(DBEventDao.Properties.City.eq(city),
				DBEventDao.Properties.Starttime.ge(today.getTime()),
				DBEventDao.Properties.Endtime.le(tomorrow.getTime()));
		currEventList = qbEvent.list();
		hea = new HotEventListAdapter(getActivity(), currEventList);
		vList.setAdapter(hea);
	}

}
