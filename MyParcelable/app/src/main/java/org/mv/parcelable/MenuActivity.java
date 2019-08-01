package org.mv.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //스택 최상위 제거

            }
        });

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    private void processIntent(Intent intent){
        if(intent != null){
            //serializable은 전달되는 데이터량이 크기 때문에 parcelable을 사용하는 것을 권장한다.
            ArrayList<String> names = (ArrayList<String>)intent.getSerializableExtra("names");

            if(names!=null){
                Toast.makeText(getApplicationContext(),
                        "전달받은 이름 리스트 갯수 : " + names.size(),
                        Toast.LENGTH_LONG).show();
            }

            //Parcelable을 통해서 데이터 가져오기

            SimpleData data = (SimpleData) intent.getParcelableExtra("data");
            if(data != null){
                Toast.makeText(getApplicationContext(),
                        "전달받은 SimpleData : " + data.message,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
