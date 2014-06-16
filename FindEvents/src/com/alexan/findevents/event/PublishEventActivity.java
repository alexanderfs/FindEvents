package com.alexan.findevents.event;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.alexan.findevents.AppConstant;
import com.alexan.findevents.R;
import com.alexan.findevents.R.id;
import com.alexan.findevents.R.layout;
import com.alexan.findevents.util.DBHelper;
import com.alexan.findevents.util.DensityUtil;
import com.alexan.findevents.util.ImageUtil;
import com.alexan.findevents.dao.DBCategory;
import com.alexan.findevents.dao.DBCity;
import com.alexan.findevents.dao.DBDistrict;
import com.alexan.findevents.dao.DBEvent;
import com.alexan.findevents.dao.DBEventCategory;
import com.alexan.findevents.dao.DBEventImage;
import com.alexan.findevents.dao.DBImage;
import com.alexan.findevents.dao.DBProvince;

public class PublishEventActivity extends SherlockActivity {

	private ActionBar vACBar;
	private EditText vTitle;
	private EditText vDesc;
	private GridView vCategory;
	private Spinner vProvince;
	private Spinner vCity;
	private Spinner vDistrict;
	private TextView vAddrName;
	private TextView vAddrDetail;
	private Button vStartTime;
	private Button vStartDate;
	private Button vEndTime;
	private Button vEndDate;
	private GridView vPhoto;
	private Button vPublish1;
	private DBEvent currEvent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publishevent);
		initView();
		initData();
	}
	
	
	private void initData() {
		currEvent = new DBEvent();
		currEvent.setProvince("北京");
		currEvent.setCity("北京");
		currEvent.setDistrict("海淀");
		currEvent.setStarttime(Calendar.getInstance().getTime().getTime());
		currEvent.setEndtime(Calendar.getInstance().getTime().getTime());
	}
	
	private class CategoryRecorder implements CategorySelectListener {

		@Override
		public void setSelectedCategory(DBCategory category, boolean checked) {
			// TODO Auto-generated method stub
			if(checked) {
				categorySet.add(category);
			} else {
				categorySet.remove(category);
			}
		}
		
	}
	
	private HashSet<DBCategory> categorySet = new HashSet<DBCategory>();
	private Calendar c = Calendar.getInstance();
	private int year = c.get(Calendar.YEAR);
	private int month = c.get(Calendar.MONTH);
	private int dayofmonth = c.get(Calendar.DAY_OF_MONTH);
	private int hour = c.get(Calendar.HOUR_OF_DAY);
	private int minute = c.get(Calendar.MINUTE);
	
	private int year2 = c.get(Calendar.YEAR);
	private int month2 = c.get(Calendar.MONTH);
	private int dayofmonth2 = c.get(Calendar.DAY_OF_MONTH);
	private int hour2 = c.get(Calendar.HOUR_OF_DAY);
	private int minute2 = c.get(Calendar.MINUTE);
	
	private List<DBProvince> provinceList = new ArrayList<DBProvince>();
	private List<DBCity> cityList = new ArrayList<DBCity>();
	private List<DBDistrict> districtList = new ArrayList<DBDistrict>();
	
	private class ProvinceAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return provinceList.size();
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
			View v = getLayoutInflater().inflate(android.R.layout.simple_spinner_item, null);
			TextView tv = (TextView) v.findViewById(android.R.id.text1);
			tv.setText(provinceList.get(position).getName());
			return v;
		}
		
	}
	
	private class CityAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return cityList == null ? 0 : cityList.size();
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
			View v = getLayoutInflater().inflate(android.R.layout.simple_spinner_item, null);
			TextView tv = (TextView) v.findViewById(android.R.id.text1);
			tv.setText(cityList.get(position).getName());
			return v;
		}
		
	}
	
	private class DistrictAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return districtList == null ? 0 : districtList.size();
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
			View v = getLayoutInflater().inflate(android.R.layout.simple_spinner_item, null);
			TextView tv = (TextView) v.findViewById(android.R.id.text1);
			tv.setText(districtList.get(position).getName());
			return v;
		}
		
	}
	
	private Button vPickSpot;
	
	private ProvinceAdapter pad = new ProvinceAdapter();
	private CityAdapter cad = new CityAdapter();
	private DistrictAdapter dad = new DistrictAdapter();

	private void initView() {
		vACBar = getSupportActionBar();
		vACBar.setDisplayHomeAsUpEnabled(true);
		vACBar.setTitle("发布活动");
		
		vTitle = (EditText) findViewById(R.id.act_publishevent_etitle);
		vDesc = (EditText) findViewById(R.id.act_publishevent_edesc);
		
		vCategory = (GridView) findViewById(R.id.act_publishevent_category);
		CategoryCBAdapter ca = new CategoryCBAdapter(this, new CategoryRecorder());
		vCategory.setAdapter(ca);
		
		/*vProvince = (Spinner) findViewById(R.id.act_publishevent_province);
		provinceList = DBHelper.getInstance(this).getProvinceDao().loadAll();
		
		vProvince.setAdapter(pad);
		vProvince.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				cityList = provinceList.get(position).getProCities();
				cad.notifyDataSetChanged();
				districtList = cityList.get(0).getCityDis();
				dad.notifyDataSetChanged();
				currEvent.setProvince(provinceList.get(position).getName());
				currEvent.setCity(cityList.get(0).getName());
				currEvent.setDistrict(districtList.get(0).getName());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		///�?��把数据存到数据库
		vCity = (Spinner) findViewById(R.id.act_publishevent_city);
		if(provinceList == null || provinceList.size() == 0) {
			cityList = new ArrayList<DBCity>();
		} else {
			cityList = provinceList.get(0).getProCities();
		}
		vCity.setAdapter(cad);
		vCity.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				districtList = cityList.get(position).getCityDis();
				dad.notifyDataSetChanged();
				currEvent.setCity(cityList.get(position).getName());
				currEvent.setDistrict(districtList.get(0).getName());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vDistrict = (Spinner) findViewById(R.id.act_publishevent_district);
		if(cityList == null || cityList.size() == 0) {
			districtList = new ArrayList<DBDistrict>();
		} else {
			districtList = cityList.get(0).getCityDis();
		}
		vDistrict.setAdapter(dad);
		vDistrict.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				currEvent.setDistrict(districtList.get(position).getName());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});*/
		
		vAddrName = (TextView) findViewById(R.id.act_publishevent_addresstitle);
		vAddrDetail = (TextView) findViewById(R.id.act_publishevent_address);
		
		vPickSpot = (Button) findViewById(R.id.act_publishevent_pickaddrbtn);
		vPickSpot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(PublishEventActivity.this, PickAddrActivity.class);
				startActivityForResult(i, 2);
			}
		});
		
		vStartTime = (Button) findViewById(R.id.act_publishevent_starttime);
		vStartTime.setText("" + hour + " : " + minute);
		vStartTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new TimePickerDialog(PublishEventActivity.this, 
						new OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								// TODO Auto-generated method stub
								vStartTime.setText("" + hourOfDay + " : " + minute);
								hour = hourOfDay;
								minute = minute;
							}
						}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
			}
		});
		
		vStartDate = (Button) findViewById(R.id.act_publishevent_startdate);
		vStartDate.setText("" + year + "-" + (month + 1) + "-" + dayofmonth);
		vStartDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(PublishEventActivity.this, 
						new OnDateSetListener() {
							
							@Override
							public void onDateSet(DatePicker view, int year, int monthOfYear,
									int dayOfMonth) {
								// TODO Auto-generated method stub
								vStartDate.setText("" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
								PublishEventActivity.this.year = year;
								month = monthOfYear;
								dayofmonth = dayOfMonth;
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		vEndTime = (Button) findViewById(R.id.act_publishevent_endtime);
		vEndTime.setText("" + hour2 + " : " + minute2);
		vEndTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new TimePickerDialog(PublishEventActivity.this, 
						new OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								// TODO Auto-generated method stub
								vEndTime.setText("" + hourOfDay + " : " + minute);
								hour2 = hourOfDay;
								PublishEventActivity.this.minute2 = minute;
							}
						}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
			}
		});
		
		vEndDate = (Button) findViewById(R.id.act_publishevent_enddate);
		vEndDate.setText("" + year + "-" + (month2 + 1) + "-" + dayofmonth2);
		vEndDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(PublishEventActivity.this, 
						new OnDateSetListener() {
							
							@Override
							public void onDateSet(DatePicker view, int year, int monthOfYear,
									int dayOfMonth) {
								// TODO Auto-generated method stub
								vEndDate.setText("" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
								PublishEventActivity.this.year2 = year;
								month2 = monthOfYear;
								dayofmonth2 = dayOfMonth;
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		vPhoto = (GridView) findViewById(R.id.act_publishevent_photo);
		pa = new PhotoAdapter();
		vPhoto.setAdapter(pa);
		
		vPublish1 = (Button) findViewById(R.id.act_publishevent_pub1);
		vPublish1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//getStoreEvent();
				new AlertDialog.Builder(PublishEventActivity.this).setTitle("选择发送目标")
					.setItems(new String[]{"发送至公告栏", "发送至我的页面", "发送至伙伴群"}, 
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								switch(which) {
								case 0:
								case 1:
									getStoreEvent();
									break;
								case 2: {
									Intent i = new Intent(PublishEventActivity.this, PickGroupActivity.class);
									startActivityForResult(i, 3);
								}
									break;
								}
							}
						}).show();
			}
		});
	}
	
	private void getStoreEvent() {
		String strTmp = vTitle.getText().toString();
		if(strTmp == null || strTmp.equals("")) {
			Toast.makeText(this, "请填写活动标题", Toast.LENGTH_SHORT).show();
			return;
		}
		currEvent.setTitle(strTmp);
		strTmp = vDesc.getText().toString();
		if(strTmp == null || strTmp.equals("")) {
			Toast.makeText(this, "请填写活动描述", Toast.LENGTH_SHORT).show();
			return;
		}
		currEvent.setDescription(strTmp);
		/*StringBuilder sb = new StringBuilder();
		int i = 0;
		for(String str : categorySet) {
			if(i != 0) {
				sb.append(',');
			}
			sb.append(str);
			i++;
		}*/
		if(categorySet == null || categorySet.size() == 0) {
			Toast.makeText(this, "请选择活动分类", Toast.LENGTH_SHORT).show();
			return;
		}
		//currEvent.setCategorylist(sb.toString());
		
		strTmp = vAddrName.getText().toString();
		if(strTmp == null || strTmp.equals("请选择活动地点")) {
			Toast.makeText(this, "请选择活动地点", Toast.LENGTH_SHORT).show();
			return;
		}
		currEvent.setAddress(strTmp);
		currEvent.setAddressdetail(strTmp);
		
		currEvent.setStarttime(new GregorianCalendar(year, month, dayofmonth, hour, minute).getTime().getTime());
		currEvent.setEndtime(new GregorianCalendar(year2, month2, dayofmonth2, hour2, minute2).getTime().getTime());
		
		long eventID = DBHelper.getInstance(this).getEventDao().insert(currEvent);
		for(DBCategory ca: categorySet) {
			DBEventCategory ec = new DBEventCategory();
			ec.setEventID(eventID);
			ec.setCategoryID(ca.getId());
			ec.setTimestamp(Calendar.getInstance().getTimeInMillis());
			DBHelper.getInstance(this).getEventCategoryDao().insert(ec);
		}
		
		for(DBImage img: bdPhotos) {
			DBEventImage ei = new DBEventImage();
			ei.setEventID(eventID);
			ei.setImageID(img.getId());
			ei.setTimestamp(System.currentTimeMillis());
			DBHelper.getInstance(this).getEventImageDao().insert(ei);
		}
		
		Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
		this.finish();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case android.R.id.home: {
			new AlertDialog.Builder(this)
				.setTitle("退出")
				.setMessage("确定放弃本次编辑？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						PublishEventActivity.this.finish();
					}
				}).setNegativeButton("返回继续编辑", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				}).show();
		}
		}
		return super.onOptionsItemSelected(item);
	}

	private List<Bitmap> eventPhotos = new ArrayList<Bitmap>();
	private List<DBImage> bdPhotos = new ArrayList<DBImage>();
	private PhotoAdapter pa;
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		int reqWidth = DensityUtil.dip2px(this, 64f);
		if(resultCode == RESULT_OK && requestCode == 0) {
			Uri imageUri = data.getData();
			String imagePath = getUriPath(imageUri);
			DBImage imgTemp = new DBImage();
			imgTemp.setImageUrl(imagePath);
			imgTemp.setTimestamp(System.currentTimeMillis());
			long id = DBHelper.getInstance(this).getImageDao().insert(imgTemp);
			imgTemp.setId(id);
			bdPhotos.add(imgTemp);
			eventPhotos.add(ImageUtil.decodeSampledBitmapFromPath(imagePath, reqWidth, reqWidth));
			pa.notifyDataSetChanged();
		} else if(resultCode == RESULT_OK && requestCode == 1) {
			  //将保存在本地的图片取出并缩小后显示在界面上  
			//String imagePath = Environment.getExternalStorageDirectory()+"/worktemp.jpg";
			Bitmap pic = ImageUtil.decodeSampledBitmapFromPath(picPathTemp, reqWidth, reqWidth);
			if(pic != null) {
				eventPhotos.add(pic);
				pa.notifyDataSetChanged();
			} else {
				Toast.makeText(this, "获取照片失败", Toast.LENGTH_SHORT).show();
			}
		} else if(resultCode == RESULT_OK && requestCode == 2) {
			String addrDesc = data.getStringExtra("addr");
			if(addrDesc == null || addrDesc.length() == 0) {
				addrDesc = "DEFAULT ADDR";
			}
			vAddrName.setText(data.getStringExtra("addr"));
			vAddrDetail.setText(data.getStringExtra("addrdetail"));
		} else if(resultCode == RESULT_OK && requestCode == 3) {
			getStoreEvent();
		}
	}
	
	private String getUriPath(Uri uri) {
		 String[] projection = {MediaStore.Images.Media.DATA};    
         Cursor cursor = managedQuery(uri, projection, null, null, null);    
         int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);    
         cursor.moveToFirst();    
         return cursor.getString(column_index);    
	}
	
	private String picPathTemp;
	private DBImage picTemp;
	
	private class PhotoAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return eventPhotos.size() + 1;
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
			View currView = getLayoutInflater().inflate(R.layout.grid_photo_item, null);
			if(position == eventPhotos.size()) {
				ImageView currImg = (ImageView) currView.findViewById(R.id.grid_photo_item);
				currImg.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new AlertDialog.Builder(PublishEventActivity.this).setTitle("选择图片")
							.setItems(new String[]{"从相册选取", "拍照"}, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									switch(which) {
									case 0: {
										Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
										openAlbumIntent.setType("image/*");
										startActivityForResult(openAlbumIntent, 0); 
									}
										break;
									case 1: {
										Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
										StringBuilder sb = new StringBuilder();
										sb.append(AppConstant.localStorage + AppConstant.folderName + "/" + System.currentTimeMillis() + ".jpg");
										picPathTemp = sb.toString();
										picTemp = new DBImage();
										picTemp.setImageUrl(picPathTemp);
										picTemp.setTimestamp(System.currentTimeMillis());
										long id = DBHelper.getInstance(PublishEventActivity.this).getImageDao().insert(picTemp);
										picTemp.setId(id);
										bdPhotos.add(picTemp);
										Uri imageUri = Uri.fromFile(new File(picPathTemp));  
										//指定照片保存路径（SD卡），workupload.jpg为一个临时文件，每次拍照后这个图片都会被替换  
										cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
										startActivityForResult(cameraIntent, 1);
									}
									default:
										break;
									}
								}
						}).show();
					}
				});
			} else {
				ImageView currImg = (ImageView) currView.findViewById(R.id.grid_photo_item);
				currImg.setImageBitmap(eventPhotos.get(position));
			}
			return currView;
		}
		
	}
}
