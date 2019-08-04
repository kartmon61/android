package org.mv.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    //서비스의 특성상 한번만들어지면 계속 존재함
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate() 호출됨.");
    }

    //명령어 처리 할때는 onStartCommand에서 처리
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand() 호출됨.");

        if(intent == null){
            return Service.START_STICKY;
        }
        else{
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }


    //화면이 없을 때 처리
    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG,"전달받은 데이터 : " + command+ ", " + name);
        try{
            Thread.sleep(5000);
        } catch (Exception e){}

        Intent showIntent = new Intent(getApplicationContext(),MainActivity.class);

        //화면이 없는 곳에서 화면을 띄우기 위해서는 문제가 생긴다.
        //Task라는 정보가 없기 때문에 flag 옵션을 줘야한다.
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                Intent.FLAG_ACTIVITY_SINGLE_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command","show");
        showIntent.putExtra("name",name+" from service.");
        startActivity(showIntent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() 호출됨.");
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
