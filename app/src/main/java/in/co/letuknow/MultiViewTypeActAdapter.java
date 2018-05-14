package in.co.letuknow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.blurry.Blurry;


/**
 * Created by anupamchugh on 09/02/16.
 */




public class MultiViewTypeActAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    MainActivity mainActivity;
    LinearLayout dim_bg;
    private ArrayList<News> dataSet;
    Context mContext;
    int total_types;
    MediaPlayer mPlayer;
    SharedPreferences sharedPreferences;
    private boolean fabStateVolume = false;
    Picasso picasso;
    boolean no_image;



    int lastPosition = -1;


    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {


        TextView ch_name,news_title;
        TextView news_time;
        ImageView share_btn_item;

        CircleImageView ch_logo;




        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.ch_name = (TextView) itemView.findViewById(R.id.news_ch_title);
            this.news_time = (TextView) itemView.findViewById(R.id.news_time2);

            this.news_title = (TextView) itemView.findViewById(R.id.news_title);
            this.ch_logo = (CircleImageView) itemView.findViewById(R.id.logo);
            this.share_btn_item = (ImageView) itemView.findViewById(R.id.share_btn_item);

        }

    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        ImageView image,blur_image;

        TextView ch_name,news_title;
        TextView news_time;

        CircleImageView ch_logo;


        ImageView share_btn_item;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

           // this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.image = (ImageView) itemView.findViewById(R.id.news_img);
            this.blur_image = (ImageView) itemView.findViewById(R.id.blur_image);
            this.ch_name = (TextView) itemView.findViewById(R.id.news_ch_title);
            this.news_time = (TextView) itemView.findViewById(R.id.news_time2);

            this.news_title = (TextView) itemView.findViewById(R.id.news_title);
            this.ch_logo = (CircleImageView) itemView.findViewById(R.id.logo);
            this.share_btn_item = (ImageView) itemView.findViewById(R.id.share_btn_item);

        }

    }

    public static class AudioTypeViewHolder extends RecyclerView.ViewHolder {


        LinearLayout mPublisherAdView;


        public AudioTypeViewHolder(View itemView) {
            super(itemView);

         //   this.txtType = (TextView) itemView.findViewById(R.id.type);
         //   this.fab = (FloatingActionButton) itemView.findViewById(R.id.fab);

          //  this.mPublisherAdView = (LinearLayout) itemView.findViewById(R.id.publisherAdView);


        }

    }


    public static class FooterTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;

        public FooterTypeViewHolder(View itemView) {
            super(itemView);


        }

    }




    public MultiViewTypeActAdapter(ArrayList<News> data, Context context, MainActivity mainActivity) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
        this.mainActivity = mainActivity;


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public MultiViewTypeActAdapter(ArrayList<News> data, Context context, MainActivity mainActivity, LinearLayout dim_bg) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
        this.mainActivity = mainActivity;
        this.dim_bg = dim_bg;


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    private void load_chanel_image(String url, CircleImageView circleImageView){
       Picasso.with(mContext).load(url).into(circleImageView);
    }

    private void load_news_image(String url, final ImageView imageView,final ImageView blur_imageView){

       // Picasso.with(mContext).load(url).into(imageView);
        //Picasso.with(mContext).load(url).into(blur_imageView);


        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.loading)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded (final Bitmap bitmap, Picasso.LoadedFrom from){
            /* Save the bitmap or do something with it here */

                        //Set it in the ImageView
                       // imageView.setVisibility(View.VISIBLE);
                        //blur_imageView.setVisibility(View.VISIBLE);

                        imageView.setImageBitmap(bitmap);
                       // Blurry.with(mContext).from(bitmap).into(blur_imageView);


                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                        //blur_imageView.setImageDrawable(placeHolderDrawable);

                    }
                });





    }


    private void share_news(News whatsapp_str, View aView){

//                    Intent sendIntent = new Intent();
//                    sendIntent.setAction(Intent.ACTION_SEND);
//                    sendIntent.putExtra(Intent.EXTRA_TEXT, whatsapp_str);
//                    sendIntent.setType("text/plain");
//                    mContext.startActivity(sendIntent);

//         show_share(aView);
//show_new_popup(aView,whatsapp_str);


    }

    private static final int ID_FB     = 1;
    private static final int ID_TW   = 2;
    private static final int ID_WP = 3;
    private static final int ID_INS   = 4;
    private static final int ID_MSG  = 5;
    private static final int ID_MAI     = 6;



