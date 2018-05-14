package in.co.letuknow.YoutubeVideos;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mac on 5/14/18.
 */

public class YoutubeChannel {

String id,title,image;

    YoutubeChannel(JSONObject jsonObject){

        try {
            id = jsonObject.getString("id");
            title = jsonObject.getString("title");
            image = jsonObject.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
