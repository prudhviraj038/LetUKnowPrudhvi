package in.co.letuknow;

import android.content.Context;
import android.util.Base64;

import org.json.JSONObject;

import java.io.Serializable;

public class Chanel implements Serializable {

        String ch_id,parent_id,ch_title,ch_title_ar,ch_title_fr,ch_image,cover_image,count;
        boolean like = false;



        Chanel(JSONObject jsonObject1,Boolean encode){
            try {
                ch_id=jsonObject1.getString("id");
                like = false;
                if(encode)
                    ch_title=decode_base64(jsonObject1.getString("title"));
                else
                    ch_title=jsonObject1.getString("title");

                ch_image=jsonObject1.getString("image");
                cover_image=jsonObject1.getString("cover_image");
                count = jsonObject1.getString("count");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    public String get_ch_title(Context context){

        return ch_title;
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


}