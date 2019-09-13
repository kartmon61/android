package org.mv.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //request 메소드 호출
                sendRequest();

            }
        });

        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImageRequest();
            }
        });

        //메인이 만들어질때 동시에 requestQueue객체 초기화
        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

    }

    public void sendImageRequest(){
        String url = "https://movie-phinf.pstatic.net/20190524_104/1558663170174Q2mmw_JPEG/movie_image.jpg";

        ImageLoadTask task = new ImageLoadTask(url,imageView);
        //이미지를 가져와서 비트맵으로 만들어 뿌려준다.
        task.execute();

    }


    //request객체에 요청을 보내는 메소드
    public void sendRequest(){
        //String url = "http://www.google.com";
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101";
        StringRequest request = new StringRequest(
                //get방식으로 요청한다.
                Request.Method.GET,
                //전달 할 url
                url,
                //정상 응답일 경우
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);

                        //응답받은 문자열 처리
                        processResponse(response);
                    }
                },
                //잘못된 응답일 경우
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ){
            //request 안에서 재정의를 할 수 있다.
            //요청 파라미터를 수정하기 위한 메소드
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                return params;
            }
        };

        //매번 받은 결과를 그대로 보여준다.
        request.setShouldCache(false);

        //requestQueue에 request를 추가한다.
        AppHelper.requestQueue.add(request);
        println("요청 보냄.");

    }

    public void processResponse(String response){
        Gson gson = new Gson();
        //무비리스트 객체 생성
        MovieList movieList = gson.fromJson(response,MovieList.class);

        if(movieList != null){
            int countMovie = movieList.boxOfficeResult.dailyBoxOfficeList.size();
            println("박스오피스 타입 : "+movieList.boxOfficeResult.boxofficeType);
            println("응답받은 영화 갯수 : " + countMovie);
        }

    }

    //텍스트 뷰를 추가하는 메소드
    public void println(String data){
        textView.append(data +"\n");
    }
}
