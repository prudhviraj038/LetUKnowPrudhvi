package in.co.letuknow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by mac on 3/18/17.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {

    NewsFragment newsFragment;
    CategoryFragment categoryFragment;
    MyChannelsFragment myChannelsFragment;
    VideosFragment videosFragment;
    MainActivity activity;



    public TabsAdapter(FragmentManager fm, MainActivity activity) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        Log.e("pos", String.valueOf(position));

//        if(position==0) {
//
//            homeFragment = HomeFragment.newInstance(position);
//
//            return homeFragment;
//        }
//
//        else if(position == 1)
//            return CurrencyChartFragmnet.newInstance("currency");
//
//        else if(position == 2){
//
//               newsFragment = NewsFragment.newInstance(position);
//
//            return newsFragment;
//        }
//
//        else if(position == 3) {
//
//            newsWebFragmnet = NewsWebFragmnet.newInstance("News");
//
//            return newsWebFragmnet;
//        }
//
//        else if(position == 4)
//            return SettingsFragment.newInstance(position);
//
//        else if(position == 5)
//            return GoldPricesFragmnet.newInstance("Gold Prices");
//
//        else if(position == 6)
//            return SilverPricesFragment.newInstance("Silver Prices");
//
//        else if(position == 7)
//            return OilPricesfragment.newInstance("Oil Prices");
//
//        else if(position == 8)
//            return ExchangeLocationsFragment.newInstance("Exchange Locations");
//
//        else if(position == 9)
//            return NewsCalendarFragment.newInstance("News Calendar");
//
//        else if(position == 10)
//            return AddCurrenciesFragment.newInstance(position);
//
//        else if (position == 11)
//            return ContactUsFragment.newInstance("Contact us",activity);
//
//        else if (position == 12)
//            return AboutFragment.newInstance("About us");
//
//        else
//            return DemoFragment.newInstance(position);

//



        if(position==0) {
            newsFragment = NewsFragment.newInstance(position);

            return newsFragment;

        }else if(position==1){

            myChannelsFragment = new MyChannelsFragment();

            return myChannelsFragment;

        }else if(position==2){

            videosFragment = VideosFragment.newInstance(position);

            return videosFragment;

        }
        else if(position==3){

            categoryFragment = CategoryFragment.newInstance(position);

            return categoryFragment;

        }else{
            return HomeFragment.newInstance(position);
        }

    }

    @Override
    public int getCount() {
        return 11;
    }
}
