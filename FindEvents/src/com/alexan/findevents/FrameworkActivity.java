package com.alexan.findevents;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.dao.DBHotSpot;
import com.alexan.findevents.dao.DBHotSpotDao;
import com.alexan.findevents.event.EventSearchActivity;
import com.alexan.findevents.event.PublishEventActivity;
import com.alexan.findevents.util.DBHelper;
import com.alexan.findevents.util.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import de.greenrobot.dao.query.QueryBuilder;

public class FrameworkActivity extends SlidingFragmentActivity {

	private Fragment mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("");
		setContentView(R.layout.activity_framework);
		
		// set the Above View
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		if (mContent == null) {
			mContent = new HotEventFragment();
		}
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.act_framework_content, mContent).commit();

		// set the Behind View
		setBehindContentView(R.layout.fragment_menu);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fgt_menu_content, new FrameMenuFragment()).commit();

		// customize the SlidingMenu
		setSlidingActionBarEnabled(false);
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setBehindWidth(DensityUtil.dip2px(this, 240f));
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		initData();
		
	}
	
	private void initData() {
		QueryBuilder<DBHotSpot> qhs = DBHelper.getInstance(this).getHotSpotDao()
				.queryBuilder().orderDesc(DBHotSpotDao.Properties.Timestamp).limit(7);
		hsList = qhs.list();
		currCity = hsList.get(0).getName();
		DBHotSpot hs = new DBHotSpot();
		hs.setName("更多");
		hsList.add(hs);
	}
	
	private class SpotListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return hsList.size();
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
			View v = getLayoutInflater().inflate(R.layout.list_simple_item_white, null);
			TextView tv = (TextView) v.findViewById(R.id.list_simple_item_white);
			tv.setText(hsList.get(position).getName());
			return v;
		}
		
	}
	
	private long exitTime = 0;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()- exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	        	finish();
	        }
	        return true;   
	    }
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home: {
			toggle();
			return true;
		}
		case R.id.action_framework_add: {
			Intent i = new Intent(this, PublishEventActivity.class);
			startActivity(i);
			return true;
		}
		case R.id.action_framework_search: {
			Intent i = new Intent(this, EventSearchActivity.class);
			startActivity(i);
			return true;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private int pflag = 1;
	public void setPFlag(int value) {
		pflag = value;
	}
	private List<DBHotSpot> hsList = new ArrayList<DBHotSpot>();
	private SpotListAdapter sla;
	private String currCity;
	public String getCurrCity (){
		return currCity;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		sla = new SpotListAdapter();
		switch(pflag) {
		case 0: {
			getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		}
		break;
		case 1: {
			getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
			getSupportActionBar().setListNavigationCallbacks(sla, new OnNavigationListener() {
				@Override
				public boolean onNavigationItemSelected(int itemPosition, long itemId) {
					// TODO Auto-generated method stub
					//Toast.makeText(FrameworkActivity.this, hsList.get(itemPosition).getName(), Toast.LENGTH_SHORT).show();
					currCity = hsList.get(itemPosition).getName();
					updateData(hsList.get(itemPosition));
					
					return false;
				}
			});
		}
		break;
		case 2: {
			getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
			getSupportActionBar().setListNavigationCallbacks(sla, new OnNavigationListener() {
				@Override
				public boolean onNavigationItemSelected(int itemPosition, long itemId) {
					// TODO Auto-generated method stub
					currCity = hsList.get(itemPosition).getName();
					updateData(hsList.get(itemPosition));
					return false;
				}
			});
		}
		break;
		case 3: {
			getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
			getSupportActionBar().setListNavigationCallbacks(sla, new OnNavigationListener() {
				@Override
				public boolean onNavigationItemSelected(int itemPosition, long itemId) {
					// TODO Auto-generated method stub
					currCity = hsList.get(itemPosition).getName();
					updateData(hsList.get(itemPosition));
					return false;
				}
			});
		}
		break;
		case 4: {
			final ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, 
					R.array.friendfunc, R.layout.list_simple_item_white);
			getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
			getSupportActionBar().setListNavigationCallbacks(aa, new OnNavigationListener() {
				
				@Override
				public boolean onNavigationItemSelected(int itemPosition, long itemId) {
					// TODO Auto-generated method stub
					Toast.makeText(FrameworkActivity.this, aa.getItem(itemPosition), Toast.LENGTH_SHORT).show();
					return false;
				}
			});
		}
		break;
		default: {
			getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		}
		break;
		}
		return super.onPrepareOptionsMenu(menu);
	}
	
	
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if(arg0 == 0 && arg1 == Activity.RESULT_OK) {
			DBHotSpot nhs = new DBHotSpot();
			nhs.setName(arg2.getStringExtra("spot_name"));
			nhs.setTimestamp(System.currentTimeMillis());
			DBHelper.getInstance(this).getHotSpotDao().insertOrReplace(nhs);
			QueryBuilder<DBHotSpot> qhs = DBHelper.getInstance(this).getHotSpotDao()
					.queryBuilder().orderDesc(DBHotSpotDao.Properties.Timestamp).limit(7);
			hsList = qhs.list();
			supportInvalidateOptionsMenu();
		}
	}

	private void updateData(DBHotSpot hs) {
		if(hs.getName().equals("更多")) {
			Intent i = new Intent(this, PickSpotActivity.class);
			startActivityForResult(i, 0);
			return;
		}
		hs.setTimestamp(System.currentTimeMillis());
		DBHelper.getInstance(this).getHotSpotDao().update(hs);
		QueryBuilder<DBHotSpot> qhs = DBHelper.getInstance(this).getHotSpotDao()
				.queryBuilder().orderDesc(DBHotSpotDao.Properties.Timestamp).limit(7);
		hsList = qhs.list();
		DBHotSpot hsm = new DBHotSpot();
		hsm.setName("更多");
		hsList.add(hsm);
		
		switch(pflag) {
		case 1: {
			((HotEventFragment)mContent).reloadData(hs.getName());
			break;
		}
		case 2: {
			((RealtimeFragment)mContent).reloadData(hs.getName());
			break;
		}
		case 3: {
			((CategoryFragment)mContent).reloadData(hs.getName());
			break;
		}
		}
	}
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.act_framework_content, fragment)
		.commit();
		getSlidingMenu().showContent();
	}
	
	
	public void switchContent(Class<? extends Fragment> fClass, String tag, Bundle args) {
		FragmentManager fm = getSupportFragmentManager();
		mContent = fm.findFragmentByTag(tag);  
        boolean isFragmentExist = true;  
        if (mContent == null) {  
            try {  
                isFragmentExist = false;  
                mContent = fClass.newInstance();  
                mContent.setArguments(new Bundle());  
            } catch (java.lang.InstantiationException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            }  
        }  
        if(mContent.isAdded()){ 
        	getSlidingMenu().showContent();
            return;  
        }  
        if( args != null && !args.isEmpty() ) {  
        	mContent.getArguments().putAll(args);  
        }  
        FragmentTransaction ft = fm.beginTransaction();  
        /*ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,  
                android.R.anim.fade_in, android.R.anim.fade_out);*/  
        if( isFragmentExist ) {  
            ft.replace(R.id.act_framework_content, mContent);  
        } else {  
            ft.replace(R.id.act_framework_content, mContent, tag);  
        }  
          
        ft.addToBackStack(tag);  
        ft.commitAllowingStateLoss();  
        getSlidingMenu().showContent();
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		if((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
			setBehindContentView(R.layout.fragment_menu);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fgt_menu_content, new FrameMenuFragment()).commit();
		}
	}

}
