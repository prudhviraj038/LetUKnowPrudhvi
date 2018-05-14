package in.co.letuknow;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Chinni on 30-07-2016.
 */
public class Categories implements Serializable {
    String id,title,title_ar,title_fr,image;
    ArrayList<Chanel> chanels;
    ArrayList<Chanel> chanels_all;

    Categories(JSONObject jsonObject){
        chanels=new ArrayList<>();
        try {
            id=jsonObject.getString("id");
            title=jsonObject.getString("title");
            title_ar=jsonObject.getString("title_ar");
            title_fr=jsonObject.getString("title_fr");
            image=jsonObject.getString("image");
            for(int i=0;i<jsonObject.getJSONArray("chanels").length();i++){

                JSONObject jsonObject2 =jsonObject.getJSONArray("chanels").getJSONObject(i);
                Chanel chanel=new Chanel(jsonObject2,false);
                chanel.parent_id = id;
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
