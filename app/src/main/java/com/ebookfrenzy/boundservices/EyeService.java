package com.ebookfrenzy.boundservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class EyeService extends Service
{
    private final IBinder myBinder = new ReturnedService();

    public EyeService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return myBinder;
    }

    public class ReturnedService extends Binder
    {
        EyeService getService()
        {
            return EyeService.this;
        }
    }
}
