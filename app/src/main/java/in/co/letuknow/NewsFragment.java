package in.co.letuknow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 3/18/17.
 */

public class NewsFragment extends Fragment {
    RecyclerView recyclerView;
    MultiViewTypeActAdapter adapter;
    int visibleItemCount,totalItemCount,firstVisibleItemIndex;
    String last_loaded_id="0";
    MainActivity mainActivity;
    String url = Session.SERVER_URL+"feeds-new3.php?";

    public static NewsFragment newInstance(int someInt) {
        NewsFragment myFragment = new NewsFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        dim_bg = (LinearLayout) view.findViewById(R.id.dim_bg);

        mainActivity = (MainActivity) getActivity();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        news = new ArrayList<>();
        adapter = new MultiViewTypeActAdapter(news,getActivity(),mainActivity,dim_bg);
        recyclerView.setAdapter(adapter);
       // recyclerView.getRecycledViewPool().setMaxRecycledViews(News.IMAGE_TYPE, 0);
        progress_holder = (LinearLayout) view.findViewById(R.id.progress_holder);

        progress_holder.setVisibility(View.GONE);
        dim_bg.setVisibility(View.GONE);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItemIndex = mLayoutManager.findFirstVisibleItemPosition();


                if (((totalItemCount) - visibleItemCount) <= firstVisibleItemIndex ) {
                    // loading NOT in progress and end of list has been reached
                    // also triggered if not enough items to fill the screen
                    // if you start loading


                    Log.e("reached", "climax");

                    if(!last_loaded_id.equals(news.get(news.size()-1).id)) {
                        get_news(getHomePageChannels(),false);
                        last_loaded_id = news.get(news.size() - 1).id;
                    }

                } else if (firstVisibleItemIndex == 0) {

                    // top of list reached



                }



            }

        });




        get_news(getHomePageChannels(),true);

        return view;
    }



    ArrayList<News> news = new ArrayList<>();
    LinearLayout progress_holder;
    LinearLayout dim_bg;

    private void get_news(String url_append, final boolean clear){
        String fi_url=url;

        if(clear) {
            news.clear();
            Log.e("url",url+url_append);
            fi_url=url+url_append;
            show_progress();
        }else{
            Log.e("url",url+url_append+"&last_id="+news.get(news.size()-1).id);
            fi_url=url+url_append+"&last_id="+news.get(news.size()-1).id;
            last_loaded_id=news.get(news.size()-1).id;
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(fi_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {




                for(int i=0;i<jsonArray.length();i++){

                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        news.add(new News(jsonObject,getActivity()));

                    } catch (JSONException e) {
                        adapter.notifyDataSetChanged();
                        e.printStackTrace();
                    }

                    if(jsonArray.length()==0){
                         Toast.makeText(getActivity(),"no feeds to display",Toast.LENGTH_SHORT).show();
                    }
                }


                if(clear)
                    hide_progress();
                adapter.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("error",volleyError.toString());

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }




    public void show_progress() {
        progress_holder.setVisibility(View.VISIBLE);
    }


    public void hide_progress() {
        progress_holder.setVisibility(View.GONE);
    }


    private String getHomePageChannels(){


        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());
        String selected_channels = databaseHandler.all_selected_channels();

        if(selected_channels.equals("0"))
            return "";
        else
            return "chanels="+selected_channels;

    }

}
