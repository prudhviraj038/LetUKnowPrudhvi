package in.co.letuknow;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {


    Context context;
    ArrayList<Categories> categoriess;
    private static LayoutInflater inflater=null;

    public CategoryAdapter(Activity mainActivity, ArrayList<Categories> categories) {
        // TODO Auto-generated constructor stub
        context=mainActivity;
        this.categoriess=categories;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return categoriess.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.popup_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.cat_name);
        holder.tv.setText(categoriess.get(position).title);
        holder.img=(ImageView) rowView.findViewById(R.id.cat_img);
        Picasso.with(context).load(categoriess.get(position).image).into(holder.img);
        return rowView;
    }

}