//    void show_new_popup(View v, final News news){
//
//        ActionItem nextItem 	= new ActionItem(ID_FB, "", mContext.getResources().getDrawable(R.drawable.facebook_square));
//        ActionItem prevItem 	= new ActionItem(ID_TW, "", mContext.getResources().getDrawable(R.drawable.twitter_square));
//        //ActionItem searchItem 	= new ActionItem(ID_WP, "", mContext.getResources().getDrawable(R.drawable.whats_up_square));
//        ActionItem infoItem 	= new ActionItem(ID_INS, "", mContext.getResources().getDrawable(R.drawable.instagram_ic));
//        //ActionItem eraseItem 	= new ActionItem(ID_MSG, "", mContext.getResources().getDrawable(R.drawable.messaging_icon));
//        //ActionItem okItem 		= new ActionItem(ID_MAI, "", mContext.getResources().getDrawable(R.drawable.mail_icon_square));
//
//        //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
//        // prevItem.setSticky(true);
//        //nextItem.setSticky(true);
//
//        //create QuickAction. Use QuickAction.VERTICAL or QuickAction.HORIZONTAL param to define layout
//        //orientation
//        final QuickAction quickAction = new QuickAction(mContext, QuickAction.HORIZONTAL);
//
//        //add action items into QuickAction
//        quickAction.addActionItem(nextItem);
//        quickAction.addActionItem(prevItem);
//       // quickAction.addActionItem(searchItem);
//        quickAction.addActionItem(infoItem);
//        //quickAction.addActionItem(eraseItem);
//        //quickAction.addActionItem(okItem);
//
//        //Set listener for action item clicked
//        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
//            @Override
//            public void onItemClick(QuickAction source, int pos, int actionId) {
//                ActionItem actionItem = quickAction.getActionItem(pos);
//                if(dim_bg!=null)
//                    dim_bg.setVisibility(View.GONE);
//
//                //here we can filter which action item was clicked with pos or actionId parameter
//                if (actionId == ID_FB) {
//                    share_fb(news);
//                } else if (actionId == ID_TW) {
//                    share_tw(news);
//                } else if (actionId == ID_WP){
//                    share_wp(news);
//                }else if (actionId == ID_INS) {
//                    share_ins(news);
//                } else if (actionId == ID_MSG) {
//                    share_msg(news);
//                } else if (actionId == ID_MAI){
//                    share_mail(news);
//                }
//            }
//        });
//
//        //set listnener for on dismiss event, this listener will be called only if QuickAction dialog was dismissed
//        //by clicking the area outside the dialog.
//        quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                // Toast.makeText(getApplicationContext(), "Dismissed", Toast.LENGTH_SHORT).show();
//                if(dim_bg!=null)
//                    dim_bg.setVisibility(View.GONE);
//            }
//        });
//
//        quickAction.show(v);
//        if(dim_bg!=null)
//           dim_bg.setVisibility(View.VISIBLE);
//    }



    private void go_to_news_detail(News news){
//        if(news.link.startsWith("http"))
//        mainActivity.goto_news_detail(news);
    }
    private void go_to_chanel_detail(News news){
//        if(news.link.startsWith("http"))
//            mainActivity.goto_news_detail(news);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {

            case News.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_no_image, parent, false);
                return new TextTypeViewHolder(view);

            case News.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
                return new ImageTypeViewHolder(view);

            case News.AUDIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
                return new AudioTypeViewHolder(view);

//            case News.FOOTER_TYPE:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
//                return new FooterTypeViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

