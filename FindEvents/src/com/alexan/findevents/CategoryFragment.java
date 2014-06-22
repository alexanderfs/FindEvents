package com.alexan.findevents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexan.findevents.dao.DBCategory;
import com.alexan.findevents.dao.DBEvent;
import com.alexan.findevents.dao.DBEventCategory;
import com.alexan.findevents.dao.DBEventDao;
import com.alexan.findevents.event.EventDetailActivity;
import com.alexan.findevents.util.DBHelper;

import de.greenrobot.dao.query.QueryBuilder;

public class CategoryFragment extends Fragment {

	private Spinner vSort;
	private int spSort;
	private TextView vCategory0;
	private TextView vCategory1;
	private TextView vCategory2;
	private TextView vCategory3;
	private TextView vCategory4;
	private TextView vCategory5;
	private TextView vCategory6;
	private TextView vCategory7;
	private TextView vCategory8;
	private TextView vCategory9;
	private TextView vCategory10;
	
	private TextView vTime1;
	private TextView vTime2;
	private TextView vTime3;
	
	private ListView vList;
	private String currCity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		currCity = getArguments().getString("curr_city");
		View v = inflater.inflate(R.layout.fragment_category, container, false);
		initView(v);
		((FrameworkActivity)getActivity()).setPFlag(3);
		((FrameworkActivity)getActivity()).supportInvalidateOptionsMenu();
		getActivity().setTitle("");
		categoryList = DBHelper.getInstance(getActivity()).getCategoryDao().loadAll();
		return v;
	}

	private String[] hotSpotsList;
	private TextView currCategory;
	private TextView currTime;
	private int category;
	private int time;

	void initView(View v) {
		vCategory0 = (TextView) v.findViewById(R.id.act_ca_ca0);
		vCategory0.setTextColor(Color.BLUE);
		currCategory = vCategory0;
		vCategory0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory0) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory0;
				currCategory.setTextColor(Color.BLUE);
				category = 0;
				reloadData();
			}
		});
		
		vCategory1 = (TextView) v.findViewById(R.id.act_ca_ca1);
		currCategory = vCategory1;
		vCategory1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory1) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory1;
				currCategory.setTextColor(Color.BLUE);
				category = 1;
				reloadData();
			}
		});
		vCategory2 = (TextView) v.findViewById(R.id.act_ca_ca2);
		vCategory2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory2) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory2;
				currCategory.setTextColor(Color.BLUE);
				category = 2;
				reloadData();
			}
		});
		vCategory3 = (TextView) v.findViewById(R.id.act_ca_ca3);
		vCategory3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory3) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory3;
				currCategory.setTextColor(Color.BLUE);
				category = 3;
				reloadData();
			}
		});
		vCategory4 = (TextView) v.findViewById(R.id.act_ca_ca4);
		vCategory4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory4) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory4;
				currCategory.setTextColor(Color.BLUE);
				category = 4;
				reloadData();
			}
		});
		vCategory5 = (TextView) v.findViewById(R.id.act_ca_ca5);
		vCategory5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory5) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory5;
				currCategory.setTextColor(Color.BLUE);
				category = 5;
				reloadData();
			}
		});
		vCategory6 = (TextView) v.findViewById(R.id.act_ca_ca6);
		vCategory6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory6) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory6;
				currCategory.setTextColor(Color.BLUE);
				category = 6;
				reloadData();
			}
		});
		vCategory7 = (TextView) v.findViewById(R.id.act_ca_ca7);
		vCategory7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory7) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory7;
				currCategory.setTextColor(Color.BLUE);
				category = 7;
				reloadData();
			}
		});
		vCategory8 = (TextView) v.findViewById(R.id.act_ca_ca8);
		vCategory8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory8) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory8;
				currCategory.setTextColor(Color.BLUE);
				category = 8;
				reloadData();
			}
		});
		vCategory9 = (TextView) v.findViewById(R.id.act_ca_ca9);
		vCategory9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory9) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory9;
				currCategory.setTextColor(Color.BLUE);
				category = 9;
				reloadData();
			}
		});
		vCategory10 = (TextView) v.findViewById(R.id.act_ca_ca10);
		vCategory10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currCategory == vCategory10) {
					return;
				}
				currCategory.setTextColor(Color.BLACK);
				currCategory = vCategory10;
				currCategory.setTextColor(Color.BLUE);
				category = 10;
				reloadData();
			}
		});
		
		vTime1 = (TextView) v.findViewById(R.id.act_ca_ca11);
		vTime1.setTextColor(Color.BLUE);
		currTime = vTime1;
		vTime1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currTime == vTime1) {
					return;
				}
				currTime.setTextColor(Color.BLACK);
				currTime = vTime1;
				currTime.setTextColor(Color.BLUE);
				time = 0;
				reloadData();
			}
		});
		vTime2 = (TextView) v.findViewById(R.id.act_ca_ca12);
		vTime2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currTime == vTime2) {
					return;
				}
				currTime.setTextColor(Color.BLACK);
				currTime = vTime2;
				currTime.setTextColor(Color.BLUE);
				time = 1;
				reloadData();
			}
		});
		vTime3 = (TextView) v.findViewById(R.id.act_ca_ca13);
		vTime3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currTime == vTime3) {
					return;
				}
				currTime.setTextColor(Color.BLACK);
				currTime = vTime3;
				currTime.setTextColor(Color.BLUE);
				time = 2;
				reloadData();
			}
		});

		vSort = (Spinner) v.findViewById(R.id.act_ca_sort);
		ArrayAdapter<CharSequence> aa2 = ArrayAdapter.createFromResource(
				getActivity(), R.array.sort_option, R.layout.list_simple_item);
		vSort.setAdapter(aa2);
		vSort.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				spSort = position;
				reloadData();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});

		vList = (ListView) v.findViewById(R.id.act_ca_list);
		reloadData();
	}
	
	private List<DBEvent> currEventList = new ArrayList<DBEvent>();
	private Date today, tomorrow;
	private List<DBCategory> categoryList;
	
	private void reloadData() {
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
		switch(time) {
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
		
		List<DBEvent> tmpList;
		if(spSort == 0) {
			tmpList = qbEvent.orderDesc(DBEventDao.Properties.Timestamp).list();
		} else {
			tmpList = qbEvent.orderDesc(DBEventDao.Properties.CollectionNum).list();
		}
		if(category != 0) {
			currEventList.clear();
			for(DBEvent e: tmpList) {
				boolean isFound = false;
				for(DBEventCategory ec: e.getCategories()) {
					DBCategory ca = DBHelper.getInstance(getActivity())
							.getCategoryDao().load(ec.getCategoryID());
					if(ca.getName().equals(categoryList.get(category).getName())) {
						isFound = true;
						break;
					}
				}
				if(isFound) {
					currEventList.add(e);
				}
			}
		} else {
			currEventList = tmpList;
		}
		
		HotEventListAdapter hea = new HotEventListAdapter(getActivity(),
				currEventList);
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
