package in.co.letuknow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import com.bumptech.glide.Glide;


/**
 * Created by anupamchugh on 09/02/16.
 */
public class MyChannelsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SectionedItem> dataSet;


    Context mContext;


    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {


        TextView tv;


        public TextTypeViewHolder(View itemView) {
            super(itemView);
            this.tv=(TextView) itemView.findViewById(R.id.cat_name);

        }

    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        ImageView image;
        LinearLayout click_view;

        public ChannelViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.cat_name);
            this.image = (ImageView) itemView.findViewById(R.id.cat_img);
            this.click_view = (LinearLayout) itemView.findViewById(R.id.click_view);

        }

    }


    public MyChannelsAdapter(ArrayList<SectionedItem> data, Context context) {
        this.dataSet = data;
        Log.e("size_adapter", String.valueOf(dataSet.size()));
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view;

                if(viewType==0) {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
                    return new TextTypeViewHolder(view);
                }else{
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_item, parent, false);
                    return new ChannelViewHolder(view);
                }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final SectionedItem object = dataSet.get(listPosition);

        if (object != null) {


            if(object.type.equals("0")){

                ((TextTypeViewHolder) holder).tv.setText(object.header);


            }else if(object.type.equals("1")){
                ((ChannelViewHolder) holder).txtType.setText(object.categories.get_title());
                 Picasso.with(mContext).load(object.categories.image).into(((ChannelViewHolder) holder).image);

                ((ChannelViewHolder) holder).click_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String ids= "";
                        for(int i=0;i<object.categories.chanels.size();i++){

                            if(i==0){
                                ids = object.categories.chanels.get(i).ch_id;
                            }else{

                                ids=ids+","+object.categories.chanels.get(i).ch_id;
                            }
                        }

                        Intent intent = new Intent(mContext,MultipleChannelactivity.class);
                        intent.putExtra("channels",ids);
                        mContext.startActivity(intent);


                    }
                });


            }else{
                ((ChannelViewHolder) holder).txtType.setText(object.chanel.get_ch_title(mContext));
                Picasso.with(mContext).load(object.chanel.ch_image).into(((ChannelViewHolder) holder).image);

                ((ChannelViewHolder) holder).click_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(mContext,SingleChannelactivity.class);
                        intent.putExtra("channel",object.chanel);
                        mContext.startActivity(intent);

                    }
                });


            }


            }
        }


    @Override
    public int getItemViewType(int position) {


        if(dataSet.get(position).type.equals("0"))
            return 0;
        else
            return 1;

    }

    @Override
    public int getItemCount() {
        Log.e("size", String.valueOf(dataSet.size()));
        return dataSet.size();
    }


}
