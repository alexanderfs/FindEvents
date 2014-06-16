package com.alexan.findevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexan.findevents.R;
import com.alexan.findevents.event.EventDetailActivity;
import com.alexan.findevents.friend.FriendCircleAdapter;

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
		FriendCircleAdapter fca = new FriendCircleAdapter(getActivity());
		vMainList.setAdapter(fca);
		vMainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), EventDetailActivity.class);
				startActivity(i);
			}
		});
	}
	
}
