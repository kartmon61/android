package org.mv.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                ArrayList<String> names = new ArrayList<String>();
                names.add("이진호");
                names.add("홍진호");

                intent.putExtra("names",names);

                //일반적으로는 객체를 extra로 전달하지 못하지만
                // Parcelable을 통해서 객체를 생성하면 전달가능하다.
                SimpleData data = new SimpleData(100,"hello");
                intent.putExtra("data",data);
                startActivityForResult(intent, 101);
            }
        });
    }
}
