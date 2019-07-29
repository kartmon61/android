package org.mv.list;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter;

    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        ListView listView = (ListView) findViewById(R.id.listView);


        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("소녀시대","010-1000-1000",R.drawable.icon01));
        adapter.addItem(new SingerItem("티아라","010-2000-2000",R.drawable.icon02));
        adapter.addItem(new SingerItem("여자친구","010-3000-3000",R.drawable.icon03));
        adapter.addItem(new SingerItem("걸스데이","010-4000-4000",R.drawable.icon04));
        adapter.addItem(new SingerItem("애프터스쿨","010-5000-5000",R.drawable.icon05));
        listView.setAdapter(adapter);

        //선택한 아이템을 클릭하면 나타내는 리스너
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingerItem item = (SingerItem) adapter.getItem(i);

                Toast.makeText(getApplicationContext(),"선택: "+item.getName(),Toast.LENGTH_LONG).show();

            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();
                String mobile = editText2.getText().toString();

                //어뎁터에 데이터를 전달
                adapter.addItem(new SingerItem(name,mobile,R.drawable.icon01));
                //리스트뷰를 갱신
                adapter.notifyDataSetChanged();
            }
        });
    }

    class SingerAdapter extends BaseAdapter{

        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item){
            items.add(item);

        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            //화면에 보이는 것을 재사용하여 데이터 처리를 한다.
            SingerItemView view = null;
            if(convertView == null){
                view = new SingerItemView(getApplicationContext());
            } else{
                view = (SingerItemView) convertView;
            }
            items.get(i);

            SingerItem item = items.get(i);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());

            return view;
        }
    }
}
