package in.co.letuknow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.co.letuknow.YoutubeVideos.YoutubeDatabaseHandler;

/**
 * Created by mac on 5/12/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TABLE_CHANNELS = "channels";
    private static final String TABLE_YCHANNELS = "ychannels";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NOTIFY = "notify";


    public DatabaseHandler(Context context) {

        super(context, "letuknow", null, 4);

    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        String CREATE_PLAYLIST_TABLE = "CREATE TABLE " + TABLE_CHANNELS + "("
                + KEY_ID + " INTEGER ," + KEY_NAME +  " TEXT," + KEY_NOTIFY + " TEXT " + ")";
        sqLiteDatabase.execSQL(CREATE_PLAYLIST_TABLE);

        String CREATE_PLAYLIST_YTABLE = "CREATE TABLE " + TABLE_YCHANNELS + "("
                + KEY_ID + " INTEGER ," + KEY_NAME +  " TEXT," + KEY_NOTIFY + " TEXT " + ")";
        sqLiteDatabase.execSQL(CREATE_PLAYLIST_YTABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CHANNELS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_YCHANNELS);
        onCreate(sqLiteDatabase);
    }





    public void addPlaylist(String id,String name,String notify) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_NAME, name);
        values.put(KEY_NOTIFY, notify);
        db.insert(TABLE_CHANNELS, null, values);
        db.close();
    }


    void updatenotify(String id,String notify) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NOTIFY, notify);
        db.update(TABLE_CHANNELS, cv, KEY_ID+"="+id, null);
        db.close();
    }


   public boolean is_following(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CHANNELS, new String[]{KEY_ID}, KEY_ID + "=?",
                new String[]{id}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.close();
                db.close();
                return true;
            } else {
                cursor.close();
                db.close();
            }
        }
        return false;

    }

    public  boolean is_notification_enabled(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CHANNELS, new String[]{KEY_ID,
                        KEY_NAME}, KEY_ID + "=?" + " and " + KEY_NOTIFY + "=?",
                new String[]{id, "1"}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
        }
    }

        return false;
    }


    public void deletePlaylist(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHANNELS, KEY_ID + " = ?",
                new String[] { id});
        db.close();
    }




    public String  all_selected_channels(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select * from "+TABLE_CHANNELS,null);

        if (cursor != null) {
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {

                String append="";

                for (int i = 0; i <cursor.getCount();i++) {


                        if(append.equals(""))
                            append=cursor.getString(cursor.getColumnIndex(KEY_ID));
                        else
                            append=append+","+cursor.getString(cursor.getColumnIndex(KEY_ID));

                    cursor.moveToNext();
                }
                if(append.equals("")){
                    cursor.close();
                    db.close();
                    return  "0";
                }

                cursor.close();
                db.close();
                return append;
            }

            cursor.close();
        }
        db.close();
        return "0";
    }




    //youtube methods



   public void addPlaylistYoutube(String id,String name,String notify) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_NAME, name);
        values.put(KEY_NOTIFY, notify);
        db.insert(TABLE_YCHANNELS, null, values);
        db.close();
    }


    public void updatenotifyYoutube(String id,String notify) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NOTIFY, notify);
        db.update(TABLE_YCHANNELS, cv, KEY_ID+"="+id, null);
        db.close();
    }


    public boolean is_followingYoutube(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_YCHANNELS, new String[]{KEY_ID}, KEY_ID + "=?",
                new String[]{id}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.close();
                db.close();
                return true;
            } else {
                cursor.close();
                db.close();
            }
        }
        return false;

    }

    public boolean is_notification_enabledYoutube(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_YCHANNELS, new String[]{KEY_ID,
                        KEY_NAME}, KEY_ID + "=?" + " and " + KEY_NOTIFY + "=?",
                new String[]{id, "1"}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.close();
                db.close();
                return true;
            } else {
                cursor.close();
                db.close();
            }
        }

        return false;
    }


    public void deletePlaylistYoutube(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_YCHANNELS, KEY_ID + " = ?",
                new String[] { id});
        db.close();
    }




    public String  all_selected_channelsYoutube(){


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select * from "+TABLE_YCHANNELS,null);

        if (cursor != null) {
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {

                String append="";

                for (int i = 0; i <cursor.getCount();i++) {


                    if(append.equals(""))
                        append=cursor.getString(cursor.getColumnIndex(KEY_ID));
                    else
                        append=append+","+cursor.getString(cursor.getColumnIndex(KEY_ID));

                    cursor.moveToNext();
                }
                if(append.equals("")){
                    cursor.close();
                    db.close();
                    return  "0";
                }

                cursor.close();
                db.close();
                return append;
            }

            cursor.close();
        }
        db.close();
        return "0";
    }


}
