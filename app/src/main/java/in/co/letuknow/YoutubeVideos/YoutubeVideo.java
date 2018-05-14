package in.co.letuknow.YoutubeVideos;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by mac on 5/14/18.
 */

public class YoutubeVideo implements Serializable {

    String id,title,title_original,description,video_link,default_image,medium,high,standard,maxres,time;
    YoutubeChannel youtubeChannel;
    YoutubeVideo(JSONObject jsonObject){

        try {
            id = jsonObject.getString("id");
        title = jsonObject.getString("title");
        title_original = jsonObject.getString("title_original");
        description = jsonObject.getString("description");
        video_link = jsonObject.getString("video_link");
        default_image = jsonObject.getString("default_image");
        medium = jsonObject.getString("medium");
        high = jsonObject.getString("high");
        standard = jsonObject.getString("standard");
        maxres = jsonObject.getString("maxres");
        time = jsonObject.getString("time");

            youtubeChannel = new YoutubeChannel(jsonObject.getJSONObject("chanel"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    }
