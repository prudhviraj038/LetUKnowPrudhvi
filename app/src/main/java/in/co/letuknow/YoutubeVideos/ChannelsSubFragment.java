package in.co.letuknow.YoutubeVideos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.co.letuknow.AppController;
import in.co.letuknow.Categories;
import in.co.letuknow.DatabaseHandler;
import in.co.letuknow.MyChannelsAdapter;
import in.co.letuknow.R;
import in.co.letuknow.Session;

/**
 * Created by mac on 5/8/18.
 */

public class ChannelsSubFragment extends Fragment {




    RecyclerView channels_listview;
    ArrayList<Categories> categories;
    ArrayList<SectionedItem> sectionedItems ;
    ArrayList<SectionedItem> sectionedItems_all;
    YoutubeChannelsAdapter sectionedAdapter;
    LinearLayout progress_holder;

    public static ChannelsSubFragment newInstance(int someInt) {
        ChannelsSubFragment myFragment = new ChannelsSubFragment();

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

        categories = new ArrayList<>();
        sectionedItems = new ArrayList<>();
        sectionedItems_all = new ArrayList<>();


        channels_listview = (RecyclerView) view.findViewById(R.id.videos_recycler);
        channels_listview.setLayoutManager(new LinearLayoutManager(getActivity()));
        sectionedAdapter  = new YoutubeChannelsAdapter(sectionedItems,getActivity());



        progress_holder = (LinearLayout) view.findViewById(R.id.progress_holder);

        progress_holder.setVisibility(View.GONE);


        get_chanels();

        return view;
    }



    private void get_chanels(){



        String url = Session.SERVER_URL + "yt-full.php";

        Log.e("url", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("response",jsonArray.toString());

                progress_holder.setVisibility(View.GONE);


                categories =new ArrayList<>();
                sectionedItems.clear();


//                if(jsonArray.length()>0){
//                    sectionedItems.add(new SectionedItem("Categories"));
//                }



//                for(int i=0;i<jsonArray.length();i++){
//
//                    try {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        YCategories categories = new YCategories(jsonObject);
//                        sectionedItems.add(new SectionedItem(categories));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }




                for(int i=0;i<jsonArray.length();i++){

                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        YCategories categories = new YCategories(jsonObject);
                        sectionedItems.add(new SectionedItem(categories.get_title()));

                        for(int j=0;j<categories.chanels.size();j++){

                            sectionedItems.add(new SectionedItem(categories.chanels.get(j)));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                sectionedItems_all = new ArrayList<>(sectionedItems);

                sectionedAdapter  = new YoutubeChannelsAdapter(sectionedItems,getActivity());
                channels_listview.setAdapter(sectionedAdapter);

                sectionedAdapter.notifyDataSetChanged();


//                if(chanels.size()==0){
//                  //  Toast.makeText(getActivity(), "You have not selected any sources", Toast.LENGTH_SHORT).show();
//                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                progress_holder.setVisibility(View.GONE);
                Log.e("error",volleyError.toString());

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        progress_holder.setVisibility(View.VISIBLE);
    }



}
