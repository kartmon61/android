package org.mv.mygrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("소녀시대","010-1000-1000",R.drawable.icon01));
        adapter.addItem(new SingerItem("티아라","010-2000-2000",R.drawable.icon02));
        adapter.addItem(new SingerItem("여자친구","010-3000-3000",R.drawable.icon03));
        adapter.addItem(new SingerItem("걸스데이","010-4000-4000",R.drawable.icon04));
        adapter.addItem(new SingerItem("애프터스쿨","010-5000-5000",R.drawable.icon05));
        gridView.setAdapter(adapter);

        //선택한 아이템을 클릭하면 나타내는 리스너
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingerItem item = (SingerItem) adapter.getItem(i);

                Toast.makeText(getApplicationContext(),"선택: "+item.getName(),Toast.LENGTH_LONG).show();

            }
        });

    }

    class SingerAdapter extends BaseAdapter {

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
