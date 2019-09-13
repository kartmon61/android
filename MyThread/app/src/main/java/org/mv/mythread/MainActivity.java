package org.mv.mythread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    //int value = 0;

    ValueHandler handler = new ValueHandler();

    Handler handler2 = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //첫번째 방법 스레드 클래스를 정의하여 사용
                //BackgroundThread thread = new BackgroundThread();
                //thread.start();

                //runnable 을 implement해서 바로 사용
                new Thread(new Runnable() {
                    boolean running = false;
                    int value = 0;
                    //아래의 스래드 클래스와 동일한 내
                    @Override
                    public void run() {
                        running = true;
                        //run 동작 내용
                        while(running) {
                           value += 1;

                            //runnable 객체를 던질 수 있다.
                            //핸들러 내부에서 실행된 전달된 코드
                            //(메인스레드에서 실행되므로, ui에 접근이 가능하다.)
                            handler2.post(new Runnable() {
                                //ui 접근이 가능하다.
                                @Override
                                public void run() {
                                    textView.setText("현재 값"+value);
                                }
                            });
                            try{
                                Thread.sleep(1000);
                            }catch (Exception e){
                            }
                        }

                    }
                }).start();


            }
        });



        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                //textView.setText("현재 값:" + value);
            }
        });
    }


    //스래드 클래스 생성
    class BackgroundThread extends Thread{
        boolean running = false;
        int value = 0;
        public void run(){

            running = true;
            while(running){
                value+=1;
                //스레드 내에서는 사용 불가능 하다.
                //textView.setText("현재 값:" + value);

                //핸들러 쪽으로 send message 를 사용
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value",value);
                message.setData(bundle);
                handler.sendMessage(message);
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                }

            }
        }
    }


    //sendMessage를 전달하면 자동으로 호출된다.
    class ValueHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            //message를 받는다.
            Bundle bundle = msg.getData();
            //내부에 있던 value 값을 전달 받는다.
            int value = bundle.getInt("value");
            //textView에 접근 가능하다.
            textView.setText("현재값"+value);

        }
    }
}
