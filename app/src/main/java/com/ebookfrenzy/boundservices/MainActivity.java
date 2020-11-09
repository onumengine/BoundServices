package com.ebookfrenzy.boundservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    Messenger myEyeService = null;
    boolean eyeServiceIsBound;

    BoundService myService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection myConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            BoundService.MyLocalBinder binder = (BoundService.MyLocalBinder) iBinder;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            isBound = false;
        }
    };

    public void showTime(View view)
    {
        String currentTime = myService.getCurrentTime();
        TextView timeTextView = findViewById(R.id.time_textview);
        timeTextView.setText(currentTime);
    }

    //TODO------------------------------------------------------SEPARATOR FOR REMOTE SERVICE CODE---------------------------------------------------------

    private ServiceConnection myEyeServiceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            myEyeService = new Messenger(iBinder);
            eyeServiceIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            myEyeService = null;
            eyeServiceIsBound = false;
        }
    };

    private void sendMessage()
    {
        if (!eyeServiceIsBound) return;

        Bundle dataBundle = new Bundle();
        dataBundle.putString("extra1", "THE ACTUAL STRING");

        Message msg = Message.obtain();
        msg.setData(dataBundle);

        try
        {
            myEyeService.send(msg);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

}