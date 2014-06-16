package com.alexan.findevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexan.findevents.R;
import com.alexan.findevents.dao.DBUser;
import com.alexan.findevents.dao.DBUserDao;
import com.alexan.findevents.util.DBHelper;

public class LoginActivity extends Activity {

	private ImageView vImage;
	private EditText vUsername;
	private EditText vPassword;
	private TextView vForgotPwd;
	private Button vLogin;

	private int logType = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		logType = getIntent().getIntExtra("log_type", 0);
		initView();
	}

	private void initView() {
		vImage = (ImageView) findViewById(R.id.act_login_icon);
		vUsername = (EditText) findViewById(R.id.act_login_username);
		vPassword = (EditText) findViewById(R.id.act_login_password);
		vLogin = (Button) findViewById(R.id.act_login_login);
		vLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (checkValid()) {
					storeUserData();
					if (logType == 0) {
						Intent mainInt = new Intent(LoginActivity.this,
								StartupActivity.class);
						mainInt.putExtra("type", 0);
						mainInt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mainInt);
					} else {
						Intent mainInt = new Intent(LoginActivity.this,
								FrameworkActivity.class);
						// mainInt.putExtra("type", 0);
						mainInt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mainInt);
					}
				} else {
					Toast.makeText(LoginActivity.this, "输入不完全，请重新输入",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		vForgotPwd = (TextView) findViewById(R.id.act_login_forgetpwd);
		vForgotPwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	private boolean checkValid() {
		if (vUsername.getText().length() == 0
				|| vPassword.getText().length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	private void storeUserData() {
		DBUser u = new DBUser();
		u.setNickname(vUsername.getText().toString());

		DBUserDao ud = DBHelper.getInstance(this).getUserDao();
		ud.deleteAll();
		ud.insert(u);

		PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
				.edit().putString("curr_user", u.getNickname()).commit();
	}
}
