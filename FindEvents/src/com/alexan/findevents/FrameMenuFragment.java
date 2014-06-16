package com.alexan.findevents;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.alexan.findevents.R;

public class FrameMenuFragment extends ListFragment {

	private String[] funcList = new String[] { "请登陆", "热门活动", "实时发布", "分类浏览",
			"伙伴圈", "系统设置" };
	private String currUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		currUser = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext())
				.getString("curr_user", "none");
		return inflater.inflate(R.layout.menu_list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		FrameMenuAdapter fma = new FrameMenuAdapter();
		setListAdapter(fma);
		this.setSelection(1);
		
	}

	public class FrameMenuAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return funcList.length;
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
			View currView = LayoutInflater.from(getActivity()).inflate(
					R.layout.menu_item, null);
			if(position == 1) {
				currView.setBackgroundColor(Color.rgb(0, 168, 255));
				getListView().setTag(currView);
			}
			
			TextView tv = (TextView) currView
					.findViewById(R.id.menu_item_title);
			tv.setText(funcList[position]);
			if(position == 0 && !currUser.equals("none")) {
				tv.setText(currUser);
			}
			if(position == 4 && currUser.equals("none")) {
				currView.setClickable(false);
			}
			return currView;
		}

	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		if ((lv.getTag() != null)) {
			((View) (lv.getTag())).setBackgroundDrawable(null);
		}
		lv.setTag(v);
		v.setBackgroundColor(Color.rgb(0, 168, 255));
		
		switch (position) {
		case 0:
			switchFragment(PersonalFragment.class, "personal");
			break;
		case 1:
			switchFragment(HotEventFragment.class, "hotevent");
			break;
		case 2:
			switchFragment(RealtimeFragment.class, "realtimeevent");
			break;
		case 3:
			switchFragment(CategoryFragment.class, "categoryevent");
			break;
		case 4:
			switchFragment(FriendCircleFragment.class, "friendcircle");
			break;
		default:
			switchFragment(SettingsFragment.class, "settings");
			break;
		}

	}

	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		FrameworkActivity fca = (FrameworkActivity) getActivity();
		fca.switchContent(fragment);
	}
	
	private void switchFragment(Class<? extends Fragment> fClass, String tag) {
		((FrameworkActivity)getActivity()).switchContent(fClass, tag, null);
	}

}
