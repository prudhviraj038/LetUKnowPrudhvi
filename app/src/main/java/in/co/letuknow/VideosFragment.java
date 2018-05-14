package in.co.letuknow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import in.co.letuknow.YoutubeVideos.YouTubeTabsAdapter;
import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by mac on 5/14/18.
 */

public class VideosFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{


    ViewPager viewPager ;
    YouTubeTabsAdapter youTubeTabsAdapter;
    public static VideosFragment newInstance(int someInt) {
        VideosFragment myFragment = new VideosFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        youTubeTabsAdapter= new YouTubeTabsAdapter(getChildFragmentManager());
        viewPager.setAdapter(youTubeTabsAdapter);
        SegmentedGroup segmented4 = (SegmentedGroup) view.findViewById(R.id.videos_segmented);
        segmented4.setTintColor(getResources().getColor(R.color.colorPrimary));
        segmented4.setOnCheckedChangeListener(this);

        return view;

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.seg_videos:
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.seg_channels:
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.seg_mychannels:
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.seg_wished:
                viewPager.setCurrentItem(3,false);
                break;
            default:
                // Nothing to do
        }
    }
}
