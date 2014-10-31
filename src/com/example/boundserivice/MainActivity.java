package com.example.boundserivice;

import com.example.boundserivice.boundservice.LocalBinder;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {


	//code to implement bound service
	boundservice serviceobj;
	Boolean mbound = false;

	private ServiceConnection myconnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mbound = false;
			Log.d("Saurav", "Service Disconnected");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			LocalBinder binder = (LocalBinder) service;
			serviceobj = binder.getService();
			mbound = true;

			serviceobj.showtoast();

			Log.d("Saurav", "Service Connected");

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		Intent intent = new Intent(this, boundservice.class);
		bindService(intent, myconnection, Context.BIND_AUTO_CREATE);



	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		if (mbound)
		{
			Log.d("Saurav", "Unbinding Client " + myconnection);
			unbindService(myconnection);
		}

		
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub

	
		super.onStop();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