//        if(position==dataSet.size()-1)
//            return News.FOOTER_TYPE;
//

        switch (dataSet.get(position).view_type) {
            case 0:
                return News.TEXT_TYPE;
            case 1:
                if(no_image)
                return News.TEXT_TYPE;
                else
                return News.IMAGE_TYPE;
            case 2:
                return News.AUDIO_TYPE;

            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {



        final News object = dataSet.get(listPosition);


        //if (object != null && listPosition!=dataSet.size()-1) {




            switch (object.view_type) {

                case News.TEXT_TYPE:
                    final TextTypeViewHolder holdermin = (TextTypeViewHolder) holder;
                    //((TextTypeViewHolder) holder).txtType.setText(object.News);
                    holdermin.ch_name.setText(object.chanels.get_ch_title(mContext));
                    holdermin.news_time.setText(object.get_time(mContext));

                    holdermin.news_title.setText(Html.fromHtml(object.title));

                    holdermin.news_title.setTextSize(sharedPreferences.getInt("font_sizee", 17));
                    load_chanel_image(object.chanels.ch_image,holdermin.ch_logo);

                    holdermin.news_title.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            go_to_news_detail(object);
                        }
                    });

                    holdermin.ch_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            go_to_chanel_detail(object);
                        }
                    });
                    holdermin.ch_logo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            go_to_chanel_detail(object);
                        }
                    });






                    holdermin.share_btn_item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            share_news(object,holdermin.share_btn_item);

                        }
                    });



                    break;

                case News.IMAGE_TYPE:
                  //  ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                   // ((ImageTypeViewHolder) holder).image.setImageResource(object.data);


                    if(no_image){
                        final TextTypeViewHolder holderminno = (TextTypeViewHolder) holder;

                        holderminno.ch_name.setText(object.chanels.get_ch_title(mContext));
                        load_chanel_image(object.chanels.ch_image,holderminno.ch_logo);

                        ((TextTypeViewHolder) holder).ch_name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                go_to_chanel_detail(object);
                            }
                        });

                        holderminno.ch_logo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                go_to_chanel_detail(object);
                            }
                        });



                        holderminno.news_time.setText(object.get_time(mContext));
                        holderminno.news_title.setTextSize(sharedPreferences.getInt("font_sizee", 17));
                        holderminno.news_title.setText(Html.fromHtml(object.title));

                        holderminno.news_title.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                go_to_news_detail(object);
                            }
                        });





                        holderminno.share_btn_item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                share_news(object,holderminno.share_btn_item);
                            }
                        });






                    }else{

                        ((ImageTypeViewHolder) holder).ch_name.setText(object.chanels.get_ch_title(mContext));
                        ((ImageTypeViewHolder) holder).news_time.setText(object.get_time(mContext));
                        ((ImageTypeViewHolder) holder).news_title.setTextSize(sharedPreferences.getInt("font_sizee", 17));

                        ((ImageTypeViewHolder) holder).news_title.setText(Html.fromHtml(object.title));
                        load_chanel_image(object.chanels.ch_image,((ImageTypeViewHolder) holder).ch_logo);

                        load_news_image(object.image,((ImageTypeViewHolder) holder).image,((ImageTypeViewHolder) holder).blur_image);


                        ((ImageTypeViewHolder) holder).news_title.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                go_to_news_detail(object);
                            }
                        });

                        ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                go_to_news_detail(object);
                            }
                        });
                        ((ImageTypeViewHolder) holder).ch_name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                go_to_chanel_detail(object);
                            }
                        });
                        ((ImageTypeViewHolder) holder).ch_logo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                go_to_chanel_detail(object);
                            }
                        });






                        ((ImageTypeViewHolder) holder).share_btn_item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                share_news(object,((ImageTypeViewHolder) holder).share_btn_item);
                            }
                        });
                    }
                    break;

                case News.AUDIO_TYPE:

                   // ((AudioTypeViewHolder) holder).txtType.setText(object.text);


