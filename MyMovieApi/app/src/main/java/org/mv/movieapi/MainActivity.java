package org.mv.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.mv.movieapi.data.MovieInfo;
import org.mv.movieapi.data.MovieList;
import org.mv.movieapi.data.ResponseInfo;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMovieList();
            }
        });

        //리퀘스트 큐 생성
       if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }


    }

    public void requestMovieList(){
        //영화 목록 요청
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";
        url += "?" + "type=1";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 받음 => " + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 발생 => " + error.getMessage());

                    }
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        println("영화목록 요청 보냄");


    }

    public void processResponse(String response){
        Gson gson = new Gson();

        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);
        if(info.code == 200){

            MovieList movieList = gson.fromJson(response,MovieList.class);
            println("영화 갯수 : " + movieList.result.size());

            for(int i=0;i<movieList.result.size();i++){
                MovieInfo movieInfo = movieList.result.get(i);
                println("영화 #" + i + " -> "+ movieInfo.id + ", " + movieInfo.title + ", " + movieInfo.grade);
            }

        }


    }


    public void println(String data){
        textView.append(data+"\n");
    }
}
