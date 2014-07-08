package com.alexan.findevents;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alexan.findevents.dao.DBComment;
import com.alexan.findevents.dao.DBCommentDao.Properties;
import com.alexan.findevents.dao.DBEvent;
import com.alexan.findevents.dao.DBEventDao;
import com.alexan.findevents.event.EventDetailActivity;
import com.alexan.findevents.friend.FCEntity;
import com.alexan.findevents.friend.FriendCircleAdapter;
import com.alexan.findevents.util.DBHelper;

import de.greenrobot.dao.query.QueryBuilder;

public class FriendCircleFragment extends Fragment {
	
	private ListView vMainList;
	private ImageView vImage;
	private TextView vName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_friendcircle, container, false);
		initView(v);
		((FrameworkActivity)getActivity()).setPFlag(4);
		((FrameworkActivity)getActivity()).supportInvalidateOptionsMenu();
		getActivity().setTitle("");
		return v;
	}
	
	private void initView(View v) {
		vMainList = (ListView) v.findViewById(R.id.act_friendcircle_mainlist);
		View listHead = getActivity().getLayoutInflater().inflate(R.layout.list_friendcircle_head, null);
		vImage = (ImageView) listHead.findViewById(R.id.list_fc_head_image);
		vName = (TextView) listHead.findViewById(R.id.list_fc_head_name);
		vMainList.addHeaderView(listHead);
		eventList = getEventList();
		FriendCircleAdapter fca = new FriendCircleAdapter(getActivity(), eventList);
		vMainList.setAdapter(fca);
		/*vMainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Bundle b = new Bundle();
				b.putLong("event_id", eventList.get(position).getEvent().getId());
				Intent i = new Intent(getActivity(), EventDetailActivity.class);
				i.putExtras(b);
				startActivity(i);
			}
		});*/
	}
	
	private List<FCEntity> eventList;
	
	private long getUserID() {
		return PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getLong("curr_user_id", 0);
	}
	
	private List<FCEntity> getEventList() {
		Long userID = getUserID();
		eventList = new ArrayList<FCEntity>();
		QueryBuilder<DBEvent> qbe = DBHelper.getInstance(getActivity()).getEventDao().queryBuilder()
				.where(DBEventDao.Properties.UserID.notEq(userID));
		List<DBEvent> t1 = qbe.list();
		for(DBEvent e: t1) {
			QueryBuilder<DBComment> qbc = DBHelper.getInstance(getActivity()).getCommentDao().queryBuilder()
					.where(Properties.EventID.eq(e.getId()));
			List<DBComment> t2 = qbc.list();
			for(DBComment c: t2) {
				FCEntity fe = new FCEntity(e, c);
				eventList.add(fe);
			}
		}
		return eventList;
	}
	
	private List<FCEntity> getEventList(int pos) {
		Long userID = getUserID();
		
		eventList = new ArrayList<FCEntity>();
		QueryBuilder<DBEvent> qbe = DBHelper.getInstance(getActivity()).getEventDao().queryBuilder()
				.where(DBEventDao.Properties.UserID.notEq(userID));
		List<DBEvent> t1 = qbe.list();
		for(DBEvent e: t1) {
			QueryBuilder<DBComment> qbc = DBHelper.getInstance(getActivity()).getCommentDao().queryBuilder()
					.where(Properties.EventID.eq(e.getId()), Properties.CommentType.eq(pos));
			List<DBComment> t2 = qbc.list();
			for(DBComment c: t2) {
				FCEntity fe = new FCEntity(e, c);
				eventList.add(fe);
			}
		}
		return eventList;
	}
	
	public void reloadData(int pos) {
		switch(pos) {
		case 0: {
			eventList = getEventList();
			FriendCircleAdapter fca = new FriendCircleAdapter(getActivity(), eventList);
			vMainList.setAdapter(fca);
			break;
		}
		case 1: {
			eventList = getEventList(1);
			FriendCircleAdapter fca = new FriendCircleAdapter(getActivity(), eventList);
			vMainList.setAdapter(fca);
			break;
		}
		case 2: {
			eventList = getEventList(3);
			FriendCircleAdapter fca = new FriendCircleAdapter(getActivity(), eventList);
			vMainList.setAdapter(fca);
			break;
		}
		case 3: {
			eventList = getEventList(4);
			FriendCircleAdapter fca = new FriendCircleAdapter(getActivity(), eventList);
			vMainList.setAdapter(fca);
			break;
		}
		}
	}
}