//                    ((AudioTypeViewHolder) holder).fab.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//                            if (fabStateVolume) {
//                                if (mPlayer.isPlaying()) {
//                                    mPlayer.stop();
//
//                                }
//                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.volume);
//                                fabStateVolume = false;
//
//                            } else {
//                                mPlayer = MediaPlayer.create(mContext, R.raw.sound);
//                                mPlayer.setLooping(true);
//                                mPlayer.start();
//                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.mute);
//                                fabStateVolume = true;
//
//                            }
//                        }
//                    });



                    ((AudioTypeViewHolder) holder).mPublisherAdView.removeAllViews();

                   // PublisherAdView mPublisherAdView;
                    //mPublisherAdView = new PublisherAdView(mContext);
                    //mPublisherAdView.setAdSizes(new AdSize(300,250));
                    //mPublisherAdView.setAdUnitId(object.title);
                    //PublisherAdRequest adRequest = new PublisherAdRequest.Builder().
                            // addTestDevice("6CF13E43F2584625AF6152F65DAC084E").
                            //addTestDevice("DF05CE517F21FBE0F3D2BC342BBEBCD9").
                            //addTestDevice(PublisherAdRequest.DEVICE_ID_EMULATOR).
                      //              build();
                    //((AudioTypeViewHolder) holder).mPublisherAdView.addView(mPublisherAdView);
                    //mPublisherAdView.loadAd(adRequest);
                    break;
            }
        }
   // }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }



    private void share_fb(final News news){

//        Intent i = new Intent(Intent.ACTION_SEND);
//        i.setType("text/plain");
//             i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
//             i.putExtra(Intent.EXTRA_SUBJECT, html2text(news.title));
//        //i.putExtra(Intent.EXTRA_TEXT, html2text(news.title));
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.setPackage("com.facebook.katana");
//        try {
//            mContext.startActivity(i);
//        } catch (Exception e) {
//
//            try {
//                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.facebook.katana")));
//            } catch (android.content.ActivityNotFoundException anfe) {
//                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.facebook.katana")));
//            }
//        }





        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("please wait..");
        progressDialog.show();

        // progressDialog.setCancelable(false);

        Picasso.with(mContext).load(news.insta_img).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                if(progressDialog!=null)
                    progressDialog.dismiss();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                i.putExtra(Intent.EXTRA_SUBJECT, html2text(news.title));
                i.putExtra(Intent.EXTRA_TEXT, html2text(news.link));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setPackage("com.facebook.katana");
                try {
                    mContext.startActivity(i);
                } catch (Exception e) {

                    try {
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.facebook.katana")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.facebook.katana")));
                    }
                }

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if(progressDialog!=null)
                    progressDialog.dismiss();

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

//                            if(progressDialog!=null)
//                                progressDialog.dismiss();

            }
        });


    }
    private void share_tw(News news){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        //     i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
        //     i.putExtra(Intent.EXTRA_SUBJECT, html2text(news.title));
        i.putExtra(Intent.EXTRA_TEXT, html2text(news.title));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setPackage("com.twitter.android");
        try {
            mContext.startActivity(i);
        } catch (Exception e) {

            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.twitter.android")));
            } catch (android.content.ActivityNotFoundException anfe) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.twitter.android")));
            }
        }


    }
    private void share_wp(News news){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, html2text(news.title));
        i.putExtra(Intent.EXTRA_TEXT, html2text(news.title));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setPackage("com.whatsapp");
        try {
            mContext.startActivity(i);
        } catch (Exception e) {

            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.whatsapp")));
            } catch (android.content.ActivityNotFoundException anfe) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.whatsapp")));
            }
        }



    }
    private void share_ins(final News news){
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("please wait..");
        progressDialog.show();

        // progressDialog.setCancelable(false);

        Picasso.with(mContext).load(news.insta_img).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                if(progressDialog!=null)
                    progressDialog.dismiss();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                i.putExtra(Intent.EXTRA_SUBJECT, html2text(news.title));
                i.putExtra(Intent.EXTRA_TEXT, html2text(news.link));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setPackage("com.instagram.android");
                try {
                    mContext.startActivity(i);
                } catch (Exception e) {

                    try {
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.instagram.android")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.instagram.android")));
                    }
                }

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if(progressDialog!=null)
                    progressDialog.dismiss();

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

//                            if(progressDialog!=null)
//                                progressDialog.dismiss();

            }
        });
    }





    private void share_msg(News news){
        String smsBody=html2text(news.title);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", smsBody);
        sendIntent.setType("vnd.android-dir/mms-sms");
        mContext.startActivity(sendIntent);


    }


    private void share_mail(News news){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, html2text(news.title));
        i.putExtra(Intent.EXTRA_TEXT, html2text(news.title));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setPackage("com.google.android.gm");
        try {
            mContext.startActivity(i);
        } catch (Exception e) {

            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.google.android.gm")));
            } catch (android.content.ActivityNotFoundException anfe) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.google.android.gm")));
            }
        }
    }



    public static String html2text(String html) {
        //return Jsoup.parse(html).text();
        return html;
    }


    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file =  new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }




}
