package in.co.letuknow.YoutubeVideos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.letuknow.DatabaseHandler;
import in.co.letuknow.R;

//import com.bumptech.glide.Glide;


/**
 * Created by anupamchugh on 09/02/16.
 */
public class YoutubeChannelsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SectionedItem> dataSet;


    Context mContext;
    DatabaseHandler db;

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
        TextView follow_btn;
        RelativeLayout follow_btn_click;
        ImageView follow_btn_image;


        public ChannelViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.cat_name);
            this.image = (ImageView) itemView.findViewById(R.id.cat_img);

            this.follow_btn_click = (RelativeLayout) itemView.findViewById(R.id.follow_btn_click);
            this.follow_btn_image = (ImageView) itemView.findViewById(R.id.follow_btn_image);
            this.follow_btn=(TextView) itemView.findViewById(R.id.follow_btn);

        }

    }


    public YoutubeChannelsAdapter(ArrayList<SectionedItem> data, Context context) {
        this.dataSet = data;
        Log.e("size_adapter", String.valueOf(dataSet.size()));
        this.mContext = context;
        db = new DatabaseHandler(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view;

                if(viewType==0) {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
                    return new TextTypeViewHolder(view);
                }else{
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_item_select, parent, false);
                    return new ChannelViewHolder(view);
                }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final SectionedItem object = dataSet.get(listPosition);

        if (object != null) {


            if(object.type.equals("0")){

                ((TextTypeViewHolder) holder).tv.setText(object.header);



            }else{
                ((ChannelViewHolder) holder).txtType.setText(object.chanel.title);
                 Picasso.with(mContext).load(object.chanel.image).into(((ChannelViewHolder) holder).image);

                ((ChannelViewHolder) holder).follow_btn.setText(Html.fromHtml(db.is_followingYoutube(dataSet.get(listPosition).chanel.id) ? "Unfollow" : "Follow"));



                if(db.is_followingYoutube(dataSet.get(listPosition).chanel.id)) {
                    ((ChannelViewHolder) holder).follow_btn.setVisibility(View.INVISIBLE);
                    ((ChannelViewHolder) holder).follow_btn_image.setVisibility(View.VISIBLE);
                }
                else {
                    ((ChannelViewHolder) holder).follow_btn.setVisibility(View.VISIBLE);
                    ((ChannelViewHolder) holder).follow_btn_image.setVisibility(View.INVISIBLE);

                }


                ((ChannelViewHolder) holder).follow_btn_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (db.is_followingYoutube(dataSet.get(listPosition).chanel.id)) {
                            db.deletePlaylistYoutube(dataSet.get(listPosition).chanel.id);
                        } else {
                            db.addPlaylistYoutube(dataSet.get(listPosition).chanel.id, dataSet.get(listPosition).chanel.id, "1");
                        }

                        notifyDataSetChanged();

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
