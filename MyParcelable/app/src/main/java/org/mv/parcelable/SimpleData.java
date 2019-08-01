package org.mv.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    int number;
    String message;

    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }

    //parcel에 있는 데이터를 가져온다.
    public SimpleData(Parcel src){
        number = src.readInt();
        message = src.readString();
    }

    //creator 필드가 필요함
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        //simpleData 객체 생성
        public SimpleData createFromParcel(Parcel src){
            return new SimpleData(src);
        }

        //객체 배열 생성
        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //simpleData를 parcel로 바꿔준다.
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(message);
    }
}
