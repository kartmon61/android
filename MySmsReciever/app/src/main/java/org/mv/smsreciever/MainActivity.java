package org.mv.smsreciever;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한이 있는지 확인
        int permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        //권한 주어져 있다면
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "SMS 수신 권한 주어져 있음.",Toast.LENGTH_LONG).show();
        }
        //권한이 없다면
        else{
            Toast.makeText(this,"SMS 수신 권한 없음",Toast.LENGTH_LONG).show();

            //이 권한에 대한 설명이 필요한지 확인
            if(ActivityCompat.
                    shouldShowRequestPermissionRationale
                            (this,Manifest.permission.RECEIVE_SMS)){
                Toast.makeText(this,"SMS 권한 설명 필요함.",Toast.LENGTH_LONG).show();
            }
            //시스템에서 권한 부여하는 대화상자 요청
            else{
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.RECEIVE_SMS
                },101);
            }
        }
    }

    //권한이 부여되었는지 확인
    @Override
    public void onRequestPermissionsResult
            (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            //requestCode를 전달 받음
            case 1:
                if(grantResults.length > 0){
                    //사용자가 권한을 수락하면
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"SMS 수신 권한을 사용자가 승인함.",Toast.LENGTH_LONG).show();
                    }
                    //사용자가 권한을 거부하면
                    else if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this,"SMS 수신 권한을 사용자가 거부함.",Toast.LENGTH_LONG).show();
                    }
                }
                //권한 부여 X
                else{
                    Toast.makeText(this,"SMS 수신 권한을 부여받지 못함.",Toast.LENGTH_LONG).show();
                }
        }
    }
}
