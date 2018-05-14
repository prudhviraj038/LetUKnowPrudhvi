package in.co.letuknow.YoutubeVideos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;

import in.co.letuknow.AppController;
import in.co.letuknow.DatabaseHandler;
import in.co.letuknow.R;
import in.co.letuknow.Session;

/**
 * Created by mac on 5/8/18.
 */

public class VideosSubFragment extends Fragment {

    ArrayList<YoutubeVideo> youtubeVideos = new ArrayList<>();
    YoutubeVideoAdapter youtubeVideoAdapter ;
    RecyclerView recyclerView;
    LinearLayout progress_holder;
    public static VideosSubFragment newInstance(int someInt) {
        VideosSubFragment myFragment = new VideosSubFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_videos, container, false);
        TextView textView = (TextView) view.findViewById(R.id.message);
        textView.setText(String.valueOf(getArguments().getInt("someInt")));
        progress_holder = (LinearLayout) view.findViewById(R.id.progress_holder);
        youtubeVideoAdapter = new YoutubeVideoAdapter(getActivity(),youtubeVideos);
        recyclerView = (RecyclerView) view.findViewById(R.id.videos_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(youtubeVideoAdapter);

        getVideos();

        return view;
    }



private void getVideos(){

    String url = Session.SERVER_URL+"yt-videos.php?"+getVideoPageChannels();

    Log.e("url",url);

    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray jsonArray) {

            progress_holder.setVisibility(View.GONE);
            try {

                for (int i = 0; i < jsonArray.length(); i++) {

                    YoutubeVideo youtubeVideo = new YoutubeVideo(jsonArray.getJSONObject(i));
                    youtubeVideos.add(youtubeVideo);

                }

                youtubeVideoAdapter.notifyDataSetChanged();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

            volleyError.printStackTrace();
        }
    });

    AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    progress_holder.setVisibility(View.VISIBLE);

}

    private String getVideoPageChannels(){


        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());
        String selected_channels = databaseHandler.all_selected_channelsYoutube();

        if(selected_channels.equals("0"))
            return "";
        else
            return "chanel="+selected_channels;

    }


}
