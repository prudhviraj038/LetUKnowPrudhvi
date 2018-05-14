package in.co.letuknow.YoutubeVideos;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import in.co.letuknow.MultiViewTypeActAdapter;
import in.co.letuknow.R;

/**
 * Created by mac on 5/14/18.
 */

public class YoutubeVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    ArrayList<YoutubeVideo> youtubeVideos = new ArrayList<>();
    Context context ;


    YoutubeVideoAdapter(Context context,ArrayList<YoutubeVideo> youtubeVideos){

        this.youtubeVideos = youtubeVideos;
        this.context = context;

    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView ch_name,news_title;
        TextView news_time;
        ImageView share_btn_item;
        ImageView news_image;
        CircleImageView ch_logo;

        public VideoViewHolder(View itemView) {
            super(itemView);

            this.ch_name = (TextView) itemView.findViewById(R.id.news_ch_title);
            this.news_time = (TextView) itemView.findViewById(R.id.news_time2);
            this.news_title = (TextView) itemView.findViewById(R.id.news_title);
            this.ch_logo = (CircleImageView) itemView.findViewById(R.id.logo);
            this.share_btn_item = (ImageView) itemView.findViewById(R.id.share_btn_item);
            this.news_image = (ImageView) itemView.findViewById(R.id.news_img);
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new YoutubeVideoAdapter.VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        VideoViewHolder videoViewHolder = (VideoViewHolder)holder;

        videoViewHolder.ch_name.setText(youtubeVideos.get(position).title_original);
        videoViewHolder.news_title.setText(youtubeVideos.get(position).title_original);
        videoViewHolder.news_time.setText(youtubeVideos.get(position).time);

        Picasso.with(context).load(youtubeVideos.get(position).medium).into(videoViewHolder.news_image);
        Picasso.with(context).load(youtubeVideos.get(position).youtubeChannel.image).into(videoViewHolder.ch_logo);

    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }
}
