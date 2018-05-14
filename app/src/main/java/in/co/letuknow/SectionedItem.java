package in.co.letuknow;

import java.io.Serializable;

public class SectionedItem implements Serializable {

       public String type="0";
    public Categories categories=null;
    public Chanel chanel=null;
    public String header=null;
    public String image="";


       SectionedItem(Categories categories){

            this.categories = categories;
            type="1";
        }

        SectionedItem(Chanel chanel){

        this.chanel = chanel;
            type="2";
        }
        SectionedItem(String header){

        this.header = header;
            type="0";
        }

}