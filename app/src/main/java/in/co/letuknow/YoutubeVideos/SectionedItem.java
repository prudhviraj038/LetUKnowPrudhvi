package in.co.letuknow.YoutubeVideos;

import java.io.Serializable;

import in.co.letuknow.Categories;
import in.co.letuknow.Chanel;

public class SectionedItem implements Serializable {

    public String type="0";
    public YCategories categories=null;
    public YoutubeChannel chanel=null;
    public String header=null;
    public String image="";


       SectionedItem(YCategories categories){

            this.categories = categories;
            type="1";
        }

        SectionedItem(YoutubeChannel chanel){

        this.chanel = chanel;
            type="2";
        }
        SectionedItem(String header){

        this.header = header;
            type="0";
        }

}