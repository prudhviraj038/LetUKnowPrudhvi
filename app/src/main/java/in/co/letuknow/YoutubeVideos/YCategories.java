package in.co.letuknow.YoutubeVideos;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import in.co.letuknow.Chanel;

/**
 * Created by Chinni on 30-07-2016.
 */
public class YCategories implements Serializable {
    String id,title,title_ar,title_fr,image;
    ArrayList<YoutubeChannel> chanels;
    ArrayList<YoutubeChannel> chanels_all;

    YCategories(JSONObject jsonObject){
        chanels=new ArrayList<>();
        try {
            id=jsonObject.getString("id");
            title=jsonObject.getString("title");
            image=jsonObject.getString("image");
            for(int i=0;i<jsonObject.getJSONArray("chanels").length();i++){

                JSONObject jsonObject2 =jsonObject.getJSONArray("chanels").getJSONObject(i);
                YoutubeChannel chanel=new YoutubeChannel(jsonObject2);
                chanels.add(chanel);
            }
            chanels_all = chanels;

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public String get_title() {
            return title;
    }



}
