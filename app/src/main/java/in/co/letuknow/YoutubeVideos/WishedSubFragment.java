package in.co.letuknow.YoutubeVideos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.co.letuknow.R;

/**
 * Created by mac on 5/8/18.
 */

public class WishedSubFragment extends Fragment {


    RecyclerView recyclerView;
    public static WishedSubFragment newInstance(int someInt) {
        WishedSubFragment myFragment = new WishedSubFragment();

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
        recyclerView = (RecyclerView) view.findViewById(R.id.videos_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

}
