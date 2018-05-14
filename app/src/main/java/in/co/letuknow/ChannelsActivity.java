package in.co.letuknow;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by mac on 5/11/18.
 */

public class ChannelsActivity extends AppCompatActivity {

    Categories categories ;
    ListView listView;
    ChannelsAdapter channelsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);
        categories = (Categories) getIntent().getSerializableExtra("category");
        listView = (ListView) findViewById(R.id.channels_listview);
        channelsAdapter = new ChannelsAdapter(this,categories.chanels);
        listView.setAdapter(channelsAdapter);

    }
}
