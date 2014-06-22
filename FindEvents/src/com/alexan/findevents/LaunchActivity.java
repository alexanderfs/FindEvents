package com.alexan.findevents;

import java.io.File;
import java.lang.ref.WeakReference;

import android.R.string;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.alexan.findevents.dao.DBCategoryDao;
import com.alexan.findevents.dao.DBHotSpotDao;
import com.alexan.findevents.dao.DBLocationDao;
import com.alexan.findevents.util.DBCopyOpenHelper;
import com.alexan.findevents.util.DBHelper;
import com.alexan.findevents.util.ImageUtil;

public class LaunchActivity extends Activity {

	
	private MainHandler mh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setBackgroundDrawableResource(R.drawable.ic_welcome);
		isFirst = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
				.getBoolean("is_first", true);
		mh = new MainHandler(this);
		DBHelper.getInstance(this);
		if(isFirst) {
			initStorageDir();
			initData();
		}
		mh.sendEmptyMessageDelayed(0, 1000);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        this.setResult(RESULT_CANCELED);
	        this.finish();
	        return true;   
	    }
		return super.onKeyUp(keyCode, event);
	}
	
	private void initData() {
		DBCopyOpenHelper oh = new DBCopyOpenHelper(this, AppConstant.DATABASE_NAME, null);
		SQLiteDatabase sdb = oh.getWritableDatabase();
		sdb.beginTransaction();
		try {
			String[] categoryList = getResources().getStringArray(R.array.category);
			for(String ca: categoryList) {
				ContentValues cv = new ContentValues();
				cv.put("name", ca);
				cv.put("timestamp", System.currentTimeMillis());
				sdb.insert(DBCategoryDao.TABLENAME, null, cv);
			}
			
			String[] locationList = getResources().getStringArray(R.array.location_name);
			String[] loDescList = getResources().getStringArray(R.array.location_desc);
			String[] loCityList = getResources().getStringArray(R.array.location_city);
			String[] loDistrictList = getResources().getStringArray(R.array.location_district);
			for(int i = 0; i < locationList.length; i++) {
				ContentValues cv = new ContentValues();
				cv.put(DBLocationDao.Properties.AddrName.columnName, locationList[i]);
				cv.put(DBLocationDao.Properties.AddrDetail.columnName, loDescList[i]);
				cv.put(DBLocationDao.Properties.AddrCity.columnName, loCityList[i]);
				cv.put(DBLocationDao.Properties.AddrDistrict.columnName, loDistrictList[i]);
				cv.put(DBLocationDao.Properties.Timestamp.columnName, System.currentTimeMillis());
				sdb.insert(DBLocationDao.TABLENAME, null, cv);
			}
			String[] hotList = getResources().getStringArray(R.array.hotspot);
			int i = 0;
			for(String hs: hotList) {
				ContentValues cv = new ContentValues();
				cv.put(DBHotSpotDao.Properties.Name.columnName, hs);
				cv.put(DBHotSpotDao.Properties.Timestamp.columnName, System.currentTimeMillis() + i++);
				sdb.insert(DBHotSpotDao.TABLENAME, null, cv);
			}
			sdb.setTransactionSuccessful();
		} catch(Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "数据库初始化异常", Toast.LENGTH_SHORT).show();
		} finally {
			sdb.endTransaction();
		}
		sdb.close();
	}
	
	private void initStorageDir() {
		boolean mkdirStatus = true;
		if(ImageUtil.getSDCardStatus()) {
			File appDir = new File(Environment.getExternalStorageDirectory() + 
					AppConstant.folderName);
			if(appDir.exists() == false) {
				if(appDir.mkdir()) {
					AppConstant.localStorage = Environment.getExternalStorageDirectory().getPath();
				} else {
					appDir = new File(Environment.getDataDirectory() + 
							AppConstant.folderName);
					if(appDir.exists() == false) {
						if(appDir.mkdir()) {
							AppConstant.localStorage = Environment.getDataDirectory().getPath();
						} else {
							mkdirStatus = false;
						}
					} else {
						AppConstant.localStorage = Environment.getDataDirectory().getPath();
					}
				}
			} else {
				AppConstant.localStorage = Environment.getExternalStorageDirectory().getPath();
			}
		} else {
			File appDir = new File(Environment.getDataDirectory() + 
					AppConstant.folderName);
			if(appDir.exists() == false) {
				if(appDir.mkdir()) {
					AppConstant.localStorage = Environment.getDataDirectory().getPath();
				} else {
					mkdirStatus = false;
				}
			} else {
				AppConstant.localStorage = Environment.getDataDirectory().getPath();
			}
		}
		if(mkdirStatus == false) {
			Toast.makeText(this, "本地文件夹初始化失败", Toast.LENGTH_SHORT).show();
		}
	}
	
	private static class MainHandler extends Handler {
		private WeakReference<Activity> mCtx;
		
		public MainHandler(Activity ctx) {
			mCtx = new WeakReference<Activity>(ctx);
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Activity act = mCtx.get();
			if(act == null) {
				return;
			}
			switch(msg.what) {
			case 0: {
				((LaunchActivity)act).startApp();
				break;
			}
			}
		}
	}
	
	private boolean isFirst;
	
	private void startApp() {
		
		if(isFirst) {
			Intent i = new Intent();
			i.setClass(LaunchActivity.this, WelcomeActivity.class);
			startActivity(i);
		} else {
			Intent i = new Intent(LaunchActivity.this, FrameworkActivity.class);
			startActivity(i);
			this.setResult(RESULT_CANCELED);
			this.finish();
		}
	}

}
