package com.ebookfrenzy.boundservices;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.renderscript.RenderScript;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RemoteEyeService extends Service
{
    final Messenger myMessenger = new Messenger(new MessageHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    class MessageHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg)
        {
            Bundle bundle =  msg.getData();
            String dataString =  bundle.getString("extra1");
        }
    }
}
