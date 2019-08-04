package org.mv.smsreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsReceiver";
    //날짜 시간을 원하는 형식으로 변형
    private static SimpleDateFormat format =
            new SimpleDateFormat("yyyy-MM-dd HH;mm");

    //sms 문자를 받을때 onReceive 함수가 자동으로 호출
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive() 호출됨.");

        //해쉬 태그로 되어있는 bundle을 가져온다.
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        //messages에 데이터가 있다면
        if(messages.length > 0){
            //발신자 번호
            String sender = messages[0].getOriginatingAddress();
            Log.d(TAG,"sender : "+ sender);
            //발신 내용
            String contents = messages[0].getMessageBody().toString();
            Log.d(TAG,"contents : " + contents);
            //수신 시간
            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(TAG,"received date : " + receivedDate);

            sendToActivity(context, sender, contents, receivedDate);

        }
    }

    //발신자, 내용, 수신 시간의 내용을 intent를 이용하여 activity로 전달하는 메소드
    private void sendToActivity
    (Context context, String sender, String contents,Date receivedDate){
        Intent intent = new Intent(context,SmsActivity.class);
        //브로드캐스트 수신자는 화면이 없기 때문에 flag가 필요하다.
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                |Intent.FLAG_ACTIVITY_SINGLE_TOP
                |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender",sender);
        intent.putExtra("contents",contents);
        intent.putExtra("receivedDate",format.format(receivedDate));
        context.startActivity(intent);
    }

    //bundle 에 있는 내용을 가져오는 메소드
    private SmsMessage[] parseSmsMessage(Bundle bundle){
        //sms 데이터를 처리 할때 pdus를 이용해서 데이터를 가져온다.
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        //messages 안에 있는 데이터의 갯수 만큼 가져온다.
        for(int i=0;i<objs.length;i++){
            //마쉬멜로우 버전보다 빌드 버전이 같거나 크다면
            //
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                //format의 정보를 두번째에 전달.
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i],format);
            }
            else{
                //objs의 i번째 pdu를 가져온다.
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }

        //bundle 데이터를 smsMessage의 데이터로 변환해서 return;
        return messages;
    }
}
