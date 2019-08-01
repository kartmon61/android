package org.mv.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                //putExtra - intent 안에 들어가는 데이터 추가(다른 엑티비티로 전달가능)
                intent.putExtra("name","mike");
                //응답에 대한 기본적인 상수를 만든 후 , 응답을 전달
                setResult(Activity.RESULT_OK,intent);
                finish(); //메뉴 화면을 없앤다. -> 메인화면이 보이게 된다. (스택)

            }
        });
    }
}
