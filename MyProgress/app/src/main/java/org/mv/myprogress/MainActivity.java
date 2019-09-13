package org.mv.myprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //post를 사용 할 경우, 스레드를 여기서 지연 시킬 수 있다.
//                //5초 후에 실행이 된다.
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        ProgressThread thread = new ProgressThread();
//                        thread.start();
//                    }
//                },5000);

                //asyncTask 호출
                ProgressTask task = new ProgressTask();
                task.execute("시작");
            }
        });
    }


    //AsyncTask 클래스 생성
    class ProgressTask extends AsyncTask<String, Integer, Integer>{
        int value = 0;

        //자동으로 호출이 된다. 스레드 안에서 동작을 하게 된다.
        //string 타입으로 전달이 되면 strings 로 받게 된다.
        //처리를 하는 코드
        @Override
        protected Integer doInBackground(String... strings) {
            while (true) {
                if (value > 100) {
                    break;
                }
                value += 1;

                //onProgressUpdate 를 호출한다.
                publishProgress(value);

                try {
                    Thread.sleep(200);
                } catch (Exception e) {}
            }
            return value;
        }

        //두번째 integer를 받게 된다.
        //중간에 호출될떄 필요한 코드들
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //int타입으로 변경하여 progress바에 전달
            progressBar.setProgress(values[0].intValue());
        }

        //세번째 integer를 받게 된다.
        //doInBackground에서 return 되는 값을 받게 된다.
        //완료가 되는 곳
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

                Toast.makeText(getApplicationContext(),"완료됨",Toast.LENGTH_LONG).show();
        }
    }

    class ProgressThread extends Thread{
        int value = 0;

        public void run(){
            while(true){
                if(value>100){
                    break;
                }
                value+=1;

                try{
                    Thread.sleep(250);
                }catch (Exception e){
                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(value);
                    }
                });

            }
        }
    }
}
