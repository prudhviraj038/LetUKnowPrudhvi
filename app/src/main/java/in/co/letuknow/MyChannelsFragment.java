package in.co.letuknow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by HP on 7/26/2016.
 */


public class MyChannelsFragment extends Fragment {

    GridView listView;
    RecyclerView channels_listview;
    ArrayList<Categories> categories;
    ArrayList<SectionedItem> sectionedItems ;
    ArrayList<SectionedItem> sectionedItems_all;
    MyChannelsAdapter sectionedAdapter;
    TextView label;
    ViewFlipper viewFlipper;
    EditText search_edit;
    LinearLayout progress_holder;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mychannels, container, false);
    }
    DatabaseHandler databaseHandler;
   // RecyclerView chipRecyclerView;
    //ListChipsAdapter listChipsAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
         databaseHandler = new DatabaseHandler(getActivity());
        View view = getView();
         //chipRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        //chipRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //chipRecyclerView.setHasFixedSize(true);



        categories = new ArrayList<>();
        sectionedItems = new ArrayList<>();
        sectionedItems_all = new ArrayList<>();


        channels_listview = (RecyclerView) view.findViewById(R.id.recyclerView);
        channels_listview.setLayoutManager(new LinearLayoutManager(getActivity()));
        sectionedAdapter  = new MyChannelsAdapter(sectionedItems,getActivity());



        progress_holder = (LinearLayout) view.findViewById(R.id.progress_holder);

        progress_holder.setVisibility(View.GONE);


        search_edit = (EditText) view.findViewById(R.id.et_search);


        search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                    sectionedItems.clear();
                    sectionedAdapter.notifyDataSetChanged();
                    for(int i=0;i<sectionedItems_all.size();i++){
                        sectionedItems.add(sectionedItems_all.get(i));
                    }

                    sectionedAdapter.notifyDataSetChanged();


                    return true;
                }
                return false;
            }
        });

        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("before", String.valueOf(s));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (s != null) {

                        if(s.toString().length()>0){

                            sectionedItems.clear();
                            sectionedAdapter.notifyDataSetChanged();


                            for (int i = 0; i < sectionedItems_all.size(); i++) {
                                if (sectionedItems_all.get(i).type.equals("2")) {
                                    if(searchHelper(sectionedItems_all.get(i).chanel.ch_title,s.toString())) {
                                        sectionedItems.add(sectionedItems_all.get(i));
                                        Log.e("aerch", String.valueOf(s));

                                    }
                                }
                            }
                            sectionedAdapter.notifyDataSetChanged();
                        }
                     else {
                            sectionedItems.clear();
                            sectionedAdapter.notifyDataSetChanged();
                            for(int i=0;i<sectionedItems_all.size();i++){
                                sectionedItems.add(sectionedItems_all.get(i));
                            }

                            sectionedAdapter.notifyDataSetChanged();
                        }
                }else{
                        sectionedItems.clear();
                        sectionedAdapter.notifyDataSetChanged();
                        for(int i=0;i<sectionedItems_all.size();i++){
                            sectionedItems.add(sectionedItems_all.get(i));
                        }

                        sectionedAdapter.notifyDataSetChanged();

                    }


                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                Log.e("after", String.valueOf(s.toString()));

            }
        });

        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());
        get_chanels(databaseHandler.all_selected_channels());

    }



    private void get_chanels(String list){



        String url = Session.NOTIFY_SERVER_URL + "chanels_cat.php?chanels=" + list;

        Log.e("url", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("response",jsonArray.toString());

                progress_holder.setVisibility(View.GONE);


                categories =new ArrayList<>();
                sectionedItems.clear();


                if(jsonArray.length()>0){
                    sectionedItems.add(new SectionedItem("Categories"));
                }

                for(int i=0;i<jsonArray.length();i++){

                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Categories categories = new Categories(jsonObject);
                        sectionedItems.add(new SectionedItem(categories));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                for(int i=0;i<jsonArray.length();i++){

                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Categories categories = new Categories(jsonObject);
                        sectionedItems.add(new SectionedItem(categories.get_title()));

                        for(int j=0;j<categories.chanels.size();j++){

                            sectionedItems.add(new SectionedItem(categories.chanels.get(j)));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                sectionedItems_all = new ArrayList<>(sectionedItems);

                sectionedAdapter  = new MyChannelsAdapter(sectionedItems,getActivity());
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



    private boolean searchHelper(String s1,String s2){

      return   Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE).matcher(s1).find();

    }

}
