package in.co.letuknow;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Chinni on 30-07-2016.
 */


public class News implements Parcelable {

    String id, title, image, data,type, link, is_urgent, now, insta_img, gallery_link = "", facebook_str, twitter_str, whatsapp_str, mail_str, video,mp4,m3u8, times,
            time, time_ar, time_fr, times2;
    Chanel chanels;
    ArrayList<String> img;

    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;
    public static final int AUDIO_TYPE=2;
    public static final int FOOTER_TYPE=3;

    public int view_type;
    Context context;
    private int mData;


    News(JSONObject jsonObject, Context context) {

        this.context = context;
        img=new ArrayList<>();
        image = "";
        title = "";

        try {

            id = jsonObject.getString("id");
            image = decode_base64(jsonObject.getString("image"));
            data = decode_base64(jsonObject.getString("data"));
            type = jsonObject.getString("type");

            if(type.equals("double_add") || type.equals("double_video")) {
                title = jsonObject.getString("title");
                title = title.replace("\\", "");

                view_type = News.AUDIO_TYPE;

            }else{
                title = decode_base64(jsonObject.getString("title"));
                if(image.equals(""))
                    view_type = News.TEXT_TYPE;
                    else
                view_type = News.IMAGE_TYPE;

            }



          //  type = "add";

            link= "";
            link = decode_base64(jsonObject.getString("link"));
            insta_img = jsonObject.getString("insta_img");
            is_urgent = jsonObject.getString("is_urgent");
            now = jsonObject.getString("now");

            JSONObject jsonObject2 = jsonObject.getJSONObject("chanel");

            chanels = new Chanel(jsonObject2,true);

            facebook_str = jsonObject.getString("facebook_str");
            twitter_str = jsonObject.getString("twitter_str");
            whatsapp_str = jsonObject.getString("whatsapp_str");
            mail_str = decode_base64(jsonObject.getString("mail_str"));
            video = jsonObject.getString("video");
            mp4 = jsonObject.getString("mp4");
            m3u8 = jsonObject.getString("m3u8");
            times = jsonObject.getString("times");
            time = jsonObject.getString("time");
            time_ar = jsonObject.getString("time_ar");
            time_fr = jsonObject.getString("time_fr");
            times2 = jsonObject.getString("times2");

            if(jsonObject.getJSONArray("gallery").length()>0) {
                for (int i = 0; i < jsonObject.getJSONArray("gallery").length(); i++) {

                    img.add( decode_base64(jsonObject.getJSONArray("gallery").getString(i)));


                }
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    byte[] data64;
    String temp;

    private String decode_base64(String encodedstring){
        temp = encodedstring;

        try {
            data64 = Base64.decode(encodedstring, Base64.DEFAULT);
            temp = new String(data64, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(temp!=null)
        return temp;

        else return "";
    }

    public String get_time(Context context) {
            return time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


    }


    public static final Parcelable.Creator<News> CREATOR
            = new Parcelable.Creator<News>() {
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        public News[] newArray(int size) {
            return new News[size];
        }
    };

    private News(Parcel in) {
        mData = in.readInt();
    }



}