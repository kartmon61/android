package org.mv.button;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class BitmapButton extends AppCompatButton {

    public BitmapButton(Context context) {
        super(context);

        init(context);
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);
    }

    private void init(Context context){
        setBackgroundResource(R.drawable.title_bitmap_button_normal);
        setTextColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.title_bitmap_button_clicked);
                break;


                case MotionEvent.ACTION_UP:
                    setBackgroundResource(R.drawable.title_bitmap_button_normal);
                    break;

        }

        invalidate(); //버튼의 그래픽을 다시 올리게 된다.

        return true;
    }
}
