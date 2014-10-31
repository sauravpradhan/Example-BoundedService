package com.example.boundserivice;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class boundservice extends Service{
	
	IBinder mBinder = new LocalBinder();
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d("Saurav", "Unbinding Service ");
		return super.onUnbind(intent);
	}

	public class LocalBinder extends Binder
	{
		boundservice getService()
		{
			return boundservice.this;
		}
	}
	
	public void showtoast()
	{
		Toast.makeText(getApplicationContext(), "Toast from a BoundService", Toast.LENGTH_LONG).show();
	}

}
