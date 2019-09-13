package org.mv.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    //클라이언트 스레드 클래스 생성
    class ClientThread extends Thread{
        public void run(){
            String host = "localHost";
            //포트를 서버와 동일하게 설정해야한다.
            int port = 5001;
            //서버에 접속
            try{
                //소켓 객체 생성
                Socket socket = new Socket(host,port);
                //서버에 보냄
                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                outStream.writeObject("안녕!");
                outStream.flush();

                Log.d("ClientThread","서버로 보냄.");

                //서버에서 받음
                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                final Object input = inStream.readObject(); //상수처럼 접근하기 위해서 final 선언
                //직접 접근 불가능 하기때문에 핸들러가 필요하다.
                //textView.setText("받은 데이터 : ",+input);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("받은 데이터 : " + input);
                    }
                });
                Log.d("ClientThread","받은 데이터 : " + input);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
