package com.alexan.findevents;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexan.findevents.dao.DBEvent;

public class CategoryFragment extends Fragment {

	private Spinner vSort;
	private TextView vCategory1;
	private ListView vList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_category, container, false);
		initView(v);
		((FrameworkActivity)getActivity()).setPFlag(3);
		((FrameworkActivity)getActivity()).supportInvalidateOptionsMenu();
		getActivity().setTitle("");
		return v;
	}

	private String[] hotSpotsList;

	void initView(View v) {

		vCategory1 = (TextView) v.findViewById(R.id.act_ca_ca1);
		vCategory1.setTextColor(Color.BLUE);

		vSort = (Spinner) v.findViewById(R.id.act_ca_sort);
		ArrayAdapter<CharSequence> aa2 = ArrayAdapter.createFromResource(
				getActivity(), R.array.sort_option, R.layout.list_simple_item);
		vSort.setAdapter(aa2);

		vList = (ListView) v.findViewById(R.id.act_ca_list);

		HotEventListAdapter hea = new HotEventListAdapter(getActivity(),
				new ArrayList<DBEvent>());
		vList.setAdapter(hea);
	}
}
