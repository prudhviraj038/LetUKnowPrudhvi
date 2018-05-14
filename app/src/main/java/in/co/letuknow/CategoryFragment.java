package in.co.letuknow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by mac on 5/8/18.
 */

public class CategoryFragment extends Fragment {


    ListView cat_listview;
    ArrayList<Categories> categories = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    LinearLayout progress_layout;
    public static CategoryFragment newInstance(int someInt) {
        CategoryFragment myFragment = new CategoryFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        progress_layout = (LinearLayout) view.findViewById(R.id.progress_holder);
        cat_listview = (ListView) view.findViewById(R.id.category_listview);
        categoryAdapter = new CategoryAdapter(getActivity(),categories);
        cat_listview.setAdapter(categoryAdapter);

        cat_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(),ChannelsActivity.class);
                intent.putExtra("category",categories.get(i));
                startActivity(intent);

            }
        });

        getCategories();
        return view;
    }



    private void getCategories(){

        String url =Session.NOTIFY_SERVER_URL + "categories.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progress_layout.setVisibility(View.GONE);

                for(int i=0;i<jsonArray.length();i++){

                    try {
                        categories.add(new Categories(jsonArray.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                categoryAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progress_layout.setVisibility(View.GONE);
            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        progress_layout.setVisibility(View.VISIBLE);
    }



}
