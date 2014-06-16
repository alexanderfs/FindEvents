package com.alexan.findevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alexan.findevents.dao.DBPerson;
import com.alexan.findevents.dao.DBPersonDao;
import com.alexan.findevents.dao.DBUser;
import com.alexan.findevents.dao.DBUserDao;
import com.alexan.findevents.util.DBHelper;

import de.greenrobot.dao.query.QueryBuilder;

public class RegisterActivity extends Activity {
	private EditText vUsername;
	private EditText vEmail;
	private EditText vPassword;
	private EditText vConfirmPwd;
	private Button vRegister;

	private int registerType = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		registerType = getIntent().getIntExtra("register_type", 0);
		initView();
	}

	private void initView() {
		vUsername = (EditText) findViewById(R.id.act_register_username);
		vPassword = (EditText) findViewById(R.id.act_register_password);
		vEmail = (EditText) findViewById(R.id.act_register_email);
		vConfirmPwd = (EditText) findViewById(R.id.act_register_confirm_password);
		vRegister = (Button) findViewById(R.id.act_register);
		vRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (checkValid() == 0) {
					storeUserData();
					if (registerType == 0) {
						Intent mainInt = new Intent(RegisterActivity.this,
								StartupActivity.class);
						mainInt.putExtra("type", 0);
						mainInt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mainInt);
					} else {
						Intent mainInt = new Intent(RegisterActivity.this,
								FrameworkActivity.class);
						// mainInt.putExtra("type", 0);
						mainInt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mainInt);
					}
				} else {
					Toast.makeText(RegisterActivity.this, "输入错误请重新输入",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	private int checkValid() {
		if (vUsername.getText().length() == 0
				|| vPassword.getText().length() == 0
				|| vEmail.getText().length() == 0
				|| vConfirmPwd.getText().length() == 0
				|| !vPassword.getText().toString()
						.equals(vConfirmPwd.getText().toString())) {
			return 1;
		} else {
			return 0;
		}
	}
	
	private void storeUserData() {
		DBUser u = new DBUser();
		u.setNickname(vUsername.getText().toString());
		u.setEmailAddr(vEmail.getText().toString());
		
		DBUserDao ud = DBHelper.getInstance(this).getUserDao();
		ud.deleteAll();
		ud.insert(u);
		
		PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
			.putString("curr_user", u.getNickname()).commit();
	}
}
