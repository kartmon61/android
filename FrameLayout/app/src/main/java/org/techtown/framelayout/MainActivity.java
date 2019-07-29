package org.techtown.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }

    public void onButtonClicked(View v) {
        changeImage();

    }

    public void changeImage(){
        if(imageIndex == 0 ){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.GONE);
            imageIndex=1;
        }
        else if(imageIndex==1){
            imageView2.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            imageIndex=0;
        }
    }
}
