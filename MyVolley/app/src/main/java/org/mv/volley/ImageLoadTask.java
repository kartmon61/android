package org.mv.volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void,Void, Bitmap> {

    private String urlStr;
    private ImageView imageView;

    //url과 비트맵을 매칭 해주는 작
    private static HashMap<String,Bitmap> bitmapHash = new HashMap<String,Bitmap>();

    //생성자
    public ImageLoadTask(String urlStr, ImageView imageView){
        this.urlStr = urlStr;
        this.imageView = imageView;
    }

    //1번째 실행
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    //2번째 실행
    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try{
            //이미 비트맵 주소가 들어있다면
            if(bitmapHash.containsKey(urlStr)){
                Bitmap oldBitmap = bitmapHash.remove(urlStr);
                if(oldBitmap != null){
                    //비트맵을 메모리에서 제거해준다.
                    oldBitmap.recycle();
                    oldBitmap=null;
                }
            }

            URL url = new URL(urlStr);
            //주소에 접근해서 이미지라면 비트맵으로 바꿔서 저장
           bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

           //새롭게 해쉬에 저장
           bitmapHash.put(urlStr,bitmap);

           //외부 라이브러리를 사용한다면 파일로 저장했다가 같은 url로 요청하면 로컬파일로 요청하여 이용한다.

        }catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }
    //3번쨰 실행
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    //4번째 실행
    //비트맵 처리
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        //이미지뷰에 설정
        imageView.setImageBitmap(bitmap);
        //혹시나 설정이 안될경우를 위해서 다시 설정
        imageView.invalidate();
    }

}
