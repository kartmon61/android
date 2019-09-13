package org.mv.myserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ServerThread thread = new ServerThread();
//                thread.start();

                //서비스 실행해준다.
                Intent intent = new Intent(getApplicationContext(),ChatService.class);
                startService(intent);
            }
        });
    }



//    //서버 스레드 선언
//    //서버를 스레드로 처리하면 시스템 리소스를 잡아먹기 때문에
//    //서버는 서비스로 구성해야한다.
//    class ServerThread extends Thread{
//        public void run(){
//            //서버를 실행하는 코드
//            int port = 5001; //5001번 포트로 요청을 받는다.
//
//            try{
//                ServerSocket server = new ServerSocket(port);
//                Log.d("ServerThread","서버가 실행됨.");
//
//                while(true){
//                    //대기 상태로 들어가게 될때 클라이언트의 요청 정보를
//                    // 소켓 객체로 받을 수 있다.
//                    Socket socket = server.accept();
//
//                    //들어오는 데이터를 처리한다.
//                    ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
//
//                    //클라이언트에서 전달받은 객체를 읽어올 수 있다.
//                    Object input = inStream.readObject();
//                    Log.d("ServerThread","input : " + input);
//
//                    //클라이언트로 데이터를 보낸다.
//                    ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
//                    outStream.writeObject(input + " from server.");
//                    //데이터에 남아있는 것을 처리해준다.
//                    outStream.flush();
//
//                    Log.d("ServerThread","output 보냄.");
//
//                    //클라이언트의 요청을 처리하면 소켓 객체를 끊어준다.
//                    socket.close();
//                }
//
//            } catch (Exception e){
//
//            }
//
//        }
//    }
}
