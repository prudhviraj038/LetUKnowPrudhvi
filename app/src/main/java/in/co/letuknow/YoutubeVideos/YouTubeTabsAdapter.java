package in.co.letuknow.YoutubeVideos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.co.letuknow.HomeFragment;

/**
 * Created by mac on 5/14/18.
 */

public class YouTubeTabsAdapter extends FragmentStatePagerAdapter {
    ChannelsSubFragment channelsSubFragment;
    MyChannelsSubFragment myChannelsSubFragment;
    VideosSubFragment videosSubFragment;
    WishedSubFragment wishedSubFragment;


    public YouTubeTabsAdapter(FragmentManager fm) {

        super(fm);


    }

    @Override
    public Fragment getItem(int position) {

        if(position==1){

            channelsSubFragment = ChannelsSubFragment.newInstance(position);

            return channelsSubFragment;
        }else if(position==2){

            myChannelsSubFragment = MyChannelsSubFragment.newInstance(position);

            return  myChannelsSubFragment;
        }else if(position==0){

            videosSubFragment = VideosSubFragment.newInstance(position);

            return videosSubFragment;
        }else if (position==3){

            wishedSubFragment = WishedSubFragment.newInstance(position);
            return wishedSubFragment;
        }


        return HomeFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 4;
    }



}
