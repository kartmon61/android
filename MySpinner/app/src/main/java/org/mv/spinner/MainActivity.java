package org.mv.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    String [] items = {"소녀시대","걸스데이","티아라","여자친구"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,items
        );
        //스피너의 형식을 정의
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //스피너에 어뎁터를 설정
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템을 선택할때
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText(items[i]);
            }
            //아무것도 선택하지 않을때
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("선택 :");
            }
        });
    }
}
