package org.mv.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //시스템이 알아볼 수 있는 포멧을 Intent가 가지고 있다.
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                //화면을 띄워주고 다시 돌아오기 위함 요청 코드 필요
                startActivityForResult(intent,101);
            }
        });
    }

    //응답을 받을 메소드 정의 override 사용 -> onActivityResult
    //data - > intent , requestCode -> 101 , resultCode -> ActivityResultOK가 전달

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            String name = data.getStringExtra("name");  //name에 대한 value값 가져온다
            Toast.makeText(getApplicationContext(),"메뉴화면으로부터 응답 : " +name, Toast.LENGTH_LONG).show();
        }
    }
}
